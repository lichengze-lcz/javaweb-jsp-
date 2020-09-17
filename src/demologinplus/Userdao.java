package demologinplus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.util.JdbcUtils;

import selectpage.PageBean;

//操作数据库增删改查。

public class  Userdao {
    //德鲁伊连接池 操作 templat
	jdbcUtils jdbcUtils=new jdbcUtils();
	JdbcTemplate template = new JdbcTemplate((DataSource) jdbcUtils.getDataSource());
      
    //验证用户名和密码， 用于初次登录页面
	public User login(User loginUser){    
		
		try {
			String sql = "select * from Demologin where username =? and password =?";
		
            User user = template.queryForObject(sql,
    		 
            //查到  数据库数据映射到 User对象     泛型        类型
	        new BeanPropertyRowMapper<User>(User.class),loginUser.getUsername(),loginUser.getPassword());
	        //	
     
 	        System.out.println("login方法 里的javaBean"+user);
 	     
 	       
			return user;    
		  } catch (DataAccessException e) {
			return null;
		}
	}
	
	
   //查找数据库 所有数据 ,封装到User对象
   public List<User> finAll(){      
		try {
			String sql = "select * from DemoLogin";
			//该方法数据类型 需包装类
            List<User> users = template.query(sql,
    		 
	        new BeanPropertyRowMapper<User>(User.class));
     
 	        System.out.println("finAll方法里的javaBean"+users);
			return users;    
		 
		  } catch (DataAccessException e) {
			return null;
		}
	}
	

	 //添加数据库到数据数据库 ，封装到User对象
     public void adddate(User user) {
		 //自增长第一个不需赋值null
    	 String sql = "insert into DemoLogin values(?,?,?,?,?,?,null,null)";
    	 //ִ
    	 template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAdress(),user.getQq(),user.getEmall());   
	      
     }	
	
	
	
	//根据指定参数id 删除数据库数据 
	public void delete(int id) {
		String sql = "delete from  DemoLogin where id = ?";
		
		template.update(sql,id);
		
	}
	
	
	//根据指定参数id 找到指定数据，   用于修改数据库数据准备工作 （先找到再进行修改）
	public User findById(int id){
		
		String sql = "select * from DemoLogin where id =?";
		return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
	}
	
	
	//根据指定参数id找到的数据  修改用户信息
	public void update(User user) {
		String sql ="update DemoLogin set name =?, gender =?, age =?, adress =?, qq =?, emall =? where id =?";
	
		template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAdress(),user.getQq(),user.getEmall(),user.getId());
	}
	
	
	//批量删除数据库用户        用于全选框 批量删除
	public void delSelecteId(String[] ids) {
		for (String id : ids) {
			//调用删除方法
			delete(Integer.parseInt(id));
			
		}
	}
	
	
//----分页查询准备方法-----------------------------------------------------------------------------------------------------------	

//查询数据库总数据记录数 封装到User对 象  ----
	public int findTotalcount() {
		//定义sql 查该表总记录
		String sql = "select count(*) from DemoLogin";
		
		//返回一 Integer 类型数据 （ 1.5自动拆箱 int不用换）
		return template.queryForObject(sql, Integer.class);
	}
	
	
//分页查询  返回该页用户信息
	public List<User> findByPage(int start, int rows,Map<String, String[]> condition){
		
		//定义sql   limit从？开始到显示？条   但sql server不支持      (分页搜索框   与查询所有拼接字符串一)
		
		String sql = "select top 5 * from DemoLogin where id BETWEEN ? AND ?";
		
		//执行sql  BeanPropertyRowMapper() 返回一个对象  如果调用方法：多结果集返回集合，单结果集返回一对象
		System.out.println(template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows)+"查出来的数据");
		System.out.println(start+"-----"+rows);
		
		return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);

		
	}
	
	
	
//----分页查询-----------------------------------------------------------------------------------------------------------
 
 public PageBean<User> findUserByPage(String _currentPage,String _rows,Map<String, String[]> condition){
	 
	 //数据类型转换
	 int currentPage =Integer.parseInt(_currentPage);
	 int rows =Integer.parseInt(_rows);
	 
	 //1.创建一个空的PageBean对象
	 PageBean<User> pb = new PageBean<User>(); 
	 
	 //2.设置参数
	 //当前页码
	 pb.setCurrentPage(currentPage);
	 //当前条数
	 pb.setRows(rows);
	 
	 
	 //3.  查询总记录数          调用 findTotalcount()方法  
	 int totalCount = conditionSelete(condition);
	 pb.setTotalCount(totalCount);
	 
	 //4.查询List集合
	 //记录一下开始的索引  当前页码 - 1 * 记录数             例如20条  当前第2页 2-1  记录数是5  从五开始查
	 int start = (currentPage - 1)* rows;
	 
	 //返回一List集合 里面放User对象
	 //分页查询每页记录   调用 findByPage(start,rows)方法   
	 
	 List<User> list = findByPage(start,rows,condition);
	 
	 pb.setList(list);
	 
	 //5.只是记录总页码                                                                                                
	 int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows)+1;
	 pb.setTotalPage(totalPage); 
	 
    return pb;
 }	
	
 
//--***搜索框--动态sql---------------------------------------------------------------------
//改造的查询所有方法
	public int conditionSelete(Map<String, String[]> condition) {
	
		//定义模板初始化sql
		String sql = "select count(*) from DemoLogin where 1 = 1 ";
		//准备拼接字符串
		StringBuilder sb = new StringBuilder(sql);
		
		//遍历map
       Set<String> keyset = condition.keySet();
       
       //定义参数集合
       List<Object> params = new ArrayList<Object>();
       
       for (String key : keyset) {
    	   
    	   //排除分页条件数
    	   if("currentPage".equals(key) ||"rows".equals(key)) {
    		   continue;
    	   }
    	   
		//根据k 获取 v  v参数条件为一个
       String value = condition.get(key)[0];
       
       //*判断value 是否有值   搜索条件
         if(value != null && !"".equals(value)) {
       	//有值 （搜索条件） 拼接字符串
       	sb.append(" and " + key + " like ? ");   //拼字符串
       	//拼几个value 参数就有几个
       	params.add("%"+value+"%"); //加参数（条件相似）
         }
		}
		
		System.out.println(sb.toString());
		System.out.println(params);
		
		return template.queryForObject(sb.toString(),Integer.class,params.toArray());   //该方法可变参
	}
	
//--List搜索框-----------------------------------------------------------------------------------------
	 public List<User> Scondition(Map<String, String[]> condition){      
			try {
				//定义模板初始化sql
				String sql = "select * from DemoLogin where 1 = 1 ";
				//准备拼接字符串
				StringBuilder sb = new StringBuilder(sql);
				
				//遍历map
		       Set<String> keyset = condition.keySet();
		       
		       //定义参数集合
		       List<Object> params = new ArrayList<Object>();
		       
		       for (String key : keyset) {
		    	   
				//根据k 获取 v  v参数条件为一个
		       String value = condition.get(key)[0];
		       
		       //*判断value 是否有值   搜索条件
		         if(value != null && !"".equals(value)) {
		       	//有值 （搜索条件） 拼接字符串
		       	sb.append(" and " + key + " like ? ");   //拼字符串
		       	//拼几个value 参数就有几个
		       	params.add("%"+value+"%"); //加参数（条件相似）
		         }
				}
				
				System.out.println(sb.toString());
				System.out.println(params);
				
				//该方法数据类型 需包装类
	            List<User> users = template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
	     
	 	        System.out.println("finAll方法里的javaBean"+users);
				return users;    
			 
			  } catch (DataAccessException e) {
				return null;
			}
		}
	
}
