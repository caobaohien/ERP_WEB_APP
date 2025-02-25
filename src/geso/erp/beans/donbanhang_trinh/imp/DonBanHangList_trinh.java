package geso.erp.beans.donbanhang_trinh.imp;


import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_trinh.IDonBanHangList_trinh;
import geso.erp.db.sql.dbutils;

public class DonBanHangList_trinh extends Phan_Trang implements IDonBanHangList_trinh {
	
	//db
	
	//khaibaothuoctinh
	
	private String maDonHang;
	
	private String trangThai;
	private String khachHang;
	private String message;
	private String mesage;
	private dbutils db;
	private ResultSet rsKhachHang;
	private ResultSet rsBanHang;
	private String userId;
	private String maKhachHang;
	
	public DonBanHangList_trinh(String maDonHang, String trangThai,
			String khachHang, String message, dbutils db, ResultSet rsBanHang,
			String userId) {
		super();
		this.maDonHang = maDonHang;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.message = message;
		this.db = db;
		this.rsBanHang = rsBanHang;
		this.userId = userId;
	}
	public DonBanHangList_trinh() {
		super();
		this.maDonHang = "";
		this.trangThai = "";
		this.khachHang = "";
		this.message = "";
		this.db = new dbutils();
		
		this.userId = "";
	}
	
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}
	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public ResultSet getRsBanHang() {
		return rsBanHang;
	}
	public void setRsBanHang(ResultSet rsBanHang) {
		this.rsBanHang = rsBanHang;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void init(String chuoi){
		//tim kiem
		//hoac load du lieu len tat ca
		String query = "";
		if(chuoi.length() > 0)
		{
			query = chuoi;
		}
		else{
			try {
				query="SELECT BH.PK_SEQ AS MADONHANG, " +
				"BH.NGAYCHUNGTU AS NGAYCHUNGTU, " +
				"BH.KHACHHANG_FK AS MAKHACHHANG, " +
				"KH.TEN AS TENKHACHHANG, " +
				"ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, " +
				"BH.TONGTIEN, " +
				"BH.NGAYTAO, " +
				"BH.NGUOITAO AS MANGUOITAO, " +
				"NV.TEN AS TENNGUOITAO " +
				"FROM BANHANG BH " +
				"LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ " +
				"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ " +
				"ORDER BY MADONHANG ASC";
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		this.rsBanHang = db.get(query);
		this.LayKhachHang();
	}
	public void LayKhachHang()
	{
		this.rsKhachHang = db.get("select KHACHHANG.PK_SEQ as MAKHACHHANG, KHACHHANG.TEN as TENKHACHHANG from KHACHHANG");
	}
	
	public void closedb()
	{
		this.db.shutDown();
	}
}
