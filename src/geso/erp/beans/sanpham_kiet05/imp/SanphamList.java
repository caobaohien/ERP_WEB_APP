package geso.erp.beans.sanpham_kiet05.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_kiet05.ISanphamList;
import geso.erp.db.sql.dbutils;

public class SanphamList extends Phan_Trang implements ISanphamList {

	String Ma, Ten, Trangthai, Donvitinh, UserId, Msg;
	ResultSet rsData, rsDonvi;
	dbutils db;
	
	public SanphamList() {
		Ma = "";
		Ten = "";
		Trangthai = "";
		Donvitinh = "";
		UserId = "";
		Msg = "";
		db = new dbutils();
	}
	
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.Ma= ma;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten= ten;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai= trangthai;
	}

	@Override
	public ResultSet getrsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	@Override
	public void setRsdata(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData= rsData;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId= userId;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg= msg;
	}

	@Override
	public String getDonvitinh() {
		// TODO Auto-generated method stub
		return this.Donvitinh;
	}

	@Override
	public void setDonvitinh(String donvitinh) {
		// TODO Auto-generated method stub
		this.Donvitinh= donvitinh;
	}

	@Override
	public ResultSet getRsdonvi() {
		// TODO Auto-generated method stub
		return this.rsDonvi;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String	query="SELECT SP.MA,SP.TEN,SP.TRANGTHAI,SP.PK_SEQ,DV.MA AS DONVI FROM SANPHAM SP "+
		" LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK";
		
		if (search.length() > 0) {
			query = search;
		}
		this.rsData = db.get(query);
		createRs();
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		String query = "select pk_seq, ma from donvi";
		this.rsDonvi = db.get(query);
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}

}
