package geso.erp.beans.THONTINHOCSINH.imp;

import java.sql.ResultSet;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.THONTINHOCSINH.IHocSinhList;


import geso.erp.db.sql.dbutils;

public class HocSinhList extends Phan_Trang implements IHocSinhList{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String hoten,ngaysinh,tdhocvan,knhocvan,quocgiahoc,pk_seq,tenquocgia;
	String userId,Id,msg,trangthai;
	ResultSet rslisths; // resultset chua thong tin don hang
	ResultSet rslistqg;
	dbutils db=new dbutils();
	public HocSinhList()
	{
		hoten="";
		ngaysinh="";
		tdhocvan="";
		knhocvan="";
		quocgiahoc="";
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
		 query=	 " select hs.PK_SEQ,hs.TEN,hs.NGAYSINH,hs.TRINHDOHOCVAN,hs.KHANANGHOCVAN,"
		 		+ "qg.TEN as tenquocgia from HOSO hs "
		 		+ "left join QUOCGIA qg on qg.PK_SEQ=hs.QUOCGIA_FK ";
		 
		}
		 this.rslisths=db.get(query); 
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query;
		query="select PK_SEQ,TEN from QUOCGIA";
		this.rslistqg=db.get(query);
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
	public void setHoten(String hoten) {
		// TODO Auto-generated method stub
		this.hoten=hoten;
	}
	@Override
	public String getHoten() {
		// TODO Auto-generated method stub
		return this.hoten;
	}
	@Override
	public void setNgaysinh(String ngaysinh) {
		// TODO Auto-generated method stub
		this.ngaysinh=ngaysinh;
	}
	@Override
	public String getNgaysinh() {
		// TODO Auto-generated method stub
		return this.ngaysinh;
	}
	@Override
	public void setTrinhdohocvan(String tdhocvan) {
		// TODO Auto-generated method stub
		this.tdhocvan=tdhocvan;
	}
	@Override
	public String getTrinhdohocvan() {
		// TODO Auto-generated method stub
		return this.tdhocvan;
	}
	@Override
	public void setKhananghocvan(String knhocvan) {
		// TODO Auto-generated method stub
		this.knhocvan=knhocvan;
	}
	@Override
	public String getKhananghocvan() {
		// TODO Auto-generated method stub
		return this.knhocvan;
	}
	
	@Override
	public void setQuocgiahoc(String qghoc) {
		// TODO Auto-generated method stub
		this.quocgiahoc=qghoc;
	}
	@Override
	public String getQuocgiahoc() {
		// TODO Auto-generated method stub
		return this.quocgiahoc;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rslisths;
	}
	@Override
	public void setDataList(ResultSet rslisths) {
		// TODO Auto-generated method stub
		this.rslisths=rslisths;
	}
	@Override
	public ResultSet getDataLisQuocgiat() {
		// TODO Auto-generated method stub
		return this.rslistqg;
	}
	@Override
	public void setDataListQuocgia(ResultSet rslistqg) {
		// TODO Auto-generated method stub
		this.rslistqg=rslistqg;
	}
	@Override
	public String getTenquocgia() {
		// TODO Auto-generated method stub
		return this.tenquocgia;
	}
	@Override
	public void settenquocgia(String tenqg) {
		// TODO Auto-generated method stub
		this.tenquocgia=tenqg;
	}
	
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}
	@Override
	public boolean delete_hs() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String query="";
				query="update THONTINHOCSINH set TRANGTHAI=1 where PK_SEQ="+this.Id;
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
		}catch(Exception err){
			this.msg="Lỗi :"+err.getMessage();
			db.update("rollback");
			err.printStackTrace();
			return false;
		}
		return true;
	}
}
