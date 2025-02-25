package geso.erp.beans.donmuahang_tan.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.donmuahang_tan.*;
import geso.erp.db.sql.dbutils;
import geso.erp.util.Library;

public class DonmuahangNew_tan implements IDonmuahangNew_tan{
	String Id,MaMH,trangthai,nhacungcap,ngaychungtu;
	double tongtien;
	String ngaytao,nguoitao,userId,msg;
	String IsCapNhat;
	ResultSet rsdmh;
	ResultSet rsNCC;
	List<ISanpham_tan> list;
	
	@Override
	public ResultSet getRsncc() {
		// TODO Auto-generated method stub
		return this.rsNCC;
	}
	@Override
	public void setRsncc(ResultSet dvt) {
		// TODO Auto-generated method stub
		this.rsNCC=dvt;
		
	}
	dbutils db=new dbutils();
	public DonmuahangNew_tan(String id) {
		// TODO Auto-generated constructor stub
	
		this.Id = id;
		this.MaMH = "";
		this.trangthai = "";
		this.nhacungcap = "";
		this.ngaychungtu = "";
		this.tongtien = 0;
		this.ngaytao ="";
		this.nguoitao = "";
		this.userId = "";
		this.IsCapNhat="1";
		this.msg="";
		this.list=new ArrayList<ISanpham_tan>();
		
		
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.userId=_userId;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}
	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=_trangthai;
		
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
	public String getMamuahang() {
		// TODO Auto-generated method stub
		return this.MaMH;
	}
	@Override
	public void setMamuahang(String _ma) {
		// TODO Auto-generated method stub
		this.MaMH=_ma;
	}
	@Override
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.ngaychungtu;
	}
	@Override
	public void setNgaychungtu(String _ngaychungtu) {
		// TODO Auto-generated method stub
		this.ngaychungtu=_ngaychungtu;
	}
	@Override
	public String getNhacungcap() {
		// TODO Auto-generated method stub
		return this.nhacungcap;
	}
	@Override
	public void setNhacungcap(String _nhacungcap) {
		// TODO Auto-generated method stub
		this.nhacungcap=_nhacungcap;
	}
	@Override
	public double getTongtien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}
	@Override
	public void setTongtien(double _tongtien) {
		// TODO Auto-generated method stub
		this.tongtien=_tongtien;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.nguoitao;
	}
	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.nguoitao = _nguoitao;	
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}
	
	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao=_ngaytao;
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
	public void setMsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg=_msg;
	}
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rsdmh;
	}
	@Override
	public void setDataList(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rsdmh=_dmhlist;
	}
	@Override
	public void init() {
		try{
			if(Id.trim().length()>0)
			{
			String query="SELECT NGAYCHUNGTU,TONGTIEN,NHACUNGCAP_FK from MUAHANG where PK_SEQ="+Id;
			System.out.println(query);
			
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.ngaychungtu=rs.getString("NGAYCHUNGTU");
				this.tongtien=rs.getDouble("tongtien");
				this.nhacungcap=rs.getString("NHACUNGCAP_FK");
				
			}
			query="select dhsp.soluong,dhsp.dongia,dhsp.thanhtien,sp.pk_seq " +
					",sp.ma,sp.ten, isnull(dv.ten,'') as donvi " +
					" from MUAHANG_SANPHAM  " +
					" dhsp inner join sanpham sp on sp.PK_SEQ=dhsp.SANPHAM_FK " +
					" left join donvi dv on dv.pk_Seq=sp.DONVI_FK " +
					" where dhsp.MUAHANG_FK= "+this.Id;
			
			list.clear();
			
			ResultSet rssp=db.get(query);
			while(rssp.next()){
				ISanpham_tan sp=new Sanpham_tan();
				sp.setId(rssp.getString("pk_Seq"));
				sp.setMasanpham( rssp.getString("ma"));
				sp.setTensanpham(rssp.getString("ten"));
				sp.setDonvitinh(rssp.getString("donvi"));
				sp.setDongia(rssp.getDouble("dongia"));
				sp.setSoluong(rssp.getDouble("soluong"));
				list.add(sp);
			}
			rssp.close();
			
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
		String queryNCC="select PK_SEQ,TEN from nhacungcap";
		this.rsNCC=db.get(queryNCC);		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean Display_dmh(){
		return true;
	}
	@Override
	public boolean save() {
		// TODO Auto-generated methods tu
		try{
			db.getConnection().setAutoCommit(false);
			
			String query="";
			if(Id.length() >0){
				if(Display_dmh()==false){
					// xoa di
					query="delete MUAHANG_SANPHAM  WHERE MUAHANG_FK ="+this.Id;
					if(!db.update(query)){
						this.msg="Không  thực hiện được câu lệnh: "+query;
						db.getConnection().rollback();
						return false;
					}
					
					query="UPDATE MUAHANG set nhacungcap_fk="+this.getNhacungcap()+",NGAYCHUNGTU=N'"+this.getNgaychungtu()+"',tongtien=N'"+this.tongtien+"' WHERE PK_SEQ="+this.Id;
					if(!db.update(query)){
						this.msg="Không  thực hiện được câu lệnh: "+query;
						db.getConnection().rollback();
						return false;
					}
					for(int i=0;i<this.list.size();i++){
						ISanpham_tan sp=list.get(i);
						query="INSERT INTO MUAHANG_SANPHAM (MUAHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
								"("+this.Id+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getThanhtien()+")"; 
						 
						if(!db.update(query)){
							db.getConnection().rollback();
							this.msg ="Không thể cập nhật dòng lệnh: "+ query;
							return false;
						}
					}
				
				}
				
			}else {
				query="INSERT INTO MUAHANG( NGAYCHUNGTU,TRANGTHAI,nhacungcap_FK,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TONGTIEN) " +
						"VALUES('"+this.ngaychungtu+"','0','"+this.nhacungcap+"','10000','"+this.getDateTime()+"','10000','"+this.getDateTime()+"',"+this.tongtien+")";
				if(!db.update(query)){
					this.msg="Không  thực hiện được câu lệnh: "+query;
					db.getConnection().rollback();
					return false;
				}
				String idcr="";
				  query = "select SCOPE_IDENTITY() as dhid";
					ResultSet rsDmh = db.get(query);
					if (rsDmh.next())
					{
						idcr = rsDmh.getString("dhid");
						
						
					}
					rsDmh.close();
				
					for(int i=0;i<this.list.size();i++){
						ISanpham_tan sp=list.get(i);
						query="INSERT INTO MUAHANG_SANPHAM(MUAHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
								"("+idcr+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getThanhtien()+")"; 
						 
						if(!db.update(query)){
							db.getConnection().rollback();
							this.msg ="Không thể cập nhật dòng lệnh: "+ query;
							return false;
						}
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
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	@Override
	public List<ISanpham_tan> getListSp() {
		// TODO Auto-generated method stub
		return this.list;
	}
	@Override
	public void setListSp(List<ISanpham_tan> list) {
		// TODO Auto-generated method stub
		this.list=list;
	}
	

}
