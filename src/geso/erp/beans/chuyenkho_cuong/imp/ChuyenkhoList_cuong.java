package geso.erp.beans.chuyenkho_cuong.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoList_cuong;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoList_cuong extends Phan_Trang implements IChuyenkhoList_cuong  {
	private String ma;
	private String khochuyen;
	private String khonhan;
	private String trangthai;
	private String ngaytao;
	private String nguoitao;
	private String userId;
	private String msg;
	private ResultSet rskhochuyen;
	private ResultSet rskhonhan;
	private ResultSet rsData;
	dbutils db;
	public ChuyenkhoList_cuong(){
		ma="";
		khochuyen="";
		khonhan="";
		trangthai="";
		ngaytao="";
		nguoitao="";
		userId="";
		msg="";
		this.db = new dbutils();
		
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma=ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setKhoChuyen(String khochuyen) {
		// TODO Auto-generated method stub
		this.khochuyen=khochuyen;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.khochuyen;
	}

	@Override
	public void setKhoNhan(String khonhan) {
		// TODO Auto-generated method stub
		this.khonhan=khonhan;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.khonhan;
	}

	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	@Override
	public void setRsData(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData=rsData;
		
	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId=userId;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="select  ck.PK_SEQ, LYDO, nv.TEN as NGUOITAO ,NGAYTAO,nv.TEN AS NGUOISUA, NGAYSUA from CHUYENKHO ck, NHANVIEN nv where nv.PK_SEQ=ck.NGUOITAO and  nv.PK_SEQ=ck.NGUOISUA";
		if(search.length()>0)
			query= search;
		this.rsData=db.get(query);
		CreateRsKhoChuyen();	
		CreateRsKhoNhan();
	}

	@Override
	public void CreateRsKhoNhan() {
		// TODO Auto-generated method stub
		String query="select pk_seq, TENKHO from kho";
			this.rskhonhan=db.get(query);
	}

	@Override
	public ResultSet getRsKhoNhan() {
		// TODO Auto-generated method stub
		return this.rskhonhan;
	}

	@Override
	public void CreateRsKhoChuyen() {
		// TODO Auto-generated method stub
		String query="select pk_seq, TENKHO from kho";
		this.rskhochuyen=db.get(query);
		
	}

	@Override
	public ResultSet getRsKhoChuyen() {
		// TODO Auto-generated method stub
		return this.rskhochuyen;
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}

}
