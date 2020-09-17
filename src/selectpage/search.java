package selectpage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demologinplus.User;
import demologinplus.Userdao;

//通过搜索参数  实现搜索框

@WebServlet("/search")
public class search extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		//获取条件查询条件参数
	    Map<String, String[]> condtion = request.getParameterMap();
		
		
		
		//2.调用Servlet userdao 执行sql查询    
		Userdao servlet =new Userdao();
		
		List users = servlet.Scondition(condtion);
		
		//2.request 域共享数据  List集合
		request.setAttribute("users", users);
		request.setAttribute("condtion", condtion);
		
		//3.转发到DemoUserList.jsp
		request.getRequestDispatcher("/DemoUserList.jsp").forward(request, response);
		
	}

}
