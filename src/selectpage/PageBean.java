package selectpage;

import java.util.List;

//PageBean 分页对象


public class PageBean<T> {
   private Integer totalCount;   //总记录数
   
   private Integer totalPage;    //总页码
 //通用	
   private List<T> list;         //每页的数据
   
   private Integer currentPage;  //当前页码
   
   private Integer rows;        //每页显示的记录数

 public Integer getTotalCount() {
	return totalCount;
}

public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
}

public Integer getTotalPage() {
	return totalPage;
}

public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
}

public List<T> getList() {
	return list;
}

public void setList(List<T> list) {
	this.list = list;
}

public Integer getCurrentPage() {
	return currentPage;
}

public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
}

public Integer getRows() {
	return rows;
}

public void setRows(Integer rows) {
	this.rows = rows;
}

@Override
public String toString() {
	return "PageBean [totalCount=" + totalCount + ", totalPage=" + totalPage + ", list=" + list + ", currentPage="
			+ currentPage + ", rows=" + rows + "]";
}
	
   
   
   
   
}
