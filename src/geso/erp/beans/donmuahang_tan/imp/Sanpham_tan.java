package geso.erp.beans.donmuahang_tan.imp;

import geso.erp.beans.donmuahang_tan.ISanpham_tan;

public class Sanpham_tan implements ISanpham_tan{
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
	public Sanpham_tan(){
		
	}
	@Override
	public String getId(){
		return this.id;
	}
	@Override
	public void setId(String id)	{
		this.id = id;
	}

	@Override
	public String getMasanpham()	{
		return this.masp;
	}
	@Override
	public void setMasanpham(String masp){
		this.masp = masp;
	}

	@Override
	public String getTensanpham()
	{
		return this.tensp;
	}
	@Override
	public void setTensanpham(String tensp)
	{
		this.tensp = tensp;
	}
	
	@Override
	public String getDonvitinh()
	{
		return this.donvitinh;
	}
	@Override
	public void setDonvitinh(String donvitinh)
	{
		this.donvitinh = donvitinh;
	}
	
	@Override
	public String getPK_SEQ()
	{
		return this.PK_SEQ;
	}
	@Override
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
