package geso.erp.beans.banhang;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBanhangList extends Serializable, IPhan_Trang
{
	 
	public String getUserId();
	public void setUserId(String userId);
	
	public String getShopId();
	public void setShopId(String ShopId);
	 
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getKh();
	public void setKh(String Kh);
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDmhList();
	public void setDmhList(ResultSet dmhlist);
	 
	public String getSochungtu();
	public void setSochungtu(String sodonmuahang);
	
 	//Bao cao don mua hang
	public ResultSet getKhRs();
	public void setKhRs(ResultSet nccRs);
	
	 	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	 
}
