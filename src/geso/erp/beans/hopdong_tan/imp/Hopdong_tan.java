package geso.erp.beans.hopdong_tan.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.hopdong_tan.IHopdong_tan;
import geso.erp.db.sql.dbutils;

public class Hopdong_tan extends Phan_Trang implements IHopdong_tan{

	String userId;
	String id;
	String pk_hoso;
	String pk_hopdong;
	String tv_trangthai;
	String quocgia;
	String sohopdong;
	
	String msg;
	ResultSet rslist;
	ResultSet rsHoso;
	dbutils db=new dbutils();
	
	public Hopdong_tan(){
		this.userId = "";
		this.id = "";
		this.pk_hoso = "";
		this.pk_hopdong = "";
		this.tv_trangthai = "";
		this.quocgia = "";
		this.sohopdong = "";
		this.msg = "";
		
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.userId = _userId;
	}

	@Override
	public String getPk_hoso() {
		// TODO Auto-generated method stub
		return this.pk_hoso;
	}

	@Override
	public void setPk_hoso(String _pk_hoso) {
		// TODO Auto-generated method stub
		this.pk_hoso = _pk_hoso;
	}

	@Override
	public String getPk_hopdong() {
		// TODO Auto-generated method stub
		return this.pk_hopdong;
	}

	@Override
	public void setPk_hopdong(String _pk_hopdong) {
		// TODO Auto-generated method stub
		this.pk_hopdong = _pk_hopdong;
	}

	@Override
	public String getQg_trangthai() {
		// TODO Auto-generated method stub
		return this.tv_trangthai;
	}

	@Override
	public void setQg_trangthai(String _tv_trangthai) {
		// TODO Auto-generated method stub
		this.tv_trangthai = _tv_trangthai;
	}

	@Override
	public String getQuocgia() {
		// TODO Auto-generated method stub
		return this.quocgia;
	}

	@Override
	public void setQuocgia(String _quocgia) {
		// TODO Auto-generated method stub
		this.quocgia = _quocgia;
	}

	@Override
	public String getSohopdong() {
		// TODO Auto-generated method stub
		return this.sohopdong;
	}

	@Override
	public void setSohopdong(String _sohopdong) {
		// TODO Auto-generated method stub
		this.sohopdong = _sohopdong;
	}

	@Override
	public String getMsg(){
		return this.msg;
	}
	@Override
	public void setMsg(String _msg){
		this.msg = _msg;
	}
	
	@Override
	public ResultSet getRsList() {
		// TODO Auto-generated method stub
		return this.rslist;
	}
	@Override
	public void setRsList(ResultSet _listHD) {
		// TODO Auto-generated method stub
		this.rslist = _listHD;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.id = _id;
	}

	@Override
	public ResultSet getRsHoso() {
		// TODO Auto-generated method stub
		return this.rsHoso;
	}

	@Override
	public void setRsHoso(ResultSet _rsHoso) {
		// TODO Auto-generated method stub
		this.rsHoso = _rsHoso;
	}

	@Override
	public void init(String _str) {
		// TODO Auto-generated method stub
		String query = "select HD.PK_SEQ as MAHD, HD.SOHOPDONG as SOHD, HD.HOSO_FK as MAHOSO, "
						+ "TV.PK_SEQ as MATV, TV.NHANXET, TV.QUOCGIA_FK as MAQUOCGIA, "
						+ "QG.TEN as TENQUOCGIA, HD.TRANGTHAI as TRANGTHAIHOPDONG "
					+"from HOPDONG HD INNER JOIN  "
						+"HOSO ON HOSO.PK_SEQ = HD.HOSO_FK INNER JOIN  "
						+"TUVAN TV ON TV.HOSO_FK = HOSO.PK_SEQ LEFT JOIN  "
						+"QUOCGIA QG ON QG.PK_SEQ = TV.QUOCGIA_FK  "
					+ "where 1=1 ";
		if(_str.equals("")==false){
			query = _str;
		}
		this.rslist=db.get(query);
		CreateRs();
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		// load danh sách vào comboBox ....
		String queryHoso = "SELECT HOSO.PK_SEQ as MAHOSO, TEN FROM HOSO";
		this.rsHoso=db.get(queryHoso);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public boolean Delete() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean Chot() {
		// TODO Auto-generated method stub
		return false;
	}

}
