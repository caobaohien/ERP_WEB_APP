package geso.training.bean.hocsinh.imp;

import geso.training.bean.hocsinh.IHocSinh;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import geso.erp.db.sql.dbutils;
public class HocSinh implements IHocSinh{
	private String MaHS;
	private String TenHS;
	private String MaLop;
	private String UserId;
	private String UserName;
	ResultSet RsLop, RsHocSinhList;
	dbutils db;
	public HocSinh(){
		this.MaHS = "";
		this.TenHS = "";
		this.MaLop = "";
		this.db=new dbutils();
	}
	public String getMaHS(){
		return MaHS;
	}
	public void setMaHS(String mahs){
		MaHS = mahs;	
	}
	public String getTenHS(){
		return TenHS;
	}
	public void setTenHS(String tenhs){
		TenHS = tenhs;
	}
	public String getMaLop(){
		return MaLop;
	}
	public void setMaLop(String malop){
		MaLop = malop;
	}
	public String getUserId(){
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
		String query = "select isnull(a.MAHS,'hien')MAHS, a.TENHS, b.TENLOP from LOP b inner join HOCSINH a on b.PK_SEQ = a.LOP_FK "
						//+"where b.PK_SEQ="+this.MaLop
						+"where a.MAHS='"+this.MaHS+"'";
		/*if(this.MaLop.trim().length()>0){
			query+="and b.PK_SEQ="+this.MaLop;
		}*/
		//System.out.println("action get "+ query);
		this.RsHocSinhList = this.db.get(query);
		try{
			while(RsHocSinhList.next()){
				this.MaHS=RsHocSinhList.getString("MAHS");
				this.TenHS=RsHocSinhList.getString("TENHS");
				this.MaLop=RsHocSinhList.getString("TENLOP");
				/*IHocSinh hs = new HocSinh();
				hs.setMaHS(RsHocSinhList.getString("MAHS"));
				hs.setTenHS(RsHocSinhList.getString("TENHS"));
				hs.setMaLop(RsHocSinhList.getString("TENLOP"));*/
			}
			RsHocSinhList.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public boolean Save(){
		String query="insert into HOCSINH(TENHS,LOP_FK, MAHS)values(N'"+this.TenHS +"','"+this.MaLop+"','"+this.MaHS+"')";
		return this.db.update(query);
	}
	public boolean Savesua(){
		String query="update HOCSINH set TENHS='"+this.TenHS+"', MAHS = '"+this.MaHS+"', LOP_FK = "+this.MaLop+""
						+" where MAHS = '"+this.MaHS+"'";
						//+" and LOP_FK = "+this.MaLop;
		return db.update(query);
	}
	public boolean Xoa(){
		String query="delete from HOCSINH where MAHS='"+this.MaHS+"'";
		return db.update(query);
	}
	public void DBClose(){
		try{
			if(this.RsLop!=null)this.RsLop.close();
			if(this.RsHocSinhList!=null)this.RsHocSinhList.close();
			if(this.db!=null)this.db.shutDown();
		}catch(Exception e){}
	}
}
