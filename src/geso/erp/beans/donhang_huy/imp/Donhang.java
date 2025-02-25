package geso.erp.beans.donhang_huy.imp;

import java.sql.Date;
import java.sql.ResultSet;


import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donhang_huy.IDonHang;
import geso.erp.db.sql.dbutils;

public class Donhang extends Phan_Trang implements IDonHang {
	String madh,tenkh,trangthai,Id,UserId, msg,nguoitao,nguoisua;
	int makh;
	float tongtien;
	String ngay;
	ResultSet rs;
	ResultSet rskh;
	dbutils db=new dbutils();
	public Donhang(){
		madh="";
		tenkh=""; 
		trangthai="";
		Id="";
		this.UserId="";
		this.msg="";
		this.tongtien=0;
		this.ngay="";
		this.nguoisua="";
		this.nguoitao="";
		this.makh=0;
	}
	
	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public void setAction(String action) {
		// TODO Auto-generated method stub
		
	}

	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public String getNguoisua() {
		return nguoisua;
	}

	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}

	public float getTongtien() {
		return tongtien;
	}

	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItems(int items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getItems() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=userId;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return Id;
	}

	public ResultSet getRskh() {
		return rskh;
	}
	public void setRskh(ResultSet rskh) {
		this.rskh = rskh;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.Id=Id;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return madh;
	}

	@Override
	public void setMa(String Ma) {
		// TODO Auto-generated method stub
		this.madh=Ma;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return tenkh;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.tenkh=ten;
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

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return rs;
	}

	@Override
	public void setDataList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		this.rs=dmhlist;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query;
		 String querykh;
		if(search.length()>0)
		{
			query=search;
		}
		else
		{
		 query=" SELECT ISNULL(bh.TRANGTHAI,'') AS TRANGTHAI,dhsp.DONHANG_FK,bh.NGAYCHUNGTU,kh.TEN,bh.TONGTIEN,bh.NGAYTAO,bh.NGUOITAO FROM BANHANG bh LEFT "+" "
		 		+ "JOIN KHACHHANG kh ON bh.KHACHHANG_FK=kh.PK_SEQ "
		 		+ "LEFT"+" JOIN DONHANG_SANPHAM dhsp ON dhsp.DONHANG_FK=bh.PK_SEQ";
		  
		}
		 this.rs=db.get(query); 
		 
		 querykh="select PK_SEQ,TEN from KHACHHANG";
		 this.rskh=db.get(querykh);
		 
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
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
