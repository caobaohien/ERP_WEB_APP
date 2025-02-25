package geso.erp.beans.donbanhang_duongnguyen;

import java.sql.ResultSet;
import java.util.List;

public interface IDonHang {
	
	public void setId(String id);
	
	public String getId();
	
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
	
	public List<ISanPham> getListSP();
	public void setListSP(List<ISanPham> listSP);

	public void init();

	public void CreateRsKhachHang();

	public ResultSet getRsKhachHang();
	
	public boolean save();
	
	public boolean update();

	public void DBclose();
}
