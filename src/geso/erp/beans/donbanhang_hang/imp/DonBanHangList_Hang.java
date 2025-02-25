package geso.erp.beans.donbanhang_hang.imp; //chiu trach nhiem cho trang ban hang tong

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_hang.IDonBanHangList_Hang;
import geso.erp.db.sql.dbutils;

public class DonBanHangList_Hang extends Phan_Trang implements IDonBanHangList_Hang {
	//nhung chi tiet tim kiem
	private String maDonHang;
	private String khachHang;
	private String trangThai;
	
	private dbutils db;
	private String msq; //dung de bao loi
	private ResultSet rsDataList;//lay tat ca thong tin cua db do len man hinh
	private ResultSet rsKhachHang;
	private String userID;	
	
	public DonBanHangList_Hang(String maDonHang, String khachHang,
			String trangThai, dbutils db, String msq, ResultSet rsDataList,
			String userID) {
		super();
		this.maDonHang = maDonHang;
		this.khachHang = khachHang;
		this.trangThai = trangThai;
		this.db = db;
		this.msq = msq;
		this.rsDataList = rsDataList;
		this.userID = userID;
	}
	
	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}

	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}

	public DonBanHangList_Hang() {
		super();
		this.maDonHang = "";
		this.khachHang = "";
		this.trangThai = "";
		this.db = new dbutils();
		this.msq = "";
		this.userID = "";
	}
	
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public String getMsq() {
		return msq;
	}
	public void setMsq(String msq) {
		this.msq = msq;
	}
	public ResultSet getRsDataList() {
		return rsDataList;
	}
	public void setRsDataList(ResultSet rsDataList) {
		this.rsDataList = rsDataList;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	} // ai thao tac tren chuc nang thi se biet

	@Override
	public void init(String query) {
		// TODO Auto-generated method stub
		
		//truy van database de lay du lieu
		String sql="";
		if(query.trim().length() > 0)
			sql = query;
		else{
			
			sql = "select bh.PK_SEQ as MADONHANG, bh.NGAYCHUNGTU AS NGAYCHUNGTU, kh.TEN as KHACHHANG, " +
					" bh.TRANGTHAI, nv.TEN as NGUOITAO, bh.NGAYTAO, bh.TONGTIEN " +
					" from BANHANG as bh inner join KHACHHANG kh on kh.PK_SEQ = bh.KHACHHANG_FK" +
					" left join NHANVIEN nv on nv.PK_SEQ = bh.NGUOITAO " +
					" left join NHANVIEN nv1 on nv1.PK_SEQ = bh.NGUOISUA";
		}
		this.rsDataList = this.db.get(sql);
		//lay rs can thiet
		getDataKhachHang();
	}
	
	public void getDataKhachHang(){
		this.rsKhachHang = this.db.get("SELECT PK_SEQ, TEN FROM KHACHHANG");
		
	}

	@Override
	public void closedb() {
		this.db.shutDown();
		
	}
	
}
