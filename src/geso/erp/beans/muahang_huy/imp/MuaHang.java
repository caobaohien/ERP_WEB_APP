package geso.erp.beans.muahang_huy.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.muahang_huy.IMuaHang;
import geso.erp.db.sql.dbutils;

public class MuaHang extends Phan_Trang implements IMuaHang{
	String mancc,trangthai,Id,UserId, msg,nguoitao,nguoisua,ghichu,mamh;
	float tongtien;
	String ngay,tenncc;
	ResultSet rs;
	ResultSet rsncc;
	dbutils db=new dbutils();

	public MuaHang(){
		mancc="";
		this.tenncc="";
		trangthai="";
		Id="";
		this.UserId="";
		this.msg="";
		this.tongtien=0;
		this.ngay="";
		this.nguoisua="";
		this.nguoitao="";
		this.mamh= "";
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=userId;
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
	public String getMaNCC() {
		// TODO Auto-generated method stub
		return this.mancc;
	}

	@Override
	public void setMaNCC(String Mancc) {
		// TODO Auto-generated method stub
		this.mancc=Mancc;
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
	public void setDataList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rs=rs;
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
		 query=	 "  select MH.NGAYTAO, mh.PK_SEQ AS MUAHANG_FK,mh.NHACUNGCAP_FK,mh.TRANGTHAI "
		 		+ ",mh.TONGTIEN,mh.NGAYCHUNGTU, NCC.TEN AS TEN , "+ 
				 " ISNULL(NT.TEN,'') AS NGUOITAO  "+
				 "  from MUAHANG mh "+
				 " left join NHACUNGCAP NCC ON NCC.PK_SEQ= MH.NHACUNGCAP_FK "+ 
				 " LEFT JOIN NHANVIEN NT ON NT.PK_sEQ=MH.NGUOITAO ";
		 
		}
		 this.rs=db.get(query); 
		 querykh="select PK_SEQ,TEN from NHACUNGCAP";
		 this.rsncc=db.get(querykh);
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
	public ResultSet getDataListNCC() {
		// TODO Auto-generated method stub
		return this.rsncc;
	}

	@Override
	public void setDataListNCC(ResultSet rsncc) {
		// TODO Auto-generated method stub
		this.rsncc=rsncc;
	}

	@Override
	public String gettenncc() {
		// TODO Auto-generated method stub
		return this.tenncc;
	}

	@Override
	public void settenncc(String tenncc) {
		// TODO Auto-generated method stub
		this.tenncc=tenncc;
	}

	@Override
	public boolean delete_dhmua() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String query="";
				query="update MUAHANG set TRANGTHAI=2 where PK_SEQ="+this.Id;
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
	public boolean chot_dhmua() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String query="";
				query="update MUAHANG set TRANGTHAI=1 where PK_SEQ="+this.Id;
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
	public void setngay(String ngay) {
		// TODO Auto-generated method stub
		this.ngay=ngay;
	}

	@Override
	public String getngay() {
		// TODO Auto-generated method stub
		return this.ngay;
	}

	@Override
	public void settongtien(float tongtien) {
		// TODO Auto-generated method stub
		this.tongtien=tongtien;
	}

	@Override
	public float gettongtien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}
}
