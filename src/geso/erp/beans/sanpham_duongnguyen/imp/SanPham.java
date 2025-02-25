package geso.erp.beans.sanpham_duongnguyen.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.erp.beans.sanpham_duongnguyen.ISanPham;
import geso.erp.db.sql.dbutils;

public class SanPham implements ISanPham {

	String userId;
	String id;
	String ma;
	String ten;
	String msg;
	String trangThai;
	String donViTinh;
	ResultSet rsDonVi;
	dbutils db = new dbutils();

	public SanPham() {
		this.userId = "";
		this.id = "";
		this.ma = "";
		this.ten = "";
		this.msg = "";
		this.trangThai = "";
		this.donViTinh = "";
	}

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getTrangThai() {
		return this.trangThai;
	}

	@Override
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String getMa() {
		return this.ma;
	}

	@Override
	public void setMa(String ma) {
		this.ma = ma;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getDonViTinh() {
		return this.donViTinh;
	}

	@Override
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;

	}

	@Override
	public ResultSet getRsDonViTinh() {
		return this.rsDonVi;
	}

	@Override
	public void setRsDonViTinh(ResultSet rsDonViTinh) {
		this.rsDonVi = rsDonViTinh;
	}

	@Override
	public String getTen() {
		return this.ten;
	}

	@Override
	public void setTen(String ten) {
		this.ten = ten;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void init() {
		try {
			String query = "select pk_seq,ma,ten,donvi_fk,trangthai from sanpham where pk_seq=" + this.id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.ten = rs.getString("ten");
				this.ma = rs.getString("ma");
				this.donViTinh = rs.getString("donvi_fk") == null ? "" : rs.getString("donvi_fk");
				this.trangThai = rs.getString("trangthai");
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void createRs() {
		String query = "select pk_seq,ma from donvi";
		this.rsDonVi = db.get(query);
	}

	@Override
	public void dbClose() {
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Override
	public boolean save() {
		try {
			db.getConnection().setAutoCommit(false);
			String query = "insert into sanpham(ma,ten,donvi_fk,trangthai)values" + "('" + this.ma + "',N'" + this.ten
					+ "','" + this.donViTinh + "','" + this.trangThai + "')";
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh ; " + query;
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
		try {
			db.getConnection().setAutoCommit(false);
			String query = "update sanpham set ma='"+this.ma+"',ten=N'"+this.ten+"',donvi_fk="+this.donViTinh+",trangthai='"+this.trangThai+"' where pk_seq = " + this.id;
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh ; " + query;
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

}
