package geso.erp.servlets.reports;

import geso.erp.db.sql.dbutils;
import geso.erp.servlets.reports.ReportAPI;
import geso.dms.distributor.util.Utility;
import geso.erp.beans.baocao.IBaocao;
import geso.erp.beans.baocao.imp.Baocao;
import geso.erp.beans.report.Ireport;
import geso.erp.beans.report.imp.Report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class ErpBCDoanhSoTheoSPSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ErpBCDoanhSoTheoSPSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Ireport obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		obj = new Report();
		obj.setuserId(userId);
		String loaiBaoCao = "0";
		session.setAttribute("obj", obj);
		session.setAttribute("loaiBaoCao", loaiBaoCao);
		String nextJSP = "/TrainingGESO/pages/Erp/ErpBCDoanhSoTheoSP.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Ireport obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		String userTen = (String) session.getAttribute("userTen");
		String userId = request.getParameter("userId");

		obj = new Report();
		obj.setuserId(userId);
		obj.setuserTen(userTen);

		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		//Co 2 loai bao cao la sell in va sell out
		String loaiBaoCao = request.getParameter("loaiBaoCao");
		loaiBaoCao = loaiBaoCao == null ? "" : loaiBaoCao;

		String action = request.getParameter("action");
		System.out.println("Action nhan duoc: " + action);

		response.setContentType("application/xlsm");
		//Dat ten file cho moi loai bao cao
		String fileName = "";
		fileName = loaiBaoCao.equals("0") ? "BCDoanhSoNhaPhanPhoiTheoSP.xlsm": "BCDoanhSoKhachHangTheoSP.xlsm";
		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
		
		OutputStream out = response.getOutputStream();
		try {
			if (!CreateExcel(out, obj, "", loaiBaoCao)) {
				throw new Exception();
			} else {
				obj.setMsg("Tạo thông báo thành công");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			String msg = "Không thể tạo báo cáo\n";
			if (tungay.equals("")) {
				msg += "Xin chọn từ ngày \n";
			}
			if (denngay.equals("")) {
				msg += "Xin chọn đến ngày\n";
			}
			obj.setMsg(msg);
			e.printStackTrace();
			session.setAttribute("obj", obj);
			session.setAttribute("loaiBaoCao", loaiBaoCao);
			String nextJSP = "/TrainingGESO/pages/Erp/ErpBCDoanhSoTheoSP.jsp";
			response.sendRedirect(nextJSP);
			return;
			
		} /*finally {
			out.close();
		}*/

	}
	
	private boolean CreateExcel(OutputStream out, Ireport obj,
			String condition, String loaiBaoCao) throws Exception {
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		// copy nguyen cai template da co san trong he thong
		fstream = new FileInputStream(getServletContext().getInitParameter("path")+ "/ErpBCDoanhSoTheoSP.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		CreateStaticHeader(workbook, obj);
		
		if (!CreateStaticData(workbook, obj, condition, loaiBaoCao)) {
			if(fstream != null)
				fstream.close();
			return false;
		}
		
		workbook.save(out); 
		fstream.close();
		return true;
	}
	
	private void CreateStaticHeader(Workbook workbook, Ireport obj) {
		String dateFrom = obj.gettungay();
		String dateTo = obj.getdenngay();
		String UserName = obj.getuserTen();

		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		worksheet.setName("Sheet1");

		Cells cells = worksheet.getCells();

		Style style;
		Font font = new Font();
		font.setColor(Color.RED);// mau chu
		font.setSize(16);// size chu
		font.setBold(true);

		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		style = cell.getStyle();
		style.setFont(font);
		style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A5");
		ReportAPI.getCellStyle(cell, Color.BLACK, false, 13, "Từ ngày: "
				+ getFormatDate(dateFrom) + " đến ngày: "
				+ getFormatDate(dateTo));

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A6");
		ReportAPI.getCellStyle(cell, Color.BLACK, false, 13, "Ngày báo cáo: "
				+ getFormatDate(ReportAPI.NOW("yyyy-MM-dd")));
	}
	
	private boolean CreateStaticData(Workbook workbook, Ireport obj,
			String condition, String loaiBaoCao) throws Exception {
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		String[] param = new String[8];
		param[0] = obj.gettungay().equals("") ? null : obj.gettungay();
		param[1] = obj.getdenngay().equals("") ? null : obj.getdenngay();
		param[2] = obj.getuserId().equals("") ? null : obj.getuserId();
		// Nam truoc
		int nam = Integer.parseInt(param[0].substring(0, 4)) - 1;
		// tu ngay ki truoc
		param[4] = nam + param[0].substring(4);
		// den ngay ki truoc
		param[5] = nam + param[1].substring(4);
		String querySellIn = "SELECT ISNULL(KYNAY.SANPHAM_FK, ISNULL(KYTRUOC.SANPHAM_FK, NAMTRUOC.SANPHAM_FK)) AS SANPHAM_FK," +
				" SP.TEN AS TENSANPHAM, DV.DIENGIAI AS DONVITINH," +
				" ISNULL(KYNAY.THANHTIENKYNAY, 0) AS THANHTIENKYNAY, " +
				" ISNULL(KYTRUOC.THANHTIENKYTRUOC, 0) AS THANHTIENKYTRUOC," +
				" ISNULL(NAMTRUOC.THANHTIENNAMTRUOC, 0) AS THANHTIENNAMTRUOC" +
				" FROM (" +
				" SELECT SANPHAM_FK, SUM(THANHTIENKYNAY) AS THANHTIENKYNAY FROM (" +
				" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG * DHSP.DONGIA) AS THANHTIENKYNAY" +
				" FROM ERP_DONDATHANGNPP DH" +
				" INNER JOIN ERP_DONDATHANGNPP_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONDATHANG_FK " +
				" WHERE DH.TRANGTHAI != 3 AND NGAYDONHANG BETWEEN '"+param[0]+"' AND '"+param[1]+"'" +
				" GROUP BY DHSP.SANPHAM_FK" +
				" UNION ALL" +
				" SELECT SANPHAM_FK, (-1)*SUM(SPTL.SOLUONG * SPTL.DONGIA) AS THANHTIENKYNAY" +
				" FROM ERP_HANGTRALAINPP DH" +
				" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DH.PK_SEQ = SPTL.HANGTRALAI_FK " +
				" WHERE DH.TRANGTHAI=1 AND DH.DOITUONG=0  AND DH.NGAYTRA BETWEEN '"+param[0]+"' AND '"+param[1]+"'" +
				" GROUP BY SPTL.SANPHAM_FK" +
				" )A GROUP BY SANPHAM_FK" +
				" ) KYNAY" +
				" FULL OUTER JOIN (" +
				" SELECT SANPHAM_FK, SUM(THANHTIENKYTRUOC) AS THANHTIENKYTRUOC FROM (" +
				" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG * DHSP.DONGIA) AS THANHTIENKYTRUOC" +
				" FROM ERP_DONDATHANGNPP DH" +
				" INNER JOIN ERP_DONDATHANGNPP_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONDATHANG_FK " +
				" WHERE DH.TRANGTHAI != 3 AND NGAYDONHANG BETWEEN '"+param[4]+"' AND '"+param[5]+"'" +
				" GROUP BY DHSP.SANPHAM_FK" +
				" UNION ALL" +
				" SELECT SANPHAM_FK AS MASANPHAM, (-1)*SUM(SPTL.SOLUONG * SPTL.DONGIA) AS THANHTIENKYTRUOC" +
				" FROM ERP_HANGTRALAINPP DH" +
				" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DH.PK_SEQ = SPTL.HANGTRALAI_FK " +
				" WHERE DH.TRANGTHAI = 1 AND DH.DOITUONG=0  AND DH.NGAYTRA BETWEEN '"+param[4]+"' AND '"+param[5]+"'" +
				" GROUP BY SPTL.SANPHAM_FK" +
				" ) B GROUP BY SANPHAM_FK" +
				" ) KYTRUOC ON KYNAY.SANPHAM_FK = KYTRUOC.SANPHAM_FK" +
				" FULL OUTER JOIN (" +
				" SELECT SANPHAM_FK, SUM(THANHTIENNAMTRUOC) AS THANHTIENNAMTRUOC FROM (" +
				" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG * DHSP.DONGIA) AS THANHTIENNAMTRUOC" +
				" FROM ERP_DONDATHANGNPP DH" +
				" INNER JOIN ERP_DONDATHANGNPP_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONDATHANG_FK " +
				" WHERE DH.TRANGTHAI != 3 AND SUBSTRING(NGAYDONHANG, 1, 4) = '"+nam+"'" +
				" GROUP BY DHSP.SANPHAM_FK" +
				" UNION ALL" +
				" SELECT SANPHAM_FK AS MASANPHAM, (-1)*SUM(SPTL.SOLUONG * SPTL.DONGIA) AS THANHTIENNAMTRUOC" +
				" FROM ERP_HANGTRALAINPP DH" +
				" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DH.PK_SEQ = SPTL.HANGTRALAI_FK " +
				" WHERE DH.TRANGTHAI=1 AND DH.DOITUONG=0  AND SUBSTRING(DH.ngaytra, 1, 4) = '"+nam+"'" +
				" GROUP BY SPTL.SANPHAM_FK" +
				" ) C GROUP BY SANPHAM_FK" +
				" ) NAMTRUOC ON NAMTRUOC.SANPHAM_FK = ISNULL(KYNAY.SANPHAM_FK,KYTRUOC.SANPHAM_FK)" +
				" LEFT JOIN SANPHAM SP ON SP.PK_SEQ =  ISNULL(KYNAY.SANPHAM_FK,ISNULL(KYTRUOC.SANPHAM_FK,NAMTRUOC.SANPHAM_FK))" +
				" LEFT JOIN DONVIDOLUONG DV ON DV.PK_SEQ=SP.DVDL_FK" +
				" ORDER BY SANPHAM_FK";
		
		String querySellOut = "SELECT ISNULL(KYNAY.SANPHAM_FK, ISNULL(KYTRUOC.SANPHAM_FK, NAMTRUOC.SANPHAM_FK)) AS SANPHAM_FK," +
									" SP.TEN AS TENSANPHAM, DV.DIENGIAI AS DONVITINH," +
									" ISNULL(KYNAY.THANHTIENKYNAY, 0) AS THANHTIENKYNAY, " +
									" ISNULL(KYTRUOC.THANHTIENKYTRUOC, 0) AS THANHTIENKYTRUOC," +
									" ISNULL(NAMTRUOC.THANHTIENNAMTRUOC, 0) AS THANHTIENNAMTRUOC" +
							" FROM (" +
									" SELECT SANPHAM_FK, SUM(THANHTIENKYNAY) AS THANHTIENKYNAY " +
									"FROM (" +
										" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG*DHSP.GIAMUA) AS THANHTIENKYNAY" +
										" FROM DONHANG DH" +
										" INNER JOIN DONHANG_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONHANG_FK" +
										" WHERE DH.TRANGTHAI != 2 AND DH.NGAYNHAP BETWEEN '"+param[0]+"' AND '"+param[1]+"'" +
										" GROUP BY DHSP.SANPHAM_FK " +
										" UNION ALL" +
										" SELECT SANPHAM_FK, SUM(SPTL.SOLUONG*SPTL.DONGIA) AS THANHTIENKYNAY" +
										" FROM ERP_HANGTRALAINPP DHTL" +
										" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DHTL.PK_SEQ = SPTL.HANGTRALAI_FK " +
										" WHERE DHTL.DOITUONG=1 AND DHTL.TRANGTHAI != 2 AND DHTL.NGAYTRA BETWEEN '"+param[0]+"' AND '"+param[1]+"'" +
										" GROUP BY SPTL.SANPHAM_FK " +
									" )A GROUP BY SANPHAM_FK" +
							" ) KYNAY" +
							" FULL OUTER JOIN (" +
									" SELECT SANPHAM_FK, SUM(THANHTIENKYTRUOC) AS THANHTIENKYTRUOC FROM (" +
										" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG*DHSP.GIAMUA) AS THANHTIENKYTRUOC" +
										" FROM DONHANG DH" +
										" INNER JOIN DONHANG_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONHANG_FK" +
										" WHERE DH.TRANGTHAI != 2 AND DH.NGAYNHAP BETWEEN '"+param[4]+"' AND '"+param[5]+"'" +
										" GROUP BY DHSP.SANPHAM_FK " +
										" UNION ALL" +
										" SELECT SANPHAM_FK, SUM(SPTL.SOLUONG*SPTL.DONGIA) AS THANHTIENKYTRUOC" +
										" FROM ERP_HANGTRALAINPP DHTL" +
										" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DHTL.PK_SEQ = SPTL.HANGTRALAI_FK " +
										" WHERE DHTL.DOITUONG=1 AND DHTL.TRANGTHAI != 2 AND DHTL.NGAYTRA BETWEEN '"+param[4]+"' AND '"+param[5]+"'" +
										" GROUP BY SPTL.SANPHAM_FK 	" +
									" ) B GROUP BY SANPHAM_FK" +
							" ) KYTRUOC ON KYNAY.SANPHAM_FK = KYTRUOC.SANPHAM_FK" +
							" FULL OUTER JOIN (" +
								" SELECT SANPHAM_FK, SUM(THANHTIENNAMTRUOC) AS THANHTIENNAMTRUOC FROM (" +
									" SELECT SANPHAM_FK, SUM(DHSP.SOLUONG*DHSP.GIAMUA) AS THANHTIENNAMTRUOC" +
									" FROM DONHANG DH" +
									" INNER JOIN DONHANG_SANPHAM DHSP ON DH.PK_SEQ = DHSP.DONHANG_FK" +
									" WHERE DH.TRANGTHAI != 2 AND SUBSTRING(DH.NGAYNHAP, 1, 4) = '"+nam+"'" +
									" GROUP BY DHSP.SANPHAM_FK " +
									" UNION ALL" +
									" SELECT SANPHAM_FK, SUM(SPTL.SOLUONG*SPTL.DONGIA) AS THANHTIENNAMTRUOC" +
									" FROM ERP_HANGTRALAINPP DHTL" +
									" INNER JOIN ERP_HANGTRALAINPP_SANPHAM SPTL ON DHTL.PK_SEQ = SPTL.HANGTRALAI_FK " +
									" WHERE DHTL.DOITUONG=1 AND DHTL.TRANGTHAI != 2 AND SUBSTRING(DHTL.NGAYTRA, 1, 4) = '"+nam+"'" +
									" GROUP BY SPTL.SANPHAM_FK 	" +
									" ) C GROUP BY SANPHAM_FK" +
							" ) NAMTRUOC ON NAMTRUOC.SANPHAM_FK = ISNULL(KYNAY.SANPHAM_FK,KYTRUOC.SANPHAM_FK)" +
							" LEFT JOIN SANPHAM SP ON SP.PK_SEQ =  ISNULL(KYNAY.SANPHAM_FK,ISNULL(KYTRUOC.SANPHAM_FK,NAMTRUOC.SANPHAM_FK))" +
							" LEFT JOIN DONVIDOLUONG DV ON DV.PK_SEQ=SP.DVDL_FK" +
							" ORDER BY SANPHAM_FK";
		
		ResultSet rs = loaiBaoCao.equals("0") ? db.get(querySellIn) : db
				.get(querySellOut);

		int i = 9;

		try {
			cells.setColumnWidth(0, 15.0f);
			cells.setColumnWidth(1, 35.0f);
			cells.setColumnWidth(2, 12.0f);
			cells.setColumnWidth(3, 20.0f);
			cells.setColumnWidth(4, 20.0f);
			cells.setColumnWidth(5, 15.0f);
			cells.setColumnWidth(6, 20.0f);
			cells.setColumnWidth(7, 15.0f);
			cells.setColumnWidth(8, 15.0f);
			Cell cell = null;
			double tongKiNay = 0, tongKiTruoc = 0, tongNamTruoc = 0;
			if (rs != null) {
				while (rs.next()) {

					cell = cells.getCell("A" + Integer.toString(i));
					cell.setValue(rs.getString("SANPHAM_FK"));
					cell = cells.getCell("B" + Integer.toString(i));
					cell.setValue(rs.getString("TENSANPHAM"));
					cell = cells.getCell("C" + Integer.toString(i));
					cell.setValue(rs.getString("DONVITINH"));
					double thanhTienKiNay = rs.getDouble("THANHTIENKYNAY");
					double thanhTienKiTruoc = rs.getDouble("THANHTIENKYTRUOC");
					double thanhTienNamTruoc = rs
							.getDouble("THANHTIENNAMTRUOC");
					cell = cells.getCell("D" + Integer.toString(i));
					cell.setValue(thanhTienKiNay);
					cell = cells.getCell("E" + Integer.toString(i));
					cell.setValue(thanhTienKiTruoc);
					tongKiNay += thanhTienKiNay;
					tongKiTruoc += thanhTienKiTruoc;
					tongNamTruoc += thanhTienNamTruoc;
					double phanTramKiTruoc = 0, phanTramNamTruoc = 0;
					
					phanTramKiTruoc = thanhTienKiTruoc == 0 ? 0 : (thanhTienKiNay / thanhTienKiTruoc) * 100;
					phanTramNamTruoc = thanhTienNamTruoc == 0 ? 0 : (thanhTienKiNay / thanhTienNamTruoc) * 100;
					

					cell = cells.getCell("F" + Integer.toString(i));
					if (phanTramKiTruoc < 0) {
						cell.setValue("");
					} else {
						cell.setValue(phanTramKiTruoc);
					}
					cell = cells.getCell("H" + Integer.toString(i));
					if (phanTramNamTruoc < 0) {
						cell.setValue("");
					} else {
						cell.setValue(phanTramNamTruoc);
					}

					cell = cells.getCell("G" + Integer.toString(i));
					cell.setValue(thanhTienNamTruoc);
					i++;
				}
				int totalRow = i + 1;
				cell = cells.getCell("A" + totalRow);
				cell.setValue("TỔNG CỘNG");
				cell = cells.getCell("D" + totalRow);
				cell.setValue(tongKiNay);
				cell = cells.getCell("E" + totalRow);
				cell.setValue(tongKiTruoc);
				cell = cells.getCell("G" + totalRow);
				cell.setValue(tongNamTruoc);
			}
			if (rs != null)
				rs.close();
			if (db != null)
				db.shutDown();
			if (i == 2) {
				obj.setMsg("Khong co bao cao trong thoi gian nay...");
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			db.shutDown();
		}
			
		return true;
	}
	// Ham dinh dang ngay thang nam dd/mm/yy
	public String getFormatDate(String dateInString) {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/mm/yyyy");
		Date date = null;
		try {
			date = dateFormat1.parse(dateInString);
		} catch (ParseException e) {
			date = null;
		}
		return dateFormat2.format(date);
	}
}
