<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/5/31
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生接收</title>
</head>
<body background="../../img/notifybackground.jpg" class="back">
<h2 style="margin-left: 400px;">${requestScope.className}</h2>
<div class="Frame">
    <div class="leftFrame">
        <input type="text" name="flushNotifyEvent" id="flushNotifyEvent" value="${requestScope.classId}" style="display: none">
        <input style="width: 840px; text-align: center;min-height: 30px;" type="button" value="通知事件查看" onclick="flushNotify()" >
        <div>
            <table>
                <tr>
                    <th style="width: 165px;text-align: left;">通知时间</th>
                    <th style="width: 150px;text-align: left;">通知人</th>
                    <th style="width: 310px;text-align: left;">通知内容</th>
                    <th style="width: 110px;text-align: left;">确认收到</th>
                    <th style="width: 80px;text-align: left;">收到情况</th>
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
        $("#eventList").val("");
        var classId = $("#flushNotifyEvent").val();
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/minuteStudentEventInfo",
            type: "GET",
            dataType: "JSON",
            data: {
                // 学生自己的Id通过session来拿
                "classId": classId
            },
            success : function (data) {
                if (data == "0"){
                    alert("本班级没有通知事件")
                } else {
                    var ownEvent = data.ownerEvent;
                    var listModel = data.allEvent;
                    var str = "";
                    $("#eventList").empty();
                    for (var i=0; i < listModel.length; ++i) {
                        if (ownEvent.search(listModel[i].time_eventID) != -1) {
                            // 未进行确认
                            str = '<tr style="font-size:12px;width: 100%;"><td style="padding-right:20px;padding-left: 5px; min-width: 140px"><div>'+ getLocalTime(listModel[i].time_eventID)+
                                '</td><td style="padding-right:20px; min-width: 50px">'+listModel[i].teacherName+
                                '</td><td style="padding-right:20px; color:red; min-width: 340px">'+listModel[i].event_message+
                                '</td><td style="padding-right:20px; min-width: 100px;"><input id="'+listModel[i].time_eventID+'" style="margin-left: 30px;font-size:12px;color: red" type="button" value="确认收到" name="'+listModel[i].time_eventID+'" onclick="OKAcceptInfo(this.name)">'+
                                '</td><td style="padding-right:20px; min-width: 100px;"><input style="margin-left: 30px;font-size:12px;" type="button" value="收到情况" name="'+listModel[i].time_eventID+'" onclick="minuteAccept(this.name)"></td></tr></div>';
                        } else {
                            str = '<tr style="font-size:12px;width: 100%;"><td style="padding-right:20px; padding-left: 5px; min-width: 140px"><div>'+ getLocalTime(listModel[i].time_eventID)+
                                '</td><td style="padding-right:20px; min-width: 50px">'+listModel[i].teacherName+
                                '</td><td style="padding-right:20px; color:red; min-width: 340px">'+listModel[i].event_message+
                                '</td><td style="padding-right:20px; min-width: 100px;"><span style="margin-left: 30px;color: gray">已确认收到</span> '+
                                '</td><td style="padding-right:20px; min-width: 100px;"><input style="margin-left: 30px;font-size:12px;" type="button" value="收到情况" name="'+listModel[i].time_eventID+'" onclick="minuteAccept(this.name)"></td></tr></div>';
                        }
                        $("#eventList").append(str);
                    }
                }
            },
            error : function () {
                alert("发生错误，请检查");
            }
        })
    }

    function OKAcceptInfo(eventId) {
        $.ajax({
            scriptCharset: 'utf-8',
            contentType: "application/json;chartset=utf-8",
            url: "/isOk",
            type: "GET",
            dataType: "JSON",
            data: {
                "eventId": eventId
            },
            success: function (data) {
                if (data == "1") {
                    alert("已经确认");
                } else {
                    alert("确认失败");
                }
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
        margin-left: 30px
    }
    .back{
        background-size:cover;
    }
</style>
