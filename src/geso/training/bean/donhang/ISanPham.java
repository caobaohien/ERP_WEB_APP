package geso.training.bean.donhang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ISanPham extends Serializable {
	public String getPK_SEQ();
	public void setPK_SEQ(String pk_seq);
	public String getMaSP();
	public void setMaSP(String MaSP);
	public String getTenSP();
	public void setTenSP(String TenSP);
	public String getDVT();
	public void setDVT(String DVT);
	public int getSoLuong();
	public void setSoLuong(int SoLuong);
	public double getDonGia();
	public void setDonGia(double DonGia);
	public double getThanhTien();
	public void setThanhTien(double ThanhTien);
	public double getChietkhau();
	public void setChietkhau(double chietkhau);
	public ResultSet getResultSet();
}
