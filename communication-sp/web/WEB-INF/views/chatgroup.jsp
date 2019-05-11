<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>老师聊天窗口</title>
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
                    <input type="button" value="刷新" id="flush">
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
                    <form id="form_photo" method="post" enctype="multipart/form-data"
                          style="width:auto;">

                        <%--进行文件管理的跳转--%>

                        <input type="file" name="filename" id="filename" onchange="fileSelected();"
                               style="display:none;">
                        <!-- <button id="fasongPhoto" name="fasongPhoto" class="sendBtn" type="submit"
                        style="border-radius: 5px"></button> -->
                    </form>
                </div>
            </div>
            <div class="RightFoot">

                    <textarea id="dope"
                              style="width: 100%;height: 100%; border: none;outline: none;padding:20px 0 0 3%;" name=""
                              rows="" cols=""></textarea>
                // TODO
                <button id="fasong" class="sendBtn" onclick="sendMessage()" style="border-radius: 5px">发送</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js"></script>

<script type="text/javascript">

    // 全局变量，进行记录访问的是哪个班级的
    var chengeClassId = 0;
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
                "classId" : chengeClassId,
                "message" : message
            }),
            success : function () {

            },
            error : function () {
                alert("请求错误")
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
                       $("#userList").append('<div><input type="button" value="刷新" id="flush"></div>');
                       $.each(data, function (k, v) {
                           console.log(data);

                           console.log(k);
                           console.log(v+"========");
                           var htmlstr = '<li>'
                               + '<div><input type="button" id="'+k+'" value="'
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
   })
    function addGroup(k,v) {
       console.log(k+"-------");
       var classId = k;
        chengeClassId = k;
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/getMessage",
            type: "GET",
            dataType: "JSON",
            data : classId,
            // 这里也可以是一个对象
            success : function (data) {
                $("#headName").empty().append(v); // 设置班级名字
                $("#message").empty();
                console.log(data);
                if (data != "") {
                    var userPhone = ${sessionScope.user};
                    for (var i = 0; i < data.length; ++i) {
                        console.log(data[i].message);
                        console.log(data[i].date);
                        console.log(data[i].sendPhone);
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