package geso.erp.beans.sanpham_nhom2.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_nhom2.ISanphamList;
import geso.erp.db.sql.dbutils;

public class SanphamList  extends Phan_Trang implements ISanphamList{

	String Ma;
	String Ten;
	String Donvitinh;
	String Trangthai;
	String Msg;
	String UserId;
	ResultSet rsdata;
	ResultSet rsdonvi;
	dbutils db;
	
	public SanphamList(){
		  Ma ="";
		  Ten="";
		  Donvitinh="";
		  Trangthai="";
		  Msg="";
		  UserId="";
		  db=new dbutils();
	}
	
	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.Ma=ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten=ten;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}

	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=trangthai;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setRsData(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsdata=rs;
	}

	@Override
	public ResultSet getRsdata() {
		// TODO Auto-generated method stub
		return this.rsdata;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=userId;
	}

	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg=msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String	query="SELECT SP.MA,SP.TEN,SP.TRANGTHAI,SP.PK_SEQ,DV.MA AS DONVI FROM SANPHAM SP "+
		" LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK";
		
		if(search.length()>0){
			query=search;
		}
		
		this.rsdata=db.get(query);
		CreateRs();
	} 

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query="select pk_seq,ma from donvi";
		this.rsdonvi=db.get(query);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		// dong tat ca cac resultset trong nay
		this.db.shutDown();
	}

	@Override
	public String getDonvitinh() {
		// TODO Auto-generated method stub
		return this.Donvitinh;
	}

	@Override
	public void setDonvitinh(String donvitinh) {
		// TODO Auto-generated method stub
		this.Donvitinh=donvitinh;
	}

	@Override
	public ResultSet getrsdonvi() {
		// TODO Auto-generated method stub
		return this.rsdonvi;
	}

}
