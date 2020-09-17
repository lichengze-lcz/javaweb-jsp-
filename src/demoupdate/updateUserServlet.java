package demoupdate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import demologinplus.User;
import demologinplus.Userdao;

//通过参数id   修改用户信息

@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //1.设置编码
		request.setCharacterEncoding("utf-8");

		//2.获取map 参数
	    Map<String,String[]> map = request.getParameterMap();
	
	    //3.把数据封装到User对象里
	    User user = new User();
	    //使用BeanUtils方法
	    try {
			BeanUtils.populate(user,map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	    //4.调用Userdao类方法对 数据进行修改
	    Userdao servlet = new Userdao();
	    //所有参数在user里  传递进行校验
	    servlet.update(user);
	    
	    //5.跳转到查询所有类 UserListServlet 
	    //重定向
	    response.sendRedirect(request.getContextPath()+"/UserListServlet");
	}

}
