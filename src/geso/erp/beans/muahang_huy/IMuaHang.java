package geso.erp.beans.muahang_huy;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IMuaHang extends Serializable,IPhan_Trang{

	public void settongtien(float tongtien);
	public float gettongtien();
	public void setngay(String ngay);
	public String getngay();
	public String gettenncc();
	public void settenncc(String tenncc);
	public String getmamh();
	public void setmamh(String mamh);
	public String getUserId();
	public void setUserId(String userId);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
 	public String getId();
	public void setId(String Id);
	public String getMaNCC();
	public void setMaNCC(String Ma);
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet rs);
	public ResultSet getDataListNCC();
	public void setDataListNCC(ResultSet rsncc);
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean delete_dhmua();
	public boolean chot_dhmua();
}
