package geso.erp.beans.chuyenkho_thai.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_thai.IChuyenkho;
import geso.erp.db.sql.dbutils;

public class Chuyenkho extends Phan_Trang implements IChuyenkho {
 	String Ma;
	String Khochuyen;
	String Khonhan;
	String Trangthai;
	String UserID;
	ResultSet rs;
	ResultSet rskc;
	ResultSet rskn;
	ResultSet rstrangthai;
	String msg;
	String Nguoitao;
	String Ngaytao;
	String Nguoisua;
	String Ngaysua;
	String Id;
	String Lydochuyen;
	String Ngaychuyen;
	
	dbutils db= new dbutils();
	public Chuyenkho(){
		 Ma ="";
		 Khochuyen="";
		 Khonhan="";
		 Trangthai="";
		 this.UserID ="";
		 this.msg="";
		 Nguoitao="";
		 Ngaytao="";
		 Nguoisua="";
		 Ngaysua="";
		 Id="";
		 Lydochuyen="";
		 Ngaychuyen="";
		 
	}
	@Override
	public void setMa(String Ma) {
		// TODO Auto-generated method stub
		this.Ma=Ma;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setKhochuyen(String Khochuyen) {
		// TODO Auto-generated method stub
		this.Khochuyen=Khochuyen;
	}

	@Override
	public String getKhochuyen() {
		// TODO Auto-generated method stub
		return this.Khochuyen;
	}

	@Override
	public void setKhonhan(String Khonhan) {
		// TODO Auto-generated method stub
		this.Khonhan=Khonhan;
	}

	@Override
	public String getKhonhan() {
		// TODO Auto-generated method stub
		return this.Khonhan;
	}

	@Override
	public void setTrangthai(String Trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=Trangthai;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setUserID(String UserID) {
		// TODO Auto-generated method stub
		this.UserID=UserID;
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return this.UserID;
	}

	

	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public String getNguoitao() {
		// TODO Auto-generated method stub
		return this.Nguoitao;
	}

	@Override
	public void setNguoitao(String Nguoitao) {
		// TODO Auto-generated method stub
		this.Nguoitao=Nguoitao;
	}

	@Override
	public String getNgaytao() {
		// TODO Auto-generated method stub
		return this.Ngaytao;
	}

	@Override
	public void setNgaytao(String Ngaytao) {
		// TODO Auto-generated method stub
		this.Ngaytao=Ngaytao;
	}

	@Override
	public String getNguoisua() {
		// TODO Auto-generated method stub
		return this.Nguoisua;
	}

	@Override
	public void setNguoisua(String Nguoisua) {
		// TODO Auto-generated method stub
		this.Nguoisua=Nguoisua;
	}

	@Override
	public String getNgaysua() {
		// TODO Auto-generated method stub
		return this.Ngaysua;
	}

	@Override
	public void setNgaysua(String Ngaysua) {
		// TODO Auto-generated method stub
		this.Ngaysua=Ngaysua;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		try {
			String query = "select PK_SEQ, NGAYCHUYENKHO,LYDO,KHOCHUYEN,KHONHAN from CHUYENKHO where PK_SEQ="
					+ this.Id;
			ResultSet rs = db.get(query);
			if (rs.next()) {
				this.Ngaychuyen = rs.getString("NGAYCHUYENKHO");
				this.Lydochuyen = rs.getString("LYDO");
				this.Khochuyen = rs.getString("KHOCHUYEN");
				this.Khonhan = rs.getString("KHONHAN");
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
 	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query1 = "select PK_SEQ as Khochuyen, TENKHO from KHO";
		this.rskc=db.get(query1);
		String query2 = "select PK_SEQ as Khonhan, TENKHO from KHO";
		this.rskn=db.get(query2);
	}
	@Override
	public void setDataList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rs=rs;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rs;
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.Id=Id;
	}
	
	@Override
	public void setListKC(ResultSet rskc) {
		// TODO Auto-generated method stub
 
		this.rskc=rskc;
	}
	@Override
	public ResultSet getListKC() {
		// TODO Auto-generated method stub
		return this.rskc;
	}
	@Override
	public void setListKN(ResultSet rskn) {
		// TODO Auto-generated method stub
		this.rskn=rskn;
	}
	@Override
	public ResultSet getListKN() {
		// TODO Auto-generated method stub
		return this.rskn;
	}
	
	@Override
	public ResultSet rstrangthai() {
		// TODO Auto-generated method stub
		return this.rstrangthai;
	}
	@Override
	public boolean save() {
		try {
			db.getConnection().setAutoCommit(false);
			String query = "insert into CHUYENKHO(NGAYCHUYENKHO, LYDO,KHOCHUYEN,KHONHAN,TRANGTHAI, NGAYTAO)values"
					+ "('" + this.Ngaychuyen + "','"
					+ this.Lydochuyen + "','" + this.Khochuyen + "','" 
					+ this.Khonhan + "','0','"
					+this.getDateTime()+"')";
			if (!db.update(query)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh : " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			db.update("rollback");
			this.msg = "Lỗi : " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update() {
		try {
			db.getConnection().setAutoCommit(false);
			String  query="update CHUYENKHO"
				+" set NGAYCHUYENKHO='"+this.Ngaychuyen+"', LYDO=N'"
				+this.Lydochuyen+"', KHOCHUYEN='"+this.Khochuyen+"',NGUOITAO='"
				+this.UserID+"', NGUOISUA='"+this.UserID+"',NGAYSUA='"
				+this.getDateTime()+"'"
				+" where PK_SEQ='"+this.Id+"'";
			
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
	public String getNgaychuyen() {
		// TODO Auto-generated method stub
		return this.Ngaychuyen;
	}
	@Override
	public void setNgaychuyen(String Ngaychuyen) {
		// TODO Auto-generated method stub
		this.Ngaychuyen=Ngaychuyen;
	}
	@Override
	public String getLydochuyen() {
		// TODO Auto-generated method stub
		return this.Lydochuyen;
	}
	@Override
	public void setLydochuyen(String Lydochuyen) {
		// TODO Auto-generated method stub
		this.Lydochuyen=Lydochuyen;
	}
	public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date); 
 }

}
