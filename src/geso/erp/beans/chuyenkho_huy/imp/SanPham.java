package geso.erp.beans.chuyenkho_huy.imp;
import geso.erp.beans.chuyenkho_huy.ISanPham;


public class SanPham implements ISanPham{
	String PK_SEQ;
	String id;
	String masp;
	String tensp;
	double soluong,booked;
	String donvitinh;
	public SanPham()
	{
		this.masp="";
		this.tensp="";
		this.soluong=0;
		this.donvitinh="";
	}
	
	@Override
	public String getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.PK_SEQ;
	}
	@Override
	public void setPK_SEQ(String pk_seq) {
		// TODO Auto-generated method stub
		this.PK_SEQ=pk_seq;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Override
	public String getMasanpham() {
		// TODO Auto-generated method stub
		return this.masp;
	}
	@Override
	public void setMasanpham(String masp) {
		// TODO Auto-generated method stub
		this.masp=masp;
	}
	@Override
	public String getTensanpham() {
		// TODO Auto-generated method stub
		return this.tensp;
	}
	@Override
	public void setTensanpham(String tensp) {
		// TODO Auto-generated method stub
		this.tensp=tensp;
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
	public String getDonvitinh() {
		// TODO Auto-generated method stub
		return this.donvitinh;
	}
	@Override
	public void setDonvitinh(String donvitinh) {
		// TODO Auto-generated method stub
		this.donvitinh=donvitinh;
	}

	@Override
	public double getBooked() {
		// TODO Auto-generated method stub
		return this.booked;
	}

	@Override
	public void setBooked(double booked) {
		// TODO Auto-generated method stub
		this.booked=booked;
	}
	
}
