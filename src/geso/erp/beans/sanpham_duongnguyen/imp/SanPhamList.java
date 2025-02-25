package geso.erp.beans.sanpham_duongnguyen.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_duongnguyen.ISanPhamList;
import geso.erp.db.sql.dbutils;

public class SanPhamList extends Phan_Trang implements ISanPhamList {

	String ma;
	String ten;
	String donViTinh;
	String trangThai;
	String msg;
	String userId;
	ResultSet rsData;
	ResultSet rsDonVi;
	dbutils db;

	public SanPhamList() {
		ma = "";
		ten = "";
		donViTinh = "";
		trangThai = "";
		msg = "";
		userId = "";
		db = new dbutils();
	}

	@Override
	public void setMa(String ma) {
		this.ma = ma;
	}

	@Override
	public String getMa() {
		return this.ma;
	}

	@Override
	public void setTen(String ten) {
		this.ten = ten;
	}

	@Override
	public String getTen() {
		return this.ten;
	}

	@Override
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String getTrangThai() {
		return this.trangThai;
	}

	@Override
	public void setRsData(ResultSet rs) {
		this.rsData = rs;
	}

	@Override
	public ResultSet getRsData() {
		return this.rsData;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;

	}

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;

	}

	@Override
	public String getDonViTinh() {
		return this.donViTinh;
	}

	@Override
	public void init(String search) {
		String query = "SELECT SP.MA,SP.TEN,SP.TRANGTHAI,SP.PK_SEQ,DV.MA AS DONVI FROM SANPHAM SP "
				+ " LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK";
		if (search.length() > 0)
			query = search;
		this.rsData = db.get(query);
		CreateRsDonVi();
	}

	@Override
	public void CreateRsDonVi() {
		String query = "select pk_seq,ma from donvi";
		this.rsDonVi = db.get(query);
	}

	@Override
	public ResultSet getRsDonVi() {
		return this.rsDonVi;
	}

	@Override
	public void DBclose() {

	}

}
