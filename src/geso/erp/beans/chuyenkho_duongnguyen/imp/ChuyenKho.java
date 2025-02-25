package geso.erp.beans.chuyenkho_duongnguyen.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import E.E;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKho;
import geso.erp.beans.chuyenkho_duongnguyen.ISanPham;
import geso.erp.db.sql.dbutils;

public class ChuyenKho extends Phan_Trang implements IChuyenKho {
	private String id;
	private String ngayChuyenKho;
	private String khoNhan;
	private String khoChuyen;
	private String nguoiTao;
	private String nguoiSua;
	private String trangThai;
	private String ngayTao;
	private String ngaySua;
	private String lyDo;
	private String userId;
	private String msg;
	private List<ISanPham> listSP;
	private ResultSet rsKhoChuyen;
	private ResultSet rsKhoNhan;
	private ResultSet rsData;
	dbutils db;

	public ChuyenKho() {
		this.id = "";
		this.ngayChuyenKho = "";
		this.khoNhan = "";
		this.khoChuyen = "";
		this.nguoiTao = "";
		this.nguoiSua = "";
		this.trangThai = "";
		this.ngayTao = "";
		this.ngaySua = "";
		this.lyDo = "";
		this.userId = "";
		this.msg = "";
		this.db = new dbutils();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getNgayChuyenKho() {
		// TODO Auto-generated method stub
		return this.ngayChuyenKho;
	}

	@Override
	public void setNgayChuyenKho(String ngayChuyenKho) {
		// TODO Auto-generated method stub
		this.ngayChuyenKho = ngayChuyenKho;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.khoNhan;
	}

	@Override
	public void setKhoNhan(String khoNhan) {
		// TODO Auto-generated method stub
		this.khoNhan = khoNhan;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.khoChuyen;
	}

	@Override
	public void setKhoChuyen(String khoChuyen) {
		// TODO Auto-generated method stub
		this.khoChuyen = khoChuyen;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.nguoiTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoiTao = nguoiTao;
	}

	@Override
	public String getNguoiSua() {
		// TODO Auto-generated method stub
		return this.nguoiSua;
	}

	@Override
	public void setNguoiSua(String nguoiSua) {
		// TODO Auto-generated method stub
		this.nguoiSua = nguoiSua;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangThai;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangThai = trangThai;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.ngayTao;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngayTao = ngayTao;
	}

	@Override
	public String getNgaySua() {
		// TODO Auto-generated method stub
		return this.ngaySua;
	}

	@Override
	public void setNgaySua(String ngaySua) {
		// TODO Auto-generated method stub
		this.ngaySua = ngaySua;
	}

	@Override
	public String getLyDo() {
		// TODO Auto-generated method stub
		return this.lyDo;
	}

	@Override
	public void setLyDo(String lyDo) {
		// TODO Auto-generated method stub
		this.lyDo = lyDo;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public ResultSet getRsKhoChuyen() {
		// TODO Auto-generated method stub
		return this.rsKhoChuyen;
	}

	@Override
	public void CreateRsKhoChuyen() {
		// TODO Auto-generated method stub
		String query = "SELECT PK_SEQ,TENKHO FROM KHO";
		this.rsKhoChuyen = db.get(query);
	}

	@Override
	public ResultSet getRsKhoNhan() {
		// TODO Auto-generated method stub
		return this.rsKhoNhan;
	}

	@Override
	public void CreateRsKhoNhan() {
		// TODO Auto-generated method stub
		String query = "SELECT PK_SEQ,TENKHO FROM KHO";
		this.rsKhoNhan = db.get(query);

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT PK_SEQ,NGAYCHUYENKHO,LYDO,KHOCHUYEN,KHONHAN FROM CHUYENKHO WHERE PK_SEQ=" + this.id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.ngayChuyenKho = rs.getString("NGAYCHUYENKHO");
				this.lyDo = rs.getString("LYDO");
				this.khoChuyen = rs.getString("KHOCHUYEN");
				this.khoNhan = rs.getString("KHONHAN");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		CreateRsKhoChuyen();
		CreateRsKhoNhan();
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		this.trangThai = "0";
		this.ngayTao = this.getDateTime();
		try {
			db.getConnection().setAutoCommit(false);
			String query = "INSERT INTO CHUYENKHO(NGAYCHUYENKHO,KHOCHUYEN,KHONHAN,NGUOITAO,TRANGTHAI,NGAYTAO,LYDO) VALUES('"
					+ this.ngayChuyenKho + "'," + this.khoChuyen + "," + this.khoNhan + "," + this.nguoiTao + ","
					+ this.trangThai + ",'" + this.ngayTao + "','" + this.lyDo + "')";
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh : " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception ex) {
			db.update("rollback");
			this.msg = "Lỗi: " + ex.getMessage();
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		this.ngaySua = this.getDateTime();
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE CHUYENKHO" + " SET NGAYCHUYENKHO='" + this.ngayChuyenKho + "',LYDO=N'" + this.lyDo
					+ "',KHOCHUYEN=" + this.khoChuyen + ",KHONHAN=" + this.khoNhan + ",NGUOISUA=" + this.nguoiSua
					+ ",NGAYSUA='" + this.ngaySua + "'" + " WHERE PK_SEQ =" + this.id;
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh :" + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception ex) {
			db.update("rollback");
			this.msg = "Lỗi: " + ex.getMessage();
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<ISanPham> getListSP() {
		// TODO Auto-generated method stub
		return this.listSP;
	}

	@Override
	public void setListSP(List<ISanPham> listSP) {
		// TODO Auto-generated method stub
		this.listSP = listSP;
	}

}
