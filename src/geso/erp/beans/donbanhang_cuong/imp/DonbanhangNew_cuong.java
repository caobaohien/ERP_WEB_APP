package geso.erp.beans.donbanhang_cuong.imp;

import java.sql.ResultSet;

import geso.erp.beans.donbanhang_cuong.IDonbanhangNew_cuong;
import geso.erp.db.sql.dbutils;

public class DonbanhangNew_cuong implements IDonbanhangNew_cuong {


	String MaDH;
	String TrangThai;
	String KhachHang;
	String Msg;
	String UserId;
	String Ngay;
	String TongTien;
	String NgayTao;
	String NguoiTao;
	String id;
	ResultSet rsdata;
	ResultSet rskhachhang;
	dbutils db;
	public DonbanhangNew_cuong(){
		id="";
		 MaDH="";
		 TrangThai="0";
		 KhachHang="";
		 Msg="";
		 UserId="";
		 Ngay="";
		 TongTien="";
		 NgayTao="";
		 NguoiTao="";
		 db= new dbutils();
	}
	@Override
	public void setMaDH(String MaDH) {
		// TODO Auto-generated method stub
		this.MaDH=MaDH;
		
	}

	@Override
	public String getMaDH() {
		// TODO Auto-generated method stub
		return this.MaDH;
	}

	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.TrangThai=trangthai;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.TrangThai;
	}

	@Override
	public void setKhachHang(String khachhang) {
		// TODO Auto-generated method stub
		this.KhachHang=khachhang;
	}

	@Override
	public String getKhachHang() {
		// TODO Auto-generated method stub
		return this.KhachHang;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=userId;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setNgay(String ngay) {
		// TODO Auto-generated method stub
		this.Ngay=ngay;
	}

	@Override
	public String getNgay() {
		// TODO Auto-generated method stub
		return this.Ngay;
	}

	@Override
	public void setTongTien(String TongTien) {
		// TODO Auto-generated method stub
		this.TongTien=TongTien;
	}

	@Override
	public String getTongTien() {
		// TODO Auto-generated method stub
		return this.TongTien;
	}

	@Override
	public void setNgayTao(String NgayTao) {
		// TODO Auto-generated method stub
		this.NgayTao=NgayTao;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.NgayTao;
	}

	@Override
	public void setNguoiTao(String NguoiTao) {
		// TODO Auto-generated method stub
		this.NguoiTao=NguoiTao;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.NguoiTao;
	}

	@Override
	public void setRsData(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsdata=rs;
	}

	@Override
	public ResultSet getRsdata() {
		// TODO Auto-generated method stub
		return this.rsdata;
	}

	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg=msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void init(String search){ 
		
		// TODO Auto-generated method stub
		String query="select bh.pk_seq,bh.ngaychungtu,kh.ten,bh.trangthai,bh.tongtien,bh.ngaytao,nv.TEN as TenNV from BANHANG bh"+ " left join KHACHHANG kh on bh.khachhang_fk = kh.pk_seq"+" left join nhanvien nv on bh.NGUOITAO=nv.PK_SEQ" ;
		if(search.length()>0){
			query=search;
		}
		
		this.rsdata=db.get(query);
		CreatRS();
	}

	@Override
	public void CreatRS() {
		// TODO Auto-generated method stub
		String query="Select PK_SEQ,TEN from KHACHHANG";
		this.rskhachhang=db.get(query);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet getrskhachhang() {
		// TODO Auto-generated method stub
		return this.rskhachhang;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public boolean save() {
		 
		try{
			db.getConnection().setAutoCommit(false);
		 
			
			String query="INSERT INTO BANHANG (NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO,NGAYTAO,TONGTIEN) VALUES('"
				+this.Ngay+"',"+0+",'"+this.KhachHang+"',"+this.UserId+",'"+this.NgayTao+"','"+this.getTongTien()+"') ";;
			
			if(!db.update(query)){
				db.getConnection().rollback();
				this.Msg="Không thể thực hiện dòng lệnh ; "+query;
				return false;
				
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			
			
			this.Msg="Lỗi : " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}

}
