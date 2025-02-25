package geso.erp.beans.THONTINHOCSINH;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IHoSoTuVanUpdate extends Serializable,IPhan_Trang {
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
	public void setMahoso(String mahoso);
	public String getMahoso();
	public void setDiengiai(String diengiai);
	public String getDiengiai();
	public String getTiemnang();
	public void setTiemnang(String tiemnang);
	public void setNhanxet(String nhanxet);
	public String getNhanxet();
	
	public String getMaquocgia();
	public void setMaquocgia(String maqg);
	public String getTenquocgia();
	public void settenquocgia(String tenqg);
	public ResultSet getDataLisQuocgiat();
	public void setDataListQuocgia(ResultSet rslistqg);
	public void setQuocgia_fk(String maquocgia);
	public String getQuocgia_fk();
	public void setNgaytuvan(String ngaytuvan);
	public String getNgaytuvan();
	public ResultSet getDataList();
	public void setDataList(ResultSet rslisths);
	public void init( );
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean edit_tuvan();
	public boolean delete_hs();
	public boolean savequocgia();
}
