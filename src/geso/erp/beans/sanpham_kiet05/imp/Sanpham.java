package geso.erp.beans.sanpham_kiet05.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.erp.beans.sanpham_kiet05.ISanpham;
import geso.erp.db.sql.dbutils;

public class Sanpham implements ISanpham {

	String Ma, Ten, UserId, Id, Trangthai, Donvitinh, Msg;
	ResultSet rs;
	dbutils db= new dbutils();
	
	public Sanpham() {
		UserId = "";
		Id = "";
		Ma = "";
		Ten = "";
		Msg = "";
		Trangthai = "";
		Donvitinh = "";
	}
	
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId= userId;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.Ma= ma;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai= trangthai;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.Id= id;
	}

	@Override
	public String getDonvitinh() {
		// TODO Auto-generated method stub
		return this.Donvitinh;
	}

	@Override
	public void setDonvitinh(String dvt) {
		// TODO Auto-generated method stub
		this.Donvitinh= dvt;
	}

	@Override
	public ResultSet getRsdDvt() {
		// TODO Auto-generated method stub
		return this.rs;
	}

	@Override
	public void setRsDvt(ResultSet rsDvt) {
		// TODO Auto-generated method stub
		this.rs= rsDvt;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten= ten;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg= msg;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT PK_SEQ,MA,TEN ,DONVI_FK, TRANGTHAI FROM SANPHAM WHERE PK_SEQ="
					+ Id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.Ten = rs.getString("ten");
				this.Ma = rs.getString("ma");

				this.Trangthai = rs.getString("trangthai");
				this.Donvitinh = (rs.getString("DONVI_FK") == null ? "" : rs
						.getString("DONVI_FK"));

			}
			rs.close();
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		String query="select pk_Seq,ma from donvi ";
		this.rs=db.get(query);
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@Override
	public boolean save() {
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "INSERT INTO SANPHAM (MA,TEN,DONVI_FK,TRANGTHAI) VALUES "
					+ "('"
					+ this.Ma
					+ "',N'"
					+ this.Ten
					+ "','"
					+ this.Donvitinh + "','" + this.Trangthai + "')";
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.Msg = "Không thể thực hiện dòng lệnh ; " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");

			this.Msg = "Lỗi : " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update() {
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "update sanpham set ma='" + this.Ma + "', ten=N'"
					+ this.Ten + "', donvi_fk='" + this.Donvitinh
					+ "', trangthai='" + this.Trangthai + "' where pk_seq= "
					+ this.Id;
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.Msg = "Không thể thực hiện dòng lệnh ; " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.Msg = "Lỗi : " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
