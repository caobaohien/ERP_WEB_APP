package geso.erp.beans.donmuahang_tan;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.util.IPhan_Trang;

public interface IDonmuahangList_tan extends Serializable, IPhan_Trang {
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String Id);
	
	public String getMamuahang();
	public void setMamuahang(String mamuahang);
	
	public String getNgaychungtu();
	public void setNgaychungtu(String nhacungcap);
	
	public String getNhacungcap();
	public void setNhacungcap(String nhacungcap);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);

	public double getTongtien();
	public void setTongtien(double tongtien);
	
	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	
	public String getNguoitao();
	public void setNguoitao(String nguoitao);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public ResultSet getDataList();
	public void setDataList(ResultSet dmhlist);
	public ResultSet getListNCC();
	public void setListNCC(ResultSet dmhlist);
	
	public void init(String search);
	public void CreateRs();
	public void DBclose();
	public boolean Delete_dh();
	
}
