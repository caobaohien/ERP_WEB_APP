package geso.erp.beans.chuyenkho_tan.imp;

import geso.erp.beans.chuyenkho_tan.ISanpham_tan;

public class Sanpham_tan implements ISanpham_tan{
	private static final long serialVersionUID = -9217977546733610214L;
	String PK_SEQ;
	String masp;
	String tensp;
	String donvitinh;
	double soluongton;
	double soluongchuyen;
	
	/* 31-08-2012 */
	public Sanpham_tan(){
		
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

	@Override
	public double getSoluongTon() {
		// TODO Auto-generated method stub
		return this.soluongton;
	}

	@Override
	public void setSoluongTon(double soluong) {
		// TODO Auto-generated method stub
		this.soluongton=soluong;
	}

	@Override
	public double getSoluongChuyen() {
		// TODO Auto-generated method stub
		return this.soluongchuyen;
	}

	@Override
	public void setSoluongChuyen(double _soluong) {
		// TODO Auto-generated method stub
		this.soluongchuyen=_soluong;
	}

	 
}
