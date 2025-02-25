package geso.dms.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import geso.erp.db.sql.dbutils;
import javax.servlet.http.HttpSession;

public class Utility implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nppId;
	String nppTen;
	String sitecode;
	
	public String getIdNhapp(String userid,dbutils db){
		String sql="select npp.pk_seq,npp.sitecode,npp.ten from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode where nv.pk_seq='"+userid+"' and nv.trangthai='1'";
		 
		ResultSet rs= db.get(sql);
		try{
			if(rs.next()){
			 this.nppId=rs.getString("pk_seq");
			 this.nppTen="Dang Test Npp:"+rs.getString("ten");
			 this.sitecode=rs.getString("sitecode");
			 System.out.println(sql);
			 System.out.println(this.nppTen);
			 rs.close();
			}
		}catch(Exception er){
			
		}
		db.shutDown();
		return this.nppId;
	}
	public String getTenNhaPP(){
		return this.nppTen;
	}
	public String getSitecode(){
		return this.sitecode;
	}
	public String ValidateParam(String param){		
		String result;
		if (param == null){
			result="";
		}else{
			if (param.indexOf("=") > 0){
				result = "";
			}else{
				result = param;
			}
		}
		return result;
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
	    	if(userId.contains(";"))
	    		userId = userId.split(";")[0];
		}else{
			userId = "";
		}
	    return userId;
	}

	
	public String CapNhatTaiKhoan(String taikhoanktNo,String taikhoanktCo,String tiente_fk,double giatri,geso.erp.db.sql.dbutils db,String thang,String nam)
	{
		try
		{
			String	query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanktNo + "' and nguyente_fk = '" + tiente_fk + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			
			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			if(rsTKNo.next())
			{
				sodong = rsTKNo.getInt("sodong");
			}
			rsTKNo.close();
			
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + giatri+ ", " +
													" GIATRINGUYENTE = GIATRINGUYENTE + "  +giatri + 
						" where taikhoankt_fk = '" + taikhoanktNo + "' and nguyente_fk = '" + tiente_fk + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, THANG, NAM) " +
						"values('" + taikhoanktNo + "', '0', " +giatri+ ", '" + tiente_fk + "', " +giatri+ ", '" + thang + "', '" + nam + "')";
			}
			
			//System.out.println("5.Cap nhat: + query");
			if(!db.update(query))
			{
				
				db.update("rollback");
				return "6.Không thể cập nhật ERP_TAIKHOAN_NOCO: " + query;
				
				
			}
			

			//Tai khoan co
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO  " +
					"where taikhoankt_fk = '" + taikhoanktCo + "' and nguyente_fk = '" + tiente_fk + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			
			rsTKNo = db.get(query);
			
			sodong = 0;
			if(rsTKNo.next())
			{
				sodong = rsTKNo.getInt("sodong");
			}
			rsTKNo.close();
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " +giatri+ ", " +
												" GIATRINGUYENTE = GIATRINGUYENTE + "  + giatri + 
						" where taikhoankt_fk = '" + taikhoanktCo + "' and nguyente_fk = '" + tiente_fk + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, THANG, NAM) " +
						"select '" + taikhoanktCo + "', " + giatri + ", '0', '" + tiente_fk + "', " + giatri + ", '" + thang + "', '" + nam + "' ";
			}
			
			if(!db.update(query))
			{
				
				db.update("rollback");
				return "6.Không thể cập nhật ERP_TAIKHOAN_NOCO: " + query;
				
			
			}
		}
		catch(Exception err)
		{
			db.update("rollback");
			return err.toString();
		}
		
		return "";
	}
	
	
	public String Update_TaiKhoan(dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc)
	{
		
		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}
		
		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}
		
		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}
		
		
		String msg = "";
		
		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;
		
		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;
		
		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();
		
		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();
		
		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();
		
		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();
		
		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();
				
		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;
		
		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;
		
		
		//GHI CO
		if(Float.parseFloat(_CO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' " +
					"and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = " update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
						" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
						" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = " insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						" select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
									" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		//GHI NO
		if(Float.parseFloat(_NO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
												" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
												" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
									" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		return msg;
		
	}
	
	public String Update_TaiKhoan_Diengiai (dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc,String Diengiai)
	{
		
		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}
		
		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}
		
		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}
		
		
		String msg = "";
		
		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;
		
		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;
		
		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();
		
		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();
		
		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();
		
		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();
		
		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();
				
		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;
		
		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;
		
		
		//GHI CO
		if(Float.parseFloat(_CO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' " +
					"and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = " update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
						" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
						" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = " insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						" select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC,DIENGIAI) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
									" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "',N'"+Diengiai+"' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		//GHI NO
		if(Float.parseFloat(_NO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
												" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
												" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC,diengiai) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
									" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "',N'"+Diengiai+"' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		return msg;
		
	}



	public String Update_TaiKhoan_TheoSoHieu(dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_SoHieu, String taikhoanCO_SoHieu, String NOIDUNGNHAPXUAT_FK, String NO, String CO, String DOITUONG,  
								String MADOITUONG, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc)
	{
		
	
		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}
		
		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}
		
		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}
		
		
		String msg = "";
		
		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;
		
		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;
		
		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();
		
		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();
		
		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();
		
		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();
		
		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();
		
		
		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;
		
		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;
	
		
		//GHI CO
		if(Float.parseFloat(_CO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "' ) and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			
			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
										" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
										" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "' ) and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						"select pk_seq, " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' " +
						"from ERP_TAIKHOANKT " +
						"where SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "' ";
			}
			
			System.out.println("1.Cap nhat tai khoan CO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO ( MOT SO TRUONG HOP SE KHONG CO DOI UNG... )
			if(taikhoanNO_SoHieu.trim().length() > 0)
			{
				query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
						"select '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", a.pk_seq, b.pk_seq, " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
									" N'" + DOITUONG + "', N'" + MADOITUONG + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "'" +
						"from ERP_TAIKHOANKT a, ERP_TAIKHOANKT b  " +
						"where a.SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "' and b.SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' ";
			}
			else
			{
				query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
										 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
						"select '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", a.pk_seq, null, " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
						" N'" + DOITUONG + "', N'" + MADOITUONG + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "'" +
						"from ERP_TAIKHOANKT a" +
						"where a.SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "'  ";
			}
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
		
		}
		
		//GHI NO
		if(Float.parseFloat(_NO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' ) and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			
			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
												" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
												" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' ) and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						"select pk_seq, '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "' " +
						"from ERP_TAIKHOANKT " +
						"where SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' ";
			}
			
			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO ( MOT SO TRUONG HOP SE KHONG CO DOI UNG... )
			if(taikhoanCO_SoHieu.trim().length() > 0)
			{
				query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
						"select '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", a.pk_seq, b.pk_seq, " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
									" N'" + DOITUONG + "', N'" + MADOITUONG + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "' " +
						"from ERP_TAIKHOANKT a, ERP_TAIKHOANKT b " +
						"where a.SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' and b.SOHIEUTAIKHOAN = '" + taikhoanCO_SoHieu + "' ";
			}
			else
			{
				query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
										 		"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC) " +
						"select '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", a.pk_seq, null, " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
									" N'" + DOITUONG + "', N'" + MADOITUONG + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "' " +
						"from ERP_TAIKHOANKT a " +
						"where a.SOHIEUTAIKHOAN = '" + taikhoanNO_SoHieu + "' ";
			}
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
		
		}
		
		return msg;
	
	}
	
	public String Update_TaiKhoan_DNTT (dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc,
			String Diengiai, String Sohoadon, String Ngayhoadon)
	{
		
		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}
		
		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}
		
		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}
		
		
		String msg = "";
		
		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;
		
		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;
		
		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();
		
		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();
		
		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();
		
		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();
		
		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();
				
		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;
		
		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;
		
		
		//GHI CO
		if(Float.parseFloat(_CO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' " +
					"and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = " update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
						" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
						" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = " insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						" select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, DIENGIAI, SOHOADON, NGAYHOADON) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
									" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", "+
									" N'" + khoanmuc + "',N'"+Diengiai+"', N'"+ Sohoadon +"', '"+ Ngayhoadon +"' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		//GHI NO
		if(Float.parseFloat(_NO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
					"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
					
			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }
			
			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
												" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
												" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
						" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
						"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "' ";
			}
			
			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
			
			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
													 "  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, DIENGIAI, SOHOADON, NGAYHOADON) " +
					"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
									" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+
									"N'"+Diengiai+"', N'"+ Sohoadon +"', '"+ Ngayhoadon +"' ) ";
			
			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}
			
		}
		
		return msg;
		
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
	
	public String getTrangThai(String querystring){
	    String trangthai;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[2];
	    		trangthai = tmp.split("=")[0];
	    	}else{
	    		trangthai = "";
	    	}
		}else{
			trangthai = "";
		}
	    return trangthai;
		
	}
	
	public String getNgayHetHan(String querystring){
	    String ngayhethan;
	    String tmp;
		if (querystring != null){
	    	if (querystring.contains("&")){
	    		tmp = querystring.split("&")[2];
	    		ngayhethan= tmp.split("=")[1];
	    	}else{
	    		ngayhethan = "";
	    	}
		}else{
			ngayhethan = "";
		}
	    return ngayhethan;
		
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
	
	public String getLoai(String querystring){
		String loai;
		String tmp;
		if(querystring != null){
			if(querystring.contains("&")){
				tmp = querystring.split("&")[2];
				loai = tmp.split("=")[1];
			}else{
				loai = "";
			}
		}else{
			loai = "";
		}
		return loai;
	}

	public String getSoChungTu(String querystring){
		String sochungtu;
		String tmp;
		if(querystring != null){
			if(querystring.contains("&")){
				tmp = querystring.split("&")[3];
				sochungtu = tmp.split("=")[1];
			}else{
				sochungtu = "";
			}
		}else{
			sochungtu = "";
		}
		return sochungtu;
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
	public String[] compareArrayString(String[] s1, String[] s2){
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
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getDateTime_2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
	
	public String calSum(String a, String b){
		String s = "" + (a.length()+ b.length())/a.length();
		return s;
	}
	
	public boolean check(String a, String b, String c){
		String tmp = calSum(a, b);
		if (tmp.equals(c)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isSessionAlive(HttpSession session){
		String userId = (String)session.getAttribute("userId");
		String userTen = (String)session.getAttribute("userTen");
		String sum = (String)session.getAttribute("sum");
		if(check(userId, userTen, sum)){		
			return true;
		}else{			
			return false;
		}
	}


	public String quyen_congty(String userId)
	{  
		String sql ="( select congty_fk from nhanvien_congty where nhanvien_fk ='"+ userId +"')";
		return sql;
	}
	
	public String quyen_khuvuc(String userId)
	{  
		String sql ="( select khuvuc_fk from nhanvien_khuvuc where nhanvien_fk ='"+ userId +"')";
		return sql;
	}
	
	public String quyen_donvithuchien(String userId)
	{  
		String sql ="( select donvithuchien_fk from nhanvien_donvithuchien where nhanvien_fk = '" + userId + "')";
		return sql;
	}
	
	public String quyen_sanpham(String userId)
	{  
		String sql ="( select sanpham_fk from nhanvien_sanpham where nhanvien_fk ='"+ userId +"')";
		return sql;
	}
	
	public String quyen_npp(String userId)
	{   String sql =" ( select npp_fk from phamvihoatdong where nhanvien_fk ='"+ userId +"') ";
		return sql;
	}
	
	public String quyen_kenh(String userId)
	{
		String sql ="( select kenh_fk as kbh_fk from nhanvien_kenh where nhanvien_fk ='"+ userId +"' )";
		return sql;
	}
	public String quyen_nhamay(String userId)
	{
		String sql ="( select nhamay_fk from nhanvien_nhamay where nhanvien_fk ='"+ userId +"' )";
		return sql;
	}
	public String quyen_khott(String userId)
	{
		String sql ="( select khott_fk from NHANVIEN_KHOTT where nhanvien_fk ='"+ userId +"' )";
		return sql;
	}
	
	public String quyen_nhacungcap(String userId)
	{  
		//String sql ="( select ncc_fk from NHANVIEN_NHACUNGCAP where nhanvien_fk = '" + userId + "')";
		String sql = "( select NCC.PK_SEQ "+ 
					" from NHANVIEN_NHACUNGCAP NVLNCC "+
					" inner join ERP_NHACUNGCAP NCC on NVLNCC.ncc_fk = NCC.pk_seq  "+
					" where NVLNCC.nhanvien_fk = '" + userId + "' )";
		return sql;
	}
	
	
	public String quyen_xemdon_mua_ban(String userId)
	{  
		String sql ="( select NHANVIEN_FK from ERP_CHUCDANH_NHANVIEN where CHUCDANH_FK=(select pk_seq from ERP_CHUCDANH a where a.NHANVIEN_FK='" + userId + "') )";
		return sql;
	}
	
	
	public int[] quyen_ungdung(String userId)
	{  
		int mang[] = new int[300];
		
		
		/*String sql =" select distinct ungdung_fk from nhomquyen where dmq_fk in (select pk_seq from danhmucquyen where pk_seq in (select dmq_fk from phanquyen where nhanvien_fk ='"+ userId +"'))";
		System.out.println("chuoi phan quyen :" + sql);
		dbutils db=new dbutils();
		ResultSet rs= db.get(sql);
		for(int j = 0;j<mang.length;j++)
			mang[j] = 0;
			int i = 0;
		if(rs!=null)
			try {
				while(rs.next())
				{
					i = Integer.parseInt(rs.getString("ungdung_fk"));
					mang[i] = 1;
					System.out.println("Ung dung : "+rs.getString("ungdung_fk"));
					System.out.println(mang[i]);
					
				}
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.shutDown();*/
		return mang;
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
		   'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'Ý', 'Ỳ', 'Ỵ', 'ỳ', 'ỵ', 'ý'};
		 
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
		   'U', 'u', 'Y', 'Y', 'Y', 'y', 'y', 'y'};
		public static String dbSync;
		public static String pass;
		public static String user;
		public static String ip;
		 
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
				
				if(trbl == true)
					return tmp;
				else return param;
			}
			
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
					/*
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
						*/
				} 
				catch (SQLException e) { return false; }
				return true;
			}
			public boolean checkDaduyet(String userId)
			{
				/*dbutils db = new dbutils();
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
				catch (SQLException e) { return false; }*/
				return true;
			}
			
			public String LastDayOfMonth(int month, int year) 
		    {
		        String ngay = "";
		        switch (month)
		        {
		            case 1:
		            case 3:
		            case 5:
		            case 7:
		            case 8:
		            case 10:
		            case 12:
		                {
		                    ngay = "31";
		                    break;
		                }
		            case 4:
		            case 6:
		            case 9:
		            case 11:
		                {
		                    ngay = "30";
		                    break;
		                }
		            case 2:
		                {
		                    if (year % 4 == 0)
		                        ngay = "29";
		                    else
		                        ngay = "28";
		                    break;
		                }
		        }

		        return ngay;
		    } 
			
			public int Songaytrongthang(int month, int year) 
		    {
		        int ngay = 0;
		        switch (month)
		        {
		            case 1:
		            case 3:
		            case 5:
		            case 7:
		            case 8:
		            case 10:
		            case 12:
		                {
		                    ngay = 31;
		                    break;
		                }
		            case 4:
		            case 6:
		            case 9:
		            case 11:
		                {
		                    ngay = 30;
		                    break;
		                }
		            case 2:
		                {
		                    if (year % 4 == 0)
		                        ngay = 29;
		                    else
		                        ngay = 28;
		                    break;
		                }
		        }

		        return ngay;
		    }
			
			public int getPhongBan(String nhanvien){
				
				String query="select DONVITHUCHIEN_FK  from NHANVIEN_DONVITHUCHIEN WHERE NHANVIEN_FK='"+nhanvien+"'";  
				dbutils db = new  dbutils();
				
				System.out.println(query);
				int pb=0;
				
				ResultSet rscheck= db.get(query);
				if(rscheck!=null){

					try 
					{
						while(rscheck.next())
						{
							int i =rscheck.getInt("DONVITHUCHIEN_FK");
							if(i==100000){
								pb = i;
							}
						}
						rscheck.close();
					} 
					catch 
					(Exception e) 
					{
						System.out.println(e.toString());
					}
				
				}
				
				System.out.println("Phong ban:"+pb);
				return pb;
				
			}
			
			public int[] Getquyen(String ungdung,String nhanvien)
			{
				int[] quyen = new int[5];
				int them =0;
				int xoa=0;
				int sua=0;
				int xem=0;
				int chot=0;
				String query ="select isnull(THEM,0) as THEM,isnull(XOA,0) as XOA,isnull(SUA,0) as SUA,isnull(XEM,0) as XEM,isnull(CHOT,0) as CHOT "+
				"from NHOMQUYEN  a inner join PHANQUYEN b on a.DMQ_fk = b.DMQ_fk where b.Nhanvien_fk='"+nhanvien+"' and UngDung_fk='"+ ungdung +"'";
				dbutils db = new  dbutils();
				
				System.out.println(query);
				ResultSet rscheck= db.get(query);
				if(rscheck!=null)
				{
					try 
					{
						while(rscheck.next())
						{
							if(rscheck.getInt("THEM")!=0)
								them=rscheck.getInt("THEM");
							
							if(rscheck.getInt("XOA")!=0)
								xoa=rscheck.getInt("XOA");
							
							if(rscheck.getInt("SUA")!=0)
								sua=rscheck.getInt("SUA");
							
							if(rscheck.getInt("XEM")!=0)
								xem=rscheck.getInt("XEM");
							
							if(rscheck.getInt("CHOT")!=0)
								chot=rscheck.getInt("CHOT");
						}
						rscheck.close();
					} 
					catch 
					(Exception e) 
					{
						System.out.println(e.toString());
					}
				}
				
				db.shutDown();
				quyen[0]=them;
				quyen[1]=xoa;
				quyen[2]=sua;
				quyen[3]=xem;
				quyen[4]=chot;
				return quyen;
				
			}
		
			
			
			public String checkNgayHopLe(geso.erp.db.sql.dbutils db,String ngaychotNV) {
				// TODO Auto-generated method stub
				try{
				if(ngaychotNV.equals("")){
					return "Vui lòng chọn ngày chốt";
					
				}
				 
				int thangtruoc=Integer.parseInt(ngaychotNV.substring(5, 7));
				int namtruoc=Integer.parseInt(ngaychotNV.substring(0, 4));
				
				 
				String sql=" select TOP 1 THANGKS, NAM from ERP_KHOASOTHANG order by NAM desc, THANGKS desc ";
				 
				ResultSet rscheckngay=db.get(sql);
				if(rscheckngay.next()){
					 if(thangtruoc <=  rscheckngay.getInt("THANGKS")  &&  namtruoc <= rscheckngay.getInt("NAM")){
						 return " Vui lòng chỉ được chọn ngày ghi nhận sau tháng khóa sổ gần nhất:Tháng :"+rscheckngay.getString("THANGKS")+",năm :  "+rscheckngay.getString("Nam");
					 }
				}else{ 
						return " Vui lòng kiểm tra khóa sổ tháng,chưa có khóa sổ tháng";
				}
 
				}catch(Exception er){
					return "Vui lòng thông báo với admin để xử lý: Lỗi: "+er.getMessage();
				}
				return "";
			 
			}
			public String getTime()
			{
				Date date = new Date();
			    SimpleDateFormat simpDate;
			    //format 24h
			    simpDate = new SimpleDateFormat("kk:mm:ss");
			    return simpDate.format(date);
			}
			

		 
			
			
}
