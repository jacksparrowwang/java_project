<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>老师端</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../../css/build.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/qq.css"/>
</head>
<body>

<div class="qqBox">
    <div class="context">
        <div class="conLeft">
            <ul id="userList">
                <div>
                    <input type="button" value="刷新" id="flush" style="width: 100%; text-align: center;min-height: 30px;">
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
                <div id="niming" style="float: left ;margin-left: 20px" >
                    <a href="/chat" target="_blank">
                        <img src="/img/nimingtupian.jpg" height="30px" width="30px" >
                    </a>
                </div>
                <div id="notify_image" style="float: left ;margin-left: 20px" >
                    <img src="/img/blankNotify.jpg" height="30px" width="30px" onclick="notifyShow()" >
                </div>
                <div id="setP" style="float: left ;margin-left: 20px" >
                    <img src="/img/shezhichegnyaun.jpg" height="30px" width="30px" onclick="setProperty()">
                </div>
            </div>
            <div class="RightFoot">
                    <textarea id="dope"
                              style="width: 100%;height: 100%; border: none;outline: none;padding:20px 0 0 3%;" name=""
                              rows="" cols=""></textarea>
                <button id="fasong" class="sendBtn" onclick="sendMessage()" style="border-radius: 5px">发送</button>
            </div>
        </div>
        <div id="fileSystem" class="conRightDoc" style="display: none;  border-right:2px solid navajowhite;">
            <ul>
                <div>
                    <h3>文件传输</h3>
                    <hr>
                    <input type="button" value="刷新" onclick="flushFile()">
                    <form  id="upload" method="post" enctype="multipart/form-data"
                          style="width:auto;">
                        <%--进行文件管理的跳转,这里使用一个fileName进行班级Id的保存 --%>
                        <input type="text" name="uploadUser" style="display: none" value="${sessionScope.username}" id="userNameOfClient">
                        <input type="text" name="classId" id="classId" style="display: none">
                        <input type="text" name="fileName" id="fileName" style="margin-top: 10px; width:150px;">
                        <input type="file" name="file" id="fileContent" onchange="filePath()" style="margin-top: 10px">
                        <input type="button" value="上传" onclick="uploadFile()" style="margin-top: 10px">
                        <hr>
                    </form>
                </div>
            </ul>
            <ul id="fileList">

            </ul>
        </div>
        <div id="notify_system" class="conRightDoc" style="display: none">
            <ul>
                <div>
                    <h3>事件通知</h3>
                    <hr>
                    <p style="color: #2b542c">请输入需要通知本班级事件：</p>
                    <input type="text" name="notify_event" id="notify_event" style="height: 50px; width: 150px;">
                    <input type="button" value="通知大家" id="notifyEveryone" style="margin-left: 78px" onclick="notifyEveryoneEvent()">
                    <hr>
                    <input type="button" value="查看通知列表" id="findNotifyList" onclick="queryNotifyList()" style="margin-left: 25px">
                </div>
            </ul>
        </div>
    </div>
</div>


<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>

<script type="text/javascript">


   function filePath() {
       $("#fileName").val($("#fileContent").val());
   }



   function uploadFile() {
       var para = $("#fileName").val();
       if (para == "") {
           alert("上传文件不能为空");
           return;
       }
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
           error: function () {
               alert("请求错误");
           }
       });
   }

</script>

<script type="text/javascript">

    function flushFile() {
        var claId = $("#classId").val();
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
                        var path = data[i].filePath;
                        var name = data[i].fileName;
                        var htmstr = '<div>' + getLocalTime(data[i].date) + ' ' + data[i].fileName + ' '
                            + data[i].upLoadUserName +'<a href= "/fileDownload?filePath='+path+'&fileName='+name+'">下载</a>'+
                            '</div>';
                        $("#fileList").append(htmstr);
                        console.log(data[i]);
                    }
                }
            },
            error: function () {
                alert("访问错误")
            }
        })
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
    var j = 0;
    function notifyShow() {
        if (j%2 == 0) {
            document.getElementById("notify_system").style.display="block";
        }else {
            document.getElementById("notify_system").style.display="none";
        }
        ++j;
    }
</script>

<script type="text/javascript">

    // 全局变量，进行记录访问的是哪个班级的
    var changeClassId = 0;
    var changeName = "noclass";


    function queryNotifyList() {
        console.log(changeName);
        var str =encodeURI(changeClassId+"&"+changeName);
        console.log(str);
        window.open("/searchNotify?"+str);
    }


    function notifyEveryoneEvent() {
        var message_event = $("#notify_event").val();
        if (message_event == "") {
            alert("请输入通知内容");
            return;
        }
        $("#notify_event").val("");
        var userNameOfClientPC =  $("#userNameOfClient").val();
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/notifyEvent",
            type: "POST",
            dataType: "JSON",
            data: JSON.stringify({
                "classId": changeClassId,
                "event_message": message_event,
                "teacherName":userNameOfClientPC
            }),
            success: function (data) {
                if (data == "1") {
                    alert("已通知本班级各位同学");
                }else {
                    alert("通知失败");
                }
            },
            error: function () {
                alert("发生未知错误");
            }
        })
    }



    function setProperty() {
        console.log(changeName);
        var str =encodeURI(changeClassId+"&"+changeName);
        console.log(str);
        window.open("/setClassMember?"+str);
    }

    function sendMessage() {
        var message = $("#dope").val();
        console.log(message);
        $("#dope").val("");
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/sendMessage",
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
               url: "/getClassAndName",
               type: "POST",
               dataType: "JSON",
               success :function (data) {
                   if (undefined != data) {
                       console.log(data);
                       //k 为班级ID v为班级名字
                       $("#userList").empty();
                       var strhead = '<li><div><span style="width: 150px; font-size: 17px; text-align: center;display: block; color: greenyellow">班级</span></div></li>';
                       $("#userList").append(strhead);
                       $.each(data, function (k, v) {
                           var htmlstr = '<li>'
                               + '<div><input style="width: 150px; font-size: 14px; text-align: center;display: block; color: darkolivegreen" type="button" id="'+k+'" value="'
                               + v
                               + '" href="javascript:void(0)" onclick="addGroup(this.id,this.value)"></div>'
                               + '</li>';
                           $("#userList").append(htmlstr);
                       })
                   }
               },
               error : function () {
                   alert("访问错误")
               }
           })
       })
   });
    function addGroup(k,v) {
       console.log(k+"-------");
       var cla = k;
        $("#classId").val(k);
        changeClassId = k;
        changeName = v;
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/getMessage",
            type: "GET",
            dataType: "JSON",
            data : {
                classId:cla
            },
            // 这里也可以是一个对象
            success : function (data) {
                $("#headName").empty().append(v); // 设置班级名字
                $("#message").empty();
                console.log(data);
                if (data != "") {
                    var userPhone = ${sessionScope.user};
                    for (var i = 0; i < data.length; ++i) {
                        var type = "";
                        if (data[i].sendType == 1) {
                            type = data[i].role+"老师:";
                        } else if (data[i].sendType == 0) {
                            type = "学生:";
                        } else {
                            type = "未知身份:";
                        }
                        if (userPhone == data[i].sendPhone) {
                            // 本人的消息
                            // TODO 待进行测试
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