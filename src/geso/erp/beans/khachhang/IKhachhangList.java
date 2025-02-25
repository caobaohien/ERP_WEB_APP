package geso.erp.beans.khachhang;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IKhachhangList extends Serializable, IPhan_Trang
{
	 
	public String getUserId();
	public void setUserId(String userId);
	
	public String getShopId();
	public void setShopId(String ShopId);
	  
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getTrangthaiNew();
	public void setTrangthaiNew(String trangthainew);
	
	
	public String getMaNew();
	public void setMaNew(String Ma);
	
	public String getId();
	public void setId(String Id);
	
	public String getTenNew();
	public void setTenNew(String ten);
	
	public String getDienthoaiNew();
	public void setDienthoaiNew(String dt);

	public String getDiachiNew();
	public void setDiachiNew(String diachi);
	
	public String getMa();
	public void setMa(String Ma);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getDienthoai();
	public void setDienthoai(String dt);

	public String getDiachi();
	public void setDiachi(String diachi);
	 
	
	 
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
