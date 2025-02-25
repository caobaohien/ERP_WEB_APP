package geso.erp.beans.banhang.imp; 
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.ISanpham;
import geso.erp.db.sql.dbutils;
import geso.erp.util.Library;
 
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Banhang implements IBanhang
{ 
	 
	public Banhang(String id){
		this.Sochungtu=id;
		this.Ngaychungtu=this.getDateTime();
		this.Msg="";
		this.Ghichu="";
		this.Khid="";
		this.tientruocvat=0;
		this.IsCapNhat="1";
		
		this.Trangthai="0";
		this.khdienthoai="";
		this.khdiachi="";
		this.khma="";
		this.KhTen="";
		this.IsThanhtoanhet="";
		this.tinhtrang_giaohang="";
		this.Diengiai_giaohang="";
		this.ShopId="";
	}
	String tinhtrang_giaohang;
	String Diengiai_giaohang;
	String ShopId;
	String IsThanhtoanhet;
	String khdienthoai="";
	String khdiachi="";
	String khma="";
	
	String IsCapNhat="";
	String Chungtutimkiem="";
	String UserId="";
	String Ghichu="";
	String Ngaychungtu="";
	String Khid="";
	String KhTen="";
	String Sochungtu="";
	String Trangthai;
	
	double tienthanhtoan;	
	double tientruocvat;
	double tienvat;
	double tiensauvat;
	ResultSet rskh;
	dbutils db=new dbutils();
	double Per_vat;
	String Msg="";
	
	List<ISanpham> splist=new ArrayList<ISanpham>();
	

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
	public String getGhiChu() {
		// TODO Auto-generated method stub
		return this.Ghichu;
	}

	@Override
	public void setGhiChu(String ghichu) {
		// TODO Auto-generated method stub
		this.Ghichu=ghichu;
	}

	@Override
	public String getSochungtu() {
		// TODO Auto-generated method stub
		return this.Sochungtu;
	}

	@Override
	public void setSochungtu(String sochungtu) {
		// TODO Auto-generated method stub
		this.Sochungtu=sochungtu;
	}

	@Override
	public String getNgaymuahang() {
		// TODO Auto-generated method stub
		return this.Ngaychungtu;
	}

	@Override
	public void setNgaymuahang(String ngaymuahang) {
		// TODO Auto-generated method stub
		this.Ngaychungtu=ngaymuahang;
	}

	@Override
	public String getKhten() {
		// TODO Auto-generated method stub
		return this.KhTen;
	}

	@Override
	public void setKhten(String nccTen) {
		// TODO Auto-generated method stub
		this.KhTen=nccTen;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=trangthai;
	}
 
	@Override
	public List<ISanpham> getSpList() {
		// TODO Auto-generated method stub
		return splist;
	}

	@Override
	public void setSpList(List<ISanpham> spList) {
		// TODO Auto-generated method stub
		splist=spList;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg=msg;
	}
	
	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		this.ShopId=Library.GetShopId(this.UserId, db);
		String query="SELECT PK_SEQ,MA+ '-'+ TEN +' - ' +isnull(diachi,'') as ten  FROM KHACHHANG where SHOP_FK="+this.ShopId ;
		this.rskh=db.get(query); 
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try{
			if(this.Sochungtu.length() >0){
			String query="SELECT  DH.THANHTOAN,isnull(KH.MA,'') as makh , isnull( KH.TEN,'') as tenkh, isnull(KH.DIACHI,'') as diachi ,isnull(KH.DIENTHOAI,'') as dienthoai, DH.NGAYCHUNGTU,DH.TRANGTHAI,isnull(KHACHHANG_FK,0) as khachhang_fk " +
					" ,DH.GHICHU,DH.NGUOITAO,DH.NGAYTAO,DH.NGUOISUA,DH.NGAYSUA,DH.TONGTIENTRUOCVAT " +
					" FROM DONHANG DH LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=DH.KHACHHANG_FK " +
					" WHERE DH.PK_SEQ="+this.Sochungtu;
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.tienthanhtoan=rs.getDouble("thanhtoan");
				this.Ngaychungtu=rs.getString("ngaychungtu");
				this.Khid=rs.getString("khachhang_fk");
				this.Ghichu=rs.getString("ghichu");
				this.tientruocvat=rs.getDouble("TONGTIENTRUOCVAT");
				this.Trangthai=rs.getString("TRANGTHAI");
				this.khma=rs.getString("makh");
				this.KhTen=rs.getString("tenkh");
				this.khdiachi=rs.getString("diachi");
				this.khdienthoai=rs.getString("dienthoai");
				if(this.tienthanhtoan==this.tientruocvat){
					this.IsThanhtoanhet="1";
				}
				
			}
			rs.close();
			query=" SELECT DV.ten as donvi, DHSP.DONHANG_FK,DHSP.SANPHAM_FK,DHSP.SOLUONG,DHSP.DONGIA,DHSP.CHIETKHAU,DHSP.THANHTIEN  , "+ 
				  " SP.TEN,SP.MA  FROM DONHANG_SANPHAM DHSP "+
				  " INNER JOIN SANPHAM SP ON SP.PK_SEQ=DHSP.SANPHAM_FK "+
				  " LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK "+
				  " WHERE DHSP.DONHANG_FK="+this.Sochungtu;
			splist.clear();
			
			ResultSet rssp=db.get(query);
			while(rssp.next()){
				ISanpham sp=new Sanpham();
				sp.setPK_SEQ(rssp.getString("SANPHAM_FK"));
				sp.setTensanpham(rssp.getString("ten"));
				sp.setMasanpham(rssp.getString("ma"));
				sp.setDongia(rssp.getDouble("DONGIA"));
				sp.setChietkhau(rssp.getDouble("CHIETKHAU"));
				sp.setThanhtien(rssp.getDouble("thanhtien"));
				sp.setSoluong(rssp.getDouble("soluong"));
				sp.setDonvitinh(rssp.getString("donvi"));
				this.splist.add(sp);
			}
			rssp.close();
			 
			}
		}catch(Exception er){
			er.printStackTrace();
		}
	}

	@Override
	public boolean createDmh() {
		// TODO Auto-generated method stub
		try{
			this.ShopId=Library.GetShopId(this.UserId, db);
			if(this.splist.size()==0){
				this.Msg="Vui lòng nhập sản phẩm trước khi lưu";
				return false;
			}
			
			if(this.Khid==null|| this.Khid.length()==0){
				this.Msg="Vui lòng chọn khách hàng";
				return false;
			}
			db.getConnection().setAutoCommit(false);
			
			String query=" INSERT INTO DONHANG (NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,GHICHU,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TONGTIENTRUOCVAT,THANHTOAN,SHOP_FK)values " +
					"('"+this.Ngaychungtu+"','0',"+this.Khid+",N'"+this.Ghichu+"',"+this.UserId+",getdate(),"+this.UserId+",getdate(),"+this.tientruocvat+","+this.tienthanhtoan+","+this.ShopId+")";
			
			if(!db.update(query)){
				db.getConnection().rollback();
				this.Msg="Không thể cập nhật dòng lệnh: "+ query;
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
			for(int i=0;i<splist.size();i++){
				ISanpham sp=splist.get(i);
				query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,CHIETKHAU,THANHTIEN) values " +
						"("+dmhCurrent+","+sp.getPK_SEQ()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getChietkhau()+","+sp.getThanhtien()+")"; 
				total_thanhtien+=  Math.round( sp.getSoluong()* (sp.getDongia()- sp.getDongia()*sp.getChietkhau()/100) *1000)/1000 ;
				
				
				if(!db.update(query)){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				query="UPDATE SANPHAM SET SOLUONG=SOLUONG-"+sp.getSoluong()+" WHERE PK_SEQ="+sp.getPK_SEQ();
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				
				
			}
			this.tientruocvat=total_thanhtien;
			
			
			 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			this.Msg="Bạn vừa thêm thành công đơn hàng số chứng từ: "+dmhCurrent;
			this.Sochungtu=dmhCurrent;
			return true;
		}catch(Exception er){
			er.printStackTrace();
			db.update("rollback");
			this.Msg=er.getMessage();
			return false;
			
		}
		
	}

	@Override
	public boolean updateDmh() {
		// TODO Auto-generated method stub
		try{
			if(this.splist.size()==0){
				this.Msg="Vui lòng nhập sản phẩm trước khi lưu";
				return false;
			}
			
			if(this.Khid==null|| this.Khid.length()==0){
				this.Msg="Vui lòng chọn khách hàng";
				return false;
			}
			db.getConnection().setAutoCommit(false);
			
			String query="SELECT TRANGTHAI FROM DONHANG WHERE PK_SEQ="+this.Sochungtu;
			
			ResultSet rsdh=db.get(query);
			
			if(rsdh.next()){
				this.Trangthai=rsdh.getString("TRANGTHAI");
			}
			rsdh.close();
			
			if(!this.getTrangthai().equals("0")){
				this.Msg="Không thể cập nhật đơn hàng này, đơn hàng này đã "+(this.Trangthai.equals("2") ? "Xóa":"Chốt");
				return false;
				 
			}
			
			
			db.getConnection().setAutoCommit(false);
			
			  query="SELECT SANPHAM_FK,SOLUONG FROM DONHANG_SANPHAM INNER JOIN DONHANG DH ON DH.PK_SEQ=DONHANG_FK " +
						 " WHERE DH.TRANGTHAI='0' AND DONHANG_FK="+this.Sochungtu;
			
			ResultSet rs=db.get(query);
			while (rs.next()){
				query="UPDATE SANPHAM SET SOLUONG=SOLUONG+ "+rs.getDouble("SOLUONG")+" WHERE PK_SEQ="+rs.getString("SANPHAM_FK");
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				 
			}
			
			query="delete DONHANG_SANPHAM where donhang_fk="+this.Sochungtu;
			
			if(!db.update(query)){
				db.getConnection().rollback();
				this.Msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			 
			
			
			 
			double total_thanhtien=0;
			for(int i=0;i<splist.size();i++){
				ISanpham sp=splist.get(i);
				query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,CHIETKHAU,THANHTIEN) values " +
						"("+this.Sochungtu+","+sp.getPK_SEQ()+","+sp.getSoluong()+","+sp.getDongia()+","+sp.getChietkhau()+","+sp.getThanhtien()+")"; 
				total_thanhtien+=  Math.round( sp.getSoluong()* (sp.getDongia()- sp.getDongia()*sp.getChietkhau()/100) *1000)/1000 ;
				
				
				if(!db.update(query)){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				query="UPDATE SANPHAM SET SOLUONG=SOLUONG-"+sp.getSoluong()+" WHERE PK_SEQ="+sp.getPK_SEQ();
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				
				
			}
			this.tientruocvat=total_thanhtien;
			
			
			query="UPDATE DONHANG SET THANHTOAN="+this.tienthanhtoan+",GHICHU=N'"+this.Ghichu+"' , KHACHHANG_FK="+this.Khid+",ngaychungtu='"+this.Ngaychungtu+"',TONGTIENTRUOCVAT="+this.tientruocvat+" WHERE PK_SEQ="+this.Sochungtu;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.Msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			
			 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			this.Msg="Bạn vừa sửa thành công đơn hàng số chứng từ: "+this.Sochungtu;
			
			return true;
		}catch(Exception er){
			er.printStackTrace();
			db.update("rollback");
			this.Msg=er.getMessage();
			return false;
			
		}
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTongtienchuaVat() {
		// TODO Auto-generated method stub
		return this.tientruocvat;
	}

	@Override
	public void setTongtienchuaVat(double ttchuavat) {
		// TODO Auto-generated method stub
			this.tientruocvat=ttchuavat;
	}

	@Override
	public double getTienVat() {
		// TODO Auto-generated method stub
		return this.tienvat;
	}

	@Override
	public void setTienVat(double vat) {
		// TODO Auto-generated method stub
		this.tienvat=vat;
	}

	@Override
	public double getTongtiensauVat() {
		// TODO Auto-generated method stub
		return this.tiensauvat;
	}

	@Override
	public void setTongtiensauVat(double ttsauvat) {
		// TODO Auto-generated method stub
		this.tiensauvat=ttsauvat;
	}

	@Override
	public double getPer_Vat() {
		// TODO Auto-generated method stub
		return this.Per_vat;
	}

	@Override
	public void setPer_Vat(double Per_Vat) {
		// TODO Auto-generated method stub
		this.Per_vat=Per_Vat;
	}

	@Override
	public ResultSet getRsKh() {
		// TODO Auto-generated method stub
		return rskh;
	}

	@Override
	public void setRsKh(ResultSet RsKh) {
		// TODO Auto-generated method stub
		this.rskh=RsKh;
	}

	@Override
	public String getKhId() {
		// TODO Auto-generated method stub
		return this.Khid;
	}

	@Override
	public void setKhId(String khId) {
		// TODO Auto-generated method stub
		this.Khid=khId;
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
	public String getchungtutimkiem() {
		// TODO Auto-generated method stub
		return this.Chungtutimkiem;
	}

	@Override
	public void setchungtutimkiem(String chungtutimkiem) {
		// TODO Auto-generated method stub
		this.Chungtutimkiem=chungtutimkiem;
	}

	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try{
			String query="SELECT TRANGTHAI FROM DONHANG WHERE PK_SEQ="+this.Sochungtu;
			
			ResultSet rsdh=db.get(query);
			
			if(rsdh.next()){
				this.Trangthai=rsdh.getString("TRANGTHAI");
			}
			rsdh.close();
			
			if(!this.getTrangthai().equals("0")){
				this.Msg="Không thể xóa đơn hàng này, đơn hàng này đã "+(this.Trangthai.equals("2") ? "Xóa":"Chốt");
				return false;
				 
			}
			
			
			db.getConnection().setAutoCommit(false);
			
			query=     " SELECT SANPHAM_FK,SOLUONG FROM DONHANG_SANPHAM INNER JOIN DONHANG DH ON DH.PK_SEQ=DONHANG_FK " +
						 " WHERE DH.TRANGTHAI='0' AND DONHANG_FK="+this.Sochungtu;
			
			ResultSet rs=db.get(query);
			while (rs.next()){
				query="UPDATE SANPHAM SET SOLUONG=SOLUONG+ "+rs.getDouble("SOLUONG")+" WHERE PK_SEQ="+rs.getString("SANPHAM_FK");
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.Msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				 
			}
			
			query="UPDATE DONHANG SET TRANGTHAI='2' WHERE PK_SEQ="+this.Sochungtu;
			if(db.updateReturnInt(query)!=1){
				db.getConnection().rollback();
				this.Msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			
			return true;
		}catch(Exception er){
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public void Init_getIdToMove(String action) {
		// TODO Auto-generated method stub
		try{
			this.ShopId=Library.GetShopId(this.UserId, db);
			String query="";
			if(action.equals("VeDau")){
				query="select ISNULL(max(pk_seq),0) as chungtu from donhang where shop_fk="+this.ShopId;
			}else if(action.equals("VeCuoi")){
				query="select ISNULL( min(pk_seq),0)  as chungtu  from donhang where ngaychungtu like '"+this.getDateTime().substring(0,8)+"%' and  shop_fk="+this.ShopId;
			}else if(action.equals("DichuyenToi")){
				if(this.Sochungtu.length() >0){
					query="select ISNULL(min(pk_seq),0)  as chungtu  from donhang where  shop_fk="+this.ShopId+" and  pk_seq>"+this.Sochungtu;
				}else{
					query="select ISNULL(max(pk_seq),0)  as chungtu  from donhang where shop_fk="+this.ShopId;
				}
			}else if(action.equals("DichuyenLui")){
				if(this.Sochungtu.length() >0){
					query="select ISNULL(max(pk_seq),0)  as chungtu  from donhang where shop_fk="+this.ShopId+" and  pk_seq<"+this.Sochungtu;
				}else{
					query="select ISNULL(max(pk_seq),0)  as chungtu  from donhang where shop_fk="+this.ShopId;
				}
				
			}else if(action.equals("timkiem")){
				query="select top 1 pk_seq  as chungtu  from donhang where shop_fk="+this.ShopId+" and  cast(pk_seq as varchar(10)) like '%"+this.Chungtutimkiem+"%'";
				this.Sochungtu="";
			}
			ResultSet rs=db.get(query);
			if(rs.next()){
				if(rs.getDouble("chungtu")>0){
					this.Sochungtu=rs.getString("chungtu");
					this.Chungtutimkiem=rs.getString("chungtu");
				}
			}
			rs.close();
			
			if(!action.equals("timkiem")){
				this.Chungtutimkiem=this.Sochungtu;
			}
		}catch(Exception er){
			er.printStackTrace();
		}
	}

	@Override
	public String getKhMa() {
		// TODO Auto-generated method stub
		return this.khma;
	}

	@Override
	public void setKhMa(String KhMa) {
		// TODO Auto-generated method stub
		this.khma=KhMa;
	}

	@Override
	public String getKhDienThoai() {
		// TODO Auto-generated method stub
		return this.khdienthoai;
	}

	@Override
	public void setKhDienThoai(String Dienthoai) {
		// TODO Auto-generated method stub
		this.khdienthoai=Dienthoai;
	}

	@Override
	public String getKhDiachi() {
		// TODO Auto-generated method stub
		return this.khdiachi;
	}

	@Override
	public void setKhDiachi(String Diachi) {
		// TODO Auto-generated method stub
		this.khdiachi=Diachi;
	}

	@Override
	public boolean saveKhachhang() {
		// TODO Auto-generated method stub
		try{
			
			if(this.khma.trim().equals("")){
				this.Msg="Vui lòng nhập mã khách hàng";
				return false;
			}
			if(this.KhTen.trim().equals("")){
				this.Msg="Vui lòng nhập tên khách hàng";
				return false;
			}
			
			String query="SELECT pk_seq FROM KHACHHANG WHERE SHOP_FK= (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+") AND  MA='"+this.khma+"'";
			ResultSet rs=db.get(query);
			if(rs.next()){
			 query="UPDATE KHACHHANG set TEN=N'"+this.KhTen+"',dienthoai=N'"+this.khdienthoai+"', diachi=N'"+this.khdiachi+"' " +
			 		" WHERE MA='"+this.khma+"' AND SHOP_FK=(SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+") ";	 
				this.Khid=rs.getString("pk_seq");
				if(!db.update(query)){
					this.Msg="Không thể cập nhật khách hàng: "+query;
					return false;
				}
			}else{
			
			  query="INSERT INTO KHACHHANG (MA,TEN,DIACHI,DIENTHOAI,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TRANGTHAI,SHOP_FK)  " +
					"SELECT '"+this.khma+"',N'"+this.KhTen+"',N'"+this.khdiachi+"','"+this.khdienthoai+"',"+this.UserId+",getdate(),"+this.UserId+",getdate(),1, (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+") ";
			  
			    if(!db.update(query)){
					this.Msg="Không thể cập nhật khách hàng: "+query;
					return false;
				}
			    query = "select SCOPE_IDENTITY() as khid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next())
				{
					this.Khid = rsDmh.getString("khid");
					
					
				}
				rsDmh.close();
				
			  
			}
			
			
			
			 
		}catch(Exception er){
			er.printStackTrace();
			this.Msg="Vui lòng thử lại : lỗi "+er.getMessage();
			return false;
			
		}
		return false;
	}

	@Override
	public double getTienThanhToan() {
		// TODO Auto-generated method stub
		return this.tienthanhtoan;
	}

	@Override
	public void setTienThanhToan(double tienthanhtoan) {
		// TODO Auto-generated method stub
		this.tienthanhtoan=tienthanhtoan;
	}

	@Override
	public String getIsThanhToanHet() {
		// TODO Auto-generated method stub
		return this.IsThanhtoanhet;
	}

	@Override
	public void setIsThanhToanHet(String IsThanhToanHet) {
		// TODO Auto-generated method stub
		this.IsThanhtoanhet=IsThanhToanHet;
	}

	@Override
	public String getShopId() {
		// TODO Auto-generated method stub
		return this.ShopId;
	}

	@Override
	public void setShopId(String ShopId) {
		// TODO Auto-generated method stub
		this.ShopId=ShopId;
	}

	@Override
	public String gettinhtrang_GIAOHANG() {
		// TODO Auto-generated method stub
		return this.tinhtrang_giaohang;
	}

	@Override
	public void settinhtrang_GIAOHANG(String tinhtrang_GIAOHANG) {
		// TODO Auto-generated method stub
		this.tinhtrang_giaohang=tinhtrang_GIAOHANG;
	}

	@Override
	public String getDiengiai_GIAOHANG() {
		// TODO Auto-generated method stub
		return this.Diengiai_giaohang;
	}

	@Override
	public void setDiengiai_GIAOHANG(String Diengiai_GIAOHANG) {
		// TODO Auto-generated method stub
		this.Diengiai_giaohang=Diengiai_GIAOHANG;
	}
}
