package geso.erp.beans.chuyenkho_hieu.imp;

import geso.erp.beans.chuyenkho_hieu.ISanpham;

public class Sanpham implements ISanpham {
	private static final long serialVersionUID = -9217977546733610214L;
	String PK_SEQ;
	String id;
	String masp;
	String tensp;
	String tenXHD;
	double soluongchuyen;
	double soluongton;
	String donvitinh;
	public Sanpham()
	{
		 
	}
 
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMasanpham()
	{
		return this.masp;
	}

	public void setMasanpham(String masp)
	{
		this.masp = masp;
	}

	public String getTensanpham()
	{
		return this.tensp;
	}

	public void setTensanpham(String tensp)
	{
		this.tensp = tensp;
	}

	 

	 
	public String getDonvitinh()
	{
		return this.donvitinh;
	}

	public void setDonvitinh(String donvitinh)
	{
		this.donvitinh = donvitinh;
	}
 
 

	public String getPK_SEQ()
	{
		return this.PK_SEQ;
	}

	
	public void setPK_SEQ(String pk_seq)
	{
		this.PK_SEQ=pk_seq;
	}

	public String getTenXHD() 
	{
		return this.tenXHD;
	}

	public void setTenXHD(String tenXHD) 
	{
		this.tenXHD = tenXHD;
	}

	@Override
	public double getSoluongton() {
		// TODO Auto-generated method stub
		return this.soluongton;
	}

	@Override
	public void setSoluongton(double soluongton) {
		// TODO Auto-generated method stub
		this.soluongton=soluongton;
	}

	@Override
	public double getSoluongchuyen() {
		// TODO Auto-generated method stub
		return this.soluongchuyen;
	}

	@Override
	public void setSoluongchuyen(double soluongtonchuyen) {
		// TODO Auto-generated method stub
		this.soluongchuyen=soluongtonchuyen;
	}

}
