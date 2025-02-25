package geso.erp.beans.sanpham_duongnguyen;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface ISanPhamList extends Serializable, IPhan_Trang {
	public void setMa(String ma);

	public String getMa();

	public void setTen(String ten);

	public String getTen();

	public void setTrangThai(String trangThai);

	public String getTrangThai();

	public void setRsData(ResultSet rs);

	public ResultSet getRsData();

	public void setUserId(String userId);

	public String getUserId();

	public void setMsg(String msg);

	public String getMsg();

	public void setDonViTinh(String donViTinh);

	public String getDonViTinh();

	public void init(String search);

	public void CreateRsDonVi();

	public ResultSet getRsDonVi();

	public void DBclose();

}
