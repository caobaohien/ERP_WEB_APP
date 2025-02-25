package geso.erp.beans.muahang_huy;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IChiTietMuaHang extends Serializable,IPhan_Trang{
	public void setnguongoc(String nguongoc);
	public String getnguongoc();
	public void setsoluongnhan(float slnhan);
	public float getsoluongnhan();
	public void setngaynhan(String ngaynhan);
	public String getngaynhan();
	public String getmamh();
	public void setmamh(String mamh);
	public String getUserId();
	public void setUserId(String userId);
 	public String getId();
	public void setId(String Id);
	public String getmasp();
	public void setmasp(String masp);
	public void setmsg(String msg);
	public String getmsg();
	 
}
