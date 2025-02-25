package geso.erp.beans.banhang.imp;

import geso.erp.beans.banhang.ISanpham;
 
 
public class Sanpham implements ISanpham
{
	private static final long serialVersionUID = -9217977546733610214L;
	String PK_SEQ;
	String id;
	String masp;
	String tensp;
	String tenXHD;
	double soluong;
	double tiente;
	String donvitinh;
	double dongia;
	double thanhtien;
	double chietkhau;
 
	 

	/* 31-08-2012 */

 
	public String getId()
	{
		return this.id;
	}

	public Sanpham() {
		super();
		PK_SEQ = "";
		this.id = "";
		this.masp = "";
		this.tensp = "";
		this.tenXHD = "";
		this.soluong = 0;
		this.tiente = 0;
		this.donvitinh = "";
		this.dongia = 0;
		this.thanhtien = 0;
		this.chietkhau = 0;
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
	public double getChietkhau() {
		// TODO Auto-generated method stub
		return this.chietkhau;
	}

	@Override
	public void setChietkhau(double Chietkhau) {
		// TODO Auto-generated method stub
		this.chietkhau=Chietkhau;
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

	 
   
}
