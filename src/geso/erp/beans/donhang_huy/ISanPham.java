package geso.erp.beans.donhang_huy;

import java.util.List;

import geso.erp.beans.muahang_huy.IChiTietMuaHang;

public interface ISanPham {
		public String getPK_SEQ();
		public void setPK_SEQ(String pk_seq);
		public String getId();
		public void setId(String id);

		public String getMasanpham();

		public void setMasanpham(String masp);

		public String getTensanpham();

		public void setTensanpham(String tensp);
		
		public double getSoluong();

		public void setSoluong(double soluong);

		public String getDonvitinh();

		public void setDonvitinh(String donvitinh);

		public double getDongia();

		public void setDongia(  double dongia);
		
		public double getThanhtien();

		public void setChietkhau(double chietkhau);
		public double getChietkhau();

		public void setThanhtien(double thanhtien);
		public List<IChiTietMuaHang> getListCTMH();
		public void setListCTMH(List<IChiTietMuaHang> listctmh);
		
		
}
