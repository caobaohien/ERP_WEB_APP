package geso.erp.beans.donbanhang_thai;

import java.sql.ResultSet;
import java.util.List;

public interface IDonbanhang {
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String id);
 
	
	public String getIsCapNhat() ;
	public void setIsCapNhat(String iscapnhat);
	
	public String getMa();
	public void setMa(String _ma);
	
	public String getNgaychungtu();
	public void setNgaychungtu(String _ngaychungtu);
	
	public String getKhachhang();
	public void setKhachhang(String _khachhang);
	
	public String getTongtien();
	public void setTongtien(String _tongtien);
	
	public String getNguoitao();
	public void setNguoitao(String _nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
 	 
	public void setmsg(String _msg);
	public String getmsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet _dmhlist);
	public ResultSet getRskh();
	public void setRskh(ResultSet dvt);
	
	
	public List<ISanpham> getListSp();
	public void setListSp(List<ISanpham> list);
	
	
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean update();
}
