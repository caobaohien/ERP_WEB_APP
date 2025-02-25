package geso.erp.beans.donbanhang_tho.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_tho.IDonbanhangListTho;
import geso.erp.db.sql.dbutils;

public class DonbanhangListTho extends Phan_Trang implements IDonbanhangListTho {
	String userId;
	String maDH;
	String tenKH;
	String Trangthai;
	String ngayCT;
	String Ngaytao;
	String Nguoitao;
	String Tongtien;
	dbutils db;
	ResultSet Datalist,RsKH;
	public DonbanhangListTho()
	{
		 userId="";
		 maDH="";
		 tenKH="";
		 Trangthai=null;
		 ngayCT="";
		 Ngaytao="";
		 Nguoitao="";
		 Tongtien="";
		 db=new dbutils();
	}
	@Override
	public void setuserId(String userid) {
		// TODO Auto-generated method stub
		this.userId=userid;
	}
	@Override
	public String getuserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public void setmaDH(String madh) {
		// TODO Auto-generated method stub
		this.maDH=madh;
	}
	@Override
	public String getmaDH() {
		// TODO Auto-generated method stub
		return this.maDH;
	}
	@Override
	public void settenKH(String tenkh) {
		// TODO Auto-generated method stub
		this.tenKH=tenkh;
	}
	@Override
	public String gettenKH() {
		// TODO Auto-generated method stub
		return this.tenKH;
	}
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=trangthai;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}
	@Override
	public void setngayCT(String ngayct) {
		// TODO Auto-generated method stub
		this.ngayCT=ngayct;
	}
	@Override
	public String getngayCT() {
		// TODO Auto-generated method stub
		return this.ngayCT;
	}
	@Override
	public void setNguoitao(String nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao=nguoitao;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}
	@Override
	public void setNgaytao(String ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=ngaytao;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	@Override
	public void setTongtien(String tongtien) {
		// TODO Auto-generated method stub
		this.Tongtien=tongtien;
	}
	@Override
	public String getTongtien() {
		// TODO Auto-generated method stub
		return this.Tongtien;
	}
	@Override
	public void setDatalist(ResultSet datalist) {
		// TODO Auto-generated method stub
		this.Datalist=datalist;
	}
	@Override
	public ResultSet getDatalist() {
		// TODO Auto-generated method stub
		return this.Datalist;
	}
	@Override
	public void setRsKH(ResultSet rskh) {
		// TODO Auto-generated method stub
		this.RsKH=rskh;
	}
	@Override
	public ResultSet getRsKH() {
		// TODO Auto-generated method stub
		return this.RsKH;
	}
	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="SELECT BH.PK_SEQ,BH.NGAYCHUNGTU,KH.TEN,BH.TRANGTHAI,BH.TONGTIEN,BH.NGAYTAO,NV.TEN as NGUOITAO from BANHANG BH,NHANVIEN NV,KHACHHANG KH WHERE kh.PK_SEQ=bh.KHACHHANG_FK AND NV.PK_SEQ=bh.NGUOITAO";
		if(search.length()>0)
			query=search;
		this.Datalist=db.get(query);
		CreateRs();
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query="select PK_SEQ,MA,TEN from KHACHHANG";
		this.RsKH=db.get(query);
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}


}