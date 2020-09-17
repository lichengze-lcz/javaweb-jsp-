package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/* Listener 监听器
 *   概念：web的三大组件之一       
 *   事件监听机制：概念
 *     事件： 一件事情                       servlet创建事件
 *     事件源：事件发生的地方         tomcat
 *     监听器：一个对象         
 *     注册监听：将事件，事件源，监听器绑定在一起，当事件源上发生某个事件后执行监听器代码（一个按钮单机事件，注册一个function函数）
 * 
 * 
 * java里监听器之一
 *    ServeltContextListener    监听ServletContext对象创建和销毁
 *       方法1.void contextDestroyed(ServletcontextEven sce): ServletContext对象被销毁之前会调用该方法 
 *       方法2.void contextInitialized(ServletcontextEven sce): ServletContext对象创建后会调用该方法
 *       
 * 实现步骤：
 *    1.定义一个类 实现    ServeltContextListener接口
 *    2.复写方法    
 *    3.配置
 * 
 */
@WebListener
public class listener implements ServletContextListener{

//	监听ServletContext对象正常关闭后执行
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
    System.out.println("contextDestroyed方法执行啦----------Listener");
		
	}

//	服务器启动后自动调用
//	监听ServletContext对象创建后执行
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		常用功能：加载资源文件
//		获取ServletContext 对象
		
		
    System.out.println("contextInitialized方法执行啦-------Listener");
		
	}

}