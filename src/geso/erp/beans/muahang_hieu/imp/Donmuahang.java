package geso.erp.beans.muahang_hieu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.muahang_hieu.IMuahang;
import geso.erp.db.sql.dbutils;

public class Donmuahang extends Phan_Trang implements IMuahang {
	String Id, Ma, Trangthai, Nhacungcap, Ngay,  Ngaytao, Nguoitao,
			UserId, msg;
	ResultSet rs;
	ResultSet rskh;
	dbutils db = new dbutils();
	 
	public Donmuahang() {
		Id = "";
		Ma = "";
		Trangthai = "";
		Nhacungcap = "";
		Ngay = "";
		 
		Ngaytao = "";
		Nguoitao = "";
		this.UserId = "";
		this.msg = "";
	}

	public Donmuahang(String ma) {
		Id = "";
		Ma = ma;
		Trangthai = "";
		Nhacungcap = "";
		Ngay = "";
	 
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
	public String getNhacungcap() {
		// TODO Auto-generated method stub
		return this.Nhacungcap;
	}

	@Override
	public void setNhacungcap(String _nhacungcap) {
		// TODO Auto-generated method stub
		this.Nhacungcap=_nhacungcap;
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
			query = " SELECT ISNULL(mh.TRANGTHAI,'') AS trangthai,mh.PK_SEQ as ma,mh.NGAYCHUNGTU as ngay,ncc.TEN as nhacungcap,mh.TONGTIEN as tongtien," +
			" mh.NGAYTAO as ngaytao,mh.NGUOITAO as nguoitao " +
			" FROM MUAHANG mh LEFT "
			+ "	JOIN NHACUNGCAP ncc ON mh.NHACUNGCAP_FK=ncc.PK_SEQ ";
			
		}
		this.rs = db.get(query);
		System.out.println(query);
		String querykh = "select PK_SEQ,TEN from NHACUNGCAP";
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

			String query = "UPDATE MUAHANG SET TRANGTHAI='2' WHERE PK_SEQ="
					+ this.Id;
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
		public boolean Chot_dh() {
			// TODO Auto-generated method stub
			try {

				db.getConnection().setAutoCommit(false);

				String query = "UPDATE MUAHANG SET TRANGTHAI='1' WHERE PK_SEQ="
						+ this.Id;
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
