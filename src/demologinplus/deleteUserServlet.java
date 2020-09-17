package demologinplus;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//数据库  List删除数据
@WebServlet("/deleteUserServlet")
public class deleteUserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取参数  字符串 id （跳转域名传递参数 id）
		String id = request.getParameter("id");

		//2.调用Userdao
		Userdao servlet = new Userdao();
		//把字符串 id   转为数字型id   数字型id 进行操作数据库
		servlet.delete(Integer.parseInt(id));

		//3.重定向到//UserListServlet  再到DemoUserList.jsp
		response.sendRedirect(request.getContextPath()+"/UserListServlet");
		
	}

}
