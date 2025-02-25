package geso.erp.beans.donbanhang_thai;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDonbanhangList extends Serializable, IPhan_Trang {
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
 
	public String getMaDH();
	public void setMaDH(String MaDH);
	
	public String getKhachhang();
	public void setKhachhang(String Khachhang);
	
	public String getTongtien();
	public void setTongtien(String Tongtien);
	
	public String getNgay();
	public void setNgay(String Ngay);
	
	public String getNguoitao();
	public void setNguoitao(String Nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String Ngaytao);
 	
	public String getTen();
	public void setTen(String Ten);
	
	public void setmsg(String msg);
	public String getmsg();
	
	public ResultSet getDataList();
	public void setDataList(ResultSet dmhlist);
	
	public ResultSet getListKH();
	public void setListKH(ResultSet rskh);
 
	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_dh();
	public void setNxtApprSplitting(int parseInt);
	
	public ResultSet rsTrangThai();
	
	
}
