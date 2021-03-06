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
body{background: #e3f9fd}
</style>

<body><br><br><br>

<h2  style="margin-left:550px";>修改联系人</h2>


<div class="container">   
<form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
<!--增加隐藏域 （把id作为参数ye提交过去） 用于修改数据，作为条件    -->
<input type="hidden" name="id" value="${user.id}">

  <div class="form-group">
    <label for="name">姓名</label>                                                <!--固定值  --> 
    <input class="form-control" type="text" name="name" value="${user.name}" readonly="readonly"  placeholder="请输入姓名">
  </div>
  
  <div class="form-group">
     <label>性别</label>
     <!--jstl表达式，回显性别  -->
     
     <c:if test="${user.gender =='男'}">
      <input type="radio"name="gender" value="男" checked="checked"/>男
     <input type="radio" name="gender" value="女"/>女
     </c:if>
     <c:if test="${user.gender =='女'}">
      <input type="radio" name="gender" value="男" />男
     <input type="radio" name="gender" value="女" checked="checked"/>女
     </c:if>
  </div>
  
  
  
 <div class="form-group">
    <label for="age">年龄</label>    <!--回显数据  设置value值  -->               
    <input class="form-control" type="text" name="age"  value="${user.age}"   placeholder="请输入年龄">
  </div>
  
 <div class="form-group">
    <label for="address">籍贯</label>
    <select name="adress" id="adress" class="form-control">
    <!--jstl下拉框回显 -->
       <c:if test="${user.adress =='庞克哈萨德'}">
          <option value="庞克哈萨" selected="selected">庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
       
       <c:if test="${user.adress =='伟大航路'}">
          <option value="庞克哈萨" >庞克哈萨德</option>
           <option value="伟大航路" selected="selected">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
       
        <c:if test="${user.adress =='罗格镇'}">
          <option value="庞克哈萨">庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇" selected="selected">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
      
        <c:if test="${user.adress =='火之国'}">
          <option value="庞克哈萨">庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国"  selected="selected">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
       
        
       < <c:if test="${user.adress =='科尔菲斯山'}">
          <option value="庞克哈萨" >庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山" selected="selected">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
        <c:if test="${user.adress =='川之国'}">
          <option value="庞克哈萨">庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国"  selected="selected">川之国</option> 
           <option value="雷之国">雷之国</option>    
       </c:if>
       
        
        <c:if test="${user.adress =='雷之国'}">
          <option value="庞克哈萨">庞克哈萨德</option>
           <option value="伟大航路">伟大航路</option>
           <option value="罗格镇">罗格镇</option>
           <option value="火之国">火之国</option>
           <option value="科尔菲斯山">科尔菲斯山</option>
           <option value="川之国">川之国</option> 
           <option value="雷之国" selected="selected">雷之国</option>    
       </c:if>
      
      
    </select>
  </div>
  
 <div class="form-group">
    <label for="exampleInputEmail1">QQ</label>
    <input type="text" name="qq" value="${user.qq}" class="form-control" placeholder="请输入QQ号码">
  </div>
  
 <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="text" name="emall" value="${user.emall}" class="form-control" placeholder="亲输入邮箱地址">
  </div>
  
  <button type="submit" class="btn btn-primary">提交</button>
  <button type="reset" class="btn btn-default">重置</button>
  <button type="button" class="btn btn-default">返回</button>
</form>
</div> 
</body>
</html>