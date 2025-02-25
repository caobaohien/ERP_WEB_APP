package geso.erp.beans.donbanhang_trang.imp;

import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.donbanhang_trang.IChiTietDonHang_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHang_Trang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.Mmap;

public class DonBanHang_Trang implements IDonBanHang_Trang {

	private String khachHang;
	private float tongTien;
	private String ngayChungTu;

	private String id;
	private String maDonHang;
	private String trangThai;
	private String nguoiTao;
	private String ngayTao;
	private String nguoiSua;
	private String ngaySua;
	private dbutils db;
	private String msg;
	private ResultSet rsKhachHang;
	private ResultSet rsDataList;// lấy tất cả thông tin lên màn hình

	// Một list chứa các SANPHAM (Mỗi phần tử trong list là các feild của
	// interface IChiTietDonHang) trong trang con
	private List<IChiTietDonHang_Trang> danhSachSanPham;

	public DonBanHang_Trang() {
		super();
		this.id = "";
		this.ngayChungTu = "";
		this.maDonHang = "";
		this.trangThai = "";
		this.khachHang = "";
		this.nguoiTao = "";
		this.ngayTao = "";
		this.nguoiSua = "";
		this.ngaySua = "";
		this.tongTien = 0;
		this.db = new dbutils();
		this.msg = "";

		// khởi tạo List SANPHAM (khởi tạo ban đầu size = 0)
		this.danhSachSanPham = new ArrayList<IChiTietDonHang_Trang>();
	}

	public DonBanHang_Trang(String id, String ngayChungTu, String maDonHang,
			String trangThai, String khachHang, String nguoiTao,
			String ngayTao, String nguoiSua, String ngaySua, float tongTien,
			dbutils db, String msg, ResultSet rsDataList) {
		super();
		this.id = id;
		this.ngayChungTu = ngayChungTu;
		this.maDonHang = maDonHang;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.nguoiTao = nguoiTao;
		this.ngayTao = ngayTao;
		this.nguoiSua = nguoiSua;
		this.ngaySua = ngaySua;
		this.tongTien = tongTien;
		this.db = db;
		this.msg = msg;
		this.rsDataList = rsDataList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}

	public String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public String getNguoiSua() {
		return nguoiSua;
	}

	public void setNguoiSua(String nguoiSua) {
		this.nguoiSua = nguoiSua;
	}

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public dbutils getDb() {
		return db;
	}

	public void setDb(dbutils db) {
		this.db = db;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}

	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}

	public ResultSet getRsDataList() {
		return rsDataList;
	}

	public void setRsDataList(ResultSet rsDataList) {
		this.rsDataList = rsDataList;
	}

	public String getNgayChungTu() {
		return ngayChungTu;
	}

	public void setNgayChungTu(String ngayChungTu) {
		this.ngayChungTu = ngayChungTu;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNgaySua() {
		return ngaySua;
	}

	public void setNgaySua(String ngaySua) {
		this.ngaySua = ngaySua;
	}

	public List<IChiTietDonHang_Trang> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void setDanhSachSanPham(List<IChiTietDonHang_Trang> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}

	@Override
	public void getDataKhachHang() {
		// TODO Auto-generated method stub
		this.rsKhachHang = this.db.get("SELECT PK_SEQ, TEN FROM KHACHHANG");
	}

	public void getMaDonHangPDF() {
		String query = "SELECT DISTINCT KH.PK_SEQ AS MA, KH.TEN AS TEN, KH.DIACHI AS DIACHI, KH.DIENTHOAI AS DIENTHOAI"
		+ " FROM KHACHHANG AS KH LEFT JOIN BANHANG AS BH ON KH.PK_SEQ = BH.KHACHHANG_FK "
		+ " WHERE BH.PK_SEQ = " + this.id;
		
		this.rsKhachHang = db.get(query);
		System.out.println("Query thong tin Output: " + query);
	}

	// Đóng kết nói DB
	public void closedb() {
		this.db.shutDown();
	}

	// Khởi tạo
	@Override
	public void init(String query) {
		// TODO Auto-generated method stub
		String sql = "";
		if (sql.length() > 0)
			query = sql;
		else {
			try {
				query = "SELECT  bh.NGAYCHUNGTU AS NGAYCHUNGTU, kh.PK_SEQ AS KHACHHANG, "
						+ " bh.TONGTIEN AS TONGTIEN"
						+ " from BANHANG AS bh INNER JOIN KHACHHANG kh ON kh.PK_SEQ = bh.KHACHHANG_FK"
						+ " where bh.PK_SEQ =" + this.id;

				ResultSet resultSet = db.get(query);
				if (resultSet.next()) {
					this.khachHang = resultSet.getString("KHACHHANG");
					this.ngayChungTu = resultSet.getString("NGAYCHUNGTU");
					this.tongTien = resultSet.getFloat("TONGTIEN");

				}
				resultSet.close();
				System.out.println("CAU LENH: " + query);

				// Hiển thị CHI TIẾT SẢN PHẨM lên trang Update
				danhSachSanPham.clear();
				query = "SELECT BHSP.BANHANG_FK AS MADONHANG, BHSP.SANPHAM_FK AS MASANPHAM, SP.MA AS MA, SP.TEN AS TEN, ISNULL(DV.TEN,'') AS DONVITINH, "
						+ " BHSP.SOLUONG AS SOLUONG, SP.SOLUONG AS SOLUONGTON, BHSP.DONGIA AS DONGIA, BHSP.THANHTIEN AS THANHTIEN"
						+ " FROM BANHANG_SANPHAM AS BHSP LEFT JOIN SANPHAM AS SP ON BHSP.SANPHAM_FK = SP.PK_SEQ"
						+ " LEFT JOIN DONVI AS DV ON DV.PK_SEQ = SP.DONVI_FK"
						+ " WHERE BHSP.BANHANG_FK =" + this.id;

				ResultSet rssp = db.get(query);
				while (rssp.next()) {
					IChiTietDonHang_Trang sp = new ChiTietDonHang_Trang();
					sp.setBanHangPK_SEQ(rssp.getString("MADONHANG"));
					sp.setSanPhamPK_SEQ(rssp.getString("MASANPHAM"));
					sp.setMaSanPham(rssp.getString("MA"));
					sp.setTenSanPham(rssp.getString("TEN"));
					sp.setDonViTinh(rssp.getString("DONVITINH"));
					sp.setSoLuong(rssp.getDouble("SOLUONG"));
					sp.setSoLuongTon(rssp.getDouble("SOLUONGTON"));
					sp.setDonGia(rssp.getDouble("DONGIA"));
					sp.setThanhTien(rssp.getDouble("THANHTIEN"));
					this.danhSachSanPham.add(sp);
					System.out.println("Cau lenh: " + query);
				}
				resultSet.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		;
		this.rsDataList = this.db.get(query);
		getDataKhachHang();
	}

	// Lấy ngày hiện tại
	private String getDateTime() {
		Date date = new Date();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s = sdf.format(date);
		return s;
	}

	@Override
	public boolean saveDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "insert into BANHANG(KHACHHANG_FK,NGAYCHUNGTU,TONGTIEN,TRANGTHAI,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA) "
					+ " values( "
					+ this.khachHang
					+ ", '"
					+ this.ngayChungTu
					+ "' , "
					+ this.tongTien
					+ ",'0','"
					+ getDateTime()
					+ "','"
					+ getDateTime()
					+ "','"
					+ this.nguoiTao
					+ "','"
					+ this.nguoiSua + "')";

			System.out.print("query save DH" + query);
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}

			String dmhCurrent = "";
			query = "select SCOPE_IDENTITY() as dmhId";
			ResultSet rsDmh = db.get(query);
			if (rsDmh.next()) {
				dmhCurrent = rsDmh.getString("dmhId");
				rsDmh.close();
			}

			/* double total_thanhtien=0; */
			for (int i = 0; i < danhSachSanPham.size(); i++) {
				IChiTietDonHang_Trang sp = danhSachSanPham.get(i);
				query = "INSERT INTO BANHANG_SANPHAM (BANHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values "
						+ "("
						+ dmhCurrent
						+ ","
						+ sp.getSanPhamPK_SEQ()
						+ ","
						+ sp.getSoLuong()
						+ ","
						+ sp.getDonGia()
						+ ","
						+ Math.round(sp.getDonGia() * sp.getSoLuong())
						+ ")";

				if (!db.update(query)) {
					db.getConnection().rollback();
					this.msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg = "Loi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deteleDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ = "
					+ this.maDonHang;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean chotDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ = "
					+ this.maDonHang;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "update BANHANG set KHACHHANG_FK='" + this.khachHang
					+ "' , NGAYCHUNGTU='" + this.ngayChungTu + "', TONGTIEN="
					+ this.tongTien + " where PK_SEQ =" + this.id;
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}

			query = "DELETE BANHANG_SANPHAM " + " WHERE BANHANG_FK = "
					+ this.id;
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}

			for (int i = 0; i < danhSachSanPham.size(); i++) {
				IChiTietDonHang_Trang sp = danhSachSanPham.get(i);
				query = "INSERT INTO BANHANG_SANPHAM (BANHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values "
						+ "("
						+ this.id
						+ ","
						+ sp.getSanPhamPK_SEQ()
						+ ","
						+ sp.getSoLuong()
						+ ","
						+ sp.getDonGia()
						+ ","
						+ Math.round(sp.getDonGia() * sp.getSoLuong()) + ")";

				if (!db.update(query)) {
					db.getConnection().rollback();
					this.msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg = "Loi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
