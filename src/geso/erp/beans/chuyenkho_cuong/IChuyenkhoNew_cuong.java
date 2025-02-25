package geso.erp.beans.chuyenkho_cuong;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IChuyenkhoNew_cuong extends Serializable,IPhan_Trang{

	public void setId(String id);
	public String getId();
	public void setMa(String ma);
	public String getMa();
	public void setKhoChuyen(String khochuyen);
	public String getKhoChuyen();
	public void setLyDo(String lydo);
	public String getLyDo();
	public void setKhoNhan(String khonhan);
	public String getKhoNhan();
	public void setTrangThai(String trangthai);
	public String getTrangThai();
	public void setNgayChuyen(String ngaychuyen);
	public String getNgayChuyen();
	public void setRsData(ResultSet rsData);
	public ResultSet getRsData();

	public void setUserId(String userId);

	public String getUserId();

	public void setMsg(String msg);

	public String getMsg();

	public void init(String search);

	public void CreateRsKhoNhan();

	public ResultSet getRsKhoNhan();
	public void CreateRsKhoChuyen();

	public ResultSet getRsKhoChuyen();

	public void DBclose();
	public boolean save();
	public boolean update();

}
