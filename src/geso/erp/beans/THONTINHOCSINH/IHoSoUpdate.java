package geso.erp.beans.THONTINHOCSINH;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import geso.dms.center.util.IPhan_Trang;

public interface IHoSoUpdate extends Serializable,IPhan_Trang {
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public String getMsg();
	public void setMsg(String msg);
	public void setId(String Id);
	public void setPK_SEQ(String pk_seq);
	public String getPK_SEQ();
	public void setHoten(String hoten);
	public String getHoten();
	public void setNguyenvongkhac(String nguyenvongkhac);
	public String getNguyenvongkhac();
	public void setNgaytaohoso(String ngaytaohoso);
	public String getNgaytaohoso();
	public void setManhanvien(String manv);
	public String getManhanvien();
	
	public void setMaquocgia(String maquocgia);
	public String getMaquocgia();
	public void setChinhanh(String chinhanh);
	public String getChinhanh();
	public String getTenquocgia();
	public void settenquocgia(String tenqg);
	public void setNgaysinh(String ngaysinh);
	public String getNgaysinh();
	public void setTrinhdohocvan(String tdhocvan);
	public String getTrinhdohocvan();
	public void setKhananghocvan(String knhocvan);
	public String getKhananghocvan();
	public void setKhanangtienganh(String kntienganh);
	public String getKhanangtienganh();
	public void set_pk_seqquocgiahoc(List<String>qghoc);
	public List<String> get_pk_seqquocgiahoc();
	public String getNguyenvong();
	public void setNguyenvong(String nguyenvong);
	public String getNguon();
	public void setNguon(String nguon);
	public String getDiachi();
	public void setDiachi(String diachi);
	public String getDienthoai();
	public void setDienthoai(String dienthoai);
	public String getEmail();
	public void setEmail(String email);
	public String getfacebook();
	public void setfacebook(String facebook);
	
	public String getHoten_cha();
	public void setHoten_cha(String hoten_cha);
	public String getNgaysinh_cha();
	public void setNgaysinh_cha(String ngaysinh_cha);
	public String getDiachi_cha();
	public void setDiachi_cha(String diachi_cha);
	public String getDienthoai_cha();
	public void setDienthoai_cha(String dienthoai_cha);
	public String getNghenghiep_cha();
	public void setNghenghiep_cha(String nghenghiep_cha);
	
	public String getHoten_me();
	public void setHoten_me(String hoten_me);
	public String getNgaysinh_me();
	public void setNgaysinh_me(String ngaysinh_me);
	public String getDiachi_me();
	public void setDiachi_me(String diachi_me);
	public String getDienthoai_me();
	public void setDienthoai_me(String dienthoai_me);
	public String getNghenghiep_me();
	public void setNghenghiep_me(String nghenghiep_me);
	
	public String getThunhap_GD();
	public void setThunhap_GD(String thunhap_gd);
	public String getEmail_khac();
	public void setEmail_khac(String email_khac);
	public String getfacebook_khac();
	public void setfacebook_khac(String facebook_khac);
	
	public ResultSet getDataList();
	public void setDataList(ResultSet rshs);
	public ResultSet getDataLisQuocgiat();
	public void setDataListQuocgia(ResultSet rslistqg);
	public void init();
	public void CreateRs();
	public void CreateRs_listnvqg();
	public void DBclose();
	public boolean save();
	public ResultSet getNhanvien_quocgia();
	public ResultSet setNhanvien_quocgia(ResultSet rslistnvqg);
	public boolean edit_hs();
	public boolean savequocgia();
	
}
