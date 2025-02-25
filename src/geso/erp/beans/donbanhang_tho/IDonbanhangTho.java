package geso.erp.beans.donbanhang_tho;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDonbanhangTho extends Serializable, IPhan_Trang{
	public void setId(String id);
	public String getId();
	
	public void setuserId(String userid);
	public String getuserId();
	
	public void setmaDH(String madh);
	public String getmaDH();
	
	public void settenKH(String tenkh);
	public String gettenKH();
	
	public void setTrangthai(String trangthai);
	public String getTrangthai();
	
	public void setngayCT(String ngayct);
	public String getngayCT();
	
	public void setNguoitao(String nguoitao);
	public String getNguoitao();
	
	public void setNgaytao(String ngaytao);
	public String getNgaytao();
	
	public void setTongtien(String tongtien);
	public String getTongtien();
	
	public void setDatalist(ResultSet datalist);
	public ResultSet getDatalist();
	
	public void setRsKH(ResultSet rskh);
	public ResultSet getRsKH();
	
	public void setMsg(String msg);
	public String getMsg();
	
	public void init(String search);
	public boolean save();
	public boolean update();
	public void CreateRs();
	public void DBclose();
}
