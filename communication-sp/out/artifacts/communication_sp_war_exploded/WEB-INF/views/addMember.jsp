<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/5/14
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加成员</title>
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
    <script type="text/javascript">


        // 查询
        function queryName() {
            var addPhoneN = $("#addValue").val();
            $.ajax({
                    scriptCharset: 'utf-8',
                    contentType: "application/json;chartset=utf-8",
                    url: "/queryName",
                    type: "GET",
                    dataType: "JSON",
                    data :addPhoneN,
                    success : function (ops) {
                        if (ops == "" || ops == null) {
                            alert("没有对应手机号用户，请重新输入！");
                        }
                        console.log(ops);
                        for (var i = 0; i < ops.length; ++i) {
                            var htmstr = '<input id="userSelect" name="userSelect" type="radio" value="'+ops[i]+'">'+ops[i];
                            $("#addNameOfPhone").append(htmstr);
                        }
                    },
                    error : function () {
                        alert("添加失败");
                    }
                }
            )
        }
    </script>

    <script type="text/javascript">
        // 添加
        function addMember() {
            var addPhoneN = $("#addValue").val();
            var studentName = $("#userSelect").val();
            console.log(studentName+"haha"+addPhoneN);
            $.ajax({
                    scriptCharset: 'utf-8',
                    contentType: "application/json;chartset=utf-8",
                    url: "/addGroupMember",
                    type: "POST",
                    dataType: "JSON",
                    data : {
                        "classId" : ${requestScope.classId},
                        "addPhoneN" : addPhoneN,
                        "studentName" : studentName
                    },
                    success : function (data) {
                        if (data == "1") {
                            alert("添加成功");
                            $("#addNameOfPhone").empty();
                            $("#addValue").val("");
                        } else {
                            alert("添加失败");
                        }
                    },
                    error : function () {
                        alert("发生错误");
                    }
                }
            )
        }
    </script>
</head>
<body>
<h1>${requestScope.className} 群成员的添加</h1>
<p>输入添加成员的手机号：</p>
<div>
    <input id="addValue" type="text">
    <input type="button" value="查询" onclick="queryName()">
</div>
<div>
    <span id="addNameOfPhone" style="color: green;"></span><br>
</div>
<input type="button" value="添加" onclick="addMember()">

</body>

<div>
    <h1>老师加入班级</h1>
    <a href="/findClassId" target="_blank">添加班级</a>
</div>
</html>
