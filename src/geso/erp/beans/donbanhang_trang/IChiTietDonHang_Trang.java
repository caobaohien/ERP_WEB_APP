package geso.erp.beans.donbanhang_trang;

import java.util.List;

public interface IChiTietDonHang_Trang
{
	//PK_SEQ (tính chất tự động tăng) của Table BANHANG
	public String getBanHangPK_SEQ();
	public void setBanHangPK_SEQ(String banHangPK_SEQ);

	public String getSanPhamPK_SEQ();
	public void setSanPhamPK_SEQ(String sanPhamPK_SEQ);
	public String getMaSanPham();
	public void setMaSanPham(String maSanPham);

	public String getTenSanPham();
	public void setTenSanPham(String tenSanPham);

	public String getDonViTinh();
	public void setDonViTinh(String donViTinh);

	public double getSoLuong();
	public void setSoLuong(double soLuong);

	public double getDonGia();
	public void setDonGia(double donGia);

	public double getThanhTien();
	public void setThanhTien(double thanhTien);
	
	public double getSoLuongTon();
	public void setSoLuongTon(double soLuongTon);
}
