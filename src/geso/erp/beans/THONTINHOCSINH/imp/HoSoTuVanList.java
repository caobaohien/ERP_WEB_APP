package geso.erp.beans.THONTINHOCSINH.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.THONTINHOCSINH.IHoSoTuVanList;
import geso.erp.db.sql.dbutils;

public class HoSoTuVanList extends Phan_Trang implements IHoSoTuVanList {
	
	String userId,Id,msg,diengiai,tiemnang,nhanxet;
	String pk_seq,chinhanh,trangthai,mahoso;
	ResultSet rshstv; // resultset chua thong tin don hang
	ResultSet rslistqg;
	dbutils db=new dbutils();
	public HoSoTuVanList() {
		super();
		this.chinhanh="";
		this.diengiai="";
		this.tiemnang = "";
		this.nhanxet="";
		this.pk_seq="";
		this.chinhanh = "";
		this.userId = "";
		this.Id = "";
		this.msg = "";
		this.trangthai="";
		mahoso="";
		
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
	public void setDiengiai(String diengiai) {
		// TODO Auto-generated method stub
		this.diengiai=diengiai;
	}
	@Override
	public String getDiengiai() {
		// TODO Auto-generated method stub
		return this.diengiai;
	}
	@Override
	public String getTiemnang() {
		// TODO Auto-generated method stub
		return this.tiemnang;
	}
	@Override
	public void setTiemnang(String tiemnang) {
		// TODO Auto-generated method stub
		this.tiemnang=tiemnang;
	}
	@Override
	public void setNhanxet(String nhanxet) {
		// TODO Auto-generated method stub
		this.nhanxet=nhanxet;
	}
	@Override
	public String getNhanxet() {
		// TODO Auto-generated method stub
		return this.nhanxet;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rshstv;
	}
	@Override
	public void setDataList(ResultSet rslisths) {
		// TODO Auto-generated method stub
		this.rshstv=rslisths;
	}
	@Override
	public ResultSet getDataLisQuocgiat() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDataListQuocgia(ResultSet rslistqg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query;
		if(search.length()>0)
		{
			query=search;
		}
		else
		{
		 query=	 "select tv.PK_SEQ,tv.HOSO_FK,tv.NGAYTAO,nv.TEN,tv.TIEMNANG,tv.NHANXET,tv.TRANGTHAI "
		 		+ "from TUVAN tv left join NHANVIEN nv on tv.NGUOITAO=nv.PK_SEQ "
		 		+ "where tv.NGUOITAO="+this.userId+" ";
		 
		}
		 this.rshstv=db.get(query); 
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
	@Override
	public boolean delete_hs() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String query="";
				query="update TUVAN set TRANGTHAI=1 where PK_SEQ="+this.Id;
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
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}
	@Override
	public void setMahoso(String mahoso) {
		// TODO Auto-generated method stub
		this.mahoso=mahoso;
	}
	@Override
	public String getMahoso() {
		// TODO Auto-generated method stub
		return mahoso;
	}
}
