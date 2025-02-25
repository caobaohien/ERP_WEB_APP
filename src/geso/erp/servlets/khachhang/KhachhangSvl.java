package geso.erp.servlets.khachhang;
import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.beans.khachhang.imp.KhachhangList;
import geso.dms.center.util.Utility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class KhachhangSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public KhachhangSvl() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IKhachhangList obj;
		request.setCharacterEncoding("UTF-8");
		
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    
	    String Id = util.getId(querystring);
	    
	    String msg = "";
	    if (action.equals("delete"))
	    {	
	    	/*IBanhang bh=new Banhang(dmhId);
	    	bh.Delete_dh();*/
	    }
	    else
	    { 
	    		if(action.equals("chot"))
		    	{
		    		 
		    	}
	    		
	    	 
	    }

	    
	    if( action.equals("update")){
	    	 obj = new KhachhangList();
	    	 obj.setUserId(userId);
	    	 obj.setId(Id);
	    	 obj.init_Kh();
	    	 
	    }else{

		    obj = new KhachhangList();
		    obj.setUserId(userId);
		    
		    if(msg.length() > 0) obj.setmsg(msg);
		    
		    obj.init("");
	    }  
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/KhachhangList.jsp";
		response.sendRedirect(nextJSP);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IKhachhangList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	/*IBanhang dmhBean = new Banhang("");
	    	dmhBean.setUserId(userId);
	    	dmhBean.createRs();
    		 
	    	session.setAttribute("dmhBean", dmhBean);
    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
    		response.sendRedirect(nextJSP);*/
	    }
	     
	    	else if(action.equals("savenew")) 
	    	{ 
	    		String manew=request.getParameter("manew");
	    		String tennew=request.getParameter("tennew");
	    		String dienthoainew=request.getParameter("dienthoainew");
	    		String diachinew=request.getParameter("diachinew");
	    		String idnew=request.getParameter("idnew");
	    		String checktrangthai=request.getParameter("checktrangthai");
	    		if(checktrangthai==null){
	    			checktrangthai="0";
	    		}
	    		
	    		
	    		obj=new KhachhangList();
	    		
	    		obj.setUserId(userId);
	    		obj.setTenNew(tennew);
	    		obj.setMaNew(manew);
	    		obj.setDienthoaiNew(dienthoainew);
	    		obj.setId(idnew);
	    		obj.setTrangthaiNew(checktrangthai);
	    		obj.setDiachiNew(diachinew);
	    		if(!obj.save()){
	    			
	    		}else{
	    			obj.setTenNew("");
		    		obj.setMaNew("");
		    		obj.setDienthoaiNew("");
		    		obj.setDiachiNew("");
		    		obj.setId("");
	    		}
	    			
	    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));


		    	obj.init("");

		    	session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/KhachhangList.jsp");	
		    	
	    		
	    	}else{
	    		obj = new KhachhangList();
	    		obj.setUserId(userId);
	    		
		    	
	    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

	    		String search = getSearchQuery(request, obj);
		    	
		    	obj.init(search);

		    	session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/KhachhangList.jsp");	
	    	}
	
	     
	    
	}
  
	private String getSearchQuery(HttpServletRequest request, IKhachhangList obj)
	{
		Utility util=new Utility();
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		

		String ma =util.antiSQLInspection(request.getParameter("ma"));
		if(ma == null)
			ma = "";
		obj.setMa(ma);
		
		String ten = request.getParameter("ten");
		if(ten == null)
			ten = "";
		obj.setTen(ten);
		
		
		String dienthoai = request.getParameter("dienthoai");
		if(dienthoai == null)
			dienthoai = "";
		obj.setDienthoai(dienthoai);
		
		String diachi = request.getParameter("diachi");
		if(diachi == null)
			diachi = "";
		obj.setDiachi(diachi);
		
		String query =   "";
		  	
		query=  " SELECT  KH.PK_SEQ AS SOCHUNGTU,KH.DIENTHOAI,KH.MA ,ISNULL(KH.DIACHI,'') AS DIACHI " +
			    " ,KH.NGAYTAO,ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO , ISNULL(KH.TRANGTHAI,'') AS TRANGTHAI  "+
				" FROM "+
				" KHACHHANG KH   "+
				" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=KH.NGUOITAO " +
				" where  " +
				" KH.shop_fk = (select shop_fk from nhanvien where pk_seq="+obj.getUserId()+")";
		if(ma.length()>0){
			query+=" AND KH.timkiem LIKE '%"+util.replaceAEIOU(obj.getMa())+"%'";
		}
		 
		if(trangthai.length()>0){
			query+=" AND KH.TRANGTHAI LIKE '"+trangthai+"'";
		}
		return query;
		
	}
 
}
