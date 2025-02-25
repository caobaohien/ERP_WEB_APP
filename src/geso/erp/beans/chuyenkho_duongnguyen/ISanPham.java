package geso.erp.beans.chuyenkho_duongnguyen;

public interface ISanPham {
	public String getChuyenKhoId();
	public void setChuyenKhoId(String chuyenKho_fk);
	public String getId();
	public void setId(String id);
	public String getMaSP();
	public void setMaSP(String maSP);
	public String getTenSP();
	public void setTenSP(String tenSP);
	public String getDVT();
	public void setDVT(String dvt);
	public double getSLTon();
	public void setSLTon(double sLTon);
	public double getSLChuyen();
	public void setSLChuyen(double sLChuyen);
}
