<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/5/14
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>学生端</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../../css/build.css"/>
    <link rel="stylesheet"  href="../../css/qq.css"/>
</head>
<body>
<div class="qqBox">
    <div class="context">
        <div class="conLeft">
            <ul id="userList">
                <div>
                    <input type="button" value="刷新" id="flush" style="margin-left: 30px">
                </div>

            </ul>
        </div>
        <div class="conRight">
            <div class="Righthead">
                <div class="headName" id="headName">${sessionScope.username}</div>
            </div>
            <div class="RightCont">
                <ul class="newsList" id="message">

                </ul>
            </div>
            <div class="RightMiddle">
                <div class="file">
                    <img src="/img/file.jpg" height="30px" width="30px" onclick="fileSelect()">
                </div>
                <div id="setP" style="float: left ;margin-left: 20px" >
                    <a href="/chat" target="_blank">
                        <img src="/img/nimingtupian.jpg" height="30px" width="30px">
                    </a>
                </div>
            </div>
            <div class="RightFoot">

                    <textarea id="dope"
                              style="width: 100%;height: 100%; border: none;outline: none;padding:20px 0 0 3%;" name=""
                              rows="" cols=""></textarea>
                <button id="fasong" class="sendBtn" onclick="sendMessage()" style="border-radius: 5px">发送</button>
            </div>
        </div>
        <div id="fileSystem" class="conRightDoc" style="display: none">
            <ul>
                <div>
                    <input type="button" value="刷新" onclick="flushFile()">
                    <form  id="upload" method="post" enctype="multipart/form-data"
                           style="width:auto;">
                        <%--进行文件管理的跳转,这里使用一个fileName进行班级Id的保存 --%>
                        <input type="text" name="uploadUser" style="display: none" value="${sessionScope.username}">
                        <input type="text" name="classId" id="classId" style="display: none">
                        <input type="text" name="fileName" id="fileName">
                        <input type="file" name="file" id="fileContent" onchange="filePath()">
                        <input type="button" value="上传" onclick="uploadFile()">
                    </form>
                </div>
            </ul>
            <ul  id="fileList">

            </ul>
        </div>
    </div>
</div>

<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>

<script type="text/javascript">

    // 文件的上传
    function uploadFile() {
        var form = new FormData(document.getElementById("upload"));
        $.ajax({
            url: "/upLoadFile",
            type: "POST",
            ContentType: "multipart/form-data;chartset=utf-8",
            data: form,
            dataType: "JSON",
            headers: {
                "cache-control": "no-cache"
            },
            processData: false,
            contentType: false,
            success: function (data) {
                if ("1" == data) {
                    alert("上传成功")
                } else if ("2" == data) {
                    alert("上传失败");
                } else {
                    alert("发生错误");
                }
            },
            error: function (e) {
                alert("请求错误");
            }
        });
    }
    // 文件刷新
    var claId = ${requestScope.classId};
    $("#classId").val(claId);
    function flushFile() {
        console.log(claId);
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/findFileByClassId",
            type: "GET",
            dataType: "JSON",
            data: {
                claId: claId
            },
            success: function (data) {
                if (data == "nofile") {
                    alert("该班级没有文件");
                } else {
                    $("#fileList").empty();
                    for (var i = 0; i < data.length; ++i) {
                        var path = encodeURI(data[i].filePath);
                        var name = encodeURI(data[i].fileName);
                        var htmstr = '<div>' + getLocalTime(data[i].date) + ' ' + data[i].fileName + ' '
                            + data[i].upLoadUserName +
                                '<a href= "/fileDownload?filePath='+path+'&fileName='+name+'">下载</a>'+
                            // '<input type="button" value="下载" name="'+data[i].filePath+'" id="'+data[i].fileName+'" onclick="downLoadFile(this.name,this.id)"> ' +
                            '</div>';
                        $("#fileList").append(htmstr);
                    }
                }
            },
            error: function () {
                alert("访问错误")
            }
        })
    }

    function downLoadFile(filePath, fileName) {
        console.log("丑呼颖"+fileName+"  " + filePath);

        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/fileDownload",
            type: "GET",
            dataType: "JSON",
            data: {
                filePath : filePath,
                fileName : fileName
            },
            success : function (data) {
                console.log(data);
            }
        })
    }

    // 显示文件名字
    function filePath() {
        $("#fileName").val($("#fileContent").val());
    }

    // 显示文件
    var i = 0;
    function fileSelect() {
        if (i%2 == 0) {
            document.getElementById("fileSystem").style.display="block";
        }else {
            document.getElementById("fileSystem").style.display="none";
        }
        ++i;
    }

</script>

<script type="text/javascript">
    // 全局变量，进行记录访问的是哪个班级的
    var changeClassId = ${requestScope.classId};
    function sendMessage() {
        var message = $("#dope").val();
        console.log(message);
        $("#dope").val("");
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/sendMessageOfStudent",
            type: "POST",
            dataType: "JSON",
            data: JSON.stringify({
                "classId" : changeClassId,
                "message" : message
            }),
            success : function (data) {
                if (data != "") {
                    var htmlstr = '<li><div class="answerHead"><img src="/img/2.png"></div><div class="answers">'
                        + '[本人]' + '   ' + getLocalTime(data.date) + '<br/>' +data.message + '</div></li>';
                    $("#message").append(htmlstr);
                    addGroup();
                } else {
                    alert("发送失败");
                }
            },
            error : function () {
                alert("请求错误");
            }
        })
    }

    // 左边群组列表,发起的时候是通过session进行身份的验证的
    $(function () {
        $("#flush").click(function () {

            $.ajax({
                scriptCharset: 'utf-8',
                contentType: "application/json;chartset=utf-8",
                url: "/getClassMember",
                type: "GET",
                dataType: "JSON",
                data : {
                    classId :${requestScope.classId}
                },
                success :function (data) {
                    if (undefined != data) {
                        console.log(data);
                        //返回的是字符串数组
                        $("#userList").empty();
                        for (var i = 0; i < data.length; ++i) {
                            var htmlstr = '<li><div><input type="button" value="' + data[i] + '" style="width: 100%; text-align: center; background: bisque"></div></li>';
                            $("#userList").append(htmlstr);
                        }
                        addGroup();
                        setClassNameOnChat(${requestScope.classId});
                    }
                },
                error : function () {
                    alert("访问错误")
                }
            })
        })
    });

    function setClassNameOnChat(c) {
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/getClassNameById",
            type: "GET",
            dataType: "JSON",
            data : {
                classId : c
            },
            success : function (data) {
                alert(data+"className");
                // TODO 需要进行修改样式
                var htm = ' <il style="right: 50px">'+data+'</il>';
                $("#headName").append(htm);
            },
            error : function () {
                alert("发生错误");
            }
        })
    }
    function addGroup() {
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/getMessage",
            type: "GET",
            dataType: "JSON",
            data : {
                classId : changeClassId
            },
            // 这里也可以是一个对象
            success : function (data) {
                $("#message").empty();
                console.log(data);
                if (data != "") {
                    var userPhone = ${sessionScope.user};
                    for (var i = 0; i < data.length; ++i) {
                        var type = "";
                        if (data[i].sendType == 1) {
                            type = data[i].role+" 老师:";
                        } else if (data[i].sendType == 0) {
                            type = " 学生:";
                        } else {
                            type = "未知身份:";
                        }
                        if (userPhone == data[i].sendPhone) {
                            // 本人的消息
                            var htmlstr = '<li><div class="answerHead"><img src="/img/2.png"></div><div class="answers">'
                                + '[本人]' + '   ' + getLocalTime(data[i].date) + '<br/>' +data[i].message + '</div></li>';
                            $("#message").append(htmlstr);
                        } else {
                            var rec = getLocalTime(data[i].date)
                                +  '<br/>'+ data[i].sendName + type
                                + '<br/>' + data[i].message;
                            setMessageInnerHTML(rec);
                        }
                    }
                }else {
                    alert("没有新的消息")
                }
            },
            error : function () {
                alert("访问错误");
            }
        })
    }

    function setMessageInnerHTML(innerHTML) {
        var msg = '<li>'
            + '<div class="nesHead">'
            + '<img src="/img/robot.jpg">'
            + ' </div>'
            + '<div class="news">'
            + innerHTML
            + '</div>'
            + '</li>';
        $("#message").append(msg);
    }

    function getLocalTime(tm) {
        var tt=new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, "         ")
        return tt;
    }

    //回车键发送消息
    $(document).keypress(function (e) {

        if ((e.keyCode || e.which) == 13) {
            $("#fasong").click();
        }

    });
</script>
</body>
</html>
