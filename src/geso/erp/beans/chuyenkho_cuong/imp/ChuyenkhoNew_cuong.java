package geso.erp.beans.chuyenkho_cuong.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoNew_cuong;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoNew_cuong extends Phan_Trang implements IChuyenkhoNew_cuong  {
	private String ma;
	private String id;
	private String khochuyen;
	private String khonhan;
	private String trangthai;
	private String ngaytao;
	private String nguoitao;
	private String userId;
	private String msg;
	private String lydo;
	private ResultSet rskhochuyen;
	private ResultSet rskhonhan;
	private ResultSet rsData;
	dbutils db;
	public ChuyenkhoNew_cuong(){
		ma="";
		id="";
		khochuyen="";
		khonhan="";
		trangthai="0";
		ngaytao="";
		nguoitao="";
		userId="";
		msg="";
		lydo="";
		this.db = new dbutils();
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma=ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setKhoChuyen(String khochuyen) {
		// TODO Auto-generated method stub
		this.khochuyen=khochuyen;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.khochuyen;
	}

	public void setKhoNhan(String khonhan) {
		// TODO Auto-generated method stub
		this.khonhan=khonhan;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.khonhan;
	}

	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.trangthai=trangthai;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	@Override
	public void setRsData(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData=rsData;
		
	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId=userId;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		try {
			String query = "select  PK_SEQ, LYDO, NGAYCHUYENKHO,KHOCHUYEN,KHONHAN from CHUYENKHO where PK_SEQ= "
					+ this.id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.ngaytao = rs.getString("NGAYCHUYENKHO");
				this.khochuyen = rs.getString("KHOCHUYEN")== null ? "" : rs.getString("KHOCHUYEN");
				this.khonhan = rs.getString("KHONHAN") == null ? "" : rs.getString("KHONHAN");
				this.lydo = rs.getString("LYDO");
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void CreateRsKhoNhan() {
		// TODO Auto-generated method stub
		String query="select pk_seq, TENKHO from kho";
			this.rskhonhan=db.get(query);
	}

	@Override
	public ResultSet getRsKhoNhan() {
		// TODO Auto-generated method stub
		return this.rskhonhan;
	}

	@Override
	public void CreateRsKhoChuyen() {
		// TODO Auto-generated method stub
		String query="select pk_seq, TENKHO from kho";
		this.rskhochuyen=db.get(query);
		
	}

	@Override
	public ResultSet getRsKhoChuyen() {
		// TODO Auto-generated method stub
		return this.rskhochuyen;
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save() {
		try {
			db.getConnection().setAutoCommit(false);
			String query = "INSERT INTO CHUYENKHO(NGAYCHUYENKHO, LYDO,KHOCHUYEN,KHONHAN,NGUOITAO,NGUOISUA,TRANGTHAI,NGAYTAO,NGAYSUA)VALUES "
					+ "('" + this.ngaytao + "','" + this.lydo + "'," + this.khochuyen + "," + this.khonhan + ","+this.userId+ ","+this.userId+ ","+this.trangthai+",'"+this.ngaytao+"','"+this.ngaytao +"')";
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh : " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			db.update("rollback");
			this.msg = "Lỗi : " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLyDo(String lydo) {
		// TODO Auto-generated method stub
	this.lydo=lydo;
	}

	@Override
	public String getLyDo() {
		// TODO Auto-generated method stub
		return this.lydo;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setNgayChuyen(String ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao=ngaytao;
	}

	@Override
	public String getNgayChuyen() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}

	

}
