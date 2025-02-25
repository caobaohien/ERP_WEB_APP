package geso.erp.beans.sanpham_thu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_thu.ISanPhamList_Thu;
import geso.erp.db.sql.dbutils;

public class SanPhamList_Thu extends Phan_Trang implements ISanPhamList_Thu {
	
	private String maSanPham;
	private String tenSanPham;
	private String maDoVi;
	private String tenDonVi;
	private String userId;
	private String trangThai;
	private dbutils db;
	private ResultSet rsSanPham;
	private ResultSet rsDonVi;
	private String msg;
	private String giaBan;
	private String soLương;
	
	
	
	public String getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}
	public String getSoLương() {
		return soLương;
	}
	public void setSoLương(String soLương) {
		this.soLương = soLương;
	}
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
	public String getMaDoVi() {
		return maDoVi;
	}
	public void setMaDoVi(String maDoVi) {
		this.maDoVi = maDoVi;
	}
	public String getTenDonVi() {
		return tenDonVi;
	}
	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public ResultSet getRsSanPham() {
		return rsSanPham;
	}
	public void setRsSanPham(ResultSet rsSanPham) {
		this.rsSanPham = rsSanPham;
	}
	public ResultSet getRsDonVi() {
		String  query="select PK_SEQ as MADONVI,TEN AS TENDONVI  from DONVI";
		this.rsDonVi=this.db.get(query);
		return rsDonVi;
	}
	public void setRsDonVi(ResultSet rsDonVi) {
		this.rsDonVi = rsDonVi;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	public SanPhamList_Thu(String maSanPham, String tenSanPham, String maDoVi,
			String tenDonVi, String userId, String trangThai, dbutils db,
			ResultSet rsSanPham, ResultSet rsDonVi, String giaBan,
			String soLương) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.maDoVi = maDoVi;
		this.tenDonVi = tenDonVi;
		this.userId = userId;
		this.trangThai = trangThai;
		this.db = db;
		this.rsSanPham = rsSanPham;
		this.rsDonVi = rsDonVi;
		this.giaBan = giaBan;
		this.soLương = soLương;
	}
	
	public SanPhamList_Thu() {
		super();
		this.maSanPham = "";
		this.tenSanPham = "";
		this.maDoVi = "";
		this.tenDonVi = "";
		this.userId = "";
		this.trangThai = "";
		this.db =new dbutils();
		
	}
	
	//ham init dung de khoi tao doi tuong 
	@Override
	public void init(String s)
	{
		String query="";
		if(s.trim().length()>0){
			query=s;
		}
		else {
			query=	"SELECT SP.PK_SEQ AS MASANPHAM,"+
					"SP.TEN AS TENSANPHAM, "+
					"DV.TEN AS TENDONVI, SP.TRANGTHAI AS TRANGTHAI, "+
					"SP.SOLUONG AS SOLUONG, SP.GIABAN AS GIABAN "+
					"from SANPHAM SP LEFT JOIN DONVI DV ON SP.DONVI_FK=DV.PK_SEQ ";
		}
		
	this.rsSanPham=this.db.get(query);	
	CreateDonVi();
	}
	
	@Override
	public void CreateDonVi(){
		String  query="select PK_SEQ as MADONVI,TEN AS TENDONVI  from DONVI";
		this.rsDonVi=this.db.get(query);
	}
	
	@Override
	public void DBclose()
	{
		this.db.shutDown();
	
		
	}
	@Override
	public boolean deleteSanPham(){
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE SANPHAM SET TRANGTHAI='0' WHERE PK_SEQ = " + this.maSanPham;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
			
		} catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
	
	
}
