package geso.erp.beans.hopdong_tan;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IHopdong_tan extends Serializable, IPhan_Trang{
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getPk_hoso();
	public void setPk_hoso(String _pk_hoso);
	
	public String getPk_hopdong();
	public void setPk_hopdong(String _pk_hopdong);
	
	public String getQg_trangthai();
	public void setQg_trangthai(String _tv_trangthai);
	
	public String getQuocgia();
	public void setQuocgia(String _quocgia);
	
	public String getSohopdong();
	public void setSohopdong(String _sohopdong);
	
	public String getMsg();
	public void setMsg(String _msg);
	
	public ResultSet getRsList();
	public void setRsList(ResultSet _listHD);
	 
	public String getId();
	public void setId(String _id);
	
	public ResultSet getRsHoso();
	public void setRsHoso(ResultSet _rsHoso);
	 	
	public void init(String _str);
	public void CreateRs();
	public void DBclose();
	public boolean Delete();
	public boolean Chot();
}
