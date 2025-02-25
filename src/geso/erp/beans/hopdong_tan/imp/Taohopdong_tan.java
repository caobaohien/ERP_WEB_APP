package geso.erp.beans.hopdong_tan.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.erp.beans.donmuahang_tan.ISanpham_tan;
import geso.erp.beans.hopdong_tan.ITaohopdong_tan;
import geso.erp.db.sql.dbutils;

public class Taohopdong_tan implements ITaohopdong_tan{
	String userId;
	String idSohopdong;
	String sohopdong;
	String hopdong_pk;
	String hoso_fk;
	String kyhopdong;
	String quocgia;
	String ngayky;
	String nguoiky;
	double giatrihopdong;
	String ngaytao;
	String nguoitao;
	String lydo;
	String ngaysua;
	String nguoisua;
	String trangthai;
	String nhanxet;
	String ngaytuvan;

	String IsCapNhat;
	String msg;
	dbutils db=new dbutils();
	ResultSet rsHD;
	ResultSet rsHS;
	ResultSet rsQG;
	
	public Taohopdong_tan(String _str){
		this.userId = "";
		this.idSohopdong = _str;
		this.hopdong_pk = "";
		this.hoso_fk = "";
		this.sohopdong = "";
		this.quocgia = "";
		this.kyhopdong = "1";
		this.ngayky = "";
		this.nguoiky = "";
		this.giatrihopdong = 0;
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.lydo = "";
		this.trangthai = "";
		this.nhanxet = "";
		this.IsCapNhat = "1";
		this.msg = "";
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.userId = _userId;
	}

	@Override
	public String getNgaytuvan() {
		// TODO Auto-generated method stub
		return this.ngaytuvan;
	}

	@Override
	public void setNgaytuvan(String _ngaytuvan) {
		// TODO Auto-generated method stub
		this.ngaytuvan = _ngaytuvan;
	}
	
	@Override
	public String getIdSohopdong() {
		// TODO Auto-generated method stub
		return this.idSohopdong;
	}

	@Override
	public void setIdSohopdong(String _idSohopdong) {
		// TODO Auto-generated method stub
		this.idSohopdong = _idSohopdong;
	}

	@Override
	public String getHopdong_pk() {
		// TODO Auto-generated method stub
		return this.hopdong_pk;
	}

	@Override
	public void setHopdong_pk(String _hopdong_pk) {
		// TODO Auto-generated method stub
		this.hopdong_pk = _hopdong_pk;
	}

	@Override
	public String getHoso_fk() {
		// TODO Auto-generated method stub
		return this.hoso_fk;
	}

	@Override
	public void setHoso_fk(String _hoso_fk) {
		// TODO Auto-generated method stub
		this.hoso_fk = _hoso_fk;
	}

	@Override
	public String getKyhopdong() {
		// TODO Auto-generated method stub
		return this.kyhopdong;
	}

	@Override
	public void setKyhopdong(String _kyhopdong) {
		// TODO Auto-generated method stub
		this.kyhopdong = _kyhopdong;
	}
	@Override
	public String getSohopdong() {
		// TODO Auto-generated method stub
		return this.sohopdong;
	}

	@Override
	public void setSohopdong(String _sohopdong) {
		// TODO Auto-generated method stub
		this.sohopdong = _sohopdong;
	}
	
	@Override
	public String getQuocgia() {
		// TODO Auto-generated method stub
		return this.quocgia;
	}

	@Override
	public void setQuocgia(String _quocgia) {
		// TODO Auto-generated method stub
		this.quocgia = _quocgia;
	}

	@Override
	public String getNgayky() {
		// TODO Auto-generated method stub
		return this.ngayky;
	}

	@Override
	public void setNgayky(String _ngayky) {
		// TODO Auto-generated method stub
		this.ngayky = _ngayky;
	}

	@Override
	public String getNguoiky() {
		// TODO Auto-generated method stub
		return this.nguoiky;
	}

	@Override
	public void setNguoiky(String _nguoiky) {
		// TODO Auto-generated method stub
		this.nguoiky = _nguoiky;
	}

	@Override
	public double getGiatrihopdong() {
		// TODO Auto-generated method stub
		return this.giatrihopdong;
	}

	@Override
	public void setGiatrihopdong(double _giatrihopdong) {
		// TODO Auto-generated method stub
		this.giatrihopdong = _giatrihopdong;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}

	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao = _ngaytao;
	}

	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.nguoitao;
	}

	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.nguoitao = _nguoitao;
	}

	@Override
	public String getLydo() {
		// TODO Auto-generated method stub
		return this.lydo;
	}

	@Override
	public void setLydo(String _lydo) {
		// TODO Auto-generated method stub
		this.lydo = _lydo;
	}

	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.ngaysua;
	}

	@Override
	public void setNgaysua(String _ngaysua) {
		// TODO Auto-generated method stub
		this.ngaysua = _ngaysua;
	}

	@Override
	public String getNguoisua() {
		// TODO Auto-generated method stub
		return this.nguoisua;
	}

	@Override
	public void setNguoisua(String _nguoisua) {
		// TODO Auto-generated method stub
		this.nguoisua = _nguoisua;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.trangthai = _trangthai;
	}

	@Override
	public String getNhanxet() {
		// TODO Auto-generated method stub
		return this.nhanxet;
	}

	@Override
	public void setNhanxet(String _nhanxet) {
		// TODO Auto-generated method stub
		this.nhanxet = _nhanxet;
	}
	
	@Override
	public String getIsCapNhat() {
		// TODO Auto-generated method stub
		return this.IsCapNhat;
	}

	@Override
	public void setIsCapNhat(String _isCapnhat) {
		// TODO Auto-generated method stub
		this.IsCapNhat = _isCapnhat;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void setMsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg = _msg;
	}

	@Override
	public ResultSet getRsHD() {
		// TODO Auto-generated method stub
		return this.rsHD;
	}

	@Override
	public void setRsHD(ResultSet _rsHD) {
		// TODO Auto-generated method stub
		this.rsHD = _rsHD;
	}

	@Override
	public ResultSet getRsHS() {
		// TODO Auto-generated method stub
		return this.rsHS;
	}

	@Override
	public void setRsHS(ResultSet _rsHS) {
		// TODO Auto-generated method stub
		this.rsHS = _rsHS;
	}

	@Override
	public ResultSet getRsQG() {
		// TODO Auto-generated method stub
		return this.rsQG;
	}

	@Override
	public void setRsQG(ResultSet _rsQG) {
		// TODO Auto-generated method stub
		this.rsQG = _rsQG;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try{
			if(this.idSohopdong!=""){
			String query="SELECT HD.PK_SEQ AS MAHD, HD.HOSO_FK as MAHS,LYDO,KYHOPDONG, SOHOPDONG, "
						+ "TV.QUOCGIA_FK as QGDK, "
						+ "TV.PK_SEQ AS MATV, TV.NHANXET, HD.GIATRIHOPDONG as GIATRIDH, "
						+ "HD.TRANGTHAI as TRANGTHAIHOPDONG "
					+ "from HOPDONG HD INNER JOIN "
						+ "HOSO ON HOSO.PK_SEQ = HD.HOSO_FK INNER JOIN " 
						+ "TUVAN TV ON TV.HOSO_FK=HOSO.PK_SEQ LEFT JOIN "
						+ "QUOCGIA QG ON QG.PK_SEQ=TV.QUOCGIA_FK "
					+ "where HD.PK_SEQ="+Integer.parseInt(this.idSohopdong)+"";
			
			ResultSet rs=db.get(query);
			if(rs.next()){
				System.out.println("Gà ran KFS");
				this.hoso_fk=rs.getString("MAHS");
				this.lydo=rs.getString("LYDO");
				this.kyhopdong=rs.getString("KYHOPDONG");
				this.sohopdong = rs.getString("SOHOPDONG");
				this.quocgia = rs.getString("QGDK");
				this.giatrihopdong = rs.getDouble("GIATRIDH");
				this.trangthai = rs.getString("TRANGTHAIQUOCGIA");
				this.nhanxet = rs.getString("NHANXET");
			}
			rs.close();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
		
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		String queryHoso = "SELECT PK_SEQ, TEN FROM HOSO";
		this.rsHS=db.get(queryHoso);
		String queryQG="select PK_SEQ,TEN from QUOCGIA";
		this.rsQG=db.get(queryQG);
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		
	}

	private String getDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try{
			if(this.hoso_fk==null|| this.hoso_fk.length()==0){
				this.msg="Vui lòng chọn hồ sơ cần ký hợp đồng";
				return false;
			}
			if(this.kyhopdong==null|| this.kyhopdong.length()==0){
				this.msg="Vui lòng chọn Ký hợp đồng hoặc Không kí hợp đồng";
				return false;
			}
			if(this.quocgia==null|| this.quocgia.length()==0){
				this.msg="Vui lòng chọn quốc gia theo học";
				return false;
			}
			if(this.giatrihopdong==0){
				this.msg="Vui lòng điền giá trị hợp đồng";
				return false;
			}
			db.getConnection().setAutoCommit(false);
			
			String query="INSERT INTO HOPDONG (HOSO_FK, KYHOPDONG,SOHOPDONG, NGAYKYHOPDONG, NGUOIKYHOPDONG, "
					+ "GIATRIHOPDONG, LYDO, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA, TRANGTHAI) "
					+ "VALUES ("+Integer.parseInt(this.hoso_fk)+", '"+this.kyhopdong+"','"+this.sohopdong+"', "
					+ "'"+this.getDateTime()+"',"+Integer.parseInt(this.userId)+","+this.giatrihopdong+", "
					+ "'"+this.lydo+"','"+this.getDateTime()+"','"+this.getDateTime()+"', "
					+ ""+Integer.parseInt(this.userId)+", "+Integer.parseInt(this.userId)+", '1')";
			if(!db.update(query)){
				this.msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			db.update("rollback");
			this.msg=ex.getMessage();
			return false;
		}
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
