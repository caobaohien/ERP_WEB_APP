package geso.erp.beans.kho_vy;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface IKho_Vy extends Serializable{
	public String getPK_SEQ();
	void setPK_SEQ(String PK_SEQ);
	
	public String getMaKho();
	public void setMaKho(String maKho);
	
	public String getTenKho();
	public void setTenKho(String tenKho);
	
	public String getDiaChi();
	public void setDiaChi(String diaChi);
	
	public String getNguoiTao();
	public void setNguoiTao(String nguoiTao);
	
	public String getNguoiSua();
	public void setNguoiSua(String nguoiSua);
	
	public List<ISanPham_Vy> getDanhSachSanPham();
	public void setDanhSachSanPham(List<ISanPham_Vy> danhSachSanPham);
	public IKho_Vy loadThongTinKHO_SANPHAM();
	public int  timSanPham(String PK_SEQ);
	
	public String getNgayTao();
	//public String setNgayTao();
	
	public String getNgaySua();
	//public String setNgaySua();
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	public int getSoLuongBooked(ISanPham_Vy sanPham);
	public int getSoLuongAvailable(ISanPham_Vy sanPham);
	public boolean checkRangBuocSoLuongTonTrongKho(int booked, int available,
				ISanPham_Vy sanPham);
	
	boolean ghiNhanPhieuChuyenKho(ISanPham_Vy sanPham);
	boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham);
	boolean khoiPhucThongSo(ISanPham_Vy sanPhamCu);
	boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham, int booked,
			int available);
	boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham, int booked,
			int available, int soLuongTon);
	int getSoLuongTon(ISanPham_Vy sanPham);
	
/*	public ResultSet getResultSetKho();
	public void setResultSetKho(ResultSet resultSet);
	public void createResultSetKho();*/
}
