package geso.dms.distributor.util;

import geso.erp.db.sql.dbutils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.lang.Math;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utility implements Serializable
{
	private static final long serialVersionUID = 1L;
	String nppId="";
	String nppTen="";
	String sitecode="";
	String dangnhap="";
	private String khoSAP;
	
	public String getIdNhapp(String userId, dbutils db) 
	{
		String sql="select nv.dangnhap, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode where nv.pk_seq='"+userId+"' and nv.trangthai='1'";
		System.out.println("Get Thong Tin NPP :"+sql);
		ResultSet rs= db.get(sql);
		try{
			if(rs.next()){
			 this.nppId=rs.getString("pk_seq");
			 this.nppTen= rs.getString("ten");
			 this.sitecode=rs.getString("sitecode");
			 this.dangnhap = rs.getString("dangnhap");
			 this.setKhoSAP(rs.getString("khosap"));
			 rs.close();
			}
		}catch(Exception er){
			
		}
		return this.nppId;
	}
	public String getIdNhapp(String userId) 
	{
		dbutils db = new dbutils();
		String sql = "select nv.dangnhap, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode where nv.pk_seq='"+userId+"' and nv.trangthai='1'";
		System.out.println(sql);
		ResultSet rs = db.get(sql);
		try
		{
			if(rs.next())
			{
				 this.nppId=rs.getString("pk_seq");
				 this.nppTen= rs.getString("ten");
				 this.sitecode=rs.getString("sitecode");
				 this.dangnhap = rs.getString("dangnhap");
				 this.setKhoSAP(rs.getString("khosap"));
				 rs.close();
			}
		}
		catch(Exception er){}
		db.shutDown();
		return this.nppId;
	}
	public String getTenNhaPP(){
		return this.nppTen;
	}
	public String getSitecode(){
		return this.sitecode;
	}
	
	public String getDangNhap(){
		return this.dangnhap;
	}
	
	public boolean isValidDate(String inDate) {

	    if (inDate == null)
	      return false;

	    //set the format to use as a constructor argument
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	    
	    if (inDate.trim().length() != dateFormat.toPattern().length())
	      return false;

	    dateFormat.setLenient(false);
	    
	    try {
	      //parse the inDate parameter
	      dateFormat.parse(inDate.trim());
	    }
	    catch (ParseException pe) {
	      return false;
	    }
	    return true;
	  }
	
	public String getUserId(String querystring){
	    String userId;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[0];
	    	}else{
	    		tmp = querystring;
	    	}
	    	userId = tmp.split("=")[1];
		}else{
			userId = "";
		}
	    return userId;
	}
	public String getAction(String querystring){
	    String action;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[1];
	    		action = tmp.split("=")[0];
	    	}else{
	    		action = "";
	    	}
		}else{
			action = "";
		}
	    return action;
		
	}

	public String getId(String querystring){
	    String id;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[1];
	    		id = tmp.split("=")[1];
	    	}else{
	    		id = "";
	    	}
		}else{
			id = "";
		}
	    return id;
		
	}

	public String getCat(String querystring, int vitriva, int vitribang){
		String id;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[vitriva];
	    		id = tmp.split("=")[vitribang];
	    	}else{
	    		id = "";
	    	}
		}else{
			id = "";
		}
	    return id;
		
	}
	
	public Hashtable<Integer, String>  ArraystringToHashtable(String[] s){
		Hashtable<Integer, String> h = new Hashtable<Integer, String>();
		if(s != null){
			int size = s.length;
			int m = 0;
			while(m < size){
				h.put(new Integer(m), s[m]) ;
				m++;
			}
		}else{
			h.put(new Integer(0), "null");
		}
		return h;
	}

	public String[]  ResultSetToArrayString(ResultSet rs){
		String[] s = new String[10];
		try{
			int m = rs.getFetchSize();
			s = new String[m+1];		 	
			while(rs.next()){
				s[1] = rs.getString(1);
			}
		}catch(java.sql.SQLException e){}
		return s;
	}

	// tra ve nhung thanh phan cua s1 khong nam trong s2
	public String[] compareArrayString(String[] s1, String[] s2)
	{
		int i = s1.length;
		int j = s2.length;	
		
		String[] s = new String[i];
		int k = 0;
		for (int m = 0; m < i; m++){
			boolean result = true;
			for (int n = 0; n < j; n++){
				if (s1[m].equals(s2[n])){
					result = false;
				}
				if (result){
					s[k++]=s1[m];
				}
			}
		}
		return s;
	}
	
	public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
        Date date = new Date();
        return dateFormat.format(date);	
	}


	public	boolean isNumeric(String input){ 
		boolean result = true;
		char[] all = input.toCharArray();
		
		for(int i = 0; i < all.length;i++) {
		   if(!(Character.isDigit(all[i]))) {
			   result = false;
		   }
		}
		return result;
	}
	/*
	public String replaceAEIOU(String s)
	{
		String result = s;
		boolean flag = false;
		for(int i=0; i < result.length(); i++){
			if (!result.substring(i, i+1).contains("a")&!result.substring(i, i+1).contains("e")&!result.substring(i, i+1).contains("i")&!result.substring(i, i+1).contains("o")&!result.substring(i, i+1).contains("u")){
				flag = true;
			    break;
			}
		}
		
		if(flag){
			
			for(int i=0; i < result.length(); i++){
		
				//result= result.replace("d", "_");
				result= result.replace("a", "_");
				result= result.replace("e", "_");
				result= result.replace("i", "_");
				result= result.replace("o", "_");
				result= result.replace("u", "_");
			}
		}
		return result;
	}
	*/
	
	public boolean checkHopLe(String userId)
	{
		dbutils db = new dbutils();
		String query = "select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode  where nv.pk_seq = '" + userId + "'";
		ResultSet rs = db.get(query);
		String nppId = "";
		int dakhoaso30 = 0;
		int dacodctk01 = 0;
		try 
		{
			if(rs.next())
			{
				nppId = rs.getString("pk_seq");
				rs.close();
			}
			query = "select count(*) as dakhoaso from khoasongay where ngayks = '2012-04-30' and npp_fk = '" + nppId + "'";
			rs = db.get(query);
			
			if(rs.next())
			{
				dakhoaso30 = rs.getInt("dakhoaso");
				rs.close();
			}
			
			if(dakhoaso30 == 0)  //chua khoa so ngay nay
				return true;
			
			query = "select count(npp_fk) as sodong from dieuchinhtonkho where npp_fk = '" + nppId + "' and trangthai = '1' and ngaydc = '2012-05-01'";
			rs = db.get(query);
			
			if(rs.next())
			{
				dacodctk01 = rs.getInt("sodong");
				rs.close();
			}
			
			if(dacodctk01 == 0)
				return false;
				
		} 
		catch (SQLException e) { return false; }
		return true;
	}
	
	public String ngaykhoaso(String nhaphanphoi)
	{   
		String ngay = "";
		dbutils db = new dbutils();
		String sql ="select isnull(max(ngayks), '') as ngay from khoasongay where npp_fk ='"+ nhaphanphoi+"'";
		ResultSet rs = db.get(sql);
		try
		{
			if(rs != null)
			{
		        rs.next();
				ngay = rs.getString("ngay");	
			}
			db.shutDown();
		}
		catch(Exception e){ db.shutDown(); }
		
		return ngay;
	}
	public void initSanPhamSearch(HttpServletRequest request,String npp)
    {
		dbutils db = new dbutils();
		HttpSession session = request.getSession();

		String query = "";
		query = "select DISTINCT a.ma, a.ten, c.giabanlenpp as dongia, isnull(b.donvi, 'Chua xac dinh') as donvi from ERP_SanPham a left join donvidoluong b on a.dvdl_fk = b.pk_seq ";
		query = query + "inner join bgbanlenpp_sanpham c on a.pk_seq = c.sanpham_fk inner join nhapp_kho d on d.npp_fk= '"+ npp +"' and d.sanpham_fk=a.pk_seq and d.available > 0 ";
		query = query + "where c.bgbanlenpp_fk in (select distinct pk_seq from banggiabanlenpp where npp_fk = '"+ npp +"' ) and c.giabanlenpp > '0' and a.trangthai='1' ";
		
		ResultSet spList = db.get(query);
		
		String list = "";
		//List<ISanpham> list = new ArrayList<ISanpham>();
		if(spList != null)
		{		
			try 
			{
				while(spList.next())
				{
					//dung ky tu dac biet, tranh truong hop trong ten sp co nhung ky tu do
					list += spList.getString("ma") + "&&" + spList.getString("ten") + "&&" + spList.getString("donvi") + "&&" + spList.getString("dongia") + "\n";
				}
				spList.close();
			} 
			catch (SQLException e) {}	
		}
		if(list.length() > 0)
		{
			list = list.substring(0, list.length() - 2);
			session.setAttribute("ListSP", list);
		}
			session.setAttribute("bgstId", "0");
		
		//neu nhapp co kenh sieu thi
		query = "select pk_seq from banggiasieuthi where npp_fk = '"+ npp +"'";
		
		//System.out.println("Kenh sieu thi la: " + query + "\n");
		
		ResultSet rsBgst = db.get(query);
		//List<ISanpham> listBgst = null;
		
		String listBgst = "";
		if(rsBgst != null)
		{
			try 
			{
				while(rsBgst.next())
				{
					//listBgst = new ArrayList<ISanpham>();
					//Khoi gan lai bgst moi
					listBgst = "";
					
					query = "select DISTINCT b.ma, b.ten, a.GIASIEUTHI as dongia, isnull(c.donvi, 'Chua xac dinh') as donvi from BANGGIAST_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ left join donvidoluong c on b.DVDL_FK = c.pk_seq inner join nhapp_kho d on d.npp_fk= '"+ npp +"' and d.sanpham_fk=b.pk_seq and d.available > 0 ";
					query += " where a.BGST_FK = '" + rsBgst.getString("pk_seq") + "' and a.GIASIEUTHI > '0' and b.trangthai = '1'";
					
					//System.out.print("\nQuery cua ban La: " + query + "\n");
					
					ResultSet rsSt = db.get(query);
					if(rsSt != null)
					{		
						//ISanpham sanpham = null;
						//String[] param = new String[8];
						try 
						{
							while(rsSt.next())
							{
								/*
							    param[0] = "";
								param[1] = rsSt.getString("ma");
								param[2] = rsSt.getString("ten");
								param[3] = "";
								param[4] = rsSt.getString("donvi");
								param[5] = rsSt.getString("dongia");
								param[6] = "";
								param[7] = "";
								
								sanpham = new Sanpham(param);
								listBgst.add(sanpham);
								*/
								listBgst += rsSt.getString("ma") + "&&" + rsSt.getString("ten") + "&&" + rsSt.getString("donvi") + "&&" + rsSt.getString("dongia") + "\n";
							}
							rsSt.close();
						} 
						catch (SQLException e) {}	
					}
					//System.out.println("List san pham la: " + listBgst + "\n");
					if(listBgst.length() > 0)
					{
						listBgst = listBgst.substring(0, listBgst.length() - 2);
						session.setAttribute("bgst" + rsBgst.getString("pk_seq"), listBgst);
					}
				}
				rsBgst.close();
			} 
			catch (SQLException e) {}
		}
   }
	
	public void setKhoSAP(String khoSAP) {
		this.khoSAP = khoSAP;
	}
	public String getKhoSAP() {
		return khoSAP;
	}
	
	//chuyen tieng viet khong dau
	private static char[] SPECIAL_CHARACTERS = { ' ', '!', '"', '#', '$', '%',
		   '*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
		   '`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
		   'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
		   'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
		   'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
		   'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
		   'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
		   'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
		   'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
		   'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
		   'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };
		 
		 private static char[] REPLACEMENTS = { '-', '\0', '\0', '\0', '\0', '\0',
		   '\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
		   '\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
		   'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
		   'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
		   'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
		   'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
		   'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
		   'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
		   'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
		   'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
		   'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
		   'U', 'u', };
		 
		 public String replaceAEIOU(String s) 
		 {
			  int maxLength = Math.min(s.length(), 236);
			  char[] buffer = new char[maxLength];
			  int n = 0;
			  for (int i = 0; i < maxLength; i++) 
			  {
				  char ch = s.charAt(i);
				  int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
				  if (index >= 0) 
				  {
					  buffer[n] = REPLACEMENTS[index];
				  } 
				  else 
				  {
					  buffer[n] = ch;
				  }
				   // skip not printable characters
				   if (buffer[n] > 31) {
				    n++;
				   }
			  }
			  
			  // skip trailing slashes
			  while (n > 0 && buffer[n - 1] == '/') 
			  {
				  n--;
			  }
			  return String.valueOf(buffer, 0, n);
		 }
		 
		 public static void main(String[] arg)
		 {
			 Utility util = new Utility();
			 String str = "chị ";
			 System.out.println("Chuoi bo dau: " + util.replaceAEIOU(str));
		 }
		 
	public String antiSQLInspection(String param){
		String tmp = param;
		//System.out.println("Chuoi moi:" + tmp);
		
		String[] keywords = {" or ","delete","insert","update","create", "alter","drop","=","--", "select","\\(","\\)"};

		boolean trbl = false;
		
		if(tmp != null){
			tmp = tmp.toLowerCase();
			for (int i = 0; i < keywords.length; i++){
				if(tmp.contains(keywords[i])){
					tmp = tmp.replaceAll(keywords[i], "--");
					trbl = true;
					break;
				}
				//System.out.println("Chuoi moi:" + tmp);
			}
			
		}
		
		//System.out.println("Chuoi moi:" + tmp);
		if(trbl == true)
			return tmp;
		else return param;
	}
	
	public boolean daKhoaSoNgay30(String nppId)
	{
		dbutils db1 = new dbutils();
		String query = "select count(*) as dakhoaso from khoasongay where ngayks >= '2012-04-30' and npp_fk = '" + nppId + "'";
		
		System.out.println("Cau lenh check: " + query);
		ResultSet rs = db1.get(query);
		
		int dakhoaso30 = 0;
		try
		{
			if(rs.next())
			{
				dakhoaso30 = rs.getInt("dakhoaso");
				rs.close();
			}
			
			if(dakhoaso30 > 0)  //chua khoa so ngay nay
			{
				query = "select count(*) as sodong from nppdaduyet where npp_fk = '" + nppId + "'";
				System.out.println("Query check :" + query);
				rs = db1.get(query);
				int daduyet = 0;
				if(rs.next())
				{
					daduyet = rs.getInt("sodong");
					rs.close();
				}
				if(daduyet > 0) //da duyet roi thi mo chuc nang ban hang
					return false;
				return true;
			}
			db1.shutDown();
		} 
		catch (SQLException e) 
		{ 
			db1.shutDown(); 
			return false; 
		}
		return false;
	}
	
	public String getPhongBan(String nhanvien){
		
		String query="select DONVITHUCHIEN_FK  from NHANVIEN_DONVITHUCHIEN WHERE NHANVIEN_FK='"+nhanvien+"'";  
		dbutils db = new  dbutils();
		
		System.out.println(query);
		String pb="";
		
		ResultSet rscheck= db.get(query);
		if(rscheck!=null){

			try 
			{
				while(rscheck.next())
				{
					pb=rscheck.getString("DONVITHUCHIEN_FK");
				}
				rscheck.close();
			} 
			catch 
			(Exception e) 
			{
				System.out.println(e.toString());
			}
		
		}
		
		return pb;
		
	}
	
	public boolean checkDaduyet(String userId)
	{
		dbutils db = new dbutils();
		String query = "select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode  where nv.pk_seq = '" + userId + "'";
		ResultSet rs = db.get(query);
		String nppId = "";
		try 
		{
			if(rs.next())
			{
				nppId = rs.getString("pk_seq");
				rs.close();
			}
			
			query = "select count(*) as sodong from nppdaduyet where npp_fk = '" + nppId + "'";
			rs = db.get(query);
			int daduyet = 0;
			if(rs.next())
			{
				daduyet = rs.getInt("sodong");
				rs.close();
			}
			if(daduyet == 0)
				return false;
				
		} 
		catch (SQLException e) { return false; }
		return true;
	}
}
