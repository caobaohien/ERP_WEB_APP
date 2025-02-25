package geso.training.bean.donhang.imp;

import geso.training.bean.donhang.IDonHang;
import geso.training.bean.sanpham.ISanPham;
import geso.training.bean.sanpham.imp.SanPham;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import geso.erp.db.sql.dbutils;

public class DonHang implements IDonHang{
	String PK_SEQ;
	String userId;
	String userTen;
	String Madh;
	String ngaydh;
	String trangthai;
	String Makh;
	String Tenkh;
	String msg;
	String khdiachi;
	String khdienthoai;
	private List<ISanPham> listSp;
	private List<IDonHang> ds_sanpham;
	double Vat;
	double TongtientruocVat;
	double TongtiensauVat;
	ResultSet dhRs;
	ResultSet khRs;
	dbutils db;
	
	String MaSP;
	String TenSP;
	String DVT;
	String PK_SEQsp;
	int SoLuong;
	double DonGia;
	double ThanhTien;
	double chietkhau;
	
	public DonHang(){
		this.PK_SEQ="";
		this.Madh="";
		this.ngaydh="";
		this.Makh="";
		this.Tenkh="";
		this.Vat=0.0;
		this.TongtientruocVat=0.0;
		this.TongtiensauVat=0.0;
		this.msg="";
		this.khdiachi="";
		this.khdienthoai="";
		this.trangthai="";
		this.db=new dbutils();
		this.listSp=new ArrayList<ISanPham>();
		this.ds_sanpham=new ArrayList<IDonHang>();
		
		this.PK_SEQ="";
		this.MaSP="";
		this.TenSP="";
		this.DVT="";
		this.SoLuong=0;
		this.DonGia=0.0;
		this.ThanhTien=0.0;
		this.chietkhau=0.0;
	}
	
	public DonHang(String PK_SEQsp, String MaSP, String TenSP, String DVT,
			int SoLuong, double DonGia, double chietkhau, double ThanhTien){
		super();
		this.PK_SEQ = PK_SEQsp;
		this.MaSP = MaSP;
		this.TenSP = TenSP;
		this.DVT = DVT;
		this.SoLuong = SoLuong;
		this.DonGia = DonGia;
		this.chietkhau = chietkhau;
		this.ThanhTien = ThanhTien;
	}
	public String getPK_SEQsp() {
		return PK_SEQ;
	}
	public void setPK_SEQsp(String pk_seq) {
		this.PK_SEQ = pk_seq;
	}
	public String getMaSP(){
		return MaSP;
	}
	public void setMaSP(String MaSP){
		this.MaSP=MaSP;
	}
	public String getTenSP(){
		return TenSP;
	}
	public void setTenSP(String TenSP){
		this.TenSP=TenSP;
	}
	public String getDVT(){
		return DVT;
	}
	public void setDVT(String DVT){
		this.DVT=DVT;
	}
	public int getSoLuong(){
		return SoLuong;
	}
	public void setSoLuong(int SoLuong){
		this.SoLuong=SoLuong;
	}
	public double getDonGia(){
		return DonGia;
	}
	public void setDonGia(double DonGia){
		this.DonGia=DonGia;
	}
	public double getThanhTien(){
		return ThanhTien;
	}
	public void setThanhTien(double ThanhTien){
		this.ThanhTien=ThanhTien;
	}
	public double getChietkhau() {
		return chietkhau;
	}
	public void setChietkhau(double chietkhau) {
		this.chietkhau=chietkhau;
	}
	
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	public void setPK_SEQ(String pk_seq) {
		PK_SEQ = pk_seq;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg=msg;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userid){
		userId=userid;
	}
	public String getUserTen(){
		return userTen;
	}
	public void setUserTen(String userTen){
		this.userTen=userTen;
	}
	public String getMadh(){
		return Madh;
	}
	public void setMadh(String Madh){
		this.Madh=Madh;
	}
	public String getNgaydh(){
		return ngaydh;
	}
	public void setNgaydh(String ngaydh){
		this.ngaydh=ngaydh;
	}
	public String getMakh(){
		return Makh;
	}
	public void setMakh(String Makh){
		this.Makh=Makh;
	}
	public String getTenkh(){
		return Tenkh;
	}
	public void setTenkh(String Tenkh){
		this.Tenkh=Tenkh;
	}
	public double getVat(){
		return Vat;
	}
	public void setVat(double Vat){
		this.Vat=Vat;
	}
	public double getTongtientruocVat(){
		return TongtientruocVat;
	}
	public void setTongtientruocVat(double TongtientruocVat){
		this.TongtientruocVat=TongtientruocVat;
	}
	public double getTongtiensauVat(){
		return TongtiensauVat;
	}
	public void setTongtiensauVat(double TongtiensauVat){
		this.TongtiensauVat=TongtiensauVat;
	}
	public String getKhDiachi() {
		// TODO Auto-generated method stub
		return this.khdiachi;
	}

	public void setKhDiachi(String Diachi) {
		// TODO Auto-generated method stub
		this.khdiachi=Diachi;
	}
	public String getKhDienthoai() {
		// TODO Auto-generated method stub
		return this.khdienthoai;
	}

	public void setKhDienthoai(String Dienthoai) {
		// TODO Auto-generated method stub
		this.khdienthoai=Dienthoai;
	}
	public String getTrangThai() {
		return trangthai;
	}
	public void setTrangThai(String trangthai) {
		this.trangthai = trangthai;
	}
	public ResultSet getDhRs(){
		return dhRs;
	}
	public void setDhRs(ResultSet dhrs){
		this.dhRs=dhrs;
	}
	public ResultSet getKhRs(){
		return khRs;
	}
	public void setKhRs(ResultSet khrs){
		this.khRs=khrs;
	}
	@Override
	public List<ISanPham> getListSp(){
		return listSp;
	}
	@Override
	public void setListSp(List<ISanPham> listSp){
		this.listSp=listSp;
	}
	public void laykhachhang(){
		String query="select PK_SEQ,SMARTID from KHACHHANG";
		this.khRs=this.db.get(query);
	}
	@Override
	public List<IDonHang> getDs_sanpham(){
		return ds_sanpham;
	}
	@Override
	public void setDs_sanpham(List<IDonHang> ds_sanpham){
		this.ds_sanpham = ds_sanpham;
	}
	public void init(String search){
		String query="";
		if(search.length() > 0){
			query=search;
		}else{
		try{
			query="select a.PK_SEQ as MADH,a.NGAYNHAP,a.TRANGTHAI,b.PK_SEQ as MAKH, b.TEN +'-'+ diachi as khTen,a.VAT,isnull(a.TONGTIEN,0)TONGTIEN,isnull(a.TONGGIATRI,0)TONGGIATRI "
					+"from DONHANG a inner join KHACHHANG b on a.KHACHHANG_FK=b.PK_SEQ where a.pk_seq='"+this.Madh+"'";
			ResultSet rs = this.db.get(query);
			while(rs.next()){
				this.Madh=rs.getString("MADH");
				this.ngaydh=rs.getString("NGAYNHAP");
				this.trangthai=rs.getString("TRANGTHAI");
				this.Makh=rs.getString("MAKH");
				this.Tenkh=rs.getString("khTen");
				this.Vat=rs.getDouble("VAT");
				this.TongtientruocVat=rs.getDouble("TONGTIEN");
				this.TongtiensauVat=rs.getDouble("TONGGIATRI");
				/*IDonHang dh=new DonHang();
				dh.setMadh(rs.getString("MADH"));
				dh.setNgaydh(rs.getString("NGAYNHAP"));
				dh.setMakh(rs.getString("MAKH"));
				dh.setTenkh(rs.getString("khTen"));
				dh.setVat(rs.getDouble("VAT"));
				dh.setTongtientruocVat(rs.getDouble("TONGTIEN"));
				dh.setTongtiensauVat(rs.getDouble("TONGGIATRI"));*/
			}
			rs.close();
			
			//lay thong tin san pham
			//this.ds_sanpham=new ArrayList<IDonHang>();
			String query1="select b.PK_SEQ as PK_SEQsp,b.MA as MASP, b.TEN as TENSP,c.DIENGIAI as TENDV,a.SOLUONG,a.GIAMUA,a.CHIETKHAU,a.THANHTIEN "
					+"from DONHANG_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ "
					+"inner join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK where a.DONHANG_FK='"+this.Madh+"'";
			
			this.listSp.clear();
			ResultSet rssp=this.db.get(query1);
				while(rssp.next()){
					ISanPham spp=new SanPham(
					rssp.getString("PK_SEQsp"),
					rssp.getString("MASP"),
					rssp.getString("TENSP"),
					rssp.getString("TENDV"),
					rssp.getInt("SOLUONG"),
					rssp.getDouble("GIAMUA"),
					rssp.getDouble("CHIETKHAU"),
					rssp.getDouble("THANHTIEN"));
					this.listSp.add(spp);
					}
				rssp.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		}
	}
	public void initsp(){
		try{
			this.listSp=new ArrayList<ISanPham>();
			String query="select b.PK_SEQ as PK_SEQsp,b.MA as MASP, b.TEN as TENSP,c.DIENGIAI as TENDV,a.SOLUONG,a.GIAMUA,a.CHIETKHAU,a.THANHTIEN "
					+"from DONHANG_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ "
					+"inner join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK where a.DONHANG_FK='"+this.Madh+"'";
			
			this.listSp.clear();
			ResultSet rssp=this.db.get(query);
				while(rssp.next()){
					ISanPham sp = new SanPham();
					sp.setPK_SEQ(rssp.getString("PK_SEQsp"));
					sp.setMaSP(rssp.getString("MASP"));
					sp.setTenSP(rssp.getString("TENSP"));
					sp.setDVT(rssp.getString("TENDV"));
					sp.setSoLuong(rssp.getInt("SOLUONG"));
					sp.setDonGia(rssp.getDouble("GIAMUA"));
					sp.setChietkhau(rssp.getDouble("CHIETKHAU"));
					sp.setThanhTien(rssp.getDouble("THANHTIEN"));
					listSp.add(sp);
					/*ISanPham sp=new SanPham(
					rssp.getString("PK_SEQsp"),
					rssp.getString("MASP"),
					rssp.getString("TENSP"),
					rssp.getString("TENDV"),
					rssp.getInt("SOLUONG"),
					rssp.getDouble("GIAMUA"),
					rssp.getDouble("CHIETKHAU"),
					rssp.getDouble("THANHTIEN"));
					listSp.add(sp);*/
					}
				rssp.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public boolean insert(){
		try{
			if(TongtiensauVat <= 0.0 || Makh.equals("") || this.userId.length() == 0){
				throw new Exception();
			}
			// mo ket noi
			db.getConnection().setAutoCommit(false);
			//them don hang moi vao db
			String query="insert into DONHANG(KHACHHANG_FK,NGUOITAO,NGAYTAO,NGAYNHAP,TRANGTHAI,TONGTIEN,VAT,TONGGIATRI)"
					+ "values('"+this.Makh+"','"+this.userId+"','"+SYSDATETIME()+"','"+this.ngaydh+"','"
					+ this.trangthai+"','"+this.TongtientruocVat+"','"+this.Vat+"','"+this.TongtiensauVat+"')";
			if(!db.update(query)){
				//neu khong insert dc ==>bao loi
				db.getConnection().rollback();
				this.msg = "không thể thực hiện được dòng lệnh: " + query +"\n";
				return false;
			}else{
				//insert được lấy mã đơn hàng mới thêm vào db
				String new_PK_SEQ="";
				query = "SELECT SCOPE_IDENTITY() AS MADH";
				ResultSet dhnewRs=db.get(query);
				if(dhnewRs.next()){
					new_PK_SEQ = dhnewRs.getString("MADH");
				}
				//insert chi tiet don hang
				for(int i=0;i<this.listSp.size();i++){
					ISanPham sp=listSp.get(i);
					String query1="insert into DONHANG_SANPHAM(DONHANG_FK,SANPHAM_FK,KHO_FK,SOLUONG,GIAMUA,CHIETKHAU,THANHTIEN)"
								+"values('"+new_PK_SEQ+"', "+ sp.getPK_SEQ()+", "+100000+", " +sp.getSoLuong()+", "+sp.getDonGia()+", "
								+sp.getChietkhau()+", "+ Math.round(sp.getThanhTien())+")";
					if(!db.update(query1)){
						db.getConnection().rollback();
						this.msg="không thể thực hiện insert chi tiết: " + query1 + "\n";
						return false;
					}
				}
			}
			//dong ket noi
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch(Exception e){
			db.update("rollback");
			this.msg+= "lỗi: " + e.getMessage() + "\n";
			e.printStackTrace();
			if(this.TongtiensauVat <= 0.0){
				this.msg+="Tổng tiền không thể âm hoặc bằng không \n";
			}
			if(this.Makh.equals("")){
				this.msg+="Khách hàng không thể thiếu \n";
			}
			if(this.userId.length()==0){
				this.msg+="Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
		return true;
	}
	public boolean Update(){
		try{
			if(TongtiensauVat <= 0.0 || Makh.equals("") || this.userId.length() == 0){
				throw new Exception();
			}
			//mo ket noi
			db.getConnection().setAutoCommit(false);
			//update donhang cu vao db
			String query="update DONHANG set KHACHHANG_FK='"+this.Makh +"',"
					+ "NGUOISUA='"+this.userId+"',"
					+ "NGAYSUA='"+SYSDATETIME()+"',"
					+ "TRANGTHAI="+this.trangthai+",TONGTIEN="+this.TongtientruocVat+",TONGGIATRI="+this.TongtiensauVat+" "
					+ "where PK_SEQ='"+this.Madh+"'";
			System.out.println("update: " +query);
			if(!db.update(query)){
				//neu khong thuc hien duoc insert thi bao loi
				this.db.getConnection().rollback();
				this.msg="không thể thực hiện update: " +query +"\n";
				return false;
			}else{
				//update thanh cong thi xoa chi tiet don hang, them chi tiêt đon hang
				String delete_query="delete DONHANG_SANPHAM where DONHANG_FK = "+this.Madh;
				if(!db.update(delete_query)){
					//neu khong thuc hien duoc xoa chi tiet
					this.db.getConnection().rollback();
					this.msg="không thể xóa chi tiết đơn hàng: " + delete_query+"\n";
					return false;
				}else{
					for(int i=0;i<this.listSp.size();i++){
						ISanPham sp=listSp.get(i);
						String query1="insert into DONHANG_SANPHAM(DONHANG_FK,SANPHAM_FK,KHO_FK,SOLUONG,GIAMUA,CHIETKHAU,THANHTIEN)"
									+"values('"+this.Madh+"', "+ sp.getPK_SEQ()+", "+100000+", " +sp.getSoLuong()+", "+sp.getDonGia()+", "
									+sp.getChietkhau()+", "+ Math.round(sp.getThanhTien())+")";
						if(!db.update(query1)){
							db.getConnection().rollback();
							this.msg="không thể thực hiện insert chi tiết: " + query1 + "\n";
							return false;
						}
					}
				}
			}
			//dong ket noi
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch(Exception e){
			db.update("rollback");
			this.msg+="lỗi: " + e.getMessage()+"\n";
			e.printStackTrace();
			if(this.TongtiensauVat <= 0.0){
				this.msg+="Tổng tiền không thể âm hoặc bằng không \n";
			}
			if(this.Makh.equals("")){
				this.msg+="Khách hàng không thể thiếu \n";
			}
			if(this.userId.length()==0){
				this.msg+="Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
		return true;
	}
private String SYSDATETIME() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.");
		Date date = new Date();
		return dateFormat.format(date);
	}
	/*	public boolean Save(){
		String query="select pk_seq from donhang where pk_seq="+this.Madh;
		ResultSet ds=db.get(query);
		try{
			if(TongtiensauVat <= 0.0 || Makh.equals("") || this.userId.length() == 0){
				throw new Exception();
			}
			if(ds.next()){
				//mo ket noi
				db.getConnection().setAutoCommit(false);
				//update donhang cu vao db
				query="update DONHANG set KHACHHANG_FK='"+this.Makh +"',"
						+ "NGUOISUA='"+this.userId+"',NGAYSUA='"+SYSDATETIME()+"',"
						+ "TRANGTHAI="+this.trangthai+",TONGGIATRI="+this.TongtiensauVat+" "
						+ "where PK_SEQ='"+this.Madh+"'";
				System.out.println("update: " +query);
				if(!db.update(query)){
					//neu khong thuc hien duoc insert thi bao loi
					this.db.getConnection().rollback();
					this.msg="không thể thực hiện update: " +query +"\n";
					return false;
				}
				//update thanh cong thi xoa chi tiet don hang, them chi tiêt đon hang
				String delete_query="delete DONHANG_SANPHAM where DONHANG_FK = "+this.Madh;
				if(!db.update(delete_query)){
					//neu khong thuc hien duoc xoa chi tiet
					this.db.getConnection().rollback();
					this.msg="không thể xóa chi tiết đơn hàng: " + delete_query+"\n";
					return false;
				}
				for(int i=0;i<this.listSp.size();i++){
					ISanPham sp=listSp.get(i);
					String query1="insert into DONHANG_SANPHAM(DONHANG_FK,SANPHAM_FK,KHO_FK,SOLUONG,GIAMUA,CHIETKHAU,THANHTIEN)"
								+"values('"+this.Madh+"', "+ sp.getPK_SEQ()+", "+100000+", " +sp.getSoLuong()+", "+sp.getDonGia()+", "
								+sp.getChietkhau()+", "+ Math.round(sp.getThanhTien())+")";
					if(!db.update(query1)){
						db.getConnection().rollback();
						this.msg="không thể thực hiện insert chi tiết: " + query1 + "\n";
						return false;
					}
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				return true;
			}else{
				// mo ket noi
				db.getConnection().setAutoCommit(false);
				//them don hang moi vao db
				query="insert into DONHANG(KHACHHANG_FK,NGUOITAO,NGAYTAO,NGAYNHAP,TRANGTHAI,TONGGIATRI)"
						+ "values('"+this.Makh+"','"+this.userId+"','"+SYSDATETIME()+"','"+this.ngaydh+"','"
						+ this.trangthai+"','"+this.TongtiensauVat+"')";
				if(!db.update(query)){
					//neu khong insert dc ==>bao loi
					db.getConnection().rollback();
					this.msg = "không thể thực hiện được dòng lệnh: " + query +"\n";
					return false;
				}else{
					//insert được lấy mã đơn hàng mới thêm vào db
					String new_PK_SEQ="";
					query = "SELECT SCOPE_IDENTITY() AS MADH";
					ResultSet dhnewRs=db.get(query);
					if(dhnewRs.next()){
						new_PK_SEQ = dhnewRs.getString("MADH");
					}
					//insert chi tiet don hang
					for(int i=0;i<this.listSp.size();i++){
						ISanPham sp=listSp.get(i);
						String query1="insert into DONHANG_SANPHAM(DONHANG_FK,SANPHAM_FK,KHO_FK,SOLUONG,GIAMUA,CHIETKHAU,THANHTIEN)"
									+"values('"+new_PK_SEQ+"', "+ sp.getPK_SEQ()+", "+100000+", " +sp.getSoLuong()+", "+sp.getDonGia()+", "
									+sp.getChietkhau()+", "+ Math.round(sp.getThanhTien())+")";
						if(!db.update(query1)){
							db.getConnection().rollback();
							this.msg="không thể thực hiện insert chi tiết: " + query1 + "\n";
							return false;
						}
						db.getConnection().commit();
						db.getConnection().setAutoCommit(true);
					}
				}
			}
			return true;
		}catch(Exception e){
			db.update("rollback");
			this.msg+="lỗi: " + e.getMessage()+"\n";
			e.printStackTrace();
			if(this.TongtiensauVat <= 0.0){
				this.msg+="Tổng tiền không thể âm hoặc bằng không \n";
			}
			if(this.Makh.equals("")){
				this.msg+="Khách hàng không thể thiếu \n";
			}
			if(this.userId.length()==0){
				this.msg+="Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
	}
*/
	public boolean deleteDonHang() {
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE DONHANG SET TRANGTHAI='2' WHERE PK_SEQ = " + this.Madh;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception e) {
			db.update("rollback");
			e.printStackTrace();
			return false;
		}

	}
	public boolean Delete(){
		String query="delete from donhang where pk_seq = "+this.Madh;
		query+=" delete from donhang_sanpham where donhang_fk = "+this.Madh;
		return db.update(query);
	}
	public boolean savekhachhang() {
		// TODO Auto-generated method stub
		try{
		db.getConnection().setAutoCommit(false);
		String query="insert into khachhang(pk_seq, ma, ten, maFAST, trangthai, diachi)"
				+ "values('"+this.Makh+"','"+this.Tenkh+"','"+this.Tenkh+"','"+this.khdienthoai+"','"+this.khdienthoai+"','"+this.khdiachi+"')";
		if(!db.update(query)){
			db.getConnection().rollback();
			this.msg="không thể save khách hàng mới: " + query;
			return false;
		}
		db.getConnection().commit();
		db.getConnection().setAutoCommit(true);
		return true;
		}catch(Exception e){
			db.update("rollback");
			this.msg+="lỗi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
	}
	public void DBClose(){
		try{
			if(this.khRs!=null)this.khRs.close();
			if(this.db!=null)this.db.shutDown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
