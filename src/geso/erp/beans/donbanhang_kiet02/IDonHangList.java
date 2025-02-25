package geso.erp.beans.donbanhang_kiet02;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDonHangList extends Serializable, IPhan_Trang{
	
	public String getMaDonHang();
	public void setMaDonHang(String maDonHang);

	public String getNgayChungTu();
	public void setNgayChungTu(String ngayChungTu);

	public String getTrangThai();
	public void setTrangThai(String trangThai);

	public String getMaKhachHang();
	public void setMaKhachHang(String maKhachHang);

	public String getTaiKhoan();
	public void setTaiKhoan(String taiKhoan);
	
	public String getNgayTao();
	public void setNgayTao(String ngayTao);

	public String getNgaySua();
	public void setNgaySua(String ngaySua);

	public float getTongTien();
	public void setTongTien(float tongTien);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public ResultSet getDataList();
	public void setDataList(ResultSet dmhlist);
	
	public void init(String search);
	
	//Lay RS cua khach hang
	public void CreateRsKH();
	public ResultSet getListKhachHang();
	
	public void DBclose();
}
