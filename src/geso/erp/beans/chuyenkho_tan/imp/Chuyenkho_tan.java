package geso.erp.beans.chuyenkho_tan.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_tan.IChuyenkho_tan;
import geso.erp.db.sql.dbutils;

public class Chuyenkho_tan extends Phan_Trang implements IChuyenkho_tan{

	String userId;
	String id;
	String pk_chuyenkho;
	String khochuyen;
	String khonhan;
	String trangthai;
	String ngaytao;
	String ngaysua;
	String lydo;
	
	String msg;
	ResultSet rslist;
	ResultSet rsKhochuyen;
	ResultSet rsKhonhan;
	dbutils db=new dbutils();
	
	public Chuyenkho_tan(){
		this.userId = "";
		this.id = "";
		this.pk_chuyenkho = "";
		this.khochuyen = "";
		this.khonhan = "";
		this.trangthai = "";
		this.msg = "";
		
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setUserId(String _userId) {
		// TODO Auto-generated method stub
		this.userId = _userId;
	}

	@Override
	public String get_pk_chuyenkho() {
		// TODO Auto-generated method stub
		return this.pk_chuyenkho;
	}

	@Override
	public void set_pk_chuyenkho(String _maCK) {
		// TODO Auto-generated method stub
		this.pk_chuyenkho =_maCK;
	}

	@Override
	public String getKhochuyen() {
		// TODO Auto-generated method stub
		return this.khochuyen;
	}

	@Override
	public void setKhochuyen(String _khochuyen) {
		// TODO Auto-generated method stub
		this.khochuyen = _khochuyen;
	}

	@Override
	public String getKhonhan() {
		// TODO Auto-generated method stub
		return this.khonhan;
	}

	@Override
	public void setKhonhan(String _khonhan) {
		// TODO Auto-generated method stub
		this.khonhan = _khonhan;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.trangthai;
	}

	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.trangthai = _trangthai;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.ngaytao;
	}

	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.ngaytao = _ngaytao;
	}

	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.ngaysua;
	}

	@Override
	public void setNgaysua(String _ngaysua) {
		// TODO Auto-generated method stub
		this.ngaysua = _ngaysua;
	}

	@Override
	public String getLydo() {
		// TODO Auto-generated method stub
		return this.lydo;
	}
	@Override
	public void setLydo(String _lydo) {
		// TODO Auto-generated method stub
		this.lydo = _lydo;
	}

	@Override
	public String getMsg(){
		return this.msg;
	}
	@Override
	public void setMsg(String _msg){
		this.msg = _msg;
	}
	
	@Override
	public ResultSet getRsList() {
		// TODO Auto-generated method stub
		return this.rslist;
	}
	@Override
	public void setRsList(ResultSet _listCK) {
		// TODO Auto-generated method stub
		this.rslist = _listCK;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.id = _id;
	}

	@Override
	public ResultSet getRsKhochuyen() {
		// TODO Auto-generated method stub
		return this.rsKhochuyen;
	}

	@Override
	public void setRsKhochuyen(ResultSet _rsKhochuyen) {
		// TODO Auto-generated method stub
		this.rsKhochuyen = _rsKhochuyen;
	}
	@Override
	public ResultSet getRsKhonhan() {
		// TODO Auto-generated method stub
		return this.rsKhonhan;
	}

	@Override
	public void setRsKhonhan(ResultSet _rsKhonhan) {
		// TODO Auto-generated method stub
		this.rsKhonhan = _rsKhonhan;
	}

	@Override
	public void init(String _str) {
		// TODO Auto-generated method stub
		String query = "SELECT CK.PK_SEQ as PK_CHUYENKHO, CK.LYDO, "
						+ "ISNULL(CK.TRANGTHAI,'') AS TRANGTHAI, "
						+ "CK.NGUOITAO, NV.TEN as TENNGUOITAO, CK.NGAYTAO, "
						+ "CK.NGUOISUA, NV2.TEN as TENNGUOISUA, CK.NGAYSUA, CK.KHOCHUYEN, CK.KHONHAN "
					+ "FROM CHUYENKHO CK "
						+ "LEFT JOIN NHANVIEN NV ON CK.NGUOITAO = NV.PK_SEQ " 
						+ "LEFT JOIN NHANVIEN NV2 ON CK.NGUOISUA = NV.PK_SEQ "
					+ "WHERE 1=1";
		if(_str.length() >0){
			query = _str;
		}
		this.rslist=db.get(query);
		CreateRs();
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		// load danh sách kho vào comboBox ....
		String queryKHO = "SELECT KHO.PK_SEQ as MAKHO, KHO.TENKHO FROM KHO";
		this.rsKhochuyen=db.get(queryKHO);
		this.rsKhonhan = db.get(queryKHO);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public boolean Delete_Chuyenkho() {
		// TODO Auto-generated method stub
		System.out.println("lấy id gì đây: "+this.id);
		try {
			db.getConnection().setAutoCommit(false);

			String query = "UPDATE CHUYENKHO SET TRANGTHAI='2' WHERE PK_SEQ=" + this.id;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
		} catch (Exception ex) {
			db.update("rollback");
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean Chot_Chuyenkho() {
		// TODO Auto-generated method stub
		System.out.println("lấy id chốt gì đây: "+this.id);
		try {
			db.getConnection().setAutoCommit(false);

			String queryGiamKC= "UPDATE KSP "
						+ "SET KSP.SOLUONG = KSP.SOLUONG - CKSP.SOLUONG "
						+ " FROM KHO_SANPHAM KSP "
							+ " JOIN KHO ON KSP.KHO_FK = KHO.PK_SEQ " 
							+ " JOIN CHUYENKHO CK ON CK.KHOCHUYEN = KHO.PK_SEQ "
							+ " JOIN CHUYENKHO_SANPHAM CKSP ON CKSP.CHUYENKHO_FK = CK.PK_SEQ "
						+ " WHERE CKSP.SANPHAM_FK = KSP.SANPHAM_FK "
						+ " 	AND CK.PK_SEQ="+this.id;
			String queryGiamKN= "UPDATE KSP "
					+ "SET KSP.AVAILABLE = KSP.AVAILABLE + CKSP.SOLUONG, KSP.SOLUONG = KSP.SOLUONG + CKSP.SOLUONG "
					+ " FROM KHO_SANPHAM KSP "
						+ " JOIN KHO ON KSP.KHO_FK = KHO.PK_SEQ " 
						+ " JOIN CHUYENKHO CK ON CK.KHONHAN = KHO.PK_SEQ "
						+ " JOIN CHUYENKHO_SANPHAM CKSP ON CKSP.CHUYENKHO_FK = CK.PK_SEQ "
					+ " WHERE CKSP.SANPHAM_FK = KSP.SANPHAM_FK "
					+ " 	AND CK.PK_SEQ="+this.id;
			String queryKN = "SELECT CKSP.SOLUONG, KHONHAN, CKSP.SANPHAM_FK "
					+ " FROM KHO_SANPHAM KSP "
						+ " JOIN KHO ON KSP.KHO_FK = KHO.PK_SEQ " 
						+ " JOIN CHUYENKHO CK ON CK.KHONHAN = KHO.PK_SEQ "
						+ " JOIN CHUYENKHO_SANPHAM CKSP ON CKSP.CHUYENKHO_FK = CK.PK_SEQ "
					+ " WHERE CKSP.SANPHAM_FK = KSP.SANPHAM_FK "
					+ " 	AND CK.PK_SEQ="+this.id;
			String query = "UPDATE CHUYENKHO SET TRANGTHAI='1' WHERE PK_SEQ=" + this.id;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}			
			if (db.updateReturnInt(queryGiamKC) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}
			ResultSet Khonhan = db.get(queryKN);
			// di chuyển về dòng cuối cùng
			Khonhan.last();
			// lấy vị trí dòng cuối cũng chính là số lượng dòng trong kết quả
			int count = Khonhan.getRow();
			
			if(count!=0){
				if (db.updateReturnInt(queryGiamKN) != 1) {
					db.getConnection().rollback();
					this.msg = "Không thể cập nhật dòng lệnh: " + query;
					return false;
				}
			}else{
				/*String str = "SELECT KHONHAN "
						+ "FROM KHO "
							+ "JOIN CHUYENKHO CK ON CK.KHONHAN = KHO.PK_SEQ "
						+ " WHERE CK.PK_SEQ = "+this.id;*/
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			return true;
		} catch (Exception ex) {
			db.update("rollback");
			ex.printStackTrace();
			return false;
		}
	}
	
}
