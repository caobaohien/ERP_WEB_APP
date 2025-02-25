package geso.erp.beans.chuyenkho_hieu;

public interface ISanpham {
	//Khoa chinh(tu dong tang) cua bang Erp_MuaHang_SP
	public String getPK_SEQ();
	public void setPK_SEQ(String pk_seq);
	// id của đon hàng
	public String getId();

	public void setId(String id);

	public String getMasanpham();

	public void setMasanpham(String masp);

	public String getTensanpham();

	public void setTensanpham(String tensp);
	
	
	 
	public double getSoluongton();

	public void setSoluongton(double soluongton);
	
	
	
	public double getSoluongchuyen();

	public void setSoluongchuyen(double soluongtonchuyen);
	 
	// Them Trung tam chi phi,Thue Nhap Khau

	public String getDonvitinh();

	public void setDonvitinh(String donvitinh);

 
}
