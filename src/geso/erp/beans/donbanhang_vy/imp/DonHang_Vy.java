package geso.erp.beans.donbanhang_vy.imp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.banhang.imp.Sanpham;
import geso.erp.beans.donbanhang_vy.IDonHangList_Vy;
import geso.erp.beans.donbanhang_vy.IDonHang_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;
import geso.erp.db.sql.dbutils;

public class DonHang_Vy implements IDonHang_Vy {
	String userId;
	String maDonHang;
	String maKhachHang;
	String tenKhachHang;
	String ngayChungTu;
	String msg;
	String trangThai;
	float tongTien;
	ResultSet resultSet;
	dbutils dbutils;
	ResultSet resultSetKH;
	
	private List<ISanPham_Vy> danhSachSanPham;
	
	@Override
	public List<ISanPham_Vy> getDanhSachSanPham() {
		return danhSachSanPham;
	}
	
	@Override
	public void setDanhSachSanPham(List<ISanPham_Vy> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}
	

	public DonHang_Vy() {
		super();
		this.userId = "";
		this.maKhachHang = "";
		this.ngayChungTu = "";
		this.msg = "";
		this.trangThai = "";
		this.dbutils = new dbutils();
		this.danhSachSanPham = new ArrayList<ISanPham_Vy>();
	}
	
	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getNgayChungTu() {
		return ngayChungTu;
	}

	public void setNgayChungTu(String ngayChungTu) {
		this.ngayChungTu = ngayChungTu;
	}

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public ResultSet getDataList() {
		return this.resultSet;
	}

	@Override
	public String getMaDonHang() {
		return this.maDonHang;
	}

	@Override
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	
	@Override
	public void CreateRsKH() {
		//String queryKH = "SELECT PK_SEQ AS MAKHACHHANG, TEN AS TENKHACHHANG FROM KHACHHANG";
		String queryKH = "SELECT PK_SEQ AS MAKHACHHANG, TEN AS TENKHACHHANG FROM KHACHHANG";
		this.resultSetKH = dbutils.get(queryKH);	
	}
	@Override
	public ResultSet getResultSetKH() {
		return this.resultSetKH;
	}
	
	
	@Override
	public void init(String search) {
		String query="";
		if(search.length() >0){
			query= search;
		}else{
			try{
				query="SELECT BH.PK_SEQ AS MADONHANG, "+
									"KH.PK_SEQ AS MAKHACHHANG, "+ 
									"KH.TEN AS TENKHACHHANG, "+ 
									"BH.NGAYCHUNGTU, "+ 
									"BH.TONGTIEN "+
							"FROM BANHANG BH "+
							"LEFT JOIN KHACHHANG KH "+
							"ON BH.KHACHHANG_FK = KH.PK_SEQ "+
							"WHERE BH.PK_SEQ = "+ this.maDonHang;
				ResultSet resultSet = dbutils.get(query);
				if(resultSet.next()){
					this.maDonHang = resultSet.getString("MADONHANG");
					this.maKhachHang = resultSet.getString("MAKHACHHANG");
					this.tenKhachHang = resultSet.getString("TENKHACHHANG");
					this.ngayChungTu = resultSet.getString("NGAYCHUNGTU");
					this.tongTien = resultSet.getFloat("TONGTIEN");
					
				}
				resultSet.close();
				
				query = "SELECT DV.ten AS DONVITINH, DHSP.DONHANG_FK AS PK_SEQDONHANG, DHSP.SANPHAM_FK AS PK_SEQSANPHAM, " +
						"DHSP.SOLUONG,DHSP.DONGIA AS GIABAN, DHSP.THANHTIEN, SP.TEN AS TENSANPHAM, SP.MA AS MASANPHAM " +
						"FROM DONHANG_SANPHAM DHSP " +
						"INNER JOIN SANPHAM SP ON SP.PK_SEQ=DHSP.SANPHAM_FK " +
						"LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK " +
						"WHERE DHSP.DONHANG_FK=" + this.getMaDonHang();
				
				this.danhSachSanPham.clear();
				
				ResultSet rsDanhSachSanPham = dbutils.get(query);
				while(rsDanhSachSanPham.next()){
					ISanPham_Vy sp = new SanPham_Vy(rsDanhSachSanPham.getString("PK_SEQSANPHAM"),
													rsDanhSachSanPham.getString("MASANPHAM"),
													rsDanhSachSanPham.getString("DONVITINH"),
													rsDanhSachSanPham.getString("TENSANPHAM"),
													rsDanhSachSanPham.getInt("SOLUONG"),
													rsDanhSachSanPham.getFloat("GIABAN"));
					
					this.danhSachSanPham.add(sp);
				}
				rsDanhSachSanPham.close();
				
			}catch(Exception er){
				er.printStackTrace();
			}
		}
	}

	@Override
	public void DBclose() {
		this.dbutils.shutDown();
	}

	@Override
	public boolean save() {
		String query = "SELECT PK_SEQ FROM BANHANG WHERE PK_SEQ = " + this.maDonHang;
		ResultSet rs = dbutils.get(query);

		try{
			if (tongTien <= 0.0 || maKhachHang.equals("") || this.userId.length() == 0) {
				throw new Exception();
			}
			if (rs.next()) {
				dbutils.getConnection().setAutoCommit(false);
				query = "UPDATE BANHANG SET KHACHHANG_FK = '"+this.maKhachHang+"', " +
											"NGAYCHUNGTU = '"+this.ngayChungTu+"', " +
											"TONGTIEN = "+this.tongTien+" " +
						"WHERE PK_SEQ = '"+this.maDonHang+"'";
				
				if(!dbutils.update(query)){
					dbutils.getConnection().rollback();
					this.msg="Không thể thực hiện dòng lệnh ; "+query + "\n";
					return false;	
				}
				
				String deleteQuery = "DELETE DONHANG_SANPHAM WHERE DONHANG_FK = " + this.maDonHang;
					/*total_thanhtien+=  Math.round( sp.getSoLuong()* sp.getGiaBan() *1000)/1000 ;*/
					if(!dbutils.update(deleteQuery)){
						dbutils.getConnection().rollback();
						this.msg="Không thể thực hiện dòng lệnh ; "+query + "\n";
						return false;	
					}
					for(int i=0;i < this.danhSachSanPham.size();i++){
						ISanPham_Vy sp=this.danhSachSanPham.get(i);
						String insertQuery="INSERT INTO DONHANG_SANPHAM (DONHANG_FK, " +
															"SANPHAM_FK, " +
															"SOLUONG, " +
															"DONGIA, " +
															"THANHTIEN )" +
											"VALUES" + "("+this.maDonHang+", " + 
														sp.getPk_seq()+", " +
														sp.getSoLuong()+"," +
														sp.getGiaBan()+", " +
														Math.round(sp.getSoLuong()*sp.getGiaBan()) +")";
						
						if(!dbutils.update(insertQuery)){
							dbutils.getConnection().rollback();
							this.msg="Không thể thực hiện dòng lệnh ; "+query + "\n";
							return false;	
						}
					}
					
					dbutils.getConnection().commit();
					dbutils.getConnection().setAutoCommit(true);
					return true;
					
				} else {
					dbutils.getConnection().setAutoCommit(false);
					query = "INSERT INTO BANHANG (KHACHHANG_FK, " +
												"NGUOITAO, " +
												"NGAYTAO, " +
												"NGAYCHUNGTU, " +
												"TRANGTHAI, " +
												"TONGTIEN) " +
										"VALUES " + "('"+this.maKhachHang+"', " +
													"'"+this.userId+"', " +
													"SYSDATETIME(), " +
													"'"+this.ngayChungTu+"', " +
													"'"+this.trangThai+"', " +
													"'"+this.tongTien+"')";
					//rs = dbutils.get(query);

					if(!dbutils.update(query)){
						dbutils.getConnection().rollback();
						this.msg="Không thể thực hiện dòng lệnh ; "+query + "\n";
						return false;	
					}
				
					
					String PK_SEQCurrent="";
					query = "SELECT SCOPE_IDENTITY() AS MADONHANG";
					ResultSet rsDmh = dbutils.get(query);
					if (rsDmh.next()){
						PK_SEQCurrent = rsDmh.getString("MADONHANG");
					}
					rsDmh.close();
					for(int i=0;i < this.danhSachSanPham.size();i++){
						ISanPham_Vy sp=this.danhSachSanPham.get(i);
						query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK, " +
															"SANPHAM_FK, " +
															"SOLUONG, " +
															"DONGIA, " +
															"THANHTIEN )" +
											"VALUES" + "("+PK_SEQCurrent+", " + 
														sp.getPk_seq()+", " +
														sp.getSoLuong()+"," +
														sp.getGiaBan()+", " +
														Math.round(sp.getSoLuong()*sp.getGiaBan()) +")";
						
						if(!dbutils.update(query)){
							dbutils.getConnection().rollback();
							this.msg="Không thể thực hiện dòng lệnh ; "+query + "\n";
							return false;	
						}
						
						dbutils.getConnection().commit();
						dbutils.getConnection().setAutoCommit(true);

					}
				}
				return true;
			}catch (Exception e) {
			dbutils.update("rollback");
			this.msg += "Lỗi : " + e.getMessage() + "\n";
			e.printStackTrace();
			if (this.tongTien <= 0.0) {
				this.msg += "Tổng tiền không thể âm hoặc bằng không \n";
			}
			if (this.maKhachHang.equals("")) {
				this.msg += "Khách hàng không được thiếu \n";
			}
			if (this.userId.length() == 0) {
				this.msg += "Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
				
	}

	@Override
	public boolean deleteDonHang() {
		// TODO Auto-generated method stub
		try {
			dbutils.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ = " + this.maDonHang;
			if (dbutils.updateReturnInt(query) != 1) {
				dbutils.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			dbutils.getConnection().commit();
			dbutils.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception er) {
			dbutils.update("rollback");
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean chotDonHang() {
		try {
			dbutils.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ = " + this.maDonHang;
			if (dbutils.updateReturnInt(query) != 1) {
				dbutils.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			dbutils.getConnection().commit();
			dbutils.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception er) {
			dbutils.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
}
