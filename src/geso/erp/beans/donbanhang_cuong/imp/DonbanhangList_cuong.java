package geso.erp.beans.donbanhang_cuong.imp;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_cuong.IDonbanhangList_cuong;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public class DonbanhangList_cuong extends Phan_Trang implements IDonbanhangList_cuong {



	private String madh;
	private String ngayCT;
	private String khachhang;
	private String trangthai;
	private float tongtien;
	private String ngaytao;
	private String nguoitao;
	private String userId;
	private String msg;
	private ResultSet rsKH;
	private ResultSet rsData;
	dbutils db;

	public DonbanhangList_cuong() {
		this.madh = "";
		this.ngayCT = "";
		this.khachhang = "";
		this.trangthai = "";
		this.tongtien = 0;
		this.ngaytao = "";
		this.nguoitao = "";
		this.userId = "";
		this.msg = "";
		this.db = new dbutils();
	}

	public void setMa(String madh) {
		// TODO Auto-generated method stub
		this.madh = madh;
	}

	public String getMa() {
		// TODO Auto-generated method stub
		return this.madh;
	}

	public void setNgayCT(String ngay) {
		// TODO Auto-generated method stub
		this.ngayCT = ngay;
	}

	public String getNgayCT() {
		// TODO Auto-generated method stub
		return this.ngayCT;
	}

	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangthai = trangThai;
	}

	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	public void setTongTien(float tongTien) {
		// TODO Auto-generated method stub
		this.tongtien = tongTien;
	}

	public float getTongTien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}

	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngaytao = ngayTao;
	}

	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}

	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoitao = nguoiTao;
	}

	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.nguoitao;
	}

	public void setRsData(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData = rsData;
	}

	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	public void init(String search) {
		// TODO Auto-generated method stub
		String query ="select bh.pk_seq,bh.ngaychungtu,kh.ten,bh.trangthai,bh.tongtien,bh.ngaytao,nv.ten as tennv from BANHANG bh"
		+" left join NHANVIEN nv on bh.nguoitao=nv.pk_seq left join KHACHHANG kh on bh.khachhang_fk = kh.pk_seq ";
		if(search.length()>0)
			query= search;
		this.rsData = db.get(query);
		CreateRsKhachHang();
	}

	public void CreateRsKhachHang() {
		// TODO Auto-generated method stub
		String query = "select pk_seq,ten from KHACHHANG";
		this.rsKH = db.get(query);	
	}

	public ResultSet getRsKhachHang() {
		// TODO Auto-generated method stub
		return this.rsKH;
	}

	public void DBclose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setKhachhang(String khachhang) {
		// TODO Auto-generated method stub
		this.khachhang=khachhang;
	}

	@Override
	public String getkhachhang() {
		// TODO Auto-generated method stub
		return this.khachhang;
	}




}
