package geso.erp.beans.taohopdong_tan.imp;

import java.sql.ResultSet;

import geso.erp.beans.taohopdong_tan.ITaohopdong_tan;
import geso.erp.db.sql.dbutils;

public class Taohopdong_tan implements ITaohopdong_tan{
	String userId;
	String idSohopdong;
	String hopdong_pk;
	String hoso_fk;
	String kyhopdong;
	String ngayky;
	String nguoiky;
	double giatrihopdong;
	String ngaytao;
	String nguoitao;
	String lydo;
	String ngaysua;
	String nguoisua;
	String trangthai;
	String nhanxet;

	String IsCapNhat;
	String msg;
	dbutils db=new dbutils();
	ResultSet rsHD;
	ResultSet rsHS;
	
	public Taohopdong_tan(String _str){
		this.userId = "";
		this.idSohopdong = _str;
		this.hopdong_pk = "";
		this.hoso_fk = "";
		this.kyhopdong = "1";
		this.ngayky = "";
		this.nguoiky = "";
		this.giatrihopdong = 0;
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.lydo = "";
		this.trangthai = "";
		this.nhanxet = "";
		this.IsCapNhat = "1";
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
	public String getIdSohopdong() {
		// TODO Auto-generated method stub
		return this.idSohopdong;
	}

	@Override
	public void setIdSohopdong(String _idSohopdong) {
		// TODO Auto-generated method stub
		this.idSohopdong = _idSohopdong;
	}

	@Override
	public String getHopdong_pk() {
		// TODO Auto-generated method stub
		return this.hopdong_pk;
	}

	@Override
	public void setHopdong_pk(String _hopdong_pk) {
		// TODO Auto-generated method stub
		this.hopdong_pk = _hopdong_pk;
	}

	@Override
	public String getHoso_fk() {
		// TODO Auto-generated method stub
		return this.hoso_fk;
	}

	@Override
	public void setHoso_fk(String _hoso_fk) {
		// TODO Auto-generated method stub
		this.hoso_fk = _hoso_fk;
	}

	@Override
	public String getKyhopdong() {
		// TODO Auto-generated method stub
		return this.kyhopdong;
	}

	@Override
	public void setKyhopdong(String _kyhopdong) {
		// TODO Auto-generated method stub
		this.kyhopdong = _kyhopdong;
	}

	@Override
	public String getNgayky() {
		// TODO Auto-generated method stub
		return this.ngayky;
	}

	@Override
	public void setNgayky(String _ngayky) {
		// TODO Auto-generated method stub
		this.ngayky = _ngayky;
	}

	@Override
	public String getNguoiky() {
		// TODO Auto-generated method stub
		return this.nguoiky;
	}

	@Override
	public void setNguoiky(String _nguoiky) {
		// TODO Auto-generated method stub
		this.nguoiky = _nguoiky;
	}

	@Override
	public double getGiatrihopdong() {
		// TODO Auto-generated method stub
		return this.giatrihopdong;
	}

	@Override
	public void setGiatrihopdong(double _giatrihopdong) {
		// TODO Auto-generated method stub
		this.giatrihopdong = _giatrihopdong;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}

	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao = _ngaytao;
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
	public String getLydo() {
		// TODO Auto-generated method stub
		return this.lydo;
	}

	@Override
	public void setLydo(String _lydo) {
		// TODO Auto-generated method stub
		this.lydo = _lydo;
	}

	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.ngaysua;
	}

	@Override
	public void setNgaysua(String _ngaysua) {
		// TODO Auto-generated method stub
		this.ngaysua = _ngaysua;
	}

	@Override
	public String getNguoisua() {
		// TODO Auto-generated method stub
		return this.nguoisua;
	}

	@Override
	public void setNguoisua(String _nguoisua) {
		// TODO Auto-generated method stub
		this.nguoisua = _nguoisua;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.trangthai = _trangthai;
	}

	@Override
	public String getNhanxet() {
		// TODO Auto-generated method stub
		return this.nhanxet;
	}

	@Override
	public void setNhanxet(String _nhanxet) {
		// TODO Auto-generated method stub
		this.nhanxet = _nhanxet;
	}
	
	@Override
	public String getIsCapNhat() {
		// TODO Auto-generated method stub
		return this.IsCapNhat;
	}

	@Override
	public void setIsCapNhat(String _isCapnhat) {
		// TODO Auto-generated method stub
		this.IsCapNhat = _isCapnhat;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void setMsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg = _msg;
	}

	@Override
	public ResultSet getRsHD() {
		// TODO Auto-generated method stub
		return this.rsHD;
	}

	@Override
	public void setRsHD(ResultSet _rsHD) {
		// TODO Auto-generated method stub
		this.rsHD = _rsHD;
	}

	@Override
	public ResultSet getRsHS() {
		// TODO Auto-generated method stub
		return this.rsHS;
	}

	@Override
	public void setRsHS(ResultSet _rsHS) {
		// TODO Auto-generated method stub
		this.rsHS = _rsHS;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
