package geso.erp.beans.donbanhang_thu;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public interface IDonBanHangList_Thu  extends IPhan_Trang{
	
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	public String getKhachHang();
	public void setKhachHang(String khachHang);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public dbutils getDb();
	public void setDb(dbutils db);
	
	public ResultSet getRsBanHang();
	public void setRsBanHang(ResultSet rsBanHang);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public void init(String string);
	
	public boolean deleteDonHang();
	
	public boolean chotDonHang();
	
	public ResultSet getRsKhachHang();
	
	public void setRsKhachHang(ResultSet rsKhachHang);
	
	public void DBclose();
	
	
}
