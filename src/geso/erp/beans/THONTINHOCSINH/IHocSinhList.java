package geso.erp.beans.THONTINHOCSINH;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IHocSinhList extends Serializable,IPhan_Trang{
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public String getMsg();
	public void setMsg(String msg);
	public void setId(String Id);
	public void setTrangthai(String trangthai);
	public String getTrangthai();
	public void setPK_SEQ(String pk_seq);
	public String getPK_SEQ();
	public void setHoten(String hoten);
	public String getHoten();
	public String getTenquocgia();
	public void settenquocgia(String tenqg);
	public void setNgaysinh(String ngaysinh);
	public String getNgaysinh();
	public void setTrinhdohocvan(String tdhocvan);
	public String getTrinhdohocvan();
	public void setKhananghocvan(String knhocvan);
	public String getKhananghocvan();
	public void setQuocgiahoc(String qghoc);
	public String getQuocgiahoc();
	public ResultSet getDataList();
	public void setDataList(ResultSet rslisths);
	public ResultSet getDataLisQuocgiat();
	public void setDataListQuocgia(ResultSet rslistqg);
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean delete_hs();
}
