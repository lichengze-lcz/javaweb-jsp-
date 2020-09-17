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

<style>
.container{width: 500px;  }

</style>
<body><br><br><br>

<h2  style="margin-left:550px";>添加联系人页面</h2>

<div class="container">   
<form action="${pageContext.request.contextPath}/addUserServlet" method="post">

  <div class="form-group">
    <label for="name">姓名</label>
    <input  type="text" name="name" class="form-control" placeholder="请输入姓名">
  </div>
  
  <div class="form-group">
     <label>性别</label>
     <input type="radio" name="gender" value="男"/>男
     <input type="radio" name="gender" value="女"/>女
  </div>
  
 <div class="form-group">
    <label for="age">年龄</label>
    <input type="text" name="age"  class="form-control" placeholder="请输入年龄">
  </div>
  
 <select name="adress" id="adress" class="form-control">
           <option>庞克哈萨德</option>
           <option>伟大航路</option>
           <option>罗格镇</option>
           <option>火之国</option>
           <option>科尔菲斯山</option>
           <option>川之国</option> 
           <option>雷之国</option>    
    </select>
    
 <div class="form-group">
    <label for="exampleInputEmail1">QQ</label>
    <input type="text" name="qq" class="form-control" placeholder="请输入QQ号码">
  </div>
 <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="text" name="emall" class="form-control" placeholder="亲输入邮箱地址">
  </div>
  <button type="submit" class="btn btn-primary">提交</button>
  <button type="reset" class="btn btn-default">重置</button>
  <button type="button" class="btn btn-default">返回</button>
</form>
</div> 
</body>
</html>