package geso.erp.beans.sanpham_trang;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

public interface ISanPham_Trang {
	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);

	public String getDonViTinh();
	public void setDonViTinh(String donViTinh);

	public float getSoLuong();
	public void setSoLuong(float soLuong);

	public float getGiaBan();
	public void setGiaBan(float giaBan);

	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);

	public String getTrangThai();
	public void setTrangThai(String trangThai);

	public String getNgayTao();
	public void setNgayTao(String ngayTao);

	public String getNgaySua();
	public void setNgaySua(String ngaySua);

	public String getNguoiTao();
	public void setNguoiTao(String nguoiTao);

	public String getNguoiSua();
	public void setNguoiSua(String nguoiSua);

	public String getTimKiem();
	public void setTimKiem(String timKiem);

	public float getChietKhau();
	public void setChietKhau(float chietKhau);

	public String getId();
	public void setId(String id) ;

	public dbutils getDb();
	public void setDb(dbutils db);

	public String getMsg();
	public void setMsg(String msg);

	public ResultSet getRsDonViTinh();
	public void setRsDonViTinh(ResultSet rsDonViTinh);

	public ResultSet getRsDataList();
	public void setRsDataList(ResultSet rsDataList);
	
	
	public void closedb();
	public boolean saveSanPham();
	public boolean deteleSanPham();
	public boolean updateSanPham();
	public void init(String query);
	public void getDataDonVi();
	
}
