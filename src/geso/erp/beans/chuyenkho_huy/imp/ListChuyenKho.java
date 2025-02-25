package geso.erp.beans.chuyenkho_huy.imp;

import java.sql.ResultSet;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_huy.IListChuyenKho;

import geso.erp.db.sql.dbutils;

public class ListChuyenKho extends Phan_Trang implements IListChuyenKho{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ngaychuyenkho,trangthai,nguoitao,lydo,tenkho,khonhan,khochuyen,pk_seq;
	String userId,Id,msg;
	ResultSet rschuyenkho; // resultset chua thong tin don hang
	ResultSet rslistkhochuyen;
	ResultSet rslistkhonhan;
	dbutils db=new dbutils();
	public ListChuyenKho()
	{
		ngaychuyenkho="";
		trangthai="";
		nguoitao="";
		lydo="";
		khonhan="";
		khochuyen="";
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
			query="select ck.PK_SEQ,ck.LYDO,ck.TRANGTHAI,nv.TEN,ck.NGAYTAO,nv.TEN,ck.NGAYSUA from CHUYENKHO ck "
					+ "left join NHANVIEN nv on ck.NGUOITAO=nv.PK_SEQ";
			 
		}
		this.rschuyenkho=db.get(query); 
		 
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String querykh;
		 querykh="select PK_SEQ,TENKHO from KHO";
		 this.rslistkhochuyen=db.get(querykh);
		 this.rslistkhonhan=db.get(querykh); 
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
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
	public String getTenkho() {
		// TODO Auto-generated method stub
		return this.tenkho;
	}
	@Override
	public void setTenkho(String tenkho) {
		// TODO Auto-generated method stub
		this.tenkho=tenkho;
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
		return this.rschuyenkho;
	}
	@Override
	public void setDataList(ResultSet rslistchuyenkho) {
		// TODO Auto-generated method stub
		this.rschuyenkho=rslistchuyenkho;
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setPK_SEQ(String pk_seq) {
		// TODO Auto-generated method stub
		this.pk_seq=pk_seq;
	}
	@Override
	public String getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.pk_seq;
	}
	@Override
	public ResultSet getDataListKhochuyen() {
		// TODO Auto-generated method stub
		return this.rslistkhochuyen;
	}
	@Override
	public void setDataListKhochuyen(ResultSet rslistkhochuyen) {
		// TODO Auto-generated method stub
		this.rslistkhochuyen=rslistkhochuyen;
	}
	@Override
	public ResultSet getDataListKhonhan() {
		// TODO Auto-generated method stub
		return this.rslistkhonhan;
	}
	@Override
	public void setDataListKhonhan(ResultSet rslistkhonhan) {
		// TODO Auto-generated method stub
		this.rslistkhonhan=rslistkhonhan;
	}
	@Override
	public String getKhochuyen() {
		// TODO Auto-generated method stub
		return this.khochuyen;
	}
	@Override
	public void setKhochuyen(String khochuyen) {
		// TODO Auto-generated method stub
		this.khochuyen=khochuyen;
	}
	@Override
	public String getKhonhan() {
		// TODO Auto-generated method stub
		return this.khonhan;
	}
	@Override
	public void setKhonhan(String khonhan) {
		// TODO Auto-generated method stub
		this.khonhan=khonhan;
	}
}
