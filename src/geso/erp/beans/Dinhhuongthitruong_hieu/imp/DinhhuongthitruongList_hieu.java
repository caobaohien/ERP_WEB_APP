package geso.erp.beans.Dinhhuongthitruong_hieu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongList_hieu;
import geso.erp.db.sql.dbutils;

public class DinhhuongthitruongList_hieu extends Phan_Trang implements IDinhhuongthitruongList_hieu {
	String Id, Trangthai,Trinhtrangthitruong,Kehoachhanhdong,Ngaytao,Nguoitao,Nguoisua,Ngaysua,UserId, Msg;
	int Thang,Nam,Thangnhandinh,Quynhandinh;
	ResultSet rs;
	ResultSet rsck;
	dbutils db = new dbutils();
	 public DinhhuongthitruongList_hieu() {
		// TODO Auto-generated constructor stub
			// TODO Auto-generated constructor stub

		Id = "";
		Trangthai = "";
		Trinhtrangthitruong = "";
		Kehoachhanhdong="";
		Thang=0;
		Nam=0;
		Thangnhandinh=0;
		Quynhandinh=0;
		Ngaytao = "";
		Nguoitao = "";
		Ngaysua="";
		Nguoisua="";
		this.UserId = "";
		this.Msg = "";
	}
	public DinhhuongthitruongList_hieu(String id) {
		Id = id;
		Trangthai = "";
		Trinhtrangthitruong = "";
		Kehoachhanhdong="";
		Thang=0;
		Nam=0;
		Thangnhandinh=0;
		Quynhandinh=0;
		Ngaytao = "";
		Nguoitao = "";
		Ngaysua="";
		Nguoisua="";
		this.UserId = "";
		this.Msg = "";
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}
	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.UserId=_userId;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.Id=_id;
	}
	@Override
	public int getThang() {
		// TODO Auto-generated method stub
		return this.Thang;
	}
	@Override
	public void setThang(int _thang) {
		// TODO Auto-generated method stub
		this.Thang=_thang;
	}
	@Override
	public int getNam() {
		// TODO Auto-generated method stub
		return this.Nam;
	}
	@Override
	public void setNam(int _nam) {
		// TODO Auto-generated method stub
		this.Nam=_nam;
	}
	@Override
	public String getTrinhtrangthitruong() {
		// TODO Auto-generated method stub
		return this.Trinhtrangthitruong;
	}
	@Override
	public void setTrinhtrangthitruong(String _trinhtrangthitruong) {
		// TODO Auto-generated method stub
		this.Trinhtrangthitruong=_trinhtrangthitruong;
	}
	@Override
	public int getThangnhandinh() {
		// TODO Auto-generated method stub
		return this.Thangnhandinh;
	}
	@Override
	public void setThangnhandinh(int _thangnhandinh) {
		// TODO Auto-generated method stub
		this.Thangnhandinh=_thangnhandinh;
	}
	@Override
	public int getQuynhandinh() {
		// TODO Auto-generated method stub
		return this.Quynhandinh;
	}
	@Override
	public void setQuynhandinh(int _quynhandinh) {
		// TODO Auto-generated method stub
		this.Quynhandinh=_quynhandinh;
	}
	@Override
	public String getKehoachhanhdong() {
		// TODO Auto-generated method stub
		return this.Kehoachhanhdong;
	}
	@Override
	public void setKehoachhanhdong(String _kehoachhanhdong) {
		// TODO Auto-generated method stub
		this.Kehoachhanhdong=_kehoachhanhdong;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}
	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=_trangthai;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.Ngaytao=_nguoitao;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=_ngaytao;
	}
	@Override
	public String getNguoisua() {
		// TODO Auto-generated method stub
		return this.Nguoisua;
	}
	@Override
	public void setNguoisua(String _nguoisua) {
		// TODO Auto-generated method stub
		this.Ngaysua=_nguoisua;
	}
	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.Ngaysua;
	}
	@Override
	public void setNgaysua(String _ngaysua) {
		// TODO Auto-generated method stub
		this.Ngaysua=_ngaysua;
	}
	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.Msg=_msg;
	}
	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rs;
	}
	@Override
	public void setDataList(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rs=_dmhlist;
	}
	@Override
	public ResultSet getRsck() {
		// TODO Auto-generated method stub
		return this.rsck;
	}
	@Override
	public void setRsck(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rsck=_dmhlist;
	}
	
	
	@Override
	public void init(String _search) {
		// TODO Auto-generated method stub
		String query = "";
		if (_search.length() > 0) {
			query = _search;
		} else {
			// TODO Auto-generated method stub
			query =" select khdh.pk_seq as ma,thang,nam,TINHTRANGTHITRUONG,THANG_NHANDINH,QUY_NHANDINH,KEHOACHHANHDONG,KHDH.NGAYTAO,nv.TEN as nguoitao,khdh.TRANGTHAI as trangthai "+
				" from KEHOACHDINHHUONG khdh left join NHANVIEN nv on khdh.NGUOITAO=nv.PK_SEQ ";
		
		}
		this.rs = db.get(query);
		
		
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try {
			 
			db.getConnection().setAutoCommit(false);

			
			String query = "UPDATE KEHOACHDINHHUONG SET TRANGTHAI='2' WHERE PK_SEQ=" + this.Id;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.Msg = "Không thể cập nhật dòng lệnh: " + query;
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

			
			String query = "UPDATE KEHOACHDINHHUONG SET TRANGTHAI='1' WHERE PK_SEQ=" + this.Id;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.Msg = "Không thể cập nhật dòng lệnh: " + query;
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
