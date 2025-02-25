package geso.erp.beans.sanpham_hang.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_hang.ISanPhamList_Hang;
import geso.erp.db.sql.dbutils;

public class SanPhamList_Hang extends Phan_Trang implements ISanPhamList_Hang {
	private String maSanPham;
	private String tenSanPham;
	private String donViTinh;
	
	private dbutils db;
	private String msg;
	private ResultSet rsDataList;
	private ResultSet rsDonvi;
	private String userID;
	
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
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResultSet getRsDataList() {
		return rsDataList;
	}
	public void setRsDataList(ResultSet rsDataList) {
		this.rsDataList = rsDataList;
	}
	public ResultSet getRsDonvi() {
		return rsDonvi;
	}
	public void setRsDonvi(ResultSet rsDonvi) {
		this.rsDonvi = rsDonvi;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public SanPhamList_Hang(String maSanPham, String tenSanPham,
			String donViTinh, dbutils db, String msg, ResultSet rsDataList,
			String userID) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.db = db;
		this.msg = msg;
		this.rsDataList = rsDataList;
		this.userID = userID;
	}
	public SanPhamList_Hang() {
		this.maSanPham = "";
		this.tenSanPham ="";
		this.donViTinh ="";
		this.db = new dbutils();
		this.msg = "";
		this.userID = "";
	}
	public void init(String query){
		String sql="";
		if(query.trim().length() > 0)
			sql = query;
		else{			
			sql = "select sp.PK_SEQ as MASANPHAM, sp.TEN as TENSANPHAM, sp.DONVI_FK as MADONVI," +
			 	" dv.TEN as DONVI,sp.SOLUONG as SOLUONG, sp.GIABAN as GIABAN, sp.TRANGTHAI as TRANGTHAI " +
				" from SANPHAM as sp inner join DONVI as dv " +
				" on sp.DONVI_FK = dv.PK_SEQ ";
		}
		this.rsDataList = this.db.get(sql);
		getDataDonVi();
	}
	public void closedb(){
		this.db.shutDown();
	}
	
	public void getDataDonVi(){
		this.rsDonvi = this.db.get("select PK_SEQ, TEN from DONVI");		
	}
}
