package geso.erp.beans.donhang_huy.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donhang_huy.IDonHangNew;
import geso.erp.db.sql.dbutils;

public class DonHangNew extends Phan_Trang implements IDonHangNew {
	String madh,ngaychungtu,makh,tenkh,trangthai;
	String userId,Id,msg;
	double tongtien;
	ResultSet rsdh; // resultset chua thong tin don hang
	ResultSet rskh;
	dbutils db=new dbutils();
	public DonHangNew()
	{
		Id="";
		madh="";
		ngaychungtu="";
		makh="";
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
		this.ngaychungtu=ngaychungtu;
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
	public void init() {
		// TODO Auto-generated method stub
		try{
			String query;
			query="select PK_SEQ,NGAYCHUNGTU,TONGTIENTRUOCVAT as tongtien,KHACHHANG_FK from DONHANG where PK_SEQ="+Id;
			ResultSet rs =this.db.get(query);
			if(rs!=null)
			{
					if(rs.next())
					{
						this.makh=rs.getString("KHACHHANG_FK");
						this.ngaychungtu=rs.getString("NGAYCHUNGTU");
						this.tongtien=rs.getDouble("tongtien");
					}
			}
		
			 
			}
			catch(Exception e)
			{}
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query;
		query="select PK_SEQ,TEN from KHACHHANG ";
		 this.rskh=db.get(query); 
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
	public boolean save() {
		// TODO Auto-generated method stub
		try{
				String query;
				query="INSERT INTO DONHANG(NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO,NGAYTAO,TONGTIENTRUOCVAT)"
						+ "values('"+this.ngaychungtu+"','0',N'"+this.getMaKH()+"',"
								+ ""+this.userId+",'"+this.getNgaychungtu()+"','"+this.getTongtien()+"')";
				if(!db.update(query))
				{
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			catch(Exception e)
			{}
		return false;
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
}
