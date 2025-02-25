package geso.erp.beans.donbanhang_duongnguyen.Imp;

import geso.erp.beans.donbanhang_duongnguyen.ISanPham;

public class SanPham implements ISanPham {
	String banHangId;
	String id;
	String maSP;
	String tenSP;
	String donViTinh;

	double soLuong;
	double donGia;
	double thanhTien;

	@Override
	public String getBanHangId() {
		// TODO Auto-generated method stub
		return this.banHangId;
	}

	@Override
	public void setBanHangId(String banHang_fk) {
		// TODO Auto-generated method stub
		this.banHangId = banHang_fk;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getMaSP() {
		// TODO Auto-generated method stub
		return this.maSP;
	}

	@Override
	public void setMaSP(String maSP) {
		// TODO Auto-generated method stub
		this.maSP = maSP;
	}

	@Override
	public String getTenSP() {
		// TODO Auto-generated method stub
		return this.tenSP;
	}

	@Override
	public void setTenSP(String tenSP) {
		// TODO Auto-generated method stub
		this.tenSP = tenSP;
	}

	@Override
	public String getDVT() {
		// TODO Auto-generated method stub
		return this.donViTinh;
	}

	@Override
	public void setDVT(String dvt) {
		// TODO Auto-generated method stub
		this.donViTinh = dvt;
	}

	@Override
	public double getSoLuong() {
		// TODO Auto-generated method stub
		return this.soLuong;
	}

	@Override
	public void setSoLuong(double soLuong) {
		// TODO Auto-generated method stub
		this.soLuong = soLuong;
	}

	@Override
	public double getDonGia() {
		// TODO Auto-generated method stub
		return this.donGia;
	}

	@Override
	public void setDonGia(double donGia) {
		// TODO Auto-generated method stub
		this.donGia = donGia;
	}

	@Override
	public double getThanhTien() {
		// TODO Auto-generated method stub
		return this.thanhTien;
	}

	@Override
	public void setThanhTien(double thanhTien) {
		// TODO Auto-generated method stub
		this.thanhTien = thanhTien;
	}

}
