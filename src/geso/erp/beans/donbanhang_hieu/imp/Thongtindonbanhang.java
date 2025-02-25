package geso.erp.beans.donbanhang_hieu.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.donbanhang_hieu.IThongtindonbanhang;
import geso.erp.beans.donbanhang_hieu.Isanpham;
import geso.erp.db.sql.dbutils;

public class Thongtindonbanhang implements IThongtindonbanhang {
	String Id, Ma, Trangthai, Khachhang, Ngay, Ngaytao, Nguoitao, UserId, msg,
			Idkhachhang, Tenkhachhang, Dienthoai, Diachi, Iscapnhat;
	ResultSet rs;
	ResultSet rskh;
	double Tongtien, Thanhtien;
	List<Isanpham> list;

	@Override
	public ResultSet getRskh() {
		// TODO Auto-generated method stub
		return this.rskh;
	}

	@Override
	public void setRskh(ResultSet dvt) {
		// TODO Auto-generated method stub
		this.rskh = dvt;

	}

	dbutils db = new dbutils();

	public Thongtindonbanhang() {
		// TODO Auto-generated constructor stub

		Id = "";
		Ma = "";
		Trangthai = "";
		Khachhang = "";
		Ngay = "";
		Tongtien = 0;
		Thanhtien = 0;
		Ngaytao = "";
		Nguoitao = "";
		this.UserId = "";
		this.msg = "";
		this.list = new ArrayList<Isanpham>();
		Idkhachhang = "";
		Tenkhachhang = "";
		Dienthoai = "";
		Diachi = "";
		Iscapnhat = "";

	}
	public Thongtindonbanhang(String _id) {
		// TODO Auto-generated constructor stub

		Id = _id;
		Ma = "";
		Trangthai = "";
		Khachhang = "";
		Ngay = "";
		Tongtien = 0;
		Thanhtien = 0;
		Ngaytao = "";
		Nguoitao = "";
		this.UserId = "";
		this.msg = "";
		this.list = new ArrayList<Isanpham>();
		Idkhachhang = "";
		Tenkhachhang = "";
		Dienthoai = "";
		Diachi = "";
		this.Iscapnhat = "1";

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
	public String getNgaychungtu() {
		// TODO Auto-generated method stub
		return this.Ngay;
	}

	@Override
	public void setNgaychungtu(String _ngaychungtu) {
		// TODO Auto-generated method stub
		this.Ngay = _ngaychungtu;
	}

	@Override
	public String getKhachhang() {
		// TODO Auto-generated method stub
		return this.Khachhang;
	}

	@Override
	public void setKhachhang(String _khachhang) {
		// TODO Auto-generated method stub
		this.Khachhang = _khachhang;
	}

	@Override
	public double getThanhtien() {
		// TODO Auto-generated method stub
		return this.Thanhtien;
	}

	@Override
	public void setThanhtien(double _thanhtien) {
		// TODO Auto-generated method stub
		this.Thanhtien = _thanhtien;
	}

	@Override
	public double getTongtien() {
		// TODO Auto-generated method stub
		return this.Tongtien;
	}

	@Override
	public void setTongtien(double _tongtien) {
		// TODO Auto-generated method stub
		this.Tongtien = _tongtien;
	}

	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}

	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao = _nguoitao;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}

	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao = _ngaytao;
	}

	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg = _msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.msg;
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
	public String getTenkhachhang() {
		// TODO Auto-generated method stub
		return this.Tenkhachhang;
	}

	@Override
	public void setTenkhachhang(String _tenkhachhang) {
		// TODO Auto-generated method stub
		this.Tenkhachhang = _tenkhachhang;
	}

	@Override
	public String getDienthoai() {
		// TODO Auto-generated method stub
		return this.Dienthoai;
	}

	@Override
	public void setDienthoai(String _dienthoai) {
		// TODO Auto-generated method stub
		this.Dienthoai = _dienthoai;
	}

	@Override
	public String getDiachi() {
		// TODO Auto-generated method stub
		return this.Diachi;
	}

	@Override
	public void setDiachi(String _diachi) {
		// TODO Auto-generated method stub
		this.Diachi = _diachi;
	}

	@Override
	public String getIdkhachhang() {
		// TODO Auto-generated method stub
		return this.Idkhachhang;
	}

	@Override
	public void setIdkhachhang(String _Idkhachhang) {
		// TODO Auto-generated method stub
		this.Idkhachhang = _Idkhachhang;
	}

	@Override
	public String getIscapnhat() {
		// TODO Auto-generated method stub
		return this.Iscapnhat;
	}

	@Override
	public void setIsCapNhat(String _iscapnhat) {
		// TODO Auto-generated method stub
		this.Iscapnhat = _iscapnhat;
	}

	@Override
	public void init() {
		try {
			if (Id.trim().length() > 0) {
				String query = "SELECT  NGAYCHUNGTU,TONGTIEN,KHACHHANG_FK from BANHANG where PK_SEQ="
						+ Id;
				System.out.println(query);

				ResultSet rs = db.get(query);
				if (rs.next()) {
					this.Ngay = rs.getString("NGAYCHUNGTU");
					this.Tongtien = rs.getDouble("tongtien");
					this.Khachhang = rs.getString("KHACHHANG_FK");

				}
				query = "select  dhsp.soluong,dhsp.dongia,dhsp.thanhtien,sp.pk_seq,sp.chietkhau"
						+ ",sp.ma,sp.ten, isnull(dv.ten,'') as donvi "
						+ " from DONHANG_SANPHAM  "
						+ " dhsp inner join sanpham sp on sp.PK_SEQ=dhsp.SANPHAM_FK "
						+ " left join donvi dv on dv.pk_Seq=sp.DONVI_FK "
						+ " where dhsp.DONHANG_Fk= " + this.Id;

				list.clear();

				ResultSet rssp = db.get(query);
				while (rssp.next()) {
					Isanpham sp = new Sanpham();
					sp.setId(rssp.getString("pk_Seq"));
					sp.setMasanpham(rssp.getString("ma"));
					sp.setTensanpham(rssp.getString("ten"));
					sp.setDonvitinh(rssp.getString("donvi"));
					sp.setDongia(rssp.getDouble("dongia"));
					sp.setSoluong(rssp.getDouble("soluong"));
					sp.setChietkhau(rssp.getDouble("chietkhau"));
					list.add(sp);
				}
				rssp.close();

				rs.close();
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String querykh = "select PK_SEQ,TEN from KHACHHANG";
		this.rskh = db.get(querykh);

	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean save() {
		// TODO Auto-generated methods tu
		try {
			db.getConnection().setAutoCommit(false);

			String query = "";
			if (Id.length() > 0) {

				// xoa di
				query = "delete  DONHANG_SANPHAM  WHERE DONHANG_FK =" + this.Id;
				if (!db.update(query)) {
					this.msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}

				query = "UPDATE BANHANG set khachhang_fk="
						+ this.getKhachhang() + ",NGAYCHUNGTU=N'"
						+ this.getNgaychungtu() + "'," + "tongtien=N'"
						+ this.Tongtien + "' WHERE PK_SEQ=" + this.Id;
				if (!db.update(query)) {
					this.msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				for (int i = 0; i < this.list.size(); i++) {
					Isanpham sp = list.get(i);
					query = "INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,CHIETKHAU,THANHTIEN) values "
							+ "("
							+ this.Id
							+ ","
							+ sp.getId()
							+ ","
							+ sp.getSoluong()
							+ ","
							+ sp.getDongia()
							+","
							+sp.getChietkhau()
							+ ","
							+ sp.getThanhtien() + ")";

					if (!db.update(query)) {
						db.getConnection().rollback();
						this.msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}

			} else {
				query = "INSERT INTO BANHANG ( NGAYCHUNGTU,TRANGTHAI,KHACHHANG_FK,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TONGTIEN) "
						+ "VALUES('"
						+ this.Ngay
						+ "','0','"
						+ this.Khachhang
						+ "','10000','"
						+ this.getDateTime()
						+ "','10000','"
						+ this.getDateTime() + "'," + this.Tongtien + ")";
				if (!db.update(query)) {
					this.msg = "Không  thực hiện được câu lệnh: " + query;
					db.getConnection().rollback();
					return false;
				}
				String idcr = "";
				query = "select SCOPE_IDENTITY() as dhid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next()) {
					idcr = rsDmh.getString("dhid");

				}
				rsDmh.close();

				for (int i = 0; i < this.list.size(); i++) {
					Isanpham sp = list.get(i);
					query = "INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,CHIETKHAU,THANHTIEN) values "
							+ "("
							+ idcr
							+ ","
							+ sp.getId()
							+ ","
							+ sp.getSoluong()
							+ ","
							+ sp.getDongia()
							+","
							+sp.getChietkhau()
							+ ","
							+ sp.getThanhtien() + ")";

					if (!db.update(query)) {
						db.getConnection().rollback();
						this.msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}

			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

		} catch (Exception err) {
			this.msg = "Lỗi :" + err.getMessage();
			db.update("rollback");
			err.printStackTrace();
			return false;

		}

		return true;

	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Override
	public List<Isanpham> getListSp() {
		// TODO Auto-generated method stub
		return this.list;
	}

	@Override
	public void setListSp(List<Isanpham> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	@Override
	public boolean savekhachhang() {
		// TODO Auto-generated method stub
		try {

			if (this.Khachhang.trim().equals("")) {
				this.msg = "Vui lòng nhập mã khách hàng";
				return false;
			}
			if (this.Tenkhachhang.trim().equals("")) {
				this.msg = "Vui lòng nhập tên khách hàng";
				return false;
			}

			String query = "SELECT pk_seq FROM KHACHHANG WHERE SHOP_FK= (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="
					+ this.UserId + ") AND  MA='" + this.Khachhang + "'";
			ResultSet rs = db.get(query);
			if (rs.next()) {
				query = "UPDATE KHACHHANG set TEN=N'"
						+ this.Tenkhachhang
						+ "',dienthoai=N'"
						+ this.Dienthoai
						+ "', diachi=N'"
						+ this.Diachi
						+ "' "
						+ " WHERE MA='"
						+ this.Khachhang
						+ "' AND SHOP_FK=(SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="
						+ this.UserId + ") ";
				this.Idkhachhang = rs.getString("pk_seq");
				if (!db.update(query)) {
					this.msg = "Không thể cập nhật khách hàng: " + query;
					return false;
				}
			} else {
				System.out.println("vao ham savekhachhang");
				query = "INSERT INTO KHACHHANG (MA,TEN,DIACHI,DIENTHOAI,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TRANGTHAI,SHOP_FK)  "
						+ "SELECT '"
						+ this.Khachhang
						+ "',N'"
						+ this.Tenkhachhang
						+ "',N'"
						+ this.Diachi
						+ "','"
						+ this.Dienthoai
						+ "',"
						+ this.UserId
						+ ",getdate(),"
						+ this.UserId
						+ ",getdate(),1, (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="
						+ this.UserId + ") ";

				if (!db.update(query)) {
					this.msg = "Không thể cập nhật khách hàng: " + query;
					return false;
				}
				query = "select SCOPE_IDENTITY() as khid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next()) {
					this.Idkhachhang = rsDmh.getString("khid");

				}
				rsDmh.close();

			}

		} catch (Exception er) {
			er.printStackTrace();
			this.msg = "Vui lòng thử lại : lỗi " + er.getMessage();
			return false;

		}
		return false;
	}

}
