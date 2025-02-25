package geso.erp.beans.sanpham_trinh.imp;

import java.sql.ResultSet;

import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_trinh.ISanPhamList_Trinh;
import geso.erp.db.sql.dbutils;

public class SanPhamList_Trinh extends Phan_Trang implements ISanPhamList_Trinh {
	private String maSnaPham;
	private String tenSanPham;
	private String maDonVi;
	private String tenDonVi;
	
	private dbutils db;
	private ResultSet rsSanPham;
	private ResultSet rsDonVi;
	
	private String message;
	private String userId;
	
	private String trangThai;
	
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getMaSnaPham() {
		return maSnaPham;
	}
	public void setMaSnaPham(String maSnaPham) {
		this.maSnaPham = maSnaPham;
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
	public String getTenDonVi() {
		return tenDonVi;
	}
	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public ResultSet getRsSanPham() {
		return rsSanPham;
	}
	public void setRsSanPham(ResultSet rsSanPham) {
		this.rsSanPham = rsSanPham;
	}
	public ResultSet getRsDonVi() {
		return rsDonVi;
	}
	public void setRsDonVi(ResultSet rsDonVi) {
		this.rsDonVi = rsDonVi;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SanPhamList_Trinh(String maSnaPham, String tenSanPham,
			String maDonVi, String tenDonVi, dbutils db, ResultSet rsSanPham,
			ResultSet rsDonVi, String message, String userId) {
		super();
		this.maSnaPham = maSnaPham;
		this.tenSanPham = tenSanPham;
		this.maDonVi = maDonVi;
		this.tenDonVi = tenDonVi;
		this.db = db;
		this.rsSanPham = rsSanPham;
		this.rsDonVi = rsDonVi;
		this.message = message;
		this.userId = userId;
	}
	
	public SanPhamList_Trinh() {
		super();
		this.maSnaPham = "";
		this.tenSanPham = "";
		this.maDonVi = "";
		this.tenDonVi = "";
		this.db = new dbutils();
		
		this.message = "";
		this.userId = "";
	}
	
	public void init(String chuoi){
		String query = "";
		if(chuoi.length() > 0){
			query = chuoi;
		}
		else{
			try {
				query = "SELECT SANPHAM.PK_SEQ AS MASANPHAM, SANPHAM.MA AS MA, SANPHAM.TEN AS TENSANPHAM, " +
						"DONVI.TEN AS TENDONVI, ISNULL(SANPHAM.TRANGTHAI,'1') AS TRANGTHAI, SANPHAM.SOLUONG, " +
						"NHANVIEN.TEN as TENNGUOITAO, SANPHAM.NGAYTAO as NGAYTAO, SANPHAM.GIABAN " +
						"FROM SANPHAM LEFT JOIN DONVI ON SANPHAM.DONVI_FK = DONVI.PK_SEQ " +
						"LEFT JOIN NHANVIEN ON SANPHAM.NGUOITAO = NHANVIEN.PK_SEQ";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		this.rsSanPham = db.get(query);
		this.layDonvi();
	}
	//hàm lấy đơn vị
	public void layDonvi(){
		this.rsDonVi = db.get("SELECT DONVI.PK_SEQ AS MADONVI , DONVI.TEN AS TENDONVI FROM DONVI");
	}
	public void closedb(){
		this.db.shutDown();
	}
}
