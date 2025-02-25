package geso.erp.beans.donbanhang_thai.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.donbanhang_thai.IDonbanhang;
import geso.erp.beans.donbanhang_thai.ISanpham;
import geso.erp.db.sql.dbutils;

public class Donbanhang implements IDonbanhang {
	String Id;
	String Ma;
	String Trangthai;
	String Khachhang;
	String Ngay;
	String Tongtien;
	String Ngaytao;
	String Nguoitao;
	String UserId;
	String msg;
	String IsCapNhat="";
	ResultSet rs;
	ResultSet rskh;
	List<ISanpham> list;
	
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
	public Donbanhang() {
		// TODO Auto-generated constructor stub
	
		Id="";
		Ma="";
		Trangthai="0";
		Khachhang="";
		Ngay="";
		Tongtien="";
		IsCapNhat="1";
		Ngaytao="";
		Nguoitao="";
		this.UserId="";
		this.msg="";
		// ham khoitao list size=0
		this.list=new ArrayList<ISanpham>() ;
		
		
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
			String query="SELECT NGAYCHUNGTU,TONGTIEN,KHACHHANG_FK from BANHANG where PK_SEQ="+Id;
			System.out.println(query);
			
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.setNgaychungtu(rs.getString("NGAYCHUNGTU"));
				this.setTongtien(rs.getString("TONGTIEN"));
				this.setKhachhang(rs.getString("KHACHHANG_FK"));
				
			}
			
			query=" SELECT DHSP.DONGIA,DHSP.SOLUONG,SP.PK_SEQ,SP.MA,SP.TEN,DV.TEN AS DONVI "+
			 " FROM [BANHANG_SANPHAM] DHSP INNER JOIN SANPHAM SP ON SP.PK_SEQ=DHSP.SANPHAM_FK "+
			 " LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK "+
			 " WHERE BANHANG_FK= "+this.Id;
			list.clear();
			ResultSet rssp=db.get(query);
			while (rssp.next()){
				ISanpham sp =new Sanpham();
				sp.setId(rssp.getString("pk_Seq"));
				sp.setTensanpham(rssp.getString("TEN"));
				sp.setMasanpham(rssp.getString("MA"));
				sp.setDonvitinh(rssp.getString("DONVI"));
				sp.setDongia(rssp.getDouble("dongia"));
				sp.setSoluong(rssp.getDouble("soluong"));
				list.add(sp);
				
			}
			rssp.close();
			

			String queryKH = "select PK_SEQ as Khachhang, TEN from KHACHHANG";
			this.rskh=db.get(queryKH);
  
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
			
			String query="INSERT INTO BANHANG (KHACHHANG_FK, " +
			"NGUOITAO, " +
			"NGAYTAO, " +
			"NGAYCHUNGTU, " +
			"TRANGTHAI, " +
			"TONGTIEN) " +
	"VALUES " + "('"+this.Khachhang+"', " +
				"'"+this.UserId+"', " +
				"SYSDATETIME(), " +
				"'"+this.Ngay+"', " +
				"'"+this.Trangthai+"', " +
				"'"+this.Tongtien+"')";
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể thực hiện: "+query;
				return false;
			}
			
			String dmhCurrent = "";
			query = "select SCOPE_IDENTITY() as dmhId";
			ResultSet rsDmh = db.get(query);
			if (rsDmh.next())
			{
				dmhCurrent = rsDmh.getString("dmhId");
				rsDmh.close();
			}
			
			double total_thanhtien=0;
			for(int i=0;i<list.size();i++){
				ISanpham sp=list.get(i);
				query="INSERT INTO BANHANG_SANPHAM (BANHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
						"("+dmhCurrent+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+" ,"+ Math.round(sp.getDongia()*sp.getSoluong())+")"; 
	  
				
				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e){
			db.update("rollback");
			this.msg="Lỗi: " + e.getMessage();
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
	@Override
	public boolean update() {

		// TODO Auto-generated methods tu
		try{
			db.getConnection().setAutoCommit(false);
			
			String query=" UPDATE BANHANG SET KHACHHANG_FK='"+this.Khachhang+"',NGAYCHUNGTU='"+this.Ngay+"',TONGTIEN='"+this.Tongtien+"' WHERE PK_SEQ=" +this.Id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể thực hiện: "+query;
				return false;
			}
			
			query="delete [BANHANG_SANPHAM] where banhang_fk="+this.Id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể thực hiện: "+query;
				return false;
			}
			
			for(int i=0;i<list.size();i++){
				ISanpham sp=list.get(i);
				query="INSERT INTO BANHANG_SANPHAM (BANHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
						"("+this.Id+","+sp.getId()+","+sp.getSoluong()+","+sp.getDongia()+" ,"+ Math.round(sp.getDongia()*sp.getSoluong())+")"; 
	  
				
				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
			}
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e){
			db.update("rollback");
			this.msg="Lỗi: " + e.getMessage();
			return false;
		}
		return true;
	
	}
	@Override
	public List<ISanpham> getListSp() {
		// TODO Auto-generated method stub
		return list;
	}
	@Override
	public void setListSp(List<ISanpham> _list) {
		// TODO Auto-generated method stub
		list=_list;
	}
	
}
