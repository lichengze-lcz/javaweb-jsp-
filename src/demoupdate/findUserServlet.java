package demoupdate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demologinplus.User;
import demologinplus.Userdao;


//通过参数id 找到要修改的数据
//查询该用户信息      回显用户信息到 Demoupdate.jsp

@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取参数id
		String id = request.getParameter("id");
		
		//2.调用Servlet进行查询  Userdao方法
		Userdao servlet = new Userdao();
		
		//返回到User对象
		User user = servlet.findById(Integer.parseInt(id));
		
		//3.将 User查到的数据存入 request域中
		request.setAttribute("user", user);
		
		//4.转发到Demoupdate.jsp   给该jsp 传递User对象      用于 设置value值 回显数据
		request.getRequestDispatcher("/Demoupdate.jsp").forward(request,response);
		
	}

}
