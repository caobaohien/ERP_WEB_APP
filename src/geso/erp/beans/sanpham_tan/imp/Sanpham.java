package geso.erp.beans.sanpham_tan.imp;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.beans.sanpham_tan.ISanpham;
import geso.erp.db.sql.dbutils;


public class Sanpham extends Phan_Trang implements ISanpham { 
	String Ma,Ten,Trangthai,Id,UserId, Donvitinh, msg;
	ResultSet rs;
	dbutils db=new dbutils();
	public Sanpham(){
		Ma="";
		Ten=""; 
		Trangthai="";
		Id="";
		this.UserId="";
		this.msg="";
		
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=  userId;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=trangthai;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String Id) {
		this.Id = Id;
		
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}
	@Override
	public void setMa(String Ma) {
		// TODO Auto-generated method stub
		this.Ma= Ma;
	}
	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}
	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten = ten;
	}
	@Override
	public String getDonvitinh() {
		// TODO Auto-generated method stub
		return this.Donvitinh;
	}
	@Override
	public void setDonvitinh(String donvitinh) {
		// TODO Auto-generated method stub
		this.Donvitinh = donvitinh;
	}
	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
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
	public void setDataList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		this.rs = dmhlist;
	}
	@Override
	public void init(String search) {
		String  query="";
		if(search.length() >0){
			query= search;
		}else{
		// TODO Auto-generated method stub
		   query=" SELECT ISNULL(SP.TRANGTHAI,'') AS TRANGTHAI ,SP.PK_SEQ,SP.MA,SP.TEN,SP.NGAYTAO ,DV.MA AS DONVI FROM SANPHAM SP LEFT "+
						" JOIN NHANVIEN NV ON NV.PK_SEQ =SP.NGUOITAO  "+
						" LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK ";
		}
		this.rs=db.get(query);
		
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
	
}
