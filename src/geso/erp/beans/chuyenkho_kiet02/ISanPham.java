package geso.erp.beans.chuyenkho_kiet02;

public interface ISanPham {

	//id DOn Hang
	public String getId();
	public void setId(String id);
	
	//Pk identity
	public String getPk_seq();
	public void setPk_seq(String pk_seq);
	
	public String getMaSanPham();
	public void setMaSanPham(String maSP);
	
	public String getTenSanPham();
	public void setTenSanPham(String tenSP);
	
	public String getDonViTinh();
	public void setDonViTinh(String dvt);
	
	public double getSoLuongTon();
	public void setSoLuongTon(double slt);
	
	public double getSoLuongChuyen();
	public void setSoLuongChuyen(double slc);
}
