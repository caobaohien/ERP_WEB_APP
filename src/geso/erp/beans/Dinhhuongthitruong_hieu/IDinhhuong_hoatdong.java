package geso.erp.beans.Dinhhuongthitruong_hieu;

public interface IDinhhuong_hoatdong {
	
	public String getPK_SEQ();
	public void setPK_SEQ(String pk_seq);
	
	public String getId();

	public void setId(String id);
	public String getMa();

	public void setMa(String ma);

	public String getTenhoatdong();

	public void setTenhoatdong(String _tenhoatdong);
	
	public String getMota();

	public void setMota(String _mota);
	
	 
	public float getKinhphi();

	public void setKinhphi(float _kinhphi);
	
	
	
	public int getSoluongkh();

	public void setSoluongkh(int _soluongkh);
	 
	// Them Trung tam chi phi,Thue Nhap Khau
	
	public int getSoluonghd();

	public void setSoluonghd(int _soluonghd);
	

	public String getTungay();

	public void setTungay(String _tungay);
	
	public String getDenngay();

	public void setDenngay(String _denngay);


 
}
