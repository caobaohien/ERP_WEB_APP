package geso.erp.beans.sanpham_trang;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public interface ISanPhamList_Trang extends IPhan_Trang {
	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);
	
	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);
	
	public String getDonViTinh();
	public void setDonViTinh(String donViTinh);
	
	public dbutils getDb();
	public void setDb(dbutils db);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public ResultSet getRsDataList();
	public void setRsDataList(ResultSet rsDataList);
	
	public ResultSet getRsDonvi();
	public void setRsDonvi(ResultSet rsDonvi);	
	
	public String getUserID();
	public void setUserID(String userID);
	public void init(String query);
	public void closedb();
	public void getDataDonVi();
}
