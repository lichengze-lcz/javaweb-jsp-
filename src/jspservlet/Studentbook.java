package jspservlet;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/Mvc")
public class Studentbook extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("request1", "requ啦啦啦啦");
	request.getRequestDispatcher("EL1.jsp").forward(request, response);
	
	HttpSession session = request.getSession();
	  
	
	session.setAttribute("session1","Session���������");
	System.out.println(";;;;;");
	
	
	
	}

	/*
	
1.MVCģʽ
	
	1.jsp�ݱ���ʷ
	    1.����ֻ��Servlet��ֻ����respose�����ǩ���鷳
	    2.����jsp�󣬼��˿���������ʹ��jsp���Էֹ�
	    3.����javaweb���mvc����ģʽ��������Ӻ�����
	
	
	2.ʲô��MVCģʽ    ��JavaWbe�� 
	
	    M�� model ģ��       ��JavaBeanʵ��      
	                 ��ɾ����ҵ������磺��ѯ���ݿ⣬��װ����    
	     
	     
	    V: View  ��ͼ      ��JSPʵ��
	                 չʾ���ݵ� 
	    
	    C�� Controller  ������    ��Servletʵ��
	                 ��ȡ�û����루��Ʋ�����
	                ����ģ��           
	                ��ģ�Ͳ���������ݽ���  ��ͼ չʾ     ��Ҫʹ����������չʾ��          
	
	
	   �ŵ㣺1.����Եͣ�����ά�����������ڷֹ�Э��
	     2.�����Եͣ�����jsp �����Խ����ƶ���
	
	   ȱ�㣺ʹ����Ŀ�ܹ����ӣ��Կ���Ҫ��ߣ����ʺ�С��
	
	
	*/
	
	
	/*
	
2.El���ʽ
	  1.���Expression language  �������
	  2.���ã��滻�ͼ�JSpҳ���е�java����
	  3.�﷨��$(���ʽ)   jspĬ��֧��El���ʽ      isELIgnored="true"  \{...}   El���ʽ��ԭ�����  
	
	
	
	  4.ʹ�ã�
	     *���㣺
	         1.�����   + - *  /(div)  %(mod)  
	         2.�Ƚ��������         ><  >=  ==  !=       
	         3.�߼��������        $$(and)  ||(or)  !(not)  
	         4.���������           empty   
	                                �����ж��ַ��������ϣ���������Ƿ�Ϊ null ����Ϊ0   $(empy list)  ��{not empty}
	           
	           ��*��ȡֵ��  
	         1.EL���ʽ��ֻ�ܴ�������л�ȡֵ
	         2.
	                                  �﷨�� $(������.����)   ��ָ�����л�ȡָ����ֵ    ͬʱ��ȡ���ͻ
	                *������      
	                   1.pageScope ---> PageContext
	
	                   2.requsetScope --->request
	                   
	                   3.sessionScope --->session
	                   
	                   4.applicationScop--->application     (ServletContext) 
	                                            �� ��request���д����� name = ����
	                                            ��ȡ  ${requestScope.name}    ��ӡ������     //����ʧ�� ��ӡ���ַ��� ����ʾnull
	
	
	              ${����}����ʾ���δ���С�����в����Ƿ��иü���Ӧ��ֵ��ֱ��ֱ��Ϊֹ PageContext--request session application
	                 
	         3.��ȡ ����  List  map ����
	                1.����user${������.����.����ֵ}
	
	                2.List����  ${������.����[����]}
	                
	                3.Map����   ${������.����.key����}  or     ${������.����["key����"]}
	                
	                
	                
	                
	         4.��ʽ����      el���ʽһ��11��        ����jap���ö���
	           
	                         ����һ�� pageContext  ���ö���
	                              ����1.���Ի�ȡ����jsp���ö���   
	                              
	        EL����11������,���趨���ֱ��ʹ�� 
pageScope        ��ȡpage��������ɵ�Map
requestScope     ��ȡreqeust��������ɵ�Map
sessionScope     ��ȡsession��������ɵ�Map
applicationScope ��ȡapplication��������ɵ�Map

pageContext      ��ȡjsp��9����ʽ����, �� ${pageContext.request}        
initParam        ��ȡ��web.xml���õ�WEBӦ�ò���

param           �������Map<String,String>,   ��: ${param.userName}
paramValues     �������Map<String,String[]>, ��: ${paramValues.userName[1]} 
header          ����ͷMap<String,String[]>,   ��: ${header["Accept-Language"] }
headerValues    ����ͷMap<String,String[]> 

cookie       ��ȡcookie����, �� ${cookie.JSESSIONID.name} ${cookie.JSESSIONID.value}
��������������������������������
                   
	                              
	                                    
	                                   
	                    
	                
3.JSTL
        1.���javaServlet  pages Tag Library        JSP��ǩ��
           *����Apache��֯�ṩ�Ŀ�Դ��jsp��ǩ
      
      	2.���ã����ڼ򻯺��滻 JSP ҳ���ϵ� java���� 
      	
      	3.ʹ�ò���
      	  1.����jstl���jar��
      	  2.�����ǩ�⣺ taglibָ�� �� <%@tablib%>   
      	  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>                 
	      3.ʹ��          
	                
	    4.����JSTL��ǩ
	      1.if      ��java�����   if
	                         ���ԣ�
             text���������ԣ�����boolean���ʽ
                  *������ʽΪtrue ����ʾ if��ǩ������ ��֮������
                  *һ������£�test����ֵ���� el ���ʽһ��ʹ��
	      
	      2.choose  ��java�����  Switch
                  when��ǩ��Ϊ�ж�             ===case
                  otherwire��ǩ���������===default
	      
	      
	      3.foreach ��java����� for���              
	                
	                
	                
	                
	                
	                
	                
	                
	*/
}
