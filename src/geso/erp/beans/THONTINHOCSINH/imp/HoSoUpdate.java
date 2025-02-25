package geso.erp.beans.THONTINHOCSINH.imp;

import java.sql.Array;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.THONTINHOCSINH.IHoSoUpdate;
import geso.erp.beans.chuyenkho_huy.IChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.ISanPham;
import geso.erp.beans.chuyenkho_huy.imp.SanPham;
import geso.erp.db.sql.dbutils;

public class HoSoUpdate extends Phan_Trang implements IHoSoUpdate {
	
	private static final long serialVersionUID = 1L;
	String hoten,ngaysinh,tdhocvan,knhocvan,kntienganh,pk_seq,tenquocgia,nguyenvong,ngaytaohoso,manv;
	String nvdihoc,nguon,diachi,dienthoai,email,facebook,hoten_cha,ngaysinh_cha,diachi_cha,dienthoai_cha,nghenghiep_cha;
	String hoten_me,ngaysinh_me,diachi_me,dienthoai_me,nghenghiep_me,email_khac,facebook_khac;
	String userId,Id,msg,nguyenvongkhac,maquocgia;
	String thunhap_thang_giadinh,chinhanh;
	ResultSet rshs; // resultset chua thong tin don hang
	ResultSet rslistqg;
	ResultSet rslistnvqg;
	List<String> hoso_quocgia_fk;
	dbutils db=new dbutils();
	public HoSoUpdate() {
		super();
		this.chinhanh="";
		this.maquocgia="";
		this.hoten = "";
		this.nguyenvongkhac="";
		this.ngaytaohoso="";
		this.ngaysinh = "";
		this.tdhocvan = "";
		this.knhocvan = "";
		this.kntienganh = "";
		
		this.pk_seq = "";
		this.tenquocgia = "";
		this.nvdihoc = "";
		this.nguon = "";
		this.diachi = "";
		this.dienthoai = "";
		this.email = "";
		this.facebook = "";
		this.hoten_cha = "";
		this.ngaysinh_cha ="" ;
		this.diachi_cha = "";
		this.dienthoai_cha = "";
		this.nghenghiep_cha = "";
		this.hoten_me = "";
		this.ngaysinh_me = "";
		this.diachi_me = "";
		this.dienthoai_me = "";
		this.nghenghiep_me = "";
		this.thunhap_thang_giadinh = "";
		this.email_khac = "";
		this.facebook_khac = "";
		this.userId = "";
		this.Id = "";
		this.msg = "";
		this.nguyenvong="";
		this.hoso_quocgia_fk = new ArrayList<String>();
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try
		{
			String query;
			query="select hs.TEN,hsqg.QUOCGIA_FK as pk_seq_quocgia,qg.TEN as tenquocgia,hs.NGAYSINH,hs.TRINHDOHOCVAN,"
					+ "hs.KHANANGHOCVAN,hs.NGUYENVONGDIHOC,hs.NGUYENVONGDIHOC_KHAC,hs.NGUON,hs.DIACHI,"
					+ "hs.DIENTHOAI,hs.EMAIL,hs.FACEBOOK,hs.TEN_BA,hs.NGAYSINH_BA,hs.DT_BA,hs.DIACHI_BA,"
					+ "hs.NGHENGHIEP_BA,hs.TEN_ME,hs.NGAYSINH_ME,hs.DIACHI_ME,hs.DT_ME,hs.NGHENGHIEP_ME,"
					+ "hs.THUNHAPHANGTHANG,hs.EMAIL_GIADINH,hs.FACEBOOK_GIADINH,hs.NGAYTAO,hs.TRANGTHAI,"
					+ "hs.KHANANGTIENGANH "
					+ "from HOSO hs left join HOSO_QUOCGIA hsqg on hs.PK_SEQ=hsqg.HOSO_FK "
					+ "left join QUOCGIA qg on qg.PK_SEQ=hsqg.QUOCGIA_FK "
					+ "where hs.PK_SEQ="+this.Id+"";
			
			ResultSet rs =this.db.get(query);
			//System.out.println("dia chi "+rs.getString("DIACHI"));
			List<String> arrayquocgia= new ArrayList<String>();
			String temp="";
			if(rs!=null)
			{
				
				while(rs.next())
				{
					this.ngaytaohoso=rs.getString("NGAYTAO");
					this.hoten=rs.getString("TEN");
					this.ngaysinh=rs.getString("NGAYSINH");
					this.tdhocvan=rs.getString("TRINHDOHOCVAN");
					this.knhocvan=rs.getString("KHANANGHOCVAN");
					this.kntienganh=rs.getString("KHANANGTIENGANH");
		
					this.nguyenvong=rs.getString("NGUYENVONGDIHOC");
					this.nguyenvongkhac=rs.getString("NGUYENVONGDIHOC_KHAC");
					this.nguon=rs.getString("NGUON");
					this.diachi=rs.getString("DIACHI");
					System.out.println("dia chi "+rs.getString("DIACHI"));
					this.dienthoai=rs.getString("DIENTHOAI");
					this.email=rs.getString("EMAIL");
					this.facebook=rs.getString("FACEBOOK");
					this.hoten_cha=rs.getString("TEN_BA");
					this.ngaysinh_cha=rs.getString("NGAYSINH_BA");
					this.diachi_cha=rs.getString("DIACHI_BA");
					this.dienthoai_cha=rs.getString("DT_BA");
					this.nghenghiep_cha=rs.getString("NGHENGHIEP_BA");
					this.hoten_me=rs.getString("TEN_ME");
					this.ngaysinh_me=rs.getString("NGAYSINH_ME");
					this.diachi_me=rs.getString("DIACHI_ME");
					this.dienthoai_me=rs.getString("DT_ME");
				
					this.nghenghiep_me=rs.getString("NGHENGHIEP_ME");
					this.thunhap_thang_giadinh=rs.getString("THUNHAPHANGTHANG");
					this.email_khac=rs.getString("EMAIL_GIADINH");
					this.facebook_khac=rs.getString("FACEBOOK_GIADINH");
					
					temp = rs.getString("pk_seq_quocgia");
					arrayquocgia.add(temp);
				}
			}
			
			this.hoso_quocgia_fk =arrayquocgia;
			rs.close();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}
	@Override
	public void CreateRs() {
		// TODO Auto-generated method stub
		String query;
		query=" select PK_SEQ,MA,TEN "
				+ " from QUOCGIA"
				+ " where MA <> 'VN'";
		this.rslistqg=db.get(query);
	}
	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId=userId;
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.Id;
	}
	@Override
	public void setId(String Id) {
		// TODO Auto-generated method stub
		this.Id=Id;
	}
	
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		try
		{
			Date today=new Date(System.currentTimeMillis());
			SimpleDateFormat timeFormat= new SimpleDateFormat("yyyy/MM/dd");
			String s=timeFormat.format(today.getTime());
			String query;
			db.getConnection().setAutoCommit(false);
			query="insert into HOSO(TEN,NGAYSINH,TRINHDOHOCVAN,KHANANGHOCVAN,"
					+ "NGUYENVONGDIHOC,NGUYENVONGDIHOC_KHAC,NGUON,DIACHI,DIENTHOAI,EMAIL,FACEBOOK,TEN_BA,"
					+ "NGAYSINH_BA,DT_BA,DIACHI_BA,NGHENGHIEP_BA,TEN_ME,NGAYSINH_ME,DIACHI_ME,DT_ME,NGHENGHIEP_ME,"
					+ "THUNHAPHANGTHANG,EMAIL_GIADINH,FACEBOOK_GIADINH,NGAYTAO,NGUOITAO,NGAYSUA,NGUOISUA,"
					+ "TRANGTHAI,KHANANGTIENGANH) "
					+ "values('"+this.getHoten()+"','"+this.getNgaysinh()+"','"+this.getTrinhdohocvan()+"',"
							+ "'"+this.getKhananghocvan()+"','"+this.getNguyenvong()+"',"
							+ "'"+this.getNguyenvongkhac()+"','"+this.getNguon()+"','"+this.getDiachi()+"',"
							+ "'"+this.getDienthoai()+"','"+this.getEmail()+"','"+this.getfacebook()+"',"
							+ "'"+this.getHoten_cha()+"','"+this.getNgaysinh_cha()+"','"+this.getDienthoai_cha()+"','"+this.getDiachi_cha()+"',"
							+ "'"+this.getNghenghiep_cha()+"','"+this.getHoten_me()+"',"
							+ "'"+this.getNgaysinh_me()+"','"+this.getDiachi_me()+"',"
							+ "'"+this.getDienthoai_me()+"','"+this.getNghenghiep_me()+"',"+this.getThunhap_GD()+","
							+ "'"+this.getEmail_khac()+"','"+this.getfacebook_khac()+"','"+this.getNgaytaohoso()+"',"
							+ ""+this.userId+",'"+s+"',"+this.userId+","
							+ "'0','"+this.getKhanangtienganh()+"')";
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
			
			String idqg="";
			  query = "select SCOPE_IDENTITY() as ckid";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next())
				{
					idqg = rsDmh.getString("ckid");

				}
			for(int i=0;i<this.hoso_quocgia_fk.size();i++)
			{
				query="insert into HOSO_QUOCGIA(HOSO_FK,QUOCGIA_FK) "
						+ "values("+idqg+","+this.hoso_quocgia_fk.get(i)+")";
				if(!db.update(query))
				{
					this.msg="khong thuc hien duoc cau lenh "+query;
					db.getConnection().rollback();
					return false;
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch(Exception e)
		{
			e.getStackTrace();
			return false;
		}

	}
	@Override
	public void setPK_SEQ(String pk_seq) {
		// TODO Auto-generated method stub
		this.pk_seq=pk_seq;
	}
	@Override
	public String getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.pk_seq;
	}
	@Override
	public void setHoten(String hoten) {
		// TODO Auto-generated method stub
		this.hoten=hoten;
	}
	@Override
	public String getHoten() {
		// TODO Auto-generated method stub
		return this.hoten;
	}
	@Override
	public void setNgaysinh(String ngaysinh) {
		// TODO Auto-generated method stub
		this.ngaysinh=ngaysinh;
	}
	@Override
	public String getNgaysinh() {
		// TODO Auto-generated method stub
		return this.ngaysinh;
	}
	@Override
	public void setTrinhdohocvan(String tdhocvan) {
		// TODO Auto-generated method stub
		this.tdhocvan=tdhocvan;
	}
	@Override
	public String getTrinhdohocvan() {
		// TODO Auto-generated method stub
		return this.tdhocvan;
	}
	@Override
	public void setKhananghocvan(String knhocvan) {
		// TODO Auto-generated method stub
		this.knhocvan=knhocvan;
	}
	@Override
	public String getKhananghocvan() {
		// TODO Auto-generated method stub
		return this.knhocvan;
	}
	@Override
	public void setKhanangtienganh(String kntienganh) {
		// TODO Auto-generated method stub
		this.kntienganh=kntienganh;
	}
	@Override
	public String getKhanangtienganh() {
		// TODO Auto-generated method stub
		return this.kntienganh;
	}
	@Override
	public void set_pk_seqquocgiahoc(List<String>qghoc) {
		// TODO Auto-generated method stub
		this.hoso_quocgia_fk=qghoc;
	}
	@Override
	public List<String> get_pk_seqquocgiahoc() {
		// TODO Auto-generated method stub
		return this.hoso_quocgia_fk;
	}
	@Override
	public ResultSet getDataList() {
		// TODO Auto-generated method stub
		return this.rshs;
	}
	@Override
	public void setDataList(ResultSet rshs) {
		// TODO Auto-generated method stub
		this.rshs=rshs;
	}
	@Override
	public ResultSet getDataLisQuocgiat() {
		// TODO Auto-generated method stub
		return this.rslistqg;
	}
	@Override
	public void setDataListQuocgia(ResultSet rslistqg) {
		// TODO Auto-generated method stub
		this.rslistqg=rslistqg;
	}
	@Override
	public String getTenquocgia() {
		// TODO Auto-generated method stub
		return this.tenquocgia;
	}
	@Override
	public void settenquocgia(String tenqg) {
		// TODO Auto-generated method stub
		this.tenquocgia=tenqg;
	}
	@Override
	public boolean savequocgia() {
		// TODO Auto-generated method stub
		try{
			String query;
			query="insert into QUOCGIA(MA,TEN) values('"+this.getMaquocgia()+"','"+this.getTenquocgia()+"')";
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg=msg;
	}
	@Override
	public String getNguyenvong() {
		// TODO Auto-generated method stub
		return this.nguyenvong;
	}
	@Override
	public void setNguyenvong(String nguyenvong) {
		// TODO Auto-generated method stub
		this.nguyenvong=nguyenvong;
	}
	@Override
	public String getNguon() {
		// TODO Auto-generated method stub
		return this.nguon;
	}
	@Override
	public void setNguon(String nguon) {
		// TODO Auto-generated method stub
		this.nguon=nguon;
	}
	@Override
	public String getDiachi() {
		// TODO Auto-generated method stub
		return this.diachi;
	}
	@Override
	public void setDiachi(String diachi) {
		// TODO Auto-generated method stub
		this.diachi=diachi;
	}
	@Override
	public String getDienthoai() {
		// TODO Auto-generated method stub
		return this.dienthoai;
	}
	@Override
	public void setDienthoai(String dienthoai) {
		// TODO Auto-generated method stub
		this.dienthoai=dienthoai;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email=email;
	}
	@Override
	public String getfacebook() {
		// TODO Auto-generated method stub
		return this.facebook;
	}
	@Override
	public void setfacebook(String facebook) {
		// TODO Auto-generated method stub
		this.facebook=facebook;
	}
	@Override
	public String getHoten_cha() {
		// TODO Auto-generated method stub
		return this.hoten_cha;
	}
	@Override
	public void setHoten_cha(String hoten_cha) {
		// TODO Auto-generated method stub
		this.hoten_cha=hoten_cha;
	}
	@Override
	public String getNgaysinh_cha() {
		// TODO Auto-generated method stub
		return this.ngaysinh_cha;
	}
	@Override
	public void setNgaysinh_cha(String ngaysinh_cha) {
		// TODO Auto-generated method stub
		this.ngaysinh_cha=ngaysinh_cha;
	}
	@Override
	public String getDiachi_cha() {
		// TODO Auto-generated method stub
		return this.diachi_cha;
	}
	@Override
	public void setDiachi_cha(String diachi_cha) {
		// TODO Auto-generated method stub
		this.diachi_cha=diachi_cha;
	}
	@Override
	public String getDienthoai_cha() {
		// TODO Auto-generated method stub
		return this.dienthoai_cha;
	}
	@Override
	public void setDienthoai_cha(String dienthoai_cha) {
		// TODO Auto-generated method stub
		this.dienthoai_cha=dienthoai_cha;
	}
	@Override
	public String getNghenghiep_cha() {
		// TODO Auto-generated method stub
		return this.nghenghiep_cha;
	}
	@Override
	public void setNghenghiep_cha(String nghenghiep_cha) {
		// TODO Auto-generated method stub
		this.ngaysinh_cha=nghenghiep_cha;
	}
	@Override
	public String getHoten_me() {
		// TODO Auto-generated method stub
		return this.hoten_me;
	}
	@Override
	public void setHoten_me(String hoten_me) {
		// TODO Auto-generated method stub
		this.hoten_me=hoten_me;
	}
	@Override
	public String getNgaysinh_me() {
		// TODO Auto-generated method stub
		return this.ngaysinh_me;
	}
	@Override
	public void setNgaysinh_me(String ngaysinh_me) {
		// TODO Auto-generated method stub
		this.ngaysinh_me=ngaysinh_me;
	}
	@Override
	public String getDiachi_me() {
		// TODO Auto-generated method stub
		return this.diachi_me;
	}
	@Override
	public void setDiachi_me(String diachi_me) {
		// TODO Auto-generated method stub
		this.diachi_me=diachi_me;
	}
	@Override
	public String getDienthoai_me() {
		// TODO Auto-generated method stub
		return this.dienthoai_me;
	}
	@Override
	public void setDienthoai_me(String dienthoai_me) {
		// TODO Auto-generated method stub
		this.dienthoai_me=dienthoai_me;
	}
	@Override
	public String getNghenghiep_me() {
		// TODO Auto-generated method stub
		return this.nghenghiep_me;
	}
	@Override
	public void setNghenghiep_me(String nghenghiep_me) {
		// TODO Auto-generated method stub
		this.nghenghiep_me=nghenghiep_me;
	}
	@Override
	public String getThunhap_GD() {
		// TODO Auto-generated method stub
		return this.thunhap_thang_giadinh;
	}
	@Override
	public void setThunhap_GD(String thunhap_gd) {
		// TODO Auto-generated method stub
		this.thunhap_thang_giadinh=thunhap_gd;
	}
	@Override
	public String getEmail_khac() {
		// TODO Auto-generated method stub
		return this.email_khac;
	}
	@Override
	public void setEmail_khac(String email_khac) {
		// TODO Auto-generated method stub
		this.email_khac=email_khac;
	}
	@Override
	public String getfacebook_khac() {
		// TODO Auto-generated method stub
		return this.facebook_khac;
	}
	@Override
	public void setfacebook_khac(String facebook_khac) {
		// TODO Auto-generated method stub
		this.facebook_khac=facebook_khac;
	}
	@Override
	public boolean edit_hs() {
		// TODO Auto-generated method stub
	try{
		db.getConnection().setAutoCommit(false);
		String query="";
		if(Id.length()>0)
		{
		query="update HOSO set TEN='"+this.hoten+"',NGAYSINH='"+this.ngaysinh+"',"
				+ "TRINHDOHOCVAN='"+this.tdhocvan+"',KHANANGHOCVAN='"+this.knhocvan+"',"
				+ "NGUYENVONGDIHOC='"+this.nguyenvong+"',NGUYENVONGDIHOC_KHAC='"+this.nguyenvongkhac+"',"
				+ "NGUON='"+this.nguon+"',DIACHI='"+this.diachi+"',DIENTHOAI='"+this.dienthoai+"',EMAIL='"+this.email+"',"
				+ "FACEBOOK='"+this.facebook+"',TEN_BA='"+this.hoten_cha+"',NGAYSINH_BA='"+this.ngaysinh_cha+"',"
				+ "DT_BA='"+this.dienthoai_cha+"',DIACHI_BA='"+this.diachi_cha+"',NGHENGHIEP_BA='"+this.nghenghiep_cha+"',"
				+ "TEN_ME='"+this.hoten_me+"',NGAYSINH_ME='"+this.ngaysinh_me+"',DIACHI_ME='"+this.diachi_me+"',"
				+ "DT_ME='"+this.dienthoai_me+"',NGHENGHIEP_ME='"+this.nghenghiep_me+"',"
				+ "THUNHAPHANGTHANG="+this.thunhap_thang_giadinh+",EMAIL_GIADINH='"+this.email_khac+"',"
				+ "FACEBOOK_GIADINH='"+this.facebook_khac+"',NGAYTAO='"+this.ngaytaohoso+"',"
				+ "KHANANGTIENGANH='"+this.kntienganh+"' where PK_SEQ="+this.Id+" ";
			if(!db.update(query)){
				this.msg="Không  thực hiện được câu lệnh: "+query;
				db.getConnection().rollback();
				return false;
			}
		query="delete from HOSO_QUOCGIA where HOSO_FK="+this.Id+"";
		if(!db.update(query)){
			this.msg="Không  thực hiện được câu lệnh: "+query;
			db.getConnection().rollback();
			return false;}
				
		}
		for(int i=0;i<this.hoso_quocgia_fk.size();i++)
		{
			query="insert into HOSO_QUOCGIA(HOSO_FK,QUOCGIA_FK) "
					+ "values("+this.Id+","+this.hoso_quocgia_fk.get(i)+")";
			if(!db.update(query))
			{
				this.msg="khong thuc hien duoc cau lenh "+query;
				db.getConnection().rollback();
				return false;
			}
		}
		db.getConnection().commit();
		db.getConnection().setAutoCommit(true);
	}catch(Exception err){
		this.msg="Lỗi :"+err.getMessage();
		db.update("rollback");
		err.printStackTrace();
		return false;
	}
	return true;
	}
	@Override
	public void setNgaytaohoso(String ngaytaohoso) {
		// TODO Auto-generated method stub
		this.ngaytaohoso=ngaytaohoso;
	}
	@Override
	public String getNgaytaohoso() {
		// TODO Auto-generated method stub
		return this.ngaytaohoso;
	}
	@Override
	public void setManhanvien(String manv) {
		// TODO Auto-generated method stub
		this.manv=manv;
	}
	@Override
	public String getManhanvien() {
		// TODO Auto-generated method stub
		return this.manv;
	}
	@Override
	public void setNguyenvongkhac(String nguyenvongkhac) {
		// TODO Auto-generated method stub
		this.nguyenvongkhac=nguyenvongkhac;
	}
	@Override
	public String getNguyenvongkhac() {
		// TODO Auto-generated method stub
		return this.nguyenvongkhac;
	}
	@Override
	public void setChinhanh(String chinhanh) {
		// TODO Auto-generated method stub
		this.chinhanh=chinhanh;
	}
	@Override
	public String getChinhanh() {
		// TODO Auto-generated method stub
		return this.chinhanh;
	}
	@Override
	public void setMaquocgia(String maquocgia) {
		// TODO Auto-generated method stub
		this.maquocgia=maquocgia;
	}
	@Override
	public String getMaquocgia() {
		// TODO Auto-generated method stub
		return this.maquocgia;
	}
	@Override
	public ResultSet getNhanvien_quocgia() {
		// TODO Auto-generated method stub
			return this.rslistnvqg;
	}
	@Override
	public void CreateRs_listnvqg() {
		// TODO Auto-generated method stub
		String query;
		query="select NHANVIEN_FK,QUOCGIA_FK from NHANVIEN_QUOCGIA"
				+ " where NHANVIEN_FK="+this.userId+"";
		this.rslistnvqg=db.get(query);
	}
	@Override
	public ResultSet setNhanvien_quocgia(ResultSet rslistnvqg) {
		// TODO Auto-generated method stub
		return this.rslistnvqg=rslistnvqg;
	}
}
