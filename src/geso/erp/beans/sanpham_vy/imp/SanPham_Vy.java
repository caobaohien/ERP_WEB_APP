package geso.erp.beans.sanpham_vy.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.db.sql.dbutils;
import geso.erp.util.UtilityKho_Vy;

public class SanPham_Vy implements ISanPham_Vy {
	private String PK_SEQ;
	private String maSanPham;
	private String maDonViTinh;
	private String tenSanPham;
	private String maTrangThai;
	private String nguoiTao;
	private String ngayTao;
	private String nguoiSua;
	private String ngaySua;
	private int soLuong;
	private String maNhanHang;
	private String maChungLoai;
	private String timKiem;
	private float giaBan;
	private float chietKhau;
	private String maShop;
	private dbutils dbutils;
	private ResultSet resultSet;
	private UtilityKho_Vy utilityKho = new UtilityKho_Vy();
	
	
	public SanPham_Vy() {
		this.PK_SEQ = "";
		this.maSanPham = "";
		this.maDonViTinh = "";
		this.tenSanPham = "";
		this.maTrangThai = "";
		this.nguoiTao = "";
		this.ngayTao = "";
		this.nguoiSua = "";
		this.ngaySua = "";
		this.soLuong = 0;
		this.maNhanHang = "";
		this.maChungLoai = "";
		this.timKiem = "";
		this.giaBan = 0;
		this.chietKhau = 0;
		this.maShop = "";
	}
	
	

	public SanPham_Vy(String pK_SEQ, String maSanPham, String maDonViTinh,
			String tenSanPham, int soLuong, float giaBan) {
		super();
		PK_SEQ = pK_SEQ;
		this.maSanPham = maSanPham;
		this.maDonViTinh = maDonViTinh;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}



	public SanPham_Vy(String pk_seq, String maSanPham, String maDonViTinh,
			String tenSanPham, String maTrangThai, String nguoiTao,
			String ngayTao, int soLuong, float giaBan, float chietKhau) {
		super();
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

	
	public SanPham_Vy(String PK_SEQ, String maSanPham, String maDonViTinh, String tenSanPham,
			int soLuong) {
		super();
		this.PK_SEQ = PK_SEQ;
		this.maSanPham = maSanPham;
		this.maDonViTinh = maDonViTinh;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
	}



	public String getPk_seq() {
		return PK_SEQ;
	}

	public void setPk_seq(String pk_seq) {
		this.PK_SEQ = pk_seq;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getmaDonViTinh() {
		return maDonViTinh;
	}

	public void setmaDonViTinh(String maDonViTinh) {
		this.maDonViTinh = maDonViTinh;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getMaTrangThai() {
		return maTrangThai;
	}

	public void setMaTrangThai(String maTrangThai) {
		this.maTrangThai = maTrangThai;
	}

	public String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRSDonViTinh() {
		String query = "SELECT PK_SEQ AS PK_SEQDONVITINH, " + 
								"TEN AS TENDONVITINH " +
						"FROM DONVI;";
		this.resultSet = dbutils.get(query);
	}

	@Override
	public void DBclose() {
		dbutils.shutDown();
		
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getResultSet() {
		// TODO Auto-generated method stub
		return this.resultSet;
	}

	
	
}
