package geso.erp.beans.donbanhang_duongnguyen.Imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.donbanhang_duongnguyen.IDonHang;
import geso.erp.beans.donbanhang_duongnguyen.ISanPham;
import geso.erp.db.sql.dbutils;

public class DonHang implements IDonHang {
	private String id;
	private String ma;
	private String ngayCT;
	private String tenKH;
	private String trangThai;
	private float tongTien;
	private String ngayTao;
	private String nguoiTao;
	private String userId;
	private String msg;
	private ResultSet rsKH;
	private ResultSet rsData;
	private List<ISanPham> listSP;
	dbutils db;

	public DonHang() {
		this.id = "";
		this.ma = "";
		this.ngayCT = "";
		this.tenKH = "";
		this.trangThai = "";
		this.tongTien = 0;
		this.ngayTao = "";
		this.nguoiTao = "";
		this.userId = "";
		this.msg = "";
		this.listSP = new ArrayList<ISanPham>();
		this.db = new dbutils();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma = ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setNgayCT(String ngay) {
		// TODO Auto-generated method stub
		this.ngayCT = ngay;
	}

	@Override
	public String getNgayCT() {
		// TODO Auto-generated method stub
		return this.ngayCT;
	}

	@Override
	public void setTenKH(String tenKH) {
		// TODO Auto-generated method stub
		this.tenKH = tenKH;
	}

	@Override
	public String getTenKH() {
		// TODO Auto-generated method stub
		return this.tenKH;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangThai = trangThai;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangThai;
	}

	@Override
	public void setTongTien(float tongTien) {
		// TODO Auto-generated method stub
		this.tongTien = tongTien;
	}

	@Override
	public float getTongTien() {
		// TODO Auto-generated method stub
		return this.tongTien;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngayTao = ngayTao;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.ngayTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoiTao = nguoiTao;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.nguoiTao;
	}

	@Override
	public void setRsData(ResultSet rsData) {
		// TODO Auto-generated method stub
		this.rsData = rsData;
	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			String query = "select pk_seq,ngaychungtu,trangthai,khachhang_fk,tongtien from banhang where pk_seq="
					+ this.id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.ngayCT = rs.getString("ngaychungtu");
				this.trangThai = rs.getString("trangthai");
				this.tenKH = rs.getString("khachhang_fk") == null ? "" : rs.getString("khachhang_fk");
				this.tongTien = rs.getFloat("tongtien");
			}
			query = " SELECT DHSP.DONGIA,DHSP.SOLUONG,SP.PK_SEQ,SP.MA,SP.TEN,DV.TEN AS DONVI "+
					 " FROM [BANHANG_SANPHAM] DHSP INNER JOIN SANPHAM SP ON SP.PK_SEQ=DHSP.SANPHAM_FK "+
					 " LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK "+
					 " WHERE BANHANG_FK= "+this.id;
			listSP.clear();
			ResultSet rsSP = db.get(query);
			while (rsSP.next()){
				ISanPham sp = new SanPham();
				sp.setId(rsSP.getString("pk_seq"));
				sp.setTenSP(rsSP.getString("ten"));
				sp.setMaSP(rsSP.getString("ma"));
				sp.setDVT(rsSP.getString("donvi"));
				sp.setDonGia(rsSP.getDouble("dongia"));
				sp.setSoLuong(rsSP.getDouble("soluong"));
				listSP.add(sp);
			}
			rsSP.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void CreateRsKhachHang() {
		// TODO Auto-generated method stub
		String query = "select pk_seq,ten from KHACHHANG";
		this.rsKH = db.get(query);
	}

	@Override
	public ResultSet getRsKhachHang() {
		// TODO Auto-generated method stub
		return this.rsKH;
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
			String query = "insert into banhang(ngaychungtu,trangthai,khachhang_fk,nguoitao,ngaytao,tongtien)values"
					+ "('" + this.ngayCT + "','" + this.trangThai + "','" + this.tenKH + "','" + this.nguoiTao + "','"
					+ this.ngayTao + "'," + this.tongTien + ")";
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
		try {
			db.getConnection().setAutoCommit(false);
			String query = "update banhang set ngaychungtu='" + this.ngayCT + "',trangthai='" + this.trangThai
					+ "',khachhang_fk='" + this.tenKH + "',tongtien=" + this.tongTien + " where pk_seq = " + this.id;
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
	public void DBclose() {
		// TODO Auto-generated method stub

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
