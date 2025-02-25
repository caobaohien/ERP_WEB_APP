package geso.erp.beans.report.imp;

import geso.dms.center.util.Utility;
import geso.erp.beans.report.Ireport;
import geso.erp.db.sql.dbutils;
 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report implements Ireport
{
	String userId;
    String nppId;
    String nppTen;
    String kenhId;
    String dvkdId;
    String nhanhangId;
    String chungloaiId;
    String tungay;
    String denngay;
    String userTen;
    String khoId;
    String book;
    ResultSet kho;
    String msg;
    ResultSet kenh;
    ResultSet dvkd;
    ResultSet nhanhang;
    ResultSet chungloai;
    String vungId;
    String khuvucId;
    ResultSet khuvuc;
    ResultSet vung;
    ResultSet npp;
    String vat;
    String gsbhId;
    ResultSet gsbh;
    String sanphamId;
    ResultSet sanpham;
    String dvdlId;
    String[] FieldShow;
    String[] FieldHidden;
    ResultSet dvdl;
    String khachhangId;
    ResultSet khachhang;
    String ddkdId;
    String ngayton;
    ResultSet ddkd;
    dbutils db;
    String view;
    String trangthai;
    String tructhuoc_fk;
    
    //Duong
    String year;
    String month;
    String muclay;
    String sanPham;
	String nhaPhanPhoi;
	String kenhBanHang;
	String nhomSanPham;
	ResultSet rsSanPham;
	ResultSet rsNhaPhanPhoi;
	ResultSet rsKenhBanHang;
	ResultSet rsNhomSanPham;

	private String ttppId;
    public Report()
    {
    	 this.userId="";
    	 this.nppId="";
    	 this.nppTen="";
    	 this.kenhId="";
    	 this.dvkdId="";
    	 this.nhanhangId="";
    	 this.chungloaiId="";
    	 this.tungay="";
    	 this.denngay="";
    	 this.userTen="";
    	 this.khoId="";
    	 this.book="";
    	 this.msg="";
    	 this.vungId="";
    	 this.khuvucId="";
    	 this.vat="";
    	 this.gsbhId="";
    	 this.sanphamId="";
    	 this.dvdlId="";
    	 this.khachhangId ="";
    	 this.ddkdId ="";
    	 this.ngayton ="0";
    	 this.trangthai="";
    	 this.view = "";
    	 this.ttppId = "";
    	 this.muclay="0";
    	 this.month = "";
    	 this.year = "";
    	 this.sanPham = "";
    	 this.nhaPhanPhoi ="";
    	 this.kenhBanHang ="";
    	 this.nhomSanPham="";
	   	 this.db = new dbutils();
    }
	
	public String getTrangthai()
	{
		return trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public void setuserId(String userId) {
		
	    this.userId = userId;
	}

	
	public String getuserId() {
		
		return this.userId;
	}

	
	public void setnppId(String nppId) {
		
		this.nppId = nppId;
	}

	
	public String getnppId() {
		
		return this.nppId;
	}

	
	public void setnppTen(String nppTen) {
		
		this.nppTen = nppTen;
	}

	
	public String getnppTen() {
		
		return this.nppTen;
	}

	
	public void setkenhId(String kenhId) {
		
		this.kenhId = kenhId;
	}

	
	public String getkenhId() {
		
		return this.kenhId;
	}

	
	public void setdvkdId(String dvkdId) {
		
		this.dvkdId = dvkdId;
	}

	
	public String getdvkdId() {
		
		return this.dvkdId;
	}

	
	public void setnhanhangId(String nhanhangId) {
		
		this.nhanhangId = nhanhangId;
	}

	
	public String getnhanhangId() {
		
		return this.nhanhangId;
	}

	
	public void setchungloaiId(String chungloaiId) {
		
		this.chungloaiId = chungloaiId;
	}

	
	public String getchungloaiId() {
		
		return this.chungloaiId;
	}

	
	public void setkenh(ResultSet kenh) {
		
		this.kenh = kenh;
	}

	
	public ResultSet getkenh() {
		
		return this.kenh;
	}

	
	public void setdvkd(ResultSet dvkd) {
		
		this.dvkd = dvkd;
	}

	
	public ResultSet getdvkd() {
		
		return this.dvkd;
	}

	
	public void setnhanhang(ResultSet nhanhang) {
		
		this.nhanhang = nhanhang;
	}

	
	public ResultSet getnhanhang() {
		
		return this.nhanhang;
	}

	
	public void setchungloai(ResultSet chungloai) {
		
		this.chungloai = chungloai;
	}

	
	public void setuserTen(String userTen) {
		
		this.userTen = userTen;
	}

	
	public String getuserTen() {
		
		return this.userTen;
	}

	
	public void settungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String gettungay() {
		
		return this.tungay;
	}

	
	public void setdenngay(String denngay) {
		
		this.denngay = denngay;
	}

	
	public String getdenngay() {
		
		return this.denngay;
	}

	

	
	public String getMsg() {
		
		return msg;
	}
	
	
	
	public void init() 
	{
		Utility Ult = new Utility();
		if(this.nppId.length()==0){
			
			this.nppId = Ult.getIdNhapp(this.userId, db);
		}
		System.out.println("nppId = " + this.nppId);
		if(this.nppId.length() > 0)
		{
			String sql = "select * from daidienkinhdoanh where pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk='"+this.nppId+"') ";
			this.ddkd = db.get(sql);
			
			if(this.ddkdId.length()>0)
			sql = " select * from khachhang where pk_seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_seq from tuyenbanhang where npp_fk ='"+ this.nppId +"' and ddkd_fk ='"+ this.ddkdId +"')) ";
			else
				sql = " select * from khachhang where pk_seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_seq from tuyenbanhang where npp_fk ='"+ this.nppId +"')) ";
			this.khachhang = db.get(sql);
			
		}
		String _npp = Ult.getIdNhapp(this.userId, db);
		if(_npp.length() > 0){
			if(view.equals("NPP")){
				String sql = "select loaiNPP, tructhuoc_fk from NHAPHANPHOI where PK_SEQ = " + _npp;
				ResultSet rs = this.db.get(sql);
				try {
					if(rs.next()){
						this.tructhuoc_fk = rs.getString("tructhuoc_fk");
						if(rs.getString("loaiNPP").equals("0"))
							this.ttppId = _npp;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String query = "select a.ma as nppMa,a.pk_seq as nppId,a.ma + '-' + a.ten as nppTen, ' ' + b.ten as kvTen " +
						"from nhaphanphoi a inner join khuvuc b on a.khuvuc_fk = b.pk_seq " +
						"where a.trangthai = '1' and a.sitecode = a.convsitecode ";
		if(this.khuvucId.length()>0)
		{
			query += " and a.khuvuc_fk ='"+this.khuvucId+"' ";
		}
		if(this.vungId.length()>0)
		{
			query += " and a.khuvuc_fk  in (select pk_seq from khuvuc where vung_fk ='"+this.vungId+"' )";
		}
		query+=" order by b.pk_seq asc ";
		System.out.println("[Npp]"+query);
		
		this.npp = db.get(query);
	
		query="select PK_SEQ as vungId,TEN as vung from vung ";
		this.vung=this.db.get(query);	
		
		query="select pk_seq as kvId,ten as kv from khuvuc a  where 1=1 ";
		
		if(this.vungId.length()>0)
			query += " and vung_fk ='"+this.vungId+"' ";
		
		this.khuvuc=this.db.get(query);
	}

	
	public void setkhachhangId(String khachhangId) {
		
		this.khachhangId = khachhangId;
	}

	
	public String getkhachhangId() {
		
		return this.khachhangId;
	}

	
	public void setkhachhang(ResultSet khachhang) {
		
		this.khachhang = khachhang;
	}

	
	public ResultSet getkhachhang() {
		
		return this.khachhang;
	}

	
	public void setMsg(String msg) 
	{
		this.msg=msg	;
		
	}

	
	public void setddkdId(String ddkdId) {
		
		this.ddkdId = ddkdId;
	}

	
	public String getddkdId() {
		
		return this.ddkdId;
	}

	
	public void setddkd(ResultSet ddkd) {
		
		this.ddkd = ddkd;
	}

	
	public ResultSet getddkd() {
		
		return this.ddkd;
	}
	public void setFieldShow(String[] fieldShow) {
		
		this.FieldShow  =fieldShow;
	}

	
	public String[] getFieldShow() {
		
		return this.FieldShow;
	}

	
	public void setFieldHidden(String[] fieldHidden) {
		
		this.FieldHidden = fieldHidden;
	}

	
	public String[] getFieldHidden() {
		
		return this.FieldHidden;
	}

	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
         Date date = new Date();
         return dateFormat.format(date);
	}

	
	public void setngayton(String ngayton) 
	{
		this.ngayton = ngayton;
	}

	public String getngayton()
	{
		return this.ngayton;
	}

	public void setNppRs(ResultSet nppRs) 
	{
		this.npp = nppRs;
	}

	public ResultSet getNppRs() 
	{
		return this.npp;
	}
	public ResultSet getVungRs()
	{
		return this.vung;
	}
	public void setVungRs(ResultSet vungRs)
	{
		this.vung=vungRs;
	}
	
	public ResultSet getKvRs()
	{
		return this.khuvuc;
	}
	public void setKvRs(ResultSet kvRs)
	{
		this.khuvuc=kvRs;
	}
	
	public String getVungId()
	{
		return this.vungId;
	}
	public void setVungId(String vungId)
	{
		this.vungId=vungId;
	}
	public String getKvId()
	{
		return this.khuvucId;
	}
	public void setKvId(String kvId)
	{
		this.khuvucId=kvId;
	}

	@Override
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public String getView() {
		return this.view;
	}

	@Override
	public void setNppTructhuoc(String value) {
		this.tructhuoc_fk = value;
	}

	@Override
	public String getNppTructhuoc() {
		return this.tructhuoc_fk;
	}

	@Override
	public String getTTPP() {
		// TODO Auto-generated method stub
		return this.ttppId;
	}

	@Override
	public void setTTPP(String ttpp) {
		this.ttppId = ttpp;
	}
	public String getMuclay() {
		return muclay;
	}

	public void setMuclay(String muclay) {
		this.muclay = muclay;
	}

	@Override
	public void setYear(String year){
		this.year= year;
	}
	@Override
	public String getYear(){
		return this.year;
	}
	@Override
	public void setMonth(String month){
		this.month= month;
	}
	@Override
	public String getMonth(){
		return this.month;
	}
	@Override
	public void setSanPham(String sanPham){
		this.sanPham = sanPham;
	}
	@Override
	public String getSanPham(){
		return this.sanPham;
	}
	@Override
	public void setNhaPhanPhoi(String nhaPhanPhoi){
		this.nhaPhanPhoi= nhaPhanPhoi; 
	}
	@Override
	public String getNhaPhanPhoi(){
		return this.nhaPhanPhoi;
	}
	@Override
	public void setKenhBanHang(String kenhBanHang){
		this.kenhBanHang = kenhBanHang;
	}
	@Override
	public String getKenhBanHang(){
		return this.kenhBanHang;
	}
	@Override
	public void setRsSanPham(){
		String query = "SELECT PK_SEQ,TEN FROM SANPHAM";
		this.rsSanPham = db.get(query);
	}
	@Override
	public void setRsNhaPhanPhoi(){
		String query = "SELECT PK_SEQ,TEN FROM NHAPHANPHOI";
		this.rsNhaPhanPhoi = db.get(query);
	}
	@Override
	public void setRsKenhBanHang(){
		String query = "SELECT PK_SEQ,DIENGIAI FROM KENHBANHANG";
		this.rsKenhBanHang = db.get(query);
	}


	@Override
	public void setNhomSanPham(String nhomSanPham) {
		this.nhomSanPham = nhomSanPham;
	}


	@Override
	public String getNhomSanPham() {
		return this.nhomSanPham;
	}


	@Override
	public void setRsNhomSanPham() {
		String query ="SELECT PK_SEQ,DIENGIAI FROM NHOMSANPHAM";
		this.rsNhomSanPham = db.get(query);
		
	}

	@Override
	public ResultSet getRsSanPham() {
		return this.rsSanPham;
	}

	@Override
	public ResultSet getRsNhaPhanPhoi() {
		return this.rsNhaPhanPhoi;
	}

	@Override
	public ResultSet getRsKenhBanHang() {
		return this.rsKenhBanHang;
	}

	@Override
	public ResultSet getRsNhomSanPham() {
		return this.rsNhomSanPham;
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
