package geso.erp.beans.donbanhang_hang.imp;

import geso.erp.beans.donbanhang_hang.IChiTietDonHang_Hang;
import geso.erp.db.sql.dbutils;

public class ChiTietDonHang_Hang implements IChiTietDonHang_Hang{
	
	private String maSanPham;
	private String maDonHang;
	private String tenSanPham;
	private String donViTinh;
	private float soLuong; //do người dùng nhập
	private float soLuongTon;// lấy từ bên table sản phẩm
	private float donGia;
	private float thanhTien;
	private String id;
	private String idSanPham;
	
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
	
	public float getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(float soLuong) {
		this.soLuong = soLuong;
	}
	
	public float getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(float soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}
	
	public ChiTietDonHang_Hang(String maSanPham,String maDonHang, String tenSanPham,
			String donViTinh, float soLuong, float soLuongTon, float donGia,
			float thanhTien, String id, String idSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.maDonHang = maDonHang;
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.id = id;
		this.idSanPham = idSanPham;
	}
	
	public ChiTietDonHang_Hang() {
		this.maSanPham = "";
		this.maDonHang = "";
		this.tenSanPham = "";
		this.donViTinh = "";
		this.soLuong = 0;
		this.soLuongTon = 0;
		this.donGia = 0;
		this.thanhTien = 0;
		this.id = "";
		this.idSanPham = "";
	}
	
	
	
}
