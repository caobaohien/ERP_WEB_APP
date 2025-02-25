package geso.erp.beans.chuyenkho_hieu;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IChuyenkhoList_hieu extends Serializable, IPhan_Trang {
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
	
	
	public String getNguoitao();
	public void setNguoitao(String _nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
 	
	public String getNguoisua();
	public void setNguoisua(String _nguoisua);
	
	public String getNgaysua();
	public void setNgaysua(String _ngaysua);
	
	public void setmsg(String _msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet _dmhlist);
	public ResultSet getRsck();
	public void setRsck(ResultSet _dmhlist);
	public ResultSet getRsck1();
	public void setRsck1(ResultSet _dmhlist);
	
	public void init(String _search);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_dh();
	public boolean Chot_dh();

}
