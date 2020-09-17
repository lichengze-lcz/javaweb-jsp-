 package demologinplus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

//添加用户信息

@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//2.获取参数
		Map<String, String[]> map = request.getParameterMap();
		System.out.println(map);
		//3.使用javaBean把Map结合数据放入USer对象
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//4.使用Servlet添加数据
		Userdao adddate = new Userdao();
		adddate.adddate(user);
		
		//5.重定向到 查询所有数据类UserListServlet  该类到DemoUserList.jsp
		response.sendRedirect(request.getContextPath()+"/UserListServlet");
		
	}

}
