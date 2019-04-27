<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/4/18
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>注册</title>
    <script text="text/javascript">
        function checkStudentParentInfo(){
            document.getElementById("sppn").innerHTML="";
            document.getElementById("spwd").innerHTML="";
            document.getElementById("spwdmeg").innerHTML="";
            document.getElementById("stuNam").innerHTML="";
            var phone=document.getElementById("parentPhoneNumber").value;
            var flag=true;
            if(phone==""){
                document.getElementById("sppn").innerHTML="";
                flag=false;
            }
            var password=document.getElementById("spassword").value;
            if(password==""){
                document.getElementById("spwd").innerHTML="密码不能为空";
                flag=false;
            }
            var username=document.getElementById("studentName").value;
            if(username==""){
                document.getElementById("stuNam").innerHTML="用户名不能为空";
                flag=false;
            }
            var repwd=document.getElementById("srepwd").value;
            if(repwd!=password){
                document.getElementById("spwdmeg").innerHTML="两次输入的密码不一致";
                flag=false;
            }
            return flag;
        }
    </script>

    <script text="text/javascript">
        function checkTeacherInfo(){
            document.getElementById("tpn").innerHTML="";
            document.getElementById("tpwd").innerHTML="";
            document.getElementById("tpwdmeg").innerHTML="";
            document.getElementById("stuNam").innerHTML="";
            var flag=true;
            var username=document.getElementById("teaName").value;
            var test = document.getElementById("teaName") console.debug(test);
            if(username==""){
                document.getElementById("tname").innerHTML="用户名不能为空";
                flag=false;
            }
            var tPhoneNumber=document.getElementById("tPhoneNumber").value;
            if(tPhoneNumber==""){
                document.getElementById("tpn").innerHTML="手机号不能为空";
                flag=false;
            }
            var password=document.getElementById("password").value;
            if(password==""){
                document.getElementById("tpwd").innerHTML="密码不能为空"
                flag=false;
            }
            var trepwd=document.getElementById("trepwd").value;
            if(trepwd!=password){
                document.getElementById("tpwdmeg").innerHTML="两次输入的密码不一致";
                flag=false;
            }
            var sub = document.getElementById("tea");
            return flag;
        }
    </script>

    <script type="text/javascript">
        function chengeTeacher() {
            document.getElementById("student").style.display="none";
            document.getElementById("teacher").style.display="block";
        }
        function chengeStudent() {
            document.getElementById("student").style.display="block";
            document.getElementById("teacher").style.display="none";
        }
    </script>
</head>
<body>
<div>
    <input type="button" value="学生家长注册" onclick="chengeStudent()"/>
    <input type="button" value="老师注册" onclick="chengeTeacher()"/>
</div>
<div id="student">
    <h3>学生家长注册</h3>
<%--学生家长注册信息表--%>
    <form action="/studentRegister" method="post" onsubmit="return checkStudentParentInfo()">
学生姓名：<input type="text" id="studentName" name="studentName"/><span id="stuNam" style="color:red;"></span><br>
        学生家长手机号:<input type="text" id="parentPhoneNumber" name="parentPhoneNumber"/><span id="sppn" style="color:red;"></span><br>          
        密  码:<input type="password" id="spassword" name="password"/><span id="spwd" style="color:red;"></span><br> 
        确认密码:<input type="password" id="srepwd" name="srepwd"/><span id="spwdmeg" style="color:red;"></span><br>
    性别：   男 <input type="radio" name="gender" value="1">    女 <input type="radio" name="gender" value="0"><br>
        学生介绍：<input type="text" name="studentDesc" width="150px" height="100px"/><br>
        <input type="submit" value="提交"/>
    </form>
</div>


<div id="teacher" style="display:none">
    <h3>老师注册</h3>
<%--老师注册信息表 --%>
    <form id="tea" action="/teachertRegister" method="post" onsubmit="return checkTeacherInfo()">
        老师称呼:<input type="text" id="teaName" name="teaName"/><span id="tname" style="color:red;"></span><br>
        老师手机号:<input type="text" id="tPhoneNumber" name="tPhoneNumber"/><span id="tpn" style="color:red;"></span><br>         
        密  码:<input type="password" id="tpassword" name="password"/><span id="tpwd" style="color:red;"></span><br>  
        确认密码:<input type="password" id="trepwd" name="trepwd"/><span id="tpwdmeg" style="color:red;"></span><br>
        老师职位:<input type="text" name="role"/><br>
        性别：   男 <input type="radio" name="gender" value="1">    女 <input type="radio" name="gender" value="0"><br>
        老师描述:<input type="text" name="tDesc" width="150px" height="100px"/><br>
        <input type="submit" value="提交"/>
    </form>
</div>


<%--这个是班级注册--%>
<%--<form action="/submit" method="post">--%>
    <%--<p>classId: <input type="text" name="classId" /></p>--%>
    <%--<p>createTUid: <input type="text" name="createTUid" /></p>--%>
    <%--<p>className: <input type="text" name="className" /></p>--%>
    <%--<p>schoolName: <input type="text" name="schoolName" /></p>--%>
    <%--<p>createPhoneNumber: <input type="text" name="createPhoneNumber" /></p>--%>
    <%--<p>schoolAddress: <input type="text" name="schoolAddress" /></p>--%>
    <%--<p>classDesc: <input type="text" name="classDesc" /></p>--%>
    <%--<p><input type="submit" value="提交"></p>--%>
<%--</form>--%>

<%--<form id="loginForm" action="/login.do" method="post">--%>
    <%--<table border="1">--%>
        <%--<tr>--%>
            <%--<td>用户名</td>--%>
            <%--<td>--%>
                <%--<input type="text" name="username">--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>密码</td>--%>
            <%--<td><input type="text" name="password"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2"><input type="submit" value="登录"></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>
</body>
</html>
