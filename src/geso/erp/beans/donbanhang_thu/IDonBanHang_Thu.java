package geso.erp.beans.donbanhang_thu;

import geso.erp.beans.sanpham_thu.ISanPham_Thu;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.util.List;

public interface IDonBanHang_Thu {
	
	public String getUserId() ;
	public void setUserId(String userId);
	
	public String getMaDonHang() ;
	public void setMaDonHang(String maDonHang) ;
	
	public String getTrangThai() ;
	public void setTrangThai(String trangThai);
	
	public String getMaKhachHang() ;
	public void setMaKhachHang(String maKhachHang) ;
	
	public String getTenKhachHang() ;
	public void setTenKhachHang(String tenKhachHang);
	
	public String getNgayChungTu() ;
	public void setNgayChungTu(String ngayChungTu);
	
	public float getTongTien() ;
	public void setTongTien(float tongTien);
	
	public List<IChiTietDonHang_Thu> getDanhSachSanPham();
	public void setDanhSachSanPham(List<IChiTietDonHang_Thu> danhSachSanPham);

	
	
	public String getMsg() ;
	public void setMsg(String msg) ;
	public dbutils getDb() ;
	public void setDb(dbutils db);
	public ResultSet getRsBanHang();
	public void setRsBanHang(ResultSet rsBanHang) ;
	
	public ResultSet getRsKhachHang() ;
	
	public void setRsKhachHang(ResultSet rsKhachHang) ;
	
	public void init(String string);

	public void DBclose();
	
	public boolean update();
	public boolean insert();

	public boolean deleteDonHang();
	public boolean chotDonHang();
	
	public void layKhachHang();
	
	public void LayKhachHangTheoMaDonHang();
	
	
}
