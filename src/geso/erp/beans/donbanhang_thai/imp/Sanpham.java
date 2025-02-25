package geso.erp.beans.donbanhang_thai.imp;

import geso.erp.beans.donbanhang_thai.ISanpham;

public class Sanpham  implements ISanpham{
	private static final long serialVersionUID = -9217977546733610214L;
	String DonhangId;
	String id;
	String masp;
	String tensp;
	String tenXHD;
	double soluong;
	 
	String donvitinh;
	double dongia;
	double thanhtien;
 
	 

	/* 31-08-2012 */
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
 
 

 

	public String getTenXHD() 
	{
		return this.tenXHD;
	}

	public void setTenXHD(String tenXHD) 
	{
		this.tenXHD = tenXHD;
	}

	@Override
	public double getSoluong() {
		// TODO Auto-generated method stub
		return this.soluong;
	}

	@Override
	public void setSoluong(double soluong) {
		// TODO Auto-generated method stub
		this.soluong=soluong;
	}

	@Override
	public double getDongia() {
		// TODO Auto-generated method stub
		return this.dongia;
	}

	@Override
	public void setDongia(double dongia) {
		// TODO Auto-generated method stub
		this.dongia=dongia;
	}

 

	@Override
	public double getThanhtien() {
		// TODO Auto-generated method stub
		return this.thanhtien;
	}

	@Override
	public void setThanhtien(double thanhtien) {
		// TODO Auto-generated method stub
		this.thanhtien=thanhtien;
	}

	@Override
	public String getBanhangId() {
		// TODO Auto-generated method stub
		return DonhangId;
	}

	@Override
	public void setBanhangId(String banhang_fk) {
		// TODO Auto-generated method stub
		DonhangId=banhang_fk;
	}

	 
   
}
