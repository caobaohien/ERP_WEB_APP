package geso.erp.beans.chuyenkho_huy.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_huy.IChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.ISanPham;
import geso.erp.beans.chuyenkho_huy.imp.SanPham;
import geso.erp.db.sql.dbutils;

public class ChuyenKhoUpdate extends Phan_Trang implements IChuyenKhoUpdate {
	String ngayghinhan,trangthai,khonhan,khochuyen,pk_seq,lydo;
	double soluong,booked;
	String userId,Id,msg;
	ResultSet rschuyenkho; // resultset chua thong tin don hang
	ResultSet rslistkhonhan;
	ResultSet rslistkhochuyen;
	List<ISanPham> listsp;
	dbutils db=new dbutils();
	public ChuyenKhoUpdate()
	{
		ngayghinhan="";
		pk_seq="";
		Id="";
		trangthai="";
		khonhan="";
		khochuyen="";
		lydo="";
		soluong=0;
		booked=0;
		this.listsp=new ArrayList<ISanPham>();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try
		{
			String query;
			query="select NGAYCHUYENKHO,LYDO,KHOCHUYEN,KHONHAN from CHUYENKHO where PK_SEQ="+Id;
			ResultSet rs =this.db.get(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					this.ngayghinhan=rs.getString("NGAYCHUYENKHO");
					this.lydo=rs.getString("LYDO");
					this.khonhan=rs.getString("KHONHAN");
					this.khochuyen=rs.getString("KHOCHUYEN");
				}
			}
			query="select sp.PK_SEQ,cksp.SOLUONG as soluongchuyen,sp.MA,sp.TEN as tensp,dv.TEN as tendv,"
					+ "khosp.AVAILABLE as soluongton from CHUYENKHO_SANPHAM cksp "
					+ "left join ERP_SANPHAM sp on sp.PK_SEQ=cksp.SANPHAM_FK "
					+ "left join ERP_DONVI dv on dv.PK_SEQ=sp.DONVI_FK "
					+ "left join KHO_SANPHAM khosp on khosp.SANPHAM_FK=cksp.SANPHAM_FK "
					+ "where CHUYENKHO_FK="+this.Id+" and khosp.KHO_FK="+this.khochuyen+"";
			listsp.clear();
			ResultSet rssp=db.get(query);
			while(rssp.next()){
				ISanPham sp=new SanPham();
				sp.setMasanpham( rssp.getString("MA"));
				sp.setTensanpham(rssp.getString("tensp"));
				sp.setDonvitinh(rssp.getString("tendv"));
				sp.setSoluong(rssp.getDouble("soluongton"));
				sp.setBooked(rssp.getDouble("soluongchuyen"));
				sp.setPK_SEQ(rssp.getString("PK_SEQ"));
				listsp.add(sp);
			}
			rssp.close();
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
		String querykh;
		 querykh="select PK_SEQ,TENKHO from KHO";
		 this.rslistkhonhan=db.get(querykh);
		 this.rslistkhochuyen=db.get(querykh);
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
		try
		{
			db.getConnection().setAutoCommit(false);
			String query;
			query="insert into CHUYENKHO(NGAYCHUYENKHO,KHONHAN,KHOCHUYEN,NGUOITAO,NGUOISUA,TRANGTHAI,NGAYTAO,NGAYSUA,LYDO) "
					+ "values('"+this.getNgayghinhan()+"',"+this.getKhonhan()+","+this.getKhochuyen()+","
							+ ""+this.userId+","+this.userId+",'0',"
									+ "'"+this.getNgayghinhan()+"','"+this.getNgayghinhan()+"','"+this.getLydo()+"')";
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;
			}
			String idkh="";
			  query = "select SCOPE_IDENTITY() as ckid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next())
				{
					idkh = rsDmh.getString("ckid");

				}
				rsDmh.close();
				System.out.println("ma can lay"+idkh);
				for(int i=0;i<this.listsp.size();i++)
				{
					ISanPham sp=listsp.get(i);
					System.out.println("id sp trong save"+sp.getId());
					System.out.println("kich thuoc"+listsp.size());
					query="INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values " +
							"("+idkh+",N'"+sp.getId()+"',"+sp.getBooked()+")"; 
					if(!db.update(query)){
						db.getConnection().rollback();
						this.msg ="Không thể cập nhật dòng lệnh: "+ query;
						return false;
					}
					
					query="update KHO_SANPHAM set BOOKED='"+sp.getBooked()+"',AVAILABLE='"+(sp.getSoluong()-sp.getBooked())+"' "
							+ "where KHO_FK="+this.khochuyen+" and SANPHAM_FK="+sp.getId()+"";
					if(!db.update(query)){
						db.getConnection().rollback();
						this.msg ="Không thể cập nhật dòng lệnh: "+ query;
						return false;
					}
				}
				
				
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return true;
	}
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		try{
			String query;
			db.getConnection().setAutoCommit(false);
			
			query="select cksp.SANPHAM_FK,cksp.SOLUONG,ck.KHOCHUYEN "
					+ "from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on ck.PK_SEQ=cksp.CHUYENKHO_FK "
					+ "where CHUYENKHO_FK="+this.Id+"";
			ResultSet rscksp=db.get(query);
			String masp="";
			String makhochuyen="";
			double sl=0;
			
			while(rscksp.next())
			{
				masp=rscksp.getString("SANPHAM_FK");
				sl=rscksp.getDouble("SOLUONG");
				makhochuyen=rscksp.getString("KHOCHUYEN");
				query="update KHO_SANPHAM SET BOOKED='0',AVAILABLE=AVAILABLE+"+sl+" "
						+ "where KHO_FK="+makhochuyen+" and SANPHAM_FK="+masp+" ";
				if(!db.update(query))
				{
					this.msg="khong thuc hien duoc cau lenh "+query;
					db.getConnection().rollback();
					return false;
				}
				
				
			}
			query="update CHUYENKHO set TRANGTHAI=2 where PK_SEQ="+Id;
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
		{
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean chot() {
		// TODO Auto-generated method stub
		try{
			String query;
			db.getConnection().setAutoCommit(false);
			System.out.println("id trong chot"+Id);
			query="select cksp.SOLUONG,ck.KHOCHUYEN,ck.KHONHAN,cksp.SANPHAM_FK "
					+ "from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on ck.PK_SEQ=cksp.CHUYENKHO_FK "
					+ "where ck.PK_SEQ="+this.Id+"";
			ResultSet rslayma=db.get(query);
			String manhan="";
			String machuyen="";
			String masp="";
			double sl=0;
			while(rslayma.next())
			{
				
				sl=rslayma.getDouble("SOLUONG");
				manhan=rslayma.getString("KHONHAN");
				machuyen=rslayma.getString("KHOCHUYEN");
				masp=rslayma.getString("SANPHAM_FK");
			}
			
			// cap nhat kho nhan
			boolean check = false;
			query="select SANPHAM_FK from KHO_SANPHAM where KHO_FK="+manhan+"";
			ResultSet rsspkho=db.get(query);
			while(rsspkho.next())
			{
				String maspkh=rsspkho.getString("SANPHAM_FK");
				if(maspkh.equals(masp))
				{
					query="update KHO_SANPHAM set SOLUONG=SOLUONG +"+sl+""
							+ ",AVAILABLE=AVAILABLE+"+sl+" where KHO_FK="+manhan+" and SANPHAM_FK="+masp+"" ;
					
					if(!db.update(query))
					{
						this.msg="khong thuc hien duoc cau lenh "+query;
						db.getConnection().rollback();
						return false;
					}
					check = true;
				}
			}
			if(check==false)
			{
				query="insert into KHO_SANPHAM(KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE)"
						+ "values("+manhan+","+masp+",'100','0','100')";
				if(!db.update(query))
				{
					this.msg="khong thuc hien duoc cau lenh "+query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			
			
			// cap nhat kho chuyen
			query="update KHO_SANPHAM set SOLUONG=SOLUONG -"+sl+","
					+ "BOOKED='0' where KHO_FK="+machuyen+" and SANPHAM_FK="+masp+"";
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			
			query="update CHUYENKHO set TRANGTHAI=1 where PK_SEQ="+Id;
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
		{
			e.getStackTrace();
		}
		return true;
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
	@Override
	public void setNgayghinhan(String ngayghinhan) {
		// TODO Auto-generated method stub
		this.ngayghinhan=ngayghinhan;
	}
	@Override
	public String getNgayghinhan() {
		// TODO Auto-generated method stub
		return this.ngayghinhan;
	}
	@Override
	public void setLydo(String lydo) {
		// TODO Auto-generated method stub
		this.lydo=lydo;
	}
	@Override
	public String getLydo() {
		// TODO Auto-generated method stub
		return this.lydo;
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
	public boolean edit() {
		// TODO Auto-generated method stub
		try
		{	
			db.getConnection().setAutoCommit(false);
			String query;
			query="select cksp.SANPHAM_FK,cksp.SOLUONG,ck.KHOCHUYEN "
					+ "from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on ck.PK_SEQ=cksp.CHUYENKHO_FK "
					+ "where CHUYENKHO_FK="+this.Id+"";
			ResultSet rskho=db.get(query);
			String masp="";
			String makhochuyen="";
			double sl=0;
			while(rskho.next())
			{
				masp=rskho.getString("SANPHAM_FK");
				sl=rskho.getDouble("SOLUONG");
				makhochuyen=rskho.getString("KHOCHUYEN");
				query="update KHO_SANPHAM SET BOOKED='0',AVAILABLE=AVAILABLE+"+sl+" "
						+ "where KHO_FK="+makhochuyen+" and SANPHAM_FK="+masp+" ";
				if(!db.update(query))
				{
					this.msg="khong thuc hien duoc cau lenh "+query;
					db.getConnection().rollback();
					return false;
				}
			}
			if(Id.length()>0)
			{
			query="update CHUYENKHO set NGAYCHUYENKHO='"+this.getNgayghinhan()+"',LYDO='"+this.getLydo()+"',"
					+ "KHOCHUYEN="+this.getKhochuyen()+",KHONHAN="+this.getKhonhan()+" where PK_SEQ="+Id;
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;
			}
			System.out.println("gia tri id trong edit:"+Id);
			query="delete from CHUYENKHO_SANPHAM where CHUYENKHO_FK="+this.Id+"";
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;}
			query="";
				for(int i=0;i<this.listsp.size();i++)
				{
					ISanPham sp=listsp.get(i);
					System.out.println("id sp trong edit"+sp.getId());
					System.out.println("kich thuoc"+listsp.size());
					query="INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values " +
							"("+this.getId()+",'"+sp.getId()+"',"+sp.getBooked()+")"; 
					if(!db.update(query)){
						db.getConnection().rollback();
						this.msg ="Không thể cập nhật dòng lệnh: "+ query;
						return false;
					}
					
					query="update KHO_SANPHAM set BOOKED='"+sp.getBooked()+"',AVAILABLE=SOLUONG-'"+(sp.getBooked())+"' "
							+ "where KHO_FK="+this.khochuyen+" and SANPHAM_FK="+sp.getId()+"";
					if(!db.update(query)){
						db.getConnection().rollback();
						this.msg ="Không thể cập nhật dòng lệnh: "+ query;
						return false;
					}
				}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return true;
	}
	@Override
	public List<ISanPham> getListSp() {
		// TODO Auto-generated method stub
		return this.listsp;
	}
	@Override
	public void setListSp(List<ISanPham> listsp) {
		// TODO Auto-generated method stub
		this.listsp=listsp;
	}
	
}
