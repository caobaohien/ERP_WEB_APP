package geso.erp.beans.sanpham_nhom2;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanphamList  extends Serializable, IPhan_Trang {
	
	public void setMa(String ma);
	public String getMa();
	
	public void setTen(String ten);
	public String getTen();
	
	public void setTrangThai( String trangthai);
	public String getTrangthai();
	// lay du lieu 
	public void setRsData(ResultSet rs);
	public ResultSet getRsdata();
	
	public String getUserId();
	public void setUserId(String userId);

	public void setmsg(String msg);
	public String getmsg();
	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public String getDonvitinh();
	public void setDonvitinh(String donvitinh);
	
	public ResultSet getrsdonvi();
	
	
}
