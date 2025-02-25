package geso.erp.beans.donbanhang_hieu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_hieu.IDonbanhang;
import geso.erp.db.sql.dbutils;

public class Donbanhang extends Phan_Trang implements IDonbanhang {
	String Id, Ma, Trangthai, Khachhang, Ngay, Ngaytao, Nguoitao,
			UserId, msg;
	double Tongtien,Thanhtien;
	ResultSet rs;
	ResultSet rskh;
	dbutils db = new dbutils();

	public Donbanhang() {
		Id = "";
		Ma = "";
		Trangthai = "";
		Khachhang = "";
		Ngay = "";
		Tongtien =0;
		Thanhtien=0;
		Ngaytao = "";
		Nguoitao = "";
		this.UserId = "";
		this.msg = "";
	}

	public Donbanhang(String ma) {
		Id = "";
		Ma =ma;
		Trangthai = "";
		Khachhang = "";
		Ngay = "";
		Tongtien =0;
		Thanhtien=0;
		Ngaytao = "";
		Nguoitao = "";
		this.UserId = "";
		this.msg = "";
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.UserId = _userId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}

	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.Id = _id;
	}

	@Override
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.Ngay;
	}

	@Override
	public void setNgaychungtu(String _ngaychungtu) {
		// TODO Auto-generated method stub
		this.Ngay = _ngaychungtu;
	}

	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg = _msg;
	}

	@Override
	public double getThanhtien() {
		// TODO Auto-generated method stub
		return this.Thanhtien;
	}

	@Override
	public void setThanhtien(double _thanhtien) {
		// TODO Auto-generated method stub
		this.Thanhtien=_thanhtien;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai = _trangthai;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String _ma) {
		// TODO Auto-generated method stub
		this.Ma = _ma;
	}

	@Override
	public String getKhachhang() {
		// TODO Auto-generated method stub
		return this.Khachhang;
	}

	@Override
	public void setKhachhang(String _khachhang) {
		// TODO Auto-generated method stub
		this.Khachhang = _khachhang;
	}

	@Override
	public double getTongtien() {
		// TODO Auto-generated method stub
		return this.Tongtien;
	}

	@Override
	public void setTongtien(double _tongtien) {
		// TODO Auto-generated method stub
		this.Tongtien = _tongtien;

	}

	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}

	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao = _nguoitao;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}

	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao = _ngaytao;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rs;
	}

	@Override
	public void setDataList(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rs = _dmhlist;
	}

	@Override
	public void init(String _search) {
		// TODO Auto-generated method stub
		String query = "";
		if (_search.length() > 0) {
			query = _search;
		} else {
			// TODO Auto-generated method stub
			query = "SELECT ISNULL(bh.TRANGTHAI,'') AS trangthai,bh.PK_SEQ as ma ,bh.NGAYCHUNGTU as ngay,"
					+ "kh.TEN as khachhang,"
					+ " bh.TONGTIEN as tongtien,bh.NGAYTAO as ngaytao,"
					+ "bh.NGUOITAO as nguoitao FROM BANHANG bh LEFT "
					+ " "
					+ "JOIN KHACHHANG kh ON bh.KHACHHANG_FK=kh.PK_SEQ ";
					

		}
		this.rs = db.get(query);
		String querykh = "select PK_SEQ,TEN from KHACHHANG";
		this.rskh = db.get(querykh);
	}

	@Override
	public ResultSet getRskh() {
		// TODO Auto-generated method stub
		return this.rskh;
	}

	@Override
	public void setRskh(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rskh = _dmhlist;
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	

	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try {
		 
			db.getConnection().setAutoCommit(false);

			
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ=" + this.Id;
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
	public boolean Chot_dh() {
		// TODO Auto-generated method stub
		try {
			 
			db.getConnection().setAutoCommit(false);

			
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ=" + this.Id;
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
