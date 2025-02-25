package geso.erp.beans.muahang_huy.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donhang_huy.ISanPham;
import geso.erp.beans.donhang_huy.imp.SanPham;
import geso.erp.beans.muahang_huy.IChiTietMuaHang;
import geso.erp.beans.muahang_huy.IMuaHangNew;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.db.sql.dbutils;

public class MuaHangNew extends Phan_Trang implements IMuaHangNew {
	String mancc,trangthai,Id,UserId, msg,nguoitao,nguoisua,ghichu,mamh;
	float tongtien;
	String ngay,tenncc;
	ResultSet rs;
	ResultSet rsncc;
	dbutils db=new dbutils();
	List<ISanPham> listsp;
	List<IChiTietMuaHang> listctmh;
	public MuaHangNew(){
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
		this.listsp=new ArrayList<ISanPham>();
	}
	@Override
	public void setmamh(String mamh) {
		// TODO Auto-generated method stub
		this.mamh=mamh;
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
	public String getmamh() {
		// TODO Auto-generated method stub
		return this.mamh;
	}
	@Override
	public float gettongtien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}
	@Override
	public void settongtien(float tongtien) {
		// TODO Auto-generated method stub
		this.tongtien=tongtien;
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
		return this.rs;
	}
	@Override
	public void setDataList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rs=rs;
	}
	@Override
	public void init() 
	{
		try{
		String queryedit;
		queryedit="select PK_SEQ,NGAYCHUNGTU,TONGTIEN,NHACUNGCAP_FK from MUAHANG where PK_SEQ="+Id;
		ResultSet rs =this.db.get(queryedit);
		if(rs!=null)
		{
				if(rs.next())
				{
					this.mancc=rs.getString("NHACUNGCAP_FK");
					this.ngay=rs.getString("NGAYCHUNGTU");
					this.tongtien=rs.getFloat("TONGTIEN");
				}
		}
		queryedit="select  dhsp.soluong,dhsp.dongia,dhsp.thanhtien,sp.pk_seq ,sp.ma,sp.ten, isnull(dv.ten,'') as donvi,isnull(sp.chietkhau,'') as chietkhau from MUAHANG_SANPHAM dhsp "
  + "left join sanpham sp on sp.MA=dhsp.SANPHAM_FK  "
  + "left join donvi dv on dv.pk_Seq=sp.DONVI_FK "
   +"where dhsp.MUAHANG_Fk="+this.Id+"";
		listsp.clear();
		ResultSet rssp=db.get(queryedit);
		while(rssp.next()){
			ISanPham sp=new SanPham();
			sp.setPK_SEQ(rssp.getString("pk_Seq"));
			sp.setMasanpham( rssp.getString("ma"));
			sp.setTensanpham(rssp.getString("ten"));
			sp.setDonvitinh(rssp.getString("donvi"));
			sp.setDongia(rssp.getDouble("dongia"));
			sp.setSoluong(rssp.getDouble("soluong"));
			sp.setChietkhau(rssp.getDouble("chietkhau"));
			listsp.add(sp);
		}
		rssp.close();
		rs.close();
		}
		catch(Exception e)
		{}
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String querykh;
		 querykh="select PK_SEQ,TEN from NHACUNGCAP";
		 this.rsncc=db.get(querykh);
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String idmh;
			String query="";
				query="INSERT INTO MUAHANG(NGAYCHUNGTU,TRANGTHAI,NHACUNGCAP_FK,NGAYTAO,TONGTIEN,NGUOITAO) "
			+"VALUES('"+this.getngay()+"','0',N'"+this.getmancc()+"','"+this.getngay()+"','"+this.gettongtien()+"',"+this.UserId+")";
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
				String idkh="";
				  query = "select SCOPE_IDENTITY() as dhid";
					ResultSet rsDmh = db.get(query);
					if (rsDmh.next())
					{
						idkh = rsDmh.getString("dhid");
	
					}
					rsDmh.close();
					System.out.println("ma can lay"+idkh);
					for(int i=0;i<this.listsp.size();i++){
						ISanPham sp=listsp.get(i);
						System.out.println("kich thuoc"+listsp.size());
						query="INSERT INTO MUAHANG_SANPHAM (MUAHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
								"("+idkh+",N'"+sp.getMasanpham()+"',"+sp.getSoluong()+","+sp.getDongia()+","+sp.getThanhtien()+")"; 
						 
						if(!db.update(query)){
							db.getConnection().rollback();
							this.msg ="Không thể cập nhật dòng lệnh: "+ query;
							return false;
						}
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
	public boolean edit() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			String query="";
			if(Id.length()>0)
			{
				query="update MUAHANG set NHACUNGCAP_FK=N'"+this.getmancc()+
						"',NGAYCHUNGTU=N'"+this.getngay()+
						"',TONGTIEN="+this.gettongtien()+" where PK_SEQ="+this.Id;
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
				query="delete from MUAHANG_SANPHAM where MUAHANG_FK="+this.Id+"";
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;}
				for(int i=0;i<this.listsp.size();i++){
					ISanPham sp=listsp.get(i);
					query="INSERT INTO MUAHANG_SANPHAM (MUAHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
							"("+this.Id+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getThanhtien()+")"; 
					 
					if(!db.update(query)){
						db.getConnection().rollback();
						this.msg ="Không thể cập nhật dòng lệnh: "+ query;
						return false;
					}
				}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
			
		}catch(Exception err){
			this.msg="Lỗi :"+err.getMessage();
			db.update("rollback");
			err.printStackTrace();
			return false;
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
	public void setmancc(String mancc) {
		// TODO Auto-generated method stub
		this.mancc=mancc;
	}
	@Override
	public String getmancc() {
		// TODO Auto-generated method stub
		return this.mancc;
	}
	@Override
	public boolean savencc() {
		// TODO Auto-generated method stub
		try
		{
			INhacungcap ncc=new Nhacungcap();
			String query;
			query="insert into NHACUNGCAP(MA,TEN) value("+ncc.getMa()+","+ncc.getTen()+")";
			String idncc="";
			  query = "select SCOPE_IDENTITY() as nccid";
			  ResultSet rsncc = db.get(query);
				if (rsncc.next())
				{
					idncc = rsncc.getString("dhid");
				}
				rsncc.close();
				query="Insert into SHOP(PK_SEQ,PHONE_NUM,ADDRESS) value("+idncc+","+ncc.getDienthoai()+","+ncc.getDiachi()+")";
				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg ="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{}
		return false;
	}
 

}
