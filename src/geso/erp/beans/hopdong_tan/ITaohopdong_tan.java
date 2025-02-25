package geso.erp.beans.hopdong_tan;

import java.sql.ResultSet;

import geso.erp.db.sql.dbutils;

public interface ITaohopdong_tan {
	public String getUserId();
	public void setUserId(String _userId);
	public String getIdSohopdong();
	public void setIdSohopdong(String _idSohopdong);
	public String getHopdong_pk();
	public void setHopdong_pk(String _hopdong_pk);
	public String getNgaytuvan();
	public void setNgaytuvan(String _ngaytuvan);
	public String getHoso_fk();
	public void setHoso_fk(String _hoso_fk);
	public String getKyhopdong();
	public void setKyhopdong(String _kyhopdong);
	public String getSohopdong();
	public void setSohopdong(String _sohopdong);
	public String getQuocgia();
	public void setQuocgia(String _quocgia);
	public String getNgayky();
	public void setNgayky(String _ngayky);
	public String getNguoiky();
	public void setNguoiky(String _nguoiky);
	public double getGiatrihopdong();
	public void setGiatrihopdong(double _giatrihopdong);
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
	public String getNguoitao();
	public void setNguoitao(String _nguoitao);
	public String getLydo();
	public void setLydo(String _lydo);
	public String getNgaysua();
	public void setNgaysua(String _ngaysua);
	public String getNguoisua();
	public void setNguoisua(String _nguoisua);
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
	public String getNhanxet();
	public void setNhanxet(String _nhanxet);

	public String getIsCapNhat();
	public void setIsCapNhat(String _isCapnhat);
	public String getMsg();
	public void setMsg(String _msg);
	
	public ResultSet getRsHD();
	public void setRsHD(ResultSet _rsHD);
	public ResultSet getRsHS();
	public void setRsHS(ResultSet _rsHS);
	public ResultSet getRsQG();
	public void setRsQG(ResultSet _rsQG);
	
	public void init();
	public void createRs();
	public void dbClose();
	public boolean save();
	public boolean update();
	public boolean delete();
	
}
