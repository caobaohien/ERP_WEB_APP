package geso.erp.beans.nhacungcap;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface INhacungcapList  extends Serializable, IPhan_Trang {

	 
	public String getUserId();
	public void setUserId(String userId);
	
	public String getShopId();
	public void setShopId(String ShopId);
	  
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
	public void setDataList(ResultSet dmhlist);
	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public void init_Kh();
	 

}
