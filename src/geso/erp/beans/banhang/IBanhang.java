package geso.erp.beans.banhang;
 
import java.sql.ResultSet;
import java.util.List;

public interface IBanhang
{
	public String getUserId();
	public void setUserId(String userId);
	public String getShopId();
	public void setShopId(String ShopId);
	
	public String getGhiChu();
	public void setGhiChu(String ghichu);
	
	
	public String gettinhtrang_GIAOHANG();
	public void settinhtrang_GIAOHANG(String tinhtrang_GIAOHANG);
	
	public String getDiengiai_GIAOHANG();
	public void setDiengiai_GIAOHANG(String Diengiai_GIAOHANG);
	
	
	public String getSochungtu();
	public void setSochungtu(String sochungtu);
	public String getNgaymuahang();
	public void setNgaymuahang(String ngaymuahang);
	public String getKhten();
	public void setKhten(String nccTen);
	public String getKhId();
	public void setKhId(String khId);
	
	public String getIsCapNhat();
	public void setIsCapNhat(String iscapnhat);
	
	public String getchungtutimkiem();
	public void setchungtutimkiem(String chungtutimkiem);
	
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);

	public double getTongtienchuaVat();
	public void setTongtienchuaVat(double ttchuavat);

	public double getTienVat();
	public void setTienVat(double vat);

	public double getTongtiensauVat();
	public void setTongtiensauVat(double ttsauvat);

	public double getTienThanhToan();
	public void setTienThanhToan(double tienthanhtoan);

	
	public double getPer_Vat();
	public void setPer_Vat(double Per_Vat);
	
	public ResultSet getRsKh();
	public void setRsKh(ResultSet RsKh);
	
	public String getKhMa();
	public void setKhMa(String KhMa);

	public String getKhDienThoai();
	public void setKhDienThoai(String Dienthoai);

	public String getKhDiachi();
	public void setKhDiachi(String Diachi);
 
	public List<ISanpham> getSpList();
	public void setSpList(List<ISanpham> spList);

	  
	public String getMsg();
	public void setMsg(String msg);
	
	public String getIsThanhToanHet();
	public void setIsThanhToanHet(String IsThanhToanHet);
	
 
	public void createRs();
	public void init();
	public boolean createDmh();
	public boolean updateDmh();

	 public void close();
	public boolean Delete_dh();
	public void Init_getIdToMove(String action);
	public boolean saveKhachhang();
	
	 
}
