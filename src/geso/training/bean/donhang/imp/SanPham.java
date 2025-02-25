package geso.training.bean.donhang.imp;

import java.sql.ResultSet;

import geso.training.bean.donhang.ISanPham;
public class SanPham implements ISanPham {
	static final long serialVersionUID = -9217977546733610214L;
	String MaSP;
	String TenSP;
	String DVT;
	String PK_SEQ;
	int SoLuong;
	double DonGia;
	double ThanhTien;
	double chietkhau;
	private ResultSet resultset;
	public SanPham(){
		this.PK_SEQ="";
		this.MaSP="";
		this.TenSP="";
		this.DVT="";
		this.SoLuong=0;
		this.DonGia=0.0;
		this.ThanhTien=0.0;
		this.chietkhau=0.0;
	}
	public SanPham(String PK_SEQsp, String MaSP, String TenSP, String DVT,
			int SoLuong, double DonGia, double chietkhau, double ThanhTien){
		super();
		this.PK_SEQ = PK_SEQsp;
		this.MaSP = MaSP;
		this.TenSP = TenSP;
		this.DVT = DVT;
		this.SoLuong = SoLuong;
		this.DonGia = DonGia;
		this.chietkhau = chietkhau;
		this.ThanhTien = ThanhTien;
	}
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	public void setPK_SEQ(String pk_seq) {
		this.PK_SEQ = pk_seq;
	}
	public String getMaSP(){
		return MaSP;
	}
	public void setMaSP(String MaSP){
		this.MaSP=MaSP;
	}
	public String getTenSP(){
		return TenSP;
	}
	public void setTenSP(String TenSP){
		this.TenSP=TenSP;
	}
	public String getDVT(){
		return DVT;
	}
	public void setDVT(String DVT){
		this.DVT=DVT;
	}
	public int getSoLuong(){
		return SoLuong;
	}
	public void setSoLuong(int SoLuong){
		this.SoLuong=SoLuong;
	}
	public double getDonGia(){
		return DonGia;
	}
	public void setDonGia(double DonGia){
		this.DonGia=DonGia;
	}
	public double getThanhTien(){
		return ThanhTien;
	}
	public void setThanhTien(double ThanhTien){
		this.ThanhTien=ThanhTien;
	}
	public double getChietkhau() {
		return chietkhau;
	}
	public void setChietkhau(double chietkhau) {
		this.chietkhau=chietkhau;
	}
	@Override
	public ResultSet getResultSet() {
		// TODO Auto-generated method stub
		return this.resultset;
	}
}
