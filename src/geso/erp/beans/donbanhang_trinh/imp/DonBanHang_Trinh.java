package geso.erp.beans.donbanhang_trinh.imp;
import geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class DonBanHang_Trinh implements IDonBanHang_Trinh {
	
	
	
	private String tenKhachHang;
	private String maKhachHang;
	private String ngayTao;
	private double soTien;
	private String ngayChungTu;
	private String trangThai;
	private String idUser;
	private String message;
	private dbutils db;
	private ResultSet rsDonHang;
	private ResultSet rsKhachHang;
	private String maDonHang;
	
	private List<IChiTietDonHang_Trinh> danhsachSanPham;
	
	
	
	public String getNgayChungTu() {
		return ngayChungTu;
	}
	public void setNgayChungTu(String ngayChungTu) {
		this.ngayChungTu = ngayChungTu;
	}
	public List<IChiTietDonHang_Trinh> getDanhsachSanPham() {
		return danhsachSanPham;
	}
	public void setDanhsachSanPham(List<IChiTietDonHang_Trinh> danhsachSanPham) {
		this.danhsachSanPham = danhsachSanPham;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
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
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	public double getSoTien() {
		return soTien;
	}
	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public ResultSet getRsDonHang() {
		return rsDonHang;
	}
	public void setRsDonHang(ResultSet rsDonHang) {
		this.rsDonHang = rsDonHang;
	}
	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}
	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}
	public DonBanHang_Trinh(String tenKhachHang, String ngayTao, double soTien,
			String idUser, String message, dbutils db, ResultSet rsDonHang,
			ResultSet rsKhachHang) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.ngayTao = ngayTao;
		this.soTien = soTien;
		this.idUser = idUser;
		this.message = message;
		this.db = db;
		this.rsDonHang = rsDonHang;
		this.rsKhachHang = rsKhachHang;
	}
	
	public DonBanHang_Trinh() {
		super();
		this.tenKhachHang = "";
		this.ngayTao = "";
		this.soTien = 0;
		this.idUser = "";
		this.message = "";
		this.ngayChungTu = "";
		this.db = new dbutils();
		this.danhsachSanPham = new ArrayList<IChiTietDonHang_Trinh>();
		
	}
	@Override
	public void init(String chuoi){
		String query = "";
		String querychitiet = "";
		if(chuoi.length() > 0)
			query = chuoi;
			else{
				try {
					
					query="SELECT BANHANG.PK_SEQ AS MADONHANG , BANHANG.NGAYTAO as  NGAYTAO, BANHANG.NGAYCHUNGTU , " +
					" KHACHHANG.TEN as TENKHACHHANG,KHACHHANG.PK_SEQ AS MAKHACHHANG, isnull(BANHANG.TRANGTHAI,'1') as " +
					" TRANGTHAI, TONGTIEN as TONGTIEN, BANHANG.NGUOITAO as " +
					" TENNGUOITAO, BANHANG.NGAYTAO as NGAYTAO  FROM BANHANG INNER JOIN KHACHHANG ON " +
					" BANHANG.KHACHHANG_FK = KHACHHANG.PK_SEQ WHERE BANHANG.PK_SEQ = " + this.maDonHang;
					
					ResultSet resultSet = db.get(query);
					if(resultSet.next()){
						this.maDonHang = resultSet.getString("MADONHANG");
						this.maKhachHang = resultSet.getString("MAKHACHHANG");
						this.tenKhachHang = resultSet.getString("TENKHACHHANG");
						this.ngayTao = resultSet.getString("NGAYTAO");
						this.soTien = resultSet.getFloat("TONGTIEN");
						this.ngayChungTu = resultSet.getString("NGAYCHUNGTU");
						
					}
					resultSet.close();
					System.out.println("CAU LENH: " + query);
					//lấy chi tiết đơn hàng nếu có
					//lấy ra chi tiết đơn hàng và gán vào list
					danhsachSanPham.clear();
					querychitiet = "SELECT SANPHAM.PK_SEQ, SANPHAM.MA AS MASANPHAM, " +
							"SANPHAM.TEN AS TENSANPHAM, DONVI.TEN AS TENDONVI," +
							"DONHANG_SANPHAM.SOLUONG, DONHANG_SANPHAM.DONGIA, " +
							"DONHANG_SANPHAM.THANHTIEN, DONHANG_SANPHAM.DONHANG_FK AS MADONHANG " +
							"FROM DONHANG_SANPHAM LEFT JOIN " +
							"(SANPHAM LEFT JOIN DONVI DONVI ON DONVI.PK_SEQ = SANPHAM.DONVI_FK) " +
							"ON SANPHAM.PK_SEQ = DONHANG_SANPHAM.SANPHAM_FK " +
							"WHERE DONHANG_SANPHAM.DONHANG_FK = " + this.maDonHang;
					ResultSet rsSanPham = db.get(querychitiet);
					while(rsSanPham.next()){
						
						IChiTietDonHang_Trinh obj =new ChiTietDonHang_Trinh(rsSanPham.getString("PK_SEQ"), rsSanPham.getString("MASANPHAM"), rsSanPham.getString("MADONHANG"), Double.parseDouble(rsSanPham.getString("SOLUONG")), 
								Double.parseDouble(rsSanPham.getString("DONGIA")),
								Double.parseDouble(rsSanPham.getString("THANHTIEN")), 
								rsSanPham.getString("TENSANPHAM"), 
								rsSanPham.getString("TENDONVI"));
						
						this.danhsachSanPham.add(obj);
						
						System.out.println("CAU LENH: " + querychitiet);
					}
					rsSanPham.close();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			};
			
			this.rsDonHang = db.get(query);
			
			LayKhachHang();
	}
	public void LayKhachHang(){
		this.rsKhachHang = db.get("select KHACHHANG.PK_SEQ as MAKHACHHANG, KHACHHANG.TEN as TENKHACHHANG from KHACHHANG");
	}
	
	public void LayKhachHangTheoMa(){
		this.rsKhachHang = db.get("Select KHACHHANG.TEN, KHACHHANG.DIACHI, KHACHHANG.DIENTHOAI " +
				"FROM KHACHHANG INNER JOIN BANHANG ON KHACHHANG.PK_SEQ = BANHANG.KHACHHANG_FK " +
				"WHERE BANHANG.PK_SEQ = " + this.maDonHang);
	}
	
	@Override
	public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date); 
 }
	public String StringToDate(String chuoi){
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date date = dateFormat.parse(chuoi);
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	        
	        return dateFormat1.format(date); 

		} catch (Exception  e) {
			e.printStackTrace();
			return "";
		}
	}
	@Override
	public void closedb(){
		db.shutDown();
	}
	@Override
	public boolean insert(){
		try{
			String query = "";
			//insert vao don hang truoc
			if(this.maKhachHang == "" || this.ngayChungTu == "" || this.soTien == 0){
				throw new Exception();
			}
			else
			{
				db.getConnection().setAutoCommit(false);
				query ="INSERT INTO BANHANG (NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO, NGAYTAO,TONGTIEN) " +
						"VALUES ('" + this.ngayChungTu+"', '" + 0+"', '" + this.maKhachHang + "', '" + this.idUser + "', SYSDATETIME(), '" + this.soTien + "')";
				
				
			}
			if(db.update(query) == false){
				db.getConnection().rollback();
				this.message = "Không thể thực hiện dòng lệnh : " + query;
				return false;
				
			}
			else
			{
				//thuc hien tiep insert vao chi tiet hoa don
				String madonhangvuatao = "";
				query = "SELECT SCOPE_IDENTITY() AS MADONHANG"; //cau lenh lay ma don hang vua tao
				ResultSet rsMaDh = db.get(query);
				if(rsMaDh.next()){
					madonhangvuatao = rsMaDh.getString("MADONHANG");
				}
				rsMaDh.close();
				for (int i = 0; i < danhsachSanPham.size(); i++) {
					IChiTietDonHang_Trinh ctdh = danhsachSanPham.get(i);
					String queryctdh = "INSERT INTO DONHANG_SANPHAM (DONHANG_FK, SANPHAM_FK, SOLUONG, DONGIA, THANHTIEN) " +
							"VALUES ('" + madonhangvuatao + "', '" + ctdh.getIdSanPham() + "', '" + ctdh.getSoLuong() + "', '" + ctdh.getDonGia() + "', '" + ctdh.getThanhTien() +"')";
					
					if(db.update(queryctdh) == false){
						db.getConnection().rollback();
						this.message = "Không thể thực hiện dòng lệnh : " + query;
						return false;
					}
					
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);	
			
		}catch (Exception e) {
			db.update("rollback");
			this.message += "Lỗi : " + e.getMessage() + "\n";
			e.printStackTrace();
			if (this.soTien <= 0.0) {
				this.message += "Tổng tiền không thể âm hoặc bằng không \n";
			}
			if (this.maKhachHang.equals("")) {
				this.message += "Khách hàng không được thiếu \n";
			}
			if (this.idUser.length() == 0) {
				this.message += "Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
		return true;		
	}
	
	public boolean update(){
		try {
			
			String query = "";
			if(this.maKhachHang == "" || this.ngayChungTu == "" || this.soTien == 0){
				throw new Exception();
			}
			else
			{
				db.getConnection().setAutoCommit(false);
				query = "UPDATE BANHANG SET KHACHHANG_FK = '" + this.maKhachHang + "', TONGTIEN='" +this.soTien + "', " +
						"NGAYCHUNGTU = '" + StringToDate(this.ngayChungTu) + "', NGUOISUA ='" + this.idUser + "', " +
								"NGAYSUA =SYSDATETIME() " +
						"WHERE PK_SEQ=" + this.maDonHang;
				if(db.update(query)==false){
					db.getConnection().rollback();
					this.message = "Không thể thực hiện dòng lệnh : " + query;
					return false;
				}
				//update chi tiet don hang
					//1. xoa tat ca cac chi tiet don hang
				String queryxoa = "DELETE DONHANG_SANPHAM WHERE DONHANG_FK = " + this.maDonHang;
				if(db.update(queryxoa)==false){
					db.getConnection().rollback();
					this.message = "Không thể thực hiện dòng lệnh : " + query;
					return false;
				}
				else
				{
					//theem lai cac chi tiet don hang vao
					for (int i = 0; i < danhsachSanPham.size(); i++) {
						IChiTietDonHang_Trinh ctdh = danhsachSanPham.get(i);
						String queryctdh = "INSERT INTO DONHANG_SANPHAM (DONHANG_FK, SANPHAM_FK, SOLUONG, DONGIA, THANHTIEN) " +
								"VALUES ('" + ctdh.getMaDonHang() + "', '" + ctdh.getIdSanPham() + "', '" + ctdh.getSoLuong() + "', '" + ctdh.getDonGia() + "', '" + ctdh.getThanhTien() +"')";
						
						if(db.update(queryctdh) == false){
							db.getConnection().rollback();
							this.message = "Không thể thực hiện dòng lệnh : " + queryctdh;
							return false;
						}
						
					}
				}
				
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);	
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.message += "Lỗi : " + e.getMessage() + "\n";
			e.printStackTrace();
			if (this.soTien <= 0.0) {
				this.message += "Tổng tiền không thể âm hoặc bằng không \n";
			}
			if (this.maKhachHang.equals("")) {
				this.message += "Khách hàng không được thiếu \n";
			}
			if (this.idUser.length() == 0) {
				this.message += "Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			
			return false;
		}
		
		return true;
	}
	//xóa đơn hàng là đưa trạng thái đơn hảng về đã hủy mã 2
	@Override
	public boolean deleteDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ = " + this.maDonHang;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.message = "Không thể cập nhật dòng lệnh: " + query;
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
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ = " + this.maDonHang;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.message = "Không thể cập nhật dòng lệnh: " + query;
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
	
}
