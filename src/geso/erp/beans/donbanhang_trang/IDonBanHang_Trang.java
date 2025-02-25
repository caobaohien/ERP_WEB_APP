package geso.erp.beans.donbanhang_trang;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.util.List;

public interface IDonBanHang_Trang {
	public String getId();
	public void setId(String id); 

	public String getTrangThai();
	public void setTrangThai(String trangThai);

	public String getKhachHang() ;
	public void setKhachHang(String khachHang);

	public String getNguoiTao();
	public void setNguoiTao(String nguoiTao);

	public String getNguoiSua();
	public void setNguoiSua(String nguoiSua);

	public float getTongTien();
	public void setTongTien(float tongTien);

	public dbutils getDb();
	public void setDb(dbutils db);

	public String getMsg();
	public void setMsg(String msg);
	
	public ResultSet getRsKhachHang();
	public void setRsKhachHang(ResultSet rsKhachHang);
	
	public void init(String query);
	
	public void getDataKhachHang();
	
	public void closedb();
	
	public boolean saveDonHang();
	public boolean deteleDonHang();
	
	public String getNgayChungTu();
	public void setNgayChungTu(String ngayChungTu);

	public String getNgayTao();
	public void setNgayTao(String ngayTao);

	public String getNgaySua();
	public void setNgaySua(String ngaySua);
	
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);
	public boolean chotDonHang();
	public boolean updateDonHang();
	public void getMaDonHangPDF();
	
	public List<IChiTietDonHang_Trang> getDanhSachSanPham();
	public void setDanhSachSanPham(List<IChiTietDonHang_Trang> danhSachSanPham);
}
