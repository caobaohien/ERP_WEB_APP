package geso.erp.beans.sanpham_trinh;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanPhamList_Trinh extends Serializable, IPhan_Trang {
	
	public String getMaSnaPham();
	public void setMaSnaPham(String maSnaPham);
	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);
	public String getMaDonVi();
	public void setMaDonVi(String maDonVi);
	public String getTenDonVi();
	public void setTenDonVi(String tenDonVi);
	public dbutils getDb();
	public void setDb(dbutils db);
	public ResultSet getRsSanPham();
	public void setRsSanPham(ResultSet rsSanPham);
	public ResultSet getRsDonVi();
	public void setRsDonVi(ResultSet rsDonVi);
	public String getMessage();
	public void setMessage(String message);
	public String getUserId();
	public void setUserId(String userId);
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	
	
	
	public void init(String chuoi);
		
	//hàm lấy đơn vị
	public void layDonvi();
	public void closedb();
}
