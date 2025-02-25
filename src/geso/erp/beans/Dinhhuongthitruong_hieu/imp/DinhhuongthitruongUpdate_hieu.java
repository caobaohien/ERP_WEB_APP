package geso.erp.beans.Dinhhuongthitruong_hieu.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuong_hoatdong;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.IChuyenkhoUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.ISanpham;
import geso.erp.beans.chuyenkho_hieu.imp.Sanpham;
import geso.erp.beans.donbanhang_hieu.Isanpham;
import geso.erp.db.sql.dbutils;

public class DinhhuongthitruongUpdate_hieu implements IDinhhuongthitruongUpdate_hieu {
	String Id,Trangthai,  Tinhtrangthitruong,
			UserId, Msg;
	int Thang, Nam,Nhandinhthoigianthang,Nhandinhthoigianquy;
	ResultSet rs;
	ResultSet rshd;
	dbutils db = new dbutils();
	List<IDinhhuong_hoatdong> list;

	public DinhhuongthitruongUpdate_hieu() {
		Id = "";
		Trangthai = "";
		Thang = 0;
		Nam = 0;
		Tinhtrangthitruong = "";
		Nhandinhthoigianthang = 0;
		Nhandinhthoigianquy = 0;
		this.list = new ArrayList<IDinhhuong_hoatdong>();
		this.UserId = "";
		this.Msg = "";
	}

	public DinhhuongthitruongUpdate_hieu(String id) {
		Id = id;
		Trangthai = "";
		Thang = 0;
		Nam = 0;
		Tinhtrangthitruong = "";
		Nhandinhthoigianthang = 0;
		Nhandinhthoigianquy = 0;
		this.list = new ArrayList<IDinhhuong_hoatdong>();
		this.UserId = "";
		this.Msg = "";
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.UserId = _userId;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai = _trangthai;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}

	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.Id = _id;
	}

	


	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.Msg = _msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rs;
	}

	@Override
	public void setDataList(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rs = _dmhlist;
	}

	
	

	

	@Override
	public int getThang() {
		// TODO Auto-generated method stub
		return this.Thang;
	}

	@Override
	public void setThang(int _thang) {
		// TODO Auto-generated method stub
		this.Thang=_thang;
	}

	@Override
	public int getNam() {
		// TODO Auto-generated method stub
		return this.Nam;
	}

	@Override
	public void setNam(int _nam) {
		// TODO Auto-generated method stub
		this.Nam=_nam;
	}

	@Override
	public String getTinhtrangthitruong() {
		// TODO Auto-generated method stub
		return this.Tinhtrangthitruong;
	}

	@Override
	public void setTinhtrangthitruong(String _tinhtrangthitruong) {
		// TODO Auto-generated method stub
		this.Tinhtrangthitruong=_tinhtrangthitruong;
	}

	@Override
	public int getNhandinhthoigiantheothang() {
		// TODO Auto-generated method stub
		return this.Nhandinhthoigianthang;
	}

	@Override
	public void setNhandinhthoigiantheothang(int _thoigianthang) {
		// TODO Auto-generated method stub
		this.Nhandinhthoigianthang=_thoigianthang;
	}

	@Override
	public int getNhandinhthoigiantheoquy() {
		// TODO Auto-generated method stub
		return this.Nhandinhthoigianquy;
	}

	@Override
	public void setNhandinhthoigiantheoquy(int _thoigianquy) {
		// TODO Auto-generated method stub
		this.Nhandinhthoigianquy=_thoigianquy;
	}

	

	@Override
	public List<IDinhhuong_hoatdong> getListkhhd() {
		// TODO Auto-generated method stub
		return this.list;
	}

	@Override
	public void setListkhhd(List<IDinhhuong_hoatdong> _list) {
		// TODO Auto-generated method stub
		this.list=_list;
	}

	@Override
	public void init() {
		try {
			if (this.Id.trim().length() > 0) {
				String query = " select thang,nam,tinhtrangthitruong,thang_nhandinh,quy_nhandinh "+
							   " from kehoachdinhhuong " + " where PK_SEQ=" 
						+ this.Id;

				ResultSet rs = db.get(query);

				if (rs.next()) {
					this.Thang = rs.getInt("thang");
					this.Nam = rs.getInt("nam");
					this.Tinhtrangthitruong = rs.getString("tinhtrangthitruong");
					this.Nhandinhthoigianthang = rs.getInt("thang_nhandinh");
					this.Nhandinhthoigianquy = rs.getInt("quy_nhandinh");

				}
				query=	" select pk_seq as ma,mota,tenhoatdong,kinhphi,tungay,denngay,soluong_kh,soluong_hd "+
						" from KEHOACHDINHHUONG_HOATDONG "+
						" where kehoachdinhhuong_fk="+this.Id ;
				list.clear();

				ResultSet rshd = db.get(query);
				while (rshd.next()) {
					IDinhhuong_hoatdong hd = new Dinhhuonghoatdong();
					hd.setMa(rshd.getString("ma"));
					hd.setTenhoatdong(rshd.getString("tenhoatdong"));
					hd.setMota(rshd.getString("mota"));
					hd.setKinhphi(rshd.getFloat("kinhphi"));
					hd.setTungay(rshd.getString("tungay"));
					hd.setDenngay(rshd.getString("denngay"));
					hd.setSoluongkh(rshd.getInt("soluong_kh"));
					hd.setSoluonghd(rshd.getInt("soluong_hd"));
					list.add(hd);
				}
				rshd.close();
				rs.close();
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

		
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		
	
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub

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
			String query = "";
			query = "INSERT INTO KEHOACHDINHHUONG(thang,nam,TINHTRANGTHITRUONG,THANG_NHANDINH," +
					"QUY_NHANDINH,KEHOACHHANHDONG,NGAYTAO,NGUOITAO,NGAYSUA,NGUOISUA,TRANGTHAI) "
				+ "VALUES('"
				+ this.getThang()
				+ "','"
				+ this.getNam()
				+ "',N'"
				+ this.getTinhtrangthitruong()
				+ "',"
				+ this.getNhandinhthoigiantheothang()
				+ ","
				+ this.getNhandinhthoigiantheoquy()
				+",N'Tăng tốc','"
				+ this.getDateTime()
				+ "','100001','"
				+ this.getDateTime()
				+ "','100003','"
				+ "0'" + ")";
			if (!db.update(query)) {
				this.Msg = "Không  thực hiện được câu lệnh: " + query;
				db.getConnection().rollback();
				return false;
			}
			String idcr = "";
			query = "select SCOPE_IDENTITY() as hdid";
			ResultSet rsDck = db.get(query);
			if (rsDck.next()) {
				idcr = rsDck.getString("hdid");
			}
			
			rsDck.close();
			for (int i = 0; i < this.list.size(); i++) {
				IDinhhuong_hoatdong dhhd = list.get(i);
				 	query = "INSERT INTO KEHOACHDINHHUONG_HOATDONG(KEHOACHDINHHUONG_FK,mota,kinhphi,tungay,denngay,soluong_kh,soluong_hd,tenhoatdong) values "
					+ "('"
					+ idcr
					+ "',N'"
					+ dhhd.getMota()
					+ "',"
					+ dhhd.getKinhphi()
					+ ",'"
					+ dhhd.getTungay()
					+ "','"
					+dhhd.getDenngay()
					+ "',"
					+dhhd.getSoluongkh()
					+ ","
					+dhhd.getSoluonghd()
					+ ",N'"
					+ dhhd.getTenhoatdong() + "')";
				 	
				 
				if (!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

		} catch (Exception err) {
			this.Msg = "Lỗi :" + err.getMessage();
			db.update("rollback");
			err.printStackTrace();
			return false;

		}
		return true;
	}
	

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			System.out.println("id co hay khong "+Id.length());
			String query = "";
			if (this.Id.length() > 0) {

				// xoa di
				query = "delete KEHOACHDINHHUONG_HOATDONG  WHERE KEHOACHDINHHUONG_FK =" + this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				query = "UPDATE KEHOACHDINHHUONG set thang ="
						+ this.getThang() + ",nam="
						+ this.getNam() + ",TINHTRANGTHITRUONG= N'"
						+ this.getTinhtrangthitruong() +"',THANG_NHANDINH ="+ this.getNhandinhthoigiantheothang() 
						+",Quy_NHANDINH ="+this.getNhandinhthoigiantheoquy()
						+ " WHERE PK_SEQ=" + this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				for (int i = 0; i < this.list.size(); i++) {
					IDinhhuong_hoatdong dhhd = list.get(i);
					 	query = "INSERT INTO KEHOACHDINHHUONG_HOATDONG(KEHOACHDINHHUONG_FK,mota,kinhphi,tungay,denngay,soluong_kh,soluong_hd,tenhoatdong) values "
						+ "('"
						+ this.Id
						+ "',N'"
						+ dhhd.getMota()
						+ "',"
						+ dhhd.getKinhphi()
						+ ",'"
						+ dhhd.getTungay()
						+ "','"
						+dhhd.getDenngay()
						+ "',"
						+dhhd.getSoluongkh()
						+ ","
						+dhhd.getSoluonghd()
						+ ",N'"
						+ dhhd.getTenhoatdong() + "')";
					 	
					 
					if (!db.update(query)) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

		} catch (Exception err) {
			this.Msg = "Lỗi :" + err.getMessage();
			db.update("rollback");
			err.printStackTrace();
			return false;

		}
		

		return true;
	}


}
