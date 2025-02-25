package geso.erp.beans.chuyenkho_huy;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.beans.chuyenkho_huy.ISanPham;

public interface IChuyenKhoUpdate extends Serializable,IPhan_Trang {
	
	public void setNgayghinhan(String ngayghinhan);
	public String getNgayghinhan();
	public void setLydo(String lydo);
	public String getLydo();
	public void setTrangthai(String trangthai);
	public String getTrangthai();
	public String getPK_SEQ();
	public void setPK_SEQ(String pk_deq);
	public String getUserId();
	public void setUserId(String userId);
 	public String getId();
	public void setId(String Id);
	public void setmsg(String msg);
	public String getmsg();
	public String getKhochuyen();
	public void setKhochuyen(String khochuyen);
	public String getKhonhan();
	public void setKhonhan(String khonhan);
	public ResultSet getDataList();
	public void setDataList(ResultSet rslistchuyenkho);
	public ResultSet getDataListKhochuyen();
	public void setDataListKhochuyen(ResultSet rslistkhochuyen);
	public ResultSet getDataListKhonhan();
	public void setDataListKhonhan(ResultSet rslistkhonhan);
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean chot();
	public boolean edit();
	public boolean delete();
	public List<ISanPham> getListSp();
	public void setListSp(List<ISanPham> listsp);
}
