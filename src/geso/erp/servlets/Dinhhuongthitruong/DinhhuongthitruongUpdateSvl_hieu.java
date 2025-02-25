package geso.erp.servlets.Dinhhuongthitruong;
import geso.dms.center.util.Utility;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuong_hoatdong;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongList_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongUpdate_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.imp.Dinhhuonghoatdong;
import geso.erp.beans.Dinhhuongthitruong_hieu.imp.DinhhuongthitruongList_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.imp.DinhhuongthitruongUpdate_hieu;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NhacungcapNewSvl
 */
@WebServlet("/DinhhuongthitruongUpdateSvl_hieu")
public class DinhhuongthitruongUpdateSvl_hieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinhhuongthitruongUpdateSvl_hieu() {
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
		IDinhhuongthitruongUpdate_hieu cku = new DinhhuongthitruongUpdate_hieu();
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
			String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongUpdate_hieu.jsp";
			response.sendRedirect(nextJSP);
		}
		else if (querystring.indexOf("display") >= 0) {
			String id = request.getParameter("display");
			session.setAttribute("action", "display");
			cku.setId(id);
			cku.init();
			session.setAttribute("obj", cku);
			String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongDisplay_hieu.jsp";
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

		IDinhhuongthitruongUpdate_hieu obj = new DinhhuongthitruongUpdate_hieu();
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
		String tinhtrang = util.antiSQLInspection(request
				.getParameter("tinhtrang"));
		int thang=0;
		
		 String temp = util.antiSQLInspection( request.getParameter("thang"));
		 
		 try{
			 thang=Integer.parseInt(temp);
			 
		 }catch(Exception er){}
		int nam=0;
			
		 String temp1 = util.antiSQLInspection( request.getParameter("nam"));
		 
		 try{
			 nam=Integer.parseInt(temp1);
			 
		 }catch(Exception er){}
		 
		 int thangdinhhuong=0;
			
		 String temp2 = util.antiSQLInspection( request.getParameter("thangdinhhuong"));
		 
		 try{
			 thangdinhhuong=Integer.parseInt(temp2);
			 
		 }catch(Exception er){}
		
		 
		 int quydinhhuong=0;
			
		 String temp3 = util.antiSQLInspection( request.getParameter("quydinhhuong"));
		 
		 try{
			 quydinhhuong=Integer.parseInt(temp3);
			 
		 }catch(Exception er){}
		obj.setTinhtrangthitruong(tinhtrang);
		obj.setThang(thang);
		obj.setNam(nam);
		obj.setNhandinhthoigiantheothang(thangdinhhuong);
		obj.setNhandinhthoigiantheoquy(quydinhhuong);

	
		String[] idhd = request.getParameterValues("idhd");
		String[] tenhd = request.getParameterValues("tenhd");
		String[] kinhphi = request.getParameterValues("kinhphi");
		String[] tungay = request.getParameterValues("tungay");
		String[] denngay = request.getParameterValues("denngay");
		String[] soluongkh = request.getParameterValues("slkh");
		String[] soluonghd = request.getParameterValues("slhd");
		String[] mota=request.getParameterValues("mota");

		
		List<IDinhhuong_hoatdong> listhd = new ArrayList<IDinhhuong_hoatdong>();
		if (tenhd != null) {
			for (int i = 0; i < tenhd.length; i++) {
				if (tenhd[i].length() > 0) {
					IDinhhuong_hoatdong hd = new Dinhhuonghoatdong();
					hd.setTenhoatdong(tenhd[i]);					
					hd.setTungay(tungay[i]);
					hd.setDenngay(denngay[i]);
					hd.setMota(mota[i]);
					float kp = 0;
					try {
						kp = Float.parseFloat(kinhphi[i].replaceAll(",",""));

					} catch (Exception er) {

					}
					hd.setKinhphi(kp);

					int slkh = 0;
					try {
						slkh = Integer.parseInt(soluongkh[i].replaceAll(
								",", ""));

					} catch (Exception er) {

					}
					hd.setSoluongkh(slkh);
					int slhd = 0;
					try {
						slhd = Integer.parseInt(soluonghd[i].replaceAll(
								",", ""));

					} catch (Exception er) {

					}
					hd.setSoluonghd(slhd);
					listhd.add(hd);

				}
			}
		}
		obj.setListkhhd(listhd);

		System.out.println("action: " + action);
		if (action.equals("save")){
			if (!obj.save()) {
				// lưu không thành công
				obj.CreateRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "save");
				response.sendRedirect("/TrainingGESO/pages/Erp/DinhhuongthitruongUpdate_hieu.jsp");
			} else {
				System.out.println("Size list la bao nhieu: " + listhd.size());
				IDinhhuongthitruongList_hieu ck = new DinhhuongthitruongList_hieu();
				ck.init("");
				session.setAttribute("obj", ck);
				String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongList_hieu.jsp";
				response.sendRedirect(nextJSP);
			}
		} else if (action.equals("update")) {

			if (!obj.update()) {
				obj.CreateRs();
				session.setAttribute("obj", obj);
				session.setAttribute("action", "update");
				response.sendRedirect("/TrainingGESO/pages/Erp/DinhhuongthitruongUpdate_hieu.jsp");
				return;
			} else {
				IDinhhuongthitruongList_hieu ck = new DinhhuongthitruongList_hieu();
				ck.init("");
				session.setAttribute("obj", ck);
				String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongList_hieu.jsp";
				response.sendRedirect(nextJSP);
				return;
			}
		}
		else {
			obj.CreateRs();
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DinhhuongthitruongUpdate_hieu.jsp");
			return;
		}

	}

	

}
