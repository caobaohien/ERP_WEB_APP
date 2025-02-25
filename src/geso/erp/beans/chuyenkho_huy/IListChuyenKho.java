package geso.erp.beans.chuyenkho_huy;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IListChuyenKho extends Serializable,IPhan_Trang{
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String Id);
	public String getTrangthai();
	public void setPK_SEQ(String pk_seq);
	public String getPK_SEQ();
	public void setTrangthai(String trangthai);
	public String getKhochuyen();
	public void setKhochuyen(String khochuyen);
	public String getKhonhan();
	public void setKhonhan(String khonhan);
	public String getTenkho();
	public void setTenkho(String tenkho);
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet rslistchuyenkho);
	public ResultSet getDataListKhochuyen();
	public void setDataListKhochuyen(ResultSet rslistkhochuyen);
	public ResultSet getDataListKhonhan();
	public void setDataListKhonhan(ResultSet rslistkhonhan);
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean save();
	
	
}
