package geso.training.bean.hocsinh;

import java.sql.ResultSet;

public interface IHocSinhList {
	public String getMaHocSinh() ;
	public void setMaHocSinh(String maHocSinh);
	public String getTenHocSinh();
	public void setTenHocSinh(String tenHocSinh);
	public String getMaLop();
	public void setMaLop(String maLop);
	public String getUserId();
	public void setUserId(String userId);
	public String getUserName();
	public void setUserName(String userName);
	public ResultSet getRsLop();
	public void setRsLop(ResultSet rsLop);
	public ResultSet getRsHocSinhList();
	public void setRsHocSinhList(ResultSet rsHocSinhList);
	public void init();
	public void creates();
	//public boolean Save();
	public void DBClose();
}
