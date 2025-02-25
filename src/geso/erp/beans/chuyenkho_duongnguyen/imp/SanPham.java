package geso.erp.beans.chuyenkho_duongnguyen.imp;

import geso.erp.beans.chuyenkho_duongnguyen.ISanPham;

public class SanPham implements ISanPham{
	private String chuyenKho_fk;
	private String id;
	private String maSP;
	private String tenSP;
	private String dvt;
	private double sLTon;
	private double sLChuyen;

	@Override
	public String getChuyenKhoId() {
		// TODO Auto-generated method stub
		return this.chuyenKho_fk;
	}

	@Override
	public void setChuyenKhoId(String chuyenKho_fk) {
		// TODO Auto-generated method stub
		this.chuyenKho_fk = chuyenKho_fk;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getMaSP() {
		// TODO Auto-generated method stub
		return this.maSP;
	}

	@Override
	public void setMaSP(String maSP) {
		// TODO Auto-generated method stub
		this.maSP = maSP;
	}

	@Override
	public String getTenSP() {
		// TODO Auto-generated method stub
		return this.tenSP;
	}

	@Override
	public void setTenSP(String tenSP) {
		// TODO Auto-generated method stub
		this.tenSP = tenSP;
	}

	@Override
	public String getDVT() {
		// TODO Auto-generated method stub
		return this.dvt;
	}

	@Override
	public void setDVT(String dvt) {
		// TODO Auto-generated method stub
		this.dvt = dvt;
	}

	@Override
	public double getSLTon() {
		// TODO Auto-generated method stub
		return this.sLTon;
	}

	@Override
	public void setSLTon(double sLTon) {
		// TODO Auto-generated method stub
		this.sLTon = sLTon;
	}

	@Override
	public double getSLChuyen() {
		// TODO Auto-generated method stub
		return this.sLChuyen;
	}

	@Override
	public void setSLChuyen(double sLChuyen) {
		// TODO Auto-generated method stub
		this.sLChuyen = sLChuyen;
	}
}
