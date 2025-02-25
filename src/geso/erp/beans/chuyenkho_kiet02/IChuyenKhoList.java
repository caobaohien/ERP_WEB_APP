package geso.erp.beans.chuyenkho_kiet02;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IChuyenKhoList extends Serializable, IPhan_Trang {

	public String getMa();
	public void setMa(String ma);
	
	public String getNgayChuyenKho();
	public void setNgayChuyenKho(String ngay);
	
	public String getKhoChuyen();
	public void setKhoChuyen(String khoChuyen);
	
	public String getKhoNhan();
	public void setKhoNhan(String khoNhan);
	
	public String getNguoiTao();
	public void setNguoiTao(String nguoiTao);
	
	public String getNguoiSua();
	public void setNguoiSua(String nguoiSua);
	
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	public String getNgayTao();
	public void setNgayTao(String ngayTao);
	
	public String getNgaySua();
	public void setNgaySua(String ngaySua);
	
	public String getLyDo();
	public void setLyDo(String lyDo);
	//-----------------------------------------
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String id);
	
	public String getMsg();
	public void setMsg(String msg);
	//-----------------------------------------
	
	public ResultSet getRsList();
	public void setRsList(ResultSet rs);
	
	public ResultSet getRsKhoChuyen();
	public void setRsKhoChuyen(ResultSet rs);

	public ResultSet getRsKhoNhan();
	public void setRsKhoNhan(ResultSet rs);
	//-----------------------------------------
	
	public void init(String search);
	public void createRs();
	public boolean deleteCK();
	public boolean finishCK();
	public void dbClose();
}
