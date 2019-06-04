<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/5/31
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通知事件</title>

</head>
<body background="../../img/notifybackground.jpg" class="back">
<h2 style="margin-left: 400px;">${requestScope.className}</h2>
<div class="Frame">
    <div class="leftFrame">
        <input type="text" name="flushNotifyEvent" id="flushNotifyEvent" value="${requestScope.classId}" style="display: none;">
        <input type="button" value="通知事件查看" onclick="flushNotify()" style="width: 840px; text-align: center;min-height: 30px;">
        <hr>
        <div>
           <table>
                <tr>
                    <th style="width: 150px;text-align: left;">通知时间</th>
                    <th style="width: 120px;text-align: left;">通知人</th>
                    <th style="width: 390px;text-align: left;">通知内容</th>
                    <th>收到情况</th>
                </tr>
           </table>
            <div id="eventList">

            </div>
        </div>
    </div>
    <div class="rightFrame">
        <h3>通知学生收到情况</h3>
        <hr>
        <h4>未确认通知</h4>
        <div id="noAccpet">

        </div>
        <hr>
        <h4>已确认通知</h4>
        <div id="accept">

        </div>
    </div>
</div>
<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script type="text/javascript">
    function flushNotify() {
        var classId = $("#flushNotifyEvent").val();
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/searchNotifyEvent",
            type: "GET",
            dataType: "JSON",
            data: {
                "classId": classId
            },
            success : function (data) {
                if (data == "0"){
                    alert("本班级没有通知的事件")
                } else {
                    $("#eventList").empty();
                    for (var i=0; i < data.length; ++i) {
                        var str = '<tr style="font-size:12px;width: 100%;"><td style="padding-right:15px; min-width: 140px">'+getLocalTime(data[i].time_eventID)+
                            '</td><td style="padding-right:20px; min-width: 50px">'+data[i].teacherName+
                            '</td><td style="padding-right:40px; color:red; min-width: 360px">'+data[i].event_message+
                            '</td><td style="padding-right:20px; min-width: 200px;"><input style="margin-left: 50px; font-size:12px;" type="button" value="查看详情" name="'+data[i].time_eventID+'" onclick="minuteAccept(this.name)"></td></tr>'
                        $("#eventList").append(str);
                    }
                }
            },
            error : function () {
                alert("发生错误，请检查");
            }
        })
    }

    function getLocalTime(tm) {
        var tt=new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, "         ")
        return tt;
    }

    function minuteAccept(eventId) {
        $("#noAccpet").empty();
        $("#accept").empty();
        var classId = $("#flushNotifyEvent").val();
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/minuteInfo",
            type: "GET",
            dataType: "JSON",
            data: {
                "eventId": eventId,
                "classId":classId
            },
            success: function (data) {
                if (data == "0") {
                    alert("该班级没有学生");
                } else {
                    for (var i = 0; i < data.length; ++i) {

                        if (data[i].flag == "0") { // 未确认
                            var str = '<div class=""><span style="color : red">' + data[i].name+'</span></div>';
                            $("#noAccpet").append(str);
                        } else { // 已经确认
                            var str = '<div class=""><span style="color : green">' + data[i].name+'</span></div>';
                            $("#accept").append(str);
                        }
                    }
                }
            }
        })
    }
</script>
</body>
</html>

<style>
    .Frame{
        border: 2px solid black;
        -webkit-box-flex: 1;
        display: -webkit-box;
        -webkit-box-orient: horizontal;
        min-height: 500px;
    }
    .leftFrame{
        width:70%;
        border-right: 3px solid blueviolet;
    }
    .rightFrame{
        width: 30%;
        margin-left: 30px;
    }
    .back{
        background-size:cover;
    }
</style>
