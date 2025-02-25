package geso.erp.beans.chuyenkho_tan;

import java.sql.ResultSet;
import java.util.List;

import geso.erp.beans.chuyenkho_tan.ISanpham_tan;

public interface IChuyenkhoNew_tan {
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getMaCK();
	public void setMaCK(String _MaCK);
	public String getSoluong();
	public void setSoluong(String _soluong);
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String _id);
	
	public String getNgayChuyenkho();
	public void setNgayChuyenkho(String _ngayghinhan);
	public String getLydo();
	public void setLydo(String _lydo);
	public String getKhochuyen();
	public void setKhochuyen(String _khochuyen);
	public String getKhonhan();
	public void setKhonhan(String _khonhan);
	
	public String getIsCapNhat();
	public void setIsCapNhat(String _isCapNhat);
	public void setMsg(String _msg);
	public String getMsg();
	
	public ResultSet getRsList();
	public void setRsList(ResultSet _CKlist);
	public ResultSet getRsKhochuyen();
	public void setRsKhochuyen(ResultSet _khochuyen);
	public ResultSet getRsKhonhan();
	public void setRsKhonhan(ResultSet _khonhan);
	
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean update();
	public boolean Delete_ck();
	
	public List<ISanpham_tan> getListSp();
	public void setListSp(List<ISanpham_tan> _list);
}
