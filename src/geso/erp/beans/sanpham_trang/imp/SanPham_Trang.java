package geso.erp.beans.sanpham_trang.imp;
import geso.erp.beans.sanpham_trang.ISanPham_Trang;
import geso.erp.db.sql.dbutils;


import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SanPham_Trang implements ISanPham_Trang{
	//dùng cho tạo mới
	private String tenSanPham;
	private String donViTinh;
	private float soLuong;
	private float giaBan;
	
	private String maSanPham;
	private String trangThai;
	private String ngayTao;
	private String ngaySua;
	private String nguoiTao;
	private String nguoiSua;
	private String timKiem;
	private float chietKhau;
	
	private String id;
	private dbutils db;
	private String msg;
	private ResultSet rsDonViTinh;
	private ResultSet rsDataList;
	
	public SanPham_Trang(String tenSanPham, String donViTinh, float soLuong,
			float giaBan, String maSanPham, String trangThai, String ngayTao,
			String ngaySua, String nguoiTao, String nguoiSua, String timKiem,
			float chietKhau, String id, dbutils db, String msg,
			ResultSet rsDataList) {
		super();
		this.tenSanPham = tenSanPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.maSanPham = maSanPham;
		this.trangThai = trangThai;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.nguoiTao = nguoiTao;
		this.nguoiSua = nguoiSua;
		this.timKiem = timKiem;
		this.chietKhau = chietKhau;
		this.id = id;
		this.db = db;
		this.msg = msg;
		this.rsDataList = rsDataList;
	}
	
	public SanPham_Trang() {
		this.tenSanPham = "";
		this.donViTinh = "";
		this.soLuong = 0;
		this.giaBan = 0;
		this.maSanPham = "";
		this.trangThai = "";
		this.ngayTao = "";
		this.ngaySua = "";
		this.nguoiTao = "";
		this.nguoiSua = "";
		this.timKiem = "";
		this.chietKhau = 0;
		this.id = "";
		this.db = new dbutils();
		this.msg = "";
	}

	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public float getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(float soLuong) {
		this.soLuong = soLuong;
	}

	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNgaySua() {
		return ngaySua;
	}
	public void setNgaySua(String ngaySua) {
		this.ngaySua = ngaySua;
	}

	public String getNguoiTao() {
		return nguoiTao;
	}
	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public String getNguoiSua() {
		return nguoiSua;
	}
	public void setNguoiSua(String nguoiSua) {
		this.nguoiSua = nguoiSua;
	}

	public String getTimKiem() {
		return timKiem;
	}
	public void setTimKiem(String timKiem) {
		this.timKiem = timKiem;
	}

	public float getChietKhau() {
		return chietKhau;
	}
	public void setChietKhau(float chietKhau) {
		this.chietKhau = chietKhau;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultSet getRsDonViTinh() {
		return rsDonViTinh;
	}
	public void setRsDonViTinh(ResultSet rsDonViTinh) {
		this.rsDonViTinh = rsDonViTinh;
	}

	public ResultSet getRsDataList() {
		return rsDataList;
	}
	public void setRsDataList(ResultSet rsDataList) {
		this.rsDataList = rsDataList;
	}
	
	private String getDateTime(){
		Date date = new Date();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s = sdf.format(date);
		return s;
	}

	@Override
	public void closedb() {
		// TODO Auto-generated method stub
		this.db.shutDown();
	}

	@Override
	public void init(String query) {
		// TODO Auto-generated method stub
		String sql = "";
		if(sql.length() > 0)
			query = sql;
		else{
			try{
				query = "select sp.PK_SEQ as MASANPHAM, sp.TEN as TENSANPHAM, sp.DONVI_FK as MADONVI, " +
						" dv.TEN as DONVI,sp.SOLUONG as SOLUONG, sp.GIABAN as GIABAN, sp.TRANGTHAI as TRANGTHAI "+
						" from SANPHAM as sp inner join DONVI as dv " +
						 " on sp.DONVI_FK = dv.PK_SEQ " +
						" where sp.PK_SEQ = " + this.id;
				ResultSet resultSet = db.get(query);
				if(resultSet.next()){
					this.tenSanPham = resultSet.getString("TENSANPHAM");
					//this.donViTinh = resultSet.getString("DONVI");
					this.donViTinh = resultSet.getString("MADONVI");
					this.soLuong = resultSet.getFloat("SOLUONG");
					this.giaBan = resultSet.getFloat("GIABAN");
					//this.trangThai = resultSet.getString("TRANGTHAI");
				}
				resultSet.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		this.rsDataList = this.db.get(query);
		getDataDonVi();
	}

	@Override
	public void getDataDonVi() {
		// TODO Auto-generated method stub
		this.rsDonViTinh = this.db.get("select PK_SEQ, TEN from DONVI");
	}
	
	@Override
	public boolean saveSanPham() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
		String query = "insert into SANPHAM(MA,DONVI_FK,TEN,TRANGTHAI,NGUOISUA,NGAYSUA," +
				"NGUOITAO,NGAYTAO,SOLUONG,GIABAN,CHIETKHAU)" +
				" values ('"+this.maSanPham+"','"+this.donViTinh+"','"+this.tenSanPham+"','1','"
				+this.nguoiSua+"','"+this.getDateTime()+"','"+this.nguoiTao+"','"+this.getDateTime()
				+"',"+this.soLuong+","+this.giaBan+","+this.chietKhau+")";		
			System.out.print("Query save Sản phẩm"+ query);
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg = "Lỗi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deteleSanPham() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "update SANPHAM set TRANGTHAI = '0' " +
							" where PK_SEQ =  " + this.maSanPham;
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

	@Override
	public boolean updateSanPham() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
		String query = "update SANPHAM set TEN = '"+this.tenSanPham+"',"+
						" DONVI_FK = "+this.donViTinh+", " +
						" SOLUONG = "+this.soLuong+", " +
						" GIABAN = "+this.giaBan+" " +					
						" where PK_SEQ = " + this.id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg = "Loi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}	
}

