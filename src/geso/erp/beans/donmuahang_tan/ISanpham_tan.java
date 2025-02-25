package geso.erp.beans.donmuahang_tan;

public interface ISanpham_tan {
	//Khoa chinh(tu dong tang) cua bang Erp_MuaHang_SP
		public String getPK_SEQ();
		public void setPK_SEQ(String pk_seq);
		// id của đon hàng
		public String getId();
		public void setId(String id);

		public String getMasanpham();
		public void setMasanpham(String masp);

		public String getTensanpham();
		public void setTensanpham(String tensp);
		
		 
		public double getSoluong();
		public void setSoluong(double soluong);
		
		// Them Trung tam chi phi,Thue Nhap Khau
		public String getDonvitinh();
		public void setDonvitinh(String donvitinh);

		public double getDongia();
		public void setDongia(double dongia);
		
		 
		public double getThanhtien();
		public void setThanhtien(double thanhtien);

}
