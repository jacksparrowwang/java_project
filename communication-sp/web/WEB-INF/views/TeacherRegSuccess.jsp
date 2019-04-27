<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/4/20
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>

    <title>注册成功</title>

    <script type="text/javascript">
        $ (function() {
            $ ('#findClass').click(function (){
                $.ajax({
                    contentType:"application/json;chartset=utf-8",
                    scriptCharset:'utf-8',
                    url : "/queryClass",
                    type : "POST",
                    dataType : "JSON",
                    data : {
                        queryAddress:$("#queryAddress").val(),
                        querySchool:$("#querySchool").val(),
                        queryClass: $("#queryClass").val()
                    },
                    success : function (ops) {
                        if (ops == "" || ops == null) {
                            alert("查找班级不存在！三个填写一个试试")
                        }
                        console.log(ops);
                        var htm = "";
                        for (var i = 0; i < ops.length; ++i) {
                            // htm += "地址:"+ops[i].schoolAddress + "学校:" + ops[i].schoolName + "班级:" +ops[i].className+"\n\r";
                            html += '<option >'
                        }
                        $("#show").val(htm);
                    },
                    error : function () {
                        alert("请求失败");
                    }
                })
            });
        });
        //
        // $('#findClass').click(function() {
        //     showFuncId();
        // });
        // function showFuncId(){
        //     $('#findClass').empty();
        //     var ciValue = $('#findClass');
        //     ciValue.append('<option value="">Pls Select</option>');
        //     $.ajax({
        //         url : '/queryClass',
        //         type : 'post',
        //         dataType : 'json',
        //         success : function (opts) {
        //             if (opts && opts.length > 0) {
        //                 var json = JSON.parse(opts);
        //                 console.log(json);
        //
        //                 var html = [];
        //                 for (var i = 0; i < opts.length; i++) {
        //                     html.push('<option value="'+opts[i].className +'"</option>');//>'+opts[i].schoolAddress+'
        //                 }
        //                 ciValue.append(html.join(''));
        //             }
        //         }
        //     });
        // }

    </script>
</head>
<body>

<div>
    <h3>亲爱的老师，您已经注册成功</h3>
</div>
<p>请选择查找授课的班级或者是创建班级</p><br>

<form>
    地址：<input id="queryAddress" name="queryAddress" type="text"/><br>
    学校：<input id="querySchool" name="querySchool" type="text"/><br>
    班级：<input id="queryClass" name="queryClass" type="text"/><br>
    <%--这里需要用ajax来进行后台查询--%>
    <input type="button" value="查找班级" id="findClass"><br>
    结果： <input id="show" name="show" type="text" style="width: 300px;height: 500px">
    <%--<select id="show" name="show" style="width: 300px; height: 200px"></select>--%>
    <input type="button" value="创建班级" onclick="location.href='http://localhost:8080/registerClass'">
</form>

<p>老师可以创建班级，或者添加班级，是一对多的关系</p>

<p>添加班级， 操作老师班级关系表，这里要提供一个搜索查询的，来获取学校的名称，可以以地址-》学校，提供模糊查询</p>
<p>创建班级，进行对班级表的插入</p>
</body>
</html>
