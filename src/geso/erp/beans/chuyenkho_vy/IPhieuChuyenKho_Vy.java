package geso.erp.beans.chuyenkho_vy;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import geso.dms.center.util.IPhan_Trang;
import geso.erp.beans.kho_vy.IKho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.util.UtilityKho_Vy;

public interface IPhieuChuyenKho_Vy extends Serializable, IPhan_Trang {
	public String getPK_SEQ();
	public void setPK_SEQ(String pK_SEQ);
	
	public String getNgayChuyenKho();
	public void setNgayChuyenKho(String ngayChuyenKho);
	
	public IKho_Vy getKhoNhan();
	public void setKhoNhan(IKho_Vy khoNhan);
	
	public IKho_Vy getKhoChuyen();
	public void setKhoChuyen(IKho_Vy khoChuyen);
	
	public String getNguoiTao();
	public void setNguoiTao(String nguoiTao);
	
	public String getNguoiSua();
	public void setNguoiSua(String nguoiSua);
	
	public String getNgayTao();
	//public String setNgaySua();
	
	public String getNgaySua();
	public void setNgayTao(String ngayTao);
	public String getTrangThai();
	public void setTrangThai(String trangThai);
	
	public String getLyDo();
	public void setLyDo(String lyDo);
	
	public String getMessage();
	public void setMessage(String message);
	
	public boolean isCapNhat();
	public void setCapNhat(boolean capNhat);
	public boolean isHienThi();
	public void setHienThi(boolean hienThi);

	public void setUserId(String userId);
	public List<ISanPham_Vy> getDanhSachSanPham();


	public void setDanhSachSanPham(List<ISanPham_Vy> danhSachSanPham);
	public void init();
	public boolean chotPhieu();
	public boolean huyPhieu();
	
	public UtilityKho_Vy getUtilityKho();
	List<ISanPham_Vy> checkNotAvailable();
	boolean save();
}
