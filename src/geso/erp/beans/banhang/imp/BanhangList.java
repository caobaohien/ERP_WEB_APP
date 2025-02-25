package geso.erp.beans.banhang.imp;

import java.sql.ResultSet;

import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.banhang.IBanhangList; 
import geso.erp.beans.doctien.DocTien;
import geso.erp.db.sql.dbutils;
import geso.erp.util.Library;


public class BanhangList extends Phan_Trang implements IBanhangList 
{ 
	private static final long serialVersionUID = 1L;
	
	String UserId;
	String Tungay;
	String Denngay;
	String KhachhangId;
	String KhachhangTen;
	String Trangthai;
	String Sochungtu;
	String ShopId;
	String Msg;
	ResultSet rslist;
	ResultSet rskh;
	private int num;
	private int[] listPages;
	private int currentPages;

	
	dbutils db=new dbutils();

	public BanhangList(){
		  UserId="";
		  Tungay="";
		  Denngay="";
		  KhachhangId="";
		  KhachhangTen="";
		  Trangthai="";
		  Sochungtu="";
		  Msg="";
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
	public String getTungay() {
		// TODO Auto-generated method stub
		return this.Tungay;
	}

	@Override
	public void setTungay(String tungay) {
		// TODO Auto-generated method stub
		this.Tungay=tungay;
	}

	@Override
	public String getDenngay() {
		// TODO Auto-generated method stub
		return this.Denngay;
	}

	@Override
	public void setDenngay(String denngay) {
		// TODO Auto-generated method stub
		this.Denngay=denngay;
	}

	@Override
	public String getTrangthai() {
		// TODO Auto-generated method stub
		return this.Trangthai;
	}

	@Override
	public void setTrangthai(String trangthai) {
		// TODO Auto-generated method stub
		this.Trangthai=trangthai;
	}

	@Override
	public String getKh() {
		// TODO Auto-generated method stub
		return this.KhachhangTen;
	}

	@Override
	public void setKh(String Kh) {
		// TODO Auto-generated method stub
		this.KhachhangTen=Kh;
	}

	@Override
	public void setmsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg=msg;
	}

	@Override
	public String getmsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public ResultSet getDmhList() {
		// TODO Auto-generated method stub
		return this.rslist;
	}

	@Override
	public void setDmhList(ResultSet dmhlist) {
		// TODO Auto-generated method stub
		this.rslist=dmhlist;
	}

	@Override
	public String getSochungtu() {
		// TODO Auto-generated method stub
		return this.Sochungtu;
	}

	@Override
	public void setSochungtu(String sodonmuahang) {
		// TODO Auto-generated method stub
		this.Sochungtu=sodonmuahang;
	}

	@Override
	public ResultSet getKhRs() {
		// TODO Auto-generated method stub
		return this.rskh;
	}

	@Override
	public void setKhRs(ResultSet rs) {
		// TODO Auto-generated method stub
		rskh=rs;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="";
		if(search.equals("")){
		  query=  		" SELECT DH.PK_SEQ AS SOCHUNGTU,DH.NGAYCHUNGTU,DH.TRANGTHAI,DH.NGAYTAO, " +
						" TONGTIENtruocVAT as tongtien, ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO "+
						" FROM DONHANG DH "+
						" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=DH.KHACHHANG_FK "+
						" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=DH.NGUOITAO  " +
						" where DH.SHOP_FK= (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+")";
		  			
		} else {
			query=search;
		}
		String query_init = createSplittingData_List(50, 10, "NGAYCHUNGTU desc, SOCHUNGTU desc", query);
		this.rslist = db.get(query_init);
		CreateRs();
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num = num;
		listPages = PhanTrang.getListPages(num);

	}

	
	public int getCurrentPage() {
		return this.currentPages;
	}

	
	public void setCurrentPage(int current) {
		this.currentPages = current;
	}

	
	public int[] getListPages() {
		return this.listPages;
	}

	
	public void setListPages(int[] listPages) {
		this.listPages = listPages;
	}

	
	public int getLastPage() {
		ResultSet rs = db.get("select count(*) as c from DONHANG");
		return PhanTrang.getLastPage(rs);
	}

	
	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage) {
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}
	@Override
	public void CreateRs() {
		this.ShopId= Library.GetShopId(this.UserId, db);
		// TODO Auto-generated method stub
		/*String query="SELECT PK_SEQ,MA+ '- '+ TEN as ten FROM KHACHHANG where SHOP_FK="+this.ShopId;
		this.rskh=db.get(query);*/
	}
	@Override
	public String getShopId() {
		// TODO Auto-generated method stub
		return this.ShopId;
	}
	@Override
	public void setShopId(String ShopId) {
		// TODO Auto-generated method stub
		this.ShopId=ShopId;
	}

	}
