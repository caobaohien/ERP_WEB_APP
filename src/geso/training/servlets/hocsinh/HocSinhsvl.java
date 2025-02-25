package geso.training.servlets.hocsinh;

import geso.training.bean.hocsinh.IHocSinhList;
import geso.training.bean.hocsinh.imp.HocSinhList;
import geso.training.bean.hocsinh.IHocSinh;
import geso.training.bean.hocsinh.imp.HocSinh;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HocSinhsvl
 */
@WebServlet("/HocSinhsvl")
public class HocSinhsvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HocSinhsvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// cau hinh UTF8
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// khoi tao session
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String userName = (String)session.getAttribute("userTen");

		IHocSinhList ob = new HocSinhList();
		ob.setUserId(userid);
		ob.setUserName(userName);

		//lay du lieu danh sach lop
		ob.creates();
		
		//lay gia tri action
		/*String action = request.getParameter("action");
		System.out.println("action get "+ action );*/
	
		//chuyen thong tin ra trang view
		session.setAttribute("ob", ob);
		String nextJSP = "/TrainingGESO/pages/Erp/Hocsinhlist.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//cau hinh UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		//khoi tao session
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String userName = (String)session.getAttribute("userTen");
		
		//lay gia tri action
		String action = request.getParameter("action");
		if(action==null){
			action = "";
		}
		/*if(action.equals("timkiem")){
			String MaLop = request.getParameter("lop");
			if(MaLop==null)
				MaLop="";
			System.out.println("action post "+ action +MaLop);
			
		}*/
		if(action.equals("taomoi")){
			IHocSinh hocsinh = new HocSinh();
			hocsinh.setUserId(userid);
			//initial value first
			hocsinh.creates();
			//chuyen thong tin ra trang view;
			session.setAttribute("hocsinh", hocsinh);
			String nextjsp = "/TrainingGESO/pages/Erp/hocsinhnew.jsp";
			response.sendRedirect(nextjsp);
		/*}else if(action.equals("sua")){
			IHocSinhSua hocsinh = new HocSinhSua();
			hocsinh.setUserId(userid);
			//initial value first
			hocsinh.creates();
			//chuyen thong tin ra trang view;
			session.setAttribute("hocsinhsua", hocsinh);
			String nextjsp="/TrainingGESO/pages/Erp/hocsinhsua.jsp";
			response.sendRedirect(nextjsp);*/
		}else{
		//lay ma lop
		String malop = request.getParameter("lop");
		IHocSinhList ob = new HocSinhList();
		ob.setUserId(userid);
		ob.setUserName(userName);
		System.out.println("bien ma lop "+malop);
		ob.setMaLop(malop);
		ob.init();
		ob.creates();
		//chuyen thong tin ra trang view;
		session.setAttribute("ob", ob);
		String nextjsp = "/TrainingGESO/pages/Erp/Hocsinhlist.jsp";
		response.sendRedirect(nextjsp);
		}
	}
}
