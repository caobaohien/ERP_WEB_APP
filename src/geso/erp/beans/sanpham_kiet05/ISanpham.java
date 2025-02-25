package geso.erp.beans.sanpham_kiet05;

import java.sql.ResultSet;

public interface ISanpham {
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getMa();
	public void setMa(String ma);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getId();
	public void setId(String id);
	
	public String getDonvitinh();
	public void setDonvitinh(String dvt);
	
	public ResultSet getRsdDvt();
	public void setRsDvt(ResultSet rsDvt);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public void init();
	public void createRs();
	public void dbClose();
	public boolean save();
	public boolean update();
}
