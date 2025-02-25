package geso.erp.beans.donbanhang_duongnguyen.Imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_duongnguyen.IDonHangList;
import geso.erp.db.sql.dbutils;

public class DonHangList extends Phan_Trang implements IDonHangList  {

	private String ma;
	private String ngayCT;
	private String tenKH;
	private String trangThai;
	private float tongTien;
	private String ngayTao;
	private String nguoiTao;
	private String userId;
	private String msg;
	private ResultSet rsKH;
	private ResultSet rsData;
	dbutils db;

	public DonHangList() {
		this.ma = "";
		this.ngayCT = "";
		this.tenKH = "";
		this.trangThai = "";
		this.tongTien = 0;
		this.ngayTao = "";
		this.nguoiTao = "";
		this.userId = "";
		this.msg = "";
		this.db = new dbutils();
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma = ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setNgayCT(String ngay) {
		// TODO Auto-generated method stub
		this.ngayCT = ngay;
	}

	@Override
	public String getNgayCT() {
		// TODO Auto-generated method stub
		return this.ngayCT;
	}

	@Override
	public void setTenKH(String tenKH) {
		// TODO Auto-generated method stub
		this.tenKH = tenKH;
	}

	@Override
	public String getTenKH() {
		// TODO Auto-generated method stub
		return this.tenKH;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangThai = trangThai;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangThai;
	}

	@Override
	public void setTongTien(float tongTien) {
		// TODO Auto-generated method stub
		this.tongTien = tongTien;
	}

	@Override
	public float getTongTien() {
		// TODO Auto-generated method stub
		return this.tongTien;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngayTao = ngayTao;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.ngayTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoiTao = nguoiTao;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.nguoiTao;
	}

	@Override
	public void setRsData(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData = rsData;
	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query ="select bh.pk_seq,bh.ngaychungtu,kh.ten,bh.trangthai,bh.tongtien,bh.ngaytao,nv.ten as tennv from BANHANG bh"
		+" left join NHANVIEN nv on bh.nguoitao=nv.pk_seq left join KHACHHANG kh on bh.khachhang_fk = kh.pk_seq ";
		if(search.length()>0)
			query= search;
		this.rsData = db.get(query);
		CreateRsKhachHang();
	}

	@Override
	public void CreateRsKhachHang() {
		// TODO Auto-generated method stub
		String query = "select pk_seq,ten from KHACHHANG";
		this.rsKH = db.get(query);
	}

	@Override
	public ResultSet getRsKhachHang() {
		// TODO Auto-generated method stub
		return this.rsKH;
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub

	}

}
