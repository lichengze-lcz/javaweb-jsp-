package delectselect;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demologinplus.Userdao;

//删除用户信息

@WebServlet("/DelSelectServlet")
public class DelSelectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //1.获取所有的选中的id   数组形式
		String[] ids = request.getParameterValues("uid");
		
		//2.调用Userdao类 方法 删除
		Userdao servlet = new Userdao();
		servlet.delSelecteId(ids);
		
	   
		//3..跳转到查询所有Servlet类 UserListServlet 
	    //重定向
	    response.sendRedirect(request.getContextPath()+"/UserListServlet");
		
		
	}

}
