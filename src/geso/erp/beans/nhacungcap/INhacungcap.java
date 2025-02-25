package geso.erp.beans.nhacungcap;
 
public interface INhacungcap   {

	 
	public String getUserId();
	public void setUserId(String userId);
	
	public String getShopId();
	public void setShopId(String ShopId);
	  
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	 	
	
	public String getMa ();
	public void setMa (String Ma);
	
	public String getId();
	public void setId(String Id);
	
  
	public String getTen();
	public void setTen(String ten);
	
	public String getDienthoai();
	public void setDienthoai(String dt);

	public String getDiachi();
	public void setDiachi(String diachi);
	 
	
	 
	public void setmsg(String msg);
	public String getmsg();
	 
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
  
}
