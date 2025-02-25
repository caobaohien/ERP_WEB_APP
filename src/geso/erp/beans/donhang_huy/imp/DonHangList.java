package geso.erp.beans.donhang_huy.imp;

import java.sql.ResultSet;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donhang_huy.IDonHangList;
import geso.erp.db.sql.dbutils;

public class DonHangList extends Phan_Trang implements IDonHangList{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String madh,ngaychungtu,trangthai,makh,nguoitao,ngaytao,tenkh;
	String userId,Id,msg;
	double tongtien;
	ResultSet rsdh; // resultset chua thong tin don hang
	ResultSet rskh;
	dbutils db=new dbutils();
	public DonHangList()
	{
		madh="";
		ngaychungtu="";
		trangthai="";
		makh="";
		nguoitao="";
		ngaytao="";
		tongtien=0;
	}
	@Override
	public void setTongtien(double tongtien) {
		// TODO Auto-generated method stub
		this.tongtien=tongtien;
	}
	@Override
	public double getTongtien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}
	@Override
	public void setNgaychungtu(String ngay) {
		// TODO Auto-generated method stub
		this.ngaychungtu=ngay;
	}
	@Override
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.ngaychungtu;
	}
	@Override
	public String getmaDH() {
		// TODO Auto-generated method stub
		return this.madh;
	}
	@Override
	public void setMaDH(String mamh) {
		// TODO Auto-generated method stub
		this.madh=mamh;
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId=userId;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.Id=Id;
	}
	@Override
	public String getMaKH() {
		// TODO Auto-generated method stub
		return this.makh;
	}
	@Override
	public void setMaKH(String Ma) {
		// TODO Auto-generated method stub
		this.makh=Ma;
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
	public ResultSet getDataListDH() {
		// TODO Auto-generated method stub
		return this.rsdh;
	}
	@Override
	public void setDataListDh(ResultSet rsdh) {
		// TODO Auto-generated method stub
		this.rsdh=rsdh;
	}
	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query;
		if(search.length()>0)
		{
			query=search;
		}
		else{
			query="select dh.PK_SEQ as madh,dh.NGAYCHUNGTU,kh.TEN,dh.TRANGTHAI,"
					+ " dh.TONGTIENTRUOCVAT as tongtien,dh.NGAYTAO,nv.TEN as nguoitao"
					+" from DONHANG dh left join KHACHHANG kh on dh.KHACHHANG_FK=kh.PK_SEQ "
					+ "left join NHANVIEN nv on dh.NGUOITAO=nv.PK_SEQ";
			 
		}
		this.rsdh=db.get(query); 
		 query="select PK_SEQ,TEN from KHACHHANG ";
		 this.rskh=db.get(query); 
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
	public ResultSet getDataListKH() {
		// TODO Auto-generated method stub
		return this.rskh;
	}
	@Override
	public void setDataListKH(ResultSet rskh) {
		// TODO Auto-generated method stub
		this.rskh=rskh;
	}
	@Override
	public String getTenKH() {
		// TODO Auto-generated method stub
		return this.tenkh;
	}
	@Override
	public void settenKH(String ten) {
		// TODO Auto-generated method stub
		this.tenkh=ten;
	}
	@Override
	public boolean chot() {
		// TODO Auto-generated method stub
		try{
			String query;
			db.getConnection().setAutoCommit(false);
			query="update DONHANG set TRANGTHAI=1 where PK_SEQ="+Id;
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{}
		return true;
	}
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		try{
			String query;
			db.getConnection().setAutoCommit(false);
			query="update DONHANG set TRANGTHAI=2 where PK_SEQ="+Id;
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{}
		return true;
	}
}
