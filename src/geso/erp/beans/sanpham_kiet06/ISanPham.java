package geso.erp.beans.sanpham_kiet06;

import java.sql.ResultSet;

public interface ISanPham {

	public String getPk_seq();
	public void setPk_seq(String pk);
	
	public String getMa();
	public void setMa(String ma);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getTrangThai();
	public void setTrangThai(String tt);
	
	public String getDonViTinh();
	public void setDonViTinh(String dvt);
	
	public ResultSet getRsDonViTinh();
	public void setRsDonViTinh(ResultSet rs);
	//-----------------------------------------
	public String getUserId();
	public void setUserId(String userId);
	
	public String getMsg();
	public void setMsg(String msg);
	//-----------------------------------------
	public void init();
	public void createRsDvt();
	public boolean save();
	public boolean update();
	public void dbClose();
	
}
