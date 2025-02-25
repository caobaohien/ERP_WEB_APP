package geso.erp.beans.donbanhang_thai;

public interface ISanpham {
	//Khoa chinh(tu dong tang) cua bang Erp_MuaHang_SP
	public String getBanhangId();
	public void setBanhangId(String banhang_fk);
	
	public String getId();//PK_SEQ CUA BANG SANPHAM

	public void setId(String id);

	public String getMasanpham();

	public void setMasanpham(String masp);

	public String getTensanpham();

	public void setTensanpham(String tensp);
	
	 
	public double getSoluong();

	public void setSoluong(double soluong);
	
	 
	 
	 
	// Them Trung tam chi phi,Thue Nhap Khau

	public String getDonvitinh();

	public void setDonvitinh(String donvitinh);

	public double getDongia();

	public void setDongia(  double dongia);
	
 

	public double getThanhtien();

	public void setThanhtien(double thanhtien);

 
}
