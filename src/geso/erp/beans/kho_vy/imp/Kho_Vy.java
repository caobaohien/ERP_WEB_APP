package geso.erp.beans.kho_vy.imp;

import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKho_Vy;
import geso.erp.beans.kho_vy.IKho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;
import geso.erp.util.UtilityKho_Vy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Kho_Vy implements IKho_Vy{
	private String PK_SEQ;
	private String maKho;
	private String tenKho;
	private String diaChi;
	private String nguoiTao;
	private String nguoiSua;
	private String ngayTao;
	private String ngaySua;
	private String trangThai;
	private String message;
	private UtilityKho_Vy utilityKho;
	
	private List<ISanPham_Vy> danhSachSanPham;
	private int[] booked;
	private int[] available;


	public Kho_Vy(String pK_SEQ) {
		super();
		PK_SEQ = pK_SEQ;
		danhSachSanPham = new ArrayList<ISanPham_Vy>();
		utilityKho = new UtilityKho_Vy();
	}

	public Kho_Vy() {
		super();
		this.PK_SEQ = "";
		this.maKho = "";
		this.tenKho = "";
		this.diaChi = "";
		this.trangThai = "";
		danhSachSanPham = new ArrayList<ISanPham_Vy>();
		utilityKho = new UtilityKho_Vy();
	}

	
	
	public Kho_Vy(String PK_SEQ, String maKho, String tenKho, String diaChi,
			String nguoiSua, String ngaySua, String trangThai, List<ISanPham_Vy> danhSachSanPham) {
		super();
		this.PK_SEQ = PK_SEQ;
		this.maKho = maKho;
		this.tenKho = tenKho;
		this.diaChi = diaChi;
		this.nguoiSua = nguoiSua;
		this.ngaySua = ngaySua;
		this.trangThai = trangThai;
		this.danhSachSanPham = danhSachSanPham;
		utilityKho = new UtilityKho_Vy();
	}
	@Override
	public String getPK_SEQ() {
		return this.PK_SEQ;
	}
	@Override
	public void setPK_SEQ(String PK_SEQ) {
		this.PK_SEQ = PK_SEQ;
	}
	@Override
	public String getMaKho() {
		return maKho;
	}
	@Override
	public void setMaKho(String maKho) {
		this.maKho = maKho;
	}
	@Override
	public String getTenKho() {
		return tenKho;
	}
	@Override
	public void setTenKho(String tenKho) {
		this.tenKho = tenKho;
	}
	@Override
	public String getDiaChi() {
		return diaChi;
	}
	@Override
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public List<ISanPham_Vy> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void setDanhSachSanPham(List<ISanPham_Vy> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}
	
	@Override
	public int getSoLuongTon(ISanPham_Vy sanPham) {
		int indexSanPham  = timSanPham(sanPham.getPk_seq());
		return this.getDanhSachSanPham().get(indexSanPham).getSoLuong();
	}
	@Override
	public int getSoLuongBooked(ISanPham_Vy sanPham) {
		int indexSanPham  = timSanPham(sanPham.getPk_seq());
		return this.getBooked()[indexSanPham];
	}
	@Override
	public int getSoLuongAvailable(ISanPham_Vy sanPham) {
		int indexSanPham  = timSanPham(sanPham.getPk_seq());
		return this.getAvailable()[indexSanPham];
	}

	public int[] getBooked() {
		return booked;
	}

	public void setBooked(int[] booked) {
		this.booked = booked;
	}

	public int[] getAvailable() {
		return available;
	}

	public void setAvailable(int[] available) {
		this.available = available;
	}

	public int  timSanPham(String PK_SEQ) {
		for (int i = 0; i < danhSachSanPham.size(); i++) {
			if (danhSachSanPham.get(i).getPk_seq().equals(PK_SEQ)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public boolean checkRangBuocSoLuongTonTrongKho(int booked, int available, ISanPham_Vy sanPham) {
		int soLuong = booked + available;
			return soLuong == this.getSoLuongTon(sanPham) ? true : false;
	}
	

	public Kho_Vy loadThongTinKHO_SANPHAM() {
		String query = "SELECT KHO_FK AS MAKHO, " +
							"SANPHAM_FK AS MASANPHAM, " +
							"SOLUONG AS SOLUONGTON, " +
							"BOOKED, " +
							"AVAILABLE " +
						"FROM KHO_SANPHAM WHERE KHO_FK = "+this.PK_SEQ;
		
		ResultSet resultSetKHO_SANPHAM = utilityKho.getDbutils().getScrol(query);
		try {
			
			while (resultSetKHO_SANPHAM.next()) {
				ISanPham_Vy sp = new SanPham_Vy();
					sp.setPk_seq(resultSetKHO_SANPHAM.getString("MASANPHAM"));
					sp.setSoLuong(resultSetKHO_SANPHAM.getInt("SOLUONGTON"));
				this.danhSachSanPham.add(sp);
				
			}
			resultSetKHO_SANPHAM.beforeFirst();
			int i = 0;
			int b[] = new int[danhSachSanPham.size()];
			int a[] = new int[danhSachSanPham.size()];
			while (resultSetKHO_SANPHAM.next()) {
				b[i] = resultSetKHO_SANPHAM.getInt("BOOKED");
				a[i] = resultSetKHO_SANPHAM.getInt("AVAILABLE");
				i++;
			}
			this.setBooked(b);
			this.setAvailable(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public boolean ghiNhanPhieuChuyenKho (ISanPham_Vy sanPham) {
		String query = "";
		int booked = 0, available = 0;
		
		System.out.println("GHI NHẬN PHIẾU CHUYỂN KHO VÀO KHO CHUYỂN");
		
		int soLuongChuyenMoi = sanPham.getSoLuong();
		booked = this.getSoLuongBooked(sanPham) + soLuongChuyenMoi;
		available = this.getSoLuongAvailable(sanPham) - soLuongChuyenMoi;
		System.out.println(sanPham.getPk_seq()+" [Booked] "+booked+ " [Available] "+available);
		
		query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
				" WHERE SANPHAM_FK = "+sanPham.getPk_seq()+" AND KHO_FK = "+this.PK_SEQ;
		
		if (this.checkRangBuocSoLuongTonTrongKho(booked, available, sanPham)){
			try {
				if(!utilityKho.getDbutils().update(query)){
					utilityKho.getDbutils().getConnection().rollback();
					utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
					return false;
				}

				System.out.println(sanPham.getPk_seq()+" Ghi nhận [Booked] "+booked+ " [Available] "+available+ 
													" soLuongChuyenMoi "+soLuongChuyenMoi);
			} catch (SQLException e) {
				utilityKho.getDbutils().update("rollback");
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean khoiPhucThongSo(ISanPham_Vy sanPhamCu) {
		String query = "";
		int booked = 0, available = 0;
		
		System.out.println("===KHÔI PHỤC KHO CHUYỂN===");
		
		int soLuongChuyenCu = sanPhamCu.getSoLuong();
		booked = this.getSoLuongBooked(sanPhamCu) - soLuongChuyenCu;
		available = this.getSoLuongAvailable(sanPhamCu) + soLuongChuyenCu;
		System.out.println(sanPhamCu.getPk_seq()+" [Booked] "+booked+ " [Available] "+available);
		
		query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
				" WHERE SANPHAM_FK = "+sanPhamCu.getPk_seq()+" AND KHO_FK = "+this.PK_SEQ;
		
		if (this.checkRangBuocSoLuongTonTrongKho(booked, available, sanPhamCu)){
			try {
				if(!utilityKho.getDbutils().update(query)){
					utilityKho.getDbutils().getConnection().rollback();
					utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
					return false;
				}

				System.out.println(sanPhamCu.getPk_seq()+" Ghi nhận [Booked] "+booked+ " [Available] "+available+ 
													" soLuongChuyenMoi "+soLuongChuyenCu);
			} catch (SQLException e) {
				utilityKho.getDbutils().update("rollback");
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham) {
		System.out.println("===CẬP NHẬT KHO CHUYỂN===");
		int booked = 0, available = 0;
		int soLuongChuyenMoi = elementSanPham.getSoLuong();
		booked = this.getSoLuongBooked(elementSanPham) + soLuongChuyenMoi;
		available = this.getSoLuongAvailable(elementSanPham) - soLuongChuyenMoi;
		System.out.println(elementSanPham.getPk_seq()+" [Booked] "+booked+ " [Available] "+available);
		
		String query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
				" WHERE SANPHAM_FK = "+elementSanPham.getPk_seq()+" AND KHO_FK = "+this.getPK_SEQ();
		
		if (this.checkRangBuocSoLuongTonTrongKho(booked, available, elementSanPham)){
			try {
				if(!utilityKho.getDbutils().update(query)){
					utilityKho.getDbutils().getConnection().rollback();
					utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
					return false;
				}

				System.out.println(elementSanPham.getPk_seq()+" Ghi nhận [Booked] "+booked+ " [Available] "+available+ 
													" soLuongChuyenMoi "+soLuongChuyenMoi);
				return true;
			} catch (SQLException e) {
				utilityKho.getDbutils().update("rollback");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham, int booked, int available) {
		System.out.println("===CẬP NHẬT KHO CHUYỂN===");
		int soLuongChuyenMoi = elementSanPham.getSoLuong();
		System.out.println(elementSanPham.getPk_seq()+" [Booked] "+booked+ " [Available] "+available);
		
		String query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
				" WHERE SANPHAM_FK = "+elementSanPham.getPk_seq()+" AND KHO_FK = "+this.getPK_SEQ();
		
		if (this.checkRangBuocSoLuongTonTrongKho(booked, available, elementSanPham)){
			try {
				if(!utilityKho.getDbutils().update(query)){
					utilityKho.getDbutils().getConnection().rollback();
					utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
					return false;
				}

				System.out.println(elementSanPham.getPk_seq()+" Ghi nhận [Booked] "+booked+ " [Available] "+available+ 
													" soLuongChuyenMoi "+soLuongChuyenMoi);
				return true;
			} catch (SQLException e) {
				utilityKho.getDbutils().update("rollback");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean updateKHO_SANPHAM(ISanPham_Vy elementSanPham, int booked, int available, int soLuongTon) {
		System.out.println("===CẬP NHẬT KHO CHUYỂN===");
		int soLuongChuyenMoi = elementSanPham.getSoLuong();
		System.out.println(elementSanPham.getPk_seq()+" [Booked] "+booked+ " [Available] "+available);
		booked = booked == -1 ? this.getSoLuongBooked(elementSanPham) : booked;
		available = available == -1 ? this.getSoLuongAvailable(elementSanPham) : available;
		soLuongTon = soLuongTon == -1 ? this.getSoLuongTon(elementSanPham) : soLuongTon;
		String query = "UPDATE KHO_SANPHAM SET SOLUONG = " +soLuongTon+", BOOKED = " +booked+", AVAILABLE = "+available+
						" WHERE SANPHAM_FK = "+elementSanPham.getPk_seq()+" AND KHO_FK = "+this.getPK_SEQ();
		
		if (this.checkRangBuocSoLuongTonTrongKho(booked, available, elementSanPham)){
			try {
				if(!utilityKho.getDbutils().update(query)){
					utilityKho.getDbutils().getConnection().rollback();
					utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
					return false;
				}

				System.out.println(elementSanPham.getPk_seq()+" Ghi nhận [Booked] "+booked+ " [Available] "+available+ 
													" soLuongChuyenMoi "+soLuongChuyenMoi);
				return true;
			} catch (SQLException e) {
				utilityKho.getDbutils().update("rollback");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/*@Override
	public boolean thaoTacPhieuChuyenKho (IPhieuChuyenKho_Vy phieuChuyenKhoMoi, String command, IPhieuChuyenKho_Vy phieuChuyenKhoCu) {
		
		String query = "";
		String maKhoChuyenTruocDo="";
		if (phieuChuyenKhoCu != null) {
			phieuChuyenKhoCu.init();
			maKhoChuyenTruocDo = phieuChuyenKhoCu.getKhoChuyen().getPK_SEQ();
		}
		String maKhoChuyenSau = "";
		if (phieuChuyenKhoMoi != null) {
			maKhoChuyenSau = phieuChuyenKhoMoi.getKhoChuyen().getPK_SEQ();
		}
		System.out.println("maKhoChuyenTruocDo "+maKhoChuyenTruocDo+" maKhoChuyenSau "+maKhoChuyenSau);
		
		int booked = 0, available = 0;
		if (command.equals("RESTORE")) {
			
			if ( maKhoChuyenTruocDo.equals(maKhoChuyenSau) ) {
				this.setPK_SEQ(maKhoChuyenSau);
			} else {
				this.setPK_SEQ(maKhoChuyenTruocDo);
				
			}
			this.loadThongTinKHO_SANPHAM();	
			Iterator<ISanPham_Vy> iteratorSanPham = phieuChuyenKhoCu.getDanhSachSanPham().iterator();
			while (iteratorSanPham.hasNext()) {
				ISanPham_Vy elementSanPham = iteratorSanPham.next();
				String PK_SEQSanPham = elementSanPham.getPk_seq();
				int soLuongChuyenCu = elementSanPham.getSoLuong();
				booked = this.getSoLuongBooked(elementSanPham) - soLuongChuyenCu;
				available = this.getSoLuongAvailable(elementSanPham) + soLuongChuyenCu;
				query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
				" WHERE SANPHAM_FK = "+PK_SEQSanPham+" AND KHO_FK = "+this.PK_SEQ;
				
				if ( this.checkRangBuocSoLuongTonTrongKho(booked, available, elementSanPham) ){
					System.out.println(elementSanPham.getPk_seq()+"Sau khi khoi phuc Booked "+booked+ " Available "+available+ 
																	" soLuongChuyenCu "+soLuongChuyenCu);
					
					try {
						utilityKho.getDbutils().getConnection().setAutoCommit(false);
						if(!utilityKho.getDbutils().update(query)){
							utilityKho.getDbutils().getConnection().rollback();
							utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
							return false;
						}
						utilityKho.getDbutils().getConnection().commit();
						utilityKho.getDbutils().getConnection().setAutoCommit(true);
						
						//this.setMessage("Cap nhat trang thai chot thanh cong");
					} catch (SQLException e) {
						utilityKho.getDbutils().update("rollback");
						e.printStackTrace();
						return false;
					}
				} else {
					System.out.println("Tinh toan sai lam ! : "+ " Booked "+booked+ " Available "+available);
					elementSanPham.init();
					this.message += elementSanPham.getTenSanPham() + " chưa sẵn sàng\n";
					return false;
				}
			}
		} else if (command.equals("INSERT")) {
			System.out.println("GHI NHẬN PHIẾU CHUYỂN KHO VÀO KHO CHUYỂN");
			List<ISanPham_Vy> dsNotAvailable = phieuChuyenKhoMoi.checkNotAvailable();
			if (dsNotAvailable.size() == 0) {
				this.setPK_SEQ(maKhoChuyenSau);
				this.loadThongTinKHO_SANPHAM();
				Iterator<ISanPham_Vy> iteratorSanPham = phieuChuyenKhoMoi.getDanhSachSanPham().iterator();
				System.out.println("Dzo ham insert");
				while (iteratorSanPham.hasNext()) {
					ISanPham_Vy elementSanPham = iteratorSanPham.next();
					String PK_SEQSanPham = elementSanPham.getPk_seq();
					int soLuongChuyenMoi = elementSanPham.getSoLuong();
					booked = this.getSoLuongBooked(elementSanPham) + soLuongChuyenMoi;
					available = this.getSoLuongAvailable(elementSanPham) - soLuongChuyenMoi;
					System.out.println(elementSanPham.getPk_seq()+" Insert Booked "+booked+ " Available "+available);
					
					query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", AVAILABLE = "+available+
					" WHERE SANPHAM_FK = "+PK_SEQSanPham+" AND KHO_FK = "+this.PK_SEQ;
					
					if (checkRangBuocSoLuongTonTrongKho(booked, available, elementSanPham)){
						System.out.println(elementSanPham.getPk_seq()+" Booked "+booked+ " Available "+available+ 
								" soLuongChuyenMoi "+soLuongChuyenMoi);
						System.out.println("maKhoChuyenTruocDo "+maKhoChuyenTruocDo+" maKhoChuyenSau "+maKhoChuyenSau);
						try {
							utilityKho.getDbutils().getConnection().setAutoCommit(false);
							if(!utilityKho.getDbutils().update(query)){
								utilityKho.getDbutils().getConnection().rollback();
								utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
								return false;
							}
							utilityKho.getDbutils().getConnection().commit();
							utilityKho.getDbutils().getConnection().setAutoCommit(true);
							
							//this.setMessage("Cap nhat trang thai chot thanh cong");
						} catch (SQLException e) {
							utilityKho.getDbutils().update("rollback");
							e.printStackTrace();
							return false;
						}
					} else {
						return false;
					}
				} end while
			} end if dsNotAvailable		
		} else if (command.equals("TRANSFER")) {
			phieuChuyenKhoMoi.init();
			maKhoChuyenSau = phieuChuyenKhoMoi.getKhoChuyen().getPK_SEQ();
			IKho_Vy khoNhan = new Kho_Vy(phieuChuyenKhoMoi.getKhoNhan().getPK_SEQ());
			khoNhan.loadThongTinKHO_SANPHAM();
			System.out.println("khoNhan.getPK_SEQ() "+khoNhan.getDanhSachSanPham().get(0).getPk_seq());
			int soLuongTonKhoChuyen = 0, soLuongTonKhoNhan = 0, availableKhoNhan = 0;
			String queryKhoNhan = "";
			List<ISanPham_Vy> dsNotAvailable = phieuChuyenKhoMoi.checkNotAvailable();
			if (dsNotAvailable.size() == 0) {
				this.setPK_SEQ(maKhoChuyenSau);
				System.out.println(maKhoChuyenSau+" TRANSFER TO "+khoNhan.getPK_SEQ());
				this.loadThongTinKHO_SANPHAM();
				Iterator<ISanPham_Vy> iteratorSanPham = phieuChuyenKhoMoi.getDanhSachSanPham().iterator();
				while (iteratorSanPham.hasNext()) {
					ISanPham_Vy elementSanPham = iteratorSanPham.next();
					String PK_SEQSanPham = elementSanPham.getPk_seq();
					int soLuongChuyen = elementSanPham.getSoLuong();
					System.out.println(elementSanPham.getPk_seq()+" BANDAU Booked "+booked+ " Available "+available+" soLuongTon "+soLuongTonKhoChuyen);
					booked = this.getSoLuongBooked(elementSanPham) - soLuongChuyen;
					soLuongTonKhoChuyen = this.getSoLuongTonTrongKhoChuyen(elementSanPham) - soLuongChuyen;
					System.out.println(elementSanPham.getPk_seq()+" TRANSFER Booked "+booked+ " Available "+available+" soLuongTon "+soLuongTonKhoChuyen);
					
					query = "UPDATE KHO_SANPHAM SET BOOKED = " +booked+", SOLUONG = "+soLuongTonKhoChuyen+
					" WHERE SANPHAM_FK = "+PK_SEQSanPham+" AND KHO_FK = "+this.PK_SEQ;
					
					soLuongTonKhoNhan = khoNhan.getSoLuongTonTrongKhoChuyen(elementSanPham) + soLuongChuyen;
					availableKhoNhan = khoNhan.getSoLuongAvailable(elementSanPham) + soLuongChuyen;
					
					queryKhoNhan = "UPDATE KHO_SANPHAM SET SOLUONG = " +soLuongTonKhoNhan+", AVAILABLE = "+availableKhoNhan+
									" WHERE SANPHAM_FK = "+PK_SEQSanPham+" AND KHO_FK = "+khoNhan.getPK_SEQ();
					
			
						System.out.println("TRANSFER"+elementSanPham.getPk_seq()+" Booked "+booked+ " Available "+available+ 
								" soLuongTon "+soLuongTonKhoChuyen+" khoChuyen "+this.PK_SEQ);
						System.out.println("TRANSFER"+elementSanPham.getPk_seq()+ " availableKhoNhan "+availableKhoNhan+ 
								" soLuongTonKhoNhan "+soLuongTonKhoNhan+" khoNhan "+khoNhan.getPK_SEQ());
						try {
							utilityKho.getDbutils().getConnection().setAutoCommit(false);
							if(!utilityKho.getDbutils().update(query)){
								utilityKho.getDbutils().getConnection().rollback();
								utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
								return false;
							}
							
							if(!utilityKho.getDbutils().update(queryKhoNhan)){
								utilityKho.getDbutils().getConnection().rollback();
								utilityKho.setMessage("Không thể thực hiện dòng lệnh ; "+query + "\n");
								return false;
							}
							
							utilityKho.getDbutils().getConnection().commit();
							utilityKho.getDbutils().getConnection().setAutoCommit(true);
							
							//this.setMessage("Cap nhat trang thai chot thanh cong");
						} catch (SQLException e) {
							utilityKho.getDbutils().update("rollback");
							e.printStackTrace();
							return false;
						}
					
				} end while
			} end if dsNotAvailable		
		}	end if command
		return true;
		
	} end method*/
	

	/*public static void main(String[] args) {
		IPhieuChuyenKho_Vy phieuChuyenKho = new PhieuChuyenKho_Vy("120050");
		phieuChuyenKho.init();

		IKho_Vy kho = new Kho_Vy(phieuChuyenKho.getKhoChuyen().getPK_SEQ());
		System.out.println(phieuChuyenKho.getKhoChuyen().getPK_SEQ());
		//phieuChuyenKho.getKhoChuyen().setPK_SEQ("100002");
		kho.thaoTacPhieuChuyenKho(phieuChuyenKho, "TRANSFER", null);
		//phieuChuyenKho.chotPhieu();
		//System.out.println(phieuChuyenKho.getDanhSachSanPham().get(0).getSoLuong());
	}*/


	
}
