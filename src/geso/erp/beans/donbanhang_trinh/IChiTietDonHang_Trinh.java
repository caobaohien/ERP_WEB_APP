package geso.erp.beans.donbanhang_trinh;

import geso.erp.db.sql.dbutils;

public interface IChiTietDonHang_Trinh {
	public dbutils getDb();
	public void setDb(dbutils db);
	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);
	public Double getSoLuong();
	public void setSoLuong(Double soLuong);
	public Double getDonGia();
	public void setDonGia(Double donGia);
	public Double getThanhTien();
	public void setThanhTien(Double thanhTien);
	public String getIdSanPham();
	public void setIdSanPham(String idSanPham);
	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);
	public String getDonViTinh();
	public void setDonViTinh(String donViTinh);
	public void closedb();
}
