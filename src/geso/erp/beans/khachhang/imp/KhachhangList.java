package geso.erp.beans.khachhang.imp;

import java.sql.ResultSet;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.db.sql.dbutils;
import geso.erp.util.Library;


public class KhachhangList extends Phan_Trang implements IKhachhangList 
{ 
	private static final long serialVersionUID = 1L;
	
	String UserId;
	String Tungay;
	String Denngay;
	String KhachhangId;
	String Trangthai;
	String Id="";
	String ShopId;
	String Msg;
	ResultSet rslist;
	String Ma;
	String Ten;
	String Dienthoai;
	String Diachi;
	
	String Manew;
	String Tennew;
	String Dienthoainew;
	String Diachinew;
	
	String Trangthainew="";
	private int num;
	private int[] listPages;
	private int currentPages;

	
	dbutils db=new dbutils();

	public KhachhangList(){
		  UserId="";
		  Tungay="";
		  Denngay="";
		  KhachhangId="";
		  this.Id="";
		  Trangthai="";
		  this.Ma="";
		  this.Ten="";
		  this.Trangthainew="";
		  this.Diachi="";
		  this.Dienthoai="";
		  this.Manew="";
		  this.Tennew="";
		  this.Diachinew="";
		  this.Dienthoainew="";
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
	public void init(String search) {
		// TODO Auto-generated method stub
		String query="";
		if(search.equals("")){
			query=      " SELECT  KH.PK_SEQ AS SOCHUNGTU,KH.DIENTHOAI,KH.MA ,ISNULL(KH.DIACHI,'') AS DIACHI " +
					    " ,KH.NGAYTAO,ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO , ISNULL(KH.TRANGTHAI,'') AS TRANGTHAI  "+
						" FROM "+
						" KHACHHANG KH   "+
						" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=KH.NGUOITAO where  " +
						" KH.shop_fk = (select shop_fk from nhanvien where pk_seq="+UserId+")";
		} else {
			query=search;
		}
		String query_init = createSplittingData_List(50, 10, "sochungtu desc ", query);
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
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}
	@Override
	public void setMa(String Ma) {
		// TODO Auto-generated method stub
		this.Ma=Ma;
	}
	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}
	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten=ten;
	}
	@Override
	public String getDienthoai() {
		// TODO Auto-generated method stub
		return this.Dienthoai;
	}
	@Override
	public void setDienthoai(String dt) {
		// TODO Auto-generated method stub
		this.Dienthoai=dt;
	}
	@Override
	public String getDiachi() {
		// TODO Auto-generated method stub
		return this.Dienthoai;
	}
	@Override
	public void setDiachi(String diachi) {
		// TODO Auto-generated method stub
		this.Diachi=diachi;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rslist;
	}
	@Override
	public void setDataList(ResultSet datalist) {
		// TODO Auto-generated method stub
		this.rslist=datalist;
	}
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try{

			
			if(this.Manew.trim().equals("")){
				this.Msg="Vui lòng nhập mã khách hàng";
				return false;
			}
			if(this.Tennew.trim().equals("")){
				this.Msg="Vui lòng nhập tên khách hàng";
				return false;
			}
			
			if(this.Id.length() >0){
				
				String query="  update khachhang set MA='"+this.Manew+"',ten=N'"+this.Tennew+"',diachi=N'"+this.Diachinew+"',dienthoai='"+this.Dienthoainew+"',trangthai='"+this.Trangthainew+"' where pk_seq= " +this.Id;
				 
				if(!db.update(query)){
					this.Msg="Không thể cập nhật khách hàng: "+query;
					return false;
				}
				
			}else{
			String query="SELECT pk_seq FROM KHACHHANG WHERE SHOP_FK= (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+") AND  MA='"+this.Manew+"'";
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.Msg="Mã khách hàng đã tồn tại,vui lòng tìm kiếm để chỉnh sửa";
			}
			
			  query=" INSERT INTO KHACHHANG (MA,TEN,DIACHI,DIENTHOAI,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA,TRANGTHAI,SHOP_FK)  " +
					" SELECT '"+this.Manew+"',N'"+this.Tennew+"',N'"+this.Diachinew+"','"+this.Dienthoainew+"', " +
					" "+this.UserId+",getdate(),"+this.UserId+",getdate(),1, (SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+this.UserId+") ";
			  
			    if(!db.update(query)){
					this.Msg="Không thể cập nhật khách hàng: "+query;
					return false;
				}
			    
			    query = "select SCOPE_IDENTITY() as khid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next())
				{
					this.Id = rsDmh.getString("khid");
					
					
				}
				rsDmh.close();
				
				
			}
					
			String query="UPDATE KHACHHANG SET TIMKIEM=dbo.ftBoDau(MA)+'-'+dbo.ftBoDau(MA)+'-'+dbo.ftBoDau(DIENTHOAI)+'-'+dbo.ftBoDau(DIACHI) WHERE PK_SEQ= "+this.Id;
			if(!db.update(query)){
				this.Msg="Không thể cập nhật khách hàng: "+query;
				return false;
			}
			    return true;
		}catch(Exception er){
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
	@Override
	public String getMaNew() {
		// TODO Auto-generated method stub
		return this.Manew;
	}
	@Override
	public void setMaNew(String Ma) {
		// TODO Auto-generated method stub
		this.Manew=Ma;
	}
	@Override
	public String getTenNew() {
		// TODO Auto-generated method stub
		return this.Tennew;
	}
	@Override
	public void setTenNew(String ten) {
		// TODO Auto-generated method stub
		this.Tennew=ten;
	}
	@Override
	public String getDienthoaiNew() {
		// TODO Auto-generated method stub
		return this.Dienthoainew;
	}
	@Override
	public void setDienthoaiNew(String dt) {
		// TODO Auto-generated method stub
		this.Dienthoainew=dt;
	}
	@Override
	public String getDiachiNew() {
		// TODO Auto-generated method stub
		return this.Diachinew;
	}
	@Override
	public void setDiachiNew(String diachi) {
		// TODO Auto-generated method stub
		this.Diachinew=diachi;
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
	public void init_Kh() {
		// TODO Auto-generated method stub
		try{
			String query="SELECT PK_SEQ,MA,TEN,DIACHI,DIENTHOAI, TRANGTHAI FROM KHACHHANG WHERE PK_SEQ="+Id;
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.Tennew=rs.getString("ten");
				this.Manew=rs.getString("ma");
				this.Diachinew=rs.getString("diachi");
				this.Dienthoainew=rs.getString("dienthoai");
				this.Trangthainew=rs.getString("trangthai");
				
				
			}
			rs.close();
		}catch(Exception er){
			er.printStackTrace();
		}
	}
	@Override
	public String getTrangthaiNew() {
		// TODO Auto-generated method stub
		return this.Trangthainew;
	}
	@Override
	public void setTrangthaiNew(String trangthainew) {
		// TODO Auto-generated method stub
		this.Trangthainew=trangthainew;
	}

	}
