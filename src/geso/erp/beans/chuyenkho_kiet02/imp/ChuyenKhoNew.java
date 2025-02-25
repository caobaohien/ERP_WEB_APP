package geso.erp.beans.chuyenkho_kiet02.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.chuyenkho_hieu.ISanpham;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoNew;
import geso.erp.beans.chuyenkho_kiet02.ISanPham;
import geso.erp.db.sql.dbutils;

public class ChuyenKhoNew implements IChuyenKhoNew {
	
	String Ma, NgayChuyenKho, KhoChuyen, KhoNhan, TrangThai, LyDo, Dvt;
	String UserId, Id, Msg, IsUpdate;
	double Booked, Available;
	ResultSet rsDataList, rsKhoChuyen, rsKhoNhan, rsDvt;
	dbutils db = new dbutils();
	List<ISanPham> spList;
	
	public ChuyenKhoNew() {
		Ma = "";
		NgayChuyenKho = "";
		KhoChuyen = "";
		KhoNhan = "";
		TrangThai = "";
		LyDo = "";
		Dvt = "";
		spList = new ArrayList<ISanPham>();
		// -----------
		UserId = "";
		Id = "";
		Msg = "";
	}
	
	public ChuyenKhoNew(String id) {
		Ma = "";
		NgayChuyenKho = "";
		KhoChuyen = "";
		KhoNhan = "";
		TrangThai = "";
		LyDo = "";
		Dvt = "";
		spList = new ArrayList<ISanPham>();
		// -----------
		UserId = "";
		Id = id;
		Msg = "";
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
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg = msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rsDataList;
	}

	@Override
	public void setDataList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsDataList = rs;
	}

	@Override
	public ResultSet getRsDvt() {
		// TODO Auto-generated method stub
		return this.rsDvt;
	}

	@Override
	public void setRsDvt(ResultSet dvt) {
		// TODO Auto-generated method stub
		this.rsDvt = dvt;
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
	public void setNgayChuyenKho(String ngaychuyenkho) {
		// TODO Auto-generated method stub
		this.NgayChuyenKho = ngaychuyenkho;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.KhoChuyen;
	}

	@Override
	public void setKhoChuyen(String khochuyen) {
		// TODO Auto-generated method stub
		this.KhoChuyen = khochuyen;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.KhoNhan;
	}

	@Override
	public void setKhoNhan(String khonhan) {
		// TODO Auto-generated method stub
		this.KhoNhan = khonhan;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.TrangThai;
	}

	@Override
	public void setTrangThai(String trangthai) {
		// TODO Auto-generated method stub
		this.TrangThai = trangthai;
	}

	@Override
	public String getLyDo() {
		// TODO Auto-generated method stub
		return this.LyDo;
	}

	@Override
	public void setLyDo(String lydo) {
		// TODO Auto-generated method stub
		this.LyDo = lydo;
	}

	@Override
	public double getBooked() {
		// TODO Auto-generated method stub
		return this.Booked;
	}

	@Override
	public void setBooked(double booked) {
		// TODO Auto-generated method stub
		this.Booked = booked;
	}

	@Override
	public double getAvailabel() {
		// TODO Auto-generated method stub
		return this.Available;
	}

	@Override
	public void setAvailabe(double avai) {
		// TODO Auto-generated method stub
		this.Available = avai;
	}

	@Override
	public List<ISanPham> getListSp() {
		// TODO Auto-generated method stub
		return this.spList;
	}

	@Override
	public void setListSp(List<ISanPham> list) {
		// TODO Auto-generated method stub
		this.spList = list;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			if (this.Id.trim().length() > 0) {
				String query = " SELECT NGAYCHUYENKHO,LYDO,KHONHAN,KHOCHUYEN"
						+ " FROM CHUYENKHO " + " where PK_SEQ=" 
						+ this.Id;

				ResultSet rs = db.get(query);

				if (rs.next()) {
					this.NgayChuyenKho = rs.getString("ngaychuyenkho");
					this.KhoChuyen = rs.getString("KHOCHUYEN");
					this.KhoNhan = rs.getString("KHONHAN");
					this.LyDo = rs.getString("LYDO");
				}
				
				query=	"select sp.PK_SEQ,cksp.SOLUONG as soluongchuyen,sp.MA,sp.TEN as tensp,dv.TEN as tendv, "+
						" khosp.AVAILABLE as soluongton from CHUYENKHO_SANPHAM cksp "+
						" left join ERP_SANPHAM sp on sp.PK_SEQ=cksp.SANPHAM_FK "+
						" left join ERP_DONVI dv on dv.PK_SEQ=sp.DONVI_FK "+
						" left join KHO_SANPHAM khosp on khosp.SANPHAM_FK=cksp.SANPHAM_FK "+
						" where CHUYENKHO_FK="+this.Id +" and khosp.KHO_FK="+this.KhoChuyen;
				spList.clear();

				ResultSet rssp = db.get(query);
				while (rssp.next()) {
					ISanPham sp = new SanPham();
					sp.setId(rssp.getString("pk_Seq"));
					sp.setMaSanPham(rssp.getString("ma"));
					sp.setTenSanPham(rssp.getString("tensp"));
					sp.setDonViTinh(rssp.getString("tendv"));
					sp.setSoLuongTon(rssp.getDouble("soluongton"));
					sp.setSoLuongChuyen(rssp.getDouble("soluongchuyen"));
					spList.add(sp);
				}
				rssp.close();
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void createRs() {
		// TODO Auto-generated method stub
		String query = "select PK_SEQ, TENKHO from KHO";
		this.rsKhoChuyen = db.get(query);
		this.rsKhoNhan = db.get(query);
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "";
			if ((this.getNgayChuyenKho() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn ngày chuyển.";
				return false;
			}
			if ((this.getLyDo() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng điền lý do chuyển.";
				return false;
			}
			if ((this.getKhoChuyen() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn kho chuyển.";
				return false;
			}
			if ((this.getKhoNhan() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn kho nhận.";
				return false;
			}
			if (this.getKhoChuyen().equals(this.getKhoNhan())) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn kho nhận khác kho chuyển.";
				return false;
			}
			query = "insert into CHUYENKHO(NGAYCHUYENKHO, KHONHAN, KHOCHUYEN, "
				+ " NGUOITAO, NGUOISUA, TRANGTHAI, NGAYTAO, NGAYSUA, LYDO) values ('"
				+ this.getNgayChuyenKho()+ "', "
				+ this.getKhoNhan()+ ", "
				+ this.getKhoChuyen()+ ", "
				+ this.getUserId()+ ", "
				+ this.getUserId()+ ", "
				+ " '0', '"						
				+ this.getDateTime()+ "', '"
				+ this.getDateTime()+ "', N'"
				+ this.getLyDo()+ "')";
			
			if (!db.update(query)) {
				this.Msg = "Không thực hiện được câu lệnh: " + query;
				db.getConnection().rollback();
				return false;
			}
			String idcr = "";
			//lay id vua them vao bang
			query = "select SCOPE_IDENTITY() as dhid";
			ResultSet rsDck = db.get(query);
			if (rsDck.next()) {
				idcr = rsDck.getString("dhid");
			}
			System.out.println("Ma can lay :" + idcr);
			rsDck.close();
			
			System.out.println("this.spList.size():" + this.spList.size());
			if (spList.size() == 0)
			{
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn sản phẩm mới.";
				return false;
			} else
			for (int i = 0; i < this.spList.size(); i++) {
				ISanPham sp = spList.get(i);
				//----------------------------------------------------
				System.out.println("SLC: "+ sp.getSoLuongChuyen());
				System.out.println("SANPHAM_FK: "+ sp.getId());
				query = "select AVAILABLE from KHO_SANPHAM where KHO_FK= "+ this.getKhoChuyen()+ " and SANPHAM_FK= "+ sp.getId();
				System.out.println("Query available: "+ query);
				ResultSet rsTestData = db.get(query);
				float temp = 0;
				while (rsTestData.next()){
					temp = rsTestData.getFloat("AVAILABLE");
				}
				System.out.println("Temp: "+ temp);
				if (sp.getSoLuongChuyen() <= 0.0 || sp.getSoLuongChuyen() > temp){
					db.getConnection().rollback();
					this.Msg = "Vui lòng nhập lại Sl chuyển.";
					return false;
				}
				//----------------------------------------------------
				else {
				query = "INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values "
						+ "("
						+ idcr
						+ ","
						+ sp.getId()
						+ ","
						+ sp.getSoLuongChuyen() + ")";
				System.out.println("Insert into CHUYENKHO_SP: "+ query);
				if (!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
				
				query = "update KHO_SANPHAM set AVAILABLE = AVAILABLE - "
					+ sp.getSoLuongChuyen() + ", SOLUONG = AVAILABLE + BOOKED, "
					+ " BOOKED = BOOKED +" + sp.getSoLuongChuyen()
					+ " where KHO_FK = "+ this.KhoChuyen
					+ " and SANPHAM_FK = "+ sp.getId();
				System.out.println("Insert into KHO_SP: "+ query);
				if (!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
				}
				//------------------- update booked ----------------------
			}
			System.out.println("Here");
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			this.Msg = "Lỗi :" + e.getMessage();
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update() {
		
		try {
			db.getConnection().setAutoCommit(false);
			System.out.println("id co hay khong " + Id.length());
			String query = "";
			if ((this.getNgayChuyenKho() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn ngày chuyển.";
				return false;
			}
			if ((this.getLyDo() == "")) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng điền lý do chuyển.";
				return false;
			}
			if (this.getKhoChuyen().equals(this.getKhoNhan())) {
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn kho nhận khác kho chuyển.";
				return false;
			}
			if (spList.size() == 0)
			{
				db.getConnection().rollback();
				this.Msg = "Vui lòng chọn sản phẩm mới.";
				return false;
			}
			//--------------------------------------------
			if (this.Id.length() > 0) {
				query = "delete CHUYENKHO_SANPHAM  WHERE CHUYENKHO_FK ="
						+ this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				query = "UPDATE CHUYENKHO set NGAYCHUYENKHO = "
						+ this.getNgayChuyenKho() + ",KHONHAN = "
						+ this.getKhoNhan() + ",KHOCHUYEN="
						+ this.getKhoChuyen() + ",LYDO = N'" + this.getLyDo()
						+ "'" + " WHERE PK_SEQ= " + this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				for (int i = 0; i < this.spList.size(); i++) {
					ISanPham sp = spList.get(i);
					//----------------------------------------------------
					System.out.println("SLC: "+ sp.getSoLuongChuyen());
					System.out.println("SANPHAM_FK: "+ sp.getId());
					query = "select AVAILABLE from KHO_SANPHAM where KHO_FK= "+ this.getKhoChuyen()+ " and SANPHAM_FK= "+ sp.getId();
					System.out.println("Query available: "+ query);
					ResultSet rsTestData = db.get(query);
					float temp = 0;
					while (rsTestData.next()){
						temp = rsTestData.getFloat("AVAILABLE");
					}
					System.out.println("Temp: "+ temp);
					if (sp.getSoLuongChuyen() <= 0.0 || sp.getSoLuongChuyen() > temp){
						db.getConnection().rollback();
						this.Msg = "Vui lòng nhập lại Sl chuyển.";
						return false;
					}
					//----------------------------------------------------
					query = "INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values "
							+ "('"
							+ this.Id
							+ "','"
							+ sp.getId()
							+ "',"
							+ sp.getSoLuongChuyen() + ")";
					if (!db.update(query)) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
					query = "UPDATE KHO_SANPHAM SET BOOKED=" + '0'
							+ " WHERE KHO_FK=" + this.KhoChuyen
							+ " and SANPHAM_FK= " + sp.getId();
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
					query = "UPDATE KHO_SANPHAM SET BOOKED = "
							+ sp.getSoLuongChuyen() + ",AVAILABLE=SOLUONG- "
							+ sp.getSoLuongChuyen() + " WHERE KHO_FK = "
							+ this.KhoChuyen + " and SANPHAM_FK= " + sp.getId();
					if (!db.update(query)) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}

			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			this.Msg = "Lỗi :" + e.getMessage();
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		db.shutDown();
	}

	@Override
	public String getIsUpdate() {
		// TODO Auto-generated method stub
		return this.IsUpdate;
	}
	

	@Override
	public void setIsUpdate(String isUpdate) {
		// TODO Auto-generated method stub
		this.IsUpdate = isUpdate;
	}
	
	
}
