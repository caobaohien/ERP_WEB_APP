package geso.erp.beans.donbanhang_kiet02.imp;

import java.sql.ResultSet;

import geso.erp.beans.donbanhang_kiet02.ISanPham;
import geso.erp.db.sql.dbutils;

public class SanPham implements ISanPham {

	String PK_SEQ, maSanPham, maDonViTinh, tenSanPham,
			maTrangThai, nguoiTao, ngayTao;
	
	int soLuong;
	float giaBan, chietKhau, thanhTien;
	dbutils dbutils;
	ResultSet resultSet;
	
	public SanPham(){
		this.PK_SEQ = "";
		this.maSanPham = "";
		this.maDonViTinh = "";
		this.tenSanPham = "";
		this.maTrangThai = "";
		this.nguoiTao = "";
		this.ngayTao = "";
		this.soLuong = 0;
	}
	
	public SanPham(String pK_SEQ, String maSanPham, String maDonViTinh,
			String tenSanPham, int soLuong, float giaBan) {
		super();
		PK_SEQ = pK_SEQ;
		this.maSanPham = maSanPham;
		this.maDonViTinh = maDonViTinh;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}
	
	public SanPham(String pk_seq, String maSanPham, String maDonViTinh,
			String tenSanPham, String maTrangThai, String nguoiTao,
			String ngayTao, int soLuong, float giaBan, float chietKhau) {
		this.PK_SEQ = pk_seq;
		this.maSanPham = maSanPham;
		this.maDonViTinh = maDonViTinh;
		this.tenSanPham = tenSanPham;
		this.maTrangThai = maTrangThai;
		this.nguoiTao = nguoiTao;
		this.ngayTao = ngayTao;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.chietKhau = chietKhau;
	}
	
	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public float getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(float chietKhau) {
		this.chietKhau = chietKhau;
	}
	
	public float getThanhTien() {
		return this.thanhTien;
	}
	
	public void setThanhTien(float tt) {
		this.thanhTien = tt;
	}
	
	@Override
	public String getPk_seq() {
		// TODO Auto-generated method stub
		return PK_SEQ;
	}

	@Override
	public void setPk_seq(String pk_seq) {
		// TODO Auto-generated method stub
		this.PK_SEQ = pk_seq;
	}

	@Override
	public String getMaSanPham() {
		// TODO Auto-generated method stub
		return maSanPham;
	}

	@Override
	public void setMaSanPham(String maSanPham) {
		// TODO Auto-generated method stub
		this.maSanPham = maSanPham;
	}

	@Override
	public String getmaDonViTinh() {
		// TODO Auto-generated method stub
		return maDonViTinh;
	}

	@Override
	public void setmaDonViTinh(String maDonViTinh) {
		// TODO Auto-generated method stub
		this.maDonViTinh = maDonViTinh;
	}

	@Override
	public String getTenSanPham() {
		// TODO Auto-generated method stub
		return tenSanPham;
	}

	@Override
	public void setTenSanPham(String tenSanPham) {
		// TODO Auto-generated method stub
		this.tenSanPham = tenSanPham;
	}

	@Override
	public String getMaTrangThai() {
		// TODO Auto-generated method stub
		return maTrangThai;
	}

	@Override
	public void setMaTrangThai(String maTrangThai) {
		// TODO Auto-generated method stub
		this.maTrangThai = maTrangThai;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return nguoiTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoiTao = nguoiTao;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return ngayTao;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngayTao = ngayTao;
	}

	@Override
	public int getSoLuong() {
		// TODO Auto-generated method stub
		return soLuong;
	}

	@Override
	public void setSoLuong(int soLuong) {
		// TODO Auto-generated method stub
		this.soLuong = soLuong;
	}

	@Override
	public ResultSet getResultSet() {
		// TODO Auto-generated method stub
		return this.resultSet;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRSDonViTinh() {
		// TODO Auto-generated method stub
		String query = "SELECT PK_SEQ AS PK_SEQDONVITINH, "
				+ "TEN AS TENDONVITINH " + "FROM DONVI;";
		this.resultSet = dbutils.get(query);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		dbutils.shutDown();
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

}
