package geso.erp.beans.sanpham_kiet05;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanphamList extends Serializable, IPhan_Trang{

	public String getMa();
	public void setMa(String ma);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public ResultSet getrsData();
	public void setRsdata(ResultSet rsData);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public String getDonvitinh();
	public void setDonvitinh(String donvitinh);
	
	public ResultSet getRsdonvi();
	
	public void init(String search);
	public void createRs();
	public void dbClose();
}
