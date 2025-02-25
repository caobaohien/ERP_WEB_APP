package geso.erp.beans.chuyenkho_thai;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IChuyenkho extends IPhan_Trang, Serializable{
 	public void setMa( String Ma);
	public String getMa();
	
	public void setKhochuyen(String Khochuyen);
	public String getKhochuyen();
	
	public void setKhonhan(String Khonhan);
	public String getKhonhan();

	public void setTrangthai(String Trangthai);
	public String getTrangthai();
	
	public void setUserID(String UserID);
	public String getUserID();
	
	public void setDataList(ResultSet rs);
	public ResultSet getDataList();
	
	public void setmsg(String msg);
	public String getmsg();
	
	public String getNguoitao();
	public void setNguoitao(String Nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String Ngaytao);
	
	public String getNguoisua();
	public void setNguoisua(String Nguoisua);
	
	public String getNgaysua();
	public void setNgaysua(String Ngaysua);
	
	public String getId();
	public void setId(String Id);
	
	public ResultSet getListKC();
	public void setListKC(ResultSet rskc);
	
	public ResultSet getListKN();
	public void setListKN(ResultSet rskn);
	
	public String getNgaychuyen();
	public void setNgaychuyen(String Ngaychuyen);
	
	public String getLydochuyen();
	public void setLydochuyen(String Lydochuyen);
	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public ResultSet rstrangthai();
	boolean update();
	boolean save();
}
