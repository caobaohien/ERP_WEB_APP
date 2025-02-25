package geso.erp.beans.chuyenkho_vy.imp;

import geso.erp.beans.chuyenkho_vy.IChiTietPhieuChuyenKho_Vy;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;

public class ChiTietPhieuChuyenKho_Vy implements IChiTietPhieuChuyenKho_Vy{
	private IPhieuChuyenKho_Vy phieuChuyenKho;
	private ISanPham_Vy sanPham;
	private int soLuong;
	
	public ChiTietPhieuChuyenKho_Vy() {
		super();
	}
	public IPhieuChuyenKho_Vy getPhieuChuyenKho() {
		return phieuChuyenKho;
	}
	public void setPhieuChuyenKho(IPhieuChuyenKho_Vy phieuChuyenKho) {
		this.phieuChuyenKho = phieuChuyenKho;
	}
	public ISanPham_Vy getSanPham() {
		return sanPham;
	}
	public void setSanPham(ISanPham_Vy sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
