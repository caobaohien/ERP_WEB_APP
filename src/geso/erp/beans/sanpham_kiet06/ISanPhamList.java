package geso.erp.beans.sanpham_kiet06;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanPhamList extends Serializable, IPhan_Trang {

	public String getMa();
	public void setMa(String ma);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getDonViTinh();
	public void setDonViTinh(String dvt);
	
	public String getTrangThai();
	public void setTrangThai(String tt);
	
	public ResultSet getRsList();
	public void setRsList(ResultSet rs);
	
	public ResultSet getRsDonVi();
	public void setRsDonVi(ResultSet rs);
	//------------------------------------
	public String getUserId();
	public void setUserId(String userId);
	
	public String getMsg();
	public void setMsg(String msg);
	//------------------------------------
	public void init(String search);
	public void createRsDvt();
	public void dbClose();
	
}
