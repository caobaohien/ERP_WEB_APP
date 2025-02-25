package geso.erp.beans.donbanhang_thu;

import geso.erp.db.sql.dbutils;

public interface IChiTietDonHang_Thu {

	public String getMaDonHang();

	public void setMaDonHang(String maDonHang);

	public String getMaSanPham();

	public void setMaSanPham(String maSanPham);

	public dbutils getBd();

	public void setBd(dbutils bd);

	public Float getDonGia();

	public void setDonGia(Float donGia);

	public int getSoLuong();

	public void setSoLuong(int soLuong);

	public Float getThanhTien();

	public void setThanhTien(Float thanhTien);

	public String getTenSanPham();

	public void setTenSanPham(String tenSanPham);

	public String getMaDonVi();

	public void setMaDonVi(String maDonVi);
	
	public String getTenDonVi();
	
	public void setTenDonVi(String tenDonVi) ;

	public String getPK_SEQ();

	public void setPK_SEQ(String pK_SEQ);

	public void init(String string);

	public void DBclose();

	public boolean insert();

	public boolean update();

}
