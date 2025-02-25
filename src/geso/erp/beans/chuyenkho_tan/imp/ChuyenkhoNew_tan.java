package geso.erp.beans.chuyenkho_tan.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.erp.beans.chuyenkho_tan.IChuyenkhoNew_tan;
import geso.erp.beans.chuyenkho_tan.ISanpham_tan;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoNew_tan implements IChuyenkhoNew_tan{
	String IsCapNhat;
	String msg;
	String userId;
	String Id;
	String MaCK;
	String soluong;
	String khonhan;
	String khochuyen;
	String ngaychuyenkho;
	String lydo;
	String trangthai;
	
	dbutils db=new dbutils();
	ResultSet rsCK;
	ResultSet rsKhochuyen;
	ResultSet rsKhonhan;
	List<ISanpham_tan> splist = new ArrayList<ISanpham_tan>();
	
	public ChuyenkhoNew_tan(String _str){
		// TODO Auto-generated constructor stub
			this.Id = _str;
			this.MaCK = "";
			this.trangthai = "";
			this.khochuyen = "";
			this.khonhan = "";
			this.ngaychuyenkho = "";
			this.lydo ="";
			this.userId = "";
			this.IsCapNhat="1";
			this.msg="";
			this.splist=new ArrayList<ISanpham_tan>();
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
	public String getMaCK() {
		// TODO Auto-generated method stub
		return this.MaCK;
	}

	@Override
	public void setMaCK(String _MaCK) {
		// TODO Auto-generated method stub
		this.MaCK = _MaCK;
	}
	
	@Override
	public String getSoluong() {
		// TODO Auto-generated method stub
		return this.soluong;
	}

	@Override
	public void setSoluong(String _soluong) {
		// TODO Auto-generated method stub
		this.soluong = _soluong;
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
	public String getNgayChuyenkho() {
		// TODO Auto-generated method stub
		return this.ngaychuyenkho;
	}

	@Override
	public void setNgayChuyenkho(String _ngayghinhan) {
		// TODO Auto-generated method stub
		this.ngaychuyenkho = _ngayghinhan;
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
	public String getIsCapNhat() {
		// TODO Auto-generated method stub
		return this.IsCapNhat;
	}

	@Override
	public void setIsCapNhat(String _isCapNhat) {
		// TODO Auto-generated method stub
		this.IsCapNhat = _isCapNhat;
	}

	@Override
	public void setMsg(String _msg) {
		// TODO Auto-generated method stub
		this.msg = _msg;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public ResultSet getRsList() {
		// TODO Auto-generated method stub
		return this.rsCK;
	}

	@Override
	public void setRsList(ResultSet _CKlist) {
		// TODO Auto-generated method stub
		this.rsCK = _CKlist;
	}

	@Override
	public ResultSet getRsKhochuyen() {
		// TODO Auto-generated method stub
		return this.rsKhochuyen;
	}

	@Override
	public void setRsKhochuyen(ResultSet _khochuyen) {
		// TODO Auto-generated method stub
		this.rsKhochuyen = _khochuyen;
	}

	@Override
	public ResultSet getRsKhonhan() {
		// TODO Auto-generated method stub
		return this.rsKhonhan;
	}

	@Override
	public void setRsKhonhan(ResultSet _khonhan) {
		// TODO Auto-generated method stub
		this.rsKhonhan = _khonhan;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try{
			if(this.Id.length() >0){
			String query="SELECT isnull(CK.PK_SEQ,'') as MACK, CK.NGAYCHUYENKHO, CK.TRANGTHAI, "
					+ "CK.NGUOITAO, CK.NGAYTAO, CK.NGUOISUA, CK.NGAYSUA, CK.KHOCHUYEN, CK.KHONHAN, CK.LYDO "
					+ "FROM CHUYENKHO CK " +
					" WHERE CK.PK_SEQ="+this.Id;
			ResultSet rs=db.get(query);
			if(rs.next()){
				if(rs.getString("TRANGTHAI").equals("1")||rs.getString("TRANGTHAI").equals("2")){
					this.IsCapNhat = "0";
				}else{
					this.IsCapNhat = "1";
				}
				this.ngaychuyenkho = rs.getString("NGAYCHUYENKHO");
				this.lydo = rs.getString("LYDO");
				this.khochuyen = rs.getString("KHOCHUYEN");
				this.khonhan = rs.getString("KHONHAN");
			}
			rs.close();
			query="SELECT CKSP.SANPHAM_FK, SP.MA as MASP, SP.TEN as TENSP, DV.TEN as DONVI, CKSP.CHUYENKHO_FK, " 
					+ "KSP.AVAILABLE as SOLUONGTON, CKSP.SOLUONG as SOLUONGCHUYEN "
				+ "FROM CHUYENKHO_SANPHAM CKSP "
					+ "INNER JOIN ERP_SANPHAM SP ON SP.PK_SEQ = CKSP.SANPHAM_FK "
					+ "LEFT JOIN KHO_SANPHAM KSP ON KSP.SANPHAM_FK = SP.PK_SEQ "
					+ "LEFT JOIN ERP_DONVI DV ON DV.PK_SEQ = SP.DONVI_FK "
				+ " WHERE CKSP.CHUYENKHO_FK="+this.Id;
			splist.clear();
			
			ResultSet rssp=db.get(query);
			while(rssp.next()){
				ISanpham_tan sp=new Sanpham_tan();
				sp.setPK_SEQ(rssp.getString("SANPHAM_FK"));
				sp.setTensanpham(rssp.getString("TENSP"));
				sp.setMasanpham(rssp.getString("MASP"));
				sp.setDonvitinh(rssp.getString("DONVI"));
				sp.setSoluongTon(rssp.getDouble("SOLUONGTON"));
				sp.setSoluongChuyen(rssp.getDouble("SOLUONGCHUYEN"));
				this.splist.add(sp);
			}
			rssp.close();
			 
			}
		}catch(Exception er){
			er.printStackTrace();
		}
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String queryKHO = "SELECT KHO.PK_SEQ as MAKHO, KHO.TENKHO FROM KHO";
		this.rsKhochuyen=db.get(queryKHO);
		this.rsKhonhan = db.get(queryKHO);
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try{
			if(this.splist.size()==0){
				this.msg="Vui lòng nhập sản phẩm trước khi lưu";
				return false;
			}
			if(this.khochuyen==null|| this.khochuyen.length()==0){
				this.msg="Vui lòng chọn kho chuyển";
				return false;
			}
			if(this.khonhan==null|| this.khonhan.length()==0){
				this.msg="Vui lòng chọn kho nhận";
				return false;
			}
			db.getConnection().setAutoCommit(false);
			
			String query=" INSERT INTO CHUYENKHO (NGAYCHUYENKHO, TRANGTHAI, NGUOITAO, NGAYTAO, "
					+ "NGUOISUA, NGAYSUA, LYDO, KHONHAN, KHOCHUYEN) VALUES " +
					"('"+this.ngaychuyenkho+"', '0', "+this.userId+", getdate(), "
					+this.userId+", getdate(), "+this.lydo+","+this.khonhan+","+this.khochuyen+"";
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}			
			String CKCurrent = "";
			query = "select SCOPE_IDENTITY() as CKId";
			ResultSet rsCK = db.get(query);
			if (rsCK.next())
			{
				CKCurrent = rsCK.getString("CKId");
				rsCK.close();
			}			
			for(int i=0;i<splist.size();i++){
				ISanpham_tan sp=splist.get(i);
				query="INSERT INTO CHUYENKHO_SANPHAM (CHUYENKHO_FK, SANPHAM_FK, SOLUONG) VALUES ("
						+ ""+CKCurrent+", "+sp.getPK_SEQ()+", "+sp.getSoluongChuyen()+")";
				
				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				query="UPDATE KHO_SANPHAM "
						+ "SET AVAILABLE=AVAILABLE-"+sp.getSoluongChuyen()+", SOLUONG=AVAILABLE+BOOKED"
						+ "WHERE KHO_FK="+this.MaCK+" AND SAPPHAM_FK = "+sp.getPK_SEQ();
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			this.msg="Bạn vừa thêm thành công việc chuyển kho có mã : "+CKCurrent;
			this.Id=CKCurrent;
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			db.update("rollback");
			this.msg=ex.getMessage();
			return false;
		}
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		try{
			if(this.splist.size()==0){
				this.msg="Vui lòng nhập sản phẩm trước khi lưu";
				return false;
			}
			
			if(this.khochuyen==null|| this.khochuyen.length()==0){
				this.msg="Vui lòng chọn kho chuyển";
				return false;
			}
			if(this.khonhan==null||this.khonhan.length()==0){
				this.msg="Vui lòng chọn kho nhận";
				return false;
			}
			db.getConnection().setAutoCommit(false);
			String query="SELECT TRANGTHAI FROM CHUYENKHO WHERE PK_SEQ="+this.Id;
			ResultSet rsCK=db.get(query);
			if(rsCK.next()){
				this.trangthai=rsCK.getString("TRANGTHAI");
			}
			rsCK.close();
			if(!this.getTrangthai().equals("0")){
				this.msg="Không thể cập nhật việc chuyển kho này, mã chuyển kho này đã "+(this.trangthai.equals("2") ? "Xóa":"Chốt");
				return false;				 
			}
			db.getConnection().setAutoCommit(false);
			query="SELECT SANPHAM_FK, SOLUONG "
				+ "FROM CHUYENKHO_SANPHAM CKSP"
			  		+ "INNER JOIN CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "
				+ " WHERE CK.TRANGTHAI='0' AND CKSP.CHUYENKHO_FK="+this.Id;
			
			ResultSet rs=db.get(query);
			while (rs.next()){
				query="UPDATE KHO_SANPHAM "
						+ "SET SOLUONG=SOLUONG+ "+rs.getDouble("SOLUONG")+" WHERE PK_SEQ="+rs.getString("SANPHAM_FK");
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				 
			}
			query="delete CHUYENKHO_SANPHAM where CHUYENKHO_FK="+this.Id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			
			for(int i=0;i<splist.size();i++){
				ISanpham_tan sp=splist.get(i);
				query="INSERT INTO CHUYENKHO_SANPHAM(CHUYENKHO_FK,SANPHAM_FK,SOLUONG) "
						+ "VALUES("+this.Id+", "+sp.getPK_SEQ()+", "+sp.getSoluongChuyen()+")";
				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				query="UPDATE KHO_SANPHAM SET SOLUONG=SOLUONG-"+sp.getSoluongChuyen()+" WHERE PK_SEQ="+sp.getPK_SEQ();
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				
				
			}		
			
			query="UPDATE CHUYENKHO"
				+ "SET NGAYCHUYENKHO='"+this.ngaychuyenkho+"', '"+this.lydo+"', "
						+ ""+this.khochuyen+", "+this.khonhan+" "
				+ "WHERE PK_SEQ="+this.Id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			this.msg="Bạn vừa sửa thành công chuyển kho mã : "+this.Id;
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			db.update("rollback");
			this.msg=ex.getMessage();
			return false;
			
		}
	}
	@Override
	public boolean Delete_ck() {
		try{
			String query="SELECT TRANGTHAI FROM CHUYENKHO WHERE PK_SEQ="+this.Id;
			
			ResultSet rsck=db.get(query);
			
			if(rsck.next()){
				this.trangthai=rsck.getString("TRANGTHAI");
			}
			rsck.close();
			
			if(!this.getTrangthai().equals("0")){
				this.msg="Không thể xóa đơn hàng này, đơn hàng này đã "+(this.trangthai.equals("2") ? "Xóa":"Chốt");
				return false;
			}
			db.getConnection().setAutoCommit(false);
			
			query=" SELECT CHUYENKHO_FK, SANPHAM_FK, SOLUONG "
				+ "FROM CHUYENKHO_SANPHAM "
					+ "INNER JOIN CHUYENKHO CK ON CK.PK_SEQ=CHUYENKHO_FK " 
				+" WHERE CK.TRANGTHAI='0' AND CHUYENKHO_FK="+this.Id;
			ResultSet rs=db.get(query);
			while (rs.next()){
				query="UPDATE KHO_SANPHAM SET SOLUONG=SOLUONG+ "+rs.getDouble("SOLUONG")+" "
						+ "WHERE PK_SEQ="+rs.getString("SANPHAM_FK");
				if(db.updateReturnInt(query)!=1){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				 
			}
			
			query="UPDATE CHUYENKHO SET TRANGTHAI='2' WHERE PK_SEQ="+this.Id;
			if(db.updateReturnInt(query)!=1){
				db.getConnection().rollback();
				this.msg="Không thể cập nhật dòng lệnh: "+ query;
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			
			return true;
		}catch(Exception er){
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ISanpham_tan> getListSp() {
		// TODO Auto-generated method stub
		return this.splist;
	}

	@Override
	public void setListSp(List<ISanpham_tan> _list) {
		// TODO Auto-generated method stub
		this.splist = _list;
	}

}
