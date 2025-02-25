package geso.erp.beans.chuyenkho_duongnguyen;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IChuyenKhoList extends Serializable, IPhan_Trang {

	public String getMa();

	public void setMa(String ma);

	public String getNgayChuyenKho();

	public void setNgayChuyenKho(String ngayChuyenKho);

	public String getKhoNhan();

	public void setKhoNhan(String khoNhan);

	public String getKhoChuyen();

	public void setKhoChuyen(String khoChuyen);

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

	public String getUserId();

	public void setUserId(String userId);

	public String getMsg();

	public void setMsg(String msg);

	public ResultSet getRsKhoChuyen();

	public void CreateRsKhoChuyen();

	public ResultSet getRsKhoNhan();

	public void CreateRsKhoNhan();

	public ResultSet getRsData();

	public void init(String search);

	public void DBclose();

}
