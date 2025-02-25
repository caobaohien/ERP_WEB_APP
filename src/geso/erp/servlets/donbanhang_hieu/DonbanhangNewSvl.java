package geso.erp.servlets.donbanhang_hieu;

import geso.dms.center.util.Utility;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.donbanhang_hieu.IDonbanhang;
import geso.erp.beans.donbanhang_hieu.IThongtindonbanhang;
import geso.erp.beans.donbanhang_hieu.Isanpham;
import geso.erp.beans.donbanhang_hieu.imp.Sanpham;
import geso.erp.beans.donbanhang_hieu.imp.Thongtindonbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Donbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Thongtindonbanhang;
import geso.erp.db.sql.dbutils;
import geso.erp.servlets.donbanhang_hieu.DonbanhangListSvl;

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
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * Servlet implementation class NhacungcapNewSvl
 */
@WebServlet("/NhacungcapNewSvl")
public class DonbanhangNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonbanhangNewSvl() {
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

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);

		String Id = util.getId(querystring);
		
		String msg = "";
		
		IThongtindonbanhang bhn = new Thongtindonbanhang();
		bhn.setId(Id);
	//	System.out.println("ma id bang nao nhieu :"+Id);
		bhn.init();
		bhn.CreateRs();
		// Ham load gia tri can update
		if (querystring.indexOf("update") >= 0) {
			String id = request.getParameter("update");
			bhn.init();

		}

		session.setAttribute("obj", bhn);
		String nextJSP = "/TrainingGESO/pages/Erp/Donbanhangnew.jsp";
		response.sendRedirect(nextJSP);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IThongtindonbanhang obj = new Thongtindonbanhang();
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
		
		double tongtien = 0;
		tongtien = Double.parseDouble(request.getParameter("tongtien")
				.replaceAll(",", ""));
		obj.setTongtien(tongtien);
		

		String khachhang = util.antiSQLInspection(request
				.getParameter("khachhang"));
		obj.setKhachhang(khachhang == null ? "" : khachhang);
		String ngaychungtu = request.getParameter("ngaychungtu");
		obj.setNgaychungtu(ngaychungtu == null ? "" : ngaychungtu);
		String[] masp = request.getParameterValues("masp");
		String[] idsp = request.getParameterValues("idsp");

		String[] tensp = request.getParameterValues("tensp");
		String[] donvitinh = request.getParameterValues("dvt");
		String[] dongia = request.getParameterValues("dongia");
		String[] soluong = request.getParameterValues("soluong");
		String[] chietkhau=request.getParameterValues("chietkhau");
		String[] thanhtien = request.getParameterValues("thanhtien");
		// lấy được 1 mảng mã sản phẩm
		List<Isanpham> listsp = new ArrayList<Isanpham>();
		if (masp != null) {
			for (int i = 0; i < masp.length; i++) {
				if (masp[i].length() > 0) {
					Isanpham sp = new Sanpham();
					sp.setMasanpham(masp[i]);
					sp.setId(idsp[i]);
					sp.setTensanpham(tensp[i]);
					sp.setDonvitinh(donvitinh[i]);
					double sl = 0;
					try {
						sl = Double.parseDouble(soluong[i].replaceAll(",", ""));

					} catch (Exception er) {

					}
					sp.setSoluong(sl);

					double dg = 0;
					try {
						dg = Double.parseDouble(dongia[i].replaceAll(",", ""));

					} catch (Exception er) {

					}
					double ck=0;
					try {
						ck = Double.parseDouble(chietkhau[i].replaceAll(",", ""));

					} catch (Exception er) {
					sp.setChietkhau(ck);
					}
					sp.setDongia(dg);

					sp.setThanhtien((sl * dg)-(sl*dg*ck/100));
					listsp.add(sp);

				}
			}
		}

		obj.setListSp(listsp);

		System.out.println("actiong: " + action);
		if (action.equals("save")) {
			
			if (!obj.save()) {
				// lưu không thành công

				obj.CreateRs();
				session.setAttribute("obj", obj);
				response.sendRedirect("/TrainingGESO/pages/Erp/Donbanhangnew.jsp");
			} else {
				IDonbanhang dh = new Donbanhang();
				dh.init("");
				// khởi tạo ra 1 session
				// tất cả mọi chỗ gọi trang DonbanhangList.jsp thì cái tên đối
				// tượng, và đối tượng truyền vào phải giống nhau
				session.setAttribute("obj", dh);
				String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList.jsp";
				response.sendRedirect(nextJSP);
			}

		}else if(action.equals("printexcel")){
			//System.out.println("Ma id luc nay bao nhieu :"+id);
	    	response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=DonMuaHang.xls");
	    	if(!CreateExcel(out,request, response,id)){
	    		response.setContentType("text/html");
			    PrintWriter writer = new PrintWriter(out);
			    writer.print("Lỗi xuất excel");
			    writer.close();
	    	}
		else if (action.equals("savekhachhang")) {
			// tao moi khach hang
			//System.out.println("da vao duoc roi");	
			obj.savekhachhang();
			String Idkh = obj.getIdkhachhang();
			obj = new Thongtindonbanhang();
			obj.setUserId(userId);
			obj.setIdkhachhang(Idkh);
			obj.CreateRs();
			session.setAttribute("obj", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/Donbanhangnew.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
		else {
			obj.CreateRs();
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/Donbanhangnew.jsp");
		//Viet ham tao khach hang moi	
		}
		}
	}
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
		NumberFormat formatter = new DecimalFormat("#,###,###");

		fstream = new FileInputStream(getServletContext().getInitParameter("pathexcel") + "\\donhangexcel.xls");
				
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);

		
		isFillData = FillData(workbook,id);
   
		if (!isFillData)
		{
			if(fstream != null)
				fstream.close();
			return false;
		}
		
		workbook.save(out);
		fstream.close();
		return true;
	}
	
	private boolean FillData(Workbook workbook,String id){
		
		dbutils db = null;
		try{
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
		cell.setValue("Chi tiết hóa đơn bán hàng ");
		font.setColor(Color.GREEN);
		font.setBold(true);
		
		style = cell.getStyle();
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        style.setColor(Color.BLUE);
		cell = cells.getCell("B5");
		cell.setValue("Số thứ tự ");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("C5");
		cell.setValue("Id mua hàng");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("D5");
		cell.setValue("Mã mua hàng");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("E5");
		cell.setValue("Tên sản phẩm");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("F5");
		cell.setValue("Đơn vị tính");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		
		cell = cells.getCell("G5");
		cell.setValue("Số lượng");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("H5");
		cell.setValue("Đơn giá");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		cell = cells.getCell("I5");
		cell.setValue("Thành tiền");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        style.setColor(null);
		
		String query="";
		query="select bh.NGAYCHUNGTU as ngaychungtu,kh.TEN as tenkhachhang,bh.TONGTIEN as tongtien,"+ 
				"	dhsp.soluong as soluong ,dhsp.dongia as dongian,dhsp.thanhtien as thanhtien," +
				"	sp.pk_seq as id,sp.ma as madonhang,sp.ten as tensanpham, isnull(dv.ten,'') as donvi"+ 
				"	from DONHANG_SANPHAM  dhsp left join sanpham sp on sp.PK_SEQ=dhsp.SANPHAM_FK"+ 
				"	left join donvi dv on dv.pk_Seq=sp.DONVI_FK "+
				"	left join BANHANG bh on bh.PK_SEQ=dhsp.DONHANG_FK"+
				"	left join KHACHHANG kh on kh.PK_SEQ=bh.KHACHHANG_FK"+
				"	where dhsp.DONHANG_Fk= "+id ;
       // System.out.println("Cau query :" +query);
		ResultSet rs = db.get(query);
	
		String khachhang="";
		String ngaychungtu="";
		int sott = 1;
		double tongtien = 0;
		while (rs.next()) {
			String[]spTitle	={"B","C","D","E","F","G","H","I"};
			String[] spTitles2 = { "" + sott,rs.getString("id"),rs.getString("madonhang"),
					rs.getString("tensanpham"),rs.getString("donvi"),
					rs.getString("soluong"),rs.getString("dongian"),
					formatter.format(rs.getDouble("thanhtien")) };
				khachhang=rs.getString("tenkhachhang");
				ngaychungtu=rs.getString("NGAYCHUNGTU");
				tongtien = tongtien + Math.round(rs.getDouble("thanhtien"));
				for (int z = 0; z < spTitles2.length; z++) {
				cell=cells.getCell(spTitle[z]+Integer.toString(5+sott));
				cell.setValue(""+spTitles2[z]+"");
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
		//System.out.print("Khach hang+ngay chung tu :"+khachhang);
		//System.out.print("Khach hang+ngay chung tu :"+ngaychungtu);
		
		cell = cells.getCell("B3");
		font.setBold(true);
		font.setColor(Color.BLACK);
		cell.setStyle(style);
		cell.setStyle(style);
		style.setFont(font);
		cell.setValue("Ngày chứng từ");
		cell.setStyle(style);
		style.setFont(font);
		cell = cells.getCell("D3");
		cell.setStyle(style);
		style.setFont(font);
		cell.setValue(""+ngaychungtu+"");
		cell.setStyle(style);
		style.setFont(font);
		cell = cells.getCell("B4");
		cell.setStyle(style);
		style.setFont(font);
		cell.setValue("Tên khách hàng");
		cell.setStyle(style);
		style.setFont(font);
		cell = cells.getCell("D4");
		cell.setValue(""+khachhang+"");
		cell.setStyle(style);
		style.setFont(font);
		
		cells.merge(5+sott, 1, 5+sott, 7);
		cell = cells.getCell("B"+Integer.toString(6+sott));
		cell.setValue("Tổng tiền");
		style.setFont(font);
		font.setBold(true);
		font.setColor(Color.GREEN);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        cell = cells.getCell("I"+Integer.toString(6+sott));
		cell.setValue(""+tongtien+"");
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
		
		return true;
		} catch ( Exception ex){
			ex.printStackTrace();
			return false;
		} finally{
			db.shutDown();
		}
	}
	
}
