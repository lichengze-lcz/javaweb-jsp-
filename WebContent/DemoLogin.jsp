<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>案例表单</title>

    <!-- Bootstrap -->
    <link href="libs/boostrapt/css/bootstrap.css" rel="stylesheet">   
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="libs/boostrapt/js/jquery-2.1.0.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="libs/boostrapt/js/bootstrap.js"></script>	
</head>

<!-- 刷新验证码Function -->
<script type="text/javascript">
    function refreshCode(){
    	<!--获取验证码图片对象--->
    var vcode = document.getElementById("vcode");
        <!--设置时间属性加时间戳-->
    vcode.src ="/jserverpage/checkCodecopyucp?time="+new Date().getTime();
    }

</script>
<style>
body{background:#D0D0D0}
.alert-warning{width: 550px; color:red;text-align: center;}
.container{border: 1px;red; }

</style>

<body>
<div class="container"> 

<form action="Demologin" method="post" class="form-inline" style="margin:80px 400px ; border: 1px red;">
 <h3 style="margin-left: 80px">管理员登录</h3>
 <br>
  <div class="form-group">
    <label for="password">用户名:</label><br>
    <input type="username" class="form-control"  name="username" id="username"  placeholder="请输入用户名"style="width:300px;">
  </div><br><br>
  <div class="form-group">
    <label for="password">密码:</label><br>
    <input type="password" class="form-control"  name="password" id="password" placeholder="请输入密码"style="width:300px;">
  </div><br/><br/>
  
   <div class="form-inline">
    <label for="vcode">验证码:</label>
    <input style="width:120px;" type="text" class="form-control"  name="verifycode" id="verifycode" placeholder="请输入验证码">
    <!--调用方法——刷新验证码-->
    <a href="javascript:refreshCode();">
    <img src="${pageContext.request.contextPath}/checkCodecopyucp" id="vcode"/>
    </a>
  </div><br>
  <div>
  <button type="submit" class="btn btn-default">登录</button>
  </div><br><br>
  
   <!--一条警告表单  -->
   <div class="alert-warning" role="alert" style="width: 250px;">
   <button  type="button" class="close" data-dismissible="alert">
    </button>
     <strong>${errormsg}</strong>
     <strong>${loginmsg}</strong>
   </div>
  
  </form>
   
</div>
   
    <li><a href="${pageContext.request.contextPath}/FindUserByPageServlet">分页</a></li>
</body>
</html>