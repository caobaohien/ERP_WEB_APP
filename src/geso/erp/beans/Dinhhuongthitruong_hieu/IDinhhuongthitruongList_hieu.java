package geso.erp.beans.Dinhhuongthitruong_hieu;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDinhhuongthitruongList_hieu extends Serializable, IPhan_Trang {
	public String getUserId();
	public void setUserId(String _userId);
	
	
 	public String getId();
	public void setId(String _id);
	
	
	
	public int getThang();
	public void setThang(int _thang);
	public int getNam();
	public void setNam(int _nam);
	
	public String getTrinhtrangthitruong();
	public void setTrinhtrangthitruong(String _trinhtrangthitruong);
	
	public int getThangnhandinh();
	public void setThangnhandinh(int _thangnhandinh);
	public int getQuynhandinh();
	public void setQuynhandinh(int _quynhandinh);
	
	public String getKehoachhanhdong();
	public void setKehoachhanhdong(String _kehoachhanhdong);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
	
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
	
	public void init(String _search);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_dh();
	public boolean Chot_dh();

}
