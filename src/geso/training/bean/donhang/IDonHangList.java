package geso.training.bean.donhang;
import java.sql.ResultSet;

public interface IDonHangList {
	public String getUserId();
	public void setUserId(String userid);
	public String getUserTen();
	public void setUserTen(String userten);
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getSodonhang();
	public void setSodonhang(String sodonhang);
	public String getKhachhang();
	public void setKhachhang(String khachhang);
	public String getMsg();
	public void setMsg(String msg);
	public ResultSet getDhRs();
	public void setDhRs(ResultSet dhrs);
	public ResultSet getKhRs();
	public void setKhRs(ResultSet khrs);
	public void init(String search);
	public String getMadh();
	public void setMadh(String Madh);
	public void DBClose();
}
