package geso.erp.beans.donbanhang_duongnguyen;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IDonHangList extends Serializable, IPhan_Trang {
	
	public void setMa(String ma);

	public String getMa();

	public void setNgayCT(String ngay);

	public String getNgayCT();

	public void setTenKH(String tenKH);
	
	public String getTenKH();

	public void setTrangThai(String trangThai);

	public String getTrangThai();

	public void setTongTien(float tongTien);
	
	public float getTongTien();
	
	public void setNgayTao(String ngayTao);
	
	public String getNgayTao();
	
	public void setNguoiTao(String nguoiTao);
	
	public String getNguoiTao();
	
	public void setRsData(ResultSet rsData);

	public ResultSet getRsData();

	public void setUserId(String userId);

	public String getUserId();

	public void setMsg(String msg);

	public String getMsg();

	public void init(String search);

	public void CreateRsKhachHang();

	public ResultSet getRsKhachHang();

	public void DBclose();


}
