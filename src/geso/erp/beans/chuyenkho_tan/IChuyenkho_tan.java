package geso.erp.beans.chuyenkho_tan;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IChuyenkho_tan extends Serializable, IPhan_Trang{
	public String getUserId();
	public void setUserId(String _userId);
	
	public String get_pk_chuyenkho();
	public void set_pk_chuyenkho(String _maCK);
	
	public String getKhochuyen();
	public void setKhochuyen(String _khochuyen);
	public String getKhonhan();
	public void setKhonhan(String _khonhan);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
	
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
	public String getNgaysua();
	public void setNgaysua(String _ngaysua);
	
	public String getLydo();
	public void setLydo(String _lydo);
	
	public String getMsg();
	public void setMsg(String _msg);
	
	//danh sách chuyển kho
	public ResultSet getRsList();
	public void setRsList(ResultSet _listCK);
	 
	public String getId();
	public void setId(String _id);
	
	public ResultSet getRsKhochuyen();
	public void setRsKhochuyen(ResultSet _rsKhochuyen);
	public ResultSet getRsKhonhan();
	public void setRsKhonhan(ResultSet _rsKhonhan);
	 	
	public void init(String _str);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_Chuyenkho();
	public boolean Chot_Chuyenkho();
}
