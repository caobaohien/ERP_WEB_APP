package geso.erp.servlets.reports;

 
import geso.dms.center.util.Utility;
import geso.erp.beans.report.Ireport;
import geso.erp.beans.report.imp.Report;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class BCDonDatHangNPP extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String setQuery(HttpServletRequest request, Ireport obj)
	{
		String query ="declare  @tb table ( "+
					"	ngayhoadon nvarchar(10), \n"+
					"	sohoadon nvarchar(20), \n"+
					"	erpdondathang_fk numeric (18,0)		 \n"+
					") \n"+
					" insert @tb  \n"+
					"	SELECT distinct  [NGAYHOADON],[SoHoaDon], ErpDonDatHang_FK 	FROM OPENROWSET('SQLOLEDB','"+ Utility.ip +"';'"+ Utility.user +"';'"+ Utility.pass +"',  'SELECT * FROM  "+ Utility.dbSync +".[dbo].[DMS_DonHang] ') a  \n"+	
					 "where ErpDonDatHang_FK IS NOT NULL \n"+ 
					 "select sp.ma as spma ,nhomsp.ten as tennhom,v.TEN as VUNG, kv.TEN as KHUVUC, kenh.TEN AS KENH,   \n "+      
						"gs.Ten AS GSBH,   \n "+      
						"dh.PK_SEQ as MADONDATHANG,MONTH( dh.NgayDonHang) as THANG, dh.NgayDonHang, p.TEN as NHAPHANPHOI, pdat.MaFAST as MA_NPPDAT, pdat.TEN as NPPDAT, tt.TEN as Tinhthanh,  \n "+      
						"CASE WHEN dh.TRANGTHAI = 0 then N'Chưa chốt'   \n "+      
						"	when dh.TRANGTHAI = 1 then N'Chờ duyệt'   \n "+      
						"	when dh.TRANGTHAI = 2 then N'Đã duyệt'   \n "+      
						"	when dh.TRANGTHAI = 4 then N'Hoàn tất' end as TRANGTHAI,   \n "+      
						"	sp.TEN AS SPTEN, dh_sp.soluong, dh_sp.dongia, dh_sp.THANHTIEN, dh_sp.SCHEME, dh_sp.TIENKM, dh_sp.DIENGIAI,\n "+      
						"	SUM(dh_sp.THANHTIEN) OVER (PARTITION BY dh.PK_SEQ) AS TONGTIEN,   \n "+      
						"	(SUM(dh_sp.THANHTIEN) OVER (PARTITION BY dh.PK_SEQ)- isnull((select SUM(ROUND(TONGGIATRI,0)) from ERP_DONDATHANGNPP_CTKM_TRAKM   \n "+      
						"				  where DONDATHANGID = dh.PK_SEQ and TRAKMID in (select PK_SEQ from TRAKHUYENMAI where LOAI = 2)),0))* dh.ChietKhau/100.0 AS CK,   \n "+      
						"	dh.DMSOrderAmount as TIENTHUVE, eff.SoHoaDon, eff.NGAYHOADON,N'Đơn đặt hàng'as loaidh \n" +
						    
						"from ERP_DONDATHANGNPP dh inner join    \n "+      
						"(   \n "+      
						"	SELECT dondathang_fk, sanpham_fk, soluong, dongia, soluong*dongia AS THANHTIEN, 0 AS TIENKM, '' AS SCHEME, '' as DIENGIAI \n "+      
						"	FROM ERP_DONDATHANGNPP_SANPHAM   \n "+      
						"	UNION ALL   \n "+      
						"	SELECT DONDATHANGID, s.PK_SEQ, km.SOLUONG, 0 as dongia, 0 AS THANHTIEN, CASE WHEN SPMA IS NOT NULL THEN 0 ELSE TONGGIATRI END AS TIENKM, k.SCHEME, k.DIENGIAI \n "+      
						"	FROM ERP_DONDATHANGNPP_CTKM_TRAKM km left join SANPHAM s on s.MA = km.SPMA \n "+      
						"	inner join CTKHUYENMAI k on k.PK_SEQ = km.CTKMID   \n "+      
						")   \n "+      
						" dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk   \n "+      
						"left join SANPHAM sp on sp.PK_SEQ = dh_sp.sanpham_fk   \n "+  
						"  left join NhomHang_SanPham nsp on nsp.sanpham_fk=dh_sp.sanpham_fk \n"+
						 " left join NhomHang nhomsp on nhomsp.PK_SEQ=nsp.nhomhang_FK  \n"+						
						"inner join NHAPHANPHOI p on p.PK_SEQ = dh.NPP_FK   \n "+      
						"inner join NHAPHANPHOI pdat on pdat.PK_SEQ = dh.NPP_DAT_FK   \n "+
						"inner join NHAPP_GIAMSATBH npp_gs on npp_gs.NPP_FK=pdat.PK_SEQ \n "+
						"inner join GIAMSATBANHANG gs on gs.PK_SEQ=npp_gs.GSBH_FK \n "+
						"inner join TINHTHANH tt on tt.PK_SEQ=pdat.TINHTHANH_FK     \n "+
						"INNER JOIN KHUVUC kv on kv.PK_SEQ = pdat.KHUVUC_FK   \n "+      
						"inner join VUNG v on v.PK_SEQ = kv.VUNG_FK   \n "+      
						"inner join KENHBANHANG kenh on kenh.PK_SEQ = dh.KBH_FK   \n "+  
						"left join " +
						"	 " +
						 "  @tb " +
						 "	" +
						 " eff on eff.ErpDonDatHang_FK = dh.pk_seq \n "+  
						"WHERE dh.TRANGTHAI != 3 and gs.trangthai=1 \n ";
		
		

			String query1 =" union all select  sp.ma as spma ,nhomsp.ten as tennhom,v.TEN as VUNG, kv.TEN as KHUVUC, kenh.TEN AS KENH,   \n"+
					" gs.Ten AS GSBH,  \n"+  
					"   dh.PK_SEQ as MADONDATHANG,MONTH(dh.ngaytra) as THANG, dh.ngaytra, p.TEN as NHAPHANPHOI, p.MaFAST as MA_NPPDAT, p.TEN as NPPDAT,tt.TEN as Tinhthanh,    \n"+
					"  CASE WHEN dh.TRANGTHAI = 0 then N'Chưa chốt'   \n"+
					"  	when dh.TRANGTHAI = 1 then N'đã chốt '    \n"+
					"  	when dh.TRANGTHAI = 2 then N'đã hủy'   end as TRANGTHAI,    \n"+
					"  	sp.TEN AS SPTEN, dh_sp.soluong, dh_sp.dongia, dh.TienSauThue,N'T ' SCHEME,  '' TIENKM, '' DIENGIAI, \n"+
					"  	SUM(dh.TienSauThue) OVER (PARTITION BY dh.PK_SEQ) AS TONGTIEN,    \n"+
					"  	'' AS CK,   \n"+
					"  	'' as TIENTHUVE, dh.SoHoaDon, dh.NGAYHOADON ,N'trả hàng' as loai \n"+
					" 	from Erp_HangTraLaiNpp dh inner join Erp_HangTraLaiNpp_SanPham dh_sp \n"+
					"   on dh.pk_seq=dh_sp.HangTraLai_fk \n"+
					"  left join SANPHAM sp on sp.PK_SEQ = dh_sp.sanpham_fk   \n"+
					"  left join NhomHang_SanPham nsp on nsp.sanpham_fk=dh_sp.sanpham_fk \n"+
					 " left join nhomhang nhomsp on nhomsp.PK_SEQ=nsp.nhomhang_FK  \n"+	
					"  inner join NHAPHANPHOI p on p.PK_SEQ = dh.NppTra_FK      \n"+
					"  inner join NHAPP_GIAMSATBH npp_gs on npp_gs.NPP_FK=p.PK_SEQ \n"+
					"  inner join GIAMSATBANHANG gs on gs.PK_SEQ=npp_gs.GSBH_FK  \n"+
					"  inner join TINHTHANH tt on tt.PK_SEQ = p.TINHTHANH_FK  \n"+
					"  inner JOIN KHUVUC kv on kv.PK_SEQ = p.KHUVUC_FK    \n"+
					"  inner join VUNG v on v.PK_SEQ = kv.VUNG_FK    \n"+
					"  inner join KENHBANHANG kenh on kenh.PK_SEQ = dh.KBH_FK    \n"+
					"  where dh.TRANGTHAI=1 and dh.doituong=0 and gs.trangthai=1 \n";
		

		if(obj.getKvId().length() >0 ){
			query += " and kv.pk_seq = "+obj.getKvId() ;
			query1 += " and kv.pk_seq = "+obj.getKvId() ;
		
		}
		if(obj.getVungId().length() >0 ){
			query += " and v.pk_seq = "+obj.getVungId() ;	
			query1 += " and v.pk_seq = "+obj.getVungId() ;	
		}
		
		if(obj.getnppId().length()>0 && !obj.getView().equals("NPP"))
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' ";
			query1 += " and p.PK_SEQ = '"+obj.getnppId()+"' ";
		}
		else if(obj.getnppId().length()> 0 && obj.getView().equals("NPP") && obj.getNppTructhuoc().equals("1")) // trung tam phan phoi
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' and dh.NPP_FK = " + obj.getTTPP();
		}
		else if(obj.getnppId().length()> 0 && obj.getView().equals("NPP") && !obj.getNppTructhuoc().equals("1")) // trung tam phan phoi
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' and dh.NPP_FK = " + obj.getNppTructhuoc();
		}
		if(obj.getView().equals("NPP") && obj.getNppTructhuoc().equals("1"))
			query += " and dh.NPP_FK = " + obj.getTTPP();
		
		if(obj.gettungay().length()>0)
		{
			query += " and  dh.NgayDonHang >= '"+obj.gettungay()+"' ";
			query1 += " and  dh.Ngaytra >= '"+obj.gettungay()+"' ";
		}
		
		if(obj.getdenngay().length()>0)
		{
			query += " and  dh.NgayDonHang <= '"+obj.getdenngay()+"' ";
			query1 += " and  dh.Ngaytra <= '"+obj.getdenngay()+"' ";
		}
		return query+query1;
	}
	
	private String setQuery_khongnhomsp(HttpServletRequest request, Ireport obj)
	{
		String query =
				"declare  @tb table ( "+
						"	ngayhoadon nvarchar(10), \n"+
						"	sohoadon nvarchar(20), \n"+
						"	erpdondathang_fk numeric (18,0)		 \n"+
						") \n"+
						" insert @tb  \n"+
						"	SELECT distinct  [NGAYHOADON],[SoHoaDon], ErpDonDatHang_FK 	FROM OPENROWSET('SQLOLEDB','"+ Utility.ip +"';'"+ Utility.user +"';'"+ Utility.pass +"',  'SELECT * FROM  "+ Utility.dbSync +".[dbo].[DMS_DonHang] ') a  \n"+	
						 "where ErpDonDatHang_FK IS NOT NULL \n"+ 
				"select sp.ma as spma ,v.TEN as VUNG, kv.TEN as KHUVUC, kenh.TEN AS KENH,   \n "+      
						" gs.TEN AS GSBH,   \n "+      
						"dh.PK_SEQ as MADONDATHANG,MONTH( dh.NgayDonHang) as THANG, dh.NgayDonHang, p.TEN as NHAPHANPHOI, pdat.MaFAST as MA_NPPDAT, pdat.TEN as NPPDAT, tt.TEN as Tinhthanh,   \n "+      
						"CASE WHEN dh.TRANGTHAI = 0 then N'Chưa chốt'   \n "+      
						"	when dh.TRANGTHAI = 1 then N'Chờ duyệt'   \n "+      
						"	when dh.TRANGTHAI = 2 then N'Đã duyệt'   \n "+      
						"	when dh.TRANGTHAI = 4 then N'Hoàn tất' end as TRANGTHAI,   \n "+      
						"	sp.TEN AS SPTEN, dh_sp.soluong, dh_sp.dongia, dh_sp.THANHTIEN, dh_sp.SCHEME, dh_sp.TIENKM, dh_sp.DIENGIAI,\n "+      
						"	SUM(dh_sp.THANHTIEN) OVER (PARTITION BY dh.PK_SEQ) AS TONGTIEN,   \n "+      
						"	(SUM(dh_sp.THANHTIEN) OVER (PARTITION BY dh.PK_SEQ)- isnull((select SUM(ROUND(TONGGIATRI,0)) from ERP_DONDATHANGNPP_CTKM_TRAKM   \n "+      
						"				  where DONDATHANGID = dh.PK_SEQ and TRAKMID in (select PK_SEQ from TRAKHUYENMAI where LOAI = 2)),0))* dh.ChietKhau/100.0 AS CK,   \n "+      
						"	dh.DMSOrderAmount as TIENTHUVE, eff.SoHoaDon, eff.NGAYHOADON,N'Đơn đặt hàng'as loaidh \n" +
						    
						"from ERP_DONDATHANGNPP dh inner join    \n "+      
						"(   \n "+      
						"	SELECT dondathang_fk, sanpham_fk, soluong, dongia, soluong*dongia AS THANHTIEN, 0 AS TIENKM, '' AS SCHEME, '' as DIENGIAI \n "+      
						"	FROM ERP_DONDATHANGNPP_SANPHAM   \n "+      
						"	UNION ALL   \n "+      
						"	SELECT DONDATHANGID, s.PK_SEQ, km.SOLUONG, 0 as dongia, 0 AS THANHTIEN, CASE WHEN SPMA IS NOT NULL THEN 0 ELSE TONGGIATRI END AS TIENKM, k.SCHEME, k.DIENGIAI \n "+      
						"	FROM ERP_DONDATHANGNPP_CTKM_TRAKM km left join SANPHAM s on s.MA = km.SPMA \n "+      
						"	inner join CTKHUYENMAI k on k.PK_SEQ = km.CTKMID   \n "+      
						")   \n "+      
						" dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk   \n "+      
						"left join SANPHAM sp on sp.PK_SEQ = dh_sp.sanpham_fk   \n "+  					
						"inner join NHAPHANPHOI p on p.PK_SEQ = dh.NPP_FK   \n "+      
						"inner join NHAPHANPHOI pdat on pdat.PK_SEQ = dh.NPP_DAT_FK   \n "+  
						"inner join NHAPP_GIAMSATBH npp_gs on npp_gs.NPP_FK=pdat.PK_SEQ \n "+
						"inner join GIAMSATBANHANG gs on gs.PK_SEQ=npp_gs.GSBH_FK \n "+
						"inner join TINHTHANH tt on tt.PK_SEQ=pdat.TINHTHANH_FK  \n "+
						"INNER JOIN KHUVUC kv on kv.PK_SEQ = pdat.KHUVUC_FK   \n "+      
						"inner join VUNG v on v.PK_SEQ = kv.VUNG_FK   \n "+      
						"inner join KENHBANHANG kenh on kenh.PK_SEQ = dh.KBH_FK   \n "+  
						"left join " +
						"	@tb " +
						 "	 " +
						 "	" +
						 " eff on eff.ErpDonDatHang_FK = dh.pk_seq \n "+  
						"WHERE dh.TRANGTHAI != 3  and gs.Trangthai=1  \n ";
		
		

			String query1 =" union all select  sp.ma as spma,v.TEN as VUNG, kv.TEN as KHUVUC, kenh.TEN AS KENH,   \n"+
					" gs.Ten AS GSBH,  \n"+  
					"   dh.PK_SEQ as MADONDATHANG,MONTH(dh.ngaytra) as THANG, dh.ngaytra, p.TEN as NHAPHANPHOI, p.MaFAST as MA_NPPDAT, p.TEN as NPPDAT, tt.TEN as Tinhthanh,    \n"+
					"  CASE WHEN dh.TRANGTHAI = 0 then N'Chưa chốt'   \n"+
					"  	when dh.TRANGTHAI = 1 then N'đã chốt '    \n"+
					"  	when dh.TRANGTHAI = 2 then N'đã hủy'   end as TRANGTHAI,    \n"+
					"  	sp.TEN AS SPTEN, dh_sp.soluong, dh_sp.dongia, dh.TienSauThue,N'T ' SCHEME,  '' TIENKM, '' DIENGIAI, \n"+
					"  	SUM(dh.TienSauThue) OVER (PARTITION BY dh.PK_SEQ) AS TONGTIEN,    \n"+
					"  	'' AS CK,   \n"+
					"  	'' as TIENTHUVE, dh.SoHoaDon, dh.NGAYHOADON ,N'trả hàng' as loai \n"+
					" 	from Erp_HangTraLaiNpp dh inner join Erp_HangTraLaiNpp_SanPham dh_sp \n"+
					"   on dh.pk_seq=dh_sp.HangTraLai_fk \n"+
					"  left join SANPHAM sp on sp.PK_SEQ = dh_sp.sanpham_fk   \n"+
					"  inner join NHAPHANPHOI p on p.PK_SEQ = dh.NppTra_FK      \n"+
					"  inner join NHAPP_GIAMSATBH npp_gs on npp_gs.NPP_FK=p.PK_SEQ \n"+
					"  inner join GIAMSATBANHANG gs on gs.PK_SEQ=npp_gs.GSBH_FK  \n"+
					"  inner join TINHTHANH tt on tt.PK_SEQ = p.TINHTHANH_FK  \n"+
					"  inner JOIN KHUVUC kv on kv.PK_SEQ = p.KHUVUC_FK    \n"+
					"  inner join VUNG v on v.PK_SEQ = kv.VUNG_FK    \n"+
					"  inner join KENHBANHANG kenh on kenh.PK_SEQ = dh.KBH_FK    \n"+
					"  where dh.TRANGTHAI=1 and gs.trangthai=1 and dh.doituong=0 \n";
		

		if(obj.getKvId().length() >0 ){
			query += " and kv.pk_seq = "+obj.getKvId() ;
			query1 += " and kv.pk_seq = "+obj.getKvId() ;
		
		}
		if(obj.getVungId().length() >0 ){
			query += " and v.pk_seq = "+obj.getVungId() ;	
			query1 += " and v.pk_seq = "+obj.getVungId() ;	
		}
		
		if(obj.getnppId().length()>0 && !obj.getView().equals("NPP"))
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' ";
			query1 += " and p.PK_SEQ = '"+obj.getnppId()+"' ";
		}
		else if(obj.getnppId().length()> 0 && obj.getView().equals("NPP") && obj.getNppTructhuoc().equals("1")) // trung tam phan phoi
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' and dh.NPP_FK = " + obj.getTTPP();
		}
		else if(obj.getnppId().length()> 0 && obj.getView().equals("NPP") && !obj.getNppTructhuoc().equals("1")) // trung tam phan phoi
		{
			query += " and pdat.PK_SEQ = '"+obj.getnppId()+"' and dh.NPP_FK = " + obj.getNppTructhuoc();
		}
		if(obj.getView().equals("NPP") && obj.getNppTructhuoc().equals("1"))
			query += " and dh.NPP_FK = " + obj.getTTPP();
		
		if(obj.gettungay().length()>0)
		{
			query += " and  dh.NgayDonHang >= '"+obj.gettungay()+"' ";
			query1 += " and  dh.Ngaytra >= '"+obj.gettungay()+"' ";
		}
		
		if(obj.getdenngay().length()>0)
		{
			query += " and  dh.NgayDonHang <= '"+obj.getdenngay()+"' ";
			query1 += " and  dh.Ngaytra <= '"+obj.getdenngay()+"' ";
		}
		return query+query1;
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Ireport obj = new Report();

		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setView(view);
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = "/SOHACO/pages/Center/BCDonDatHangNPP.jsp";
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Ireport obj = new Report();
		Utility util = new Utility();

		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);

		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);

		String nppId = "";
		nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);

		String vungId = request.getParameter("vungId");
		if (vungId == null)
			vungId = "";
		obj.setVungId(vungId);

		String khuvucId = request.getParameter("khuvucId");
		if (khuvucId == null)
			khuvucId = "";
		obj.setKvId(khuvucId);

		String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String view = util.antiSQLInspection(request.getParameter("view"));
		if (view == null)
			view = "";
		obj.setView(view);
		
		String tructhuoc_fk = util.antiSQLInspection(request.getParameter("tructhuoc_fk"));
		if (tructhuoc_fk == null)
			tructhuoc_fk = "";
		obj.setNppTructhuoc(tructhuoc_fk);
		
		
		String ttppId = util.antiSQLInspection(request.getParameter("ttppId"));
		if (ttppId == null)
			ttppId = "";
		obj.setTTPP(ttppId);
		
		String muclay = request.getParameter("muclay");
		if (muclay == null)
			muclay = "";
		obj.setMuclay(muclay);
		System.out.println("muc lay la "+muclay);
		
		String st = " and ddh.npp_fk in " + util.quyen_npp(obj.getuserId()) + " and ddh.kbh_fk in  " + util.quyen_kenh(obj.getuserId());

		if (nppId.length() > 0)
			st += " and ddh.npp_fk ='" + nppId + "'";

		if (khuvucId.length() > 0)
		{
			st += " and npp.khuvuc_fk =" + khuvucId + " ";
		}

		if (vungId.length() > 0)
		{
			st += " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk =" + vungId + ") ";
		}
		if(trangthai.length()>0)
		{
			st+=" and ddh.trangthai="+trangthai+" ";
		}

		String action = request.getParameter("action");
		if (action.equals("tao"))
		{

			OutputStream out = response.getOutputStream();
			String query="";
			if(muclay.equals("1"))
			{
			response.setHeader("Content-Disposition", "attachment; filename=BCDonDatHangNPP_nhom.xlsm");
			 query = setQuery(request, obj);
			}
			else
			{
				response.setHeader("Content-Disposition", "attachment; filename=BCDonDatHangNPP.xlsm");
				 query = setQuery_khongnhomsp(request, obj);
			}
			try 
			{
				//ExportToExcel(response,out,obj,query);
				CreatePivotTable(out, obj, query);
				return;
			} 
			catch (Exception e) {
				e.printStackTrace();
				obj.init();
				obj.setMsg("Exception" + e.getMessage());
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				String nextJSP = "/SOHACO/pages/Center/BCDonDatHangNPP.jsp";
				response.sendRedirect(nextJSP);
				
				return;
				
			}
			
			
		}
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = "/SOHACO/pages/Center/BCDonDatHangNPP.jsp";
		response.sendRedirect(nextJSP);
		return;
	}

	private boolean CreatePivotTable(OutputStream out, Ireport obj, String query) throws Exception
	{
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		if(obj.getMuclay().equals("1"))
		{
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonDatHangNPP.xlsm");
		}
		else
		{
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonDatHangNPP_nhom.xlsm");
		}
		
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		CreateHeader(workbook, obj);

		FillData(workbook, query, obj);
		
		workbook.save(out);
		fstream.close();
		return true;
	}

	private void CreateHeader(Workbook workbook, Ireport obj)
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();

		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo Đơn Đặt Hàng Nhà Phân Phối");
		cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

		cell = cells.getCell("FA1");		cell.setValue("VUNG");				
		cell = cells.getCell("FB1");		cell.setValue("KHUVUC");	
		cell = cells.getCell("FC1");		cell.setValue("KENH");		
		cell = cells.getCell("FD1");		cell.setValue("GSBH");			
		cell = cells.getCell("FE1");		cell.setValue("MADONDATHANG");
		cell = cells.getCell("FF1");		cell.setValue("THANG");
		cell = cells.getCell("FG1");		cell.setValue("NGAYDONHANG");			
		cell = cells.getCell("FH1");		cell.setValue("NHAPHANPHOI");					
		cell = cells.getCell("FI1");		cell.setValue("MA_NPPDAT");  		
		cell = cells.getCell("FJ1");		cell.setValue("NPPDAT");	
		cell = cells.getCell("FK1");		cell.setValue("TINHTHANH");	
		cell = cells.getCell("FL1");		cell.setValue("TRANGTHAI");				
		cell = cells.getCell("FM1");		cell.setValue("SPTEN");			
		cell = cells.getCell("FN1");		cell.setValue("soluong");				
		cell = cells.getCell("FO1");		cell.setValue("DonGia");				
		cell = cells.getCell("FP1");		cell.setValue("THANHTIEN");				
		cell = cells.getCell("FQ1");		cell.setValue("SCHEME");  			
		cell = cells.getCell("FR1");		cell.setValue("TONGTIEN");  		    	
		cell = cells.getCell("FS1");		cell.setValue("CK");  			
		cell = cells.getCell("FT1");		cell.setValue("TIENTHUVE");
		cell = cells.getCell("FU1");		cell.setValue("DIENGIAI");
		cell = cells.getCell("FV1");		cell.setValue("TIENKM");
		cell = cells.getCell("FW1");		cell.setValue("SOHOADON");
		cell = cells.getCell("FX1");		cell.setValue("NGAYHOADON");
		cell = cells.getCell("FY1");		cell.setValue("LOAIDH");
		cell = cells.getCell("FZ1");		cell.setValue("MASP");
		if(obj.getMuclay().equals("1"))
		{
		cell = cells.getCell("GA1");		cell.setValue("NHOMSP");
		}
		
	}

	private void FillData(Workbook workbook, String query, Ireport obj) throws Exception
	{
		ResultSet rs = null;
		dbutils db = new dbutils();
		try
		{	
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();

			for(int i=0;i<15;++i)
			{
				cells.setColumnWidth(i, 15.0f);	    	
			}	
			rs = db.get(query);
			int index = 2;
			Cell cell = null;
			String dh_bk="";
			String kh_bk = "";
			String dhid="";
			String khid = "";
			System.out.println("vao xuat excell" + query);
			if(rs!=null)
			{
				
				while (rs.next())
				{

					cell = cells.getCell("FA" + String.valueOf(index));		cell.setValue(rs.getString("VUNG"));			
					cell = cells.getCell("FB" + String.valueOf(index));		cell.setValue(rs.getString("KHUVUC"));
					cell = cells.getCell("FC" + String.valueOf(index));		cell.setValue(rs.getString("KENH"));
					cell = cells.getCell("FD" + String.valueOf(index));		cell.setValue(rs.getString("GSBH"));
					cell = cells.getCell("FE" + String.valueOf(index));		cell.setValue(rs.getString("MADONDATHANG"));  
					cell = cells.getCell("FF" + String.valueOf(index));		cell.setValue(rs.getString("THANG"));
					cell = cells.getCell("FG" + String.valueOf(index));		cell.setValue(rs.getString("NgayDonHang"));
					cell = cells.getCell("FH" + String.valueOf(index));		cell.setValue(rs.getString("NHAPHANPHOI"));
					cell = cells.getCell("FI" + String.valueOf(index));		cell.setValue(rs.getString("MA_NPPDAT"));
					cell = cells.getCell("FJ" + String.valueOf(index));		cell.setValue(rs.getString("NPPDAT"));
					cell = cells.getCell("FK" + String.valueOf(index));		cell.setValue(rs.getString("TINHTHANH"));
					cell = cells.getCell("FL" + String.valueOf(index));		cell.setValue(rs.getString("TRANGTHAI"));
					cell = cells.getCell("FM" + String.valueOf(index));		cell.setValue(rs.getString("SPTEN"));
					cell = cells.getCell("FN" + String.valueOf(index));		cell.setValue(rs.getFloat("soluong"));
					cell = cells.getCell("FO" + String.valueOf(index));		cell.setValue(rs.getFloat("dongia"));
					cell = cells.getCell("FP" + String.valueOf(index));		cell.setValue(rs.getFloat("THANHTIEN"));
					cell = cells.getCell("FQ" + String.valueOf(index));		cell.setValue(rs.getString("SCHEME"));
					cell = cells.getCell("FR" + String.valueOf(index));		cell.setValue("0");//TONG TIEN
					cell = cells.getCell("FS" + String.valueOf(index));		cell.setValue("0");// TIEN CK
					cell = cells.getCell("FT" + String.valueOf(index));		cell.setValue(rs.getFloat("TIENTHUVE"));
					cell = cells.getCell("FU" + String.valueOf(index));		cell.setValue(rs.getString("DIENGIAI"));
					cell = cells.getCell("FV" + String.valueOf(index));		cell.setValue(rs.getFloat("TIENKM"));
					cell = cells.getCell("FW" + String.valueOf(index));		cell.setValue(rs.getString("SOHOADON"));
					cell = cells.getCell("FX" + String.valueOf(index));		cell.setValue(rs.getString("NGAYHOADON"));
					cell = cells.getCell("FY" + String.valueOf(index));		cell.setValue(rs.getString("loaidh"));
					cell = cells.getCell("FZ" + String.valueOf(index));		cell.setValue(rs.getString("spma"));
					if(obj.getMuclay().equals("1"))
					{
					cell = cells.getCell("GA" + String.valueOf(index));		cell.setValue(rs.getString("tennhom"));
					}
					index++;
				}
				if(obj.getMuclay().equals("1"))
				cell = cells.getCell("A5");		cell.setValue("Lấy theo nhóm sản phẩm tổng doanh số sẽ sai");
			}
			if(rs!=null){
				rs.close();
			}	

			ReportAPI.setHidden(workbook,14);



		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(rs != null)
			{
				rs.close();
			}
			ex.printStackTrace();
			if(db != null)
				db.shutDown();

			throw new Exception(ex.getMessage());
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getPiVotName()
	{
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String name = sdf.format(cal.getTime());
		name = name.replaceAll("-", "");
		name = name.replaceAll(" ", "_");
		name = name.replaceAll(":", "");
		return "_" + name;

	}

	private void TaoBaoCao(Workbook workbook,Ireport obj,String query )throws Exception
	{
		try{


			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 50.0f);
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,"ĐƠN ĐẶT HÀNG NPP");

			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ngày tạo : "+ getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,"Người tạo : " + obj.getuserTen());

			worksheet.setGridlinesVisible(false);
			dbutils db = new dbutils();
			ResultSet rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 8;

			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow,i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, Color.YELLOW, BorderLineType.THIN, true, 0);	

			}
			countRow ++;


			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					cell = cells.getCell(countRow,i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}

			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}


		}catch(Exception ex){

			System.out.println("Errrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}

	private void ExportToExcel(HttpServletResponse response,OutputStream out,Ireport obj,String query )throws Exception
	{
		try{ 		
			response.setContentType("application/xlsm");
    		response.setHeader("Content-Disposition", "attachment; filename=bBCDonDatHangNPP.xlsm");
    		
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook,obj,query);		
			workbook.save(out);	

		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}

	}

}
