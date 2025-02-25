package geso.erp.beans.donmuahang_tan.imp;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donmuahang_tan.IDonmuahangList_tan;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.db.sql.dbutils;

public class DonmuahangList_tan extends Phan_Trang implements IDonmuahangList_tan{
	String userId;
	String id; 
	String mamh;
	String ngaychungtu;
	String nhacungcap;
	String trangthai;
	double tongtien;
	String ngaytao;
	String nguoitao;
	
	String msg;
	ResultSet rsdmh;
	ResultSet rsncc;
	dbutils db = new dbutils();
	
	public DonmuahangList_tan(){
		this.userId = "";
		this.mamh = "";
		this.ngaychungtu = "";
		this.nhacungcap = "";
		this.trangthai = "";
		this.tongtien = 0;
		this.ngaytao = "";
		this.nguoitao = "";
		
		this.msg = "";
		
	}
	
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}
	@Override
	public String getId(){
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(String Id){
		// TODO Auto-generated method stub
		this.id = Id;
	}
	@Override
	public String getMamuahang() {
		// TODO Auto-generated method stub
		return this.mamh;
	}
	@Override
	public void setMamuahang(String mamuahang) {
		// TODO Auto-generated method stub
		this.mamh=mamuahang;
	}
	@Override
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.ngaychungtu;
	}
	@Override
	public void setNgaychungtu(String ngaychungtu) {
		// TODO Auto-generated method stub
		this.ngaychungtu=ngaychungtu;
	}
	@Override
	public String getNhacungcap() {
		// TODO Auto-generated method stub
		return this.nhacungcap;
	}
	@Override
	public void setNhacungcap(String nhacungcap) {
		// TODO Auto-generated method stub
		this.nhacungcap=nhacungcap;
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
	public double getTongtien() {
		// TODO Auto-generated method stub
		return this.tongtien;
	}
	@Override
	public void setTongtien(double tongtien) {
		// TODO Auto-generated method stub
		this.tongtien=tongtien;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}
	@Override
	public void setNgaytao(String ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao=ngaytao;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.nguoitao;
	}
	@Override
	public void setNguoitao(String nguoitao) {
		// TODO Auto-generated method stub
		this.nguoitao=nguoitao;
	}
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rsdmh;
	}
	@Override
	public void setDataList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		this.rsdmh=dmhlist;
	}
	@Override
	public ResultSet getListNCC() {
		// TODO Auto-generated method stub
		return this.rsncc;
	}
	@Override
	public void setListNCC(ResultSet ncclist) {
		// TODO Auto-generated method stub
		this.rsncc=ncclist;
	}
	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String queryDMH="";
		if(search.length() >0){
			queryDMH = search;
		}else{
		// TODO Auto-generated method stub
			queryDMH = "SELECT MH.PK_SEQ AS MAMH, MH.NGAYCHUNGTU, MH.NHACUNGCAP_FK AS NHACUNGCAP, NCC.TEN AS TEN,"
					+ " ISNULL(MH.TRANGTHAI,'') AS TRANGTHAI, MH.TONGTIEN, MH.NGAYTAO, MH.NGUOITAO "
					+ "FROM MUAHANG MH LEFT JOIN NHANVIEN NV ON MH.NGUOITAO = NV.PK_SEQ  "
					+ "LEFT JOIN NHACUNGCAP NCC ON NCC.PK_SEQ = MH.NHACUNGCAP_FK "
					+ "WHERE 1=1";
		}
		this.rsdmh=db.get(queryDMH);
		String queryNCC = "SELECT PK_SEQ AS NHACUNGCAP, TEN FROM NHACUNGCAP";
		this.rsncc=db.get(queryNCC);
	}
	
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try {
			 
			db.getConnection().setAutoCommit(false);

			String query = "UPDATE MUAHANG SET TRANGTHAI='2' WHERE PK_SEQ=" + this.id;
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
	
}
