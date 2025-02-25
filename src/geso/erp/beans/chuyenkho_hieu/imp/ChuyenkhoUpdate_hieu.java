package geso.erp.beans.chuyenkho_hieu.imp;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import geso.erp.beans.chuyenkho_hieu.IChuyenkhoUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.ISanpham;
import geso.erp.beans.chuyenkho_hieu.imp.Sanpham;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoUpdate_hieu implements IChuyenkhoUpdate_hieu {
	String Id, Ma, Trangthai, Khochuyen, Khonhan, Ngaychuyenkho, Lydochuyen,
			UserId, Msg, Dvt;
	double booked,available;
	ResultSet rs;
	ResultSet rsck;
	ResultSet rsck1;
	ResultSet rsck2;
	dbutils db = new dbutils();
	List<ISanpham> list;

	public ChuyenkhoUpdate_hieu() {
		Id = "";
		Ma = "";
		Trangthai = "";
		Khochuyen = "";
		Khonhan = "";
		Ngaychuyenkho = "";
		Lydochuyen = "";
		Dvt = "";
		this.list = new ArrayList<ISanpham>();
		this.UserId = "";
		this.Msg = "";
	}

	public ChuyenkhoUpdate_hieu(String id) {
		Id = id;
		Ma = "";
		Trangthai = "";
		Khochuyen = "";
		Khonhan = "";
		Ngaychuyenkho = "";
		Lydochuyen = "";
		Dvt = "";
		this.UserId = "";
		this.Msg = "";
		this.list = new ArrayList<ISanpham>();
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
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String _ma) {
		// TODO Auto-generated method stub
		this.Ma = _ma;
	}

	@Override
	public String getNgaychuyenkho() {
		// TODO Auto-generated method stub
		return this.Ngaychuyenkho;
	}

	@Override
	public void setNgaychuyenkho(String _ngaychuyenkho) {
		// TODO Auto-generated method stub
		this.Ngaychuyenkho = _ngaychuyenkho;
	}

	@Override
	public String getKhochuyen() {
		// TODO Auto-generated method stub
		return this.Khochuyen;
	}

	@Override
	public void setKhochuyen(String _khochuyen) {
		// TODO Auto-generated method stub
		this.Khochuyen = _khochuyen;
	}

	@Override
	public String getKhonhan() {
		// TODO Auto-generated method stub
		return this.Khonhan;
	}

	@Override
	public void setKhonhan(String _khonhan) {
		// TODO Auto-generated method stub
		this.Khonhan = _khonhan;
	}

	@Override
	public String getLydochuyen() {
		// TODO Auto-generated method stub
		return this.Lydochuyen;
	}

	@Override
	public void setLydochuyen(String _lydochuyen) {
		// TODO Auto-generated method stub
		this.Lydochuyen = _lydochuyen;
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
	public ResultSet getRsdvk() {
		// TODO Auto-generated method stub
		return this.rsck2;
	}

	@Override
	public void setRsdvk(ResultSet dvt) {
		// TODO Auto-generated method stub
		this.rsck2 = dvt;
	}
	

	@Override
	public double getBooked() {
		// TODO Auto-generated method stub
		return this.booked;
	}

	@Override
	public void setBooked(double _booked) {
		// TODO Auto-generated method stub
		this.booked=_booked;
	}

	@Override
	public double getavailabel() {
		// TODO Auto-generated method stub
		return this.available;
	}

	@Override
	public void setavailaber(double _availabel) {
		// TODO Auto-generated method stub
		this.available=_availabel;
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
					this.Ngaychuyenkho = rs.getString("ngaychuyenkho");
					this.Khochuyen = rs.getString("KHOCHUYEN");
					this.Khonhan = rs.getString("KHONHAN");
					this.Lydochuyen = rs.getString("LYDO");

				}
				query=	"select sp.PK_SEQ,cksp.SOLUONG as soluongchuyen,sp.MA,sp.TEN as tensp,dv.TEN as tendv, "+
						" khosp.AVAILABLE as soluongton from CHUYENKHO_SANPHAM cksp "+
						" left join ERP_SANPHAM sp on sp.PK_SEQ=cksp.SANPHAM_FK "+
						" left join ERP_DONVI dv on dv.PK_SEQ=sp.DONVI_FK "+
						" left join KHO_SANPHAM khosp on khosp.SANPHAM_FK=cksp.SANPHAM_FK "+
						" where CHUYENKHO_FK="+this.Id +"and khosp.KHO_FK="+this.Khochuyen;
				list.clear();

				ResultSet rssp = db.get(query);
				while (rssp.next()) {
					ISanpham sp = new Sanpham();
					sp.setId(rssp.getString("pk_Seq"));
					sp.setMasanpham(rssp.getString("ma"));
					sp.setTensanpham(rssp.getString("tensp"));
					sp.setDonvitinh(rssp.getString("tendv"));
					sp.setSoluongton(rssp.getDouble("soluongton"));
					sp.setSoluongchuyen(rssp.getDouble("soluongchuyen"));
					list.add(sp);
				}
				rssp.close();
				rs.close();
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String querykh = "select PK_SEQ,TENKHO from kho";
		this.rsck = db.get(querykh);	
		this.rsck1 = db.get(querykh);
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
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "";
			query = "INSERT INTO CHUYENKHO(NGAYCHUYENKHO,KHONHAN,KHOCHUYEN,NGUOITAO,NGUOISUA,TRANGTHAI,NGAYTAO,NGAYSUA,LYDO) "
				+ "VALUES('"
				+ this.getNgaychuyenkho()
				+ "',"
				+ this.getKhonhan()
				+ ","
				+ this.getKhochuyen()
				+ ",100001,"
				+ "100003,'"
				+ "0','"
				+ this.getDateTime()
				+ "','"
				+ this.getDateTime()
				+ "',N'"
				+ this.getLydochuyen() + "')";
			if (!db.update(query)) {
				this.Msg = "Không  thực hiện được câu lệnh: " + query;
				db.getConnection().rollback();
				return false;
			}
			String idcr = "";
			query = "select SCOPE_IDENTITY() as dhid";
			ResultSet rsDck = db.get(query);
			if (rsDck.next()) {
				idcr = rsDck.getString("dhid");
			}
			System.out.println("Ma can lay :"+idcr);
			rsDck.close();
			for (int i = 0; i < this.list.size(); i++) {
				ISanpham sp = list.get(i);
				 	query = "INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values "
					+ "("
					+ idcr
					+ ",'"
					+ sp.getId()
					+ "',"
					+ sp.getSoluongchuyen() + ")";
				 	if (!db.update(query)) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				 	query="UPDATE KHO_SANPHAM SET BOOKED = "
				 		+sp.getSoluongchuyen()
				 		+",AVAILABLE= "
				 		+(sp.getSoluongton()-sp.getSoluongchuyen())+
				 		" WHERE KHO_FK = "+this.Khochuyen+" and SANPHAM_FK= "+sp.getId();
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
				query = "delete CHUYENKHO_SANPHAM  WHERE CHUYENKHO_FK =" + this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				query = "UPDATE CHUYENKHO set NGAYCHUYENKHO ="
						+ this.getNgaychuyenkho() + ",KHONHAN="
						+ this.getKhonhan() + ",KHOCHUYEN="
						+ this.getKhochuyen() +",LYDO =N'"+ this.getLydochuyen() +"'"
						+ " WHERE PK_SEQ=" + this.Id;
				if (!db.update(query)) {
					this.Msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				for (int i = 0; i < this.list.size(); i++) {
					ISanpham sp = list.get(i);
					
					query = "INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK,SANPHAM_FK,SOLUONG) values "
						+ "('"
						+ this.Id
						+ "','"
						+ sp.getId()
						+ "',"
						+ sp.getSoluongchuyen() + ")";
					if (!db.update(query)) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
					query = "UPDATE KHO_SANPHAM SET BOOKED="+'0' 
					+" WHERE KHO_FK=" + this.Khochuyen +"and SANPHAM_FK= "+sp.getId() ;
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
					query="UPDATE KHO_SANPHAM SET BOOKED = "
				 		+sp.getSoluongchuyen()
				 		+",AVAILABLE=SOLUONG- "
				 		+sp.getSoluongchuyen()+
				 		" WHERE KHO_FK = "+this.Khochuyen+" and SANPHAM_FK= "+sp.getId();
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

	@Override
	public List<ISanpham> getListSp() {
		// TODO Auto-generated method stub
		return this.list;
	}

	@Override
	public void setListSp(List<ISanpham> _list) {
		// TODO Auto-generated method stub
		this.list = _list;
	}

	@Override
	public ResultSet getRsck() {
		// TODO Auto-generated method stub
		return this.rsck;
	}

	@Override
	public void setRsck(ResultSet _cklist) {
		// TODO Auto-generated method stub
		this.rsck = _cklist;
	}

	@Override
	public ResultSet getRsck1() {
		// TODO Auto-generated method stub
		return this.rsck1;
	}

	@Override
	public void setRsck1(ResultSet _cklist) {
		// TODO Auto-generated method stub
		this.rsck1 = _cklist;
	}

}
