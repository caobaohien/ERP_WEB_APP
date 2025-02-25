package geso.erp.beans.donbanhang_thai.imp;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_thai.IDonbanhangList;
import geso.erp.beans.donbanhang_thai.IDonbanhangList;
import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.db.sql.dbutils;


public class DonbanhangList extends Phan_Trang implements IDonbanhangList {
	String MaDH;
	String Trangthai;
	String UserId;
	String Id;
	String Khachhang;
	String Ngaychungtu;
	String Tongtien;
	String Nguoitao;
	String Ngaytao;
	String Ten;
	String msg;
	ResultSet rsTrangThai;
	ResultSet rs;
	ResultSet rskh;
	dbutils db=new dbutils();
	public DonbanhangList() {
		MaDH = "";
		Ngaychungtu = "";
		Khachhang = "";
		Trangthai = "";
		Tongtien = "";
		Nguoitao = "";
		Ngaytao = "";
		Id="";
		this.UserId = "";
		this.msg = "";
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=  userId;
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
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}
	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Trangthai=ten;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	@Override
	public String getMaDH() {
		// TODO Auto-generated method stub
		return this.MaDH;
	}
	@Override
	public void setMaDH(String MaDH) {
		// TODO Auto-generated method stub
		this.MaDH= MaDH;
	}
	@Override
	public String getKhachhang() {
		// TODO Auto-generated method stub
		return this.Khachhang;
	}
	@Override
	public void setKhachhang(String Khachhang) {
		// TODO Auto-generated method stub
		this.Khachhang = Khachhang;
	}
	@Override
	public String getTongtien() {
		// TODO Auto-generated method stub
		return this.Tongtien;
	}
	@Override
	public void setTongtien(String Tongtien) {
		// TODO Auto-generated method stub
		this.Tongtien = Tongtien;
	}

	@Override
	public String getNgay() {
		// TODO Auto-generated method stub
		return this.Ngaychungtu;
	}
	@Override
	public void setNgay(String Ngay) {
		// TODO Auto-generated method stub
		this.Ngaychungtu=Ngay;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}
	@Override
	public void setNguoitao(String Nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao=Nguoitao;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	@Override
	public void setNgaytao(String Ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=Ngaytao;
	}
	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
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
	public void setDataList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		this.rs = dmhlist;
	}
	@Override
	public ResultSet getListKH() {
		// TODO Auto-generated method stub
		return this.rskh;
	}
	@Override
	public void setListKH(ResultSet rskh) {
		// TODO Auto-generated method stub
		this.rskh = rskh;
	}
	@Override
	public void init(String search) {
		String query= "SELECT BH.PK_SEQ AS MaDH, BH.NGAYCHUNGTU as Ngaychungtu, KH.TEN as Khachhang, ISNULL(BH.TRANGTHAI,'') AS Trangthai,"
			 +" BH.TONGTIEN AS Tongtien, BH.NGAYTAO as Ngaytao, NV.TEN as Nguoitao, KH.TEN as Ten" 
			 +" FROM BANHANG BH LEFT JOIN NHANVIEN NV ON NV.PK_SEQ = BH.NGUOITAO" 
			 +" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = BH.KHACHHANG_FK"
			 +" WHERE 1=1";
			 
		if(search.length() >0){
			query= search;
		}else{
		// TODO Auto-generated method stub
			
		}
		CreateRs();
		this.rs=db.get(query);
		
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String queryKH = "select PK_SEQ as Khachhang, TEN from KHACHHANG";
		this.rskh=db.get(queryKH);
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.Id = Id;
	}
	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try {
			 
			db.getConnection().setAutoCommit(false);

			
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ=" + this.Id;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception ex) {
			db.update("rollback");
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public ResultSet rsTrangThai() {
		// TODO Auto-generated method stub
		return this.rsTrangThai;
	}
	
}
