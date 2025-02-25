package geso.erp.beans.sanpham_duongnguyen;

import java.sql.ResultSet;

public interface ISanPham {
	public String getUserId();

	public void setUserId(String userId);

	public String getTrangThai();

	public void setTrangThai(String trangThai);

	public String getMa();

	public void setMa(String ma);

	public String getId();

	public void setId(String id);

	public String getDonViTinh();

	public void setDonViTinh(String donViTinh);

	public ResultSet getRsDonViTinh();

	public void setRsDonViTinh(ResultSet rsDonViTinh);

	public String getTen();

	public void setTen(String ten);

	public String getMsg();

	public void setMsg(String msg);

	public void init();

	public void createRs();

	public void dbClose();

	public boolean save();

	public boolean update();
}
