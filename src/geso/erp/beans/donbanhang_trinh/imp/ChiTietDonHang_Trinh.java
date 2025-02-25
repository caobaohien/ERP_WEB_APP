package geso.erp.beans.donbanhang_trinh.imp;

import geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh;
import geso.erp.db.sql.dbutils;

public class ChiTietDonHang_Trinh implements IChiTietDonHang_Trinh {
	private String idSanPham;
	private String maSanPham;
	private String maDonHang;
	private Double soLuong;
	private Double donGia;
	private Double thanhTien;
	private dbutils db;
	private String tenSanPham;
	private String donViTinh;
	
	
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	
	public String getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public Double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Double soLuong) {
		this.soLuong = soLuong;
	}
	public Double getDonGia() {
		return donGia;
	}
	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}
	public Double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public ChiTietDonHang_Trinh(String idSanPham, String maSanPham,
			String maDonHang, Double soLuong, Double donGia, Double thanhTien,
			String tenSanPham, String donViTinh) {
		super();
		this.idSanPham = idSanPham;
		this.maSanPham = maSanPham;
		this.maDonHang = maDonHang;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.db = new dbutils();
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
	}
	public ChiTietDonHang_Trinh() {
		super();
		this.idSanPham = "";
		this.maSanPham = "";
		this.maDonHang = "";
		this.soLuong = 0.0;
		this.donGia = 0.0;
		this.thanhTien = 0.0;
		this.db = new dbutils();
		this.tenSanPham = "";
		this.donViTinh = "";
	}
	
	public void closedb(){
		db.shutDown();
	}
}
