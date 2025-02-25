package geso.erp.beans.muahang_hieu;

import geso.erp.beans.donbanhang_hieu.Isanpham;

import java.sql.ResultSet;
import java.util.List;

public interface IThongtinmuahang {
	public String getUserId();
	public void setUserId(String _userId);
	
	public String getTrangthai();
	public void setTrangthai(String _trangthai);
 	public String getId();
	public void setId(String _id);
 
	public String getMa();
	public void setMa(String _ma);
	
	public String getNgaychungtu();
	public void setNgaychungtu(String _ngaychungtu);
	
	public String getNhacungcap();
	public void setNhacungcap(String _nhacungcap);
	
	public String getTennhacungcap();
	public void setTennhacungcap(String _tennhacungcap);
	
	public String getDienthoainhacungcap();
	public void setDienthoainhacungcap(String _dienthoainhacungcap);
	
	public String getDiachinhacungcap();
	public void setDiachinhacungcap(String _diachinhacungcap);
	
	
	public double getTongtien();
	public void setTongtien(double _tongtien);
	
	public double getThanhtien();
	public void setThanhtien(double _thanhtien);
	
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
	public void init();
	public void CreateRs();
	public void DBclose();
	public boolean save();

	public List<Isanpham> getListSp();
	public void setListSp(List<Isanpham> list);
	public boolean update();
	public boolean saveNhacungcap();

}
