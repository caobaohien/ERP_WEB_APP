package geso.erp.beans.chuyenkho_kiet02.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoList;
import geso.erp.db.sql.dbutils;

public class ChuyenKhoList extends Phan_Trang implements IChuyenKhoList {

	String Ma, NgayChuyenKho, KhoChuyen, KhoNhan, NguoiTao, NguoiSua,
			TrangThai, NgayTao, NgaySua, LyDo;
	String UserId, Id, Msg;
	ResultSet rsList, rsKhoChuyen, rsKhoNhan;
	dbutils db = new dbutils();
	
	public ChuyenKhoList() {
		Ma = "";
		NgayChuyenKho = "";
		KhoChuyen = "";
		KhoNhan = "";
		NguoiTao = "";
		NguoiSua = "";
		TrangThai = "";
		NgayTao = "";
		NgaySua = "";
		LyDo = "";
		UserId = "";
		Id = "";
		Msg = "";
	}
	
	public ChuyenKhoList(String ma) {
		Ma = ma;
		NgayChuyenKho = "";
		KhoChuyen = "";
		KhoNhan = "";
		NguoiTao = "";
		NguoiSua = "";
		TrangThai = "";
		NgayTao = "";
		NgaySua = "";
		LyDo = "";
		UserId = "";
		Id = "";
		Msg = "";
	}
	
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.Ma = ma;
	}

	@Override
	public String getNgayChuyenKho() {
		// TODO Auto-generated method stub
		return this.NgayChuyenKho;
	}

	@Override
	public void setNgayChuyenKho(String ngay) {
		// TODO Auto-generated method stub
		this.NgayChuyenKho = ngay;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.KhoChuyen;
	}

	@Override
	public void setKhoChuyen(String khoChuyen) {
		// TODO Auto-generated method stub
		this.KhoChuyen = khoChuyen;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.KhoNhan;
	}

	@Override
	public void setKhoNhan(String khoNhan) {
		// TODO Auto-generated method stub
		this.KhoNhan = khoNhan;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.NgayTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.NguoiTao = nguoiTao;
	}

	@Override
	public String getNguoiSua() {
		// TODO Auto-generated method stub
		return this.NguoiSua;
	}

	@Override
	public void setNguoiSua(String nguoiSua) {
		// TODO Auto-generated method stub
		this.NguoiSua = nguoiSua;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.TrangThai;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.TrangThai = trangThai;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.NgayTao;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.NgayTao = ngayTao;
	}

	@Override
	public String getNgaySua() {
		// TODO Auto-generated method stub
		return this.NgaySua;
	}

	@Override
	public void setNgaySua(String ngaySua) {
		// TODO Auto-generated method stub
		this.NgaySua = ngaySua;
	}

	@Override
	public String getLyDo() {
		// TODO Auto-generated method stub
		return this.LyDo;
	}

	@Override
	public void setLyDo(String lyDo) {
		// TODO Auto-generated method stub
		this.LyDo = lyDo;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId = userId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.Id = id;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg = msg;
	}

	@Override
	public ResultSet getRsList() {
		// TODO Auto-generated method stub
		return this.rsList;
	}

	@Override
	public void setRsList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsList = rs;
	}

	@Override
	public ResultSet getRsKhoChuyen() {
		// TODO Auto-generated method stub
		return this.rsKhoChuyen;
	}

	@Override
	public void setRsKhoChuyen(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsKhoChuyen = rs;
	}

	@Override
	public ResultSet getRsKhoNhan() {
		// TODO Auto-generated method stub
		return this.rsKhoNhan;
	}

	@Override
	public void setRsKhoNhan(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsKhoNhan = rs;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query = "";
		if (search.length() > 0) {
			query = search;
		} else {
			// TODO Auto-generated method stub
			query =" SELECT ISNULL(ck.TRANGTHAI,'') AS trangthai,ck.PK_SEQ as ma ,ck.NGAYCHUYENKHO as ngaychuyenkho, "+
			" k.tenkho as khonhan,k1.tenkho as khochuyen,ck.NGAYTAO as ngaytao ,ck.NGAYSUA as ngaysua, "+
			" NV.TEN as nguoitao,NV1.TEN as nguoisua,ck.lydo as lydo "+
			" FROM chuyenkho ck LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ" +
			" LEFT JOIN NHANVIEN NV ON CK.NGUOITAO=NV.PK_SEQ LEFT JOIN NHANVIEN NV1 ON CK.NGUOISUA=NV1.PK_SEQ ";
		
		}
		this.rsList = db.get(query);
		createRs();
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		String query = "select PK_SEQ, TENKHO from KHO";
		this.rsKhoChuyen = db.get(query);
		this.rsKhoNhan = db.get(query);
	}

	@Override
	public boolean deleteCK() {
		try {
			if (this.Id.trim().length() > 0) {
				String query = " select cksp.SANPHAM_FK as sanpham,cksp.SOLUONG as soluong,ck.KHOCHUYEN as khochuyen"+
							   " from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "+
							   " where ck.PK_SEQ="+this.Id;
				ResultSet rs = db.get(query);
				while (rs.next()) {
					this.KhoChuyen = rs.getString("KHOCHUYEN");
					double Soluongchuyen = rs.getDouble("SOLUONG");
					String masp = rs.getString("SANPHAM");
					query = "UPDATE KHO_SANPHAM SET BOOKED=" + '0'
							+ ",AVAILABLE= AVAILABLE+" + Soluongchuyen
							+ " WHERE KHO_FK=" + this.KhoChuyen
							+ "and SANPHAM_FK= " + masp;
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}
				query = "UPDATE CHUYENKHO SET TRANGTHAI='2' WHERE PK_SEQ="
						+ this.Id;
				if (db.updateReturnInt(query) != 1) {
					db.getConnection().rollback();
					this.Msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			return true;
		} catch (Exception e) {
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean finishCK() {

		try {
			if (this.Id.trim().length() > 0) {
				String query = " select cksp.SANPHAM_FK as sanpham,cksp.SOLUONG as soluong,ck.KHOCHUYEN as khochuyen,CK.KHONHAN AS KHONHAN"
						+ " from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "
						+ " where ck.PK_SEQ=" + this.getId();
				ResultSet rs = db.get(query);
				while (rs.next()) {
					this.KhoNhan = rs.getString("khonhan");
					this.KhoChuyen = rs.getString("KHOCHUYEN");
					double Soluongchuyen = rs.getDouble("SOLUONG");
					String masp = rs.getString("SANPHAM");
					query = "UPDATE KHO_SANPHAM SET BOOKED=" + '0'
							+ ", SOLUONG= SOLUONG-" + Soluongchuyen
							+ " WHERE KHO_FK=" + this.KhoChuyen
							+ " and SANPHAM_FK= " + masp;

					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}

					String k = "select count(*) from KHO_SANPHAM where KHO_FK= "
							+ this.KhoNhan + " and SANPHAM_FK=" + masp;
					ResultSet rsj = this.db.get(k);

					if (rsj != null) {

						query = "UPDATE KHO_SANPHAM SET AVAILABLE =AVAILABLE+ "
								+ Soluongchuyen + ",SOLUONG = SOLUONG+"
								+ Soluongchuyen + " WHERE KHO_FK = "
								+ this.KhoNhan + " and SANPHAM_FK = " + masp;
						if (db.updateReturnInt(query) == -1) {
							db.getConnection().rollback();
							this.Msg = "Không thể cập nhật dòng lệnh: " + query;
							return false;
						}
					} else {
						query = "INSERT INTO(KHO_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE)"
								+ "VALUES('"
								+ this.KhoNhan
								+ "',"
								+ masp
								+ ","
								+ Soluongchuyen + ",0," + Soluongchuyen + ")";
						if (db.updateReturnInt(query) != 1) {
							db.getConnection().rollback();
							this.Msg = "Không thể cập nhật dòng lệnh: " + query;
							return false;
						}
					}
				}
			
				query = "UPDATE CHUYENKHO SET TRANGTHAI='1' WHERE PK_SEQ="
						+ this.getId();
				if (db.updateReturnInt(query) != 1) {
					db.getConnection().rollback();
					this.Msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			return true;
		} catch (Exception e) {
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		db.shutDown();
	}

}
