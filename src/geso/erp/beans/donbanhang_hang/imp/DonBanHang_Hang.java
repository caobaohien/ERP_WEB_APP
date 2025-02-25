package geso.erp.beans.donbanhang_hang.imp;


import geso.erp.beans.donbanhang_hang.IChiTietDonHang_Hang;
import geso.erp.beans.donbanhang_hang.IDonBanHang_Hang;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.Mmap;

public class DonBanHang_Hang implements IDonBanHang_Hang {
	
	private String khachHang;
	private float tongTien;
	private String ngayChungTu;
	
	private String id;
	private String maDonHang;
	private String trangThai;
	private String nguoiTao;
	private String ngayTao;
	private String nguoiSua;
	private String ngaySua;	
	private dbutils db;
	private String msg;
	private ResultSet rsKhachHang;
	private ResultSet rsDataList;//lay tat ca thong tin len man hinh
	
	private List<IChiTietDonHang_Hang>list;
	
	public DonBanHang_Hang() {
		super();
		this.id = "";
		this.ngayChungTu = "";
		this.maDonHang = "";
		this.trangThai = "";
		this.khachHang = "";
		this.nguoiTao = "";
		this.ngayTao = "";
		this.nguoiSua = "";
		this.ngaySua = "";
		this.tongTien = 0;
		this.db = new dbutils();
		this.msg = "";
		//hàm khởi tạo list với size = 0
		this.list = new ArrayList<IChiTietDonHang_Hang>();
	}
	
	public DonBanHang_Hang(String id, String ngayChungTu, String maDonHang,String trangThai,
			String khachHang, String nguoiTao, String ngayTao, String nguoiSua,
			String ngaySua, float tongTien, dbutils db, String msg, ResultSet rsDataList) {
		super();
		this.id = id;
		this.ngayChungTu = ngayChungTu;
		this.maDonHang = maDonHang;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.nguoiTao = nguoiTao;
		this.ngayTao = ngayTao;
		this.nguoiSua = nguoiSua;
		this.ngaySua = ngaySua;
		this.tongTien = tongTien;
		this.db = db;
		this.msg = msg;
		this.rsDataList = rsDataList;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
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

	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
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
	
	public ResultSet getRsKhachHang() {
		return rsKhachHang;
	}
	public void setRsKhachHang(ResultSet rsKhachHang) {
		this.rsKhachHang = rsKhachHang;
	}

	public ResultSet getRsDataList() {
		return rsDataList;
	}
	public void setRsDataList(ResultSet rsDataList) {
		this.rsDataList = rsDataList;
	}
	
	public String getNgayChungTu() {
		return ngayChungTu;
	}
	public void setNgayChungTu(String ngayChungTu) {
		this.ngayChungTu = ngayChungTu;
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

	@Override
	public void getDataKhachHang() {
		// TODO Auto-generated method stub
		this.rsKhachHang = this.db.get("SELECT PK_SEQ, TEN FROM KHACHHANG");	
	}

	public void closedb() {
		this.db.shutDown();
		
	}

	@Override
	public void init(String query) {
		// TODO Auto-generated method stub
		String sql = "";
		if(sql.length() > 0)
			query = sql;
			else{
				try {
					query = "select  bh.NGAYCHUNGTU AS NGAYCHUNGTU, kh.PK_SeQ as KHACHHANG, " +
					" bh.TONGTIEN as TONGTIEN" +
					" from BANHANG as bh inner join KHACHHANG kh on kh.PK_SEQ = bh.KHACHHANG_FK" +
					" where bh.pk_seq =" +this.id ;
					
					
					ResultSet resultSet = db.get(query);
					if(resultSet.next()){
						this.khachHang = resultSet.getString("KHACHHANG");
						this.ngayChungTu = resultSet.getString("NGAYCHUNGTU");
						this.tongTien = resultSet.getFloat("TONGTIEN");
						
					}
					
					query = "select dhsp.DONHANG_FK as MADONHANG, sp.PK_SEQ, sp.MA as MASANPHAM,sp.TEN as TENSANPHAM,"+
						" dv.TEN as DONVITINH, dhsp.SOLUONG as SOLUONG, sp.SOLUONG as SOLUONGTON,"+
						" dhsp.DONGIA as DONGIA, dhsp.THANHTIEN as THANHTIEN "+
						" from DONHANG_SANPHAM as dhsp inner join SANPHAM as sp on sp.PK_SEQ = dhsp.SANPHAM_FK"+
						" inner join DONVI as dv on dv.PK_SEQ = sp.DONVI_FK"+
						" where DONHANG_FK = " + this.id;
					
					//list.clear();
					ResultSet rssp = db.get(query);
					while(rssp.next()){
						IChiTietDonHang_Hang sp = new ChiTietDonHang_Hang();
						sp.setId(rssp.getString("MADONHANG"));
						sp.setIdSanPham(rssp.getString("PK_SEQ"));						
						sp.setMaSanPham(rssp.getString("MASANPHAM"));
						sp.setTenSanPham(rssp.getString("TENSANPHAM"));
						sp.setDonViTinh(rssp.getString("DONVITINH"));
						sp.setSoLuong(rssp.getFloat("SOLUONG"));
						sp.setSoLuongTon(rssp.getFloat("SOLUONGTON"));
						sp.setDonGia(rssp.getFloat("DONGIA"));
						sp.setThanhTien(rssp.getFloat("THANHTIEN"));
						list.add(sp);
					}
					resultSet.close();
					System.out.println("query = " + query);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}	
			this.rsDataList = this.db.get(query);
			getDataKhachHang();
	}

	private String getDateTime(){
		Date date = new Date();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s = sdf.format(date);
		return s;
	}
	@Override
	public boolean saveDonHang() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
		String query = "insert into BANHANG(KHACHHANG_FK,NGAYCHUNGTU,TONGTIEN,TRANGTHAI,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA) " +
				" values( " +this.khachHang+ ", '"+this.ngayChungTu+ "' , " + this.tongTien + ",'0','" + getDateTime()+"','"+
				getDateTime()+"','"+this.nguoiTao+"','"+this.nguoiSua+"')";
		System.out.print("query save DH"+ query);
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}
			
			//lấy được id
			String dmhCurrent = "";
			query = "select SCOPE_IDENTITY() as dmhId";
			ResultSet rsDmh = db.get(query);
			if (rsDmh.next())
			{
				dmhCurrent = rsDmh.getString("dmhId");
				rsDmh.close();
			}
			
			for(int i = 0; i < list.size(); i++){
				IChiTietDonHang_Hang sp = list.get(i);
				query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
						"("+dmhCurrent+","+sp.getIdSanPham()+","+sp.getSoLuong()+","+sp.getDonGia()+","+Math.round(sp.getSoLuong()*sp.getDonGia())+")"; 

				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
				
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			}
		}catch (Exception e) {
			// TODO: handle exception
			db.update("rollback");
			this.msg = "Loi: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deteleDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='2' WHERE PK_SEQ = " + this.maDonHang;
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
	public boolean chotDonHang() {
		// TODO Auto-generated method stub
		try {
			db.getConnection().setAutoCommit(false);
			String query = "UPDATE BANHANG SET TRANGTHAI='1' WHERE PK_SEQ = " + this.maDonHang;
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
	public boolean updateDonHang() {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
		String query = "update BANHANG set KHACHHANG_FK='"+this.khachHang+ "' , NGAYCHUNGTU='" + this.ngayChungTu +"', TONGTIEN="+ this.tongTien +
						" where PK_SEQ =" + this.id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}
			
			query =  "delete DONHANG_SANPHAM where DONHANG_FK = " + this.id;
			if(!db.update(query)){
				db.getConnection().rollback();
				this.msg = "Khong the " + query;
				return false;
			}
			for(int i = 0; i < list.size(); i++){
				IChiTietDonHang_Hang sp = list.get(i);
				query="INSERT INTO DONHANG_SANPHAM (DONHANG_FK,SANPHAM_FK,SOLUONG,DONGIA,THANHTIEN) values " +
						"("+this.id+","+sp.getIdSanPham()+","+sp.getSoLuong()+","+sp.getDonGia()+","+Math.round(sp.getSoLuong()*sp.getDonGia())+")"; 

				if(!db.update(query)){
					db.getConnection().rollback();
					this.msg="Không thể cập nhật dòng lệnh: "+ query;
					return false;
				}
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

	@Override
	public List<IChiTietDonHang_Hang> getListSp() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void setListSp(List<IChiTietDonHang_Hang> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	@Override
	public void getKhachHangTheoMa() {
		// TODO Auto-generated method stub
		
		String query = "select kh.TEN as TEN, kh.DIACHI as DIACHI, kh.DIENTHOAI as DIENTHOAI " +
		" from KHACHHANG as kh inner join BANHANG as bh "+
		" on kh.PK_SEQ = bh.KHACHHANG_FK " +
		" where bh.PK_SEQ = " + this.id;
		
		/*this.rsKhachHang = db.get("select kh.TEN as TEN, kh.DIACHI as DIACHI, kh.DIENTHOAI as DIENTHOAI " +
							" from KHACHHANG as kh inner join BANHANG as bh "+
							" on kh.PK_SEQ = bh.KHACHHANG_FK " +
							" where bh.PK_SEQ = " + this.id);*/
		
		this.rsKhachHang = db.get(query);
		
		System.out.println("Query thong tin khach hang: " + query);
		//System.out.println("Query thông tin khách hàng: " + this.rsKhachHang);
		
	}	
	
}
