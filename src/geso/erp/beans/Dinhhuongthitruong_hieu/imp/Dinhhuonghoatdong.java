package geso.erp.beans.Dinhhuongthitruong_hieu.imp;

import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuong_hoatdong;

public class Dinhhuonghoatdong implements IDinhhuong_hoatdong {
	private static final long serialVersionUID = -9217977546733610214L;
	String PK_SEQ;
	String id;
	String ma;
	String tenhd;
	String tungay;
	String denngay;
	String mota;
	float kinhphi;
	int soluongkh;
	int soluonghd;
	public  Dinhhuonghoatdong() {
		// TODO Auto-generated constructor stub		 
	}
	@Override
	public String getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.PK_SEQ;
	}
	@Override
	public void setPK_SEQ(String pk_seq) {
		// TODO Auto-generated method stub
		this.PK_SEQ=pk_seq;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}
	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma=ma;
	}
	@Override
	public String getTenhoatdong() {
		// TODO Auto-generated method stub
		return this.tenhd;
	}
	@Override
	public void setTenhoatdong(String _tenhoatdong) {
		// TODO Auto-generated method stub
		this.tenhd=_tenhoatdong;
	}
	@Override
	public float getKinhphi() {
		// TODO Auto-generated method stub
		return this.kinhphi;
	}
	@Override
	public void setKinhphi(float _kinhphi) {
		// TODO Auto-generated method stub
		this.kinhphi=_kinhphi;
	}
	@Override
	public int getSoluongkh() {
		// TODO Auto-generated method stub
		return this.soluongkh;
	}
	@Override
	public void setSoluongkh(int _soluongkh) {
		// TODO Auto-generated method stub
		this.soluongkh=_soluongkh;
	}
	@Override
	public int getSoluonghd() {
		// TODO Auto-generated method stub
		return this.soluonghd;
	}
	@Override
	public void setSoluonghd(int _soluonghd) {
		// TODO Auto-generated method stub
		this.soluonghd=_soluonghd;
		
	}
	@Override
	public String getTungay() {
		// TODO Auto-generated method stub
		return this.tungay;
	}
	@Override
	public void setTungay(String _tungay) {
		// TODO Auto-generated method stub
		this.tungay=_tungay;
	}
	@Override
	public String getDenngay() {
		// TODO Auto-generated method stub
		return this.denngay;
	}
	@Override
	public void setDenngay(String _denngay) {
		// TODO Auto-generated method stub
		this.denngay=_denngay;
	}
	@Override
	public String getMota() {
		// TODO Auto-generated method stub
		return this.mota;
	}
	@Override
	public void setMota(String _mota) {
		// TODO Auto-generated method stub
		this.mota=_mota;
	}
	
	

}
