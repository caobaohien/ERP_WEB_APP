package geso.erp.beans.donbanhang_kiet02.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.donbanhang_kiet02.IDonHangList;
import geso.erp.db.sql.dbutils;

public class DonHangList extends Phan_Trang implements IDonHangList {

	private String maDonHang;
	private String ngayChungTu;
	private String trangThai;
	private String maKhachHang;
	private String taiKhoan;
	private String ngayTao;
	private String ngaySua;
	private float tongTien;
	private String msg;
	private ResultSet resultSetDH;
	private ResultSet resultSetKH;
	private dbutils dbutils;

	public DonHangList() {
		this.maDonHang = "";
		this.ngayChungTu = "";
		this.trangThai = "";
		this.maKhachHang = "";
		this.taiKhoan = "";
		this.ngayTao = "";
		this.ngaySua = "";
		this.tongTien = 0;
		this.msg = "";
		this.dbutils = new dbutils();
	}
	
	@Override
	public String getMaDonHang() {
		return maDonHang;
	}
	
	@Override
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	
	@Override
	public String getNgayChungTu() {
		return ngayChungTu;
	}
	
	@Override
	public void setNgayChungTu(String ngayChungTu) {
		this.ngayChungTu = ngayChungTu;
	}
	
	@Override
	public String getTrangThai() {
		return trangThai;
	}
	
	@Override
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	@Override
	public String getMaKhachHang() {
		return maKhachHang;
	}
	
	@Override
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	
	@Override
	public String getNgayTao() {
		return ngayTao;
	}
	
	@Override
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	
	@Override
	public String getNgaySua() {
		return ngaySua;
	}
	
	@Override
	public void setNgaySua(String ngaySua) {
		this.ngaySua = ngaySua;
	}
	
	@Override
	public float getTongTien() {
		return tongTien;
	}
	
	@Override
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.resultSetDH;
	}

	@Override
	public void setDataList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getListKhachHang() {
		// TODO Auto-generated method stub
		return this.resultSetKH;
	}

	@Override
	public void init(String search) {
		//Tao query
		String query = "";
		if (search.length() > 0) {
			query = search;
		}
		else {
			query = "SELECT BH.PK_SEQ AS MADONHANG, " +
							"BH.NGAYCHUNGTU AS NGAYCHUNGTU, " +
							"BH.KHACHHANG_FK AS MAKHACHHANG, " +
							"KH.TEN AS TENKHACHHANG, " +
							"ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, " +
							"BH.TONGTIEN, " +
							"BH.NGAYTAO, " +
							"BH.NGUOITAO AS MANGUOITAO, " +
							"NV.TEN AS TENNGUOITAO " +
							"FROM BANHANG BH " +
							"LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ " +
							"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ " +
							"ORDER BY MADONHANG ASC";
			/*query = "select PK_SEQ AS MADONHANG FROM BANHANG";*/
		
			
		}
		//Thuc thi cau lenh
		this.resultSetDH = dbutils.get(query);
		CreateRsKH();
	}

	@Override
	public void CreateRsKH() {
		//String queryKH = "SELECT PK_SEQ AS MAKHACHHANG, TEN AS TENKHACHHANG FROM KHACHHANG";
		String queryKH = "SELECT PK_SEQ AS MAKHACHHANG, TEN AS TENKHACHHANG FROM KHACHHANG";
		this.resultSetKH = dbutils.get(queryKH);
	}

	@Override
	public void DBclose() {
		dbutils.shutDown();
	}

	@Override
	public String getTaiKhoan() {
		// TODO Auto-generated method stub
		return this.taiKhoan;
	}

	@Override
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
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

}
