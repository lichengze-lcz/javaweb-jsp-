package demologinplus;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demologinplus.Userdao;

//登录之前查询数据库所有的数据，并把数据转发到表单DemoUserList.jsp页面


//数据库查找所有数据，**转发到 DemoUserList.jsp       转发(DemoUserList.jsp 返回上一页面数据还在)
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//需调用 finAl方法，执行sql，查询所有数据
		Userdao servlet = new Userdao();
		List users = servlet.finAll();
		
		//2.request 域共享数据  List集合
		request.setAttribute("users", users);
		
		//3.转发到DemoUserList.jsp
		request.getRequestDispatcher("/DemoUserList.jsp").forward(request, response);
	
	}

}
