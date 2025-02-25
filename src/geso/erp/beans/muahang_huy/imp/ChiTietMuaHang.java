package geso.erp.beans.muahang_huy.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.muahang_huy.IChiTietMuaHang;
import geso.erp.db.sql.dbutils;

public class ChiTietMuaHang  extends Phan_Trang implements IChiTietMuaHang{
	String mamh,masp,ngaynhan,nguongoc,userid,id,msg;
	float slnhan;
	ResultSet rs;
	dbutils db=new dbutils();
	public  ChiTietMuaHang() {
		// TODO Auto-generated constructor stub
		mamh="";
		masp="";
		ngaynhan="";
		nguongoc="";
		slnhan=0;
	}
	@Override
	public void setnguongoc(String nguongoc) {
		// TODO Auto-generated method stub
		this.nguongoc=nguongoc;
		
	}
	@Override
	public String getnguongoc() {
		// TODO Auto-generated method stub
		return this.nguongoc;
	}
	@Override
	public void setsoluongnhan(float slnhan) {
		// TODO Auto-generated method stub
		this.slnhan=slnhan;
	}
	@Override
	public float getsoluongnhan() {
		// TODO Auto-generated method stub
		return this.slnhan;
	}
	@Override
	public void setngaynhan(String ngaynhan) {
		// TODO Auto-generated method stub
		this.ngaynhan=ngaynhan;
	}
	@Override
	public String getngaynhan() {
		// TODO Auto-generated method stub
		return this.ngaynhan;
	}
	@Override
	public String getmamh() {
		// TODO Auto-generated method stub
		return this.mamh;
	}
	@Override
	public void setmamh(String mamh) {
		// TODO Auto-generated method stub
		this.mamh=mamh;
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userid;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userid=userId;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.id=Id;
	}
	@Override
	public String getmasp() {
		// TODO Auto-generated method stub
		return this.masp;
	}
	@Override
	public void setmasp(String masp) {
		// TODO Auto-generated method stub
		this.masp=masp;
	}
	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}
	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}
 
}
