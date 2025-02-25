package geso.training.bean.donhang.imp;
import geso.training.bean.donhang.IDonHangList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Exception;

import geso.erp.db.sql.dbutils;
public class DonHangList implements IDonHangList{
	String userId;
	String userTen;
	String tungay;
	String denngay;
	String trangthai;
	String sodonhang;
	String khachhang;
	String msg;
	String Madh;
	ResultSet dhRs;
	ResultSet khRs;
	dbutils db;
	public DonHangList(){
		this.tungay="";
		this.denngay="";
		this.trangthai="";
		this.sodonhang="";
		this.khachhang="";
		this.Madh="";
		this.msg="";
		this.db=new dbutils();
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userid){
		userId=userid;
	}
	public String getUserTen(){
		return userTen;
	}
	public void setUserTen(String userTen){
		this.userTen=userTen;
	}
	public String getTungay(){
		return tungay;
	}
	public void setTungay(String tungay){
		this.tungay=tungay;
	}
	public String getDenngay(){
		return denngay;
	}
	public void setDenngay(String denngay){
		this.denngay=denngay;
	}
	public String getTrangthai(){
		return trangthai;
	}
	public void setTrangthai(String trangthai){
		this.trangthai=trangthai;
	}
	public String getSodonhang(){
		return sodonhang;
	}
	public void setSodonhang(String sodonhang){
		this.sodonhang=sodonhang;
	}
	public String getKhachhang(){
		return khachhang;
	}
	public void setKhachhang(String khachhang){
		this.khachhang=khachhang;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg=msg;
	}
	public ResultSet getDhRs(){
		return dhRs;
	}
	public void setDhRs(ResultSet dhrs){
		this.dhRs=dhrs;
	}
	public ResultSet getKhRs(){
		return khRs;
	}
	public void setKhRs(ResultSet khrs){
		this.khRs=khrs;
	}
	public String getMadh(){
		return Madh;
	}
	public void setMadh(String Madh){
		this.Madh=Madh;
	}
	public void init(String search){
		String query="";
		if(search.length()>0){
			query=search;
		}else{
		//lay danh sach don hang
		query="select a.PK_SEQ,b.TEN,isnull(a.TONGGIATRI,0)TONGGIATRI,a.TRANGTHAI,a.NGAYNHAP,"
						+"a.NGAYTAO,c.TEN as NGUOITAO,a.NGAYSUA, d.ten as NGUOISUA from "
						+"DONHANG a inner join KHACHHANG b on a.KHACHHANG_FK=b.PK_SEQ "
						+"inner join NHANVIEN c on c.PK_SEQ=a.NGUOITAO "
						+"inner join NHANVIEN d on d.PK_SEQ=a.NGUOISUA";
			if(this.khachhang.trim().length()>0){
				query+=" where a.KHACHHANG_FK ="+this.khachhang;
			}
		}
		this.dhRs=this.db.get(query);
		String querykh="select pk_seq,ten from khachhang";
		this.khRs=this.db.get(querykh);
	}
	public void DBClose(){
		try{
			if(this.khRs!=null)this.khRs.close();
			if(this.dhRs!=null)this.dhRs.close();
			if(this.db!=null)this.db.shutDown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
