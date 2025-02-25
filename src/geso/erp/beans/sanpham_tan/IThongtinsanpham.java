package geso.erp.beans.sanpham_tan;

import java.sql.ResultSet;

public interface IThongtinsanpham {


	 
	public String getUserId();
	public void setUserId(String userId);
 	  
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	 	
	
	public String getMa ();
	public void setMa (String Ma);
	
	public String getId();
	public void setId(String Id);
	

	public String getDvt();
	public void setDvt(String dvt);
	
	
	public ResultSet getRsDvt();
	public void setRsDvt(ResultSet dvt);
	
	
	
	public String getTen();
	public void setTen(String ten);
 
	 
	public void setmsg(String msg);
	public String getmsg();
	 
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
  

}
