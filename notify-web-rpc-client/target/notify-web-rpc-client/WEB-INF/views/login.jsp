<%--
  Created by IntelliJ IDEA.
  User: gyf
  Date: 2018/5/28
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="../../css/common_form.css" type="text/css">
    <script type="text/javascript">
    function check(){
        document.getElementById("userPmg").innerHTML="";
        document.getElementById("pwd").innerHTML="";
        var flag=true;
        var phoneNumber=document.getElementById("phoneNumber").value;
        if(phoneNumber.length===0){
            document.getElementById("userPmg").innerHTML="用户名不能为空";
            flag=false;
        }
        var password=document.getElementById("password").value;
        if(password.length===0){
            document.getElementById("pwd").innerHTML="密码不能为空";
            flag=false;
        }
        return flag;
    }
</script>
<script type="text/javascript">
    function refreshCode() {
        // 获取imge标签
        var imageTag =  document.getElementById("code");
        // 进行刷新
        imageTag.src = '/validateCode?' + new Date().getTime();
    }
</script>
</head>
<body>
    <header>
        <div class="header-line"></div>
    </header>
    <div class="content">
        <%--<img class="content-logo" src="../../img/robot.jpg" alt="logo">--%>
        <h1 class="content-title">登录</h1>
        <div class="content-form">
            <form method="post" action="/communication" onsubmit="return submitTest()">
                <div id="change_margin_1">
                    <input class="phoneNumber" type="text" name="phoneNumber" placeholder="请输入手机号" onblur="oBlur_1()" onfocus="oFocus_1()">
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="remind_1"></p>
                <div id="change_margin_2">
                    <input class="password" type="password" name="password" placeholder="请输入密码" onblur="oBlur_2()" onfocus="oFocus_2()">
                </div>
                <!-- input的value为空时弹出提醒 -->
                <p id="remind_2"></p>
                <div>
                    <input type="radio" name="identity" value="0" checked>学生家长
                    <input type="radio" name="identity" value="1">老师<br>
                </div>
                <p id="remind_3"></p>
                <div>
                    <input class="code" type="text" name="clientCode" placeholder="输入验证码"><br>
                    <img id="code" src="/validateCode" alt="">
                    <a href="javascript:refreshCode();">看不清，刷新验证码</a><br>
                </div>
                <div id="change_margin_3">
                    <input class="content-form-signup" type="submit" onclick="loginfail()" value="登录">
                </div>
            </form>
        </div>
        <div class="content-login-description">没有账户？</div>
        <div><a class="content-login-link" href="/register">注册</a></div>
    </div>
<%--<form action="/communication" method="post" onsubmit="return check()">  --%>
    <%--用户名：<input type="text" id="phoneNumber" name="phoneNumber"/><span id="userPmg" style="color:red;"></span><br><br> --%>
    <%--密 码 ：<input type="password" id="password" name="password"/><span id="pwd" style="color:red;"></span><br>--%>
    <%--身  份  :<input type="radio" name="identity" value="0" checked>学生家长--%>
    <%--<input type="radio" name="identity" value="1">老师<br>--%>
    <%--验证码：<input type="text" name="clientCode">--%>
    <%--<img id="code" src="/validateCode" alt=""><a href="javascript:refreshCode();">看不清，刷新验证码</a><br>--%>
    <%--<input type="submit" value="登陆"/>--%>
    <%--<input type="button" value="注册" onclick="location.href='http://localhost:8080/register'"/>--%>
<%--</form>  --%>
 
<%--<form id="loginForm" action="/login" method="post">--%>
    <%--用户名 <input type="text" name="username"><br/>--%>
    <%--密码<input type="text" name="password"><br/>--%>
    <%--身份:<input type="radio" name="identity" value="1">老师--%>
    <%--<input type="radio" name="identity" value="0" checked>学生家长<br/>--%>
    <%--<input type="submit" value="登录"> <button><a href="http://localhost:8080/register"></a>注册</button>--%>
<%--</form>--%>
    <script src="../../js/common_form_test.js"></script>
</body>
</html>
