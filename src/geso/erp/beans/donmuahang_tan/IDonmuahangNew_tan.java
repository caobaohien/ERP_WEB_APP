package geso.erp.beans.donmuahang_tan;

import java.sql.ResultSet;
import java.util.List;

public interface IDonmuahangNew_tan {
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String _id);
 
	public String getMamuahang();
	public void setMamuahang(String _ma);
	
	public String getNgaychungtu();
	public void setNgaychungtu(String _ngaychungtu);
	
	public String getNhacungcap();
	public void setNhacungcap(String _nhacungcap);
	
	public double getTongtien();
	public void setTongtien(double _tongtien);
	
	public String getNguoitao();
	public void setNguoitao(String _nguoitao);
	
	public String getNgaytao();
	public void setNgaytao(String _ngaytao);
 	 
	public String getIsCapNhat();
	public void setIsCapNhat(String _isCapNhat);
	public void setMsg(String _msg);
	public String getMsg();
	public ResultSet getDataList();
	public void setDataList(ResultSet _dmhlist);
	public ResultSet getRsncc();
	public void setRsncc(ResultSet dvt);
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();
	public boolean Display_dmh();
	
	public List<ISanpham_tan> getListSp();
	public void setListSp(List<ISanpham_tan> list);
	
}
