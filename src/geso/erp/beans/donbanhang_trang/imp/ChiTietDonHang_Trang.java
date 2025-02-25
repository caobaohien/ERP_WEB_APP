package geso.erp.beans.donbanhang_trang.imp;

import geso.erp.beans.donbanhang_trang.IChiTietDonHang_Trang;
 
 
public class ChiTietDonHang_Trang implements IChiTietDonHang_Trang
{
	private static final long serialVersionUID = -9217977546733610214L;
	private String banHangPK_SEQ;
	private String sanPhamPK_SEQ;
	private String maSanPham;
	private String tenSanPham;
	private String donViTinh;
	private double soLuong;
	private double soLuongTon;
	private double donGia;
	private double thanhTien;
	
	
	public ChiTietDonHang_Trang() {
		super();
		this.banHangPK_SEQ = "";
		this.sanPhamPK_SEQ = "";
		this.maSanPham = "";
		this.tenSanPham = "";
		this.donViTinh = "";
		this.soLuong = 0;
		this.soLuongTon = 0;
		this.donGia = 0;
		this.thanhTien = 0;
	}
	
	public ChiTietDonHang_Trang(String banHangPK_SEQ, String sanPhamPK_SEQ,
			String maSanPham, String tenSanPham, String donViTinh,
			double soLuong, double soLuongTon ,double donGia, double thanhTien) {
		super();
		this.banHangPK_SEQ = banHangPK_SEQ;
		this.sanPhamPK_SEQ = sanPhamPK_SEQ;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}

	public String getBanHangPK_SEQ() {
		return banHangPK_SEQ;
	}
	public void setBanHangPK_SEQ(String banHangPK_SEQ) {
		this.banHangPK_SEQ = banHangPK_SEQ;
	}

	public String getSanPhamPK_SEQ() {
		return sanPhamPK_SEQ;
	}
	public void setSanPhamPK_SEQ(String sanPhamPK_SEQ) {
		this.sanPhamPK_SEQ = sanPhamPK_SEQ;
	}

	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
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

	public double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}
	
	public double getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(double soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
