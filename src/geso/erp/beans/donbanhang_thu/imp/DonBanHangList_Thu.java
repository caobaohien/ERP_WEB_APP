package geso.erp.beans.donbanhang_thu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_thu.IDonBanHangList_Thu;
import geso.erp.db.sql.dbutils;

public class DonBanHangList_Thu extends Phan_Trang implements IDonBanHangList_Thu {
	private String maDonHang;
	private String trangThai;
	private String khachHang;
	private String msg;
	private dbutils db;
	private ResultSet rsBanHang;
	private ResultSet rsKhachHang;
	
	
	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}
	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}
	private String userId;
	
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	
	
	public DonBanHangList_Thu(String maDonHang, String trangThai,
			String khachHang, String msg, dbutils db, ResultSet rsBanHang,
			String userId) {
		super();
		this.maDonHang = maDonHang;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.msg = msg;
		this.db = db;
		this.rsBanHang = rsBanHang;
		this.userId = userId;
	}
	
	
	public DonBanHangList_Thu() {
		super();
		this.maDonHang = "";
		this.trangThai = "";
		this.khachHang = "";
		this.msg = "";
		this.db = new dbutils() ;
		this.userId = "";
	}
	
	@Override
	public void init(String string) {
		// TODO Auto-generated method stub
		String query="";
		if(string.trim().length()>0)
			query=string;
		else{
			query="select BH.PK_SEQ as MADONHANG, isnull(BH.NGAYCHUNGTU,'') as NGAYCHUNGTU,"+
			" KH.TEN as TEN,BH.TRANGTHAI as TRANGTHAI, "+
			" BH.TONGTIEN as TONGTIEN,BH.NGAYTAO as NGAYTAO, "+
			" BH.NGUOITAO as NGUOITAO  "+
			" from BANHANG BH left join  KHACHHANG KH on KH.PK_SEQ = BH.KHACHHANG_FK ";
		}
		
		/*thực thi*/
		this.rsBanHang=this.db.get(query);
		this.layKhachHang(); 
	}
	
	public void layKhachHang(){
		String  query="select MA AS MA,TEN AS TEN from KHACHHANG";
		this.rsKhachHang=this.db.get(query);
	}
	
	@Override
	public void DBclose()
	{
		this.db.shutDown();
	
		
	}
	
	@Override
	public boolean deleteDonHang() {
		// TODO Auto-generated method stub
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ = " + this.maDonHang;
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
	@Override
	public boolean chotDonHang() {
		// TODO Auto-generated method stub
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ = " + this.maDonHang;
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
