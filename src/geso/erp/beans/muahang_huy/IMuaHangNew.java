package geso.erp.beans.muahang_huy;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.beans.donhang_huy.ISanPham;
import geso.erp.beans.nhacungcap.INhacungcap;

public interface IMuaHangNew extends Serializable,IPhan_Trang{
	public void setmamh(String mamh);
	public void setmancc(String mancc);
	public String getmancc();
	public void setngay(String ngay);
	public String getngay();
	public String getmamh();
	public float gettongtien();
	public void settongtien(float tongtien);
	public String getUserId();
	public void setUserId(String userId);
	public String gettenncc();
	public void settenncc(String tenncc);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
 	public String getId();
	public void setId(String Id);
	public void setmsg(String msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet rs);
	public ResultSet getDataListNCC();
	public void setDataListNCC(ResultSet rsncc);
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean savencc();
	public boolean edit();
	public List<ISanPham> getListSp();
	public void setListSp(List<ISanPham> listsp);
	
}
