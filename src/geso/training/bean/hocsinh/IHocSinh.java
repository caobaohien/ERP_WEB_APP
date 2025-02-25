package geso.training.bean.hocsinh;

import java.sql.ResultSet;

public interface IHocSinh {
	public String getMaHS();
	public void setMaHS(String mahs);
	public String getTenHS();
	public void setTenHS(String tenhs);
	public String getMaLop();
	public void setMaLop(String malop);
	public void setUserId(String userId); 
	public String getUserName();
	public void setUserName(String userName);
	public ResultSet getRsLop();
	public void setRsLop(ResultSet rsLop);
	public ResultSet getRsHocSinhList();
	public void setRsHocSinhList(ResultSet rshocsinhlist);
	public boolean Save();
	public boolean Savesua();
	public boolean Xoa();
	public void init();
	public void creates();
	public void DBClose();
}
