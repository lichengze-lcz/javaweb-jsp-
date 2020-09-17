
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="demologinplus.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     <!-- jstl高版本 -->

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
    
    
   <script type="text/javascript">
      function deleteUser(id){
    	  if(confirm("你确定要删除吗？")){
            //访问路径
            location.href = "${pageContext.request.contextPath}/deleteUserServlet?id="+id;
    		  
    	  }
	}
    
      window.onload = function() {
		  //1.提交选中的框(checked) 传递其参数id 定义sql删除
    	  //给删除选中按钮添加点击事件
		document.getElementById("delSelected").onclick = function(){
			 //表单删除提示信息
			 if(confirm("您确定删除选中条目吗")){
		         //对表单选中判断，选中则提交
		         var flag = false;
				 var cbs = document.getElementsByName("uid");
    	  
    	         for(var i = 0; i < cbs.length; i++){
				     if(cbs[i].checked){
				    	 //有一个选中
				    	 flag = true;
				    	 break;
				     }
    	         }
		         //选中可以提交
    	         if(flag){
    	        	//点击按钮提交表单 传递参数 id
    	 			document.getElementById("form").submit();   
    	         }
	     	}
	  }
		  
         //2.第一个checked 全选全不选操作
          //获取第一个控制框
         document.getElementById("firstcb").onclick = function(){
          //获取所有的checked  除第一个控制框
         var cbs = document.getElementsByName("uid");
    	  //遍历获取所有
    	  for(var i = 0; i < cbs.length; i++){
          //设置这些cbs[i] 的checked的状态  ==  第一个控制框id为firstcb的状态 firstcb.checked
    		cbs[i].checked = this.checked;   //(在("firstcb")事件里编写   this代表当前checked属性)
          
    	  }
       }
      
      }
    </script>

  <style>
 body{background: #f3f9f1}
</style>
	
  </head>
  <body>
    
    <!--容器类型 两边空白  -->
    <div class="container"> 
    
    <!--表单标题  -->
    <h2 style="margin-left:550px; ">用户信息列表</h2>
    
  <!-- 搜索框 -->
  <div style="float:left;">

    <form class="form-inline" action="${pageContext.request.contextPath}/search" method="post">
  <div class="form-group">
    <label for="exampleInputName2">姓名</label>
    <input type="text" name="name" value="${condtion.name[0]}" class="form-control" id="exampleInputName2">
  </div>
   <div class="form-group">
    <label for="exampleInputName4">籍贯</label>
    <input type="text" name="adress" value="${condtion.adress[0]}" class="form-control" id="exampleInputName4">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail2">邮箱</label>
    <input type="email" name="emall" value="${condtion.emall[0]}" class="form-control" id="exampleInputEmail2">
  </div>
  <button type="submit" class="btn btn-default">查询</button>
</form>
    
</div>
    
    
    <!--添加和删除数据 -->
     <div style="float:right;">
        <a href="${pageContext.request.contextPath}/Demoadd.jsp" class="btn btn-primary">添加联系人</a>
        
           <!--@@@@@@@@@@@给按钮加点击事件   执行函数 进行form表单提交   -->     
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    
    </div>
    
    <br>
    <!--用form表单包裹，获取选中id  执行函数提交表格数据-->
    <form id ="form" action="${pageContext.request.contextPath}/DelSelectServlet" method="post">
    <table class="table table-hover">
    </form>

      <!-- 表头行 -->
      <tr bgcolor="#EB7347">
      <!--全选全不选-->
      
         <th><input type="checkbox" id="firstcb"></th>
         <th>编号</th>
         <th>姓名</th>
         <th>年龄</th>
         <th>性别</th>
         <th>籍贯</th>
         <th>QQ</th>
         <th>邮箱</th>
         <th>操作</th>   
      </tr>
   <!--数据行 -->
     
      <c:forEach items="${users}" var="user" varStatus="s">
      
       <tr bgcolor="#84AF9B">  <!-- @@@@@@@@***javascript提交表单  提交选中框       传递id参数  传递到servlet-->
         <th><input type="checkbox" name="uid" value="${user.id}"></th>
         <td>${s.count}</td>
         <td>${user.name}</td>
         <td>${user.age}</td>
         <td>${user.gender}</td>
         <td>${user.adress}</td>
         <td>${user.qq}</td>
         <td>${user.emall}</td>
        
         <td>
                 <!--操作按钮 传递当前行id值-->
          <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>
          <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a>
         
         </td>
     
      </tr>
      </c:forEach>

    </form> 
   </table>
   
   <!--分页 -->
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
   
    
 </div>
    
</body>
</html>