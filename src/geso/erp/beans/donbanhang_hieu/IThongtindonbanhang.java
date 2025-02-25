package geso.erp.beans.donbanhang_hieu;
 
import java.sql.ResultSet;
import java.util.List;

public interface IThongtindonbanhang {
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
	
	public String getKhachhang();
	public void setKhachhang(String _khachhang);
	
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
	
	//Them khac hang moi
	public String getTenkhachhang();
	public void setTenkhachhang(String _tenkhachhang);
	
	public String getDienthoai();
	public void setDienthoai(String _dienthoai);
	
	public String getDiachi();
	public void setDiachi(String _diachi);
	
	public String getIdkhachhang();
	public void setIdkhachhang(String _Idkhachhang);
	public boolean savekhachhang();
	
	public String getIscapnhat();
	public void setIsCapNhat(String _iscapnhat);
	
}
