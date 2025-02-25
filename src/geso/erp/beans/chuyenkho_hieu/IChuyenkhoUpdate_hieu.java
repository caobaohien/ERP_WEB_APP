package geso.erp.beans.chuyenkho_hieu;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface IChuyenkhoUpdate_hieu{
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String _id);
 
	public String getMa();
	public void setMa(String _ma);
	
	public String getNgaychuyenkho();
	public void setNgaychuyenkho(String _ngaychuyenkho);
	
	public String getKhochuyen();
	public void setKhochuyen(String _khochuyen);
	
	public String getKhonhan();
	public void setKhonhan(String _khonhan);
	
	public String getLydochuyen();
	public void setLydochuyen(String _lydochuyen);
	
	public void setmsg(String _msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet _dmhlist);
	public ResultSet getRsdvk();
	public void setRsdvk(ResultSet dvt);
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public ResultSet getRsck();
	public void setRsck(ResultSet _cklist);
	public ResultSet getRsck1();
	public void setRsck1(ResultSet _cklist);
	
	public List<ISanpham> getListSp();
	public void setListSp(List<ISanpham> _list);
	public boolean update();
	public double getBooked();
	public void setBooked(double _booked);
	public double getavailabel();
	public void setavailaber(double _availabel);
	
	
}
