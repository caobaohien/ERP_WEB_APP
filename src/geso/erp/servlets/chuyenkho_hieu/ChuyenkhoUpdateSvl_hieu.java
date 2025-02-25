package geso.erp.servlets.chuyenkho_hieu;

import geso.dms.center.util.Utility;

import geso.erp.beans.chuyenkho_hieu.IChuyenkhoList_hieu;
import geso.erp.beans.chuyenkho_hieu.IChuyenkhoUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.ISanpham;
import geso.erp.beans.chuyenkho_hieu.imp.ChuyenkhoList_hieu;
import geso.erp.beans.chuyenkho_hieu.imp.ChuyenkhoUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.imp.Sanpham;
import geso.erp.db.sql.dbutils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Servlet implementation class NhacungcapNewSvl
 */
@WebServlet("/NhacungcapNewSvl")
public class ChuyenkhoUpdateSvl_hieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChuyenkhoUpdateSvl_hieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// dùng để lấy dữ liệu
		request.setCharacterEncoding("UTF-8");
		// dung để điều hướng
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
		IChuyenkhoUpdate_hieu cku = new ChuyenkhoUpdate_hieu();
		cku.setId(Id);
		cku.init();
		cku.CreateRs();
		// Ham load gia tri can update
		if (querystring.indexOf("update") >= 0) {
			String id = request.getParameter("update");
			session.setAttribute("action", "update");
			cku.setId(id);
			cku.init();
			System.out.println("update ma bao nhiêu :" + id);
			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp";
			response.sendRedirect(nextJSP);
		}
		else if (querystring.indexOf("display") >= 0) {
			String id = request.getParameter("display");
			session.setAttribute("action", "display");
			cku.setId(id);
			cku.init();
			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoDisplay_hieu.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{

		session.setAttribute("obj", cku);
		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp";
		response.sendRedirect(nextJSP);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IChuyenkhoUpdate_hieu obj = new ChuyenkhoUpdate_hieu();
		OutputStream out = response.getOutputStream();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}

		Utility util = new Utility();

		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);

		// trường hợp tạo mới ID sẽ không có ,bằng khoảng trắng
		String id = util.antiSQLInspection(request.getParameter("id"));
		obj.setId(id == null ? "" : id);
		String ngayghinhan = util.antiSQLInspection(request
				.getParameter("ngayghinhan"));
		obj.setNgaychuyenkho(ngayghinhan == null ? "" : ngayghinhan);
		String khochuyen = util.antiSQLInspection(request
				.getParameter("khochuyen"));
		obj.setKhochuyen(khochuyen == null ? "" : khochuyen);
		String lydo = util.antiSQLInspection(request.getParameter("lydo"));
		obj.setLydochuyen(lydo == null ? "" : lydo);
		String khonhan = util
				.antiSQLInspection(request.getParameter("khonhan"));
		obj.setKhonhan(khonhan == null ? "" : khonhan);

		String[] masp = request.getParameterValues("masp");
		String[] idsp = request.getParameterValues("idsp");
		String[] tensp = request.getParameterValues("tensp");
		String[] donvitinh = request.getParameterValues("dvt");
		String[] soluongton = request.getParameterValues("slton");
		String[] soluongchuyen = request.getParameterValues("slchuyen");

		// lấy được 1 mảng mã sản phẩm
		List<ISanpham> listsp = new ArrayList<ISanpham>();
		if (masp != null) {
			for (int i = 0; i < masp.length; i++) {
				if (masp[i].length() > 0) {
					ISanpham sp = new Sanpham();
					sp.setMasanpham(masp[i]);
					sp.setId(idsp[i]);
					sp.setTensanpham(tensp[i]);
					sp.setDonvitinh(donvitinh[i]);

					double slt = 0;
					try {
						slt = Double.parseDouble(soluongton[i].replaceAll(",",
								""));

					} catch (Exception er) {

					}
					sp.setSoluongton(slt);

					double slc = 0;
					try {
						slc = Double.parseDouble(soluongchuyen[i].replaceAll(
								",", ""));

					} catch (Exception er) {

					}
					sp.setSoluongchuyen(slc);
					listsp.add(sp);

				}
			}
		}
		obj.setListSp(listsp);

		System.out.println("action: " + action);
		if (action.equals("save")){
			if (!obj.save()) {
				// lưu không thành công
				obj.CreateRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "save");
				response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp");
			} else {
				IChuyenkhoList_hieu ck = new ChuyenkhoList_hieu();
				ck.init("");
				session.setAttribute("obj", ck);
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp";
				response.sendRedirect(nextJSP);
			}
		} else if (action.equals("update")) {

			if (!obj.update()) {
				obj.CreateRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "update");
				response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp");
				return;
			} else {
				IChuyenkhoList_hieu ck = new ChuyenkhoList_hieu();
				ck.init("");
				session.setAttribute("obj", ck);
				String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp";
				response.sendRedirect(nextJSP);
				return;
			}
		}else if (action.equals("printpdf")) {
			System.out.println("dang o pdf");
			Create_PO_PDF(response, request,id);
			
		} 
		else if (action.equals("printexcel")) {

			response.setContentType("application/xls");
			response.setHeader("Content-Disposition",
					"attachment; filename=DonMuaHang.xls");
			if (!CreateExcel(out, request, response, id)) {
				response.setContentType("text/html");
				PrintWriter writer = new PrintWriter(out);
				writer.print("Lỗi xuất excel");
				writer.close();
			}
		} else if (action.equals("chuyenkho")) {

			session.setAttribute("kho", khochuyen);
			obj.init();
			listsp.clear();
			obj.CreateRs();
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp");

		}
		else {
			obj.CreateRs();
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp");
			return;
		}

	}

	private boolean CreateExcel(OutputStream out, HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
		NumberFormat formatter = new DecimalFormat("#,###,###");

		fstream = new FileInputStream(getServletContext().getInitParameter(
				"path")
				+ "\\donhangexcel.xls");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);

		isFillData = FillData(workbook, id);

		if (!isFillData) {
			if (fstream != null)
				fstream.close();
			return false;
		}

		workbook.save(out);
		fstream.close();
		return true;
	}

	private boolean FillData(Workbook workbook, String id) {

		dbutils db = null;
		try {
			db = new dbutils();

			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");

			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style style = null;
			// Khai báo font
			Font font = new Font();
			font.setName("Arial");
			font.setSize(14);

			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU ");
			font.setColor(Color.BLACK);
			font.setBold(true);

			style = cell.getStyle();
			style.setFont(font);

			style.setColor(null);
			cell = cells.getCell("C8");
			cell.setValue("Số thứ tự ");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			cell = cells.getCell("D8");
			cell.setValue("Mã sản phẩm ");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			cell = cells.getCell("E8");
			cell.setValue("Tên sản phẩm ");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			cell = cells.getCell("F8");
			cell.setValue("Đơn vị");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			cell = cells.getCell("G8");
			cell.setValue("Số lượng chuyển");
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			cell.setStyle(style);
			style.setColor(null);
			
			String query = "";
			query = " select ck.PK_SEQ as machungtu, sp.MA as masp,sp.TEN as tensp,dv.TEN  as donvi,cksp.SOLUONG as soluongchuyen,k.TENKHO as khonhan,k1.TENKHO as khochuyen,ck.LYDO "
					+ " as lydo,k.DIACHI as diachikhonhan,k1.DIACHI as diachikhochuyen,ck.ngaychuyenkho as ngaychuyenkho "
					+ " from ERP_SANPHAM sp left join ERP_DONVI dv on sp.DONVI_FK=dv.PK_SEQ "
					+ " left join CHUYENKHO_SANPHAM cksp on cksp.SANPHAM_FK=sp.PK_SEQ "
					+ " left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "
					+ " LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ "
					+ " where cksp.CHUYENKHO_FK= " + id;
			// System.out.println("Cau query :" +query);
			ResultSet rs = db.get(query);

			String mact = "", ngaylap = "", tukho = "", denkho = "", lydochuyen = "", diachigiaohang = "", diachi = "";
			int sott = 1;
		
			while (rs.next()) {
				String[] spTitle = { "C", "D", "E", "F", "G" };
				String[] spTitles2 = { "" + sott, rs.getString("masp"),
						rs.getString("tensp"), rs.getString("DONVI"),
						rs.getString("soluongchuyen") };
				mact = rs.getString("machungtu");
				ngaylap = rs.getString("ngaychuyenkho");
				tukho = rs.getString("khochuyen");
				denkho = rs.getString("khonhan");
				lydochuyen = rs.getString("lydo");
				diachigiaohang = rs.getString("diachikhonhan");
				diachi = rs.getString("diachikhochuyen");
				for (int z = 0; z < spTitles2.length; z++) {
					cell = cells.getCell(spTitle[z]
							+ Integer.toString(8 + sott));
					cell.setValue("" + spTitles2[z] + "");
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
				sott++;
			}

			cell = cells.getCell("B3");
			font.setBold(false);
			font.setColor(Color.BLACK);

			style.setFont(font);
			cell.setValue("Mã chứng từ");

			style.setFont(font);
			cell = cells.getCell("C3");

			style.setFont(font);
			cell.setValue("" + mact + "");

			style.setFont(font);
			cell = cells.getCell("B4");

			style.setFont(font);
			cell.setValue("Từ kho");

			style.setFont(font);
			cell = cells.getCell("C4");
			cell.setValue("" + tukho + "");

			style.setFont(font);
			cell = cells.getCell("B5");

			style.setFont(font);
			cell.setValue("Địa chỉ");

			style.setFont(font);
			cell = cells.getCell("C5");
			cell.setValue("" + diachigiaohang + "");

			style.setFont(font);
			cell = cells.getCell("B6");

			style.setFont(font);
			cell.setValue("Lý do chuyển");

			style.setFont(font);
			cell = cells.getCell("C6");
			cell.setValue("" + lydochuyen + "");

			style.setFont(font);

			cell = cells.getCell("E3");
			font.setBold(false);
			style.setFont(font);
			cell.setValue("Ngày lập");

			style.setFont(font);
			cell = cells.getCell("F3");
			
			style.setFont(font);
			cell.setValue("" + mact + "");

			style.setFont(font);
			cell = cells.getCell("E4");

			style.setFont(font);

			cell.setValue("Đến kho");

			style.setFont(font);
			cell = cells.getCell("F4");

			cell.setValue("" + denkho + "");

			style.setFont(font);

			cell = cells.getCell("E5");

			style.setFont(font);

			cell.setValue("Địa chỉ giao hàng");

			style.setFont(font);

			cell = cells.getCell("F5");
			cell.setValue("" + diachigiaohang + "");

			style.setFont(font);

			cell = cells.getCell("C" + Integer.toString(12 + sott));
			cell.setValue("Trường phòng cung ứng");
			style.setFont(font);

			cell = cells.getCell("F" + Integer.toString(12 + sott));
			cell.setValue("Người nhận");
			style.setFont(font);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			db.shutDown();
		}
	}
	private void Create_PO_PDF(HttpServletResponse response,
			HttpServletRequest request,String id) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				" inline; filename=DonMuaHang.pdf");

		float CONVERT = 28.346457f;// 1 cm
		float PAGE_LEFT = 2.0f * CONVERT, PAGE_RIGHT = 1.5f * CONVERT, PAGE_TOP = 0.5f * CONVERT, PAGE_BOTTOM = 0.0f * CONVERT; // cm
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT,
				PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();

			this.CreatePO_Training(document, outstream, response, request, db,id);
			db.shutDown();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}

	private void CreatePO_Training(Document document,ServletOutputStream outstream, HttpServletResponse response,
			HttpServletRequest request, dbutils db,String id) {
		
		HttpSession session = request.getSession();
		try {
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			com.itextpdf.text.Font font10 = new com.itextpdf.text.Font(bf, 10,
					com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font font11 = new com.itextpdf.text.Font(bf, 11,
					com.itextpdf.text.Font.BOLD);

			PdfPTable ima = new PdfPTable(1);
			ima.setWidthPercentage(50);
			ima.setHorizontalAlignment(Element.ALIGN_RIGHT);
			PdfPTable tbl_header = new PdfPTable(1);

			float[] crtbl_header = { 7f };
			tbl_header.setWidths(crtbl_header);
			tbl_header.setWidthPercentage(60);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell = new PdfPCell(new Paragraph(
					"CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU ", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);

			cell = new PdfPCell(new Paragraph(
					"234 Nguyễn Trọng Tuyển,Phú Nhuận,HCM", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			Paragraph pxk1 = new Paragraph("\n");
			document.add(pxk1);
			String mact = "", ngaylap = "", tukho = "", denkho = "", lydochuyen = "", diachigiaohang = "", diachi = "";
			Paragraph pxk = new Paragraph("PHIẾU CHUYỂN KHO",
					new com.itextpdf.text.Font(bf, 14,
							com.itextpdf.text.Font.BOLD));
			String query = "";

			query = " select ck.PK_SEQ as machungtu, sp.MA as masp,sp.TEN as tensp,dv.TEN  as donvi,cksp.SOLUONG as soluongchuyen,k.TENKHO as khonhan,k1.TENKHO as khochuyen,ck.LYDO "
				+ " as lydo,k.DIACHI as diachikhonhan,k1.DIACHI as diachikhochuyen,ck.ngaychuyenkho as ngaychuyenkho "
				+ " from ERP_SANPHAM sp left join ERP_DONVI dv on sp.DONVI_FK=dv.PK_SEQ "
				+ " left join CHUYENKHO_SANPHAM cksp on cksp.SANPHAM_FK=sp.PK_SEQ "
				+ " left join CHUYENKHO ck on cksp.CHUYENKHO_FK=ck.PK_SEQ "
				+ " LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ "
				+ " where cksp.CHUYENKHO_FK= " + id;
			ResultSet rs = db.get(query);
			while (rs.next()) {
				mact = rs.getString("machungtu");
				ngaylap = rs.getString("ngaychuyenkho");
				tukho = rs.getString("khochuyen");
				denkho = rs.getString("khonhan");
				lydochuyen = rs.getString("lydo");
				diachigiaohang = rs.getString("diachikhonhan");
				diachi = rs.getString("diachikhochuyen");
			}
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			document.add(pxk1);
			PdfPTable ct = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Mã chứng từ :" + mact, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			cell = new PdfPCell(new Paragraph("Ngày lập :" + ngaylap, font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Từ kho :" + tukho, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Đến kho :" + denkho, font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Địa chỉ :" + diachi, font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Địa chỉ giao hàng :"
					+ diachigiaohang, font11));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("Lý do chuyển :" + lydochuyen,
					font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			cell = new PdfPCell(new Paragraph("", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);

			document.add(ct);
			document.add(pxk1);
			float[] crSanpham = { 1f * CONVERT, 2.0f * CONVERT, 4.0f * CONVERT,
					2.0f * CONVERT, 2.0f * CONVERT };
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			String[] spTitles = { "SỐ TT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM",
					"ĐƠN VỊ", "SỐ LƯỢNG CHUYỂN " };

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11));
				cell.setPadding(3.0f);

				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			int sott = 1;
			ResultSet rs1 = db.get(query);
			while (rs1.next()) {
				String[] spTitles2 = { "" + sott, rs1.getString("masp"),
						rs1.getString("tensp"), rs1.getString("DONVI"),
						rs1.getString("soluongchuyen") };
				

				for (int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);

					if (z == 4 || z == 5 || z == 7) {
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++;
			}
			document.add(tbSanPham);
			PdfPTable kt = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Trưởng phòng cung ứng:", font11));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			kt.addCell(cell);

			cell = new PdfPCell(new Paragraph("Người nhận :", font11));
			cell.setPaddingRight(10.0f);
			cell.setBorder(0);
			kt.addCell(cell);
			document.add(kt);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
	}

}
