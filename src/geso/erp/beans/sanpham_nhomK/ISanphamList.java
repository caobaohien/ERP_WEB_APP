package geso.erp.beans.sanpham_nhomK;

import java.sql.ResultSet;

public interface ISanphamList {
	public void setMa(String ma);
	public String getMa();
	
	public void setTen(String ten);
	public String getTen();
	
	public void setTrangThai(String trangthai);
	public String getTrangThai();
	
	public void setRsData(ResultSet rs);
	public ResultSet getRsData();
	
	public void setUserId(String userId);
	public String getUserId();
	
	public void setMsg(String msg);
	public String getMsg();
	
	public void init(String search);
	public void createRs();
   
}
