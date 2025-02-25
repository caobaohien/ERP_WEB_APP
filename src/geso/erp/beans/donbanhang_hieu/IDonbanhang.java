package geso.erp.beans.donbanhang_hieu;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IDonbanhang  extends Serializable, IPhan_Trang {

	 
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String _id);
 
	public String getMa();
	public void setMa(String _ma);
	
	public String getNgaychungtu();
	public void setNgaychungtu(String _ngaychungtu);
	
	public String getKhachhang();
	public void setKhachhang(String _khachhang);
	
	public double getThanhtien();
	public void setThanhtien(double _thanhtien);
	public double getTongtien();
	public void setTongtien(double _tongtien);
	
	public String getNguoitao();
	public void setNguoitao(String _nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
 	
	public void setmsg(String _msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet _dmhlist);
	public ResultSet getRskh();
	public void setRskh(ResultSet _dmhlist);
	public void init(String _search);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_dh();
	public boolean Chot_dh();

}
