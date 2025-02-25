package geso.erp.beans.donbanhang_trinh;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDonBanHangList_trinh extends Serializable, IPhan_Trang  {
	public String getMaDonHang() ;
	
	public void setMaDonHang(String maDonHang) ;
	
	public String getTrangThai() ;
	
	public void setTrangThai(String trangThai) ;
	
	public String getKhachHang();
	
	public void setKhachHang(String khachHang);
	
	public String getMessage();
	
	public void setMessage(String message);
	
	public dbutils getDb();
	
	public void setDb(dbutils db);
	
	public ResultSet getRsBanHang();
	
	public void setRsBanHang(ResultSet rsBanHang);
	
	public String getUserId();
	
	//public void DBclose();
	public void closedb();
	
	public String getMaKhachHang();
	public void setMaKhachHang(String maKhachHang);
	
	public String getMesage();
	public void setMesage(String mesage);
	
	public void setUserId(String userId);
	public ResultSet getRsKhachHang() ;
	public void setRsKhachHang(ResultSet rsKhachHang);
	public void init(String query);
	public void LayKhachHang();
	
	
}
