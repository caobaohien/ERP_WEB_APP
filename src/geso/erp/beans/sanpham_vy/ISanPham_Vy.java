package geso.erp.beans.sanpham_vy;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanPham_Vy extends Serializable{
	
	public String getPk_seq();
	public void setPk_seq(String pk_seq);

	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);
	
	public String getmaDonViTinh();

	public void setmaDonViTinh(String maDonViTinh);

	public String getTenSanPham();

	public void setTenSanPham(String tenSanPham);

	public String getMaTrangThai();

	public void setMaTrangThai(String maTrangThai);

	public String getNguoiTao();

	public void setNguoiTao(String nguoiTao);

	public String getNgayTao();

	public void setNgayTao(String ngayTao);

	public int getSoLuong();

	public void setSoLuong(int soLuong);
	
	public float getGiaBan();

	public void setGiaBan(float giaBan);

	public float getChietKhau();

	public void setChietKhau(float chietKhau);
	
	public ResultSet getResultSet();
	
	public void init();
	public void createRSDonViTinh();
	public void DBclose();
	public boolean save();
}
