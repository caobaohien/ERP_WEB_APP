package geso.training.bean.sanpham.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.lang.Exception;

import geso.erp.db.sql.dbutils;
import geso.training.bean.donhang.IDonHang;
import geso.training.bean.sanpham.ISanPham;

public class SanPham implements ISanPham {
	static final long serialVersionUID = 1L;
	String MaSP;
	String TenSP;
	String DVT;
	String PK_SEQ;
	int SoLuong;
	double DonGia;
	double ThanhTien;
	double chietkhau;
	private ResultSet resultset;
	
	private List<ISanPham> listSp;
	dbutils db;
	String PK_SEQdh;
	String userId;
	String userTen;
	String Madh;
	String ngaydh;
	String trangthai;
	String Makh;
	String Tenkh;
	String msg;
	double Vat;
	double TongtientruocVat;
	double TongtiensauVat;
	String khdiachi;
	String khdienthoai;
	public SanPham(){
		this.PK_SEQ="";
		this.MaSP="";
		this.TenSP="";
		this.DVT="";
		this.SoLuong=0;
		this.DonGia=0.0;
		this.ThanhTien=0.0;
		this.chietkhau=0.0;
		this.listSp = new ArrayList<ISanPham>();
		
		this.Madh="";
		this.db = new dbutils();
		this.userId="";
		this.PK_SEQdh="";
		this.userTen="";
		this.ngaydh="";
		this.trangthai="";
		this.Makh="";
		this.Tenkh="";
		this.msg="";
		this.Vat=0.0;
		this.TongtientruocVat=0.0;
		this.TongtiensauVat=0.0;
		this.khdiachi="";
		this.khdienthoai="";
	}
	public SanPham(String PK_SEQsp, String MaSP, String TenSP, String DVT,
			int SoLuong, double DonGia, double chietkhau, double ThanhTien){
		super();
		this.PK_SEQ = PK_SEQsp;
		this.MaSP = MaSP;
		this.TenSP = TenSP;
		this.DVT = DVT;
		this.SoLuong = SoLuong;
		this.DonGia = DonGia;
		this.chietkhau = chietkhau;
		this.ThanhTien = ThanhTien;
	}
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	public void setPK_SEQ(String pk_seq) {
		this.PK_SEQ = pk_seq;
	}
	public String getMaSP(){
		return MaSP;
	}
	public void setMaSP(String MaSP){
		this.MaSP=MaSP;
	}
	public String getTenSP(){
		return TenSP;
	}
	public void setTenSP(String TenSP){
		this.TenSP=TenSP;
	}
	public String getDVT(){
		return DVT;
	}
	public void setDVT(String DVT){
		this.DVT=DVT;
	}
	public int getSoLuong(){
		return SoLuong;
	}
	public void setSoLuong(int SoLuong){
		this.SoLuong=SoLuong;
	}
	public double getDonGia(){
		return DonGia;
	}
	public void setDonGia(double DonGia){
		this.DonGia=DonGia;
	}
	public double getThanhTien(){
		return ThanhTien;
	}
	public void setThanhTien(double ThanhTien){
		this.ThanhTien=ThanhTien;
	}
	public double getChietkhau() {
		return chietkhau;
	}
	public void setChietkhau(double chietkhau) {
		this.chietkhau=chietkhau;
	}
	@Override
	public ResultSet getResultSet() {
		// TODO Auto-generated method stub
		return this.resultset;
	}
	@Override
	public String getMadh() {
		// TODO Auto-generated method stub
		return Madh;
	}
	@Override
	public void setMadh(String madh) {
		// TODO Auto-generated method stub
		this.Madh = madh;
	}
	@Override
	public List<ISanPham> getListSp(){
		return listSp;
	}
	@Override
	public void setListSp(List<ISanPham> listSp){
		this.listSp=listSp;
	}
	public void init(){
		try{
			String query="select a.PK_SEQ as MADH,a.NGAYNHAP,b.PK_SEQ as MAKH, b.TEN +'-'+ diachi as khTen,a.VAT,isnull(a.TONGTIEN,0)TONGTIEN,isnull(a.TONGGIATRI,0)TONGGIATRI "
					+"from DONHANG a inner join KHACHHANG b on a.KHACHHANG_FK=b.PK_SEQ where a.pk_seq='"+this.Madh+"'";
			ResultSet rs = this.db.get(query);
			while(rs.next()){
				this.Madh=rs.getString("MADH");
				this.ngaydh=rs.getString("NGAYNHAP");
				this.Makh=rs.getString("MAKH");
				this.Tenkh=rs.getString("khTen");
				this.Vat=rs.getDouble("VAT");
				this.TongtientruocVat=rs.getDouble("TONGTIEN");
				this.TongtiensauVat=rs.getDouble("TONGGIATRI");
			}
			rs.close();
			//this.listSp=new ArrayList<ISanPham>();
			String query1="select b.PK_SEQ as PK_SEQsp,b.MA as MASP, b.TEN as TENSP,c.DIENGIAI as TENDV,a.SOLUONG,a.GIAMUA,a.CHIETKHAU,a.THANHTIEN "
					+"from DONHANG_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ "
					+"inner join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK where a.DONHANG_FK='"+this.Madh+"'";
			
			listSp.clear();
			ResultSet rssp=this.db.get(query1);
				while(rssp.next()){
					/*this.PK_SEQ = rssp.getString("PK_SEQsp");
					this.MaSP = rssp.getString("MASP");
					this.TenSP = rssp.getString("TENSP");
					this.DVT = rssp.getString("TENDV");
					this.SoLuong = rssp.getInt("SOLUONG");
					this.DonGia = rssp.getDouble("GIAMUA");
					this.chietkhau = rssp.getDouble("CHIETKHAU");
					this.ThanhTien = rssp.getDouble("THANHTIEN");
					ISanPham sp = new SanPham();
					sp.getPK_SEQ();
					sp.getMaSP();
					sp.getTenSP();
					sp.getDVT();
					sp.getSoLuong();
					sp.getDonGia();
					sp.getChietkhau();
					sp.getThanhTien();
					sp.setPK_SEQ(rssp.getString("PK_SEQsp"));
					sp.setMaSP(rssp.getString("MASP"));
					sp.setTenSP(rssp.getString("TENSP"));
					sp.setDVT(rssp.getString("TENDV"));
					sp.setSoLuong(rssp.getInt("SOLUONG"));
					sp.setDonGia(rssp.getDouble("GIAMUA"));
					sp.setChietkhau(rssp.getDouble("CHIETKHAU"));
					sp.setThanhTien(rssp.getDouble("THANHTIEN"));
					this.ds_sanpham.add(sp);*/
					ISanPham sp=new SanPham(
					rssp.getString("PK_SEQsp"),
					rssp.getString("MASP"),
					rssp.getString("TENSP"),
					rssp.getString("TENDV"),
					rssp.getInt("SOLUONG"),
					rssp.getDouble("GIAMUA"),
					rssp.getDouble("CHIETKHAU"),
					rssp.getDouble("THANHTIEN"));
					listSp.add(sp);
					}
				rssp.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Override
	public void initsp() {
		try{
			ISanPham sanpham = new SanPham();
			String query1="select b.PK_SEQ as PK_SEQsp,b.MA as MASP, b.TEN as TENSP,c.DIENGIAI as TENDV,a.SOLUONG,a.GIAMUA,a.CHIETKHAU,a.THANHTIEN "
					+"from DONHANG_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ "
					+"inner join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK where a.DONHANG_FK='"+this.Madh+"'";
			
			listSp.clear();
			ResultSet rssp=this.db.get(query1);
				while(rssp.next()){
					/*ISanPham sp = new SanPham();
					this.PK_SEQ = rssp.getString("PK_SEQsp");
					this.MaSP = rssp.getString("MASP");
					this.TenSP = rssp.getString("TENSP");
					this.DVT = rssp.getString("TENDV");
					this.SoLuong = rssp.getInt("SOLUONG");
					this.DonGia = rssp.getDouble("GIAMUA");
					this.chietkhau = rssp.getDouble("CHIETKHAU");
					this.ThanhTien = rssp.getDouble("THANHTIEN");
					
					sp.getPK_SEQ();
					sp.getMaSP();
					sp.getTenSP();
					sp.getDVT();
					sp.getSoLuong();
					sp.getDonGia();
					sp.getChietkhau();
					sp.getThanhTien();
					/*sp.setPK_SEQ(rssp.getString("PK_SEQsp"));
					sp.setMaSP(rssp.getString("MASP"));
					sp.setTenSP(rssp.getString("TENSP"));
					sp.setDVT(rssp.getString("TENDV"));
					sp.setSoLuong(rssp.getInt("SOLUONG"));
					sp.setDonGia(rssp.getDouble("GIAMUA"));
					sp.setChietkhau(rssp.getDouble("CHIETKHAU"));
					sp.setThanhTien(rssp.getDouble("THANHTIEN"));
					this.ds_sanpham.add(sp);*/
					ISanPham sp=new SanPham(
					rssp.getString("PK_SEQsp"),
					rssp.getString("MASP"),
					rssp.getString("TENSP"),
					rssp.getString("TENDV"),
					rssp.getInt("SOLUONG"),
					rssp.getDouble("GIAMUA"),
					rssp.getDouble("CHIETKHAU"),
					rssp.getDouble("THANHTIEN"));
					listSp.add(sp);
					}
				sanpham.setListSp(listSp);
				rssp.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}
	@Override
	public void setUserId(String userid) {
		// TODO Auto-generated method stub
		this.userId = userid;
	}
	@Override
	public String getMakh() {
		// TODO Auto-generated method stub
		return Makh;
	}
	@Override
	public void setMakh(String Makh) {
		// TODO Auto-generated method stub
		this.Makh = Makh;
	}
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return msg;
	}
	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}
	@Override
	public String getNgaydh() {
		// TODO Auto-generated method stub
		return ngaydh;
	}
	@Override
	public void setNgaydh(String ngaydh) {
		// TODO Auto-generated method stub
		this.ngaydh=ngaydh;
	}
	@Override
	public String getTenkh() {
		// TODO Auto-generated method stub
		return Tenkh;
	}
	@Override
	public void setTenkh(String Tenkh) {
		// TODO Auto-generated method stub
		this.Tenkh = Tenkh;
	}
	@Override
	public String getUserTen() {
		// TODO Auto-generated method stub
		return userTen;
	}
	@Override
	public void setUserTen(String userten) {
		// TODO Auto-generated method stub
		this.userTen=userten;
	}
	@Override
	public double getTongtientruocVat() {
		// TODO Auto-generated method stub
		return TongtientruocVat;
	}
	@Override
	public void setTongtientruocVat(double TongtientruocVat) {
		// TODO Auto-generated method stub
		this.TongtientruocVat=TongtientruocVat;
	}
	@Override
	public double getTongtiensauVat() {
		// TODO Auto-generated method stub
		return TongtiensauVat;
	}
	@Override
	public void setTongtiensauVat(double TongtiensauVat) {
		// TODO Auto-generated method stub
		this.TongtiensauVat = TongtiensauVat;
	}
	@Override
	public double getVat() {
		// TODO Auto-generated method stub
		return Vat;
	}
	@Override
	public void setVat(double Vat) {
		// TODO Auto-generated method stub
		this.Vat = Vat;
	}
	@Override
	public String getKhDiachi() {
		// TODO Auto-generated method stub
		return khdiachi;
	}
	@Override
	public void setKhDiachi(String Diachi) {
		// TODO Auto-generated method stub
		this.khdiachi=Diachi;
	}
	@Override
	public String getKhDienthoai() {
		// TODO Auto-generated method stub
		return khdienthoai;
	}
	@Override
	public void setKhDienthoai(String Dienthoai) {
		// TODO Auto-generated method stub
		this.khdienthoai=Dienthoai;
	}
	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return trangthai;
	}
	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}
	public void DBClose(){
		try{
			if(this.db!=null)this.db.shutDown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
