package geso.erp.beans.donbanhang_trang.imp; //chiu trach nhiem cho trang ban hang tong

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHangList_Trang;
import geso.erp.db.sql.dbutils;

public class DonBanHangList_Trang extends Phan_Trang implements IDonBanHangList_Trang {
	//Tiêu chí tìm kiếm
	private String maDonHang;
	private String khachHang;
	private String trangThai;
	
	private dbutils db;
	private String msq; 				//Thông báo lỗi
	private ResultSet rsDataList; 		//Load thông tin từ DB để hiển thị lên web
	private ResultSet rsKhachHang;		//Load thông tin từ DB bỏ vào combo box
	private String userID;				//Load vết truy cặp
	
	public DonBanHangList_Trang(String maDonHang, String khachHang,
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

	public DonBanHangList_Trang() {
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
	}

	@Override
	public void init(String query) {
		// TODO Auto-generated method stub
		
		//Truy vấn DB để lấy dữ liệu
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
		
		//Lấy danh sách khách hàng bỏ vào combo box
		getDataKhachHang();
	}
	
	public void getDataKhachHang(){
		this.rsKhachHang = this.db.get("SELECT PK_SEQ, TEN FROM KHACHHANG");
		
	}
	@Override
	//Ngắt kết nối
	public void closedb() {
		this.db.shutDown();
		
	}
}