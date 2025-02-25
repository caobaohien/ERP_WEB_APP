package geso.erp.beans.chuyenkho_kiet02.imp;

import geso.erp.beans.chuyenkho_kiet02.ISanPham;

public class SanPham implements ISanPham {

	private static final long serialVersionUID = -9217977546733610214L;
	String Pk_seq, Id, Msg;
	String MaSanPham, TenSanPham, DonViTinh, TenXHD;
	double SoLuongTon, SoLuongChuyen;
	
	public SanPham(){
		Pk_seq = "";
		Id = "";
		Msg = "";
		MaSanPham = "";
		TenSanPham = "";
		DonViTinh = "";
		TenXHD = "";
		SoLuongTon = 0;
		SoLuongChuyen = 0;
	}
	
	@Override
	public String getPk_seq() {
		// TODO Auto-generated method stub
		return this.Pk_seq;
	}

	@Override
	public void setPk_seq(String pk_seq) {
		// TODO Auto-generated method stub
		this.Pk_seq = pk_seq;
	}

	@Override
	public String getMaSanPham() {
		// TODO Auto-generated method stub
		return this.MaSanPham;
	}

	@Override
	public void setMaSanPham(String maSP) {
		// TODO Auto-generated method stub
		this.MaSanPham = maSP;
	}

	@Override
	public String getTenSanPham() {
		// TODO Auto-generated method stub
		return this.TenSanPham;
	}

	@Override
	public void setTenSanPham(String tenSP) {
		// TODO Auto-generated method stub
		this.TenSanPham = tenSP;
	}

	@Override
	public String getDonViTinh() {
		// TODO Auto-generated method stub
		return this.DonViTinh;
	}

	@Override
	public void setDonViTinh(String dvt) {
		// TODO Auto-generated method stub
		this.DonViTinh = dvt;
	}

	@Override
	public double getSoLuongTon() {
		// TODO Auto-generated method stub
		return this.SoLuongTon;
	}

	@Override
	public void setSoLuongTon(double slt) {
		// TODO Auto-generated method stub
		this.SoLuongTon = slt;
	}

	@Override
	public double getSoLuongChuyen() {
		// TODO Auto-generated method stub
		return this.SoLuongChuyen;
	}

	@Override
	public void setSoLuongChuyen(double slc) {
		// TODO Auto-generated method stub
		this.SoLuongChuyen = slc;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.Id = id;
	}

	public String getTenXHD(){
		return this.TenXHD;
	}
	
	public void setTenXHD(String tenXHD){
		this.TenXHD = tenXHD;
	}
}
