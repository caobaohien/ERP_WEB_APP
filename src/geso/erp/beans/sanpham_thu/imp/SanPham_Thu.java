package geso.erp.beans.sanpham_thu.imp;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.beans.sanpham_thu.ISanPham_Thu;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

public class SanPham_Thu implements ISanPham_Thu {
	
	private String userId;
	private String maSanPham;
	private String tenSanPham;
	private String maDoVi;
	private String tenDonVi;
	
	private String trangThai;
	private dbutils db;
	private ResultSet rsSanPham;
	private ResultSet rsDonVi;
	private String msg;
	
	private float giaBan;
	private float soLương;
	
	private String PK_SEQ;
	
	public Float getGiaBan() {
		return giaBan;
	}
	public String getPK_SEQ() {
		return PK_SEQ;
	}
	public void setPK_SEQ(String pK_SEQ) {
		PK_SEQ = pK_SEQ;
	}
	
	
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	public void setGiaBan(Float giaBan) {
		this.giaBan = giaBan;
	}
	public Float getSoLương() {
		return soLương;
	}
	public void setSoLương(Float soLương) {
		this.soLương = soLương;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getMaDoVi() {
		return maDoVi;
	}
	public void setMaDoVi(String maDoVi) {
		this.maDoVi = maDoVi;
	}
	public String getTenDonVi() {
		return tenDonVi;
	}
	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public ResultSet getRsSanPham() {
		return rsSanPham;
	}
	public void setRsSanPham(ResultSet rsSanPham) {
		this.rsSanPham = rsSanPham;
	}
	public ResultSet getRsDonVi() {
		String  query="select PK_SEQ as MADONVI,TEN AS TENDONVI  from DONVI";
		this.rsDonVi=this.db.get(query);
		return rsDonVi;
	}
	public void setRsDonVi(ResultSet rsDonVi) {
		this.rsDonVi = rsDonVi;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public SanPham_Thu(String pK_SEQ, String maSanPham, 
			String tenSanPham,String maDonViTinh, int soLuong, float giaBan) {
		super();
		PK_SEQ = pK_SEQ;
		this.maSanPham = maSanPham;
		this.maDoVi = maDonViTinh;
		this.tenSanPham = tenSanPham;
		this.soLương = soLuong;
		this.giaBan = giaBan;
	}
	
	
	
	public SanPham_Thu(String userId, String maSanPham, String tenSanPham,
			String maDoVi, String tenDonVi, String trangThai, dbutils db,
			ResultSet rsSanPham, ResultSet rsDonVi, String msg, Float giaBan,
			Float soLương) {
		super();
		//this.userId = userId;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.maDoVi = maDoVi;
		this.tenDonVi = tenDonVi;
		this.trangThai = trangThai;
		this.db = db;
		this.rsSanPham = rsSanPham;
		this.rsDonVi = rsDonVi;
		this.msg = msg;
		this.giaBan = giaBan;
		this.soLương = soLương;
	}
	
	
	
	public SanPham_Thu() {
		super();
		this.userId = "";
		this.maSanPham = "";
		this.tenSanPham = "";
		this.maDoVi = "";
		this.tenDonVi = "";
		this.trangThai = "";
		this.db = new dbutils();
		this.msg = "";
		this.giaBan = 0;
		this.soLương = 0;
	}
	
	//hàm init này lấy 1 ra 1sp gán cho obj
	@Override
	public void init(String s)
	{
		String query="";
		if(s.trim().length()>0){
			query=s;
		}
		else {
			try {
				query=	"SELECT SP.PK_SEQ AS MASANPHAM,"+
				"SP.TEN AS TENSANPHAM, "+ "SP.DONVI_FK AS MADONVI, "+
				"DV.TEN AS TENDONVI, SP.TRANGTHAI AS TRANGTHAI, "+
				"SP.SOLUONG AS SOLUONG, SP.GIABAN AS GIABAN "+
				"from SANPHAM SP LEFT JOIN DONVI DV ON SP.DONVI_FK=DV.PK_SEQ "+
				" where SP.PK_SEQ=" + this.maSanPham;
				ResultSet laysanpham =this.db.get(query);
				if(laysanpham.next()){
					this.maSanPham=laysanpham.getString("MASANPHAM");
					this.tenSanPham=laysanpham.getString("TENSANPHAM");
					this.tenDonVi=laysanpham.getString("TENDONVI");
					this.maDoVi=laysanpham.getString("MADONVI");
					this.soLương=laysanpham.getFloat("SOLUONG");
					this.giaBan=laysanpham.getFloat("GIABAN");
				}
				laysanpham.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		
		CreateDonVi();
	}
	
	
	@Override
	public void CreateDonVi(){
		String  query="select PK_SEQ as MADONVI,TEN AS TENDONVI  from DONVI";
		this.rsDonVi=this.db.get(query);
	}
	
	@Override
	public void DBclose()
	{
		this.db.shutDown();	
	}
	
  //hàm save	
	public boolean saveSanPham(){
		//xác định là update hay là insert bằng cách lấy thử trong ds ra 
		//nếu tồn tại là update không thì là insert
		//nếu số tiền và số lượng âm, trống dữ liệu thì báo lỗi ...
		try {
			if(this.maSanPham !="")
			{
				if(tenSanPham.equals("")||giaBan <=0 || soLương<=0 ||maDoVi.equals("")
						|| this.userId.length()==0){
					throw new Exception();
				}
			db.getConnection().setAutoCommit(false);
			//thực hiện update
			String query1="UPDATE SANPHAM SET "+
			" TEN=' "+ this.tenSanPham+ ",' "+
			" DONVI_FK=" +this.maDoVi+",' "+
			" SOLUONG=" +this.soLương+",' "+
			" GIABAN="+this.giaBan +",' "+
			" NGUOISUA="+this.userId+",' "+
			"NGAYSUA=SYSDATETIME(), "+
			" WHERE PK_SEQ=" +this.maSanPham;
			
			
			
			if (!db.update(query1)) {
				db.getConnection().rollback();
				this.msg = "Không thể thực hiện dòng lệnh ; " + query1
				+ "\n";
				return false;
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			
			}
			else
			{
				if(tenSanPham.equals("")||giaBan <=0 || soLương<=0 ||maDoVi.equals("")
						|| this.userId.length()==0){
					throw new Exception();
				}
				
				db.getConnection().setAutoCommit(false);
				String query2 = "INSERT INTO SANPHAM (TEN, NGUOITAO,NGAYTAO, DONVI_FK, TRANGTHAI,GIABAN, SOLUONG) " + 
						" VALUES " + "('" + this.tenSanPham
						+ "', " + "'" + this.userId + "', " + "SYSDATETIME(), "
						+ "'" + this.maDoVi + "', " + "'1', " 
						+ this.giaBan +"," +this.soLương+ " )";
				
				
				System.out.print("câu truy vấn insert:"+query2);
				
				
				if (!db.update(query2)) {
					db.getConnection().rollback();
					this.msg = "Không thể thực hiện dòng lệnh ; " + query2
							+ "\n";
					return false;
				}

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg += "Lỗi : " + e.getMessage() + "\n";
			e.printStackTrace();
			if (this.giaBan <= 0.0) {
				this.msg += "Tổng tiền không thể âm hoặc bằng không \n";
			}
			if (this.soLương <= 0.0) {
				this.msg += "Số lượng không thể âm hoặc bằng không \n";
			}
			if (this.maDoVi.equals("")) {
				this.msg += "Đơn vị không được thiếu \n";
			}
			if (this.tenSanPham.equals("")) {
				this.msg += "Tên sản phẩm không được thiếu \n";
			}
			if (this.userId.length() == 0) {
				this.msg += "Hết phiên làm việc. Hãy đăng nhập lại \n";
			}
			return false;
		}
		return true;
		}
		
		
		
		
  //hàm xóa
	@Override
	public boolean deleteSanPham(){
		
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE SANPHAM SET TRANGTHAI='0' WHERE PK_SEQ = " + this.maSanPham;
			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				this.msg = "Không thể cập nhật dòng lệnh: " + query;
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;
			
		} catch (Exception er) {
			db.update("rollback");
			er.printStackTrace();
			return false;
		}
	}
	
}
