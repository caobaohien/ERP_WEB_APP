package geso.erp.beans.donbanhang_trinh;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.util.List;

public interface IDonBanHang_Trinh {
	
	public String getTrangThai();
	
	public void setTrangThai(String trangThai);
	
	public String getTenKhachHang();
	
	public void setTenKhachHang(String tenKhachHang);
	
	public String getNgayTao();
	
	public void setNgayTao(String ngayTao);
	
	public double getSoTien();
	
	public void setSoTien(double soTien);
	
	public String getIdUser();
	
	public void setIdUser(String idUser);
	
	public String getMessage();
	
	public void setMessage(String message);
	
	public dbutils getDb();
	
	public void setDb(dbutils db);
	
	public ResultSet getRsDonHang();
	
	public void setRsDonHang(ResultSet rsDonHang);
	
	public ResultSet getRsKhachHang();
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);
	public void setRsKhachHang(ResultSet rsKhachHang);
	
	public String getMaKhachHang();
	public void setMaKhachHang(String maKhachHang);
	public List<IChiTietDonHang_Trinh> getDanhsachSanPham();
	public void setDanhsachSanPham(List<IChiTietDonHang_Trinh> danhsachSanPham);
	
	
	public void init(String chuoi);
	
	
	public void closedb();
	public boolean insert();
	public boolean update();
	public String getDateTime();
	
	public void LayKhachHang();
	

	public boolean deleteDonHang();
	
	public String getNgayChungTu();
	public void setNgayChungTu(String ngayChungTu);
	public void LayKhachHangTheoMa();
	public boolean chotDonHang() ;
}
