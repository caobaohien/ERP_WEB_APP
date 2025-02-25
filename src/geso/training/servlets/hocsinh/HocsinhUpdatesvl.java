package geso.training.servlets.hocsinh;


import geso.dms.center.util.Utility;
import geso.training.bean.hocsinh.IHocSinh;
import geso.training.bean.hocsinh.IHocSinhList;
import geso.training.bean.hocsinh.imp.HocSinh;
import geso.training.bean.hocsinh.imp.HocSinhList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HocsinhUpdatesvl
 */
@WebServlet("/HocsinhUpdatesvl")
public class HocsinhUpdatesvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HocsinhUpdatesvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility utility = new Utility();
		String querystring= request.getQueryString();
		String mahs = utility.getId(querystring);
		String action=utility.getAction(querystring);
		String userid=utility.getUserId(querystring);
		IHocSinh ob = new HocSinh();
		ob.setUserId(userid);
		ob.setMaHS(mahs);
		ob.creates();
		if(action.equals("update")){
			ob.init();
			session.setAttribute("hocsinhsua", ob);
			String nextjsp = "/TrainingGESO/pages/Erp/hocsinhsua.jsp";
			response.sendRedirect(nextjsp);
		}
		if(action.equals("delete")){
			if(!ob.Xoa()){
				System.out.println("xoa khong thanh cong");
				//truyen gia trị den trang view
			}else{
				String userId = (String) session.getAttribute("userId");
				String userName = (String) session.getAttribute("userTen");
				IHocSinhList obj = new HocSinhList();
				obj.setUserId(userId);
				obj.setUserName(userName);
				//lấy dữ liệu danh sách lớp
				obj.init();
				obj.creates();
				//chuyển thông tin ra trang view
				session.setAttribute("ob", obj);
				String nextJSP = "/TrainingGESO/pages/Erp/Hocsinhlist.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//Khởi tạo session
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userTen");
		IHocSinh hocsinh=new HocSinh();
		hocsinh.setUserId(userId);
		hocsinh.setUserName(userName);
		String maLop = request.getParameter("lop");
		String tenHS = request.getParameter("tenhocsinh");
		String mahocsinh = request.getParameter("mahocsinh");
		hocsinh.setMaLop(maLop);
		hocsinh.setTenHS(tenHS);
		hocsinh.setMaHS(mahocsinh);
		//lấy giá trị action
		String action = request.getParameter("action");
		if (action == null){
			action = "";
		}
		if(action.equals("luumoi"))
		{
		//ham lưu
		//nếu lưu không thanh công trả ve trang tạo mới
		if(!hocsinh.Save()){
		hocsinh.creates();
		//truyen gia trị den trang view
		session.setAttribute("hocsinh", hocsinh);
		String nextJSP = "/TrainingGESO/pages/Erp/hocsinhnew.jsp";
		response.sendRedirect(nextJSP);
		}
		//lưu thanh cong trả về trang tổng
		else{
		//Khởi tạo Lớp học sinh
		IHocSinhList ob = new HocSinhList();
		ob.setUserId(userId);
		ob.setUserName(userName);
		//lấy dữ liệu danh sách lớp
		ob.init();
		ob.creates();
		//chuyển thông tin ra trang view
		session.setAttribute("ob", ob);
		String nextJSP = "/TrainingGESO/pages/Erp/Hocsinhlist.jsp";
		response.sendRedirect(nextJSP);
		}
		}
		if(action.equals("luusua")){
			if(!hocsinh.Savesua()){
				hocsinh.creates();
				//truyen gia trị den trang view
				session.setAttribute("hocsinhsua", hocsinh);
				String nextJSP = "/TrainingGESO/pages/Erp/hocsinhsua.jsp";
				response.sendRedirect(nextJSP);
			}else{
				IHocSinhList ob = new HocSinhList();
				ob.setUserId(userId);
				ob.setUserName(userName);
				//lấy dữ liệu danh sách lớp
				ob.init();
				ob.creates();
				//chuyển thông tin ra trang view
				session.setAttribute("ob", ob);
				String nextJSP = "/TrainingGESO/pages/Erp/Hocsinhlist.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}
}
