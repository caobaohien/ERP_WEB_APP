package geso.erp.beans.donbanhang_duongnguyen;

public interface ISanPham {
	public String getBanHangId();

	public void setBanHangId(String banHang_fk);

	public String getId();

	public void setId(String id);

	public String getMaSP();

	public void setMaSP(String maSP);

	public String getTenSP();

	public void setTenSP(String tenSP);

	public String getDVT();

	public void setDVT(String dvt);

	public double getSoLuong();

	public void setSoLuong(double soLuong);

	public double getDonGia();

	public void setDonGia(double donGia);

	public double getThanhTien();

	public void setThanhTien(double thanhTien);

}
