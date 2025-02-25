package geso.erp.beans.donbanhang_trang;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public interface IDonBanHangList_Trang extends IPhan_Trang{
	
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);
	
	public String getKhachHang();
	public void setKhachHang(String khachHang);
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	public dbutils getDb();
	public void setDb(dbutils db);
	
	public String getMsq();
	public void setMsq(String msq);
	
	public ResultSet getRsDataList();
	public void setRsDataList(ResultSet rsDataList);
	
	public String getUserID();
	public void setUserID(String userID);
	
	public ResultSet getRsKhachHang();
	public void setRsKhachHang(ResultSet rsKhachHang);
	
	//Khởi tạo dữ liệu
	public void init(String query);
	
	//Đóng kết nối
	public void closedb();
}
