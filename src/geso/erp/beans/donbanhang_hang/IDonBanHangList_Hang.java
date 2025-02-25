package geso.erp.beans.donbanhang_hang;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public interface IDonBanHangList_Hang extends IPhan_Trang{
	
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
	public void setUserID(String userID);// ai thao tac tren chuc nang thi se biet
	
	public ResultSet getRsKhachHang();
	public void setRsKhachHang(ResultSet rsKhachHang);
	//load tat ca cac thong so
	public void init(String query);
	public void closedb();
}
