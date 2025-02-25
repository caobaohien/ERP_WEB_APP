package geso.erp.beans.donhang_huy;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IDonHangNew extends Serializable,IPhan_Trang {
	public void setTongtien(double tongtien);
	public double getTongtien();
	public void setNgaychungtu(String ngay);
	public String getNgaychungtu();
	public void setTrangthai(String trangthai);
	public String getTrangthai();
	public String getmaDH();
	public void setMaDH(String mamh);
	public String getUserId();
	public void setUserId(String userId);
 	public String getId();
	public void setId(String Id);
	public String getMaKH();
	public void setMaKH(String Ma);
	public String getTenKH();
	public void settenKH(String ten);
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDataListDH();
	public void setDataListDh(ResultSet rskh);
	public ResultSet getDataListKH();
	public void setDataListKH(ResultSet rskh);
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
}
