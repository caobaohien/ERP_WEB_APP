package geso.erp.beans.donbanhang_tho.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_tho.IDonbanhangTho;
import geso.erp.db.sql.dbutils;

public class DonbanhangTho extends Phan_Trang implements IDonbanhangTho {
	String userId;
	String Id;
	String maDH;
	String tenKH;
	String Trangthai;
	String ngayCT;
	String Ngaytao;
	String Nguoitao;
	String Tongtien;
	String Mgs;
	dbutils db;
	ResultSet Datalist,RsKH;
	public DonbanhangTho()
	{
		 Id="";
		 userId="";
		 maDH="";
		 tenKH="";
		 Trangthai=null;
		 ngayCT="";
		 Ngaytao="";
		 Nguoitao="";
		 Tongtien="";
		 Mgs="";
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
		String query="SELECT * FROM BANHANG WHERE PK_SEQ="+this.Id;
		if(search.length()>0)
			query=search;
		ResultSet rs=db.get(query);
		try {
			while(rs.next())
			{	this.Tongtien=rs.getString("tongtien");
				this.ngayCT=rs.getString("ngaychungtu");
				this.tenKH=rs.getString("khachhang_fk");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateRs();
		
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query="SELECT PK_SEQ,TEN FROM KHACHHANG";
		this.RsKH=db.get(query);
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try
		{
			db.getConnection().setAutoCommit(false);
			String query="INSERT INTO BANHANG (NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO,NGAYTAO,TONGTIEN) VALUES('"+this.ngayCT+"',"+0+",'"+this.tenKH+"',"+this.userId+",'"+this.ngayCT+"','"+this.getTongtien()+"') ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.Mgs="Không thực hiện được câu lệnh"+query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			db.update("rollback");
			this.Mgs="Lỗi: "+ e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Mgs=msg;
	}
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Mgs;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.Id=id;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		try
		{
			db.getConnection().setAutoCommit(false);
			String query="UPDATE BANHANG SET TONGTIEN='"+this.Tongtien+"', NGAYCHUNGTU='"+this.ngayCT+"',KHACHHANG_FK='"+this.tenKH+"' WHERE PK_SEQ="+this.Id;
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.Mgs="Không thực hiện được câu lệnh"+query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			db.update("rollback");
			this.Mgs="Lỗi: "+ e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}


}