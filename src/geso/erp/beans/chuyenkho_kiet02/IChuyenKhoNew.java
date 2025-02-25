package geso.erp.beans.chuyenkho_kiet02;

import geso.erp.beans.chuyenkho_kiet02.ISanPham;

import java.sql.ResultSet;
import java.util.List;

public interface IChuyenKhoNew {

	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String id);
	
	public String getIsUpdate();
	public void setIsUpdate(String isUpdate);
	
	public void setMsg(String msg);
	public String getMsg();
	
	public ResultSet getDataList();
	public void setDataList(ResultSet rs);
	
	public ResultSet getRsDvt();
	public void setRsDvt(ResultSet dvt);
	
	public ResultSet getRsKhoChuyen();
	public void setRsKhoChuyen(ResultSet rs);
	
	public ResultSet getRsKhoNhan();
	public void setRsKhoNhan(ResultSet rs);
	//---------------------------------------
	public String getMa();
	public void setMa(String ma);
	
	public String getNgayChuyenKho();
	public void setNgayChuyenKho(String ngaychuyenkho);
	
	public String getKhoChuyen();
	public void setKhoChuyen(String _khochuyen);
	
	public String getKhoNhan();
	public void setKhoNhan(String _khonhan);
	
	public String getTrangThai();
	public void setTrangThai(String trangthai);
	
	public String getLyDo();
	public void setLyDo(String lydo);
	
	public double getBooked();
	public void setBooked(double booked);
	
	public double getAvailabel();
	public void setAvailabe(double avai);

	public List<ISanPham> getListSp();
	public void setListSp(List<ISanPham> list);
	//---------------------------------------
	public void init();
	public void createRs();
	public void dbClose();
	public boolean save();
	public boolean update();
	public String getDateTime();
	
}
