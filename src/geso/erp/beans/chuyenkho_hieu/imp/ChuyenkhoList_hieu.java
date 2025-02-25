package geso.erp.beans.chuyenkho_hieu.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_hieu.IChuyenkhoList_hieu;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoList_hieu extends Phan_Trang implements IChuyenkhoList_hieu {
	String Id, Ma, Trangthai,Khochuyen,Khonhan,Ngaychuyenkho,Ngaytao,Nguoitao,Nguoisua,Ngaysua,Lydochuyen,UserId, Msg;

	ResultSet rs;
	ResultSet rsck;
	ResultSet rsck1;
	dbutils db = new dbutils();
	public ChuyenkhoList_hieu() {
		Id = "";
		Ma = "";
		Trangthai = "";
		Khochuyen = "";
		Khonhan="";
		Ngaychuyenkho = "";
		Lydochuyen ="";
		Ngaytao = "";
		Nguoitao = "";
		Ngaysua="";
		Nguoisua="";
		this.UserId = "";
		this.Msg = "";
	}
	public ChuyenkhoList_hieu(String ma) {
		Id = "";
		Ma = ma;
		Trangthai = "";
		Khochuyen = "";
		Khonhan="";
		Ngaychuyenkho = "";
		Lydochuyen ="";
		Ngaytao = "";
		Nguoitao = "";
		Ngaysua="";
		Nguoisua="";
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
		this.Id=_userId;
	}
	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}
	@Override
	public void setTrangthai(String _trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=_trangthai;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String _id) {
		// TODO Auto-generated method stub
		this.Id=_id;
	}
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}
	@Override
	public void setMa(String _ma) {
		// TODO Auto-generated method stub
		this.Ma=_ma;
	}
	@Override
	public String getNgaychuyenkho() {
		// TODO Auto-generated method stub
		return this.Ngaychuyenkho;
	}
	@Override
	public void setNgaychuyenkho(String _ngaychuyenkho) {
		// TODO Auto-generated method stub
		this.Ngaychuyenkho=_ngaychuyenkho;
	}
	@Override
	public String getKhochuyen() {
		// TODO Auto-generated method stub
		return this.Khochuyen;
	}
	@Override
	public void setKhochuyen(String _khochuyen) {
		// TODO Auto-generated method stub
		this.Khochuyen=_khochuyen;
	}
	@Override
	public String getKhonhan() {
		// TODO Auto-generated method stub
		return this.Khonhan;
	}
	@Override
	public void setKhonhan(String _khonhan) {
		// TODO Auto-generated method stub
		this.Khonhan=_khonhan;
	}
	@Override
	public String getLydochuyen() {
		// TODO Auto-generated method stub
		return this.Lydochuyen;
	}
	@Override
	public void setLydochuyen(String _lydochuyen) {
		// TODO Auto-generated method stub
		this.Lydochuyen=_lydochuyen;
	}
	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}
	@Override
	public void setNguoitao(String _nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao=_nguoitao;
	}
	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}
	@Override
	public void setNgaytao(String _ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=_ngaytao;
	}
	@Override
	public String getNguoisua() {
		// TODO Auto-generated method stub
		return this.Nguoisua;
	}
	@Override
	public void setNguoisua(String _nguoisua) {
		// TODO Auto-generated method stub
		this.Nguoisua=_nguoisua;
	}
	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.Ngaysua;
	}
	@Override
	public void setNgaysua(String _ngaysua) {
		// TODO Auto-generated method stub
		this.Ngaysua=_ngaysua;
	}
	@Override
	public void setmsg(String _msg) {
		// TODO Auto-generated method stub
		this.Msg=_msg;
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
		this.rs=_dmhlist;
	}
	
	@Override
	public ResultSet getRsck() {
		// TODO Auto-generated method stub
		return this.rsck;
	}
	@Override
	public void setRsck(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rsck=_dmhlist;
	}
	
	@Override
	public ResultSet getRsck1() {
		// TODO Auto-generated method stub
		return this.rsck1;
	}
	@Override
	public void setRsck1(ResultSet _dmhlist) {
		// TODO Auto-generated method stub
		this.rsck1=_dmhlist;
	}
	@Override
	public void init(String _search) {
		// TODO Auto-generated method stub
		String query = "";
		if (_search.length() > 0) {
			query = _search;
		} else {
			// TODO Auto-generated method stub
			query =" SELECT ISNULL(ck.TRANGTHAI,'') AS trangthai,ck.PK_SEQ as ma ,ck.NGAYCHUYENKHO as ngaychuyenkho, "+
			" k.tenkho as khonhan,k1.tenkho as khochuyen,ck.NGAYTAO as ngaytao ,ck.NGAYSUA as ngaysua, "+
			" NV.TEN as nguoitao,NV1.TEN as nguoisua,ck.lydo as lydo "+
			" FROM chuyenkho ck LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ" +
			" LEFT JOIN NHANVIEN NV ON CK.NGUOITAO=NV.PK_SEQ LEFT JOIN NHANVIEN NV1 ON CK.NGUOISUA=NV1.PK_SEQ ";
		
		}
		this.rs = db.get(query);
		String querykh = "select PK_SEQ,TENKHO from KHO";
		this.rsck = db.get(querykh);
		this.rsck1 = db.get(querykh);
		
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public boolean Delete_dh() {
		// TODO Auto-generated method stub
		try {
			if (this.Id.trim().length() > 0) {
				String query = " select cksp.SANPHAM_FK as sanpham,cksp.SOLUONG as soluong,ck.KHOCHUYEN as khochuyen"+
							   " from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "+
							   " where ck.PK_SEQ="+this.Id;
				ResultSet rs = db.get(query);
				while (rs.next()) {
					 this.Khochuyen = rs.getString("KHOCHUYEN");
					double Soluongchuyen=rs.getDouble("SOLUONG");
					String masp=rs.getString("SANPHAM");
					query = "UPDATE KHO_SANPHAM SET BOOKED="+'0' +",AVAILABLE= AVAILABLE+"+Soluongchuyen 
					+" WHERE KHO_FK=" + this.Khochuyen +"and SANPHAM_FK= "+masp ;
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
				}
				 query = "UPDATE CHUYENKHO SET TRANGTHAI='2' WHERE PK_SEQ=" + this.Id;
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			}
			return true;
			}catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean Chot_dh() {
		// TODO Auto-generated method stub
		try {
			if (this.Id.trim().length() > 0) {
				String query = " select cksp.SANPHAM_FK as sanpham,cksp.SOLUONG as soluong,ck.KHOCHUYEN as khochuyen,CK.KHONHAN AS KHONHAN"+
							   " from CHUYENKHO_SANPHAM cksp left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "+
							   " where ck.PK_SEQ="+this.Id;
				ResultSet rs = db.get(query);
				while (rs.next()) {
					this.Khonhan=rs.getString("khonhan");
					this.Khochuyen = rs.getString("KHOCHUYEN");
					double Soluongchuyen=rs.getDouble("SOLUONG");
					String masp=rs.getString("SANPHAM");
					query = "UPDATE KHO_SANPHAM SET BOOKED="+'0' +",SOLUONG= SOLUONG-"+Soluongchuyen 
					+" WHERE KHO_FK=" + this.Khochuyen +"and SANPHAM_FK= "+masp ;
					
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}
					
					String k="select count(*) from KHO_SANPHAM where KHO_FK= "+this.Khonhan +"and SANPHAM_FK="+masp;
					ResultSet rsj = this.db.get(k);
					
					if( rsj!= null){
						
						query = "UPDATE KHO_SANPHAM SET AVAILABLE=AVAILABLE+"+Soluongchuyen +",SOLUONG= SOLUONG+"+Soluongchuyen 
						+" WHERE KHO_FK=" + this.Khonhan +"and SANPHAM_FK= "+masp ;
						if (db.updateReturnInt(query) != 1) {
							db.getConnection().rollback();
							this.Msg = "Không thể cập nhật dòng lệnh: " + query;
							return false;
						}
					}
					else
					{
						query="INSERT INTO(KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE)" + 
						"VALUES('"
						+ this.Khonhan
						+ "',"
						+ masp
						+ ","
						+ Soluongchuyen
						+ ",0,"
						+  Soluongchuyen + ")";
						if (db.updateReturnInt(query) != 1) {
							db.getConnection().rollback();
							this.Msg = "Không thể cập nhật dòng lệnh: " + query;
							return false;
						}
					}
				}
			
				query = "UPDATE CHUYENKHO SET TRANGTHAI='1' WHERE PK_SEQ=" + this.Id;
					if (db.updateReturnInt(query) != 1) {
						db.getConnection().rollback();
						this.Msg = "Không thể cập nhật dòng lệnh: " + query;
						return false;
					}

					db.getConnection().commit();
					db.getConnection().setAutoCommit(true);
			}
			return true;
			
		} catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
}
	
	
	
	
	


