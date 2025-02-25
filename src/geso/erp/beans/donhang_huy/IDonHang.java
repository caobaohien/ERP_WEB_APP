package geso.erp.beans.donhang_huy;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IDonHang extends Serializable,IPhan_Trang{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
 	public String getId();
	public void setId(String Id);
 
	public String getMa();
	public void setMa(String Ma);
	
	public String getTen();
	public void setTen(String ten);
	
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet rskh);
	public ResultSet getRskh();
	public void setRskh(ResultSet rskh);
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean save();
}
