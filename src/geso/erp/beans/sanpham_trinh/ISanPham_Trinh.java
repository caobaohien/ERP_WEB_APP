package geso.erp.beans.sanpham_trinh;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISanPham_Trinh {
	
	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);
	
	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);
	
	public String getMaDonVi();
	public void setMaDonVi(String maDonVi);
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	public String getMessage();
	public void setMessage(String message);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public dbutils getDb();
	public void setDb(dbutils db);
	
	public ResultSet getRsSanPham();
	public void setRsSanPham(ResultSet rsSanPham);
	
	public ResultSet getRsDonVi();
	public void setRsDonVi(ResultSet rsDonVi);
	
	public double getGiaBan();
	public void setGiaBan(double giaBan);
	
	public Double getSoLuong();
	public void setSoLuong(Double soLuong);
	
	public String getMaSanPhamAo();
	public void setMaSanPhamAo(String maSanPhamAo);
	
	//hàm close db
	public void closeDb();
	
	//hàm khởi tạo
	public void init(String chuoi);
	
	//hàm lấy đơn vị
	public void layDonVi();
	
	//hàm save giá trị
	public boolean save();
	public boolean DeleteSP();
	
}
