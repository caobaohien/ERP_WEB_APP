package geso.erp.beans.THONTINHOCSINH.imp;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.util.Phan_Trang;

import geso.erp.beans.THONTINHOCSINH.IHoSoTuVanUpdate;
import geso.erp.db.sql.dbutils;

public class HoSoTuVanUpdate extends Phan_Trang implements IHoSoTuVanUpdate{
	String userId,Id,msg,diengiai,tiemnang,nhanxet,tenquocgia;
	String pk_seq,chinhanh,trangthai,mahoso,ngaytuvan,maquocgia,maqg;
	ResultSet rshstv; // resultset chua thong tin don hang
	ResultSet rslistqg;
	dbutils db=new dbutils();
	public HoSoTuVanUpdate() {
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
		ngaytuvan="";
		this.maquocgia="";
		tenquocgia="";
		maqg="";
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
	public void init( ) {
		// TODO Auto-generated method stub
		try
		{
		String query;
		
		 query=	 "select PK_SEQ,HOSO_FK,DIENGIAI,NHANXET,TIEMNANG,NGAYTAO,QUOCGIA_FK,TRANGTHAI"
		 		+ " from TUVAN where PK_SEQ="+this.Id+" ";
		 
		 ResultSet rs =this.db.get(query);
		 if(rs!=null)
		 {
			 while(rs.next())
			 {
				 this.ngaytuvan=rs.getString("NGAYTAO");
				 this.mahoso=rs.getString("HOSO_FK");
				 this.maquocgia=rs.getString("QUOCGIA_FK");
				 this.diengiai=rs.getString("DIENGIAI");
				 this.nhanxet=rs.getString("NHANXET");
				 this.tiemnang=rs.getString("TIEMNANG");
				 this.trangthai=rs.getString("TRANGTHAI");
			 }
		 }
		 rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
	
		String query;
		query=" select PK_SEQ,MA,TEN "
				+ " from QUOCGIA"
				+ " where MA <> 'VN'";
		this.rslistqg=db.get(query);
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
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try{
			Date today=new Date(System.currentTimeMillis());
			SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy/MM/dd");
			String s=timeFormat.format(today.getTime());
			String query;
			query=" insert into TUVAN(DIENGIAI,TIEMNANG,HOSO_FK,NHANXET,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA,"
					+ "QUOCGIA_FK,TRANGTHAI) "
					+ "values('"+this.getDiengiai()+"','"+this.getTiemnang()+"',"+this.mahoso+","
					+ "'"+this.getNhanxet()+"',"+this.userId+","+this.userId+",'"+this.getNgaytuvan()+"',"
					+ "'"+s+"',"+this.getQuocgia_fk()+",'0')";
			db.getConnection().setAutoCommit(false);
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			query="update HOSO set TRANGTHAI=1 where PK_SEQ="+this.getMahoso()+"";
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete_hs() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		
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
	@Override
	public void setNgaytuvan(String ngaytuvan) {
		// TODO Auto-generated method stub
		this.ngaytuvan=ngaytuvan;
	}
	@Override
	public String getNgaytuvan() {
		// TODO Auto-generated method stub
		return this.ngaytuvan;
	}
	@Override
	public boolean edit_tuvan() {
		// TODO Auto-generated method stub
		try{
			String query;
			query="update tuvan set NGAYTAO='"+this.getNgaytuvan()+"',NHANXET='"+this.getNhanxet()+"',"
					+ "DIENGIAI='"+this.getDiengiai()+"',TIEMNANG='"+this.getTiemnang()+"',"
					+ "QUOCGIA_FK="+this.getQuocgia_fk()+" "
					+ "where PK_SEQ="+this.Id+"";
			db.getConnection().setAutoCommit(false);
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public void setQuocgia_fk(String maquocgia) {
		// TODO Auto-generated method stub
		this.maquocgia=maquocgia;
	}
	@Override
	public String getQuocgia_fk() {
		// TODO Auto-generated method stub
		return this.maquocgia;
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
	public String getMaquocgia() {
		// TODO Auto-generated method stub
		return this.maqg;
	}
	@Override
	public void setMaquocgia(String maqg) {
		// TODO Auto-generated method stub
		this.maqg=maqg;
	}
	@Override
	public boolean savequocgia() {
		// TODO Auto-generated method stub
		try{
			String query;
			query="insert into QUOCGIA(MA,TEN) values('"+this.getMaquocgia()+"','"+this.getTenquocgia()+"')";
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
