package filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/*
 *   敏感词汇过滤器      使用设计模式 代理模式（动态代理）
 *  
 * 
 * 
 * 
 * 
 */

import org.apache.tomcat.websocket.BackgroundProcess;
//设计模式：一些通用的解决固有问题的模式
//代理模式
//代理对象和真实对象实现相同的接口
//代理对象 = prooxy.newProxyinstance()'
//   增强方式
//     增强参数列表   返回值类型    方法体执行
       
@WebFilter("/*")
public class sensitiveWordFilter implements Filter {



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
//		1.创建代理对象，增强getParameter 方法
		
//		     参数 1. 类加载器：真实对象.getClass().getClassLoader()         获取request真实对象进行增强（获取了类加载器）
//		     2. 接口数组真实对象.getClass().getInterfaces()
//		     3. 处理器：new InvocationHandler()   
//		返回一 ServletRequest对象 接收
	ServletRequest proxeyreq =(ServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(),request.getClass().getInterfaces(), new InvocationHandler() {
//		  代理逻辑编写的方法：代理对象所有方法都会触发该方法执行
//		    1.proxy:代理对象  
//		    2.method：代理对象调用的的方法，被封装成的对象
//		    3.args：代理对象调用方法时，传递的实际参数
		
		
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//			增强getParameter 方法
//				判断是否是getParameter方法(如果是getParameter参数,就进行增强)
				if(method.getName().equals("getParameter")) {
//					增强返回值，对返回值进行敏感词过滤 
//					获取返回值   
					String value = (String)method.invoke(request, args);
					
					if(value != null) {//有返回值
						for(String str:list) {
							if(value.contains(str)) { //有敏感词汇
								value = value.replace(str,"***");
								System.out.println(value+"过滤了敏感词汇");
							}
						}
					}
					return value;
					
				}
//				如果不是getParameter参数,传递真实对象，传递args数组
				return method.invoke(request, args);
			}
		});
		
		
//		2.放行
		
//		把ServletRequest 对象传入  ，在chain.dofilter 调用、该对象 增强步骤才会执行
//	    在
		chain.doFilter(proxeyreq, response);
	}
//  敏感词汇集合
	private List<String> list = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		
		try {
//			服务器开始，执行一次
//			加载配置文件
//			获取文件真实路径  fConfig获取
			ServletContext servletcontext = fConfig.getServletContext();
		   
//			文件放在了src下
			String realPath = servletcontext.getRealPath("WEB-INF/classes/sentive.txt");
//			将文件加载进内存
			System.out.println(realPath);
			BufferedReader br = new BufferedReader(new FileReader(realPath));
		
//		          读取文件
			String line = null;
			while( (line = br.readLine()) != null) {
//			   每读一行 list集合 添加一行
				list.add(line);
				
			} 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
System.out.println(list);
	}

	public void destroy() {
		
	}
	
	
}






