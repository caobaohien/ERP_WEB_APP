package geso.erp.beans.donbanhang_thu.imp;

import java.sql.ResultSet;

import geso.erp.beans.donbanhang_thu.IChiTietDonHang_Thu;
import geso.erp.db.sql.dbutils;

public class ChiTietDonHang_Thu implements IChiTietDonHang_Thu{

	private String maDonHang;
	private String maSanPham;
	private String tenSanPham;
	private float donGia;
	private int soLuong;
	private float thanhTien;
	private String maDonVi;
	private String tenDonVi;
	
	private dbutils db;
	
	public String getTenDonVi() {
		return tenDonVi;
	}
	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getMaDonVi() {
		return maDonVi;
	}
	public void setMaDonVi(String maDonVi) {
		this.maDonVi = maDonVi;
	}
	private String PK_SEQ;
	
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	public void setPK_SEQ(String pK_SEQ) {
		PK_SEQ = pK_SEQ;
	}
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	
	public dbutils getBd() {
		return db;
	}
	public void setBd(dbutils bd) {
		this.db = db;
	}
	public Float getDonGia() {
		return donGia;
	}
	public void setDonGia(Float donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(Float thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public ChiTietDonHang_Thu(String maDonHang, String maSanPham, Float donGia,
			int soLuong, Float thanhTien, dbutils bd) {
		super();
		this.maDonHang = maDonHang;
		this.maSanPham = maSanPham;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.db = db;
	}
	public ChiTietDonHang_Thu(String pk_sqe, String maSanPham,String tensp, Float donGia, String madonvi,int soLuong, Float ttien) {
		super();
		this.PK_SEQ=pk_sqe;
		this.maSanPham = maSanPham;
		this.donGia = donGia;
		this.maDonVi=madonvi;
		this.soLuong = soLuong;
		this.thanhTien=ttien;
	
	}
	
	public ChiTietDonHang_Thu(String pk_sqe, String maSanPham, String ten,String tensp, Float donGia, String madonvi,
			int soLuong) {
		super();
		this.PK_SEQ=pk_sqe;
		this.maSanPham = maSanPham;
		this.tenSanPham=ten;
		this.maDonVi=madonvi;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	
	public ChiTietDonHang_Thu() {
		super();
		this.maDonHang ="";
		this.maSanPham = "";
		this.donGia = (float)0.0;
		this.soLuong = 0;
		this.thanhTien =(float) 0.0;
		this.db =new dbutils();
	}
	
	@Override
	public void DBclose() {
		this.db.shutDown();
	}

	
	@Override
	public void init(String s){

	}
	
	public boolean insert(){
		return true;
	}
	public boolean update(){
		return true;
	}
	
}
