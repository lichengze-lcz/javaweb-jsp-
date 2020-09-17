package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import jspservlet.user;

//完成登录页面的过滤器

@WebFilter("/*")
public class LoginFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain arg2)throws IOException, ServletException {
		//过滤与登录资源相关的路径     不是儿子 http
		System.out.println("doFilter  过滤器执行啦");
		//强转类型转换
		HttpServletRequest request = (HttpServletRequest) req;
		
		//1.获取登录资源请求路径
		String uri = request.getRequestURI();
		 
		//2.判断是否包含登录相关资源路径  要注意排除 css/js/图片/验证码    //排除css..失效
		if(uri.contains("/DemoLogin.jsp") || uri.contains("/Demologin")|| uri.contains("/checkCodecopyucp")|| uri.contains("/css")|| uri.contains("/js")){
			//包含，这哥们请求就是想登录 放行
			arg2.doFilter(request, response);
		}else {
			//不包含，验证用户是否登录
			//3.从获取session中获取User
		Object user= request.getSession().getAttribute("User");
		      
		    if (user != null) {
				//登录啦 放行
		    	arg2.doFilter(request, response);
			}else {
				//没有登录，跳转登录页面
				request.setAttribute("loginmsg","您尚未登录，请登录");
				//去登录
				request.getRequestDispatcher("/DemoLogin.jsp").forward(request, response);
			}
		
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}

	@Override
	public void destroy() {	
	}

}
