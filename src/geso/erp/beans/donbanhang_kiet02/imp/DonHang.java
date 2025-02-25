package geso.erp.beans.donbanhang_kiet02.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.erp.beans.donbanhang_kiet02.IDonHang;
import geso.erp.beans.donbanhang_kiet02.ISanPham;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;
import geso.erp.db.sql.dbutils;

public class DonHang implements IDonHang {
	
	String userId, maDonHang, maKhachHang, tenKhachHang,
			ngayChungTu, msg, trangThai;
	float tongTien;
	ResultSet resultSet, resultSetKH;
	dbutils dbutils;

	
	
	private List<ISanPham> danhSachSanPham;
	
	public DonHang() {
		this.userId = "";
		this.maKhachHang = "";
		this.ngayChungTu = "";
		this.msg = "";
		this.trangThai = "";
		this.dbutils = new dbutils();
		this.danhSachSanPham = new ArrayList<ISanPham>();
	}
	
	public DonHang(String id) {
		this.maDonHang = id;
		this.userId = "";
		this.maKhachHang = "";
		this.ngayChungTu = "";
		this.msg = "";
		this.trangThai = "";
		this.dbutils = new dbutils();
		this.danhSachSanPham = new ArrayList<ISanPham>();
	}
	
	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return trangThai;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangThai = trangThai;
	}

	@Override
	public String getMaDonHang() {
		// TODO Auto-generated method stub
		return this.maDonHang;
	}

	@Override
	public void setMaDonHang(String maDonHang) {
		// TODO Auto-generated method stub
		this.maDonHang = maDonHang;
	}

	@Override
	public String getMaKhachHang() {
		// TODO Auto-generated method stub
		return maKhachHang;
	}

	@Override
	public void setMaKhachHang(String maKhachHang) {
		// TODO Auto-generated method stub
		this.maKhachHang = maKhachHang;
	}

	@Override
	public String getNgayChungTu() {
		// TODO Auto-generated method stub
		return ngayChungTu;
	}

	@Override
	public void setNgayChungTu(String ngayChungTu) {
		// TODO Auto-generated method stub
		this.ngayChungTu = ngayChungTu;
	}

	@Override
	public float getTongTien() {
		// TODO Auto-generated method stub
		return tongTien;
	}

	@Override
	public void setTongTien(float tongTien) {
		// TODO Auto-generated method stub
		this.tongTien = tongTien;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public List<ISanPham> getDanhSachSanPham() {
		// TODO Auto-generated method stub
		return danhSachSanPham;
	}

	@Override
	public void setDanhSachSanPham(List<ISanPham> danhSachSanPham) {
		// TODO Auto-generated method stub
		this.danhSachSanPham = danhSachSanPham;
	}

	@Override
	public void CreateRsKH() {
		// TODO Auto-generated method stub
		String queryKH = "SELECT PK_SEQ AS MAKHACHHANG, TEN AS TENKHACHHANG FROM KHACHHANG";
		this.resultSetKH = dbutils.get(queryKH);
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.resultSet;
	}

	@Override
	public ResultSet getResultSetKH() {
		// TODO Auto-generated method stub
		return this.resultSetKH;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
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
					ISanPham sp = new SanPham(rsDanhSachSanPham.getString("PK_SEQSANPHAM"),
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
		// TODO Auto-generated method stub
		this.dbutils.shutDown();
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
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
						ISanPham sp=this.danhSachSanPham.get(i);
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
													""+this.userId+", " +
													"SYSDATETIME(), " +
													"'"+this.ngayChungTu+"', " +
													"'"+this.trangThai+"', " +
													""+this.tongTien+")";
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
						ISanPham sp=this.danhSachSanPham.get(i);
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
						return true;
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
		// TODO Auto-generated method stub
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
