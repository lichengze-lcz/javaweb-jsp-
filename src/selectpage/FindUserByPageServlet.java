package selectpage;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demologinplus.User;
import demologinplus.Userdao;

//分页操作 半成品

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
		//1.获取参数
		//当前页码
		String currentPage =request.getParameter("currentPage");
		//每页显示的条数
		String rows =  request.getParameter("rows");
		
		
		if(currentPage == null ||"".equals(currentPage)) {
			currentPage ="1";
		}
		if(rows ==null ||"".equals(rows)) {
			rows ="5";
		}
		//获取条件查询条件参数
	    Map<String, String[]> condtion = request.getParameterMap();
		
		
		
		//2.调用Servlet userdao 执行sql查询    
		Userdao servlet =new Userdao();
		//数据返回给 PageBean
		PageBean<User> pb = servlet.findUserByPage(currentPage,rows,condtion);
		
		System.out.println("打印一下pb"+pb);
		
		//3.将PageBean 存入 request域中        （PageBean对象有 分页的页数  和条数）
		request.setAttribute("pb",pb);
		
		//转发到  DemoUserList.jsp
		request.getRequestDispatcher("/selectList.jsp").forward(request,response);
		
	}

}
