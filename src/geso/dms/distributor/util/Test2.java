package geso.dms.distributor.util;

import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test2() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession(false);
	    

	    if(session != null && !session.isNew()) {
	    	out.println(request.getRequestedSessionId());
	    } else {
	        out.println("expired");
	    }


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	
	private boolean UpdateTonDau(String khott_fk, int thang, int nam)
	{
		dbutils db = new dbutils();
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			//B1. Cap nhat BOOKED + AVAILABLE kho can cap nhat
			String query = "update ERP_KHOTT_SANPHAM set soluong = 0, booked = 0, available = 0 " +
							"where khott_fk = '" + khott_fk + "' and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("1.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_KHOTT_SP_CHITIET " +
					"where khott_fk = '" + khott_fk + "' and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("2.Loi cap nhat kho CT: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = " select sanpham_fk, tonkho, giaton, " +
					"  ( select count(*) as sodong from ERP_KHOTT_SANPHAM where sanpham_fk = temp.sanpham_fk and khott_fk = '" + khott_fk + "' ) as soDong " +
					" from TEMP_TONKHO temp " +
					" where khott_fk = '" + khott_fk + "' and sanpham_fk in ( select pk_seq from ERP_SANPHAM ) ";
			ResultSet rsSP = db.get(query);
			
			System.out.println("___DUA SO VAO: " + query);
			while(rsSP.next())
			{
				String sanpham_fk = rsSP.getString("sanpham_fk");
				String tonkho = rsSP.getString("tonkho");
				
				int sodong = rsSP.getInt("soDong");
				
				NumberFormat formatter = new DecimalFormat("#######.###");
				String giaton = formatter.format(rsSP.getDouble("giaton"));
				
				if(sodong > 0) //Da Co
				{
					query = " Update ERP_KHOTT_SANPHAM set soluong = '" + tonkho + "', booked = '0', available = '" + tonkho + "', " +
								"giaton = " + giaton + ", thanhtien =  " + giaton + " * " + tonkho +
							" where khott_fk = '" + khott_fk + "' and sanpham_fk = '" + sanpham_fk + "'";
				}
				else
				{
					query = " Insert ERP_KHOTT_SANPHAM(khott_fk, sanpham_fk, soluong, booked, available, giaton, thanhtien) " +
							" values('" + khott_fk + "', '" + sanpham_fk + "', '" + tonkho + "', '0', '" + tonkho + "', " + giaton + ", " + giaton + " * " + tonkho + ") ";
				}
				
				System.out.println("___QUERY KHO: " + query);
				if(!db.update(query))
				{
					System.out.println("3.Loi cap nhat kho: " + query);
					db.getConnection().rollback();
					return false;
				}
				
			}
			rsSP.close();
			
			
			//Chen KHO CHI TIET
			query = "insert ERP_KHOTT_SP_CHITIET(khott_fk, sanpham_fk, soluong, booked, available, solo, bin, ngaysanxuat, ngayhethan, ngaynhapkho) " +
					"select khott_fk, sanpham_fk, soluong, booked, available, ' ', '100000', '2013-01-01', '2016-01-01', '2013-04-01' " +
					"from ERP_KHOTT_SANPHAM where khott_fk in ('" + khott_fk + "')" +
							" and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("4.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			//CHEN TON KHO THANG
			query = "delete ERP_TONKHOTHANG where khott_fk = '" + khott_fk + "' and thang = '" + thang + "' and nam = '" + nam + "' " +
						"and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("5.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = "insert ERP_TONKHOTHANG(KHOTT_FK, SANPHAM_FK, GIATON, SOLUONG, THANHTIENTON, BOOKED, AVAILABLE, THANG, NAM) " +
					"select KHOTT_FK, SANPHAM_FK, GIATON, SOLUONG, THANHTIEN, BOOKED, AVAILABLE, '" + thang + "', '" + nam + "' " +
					"from ERP_KHOTT_SANPHAM " +
					"where KHOTT_FK = '" + khott_fk + "' and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("6.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			//CHEN TON KHO THANG CHI TIET
			query = "delete ERP_TONKHOTHANG_CHITIET where khott_fk = '" + khott_fk + "' and thang = '" + thang + "' and nam = '" + nam + "'" +
						" and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("7.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = "insert ERP_TONKHOTHANG_CHITIET(KHOTT_FK, BIN, SOLO, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, NGAYHETHAN, GIATON, THANHTIEN, THANG, NAM) " +
					"select KHOTT_FK, BIN, SOLO, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, NGAYHETHAN, 0, 0, '" + thang + "', '" + nam + "' " +
					"from ERP_KHOTT_SP_CHITIET  " +
					"where KHOTT_FK = '" + khott_fk + "' and sanpham_fk in ( select sanpham_fk from TEMP_TONKHO ) ";
			if(!db.update(query))
			{
				System.out.println("8.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete TEMP_TONKHO ";
			if(!db.update(query))
			{
				System.out.println("8.Loi cap nhat kho: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			System.out.println("OK..............");
			db.getConnection().commit();
		} 
		catch (Exception e) 
		{
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("___Exception: " + e.getMessage());
			
			return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] arg)
	{
		Test2 test2 = new Test2();
		test2.UpdateTonDau("100016", 7, 2013);
		
		/*dbutils db = new dbutils();
		
		String query = "select pk_seq, quycach, malon from TEMP_THANHPHAM";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				while(rs.next())
				{
					String pk_seq = rs.getString("pk_seq");
					String quycach = rs.getString("quycach");
					
					//System.out.println("--------------QC: " + quycach);
					
					String dai = "";
					String rong = "";
					String dinhluong = "";
					
					dai = quycach.substring(0, quycach.indexOf("MM")).trim();
					quycach = quycach.substring(quycach.indexOf("MM") + 2, quycach.length()).trim();
					
					//System.out.println("--------------Dai: " + dai + "  -- QC: " + quycach + " -- VI TRI: " + quycach.indexOf(" M"));
					
					rong = quycach.substring( quycach.indexOf("x") + 2, quycach.indexOf("M") ).trim();
					quycach = quycach.substring(quycach.indexOf(" M") + 2 , quycach.length()).trim();
					
					//System.out.println("--------------RONG: " + rong + "  -- QC: " + quycach);
					
					dinhluong = quycach.substring(quycach.indexOf("x ") + 2, quycach.indexOf(" G") ).trim();
					
					System.out.println("----QUY CACH: " + quycach + ": DAI - " + dai + " ; RONG - " + rong + " ; DINH LUONG - " + dinhluong);
					
					query = " update TEMP_THANHPHAM set rong = '" + dai + "', dai = '" + rong + "', dinhluong = '" + dinhluong + "' " +
							"where pk_seq = '" + pk_seq + "' ";
					db.update(query);
					
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("__Exception: " + e.getMessage());
			}
		}*/
		
		
	}
	

}
