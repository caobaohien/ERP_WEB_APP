package geso.erp.beans.chuyenkho_vy.imp;

import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.donbanhang_vy.IDonHangList_Vy;
import geso.erp.beans.kho_vy.IKho_Vy;
import geso.erp.beans.kho_vy.imp.Kho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;
import geso.erp.util.UtilityKho_Vy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhieuChuyenKho_Vy extends Phan_Trang implements IPhieuChuyenKho_Vy{
	private String PK_SEQ;
	private String ngayChuyenKho;
	private IKho_Vy khoNhan;
	private IKho_Vy khoChuyen;
	private String nguoiTao;
	private String nguoiSua;
	private String ngayTao;
	private String ngaySua;
	private String trangThai;
	private String lyDo;
	private String message;
	private String userId;
	private List<ISanPham_Vy> danhSachSanPham;
	private UtilityKho_Vy utilityKho = new UtilityKho_Vy();
	private boolean capNhat;
	private boolean hienThi;
	
	
	public PhieuChuyenKho_Vy(){
		this.PK_SEQ = "";
		this.ngayChuyenKho = "";
		this.ngayTao = "";
		this.setKhoNhan(new Kho_Vy());
		this.setKhoChuyen(new Kho_Vy());
		this.nguoiTao = "";
		this.nguoiSua = "";
		this.trangThai = "";
		this.lyDo = "";
		this.danhSachSanPham = new ArrayList<ISanPham_Vy>();
		this.message="";
	}
	
	
	
	public PhieuChuyenKho_Vy(String pK_SEQ) {
		super();
		PK_SEQ = pK_SEQ;
		this.ngayChuyenKho = "";
		this.ngayTao = "";
		this.setKhoNhan(new Kho_Vy());
		this.setKhoChuyen(new Kho_Vy());
		this.nguoiTao = "";
		this.nguoiSua = "";
		this.trangThai = "";
		this.lyDo = "";
		this.capNhat = false;
		this.hienThi = false;
		this.danhSachSanPham = new ArrayList<ISanPham_Vy>();
		this.message="";
	}



	public PhieuChuyenKho_Vy(String pK_SEQ, IKho_Vy khoNhan, IKho_Vy khoChuyen,
			String trangThai) {
		super();
		PK_SEQ = pK_SEQ;
		this.ngayTao = "";
		this.khoNhan = khoNhan;
		this.khoChuyen = khoChuyen;
		this.trangThai = trangThai;
		this.lyDo = "";
		this.capNhat = false;
		this.hienThi = false;
		this.danhSachSanPham = new ArrayList<ISanPham_Vy>();
		this.message="";
	}
	
	
	@Override
	public boolean isCapNhat() {
		return capNhat;
	}


	@Override
	public void setCapNhat(boolean capNhat) {
		this.capNhat = capNhat;
	}


	@Override
	public boolean isHienThi() {
		return hienThi;
	}

	@Override
	public void setHienThi(boolean hienThi) {
		this.hienThi = hienThi;
	}

	@Override
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	@Override
	public void setPK_SEQ(String pK_SEQ) {
		PK_SEQ = pK_SEQ;
	}
	@Override
	public String getNgayChuyenKho() {
		return ngayChuyenKho;
	}
	@Override
	public void setNgayChuyenKho(String ngayChuyenKho) {
		this.ngayChuyenKho = ngayChuyenKho;
	}
	@Override
	public IKho_Vy getKhoNhan() {
		return khoNhan;
	}
	@Override
	public void setKhoNhan(IKho_Vy khoNhan) {
		this.khoNhan = khoNhan;
	}
	@Override
	public IKho_Vy getKhoChuyen() {
		return khoChuyen;
	}
	@Override
	public void setKhoChuyen(IKho_Vy khoChuyen) {
		this.khoChuyen = khoChuyen;
	}
	@Override
	public String getNguoiTao() {
		return nguoiTao;
	}
	@Override
	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}
	@Override
	public String getNguoiSua() {
		return nguoiSua;
	}
	@Override
	public void setNguoiSua(String nguoiSua) {
		this.nguoiSua = nguoiSua;
	}
	
	@Override
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	

	@Override
	public String getNgayTao() {
		return ngayTao;
	}

	@Override
	public String getNgaySua() {
		return ngaySua;
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
	public String getLyDo() {
		return lyDo;
	}
	@Override
	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}


	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}
	
	@Override
	public List<ISanPham_Vy> getDanhSachSanPham() {
		return danhSachSanPham;
	}
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public void setDanhSachSanPham(List<ISanPham_Vy> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}

	@Override
	public UtilityKho_Vy getUtilityKho() {
		return utilityKho;
	}
	
	@Override
	public void init() {
		String query = "";
			try{
				query = "SELECT CK.PK_SEQ, CK.NGAYTAO, CK.LYDO, CK.KHOCHUYEN, CK.KHONHAN, " +
								"SP.PK_SEQ AS SPPK_SEQ, SP.MA AS MASANPHAM, SP.TEN AS TENSANPHAM, " +
								"DV.TEN AS DONVITINH, KSP.SOLUONG AS SOLUONGTON, CKSP.SOLUONG AS SOLUONGCHUYEN " +
								"FROM CHUYENKHO CK " +
								"LEFT JOIN CHUYENKHO_SANPHAM CKSP ON CK.PK_SEQ = CKSP.CHUYENKHO_FK   " +
								"LEFT JOIN ERP_SANPHAM SP ON CKSP.SANPHAM_FK = SP.PK_SEQ  " +
								"LEFT JOIN ERP_DONVI DV ON DV.PK_SEQ = SP.DONVI_FK " +
								"LEFT JOIN KHO_SANPHAM KSP ON KSP.SANPHAM_FK = SP.PK_SEQ AND KSP.KHO_FK = CK.KHOCHUYEN " +
								"WHERE CK.PK_SEQ = "+this.PK_SEQ;
				ResultSet resultSet = utilityKho.getDbutils().getScrol(query);
				this.danhSachSanPham.clear();
				if(resultSet.next()){
					this.ngayTao = resultSet.getString("NGAYTAO");
					this.lyDo = resultSet.getString("LYDO");
					this.khoChuyen.setPK_SEQ(resultSet.getString("KHOCHUYEN"));
					this.khoNhan.setPK_SEQ(resultSet.getString("KHONHAN"));
				}
				resultSet.beforeFirst();
				while(resultSet.next()){
					String PK_SEQSanPham = resultSet.getString("SPPK_SEQ");
					ISanPham_Vy sp = new SanPham_Vy(PK_SEQSanPham,
													resultSet.getString("MASANPHAM"),
													resultSet.getString("DONVITINH"),
													resultSet.getString("TENSANPHAM"),
													resultSet.getInt("SOLUONGCHUYEN"));
					
					ISanPham_Vy spKho = new SanPham_Vy(resultSet.getString("SPPK_SEQ"),
							resultSet.getString("MASANPHAM"),
							resultSet.getString("DONVITINH"),
							resultSet.getString("TENSANPHAM"),
							resultSet.getInt("SOLUONGTON"));
					this.khoChuyen.getDanhSachSanPham().add(spKho);
					this.danhSachSanPham.add(sp);
					/*this.message += "Phiếu chuyển kho số "+PK_SEQSanPham+"";*/
				}
				
				resultSet.close();
			}catch(Exception er){
				er.printStackTrace();
			}
		
	}

	@Override
	public boolean save() {
		List<ISanPham_Vy> dsNotAvailable = this.checkNotAvailable();
		boolean ketQua = false;

		try {
			if (this.khoChuyen.getPK_SEQ().equals(this.khoNhan.getPK_SEQ())) {
				throw new Exception();
			}
			if ( dsNotAvailable != null ) {
				ketQua = this.PK_SEQ.length() != 0 ? this.capNhat() : this.them();
				ketQua = true;
			} else {
				for (int i = 0; i < dsNotAvailable.size(); i++) {
					ISanPham_Vy sanPham = dsNotAvailable.get(i);
					sanPham.init();
					int available = khoChuyen.getSoLuongAvailable(sanPham);
					this.message += sanPham.getTenSanPham() + " just "+ available +" not available.  \n";
				}
			}
		} catch (Exception e) {
			if (khoChuyen.getPK_SEQ().equals(khoNhan.getPK_SEQ())){ 
				this.message += "Kho nhận và kho chuyển không thể cùng là một kho\n";
			}
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public boolean insertCHUYENKHO_SANPHAM(ISanPham_Vy elementSanPham) throws SQLException {
		String query="INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK, SANPHAM_FK, SOLUONG)" +
		"VALUES" + "("+this.getPK_SEQ()+", "+elementSanPham.getPk_seq()+", "+elementSanPham.getSoLuong()+")";
			if(!utilityKho.getDbutils().update(query)){
				utilityKho.getDbutils().getConnection().rollback();
				utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
				return false;	
			}
		return true;
	}
	
	public String insertCHUYENKHO() {
		IKho_Vy khoChuyen = new Kho_Vy(this.khoChuyen.getPK_SEQ());
		this.setTrangThai("0");
		String PK_SEQCurrent=null;
		try {
			String query = "INSERT INTO CHUYENKHO (KHONHAN, " +
										"KHOCHUYEN, " +
										"NGUOITAO, " +
										"NGAYTAO, " +
										"TRANGTHAI, " +
										"LYDO) " +
								"VALUES " + "('"+this.khoNhan.getPK_SEQ()+"', " +
											"'"+this.khoChuyen.getPK_SEQ()+"', " +
											"'"+this.userId+"', " +
											"'"+this.ngayTao+"', " +
											"'"+this.trangThai+"', " +
											"N'"+this.lyDo+"')";
			
			if(!utilityKho.getDbutils().update(query)){
				utilityKho.getDbutils().getConnection().rollback();
				utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
				return null;	
			}

			query = "SELECT SCOPE_IDENTITY() AS MAPHIEUCHUYEN";
			ResultSet resultSet = utilityKho.getDbutils().get(query);
			
			if (resultSet.next()){
				PK_SEQCurrent = resultSet.getString("MAPHIEUCHUYEN");
			}
			resultSet.close();
			
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			this.message += "Thêm phiếu chuyển kho thất bại";
			return null;
		}
		return PK_SEQCurrent;
	}
	
	public boolean them() {
		try {
			String PK_SEQCurrent = this.insertCHUYENKHO();
			if (PK_SEQCurrent != null) {
				this.setPK_SEQ(PK_SEQCurrent);
				//Insert vao CHUYENKHO_SANPHAM va cap nhat KHO_SANPHAM
				khoChuyen.loadThongTinKHO_SANPHAM();
				this.trangThai = "0";
				
				utilityKho.getDbutils().getConnection().setAutoCommit(false);
				Iterator<ISanPham_Vy> iteratorSanPham = this.getDanhSachSanPham().iterator();
								
				while (iteratorSanPham.hasNext()) {
					ISanPham_Vy elementSanPham = iteratorSanPham.next();
					this.insertCHUYENKHO_SANPHAM(elementSanPham);
					System.out.println("GHI NHẬN PHIẾU CHUYỂN KHO VÀO KHO CHUYỂN");
					khoChuyen.updateKHO_SANPHAM(elementSanPham);
				}
			}
			utilityKho.getDbutils().getConnection().commit();
			utilityKho.getDbutils().getConnection().setAutoCommit(true);
		
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			this.message += "Thêm phiếu chuyển kho thất bại";
			return false;
		}
		return true;
	}
	
	public boolean updateCHUYENKHO() {
		String query = "UPDATE CHUYENKHO SET KHONHAN = '"+this.khoNhan.getPK_SEQ()+"', " +
											"KHOCHUYEN = "+this.khoChuyen.getPK_SEQ()+", " +
											"NGUOISUA = '"+this.userId+"'"+", " +
											"NGAYSUA = SYSDATETIME(), "+
											"NGAYTAO = SYSDATETIME(), "+
											"LYDO = '"+this.lyDo+"' " +
											"WHERE PK_SEQ = " + this.getPK_SEQ();
		try {
			if(!utilityKho.getDbutils().update(query)){
				utilityKho.getDbutils().getConnection().rollback();
				utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
				return false;
			}
			return true;
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteCHUYENKHO_SANPHAM(ISanPham_Vy sanPham) throws SQLException {
		String query = "";
		query = "DELETE CHUYENKHO_SANPHAM WHERE CHUYENKHO_FK = "+this.getPK_SEQ()+" AND SANPHAM_FK = "+sanPham.getPk_seq();
		if(!utilityKho.getDbutils().update(query)){
			utilityKho.getDbutils().getConnection().rollback();
			utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
			return false;	
		}
		return true;
	}
	
	public boolean deleteCHUYENKHO_SANPHAM() throws SQLException {
		String query = "";
		query = "DELETE CHUYENKHO_SANPHAM WHERE CHUYENKHO_FK = "+this.getPK_SEQ();
		if(!utilityKho.getDbutils().update(query)){
			utilityKho.getDbutils().getConnection().rollback();
			utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
			return false;	
		}
		return true;
	}
	
	public boolean capNhat() {
		String query = "";
		IPhieuChuyenKho_Vy phieuChuyenKhoCu = new PhieuChuyenKho_Vy(this.PK_SEQ);
		phieuChuyenKhoCu.init();
		
		System.out.println("Update phieu chuyen kho so "+this.PK_SEQ);
		try {
			utilityKho.getDbutils().getConnection().setAutoCommit(false);
			this.deleteCHUYENKHO_SANPHAM();
			//Khoi phuc lai thong so cua KHO_SANPHAM
			String maKhoChuyenTruocDo = phieuChuyenKhoCu.getKhoChuyen().getPK_SEQ();
			
			String maKhoChuyenSau = this.getKhoChuyen().getPK_SEQ();
			System.out.println("maKhoChuyenTruocDo "+maKhoChuyenTruocDo+" maKhoChuyenSau "+maKhoChuyenSau);

			IKho_Vy khoChuyen = new Kho_Vy();
			IKho_Vy khoChuyenMoi = new Kho_Vy(maKhoChuyenSau);
			if ( maKhoChuyenTruocDo.equals(maKhoChuyenSau) ) {
				khoChuyen.setPK_SEQ(maKhoChuyenSau);
			} else {
				khoChuyen.setPK_SEQ(maKhoChuyenTruocDo);
			}
			khoChuyenMoi.loadThongTinKHO_SANPHAM();
			khoChuyen.loadThongTinKHO_SANPHAM();
			
			int booked = 0, available = 0;
			int soLuongChuyenCu = 0, soLuongChuyenMoi = 0;
			int indexSanPhamCu = -1, indexSanPhamMoi = -1;
			//Danh sach san pham truoc khi update
			List<ISanPham_Vy> dsSanPhamCu = phieuChuyenKhoCu.getDanhSachSanPham();
			//Danh sach san pham sau khi update
			List<ISanPham_Vy> dsSanPhamMoi = this.getDanhSachSanPham();

			Iterator<ISanPham_Vy> iteratorSanPhamMoi = dsSanPhamMoi.iterator();
			while (iteratorSanPhamMoi.hasNext()) {
				ISanPham_Vy elementSanPhamMoi = iteratorSanPhamMoi.next();
				this.insertCHUYENKHO_SANPHAM(elementSanPhamMoi);
			}
			Iterator<ISanPham_Vy> iteratorSanPhamTrongKho = khoChuyen.getDanhSachSanPham().iterator();
			while (iteratorSanPhamTrongKho.hasNext()) {
				ISanPham_Vy elementSanPhamTrongKho = iteratorSanPhamTrongKho.next();
				
				indexSanPhamMoi = utilityKho.timSanPham(dsSanPhamMoi, elementSanPhamTrongKho);
				soLuongChuyenMoi = indexSanPhamMoi == -1 ? 0 : dsSanPhamMoi.get(indexSanPhamMoi).getSoLuong();
				System.out.println("soLuongChuyenMoi"+soLuongChuyenMoi);
				indexSanPhamCu = utilityKho.timSanPham(dsSanPhamCu, elementSanPhamTrongKho);
				soLuongChuyenCu = indexSanPhamCu == -1 ? 0 : dsSanPhamCu.get(indexSanPhamCu).getSoLuong();
				System.out.println("soLuongChuyenCu"+soLuongChuyenCu);
				
				
				if ( maKhoChuyenTruocDo.equals(maKhoChuyenSau) ) {
					booked = khoChuyen.getSoLuongBooked(elementSanPhamTrongKho) - soLuongChuyenCu + soLuongChuyenMoi;
					available = khoChuyen.getSoLuongAvailable(elementSanPhamTrongKho) + soLuongChuyenCu - soLuongChuyenMoi;
					khoChuyen.updateKHO_SANPHAM(elementSanPhamTrongKho, booked, available);
				} else {
					khoChuyen.khoiPhucThongSo(elementSanPhamTrongKho);
					khoChuyenMoi.updateKHO_SANPHAM(elementSanPhamTrongKho);
				}
			}	
			this.updateCHUYENKHO();
			utilityKho.getDbutils().getConnection().commit();
			utilityKho.getDbutils().getConnection().setAutoCommit(true);
			return true;
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean chotPhieu() {
		try {
			this.init();
			utilityKho.getDbutils().getConnection().setAutoCommit(false);
			String newQueryUpdateTrangThai = utilityKho.getQueryUpdateTrangThai() + "1, NGUOISUA="+this.nguoiSua+" ," +
																						"NGAYSUA = SYSDATETIME(), "+
																						"NGAYCHUYENKHO = SYSDATETIME() "+
																						"WHERE PK_SEQ = " + this.getPK_SEQ();
			
			IKho_Vy khoChuyen = new Kho_Vy(this.getKhoChuyen().getPK_SEQ());
			IKho_Vy khoNhan = new Kho_Vy(this.getKhoNhan().getPK_SEQ());
			
			khoChuyen.loadThongTinKHO_SANPHAM();
			khoNhan.loadThongTinKHO_SANPHAM();
			
			int bookedKhoChuyen = 0, availableKhoChuyen = 0;
			int soLuongTonKhoChuyen = 0, soLuongTonKhoNhan = 0, availableKhoNhan = 0;
			String queryKhoNhan = "", queryKhoChuyen = "";
			
			Iterator<ISanPham_Vy> iteratorSanPham = this.getDanhSachSanPham().iterator();
			while (iteratorSanPham.hasNext()) {
				ISanPham_Vy elementSanPham = iteratorSanPham.next();
				String PK_SEQSanPham = elementSanPham.getPk_seq();
				int soLuongChuyen = elementSanPham.getSoLuong();
				System.out.println(elementSanPham.getPk_seq()+" BANDAU Booked "+bookedKhoChuyen+ " soLuongTon "+soLuongTonKhoChuyen);
				bookedKhoChuyen = khoChuyen.getSoLuongBooked(elementSanPham) - soLuongChuyen;
				soLuongTonKhoChuyen = khoChuyen.getSoLuongTon(elementSanPham) - soLuongChuyen;
				System.out.println(elementSanPham.getPk_seq()+" TRANSFER Booked "+bookedKhoChuyen+" soLuongTon "+soLuongTonKhoChuyen);

				soLuongTonKhoNhan = khoNhan.getSoLuongTon(elementSanPham) + soLuongChuyen;
				availableKhoNhan = khoNhan.getSoLuongAvailable(elementSanPham) + soLuongChuyen;
				khoChuyen.updateKHO_SANPHAM(elementSanPham, bookedKhoChuyen, -1, soLuongTonKhoChuyen);
				khoNhan.updateKHO_SANPHAM(elementSanPham, -1, availableKhoNhan, soLuongTonKhoNhan);
				
			}	
			if (utilityKho.getDbutils().updateReturnInt(newQueryUpdateTrangThai) != 1) {
				utilityKho.getDbutils().getConnection().rollback();
				this.message = "Không thể cập nhật dòng lệnh: " + newQueryUpdateTrangThai;
				return false;
			}
			utilityKho.getDbutils().getConnection().commit();
			utilityKho.getDbutils().getConnection().setAutoCommit(true);		
			return true;
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			return false;
		}	

	}
	
	@Override
	public boolean huyPhieu() {
		String newQueryUpdateTrangThai = utilityKho.getQueryUpdateTrangThai() + "2, NGUOISUA="+this.nguoiSua+" ," +
																					"NGAYSUA = SYSDATETIME() "+
																					"WHERE PK_SEQ = " + this.getPK_SEQ();
		this.init();
		IKho_Vy khoChuyen = new Kho_Vy(this.getKhoChuyen().getPK_SEQ());
		khoChuyen.loadThongTinKHO_SANPHAM();
		try {
			String query = "";
			utilityKho.getDbutils().getConnection().setAutoCommit(false);
			System.out.println(khoChuyen.getPK_SEQ());
			Iterator<ISanPham_Vy> iteratorSanPhamTrongKho = this.getDanhSachSanPham().iterator();
			int index = -1, soLuongChuyen = 0, booked = 0, available = 0;
			while (iteratorSanPhamTrongKho.hasNext()) {
				ISanPham_Vy elementSanPhamTrongKho = iteratorSanPhamTrongKho.next();
				String PK_SEQSanPhamTrongKho = elementSanPhamTrongKho.getPk_seq();
				soLuongChuyen = elementSanPhamTrongKho.getSoLuong();
				booked = khoChuyen.getSoLuongBooked(elementSanPhamTrongKho) - soLuongChuyen;
				available = khoChuyen.getSoLuongAvailable(elementSanPhamTrongKho) + soLuongChuyen;	
				
				khoChuyen.updateKHO_SANPHAM(elementSanPhamTrongKho, booked, available, -1);
				System.out.println(PK_SEQSanPhamTrongKho+" [Booked] "+booked+ " [Available] "+available);
			}
			
			if (utilityKho.getDbutils().updateReturnInt(newQueryUpdateTrangThai) != 1) {
				utilityKho.getDbutils().getConnection().rollback();
				this.message = "Không thể cập nhật dòng lệnh: " + newQueryUpdateTrangThai;
				return false;
			}
			
			utilityKho.getDbutils().getConnection().commit();
			utilityKho.getDbutils().getConnection().setAutoCommit(true);
			this.setMessage("Huy phieu thanh cong");

			return true;
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public List<ISanPham_Vy> checkNotAvailable() {
		List<ISanPham_Vy> sanPhamNotAvailable = new ArrayList<ISanPham_Vy>();
		this.getKhoChuyen().loadThongTinKHO_SANPHAM();
		if (this.getDanhSachSanPham().size() > 0) {
			String KHO_FK = this.getKhoChuyen().getPK_SEQ();
			Iterator<ISanPham_Vy> iteratorSanPham = this.getDanhSachSanPham().iterator();
			while (iteratorSanPham.hasNext()) {
				ISanPham_Vy elementSanPham = iteratorSanPham.next();
				String SANPHAM_FK = elementSanPham.getPk_seq();
				int indexSanPham = this.getKhoChuyen().timSanPham(SANPHAM_FK);

				if (indexSanPham != -1) {
					int avalableSanPham = utilityKho.selectAvailableOfEachProductAtAWarehouse(SANPHAM_FK, KHO_FK);
					if (avalableSanPham > elementSanPham.getSoLuong()) {
						System.out.println(SANPHAM_FK + " available\n");
						continue;
					} else {
						sanPhamNotAvailable.add(elementSanPham);
						System.out.println(SANPHAM_FK + " not available\n");
					}
				} else {
					this.message += " Không tìm thấy sản phẩm\n";
				}
				
			}
		} else {
			this.message += " Phiếu chuyển kho không có sản phẩm nào hoặc chưa init()\n";
			return null;
		}
		return sanPhamNotAvailable;
	}

	///Chu y ham nguy hiem
	/*public boolean capNhat() {
		System.out.println("Update phieu chuyen kho so "+this.PK_SEQ);
		String query = "";
		int booked = 0, available = 0, soLuongChuyenCu = 0, soLuongChuyenMoi = 0;
		//Lay thong tin phieu chuyen kho cu da ton tai trong csdl
		IPhieuChuyenKho_Vy phieuChuyenKhoCu = new PhieuChuyenKho_Vy(this.PK_SEQ);
		phieuChuyenKhoCu.init();
		String maKhoChuyenTruocDo = phieuChuyenKhoCu.getKhoChuyen().getPK_SEQ();
		String maKhoChuyenSau = this.getKhoChuyen().getPK_SEQ(); 
		IKho_Vy khoChuyenCu = new Kho_Vy();
		try {
			utilityKho.getDbutils().getConnection().setAutoCommit(false);
		
			//Khoi phuc lai thong so cua KHO_SANPHAM
			
			System.out.println("Tu maKhoChuyenTruocDo "+maKhoChuyenTruocDo+" den maKhoChuyenSau "+maKhoChuyenSau);
			
			if ( maKhoChuyenTruocDo.equals(maKhoChuyenSau) ) {
				khoChuyenCu.setPK_SEQ(maKhoChuyenSau);
			} else {
				khoChuyenCu.setPK_SEQ(maKhoChuyenTruocDo);
				khoChuyen.setPK_SEQ(maKhoChuyenSau);
				khoChuyen.loadThongTinKHO_SANPHAM();
			}
			khoChuyenCu.loadThongTinKHO_SANPHAM();
			
			Iterator<ISanPham_Vy> iteratorSanPhamTrongKho = khoChuyenCu.getDanhSachSanPham().iterator();
			int indexSanPhamCu = -1;
			int indexSanPhamMoi = -1;
			List<ISanPham_Vy> dsSanPhamCu = phieuChuyenKhoCu.getDanhSachSanPham();
			ISanPham_Vy sanPhamCu = null;
			//So sanh san pham trong kho voi phieu chuyen kho cu va chuyen kho moi
			//Khoi phuc lai KHO_SANPHAM
			while (iteratorSanPhamTrongKho.hasNext()) {
				ISanPham_Vy elementSanPhamTrongKho = iteratorSanPhamTrongKho.next();

				indexSanPhamCu = utilityKho.timSanPham(dsSanPhamCu, elementSanPhamTrongKho);
				if (indexSanPhamCu != -1) {
					sanPhamCu = dsSanPhamCu.get(indexSanPhamCu);
					soLuongChuyenCu = sanPhamCu.getSoLuong();
					this.deleteCHUYENKHO_SANPHAM(sanPhamCu);
					khoChuyenCu.khoiPhucThongSo(sanPhamCu);
				}			
			}
			
			List<ISanPham_Vy> dsSanPhamMoi = this.getDanhSachSanPham();
			ISanPham_Vy sanPhamMoi = null;
			
			if ( maKhoChuyenTruocDo.equals(maKhoChuyenSau) ) {
				System.out.println("maKhoChuyenTruocDo.equals(maKhoChuyenSau)");
				while (iteratorSanPhamTrongKho.hasNext()) {
					ISanPham_Vy elementSanPhamTrongKho = iteratorSanPhamTrongKho.next();
					indexSanPhamMoi = utilityKho.timSanPham(dsSanPhamMoi, elementSanPhamTrongKho);
					if (indexSanPhamMoi != -1) {
						sanPhamMoi = dsSanPhamMoi.get(indexSanPhamMoi);
						soLuongChuyenMoi = sanPhamMoi.getSoLuong();
						if (this.insertCHUYENKHO_SANPHAM(sanPhamMoi)) {
							khoChuyenCu.updateKHO_SANPHAM(sanPhamMoi);
						} else throw new Exception();
					}			
				}			
			} else { 
				iteratorSanPhamTrongKho = khoChuyen.getDanhSachSanPham().iterator();
				while (iteratorSanPhamTrongKho.hasNext()) {
					ISanPham_Vy elementSanPhamTrongKho = iteratorSanPhamTrongKho.next();
					indexSanPhamMoi = utilityKho.timSanPham(dsSanPhamMoi, elementSanPhamTrongKho);
					if (indexSanPhamMoi != -1) {
						sanPhamMoi = dsSanPhamMoi.get(indexSanPhamMoi);
						soLuongChuyenMoi = sanPhamMoi.getSoLuong();
						if (this.insertCHUYENKHO_SANPHAM(sanPhamMoi)) {
							khoChuyen.updateKHO_SANPHAM(sanPhamMoi);
						} else throw new Exception();
					}	
				}
			}
			
			this.updateCHUYENKHO();		
		
			utilityKho.getDbutils().getConnection().commit();
			utilityKho.getDbutils().getConnection().setAutoCommit(true);
			return true;
		} catch (Exception e) {
			utilityKho.getDbutils().update("rollback");
			e.printStackTrace();
			return false;
		}
	}*/

	
}
