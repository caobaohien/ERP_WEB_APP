package geso.erp.servlets.chuyenkho_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoList;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoNew;
import geso.erp.beans.chuyenkho_kiet02.ISanPham;
import geso.erp.beans.chuyenkho_kiet02.imp.ChuyenKhoList;
import geso.erp.beans.chuyenkho_kiet02.imp.ChuyenKhoNew;
import geso.erp.beans.chuyenkho_kiet02.imp.SanPham;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.cete.dynamicpdf.Align;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

/**
 * Servlet implementation class ChuyenKhoNewSvl_kiet02
 */
@WebServlet("/ChuyenKhoNewSvl_kiet02")
public class ChuyenKhoNewSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenKhoNewSvl_kiet02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String action = util.getAction(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String Id = util.getId(querystring);
		String msg = "";
		
		IChuyenKhoNew cku = new ChuyenKhoNew();
		cku.setId(Id);
		cku.init();
		cku.createRs();
		// Ham load gia tri can update
		if (querystring.indexOf("update") >= 0) {
			String id = request.getParameter("update");
			session.setAttribute("action", "update");
			cku.setId(id);
			cku.init();
			System.out.println("update ma bao nhiêu :" + id);
			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
			response.sendRedirect(nextJSP);
			
		} else if (querystring.indexOf("display") >= 0) {
			String id = request.getParameter("display");
			session.setAttribute("action", "display");
			cku.setId(id);
			cku.init();
			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
			response.sendRedirect(nextJSP);
			
		} else {

			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if (action == null){
			action = "";
		}
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		IChuyenKhoNew obj = new ChuyenKhoNew();
		obj.setUserId(userId);
		
		String id = util.antiSQLInspection(request.getParameter("id"));
		obj.setId(id == null ? "": id);
		
		String ngayChuyen =  util.antiSQLInspection(request.getParameter("ngayghinhan"));
		obj.setNgayChuyenKho(ngayChuyen == null? "": ngayChuyen);
		
		String khoChuyen = util.antiSQLInspection(request.getParameter("khochuyen"));
		obj.setKhoChuyen(khoChuyen == null? "": khoChuyen);
		
		String khoNhan = util.antiSQLInspection(request.getParameter("khonhan"));
		obj.setKhoNhan(khoNhan == null? "": khoNhan);
		
		String lyDo = util.antiSQLInspection(request.getParameter("lydo"));
		obj.setLyDo(lyDo == null ?"": lyDo);
		
		String[] idsp = request.getParameterValues("idsp");
		String[] masp = request.getParameterValues("masp");
		String[] tensp = request.getParameterValues("tensp");
		String[] dvt = request.getParameterValues("dvt");
		String[] slc = request.getParameterValues("slchuyen");
		String[] slt = request.getParameterValues("slton");
		
		List<ISanPham> spList = new ArrayList<ISanPham>();
		if (masp != null){
			for (int i =0; i< masp.length; i++){
				if (masp[i].length() > 0){
					ISanPham sp = new SanPham();
					sp.setId(idsp[i]);
					sp.setMaSanPham(masp[i]);
					sp.setTenSanPham(tensp[i]);
					sp.setDonViTinh(dvt[i]);
					
					double sltSP = 0;
					try {
						sltSP = Double.parseDouble(slt[i].replaceAll(",", ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sp.setSoLuongTon(sltSP);
					double slcSP = 0;
					try {
						slcSP = Double.parseDouble(slc[i].replaceAll(",", ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sp.setSoLuongChuyen(slcSP);
					spList.add(sp);
				}
			}
		}
		obj.setListSp(spList);
		System.out.println("Action: " + action);
		System.out.println("spList.size(): "+spList.size());
		if (action.equals("save")) {
			if (!obj.save()) { // save isn't success
				obj.createRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "save");
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
				response.sendRedirect(nextJSP);
			} else {
				IChuyenKhoList ckList = new ChuyenKhoList();
				ckList.init("");
				session.setAttribute("obj", ckList);
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoList_kiet02.jsp";
				response.sendRedirect(nextJSP);
			}
		} else if (action.equals("update")) {
			if (!obj.update()){ //update isn't sucess
				obj.createRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "update");
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
				response.sendRedirect(nextJSP);
				return;
			} else {
				IChuyenKhoList ckList = new ChuyenKhoList();
				ckList.init("");
				session.setAttribute("obj", ckList);
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoList_kiet02.jsp";
				response.sendRedirect(nextJSP);
				return;
			}
		} else if (action.equals("chuyenkho")){
			obj.init();
			spList.clear();
			obj.createRs();
			session.setAttribute("kho", khoChuyen);
			session.setAttribute("obj", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
			response.sendRedirect(nextJSP);
		} else {
			obj.createRs();
			session.setAttribute("obj", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_kiet02.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
	}

	
	protected void create_PO_PDF(HttpServletResponse response, HttpServletRequest request, String id){
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename= PhieuChuyenKho.pdf");
		
		float CONVERT = 28.346257f;
		float PAGE_LEFT = 2.0f* CONVERT, PAGE_RIGHT = 1.5f* CONVERT, 
				PAGE_TOP = 0.5f* CONVERT, PAGE_BOTTOM = 0.0f* CONVERT;
		Document document = new Document(PageSize.A4,
				PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();
			
			create_PO_Training(document, outstream, response, request, db, id);
			db.shutDown();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("_Exception PO Print: "+ e.getMessage());
		}
	}
	
	protected void create_PO_Training(Document document, ServletOutputStream outstream, 
			HttpServletResponse response, HttpServletRequest request, dbutils db, String id){
		
		HttpSession session = request.getSession();
		try {
			//create obj PdfWitter
			PdfWriter.getInstance(document, outstream);
			document.open();
			float CONVERT = 28.346457f;
			BaseFont bf = BaseFont.createFont("c:\\windows\\font\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			com.itextpdf.text.Font font10 = new com.itextpdf.text.Font(bf, 10,
					com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font font11 = new com.itextpdf.text.Font(bf, 11,
					com.itextpdf.text.Font.BOLD);
			//---------------------
			PdfPTable ima = new PdfPTable(1);
			ima.setWidthPercentage(50);
			ima.setHorizontalAlignment(Element.ALIGN_RIGHT);
			PdfPTable tbl_header = new PdfPTable(1);
			
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			tbl_header.setWidthPercentage(60);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPCell cell = new PdfPCell(new Paragraph(
					"CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU", font11));
			
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(
					"234, Nguyễn Trọng Tuyển, phường 8, Phú Nhuận", font11));
			cell.setPadding(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			
			Paragraph para = new Paragraph("\n");
			document.add(para);
			String MaCT = "", NgayLap = "", TuKho = "", DenKho = "", LyDoChuyen = "",
					DiaChiGH = "", DiaChi = "";
			Paragraph pck = new Paragraph("PHIẾU CHUYỂN KHO",
					new com.itextpdf.text.Font(bf, 14,
							com.itextpdf.text.Font.BOLD));
			
			String query = "";
			query = "select CK.PK_SEQ as MaChungTu, SP.MA as MaSanPham, SP.TEN as TenSanPham, "
					+ " DV.TEN as DonVi, CK_SP.SOLUONG as SoLuongChuyen, K.TENKHO as KhoNhan, "
					+ " K1.TENKHO as KhoChuyen, CK.LYDO as LyDo, K.DIACHI as DiaChiKhoNhan, "
					+ " K1.DIACHI as DiaChiKhoChuyen, CK.NGAYCHUYENKHO as NgayChuyenKho, "
					+ " from ERP_SANPHAM SP left join ERP.DONVI DV on DV.PK_SEQ = SP.DONVI_FK "
					+ " left join CHUYENKHO_SANPHAM CK_SP on CK_SP.SANPHAM_FK = SP.PK_SEQ "
					+ " left join CHUYENKHO CK on CK.PK_SEQ = CK_SP.CHUYENKHO_FK "
					+ " left join KHO K on K.PK_SEQ = CK.KHONHAN "
					+ " left join KHO K1 on K1.PK_SEQ = CK.KHOCHUYEN "
					+ " where CK_SP.CHUYENKHO_FK = " + id;
			ResultSet rs = db.get(query);
			while (rs.next()){
				MaCT = rs.getString("MaChungTu");
				NgayLap = rs.getString("NgayChuyenKho");
				TuKho = rs.getString("KhoChuyen");
				DenKho = rs.getString("KhoNhan");
				LyDoChuyen = rs.getString("LyDo");
				DiaChiGH = rs.getString("DiaChiKhoNhan");
				DiaChi = rs.getString("DiaChiKhoChuyen");
			}
			
			pck.setSpacingAfter(2);
			pck.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(pck);
			PdfPTable ct = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Mã chứng từ: "+ MaCT, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Ngày lập: " + NgayLap, font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Từ kho: " + TuKho, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Đến kho: " + DenKho, font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Địa chỉ: " + DiaChi, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Địa chỉ giao hàng: " + DiaChiGH,
					font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Lý do chuyển: " + LyDoChuyen,
					font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			document.add(ct);
			document.add(pck);
			
			float[] crSanPham = { 1f * CONVERT, 2.0f * CONVERT, 4.0f * CONVERT,
					2.0f * CONVERT, 2.0f * CONVERT };
			PdfPTable tbSanPham = new PdfPTable(crSanPham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbSanPham.setWidths(crSanPham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			String[] spTitle = { "STT", "Mã Sản Phẩm", "Tên Sản Phẩm",
					"Đơn Vị", "Số Lượng Chuyển" };
			
			for (int i= 0; i < spTitle.length; i++){
				cell = new PdfPCell(new Paragraph(spTitle[i], font11));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			
			int stt =0;
			ResultSet rs02 = db.get(query);
			while (rs02.next()){
				String[] spTitle02 = {""+ stt, rs02.getString("masp"),
						rs02.getString("tensp"), rs02.getString("donvi"),
						rs02.getString("coluongchuyen")};
				
				for (int i = 0; i < spTitle02.length; i++){
					cell = new PdfPCell(new Paragraph(spTitle02[i], font10));
					cell.setPadding(3.0f);
					if (i == 4 || i == 5 || i == 7){
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				stt++;
			}
			document.add(tbSanPham);
			PdfPTable pTable = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Trưởng phòng kinh doanh: ", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			pTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Người nhận: ", font11));
			cell.setPaddingRight(10.0f);
			cell.setBorder(0);
			pTable.addCell(cell);

			document.add(pTable);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in PDF: "+ e.getMessage());
		}
	}
	//-------------------------------------------------
	protected boolean createExcel(OutputStream out, HttpServletResponse response,
			HttpServletRequest request, String id) throws IOException{
		boolean isFillData = false;
		FileInputStream fStream = null;
		Workbook wb = new Workbook();
		NumberFormat numberFormat_2sole = new DecimalFormat("#, ###, ###.###");
		NumberFormat numberFormat = new DecimalFormat("#, ###, ###");
		
		fStream = new FileInputStream(getServletContext().getInitParameter(
				"path")
				+ "\\donhangexcel.xls");
		wb.open(fStream);
		wb.setFileFormatType(FileFormatType.EXCEL97TO2003);
		
		isFillData = FillData(wb, id);
		if (!isFillData){
			if (fStream != null){
				fStream.close();
				return false;
			}
		}
		
		wb.save(out);
		fStream.close();
		return true;
	}
	
	protected boolean FillData(Workbook wb, String id){

		
		dbutils db = new dbutils();
		try {
			Worksheets ws = wb.getWorksheets();
			Worksheet ws02 = ws.getSheet(0);
			NumberFormat format_2sole = new DecimalFormat("#, ###, ###.###");
			NumberFormat format = new DecimalFormat("#, ###, ###");
			
			Cells cells = ws02.getCells();
			Cell cell = null;
			Style style = null;
			
			Font font = new Font();
			font.setName("Arial");
			font.setSize(14);
			
			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU");
			font.setColor(Color.BLACK);
			font.setBold(true);
			
			style = cell.getStyle();
			style.setFont(font);
			style.setColor(null);
			
			cell = cells.getCell("C8");
			cell.setValue("STT");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			// -----------------------------

			cell = cells.getCell("D8");
			cell.setValue("Mã Sản Phẩm");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			// -----------------------------

			cell = cells.getCell("E8");
			cell.setValue("Tên Sản Phẩm");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			// -----------------------------

			cell = cells.getCell("F8");
			cell.setValue("Đơn Vị");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			// -----------------------------

			cell = cells.getCell("G8");
			cell.setValue("Số Lượng Chuyển");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(null);
			// -----------------------------
			
			String query = "";
			query = "select CK.PK_SEQ as MaChungTu, SP.MA as MaSanPham, SP.TEN as TenSanPham, "
				+ " DV.TEN as DonVi, CK_SP.SOLUONG as SoLuongChuyen, K.TENKHO as KhoNhan, "
				+ " K1.TENKHO as KhoChuyen, CK.LYDO as LyDo, K.DIACHI as DiaChiKhoNhan, "
				+ " K1.DIACHI as DiaChiKhoChuyen, CK.NGAYCHUYENKHO as NgayChuyenKho, "
				+ " from ERP_SANPHAM SP left join ERP.DONVI DV on DV.PK_SEQ = SP.DONVI_FK "
				+ " left join CHUYENKHO_SANPHAM CK_SP on CK_SP.SANPHAM_FK = SP.PK_SEQ "
				+ " left join CHUYENKHO CK on CK.PK_SEQ = CK_SP.CHUYENKHO_FK "
				+ " left join KHO K on K.PK_SEQ = CK.KHONHAN "
				+ " left join KHO K1 on K1.PK_SEQ = CK.KHOCHUYEN "
				+ " where CK_SP.CHUYENKHO_FK = " + id;
			System.out.println("Query= ");
			ResultSet rs = db.get(query);
			String MaCT = "", NgayLap = "", TuKho = "", DenKho = "", DiaChiGH = "",
							DiaChi = "", LyDoChuyen = "";
			int stt = 1;
			while (rs.next()){
				String[] spTitle = {"C", "D", "E", "F", "G"};
				String[] spTitle02 = {""+ stt, rs.getString("masp"), rs.getString("tensp"),
						rs.getString("donvi"), rs.getString("soluongchuyen")};
				
				MaCT = rs.getString("MaChungTu");
				NgayLap = rs.getString("TenSanPham");
				TuKho = rs.getString("KhoChuyen");
				DenKho = rs.getString("KhoNhan");
				LyDoChuyen = rs.getString("LyDo");
				DiaChiGH = rs.getString("DiaChiKhoNhan");
				DiaChi = rs.getString("DiaChiKhoChuyen");
				
				for (int i = 0; i < spTitle02.length; i++) {
					cell = cells.getCell(spTitle[i] + Integer.toString(8 + stt));
					cell.setValue(spTitle02[i]);
					font.setColor(Color.BLACK);
					font.setBold(false);
					font.setSize(12);
					style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
					style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
					style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
					style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
					cell.setStyle(style);
					style.setFont(font);
				}
				stt++;
				
				cell = cells.getCell("B3");
				font.setBold(false);
				font.setColor(Color.BLACK);
				
				style.setFont(font);
				cell.setValue("Mã Chứng Từ");
				style.setFont(font);
				
				cell = cells.getCell("C3");
				style.setFont(font);
				cell.setValue(MaCT);
				
				style.setFont(font);
				cell = cells.getCell("B4");
				style.setFont(font);
				cell.setValue("Từ Kho: ");
				
				style.setFont(font);
				cell = cells.getCell("C4");
				style.setFont(font);
				cell.setValue(TuKho);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
}





