package demologinplus;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import  java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

//创建数据源   Druid
public class jdbcUtils {
	
	public static DataSource ds;
	
	static {
		try {
			
			Properties pro = new Properties();
			
			pro.load(new  FileInputStream("D:\\Myjava\\javawebdemo\\javawebb\\javae\\src\\loginDemo\\produrid"));
			System.out.println(pro.toString());
			
		    ds = (DataSource) DruidDataSourceFactory.createDataSource(pro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		
		return ds;
	}
	
	public static Connection getconnection() throws SQLException{
		
		return ds.getConnection();
	}
	
}
