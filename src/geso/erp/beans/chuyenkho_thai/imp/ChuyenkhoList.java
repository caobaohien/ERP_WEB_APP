package geso.erp.beans.chuyenkho_thai.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_thai.IChuyenkhoList;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoList extends Phan_Trang implements IChuyenkhoList{

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
	public ChuyenkhoList(){
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
		return null;
	}

	@Override
	public void setNgaysua(String Ngaysua) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="select CK.PK_SEQ as Ma, CK.LYDO as Lydochuyen, NV.TEN as Nguoitao,CK.NGAYTAO as Ngaytao, NV.TEN as Nguoisua, CK.NGAYSUA as Ngaysua,CK.TRANGTHAI as Trangthai"
			 + " from CHUYENKHO as CK left join KHO as K on CK.KHOCHUYEN=K.PK_SEQ and CK.KHONHAN=K.PK_SEQ"
			 + " left join NHANVIEN as NV on CK.NGUOITAO=NV.PK_SEQ and CK.NGUOISUA=NV.PK_SEQ"
			 + " WHERE 1=1";
		if(search.length()>0){
			query=search;
		}
		this.rs=db.get(query);
		CreateRs();
		
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query1 = "select PK_SEQ as Khochuyen, TENKHO from KHO";
		this.rskc=db.get(query1);
		String query2 = "select PK_SEQ as Khonhan, TENKHO from KHO";
		this.rskn=db.get(query2);
		String query3 = "select TRANGTHAI as Trangthai from CHUYENKHO";
		this.rstrangthai=db.get(query3);
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
		return null;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		
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
	public String getNgaychuyen() {
		// TODO Auto-generated method stub
		return this.Ngaychuyen;
	}
	@Override
	public void setNgaychuyen(String Ngaychuyen) {
		// TODO Auto-generated method stub
		this.Ngaychuyen=Ngaychuyen;
	}

}
