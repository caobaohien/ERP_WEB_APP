package geso.erp.beans.donbanhang_tan.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.donbanhang_tan.*;
import geso.erp.db.sql.dbutils;

public class DonhangNew_tan implements IDonhangNew_tan{
	String Id,Ma,Trangthai,Khachhang,Ngay,Tongtien,Ngaytao,Nguoitao,UserId,msg;
	String IsCapNhat="";
	ResultSet rs;
	ResultSet rskh;
	/*List<Isanpham> list;*/
	
	@Override
	public ResultSet getRskh() {
		// TODO Auto-generated method stub
		return this.rskh;
	}
	@Override
	public void setRskh(ResultSet dvt) {
		// TODO Auto-generated method stub
		this.rskh=dvt;
		
	}
	dbutils db=new dbutils();
	public DonhangNew_tan() {
		// TODO Auto-generated constructor stub
	
		Id="";
		Ma="";
		Trangthai="";
		Khachhang="";
		Ngay="";
		Tongtien="";
		IsCapNhat="1";
		Ngaytao="";
		Nguoitao="";
		this.UserId="";
		this.msg="";
		/*this.list=new ArrayList<Isanpham>();*/
		
		
	}

	@Override
	public String getIsCapNhat() {
		// TODO Auto-generated method stub
		return this.IsCapNhat;
	}

	@Override
	public void setIsCapNhat(String iscapnhat) {
		// TODO Auto-generated method stub
		this.IsCapNhat=iscapnhat;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}
	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.UserId=_userId;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}
	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=_trangthai;
		
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.Id=_id;
		
	}
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}
	@Override
	public void setMa(String _ma) {
		// TODO Auto-generated method stub
		this.Ma=_ma;
	}
	@Override
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.Ngay;
	}
	@Override
	public void setNgaychungtu(String _ngaychungtu) {
		// TODO Auto-generated method stub
		this.Ngay=_ngaychungtu;
	}
	@Override
	public String getKhachhang() {
		// TODO Auto-generated method stub
		return this.Khachhang;
	}
	@Override
	public void setKhachhang(String _khachhang) {
		// TODO Auto-generated method stub
		this.Khachhang=_khachhang;
	}
	@Override
	public String getTongtien() {
		// TODO Auto-generated method stub
		return this.Tongtien;
	}
	@Override
	public void setTongtien(String _tongtien) {
		// TODO Auto-generated method stub
		this.Tongtien=_tongtien;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}
	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao=_nguoitao;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	
	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=_ngaytao;
	}
	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg=_msg;
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
	public void setDataList(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rs=_dmhlist;
	}
	@Override
	public void init() {
		try{
			if(Id.trim().length()>0)
			{
			String query="SELECT NGAYCHUNGTU,TONGTIENTRUOCVAT,KHACHHANG_FK from DONHANG where PK_SEQ="+Id;
			System.out.println(query);
			
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.setNgaychungtu(rs.getString("NGAYCHUNGTU"));
				this.setTongtien(rs.getString("TONGTIENTRUOCVAT"));
				this.setKhachhang(rs.getString("KHACHHANG_FK"));
				
			}

			String queryKH = "select PK_SEQ as Khachhang, TEN from KHACHHANG";
			this.rskh=db.get(queryKH);
			/*
			query="select  dhsp.soluong,dhsp.dongia,dhsp.thanhtien,sp.pk_seq " +
					",sp.ma,sp.ten, isnull(dv.ten,'') as donvi " +
					" from DONHANG_SANPHAM  " +
					" dhsp inner join sanpham sp on sp.PK_SEQ=dhsp.SANPHAM_FK " +
					" left join donvi dv on dv.pk_Seq=sp.DONVI_FK " +
					" where dhsp.DONHANG_Fk= "+this.Id;
			
			list.clear();
			
			ResultSet rssp=db.get(query);
			while(rssp.next()){
				Isanpham sp=new Sanpham();
				sp.setId(rssp.getString("pk_Seq"));
				sp.setMasanpham( rssp.getString("ma"));
				sp.setTensanpham(rssp.getString("ten"));
				sp.setDonvitinh(rssp.getString("donvi"));
				sp.setDongia(rssp.getDouble("dongia"));
				sp.setSoluong(rssp.getDouble("soluong"));
				list.add(sp);
			}
			rssp.close();
			*/
			rs.close();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String querykh="select PK_SEQ as Khachhang,TEN from KHACHHANG";
		this.rskh=db.get(querykh); 	
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean save() {
		// TODO Auto-generated methods tu
		try{
			db.getConnection().setAutoCommit(false);
			
			String query="";
			if(Id.length() >0){
				
				query="UPDATE DONHANG SET KHACHHANG_FK="+this.getKhachhang()+", "
						+ "NGAYCHUNGTU='"+this.getNgaychungtu()+"', TONGTIENTRUOCVAT="+this.getTongtien()+" "
								+ "WHERE PK_SEQ="+this.Id;
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}				
				
			}else{
				query="INSERT INTO DONHANG ( NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TONGTIENTRUOCVAT) " +
						"VALUES('"+this.getNgaychungtu()+"','0',"+this.getKhachhang()+",10000,'"+this.getDateTime()+"',10000,'"+this.getDateTime()+"',"+this.getTongtien()+")";
				
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
				/*String idcr="";
				  query = "select SCOPE_IDENTITY() as dhid";
					ResultSet rsDmh = db.get(query);
					if (rsDmh.next())
					{
						idcr = rsDmh.getString("dhid");
						
						
					}
					rsDmh.close();
				
					for(int i=0;i<this.list.size();i++){
						Isanpham sp=list.get(i);
						query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
								"("+idcr+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getThanhtien()+")"; 
						 
						if(!db.update(query)){
							db.getConnection().rollback();
							this.msg ="Không thể cập nhật dòng lệnh: "+ query;
							return false;
						}
					}*/
				
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
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	/*@Override
	public List<Isanpham> getListSp() {
		// TODO Auto-generated method stub
		return this.list;
	}
	@Override
	public void setListSp(List<Isanpham> list) {
		// TODO Auto-generated method stub
		this.list=list;
	}
	*/
	

}
