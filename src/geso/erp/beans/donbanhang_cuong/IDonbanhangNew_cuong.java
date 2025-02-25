package geso.erp.beans.donbanhang_cuong;

import java.sql.ResultSet;

public interface IDonbanhangNew_cuong {
	public void setId(String id);
	public String getId();
	public void setMaDH(String MaDH);
	public String getMaDH();
	
	public void setTrangThai(String trangthai);
	public String getTrangThai();
	
	public void setKhachHang(String khachhang);
	public String getKhachHang();
	
	public void setUserId(String userId);
	public String getUserId();
	
	public void setNgay(String khachhang);
	public String getNgay();
	
	public void setTongTien(String khachhang);
	public String getTongTien();
	
	public void setNgayTao(String khachhang);
	public String getNgayTao();
	
	public void setNguoiTao(String khachhang);
	public String getNguoiTao();
	public ResultSet getrskhachhang();
	
	public void setRsData(ResultSet rs);
	public ResultSet getRsdata();
	public void setmsg(String msg);
	public String getmsg();
	public void init(String search);
	public void CreatRS();
	public void DBclose();
	public boolean save();
}
