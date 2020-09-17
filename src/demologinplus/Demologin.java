package demologinplus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 登录校验页面
 */
@WebServlet("/Demologin")

public class Demologin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //1.编码
		request.setCharacterEncoding("GBK");
		
		//2.获取登录数据
		//获取用户填写的验证码
		String verifycode = request.getParameter("verifycode");
		
		
		//3.验证码校验
		HttpSession session = request.getSession();
		String checkcode = (String)session.getAttribute("checkCodeSession02");
		
		//确保验证码一次性
		session.removeAttribute("checkCodeSession02");
		
		if(!checkcode.equalsIgnoreCase(verifycode)) {
			//验证码不正确
			//提示信息
			request.setAttribute("errormsg", "验证码错误");
			//跳转页面登录  转发
            request.getRequestDispatcher("/DemoLogin.jsp").forward(request, response);			
			
			return;
		}
		
		//创建容器Map集合，装用户名和密码,直接获取
        Map<String, String[]> map = request.getParameterMap();		
        
        //4.把获取的用户名和密码，封装User对象      
		User user = new User();
		
		 try {
			BeanUtils.populate(user,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		 
		//5.调用Service查询数据
        Userdao userdao = new Userdao();
        
      //重新创建user对象，装查询到的用户名和密码,  userdao进行校验
        User loginuser = userdao.login(user);
        
        userdao.login(user);
        //登录成功
        if(loginuser != null) {
        	
        	System.out.println(loginuser);
        	//登录成功
        	//*****将用户信息存入Session
        	session.setAttribute("user1",loginuser);
        	
        	
        	//跳转页面 动态地址  重定向  （）
        	response.sendRedirect(request.getContextPath()+"/UserListServlet");
        	
        	
        }else {
        	//登录失败
        	//提示错误信息
        	request.setAttribute("errormsg", "用户名或密码错误");
        	//跳转页面登录  转发
            request.getRequestDispatcher("/DemoLogin.jsp").forward(request, response);		
        }
	}

}
