package geso.erp.beans.chuyenkho_vy;

import geso.erp.beans.sanpham_vy.ISanPham_Vy;

public interface IChiTietPhieuChuyenKho_Vy {

	public IPhieuChuyenKho_Vy getPhieuChuyenKho();
	public void setPhieuChuyenKho(IPhieuChuyenKho_Vy phieuChuyenKho);
	public ISanPham_Vy getSanPham();
	public void setSanPham(ISanPham_Vy sanPham);
	public int getSoLuong();
	public void setSoLuong(int soLuong);
}
