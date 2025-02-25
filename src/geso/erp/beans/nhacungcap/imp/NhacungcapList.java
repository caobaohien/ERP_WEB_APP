package geso.erp.beans.nhacungcap.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.db.sql.dbutils;

public class NhacungcapList  extends Phan_Trang implements INhacungcapList {
	
	String UserId;
	String ShopId;
	String Trangthai;
	String Ma;
	String Ten;
	String Diachi;
	String Dienthoai;
	String Msg="";
	
	ResultSet rslist;
	dbutils db=new dbutils();
	
	public NhacungcapList(){
		this.Trangthai="";
		this.Ma="";
		this.Ten="";
		this.Diachi="";
		this.Dienthoai="";
		this.Msg="";
	}
	
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId=userId;
	}

	@Override
	public String getShopId() {
		// TODO Auto-generated method stub
		return this.getShopId();
	}

	@Override
	public void setShopId(String ShopId) {
		// TODO Auto-generated method stub
		this.ShopId=ShopId;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		
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
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String Ma) {
		// TODO Auto-generated method stub
		this.Ma="";
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		
	}

	 
	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rslist;
	}

	@Override
	public void setDataList(ResultSet rs) {
		// TODO Auto-generated method stub
			this.rslist=rs;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="";
		if(search.equals("")){
			query=      " SELECT  KH.PK_SEQ AS SOCHUNGTU, KH.MA   " +
					    " ,KH.NGAYTAO,ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO , ISNULL(KH.TRANGTHAI,'') AS TRANGTHAI  "+
						" FROM "+
						" NHACUNGCAP KH   "+
						" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=KH.NGUOITAO  ";
						
		} else {
			query=search;
		}
		String query_init = createSplittingData_List(50, 10, "sochungtu desc ", query);
		this.rslist = db.get(query_init);
		CreateRs();
	}

	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init_Kh() {
		// TODO Auto-generated method stub
		
	}
	
}
