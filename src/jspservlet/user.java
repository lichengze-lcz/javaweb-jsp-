package jspservlet;

import java.util.Date;
import java.text.SimpleDateFormat;



//javaBean

public class user {

	private String name;
	private int age;
	private Date brithday;
	
	public String getName() {
		return name;
	}	
		
	
	//逻辑视图
	public String getBirStr(){
		
		if(brithday != null) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日 HH时mm分ss秒");
		return sdf.format(brithday);
	
		}else {
			return "";
		}
	}
		
		
		
		
		
		
		
		
		
		
	
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBrithday() {
		return brithday;
	}
	@Override
	public String toString() {
		return "user [name=" + name + ", age=" + age + ", brithday=" + brithday + "]";
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public user(String name, int age, Date brithday) {
		super();
		this.name = name;
		this.age = age;
		this.brithday = brithday;
	}
	public user() {
		super();
	}
	
	
	
	
	
	
	
	
	
	
}
