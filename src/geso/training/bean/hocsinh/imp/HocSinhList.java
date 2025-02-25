package geso.training.bean.hocsinh.imp;

import geso.training.bean.hocsinh.IHocSinhList;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public class HocSinhList implements IHocSinhList{
	private String MaHocSinh;
	private String TenHocSinh;
	private String MaLop;
	private String UserId;
	private String UserName;
	private ResultSet RsLop;
	private ResultSet RsHocSinhList;
	dbutils db;
	public HocSinhList () {
	this.MaHocSinh="";
	this.TenHocSinh="";
	this.MaLop="";
	this.db = new dbutils();
	}
	public String getMaHocSinh() {
	return MaHocSinh;
	}
	public void setMaHocSinh(String maHocSinh) {
	MaHocSinh = maHocSinh;
	}
	public String getTenHocSinh() {
	return TenHocSinh;
	}
	public void setTenHocSinh(String tenHocSinh) {
	TenHocSinh = tenHocSinh;
	}
	public String getMaLop() {
	return MaLop;
	}
	public void setMaLop(String maLop) {
	MaLop = maLop;
	}
	public String getUserId() {
	return UserId;
	}
	public void setUserId(String userId) {
	UserId = userId;
	}
	public String getUserName() {
	return UserName;
	}
	public void setUserName(String userName) {
	UserName = userName;
	}
	public ResultSet getRsLop() {
	return RsLop;
	}
	public void setRsLop(ResultSet rsLop) {
	RsLop = rsLop;
	}
	public ResultSet getRsHocSinhList() {
	return RsHocSinhList;
	}
	public void setRsHocSinhList(ResultSet rsHocSinhList) {
	RsHocSinhList = rsHocSinhList;
	}
	public void creates()
	{
		String query="select PK_SEQ,TENLOP from LOP";
		this.RsLop=this.db.get(query);
	}
	public void init(){
		String query = "select isnull(a.MAHS,'hien')MAHS, a.TENHS, b.TENLOP from LOP b inner join HOCSINH a on b.PK_SEQ = a.LOP_FK ";
						//+"where b.PK_SEQ="+this.MaLop;
		if(this.MaLop.trim().length()>0){
			query+="and b.PK_SEQ="+this.MaLop;
		}
		//System.out.println("action get "+ query);
		this.RsHocSinhList = this.db.get(query);
	}
	public void DBClose()
	{
		try{
			if(this.RsLop!=null)this.RsLop.close();
			if(this.RsHocSinhList!=null)this.RsHocSinhList.close();
			if(this.db!=null)this.db.shutDown();
		}catch(Exception e){
			System.out.println("CSDL is null");
		}
	}
}
