package geso.erp.beans.donhang_huy.imp;

import java.util.ArrayList;
import java.util.List;

import geso.erp.beans.donhang_huy.ISanPham;
import geso.erp.beans.muahang_huy.IChiTietMuaHang;

public class SanPham implements ISanPham{
	String PK_SEQ;
	String id;
	String masp;
	double chietkhau;
	String tensp;
	double soluong;
	double tiente;
	String donvitinh;
	double dongia;
	double thanhtien;
	List<IChiTietMuaHang> list = new ArrayList<IChiTietMuaHang>();
	public SanPham()
	{
		this.masp="";
		this.tensp="";
		this.soluong=0;
		this.donvitinh="";
		this.thanhtien=0;
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
	public List<IChiTietMuaHang> getListCTMH() {
		// TODO Auto-generated method stub
		return list;
	}
	@Override
	public void setListCTMH(List<IChiTietMuaHang> listctmh) {
		// TODO Auto-generated method stub
		list=listctmh;
	}

	@Override
	public void setChietkhau(double chietkhau) {
		// TODO Auto-generated method stub
		this.chietkhau=chietkhau;
	}

	@Override
	public double getChietkhau() {
		// TODO Auto-generated method stub
		return this.chietkhau;
	}
}
