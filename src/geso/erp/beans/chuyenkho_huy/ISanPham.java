package geso.erp.beans.chuyenkho_huy;

public interface ISanPham {
		public String getPK_SEQ();
		public void setPK_SEQ(String pk_seq);
		public String getId();
		public void setId(String id);
		
		public double getBooked();
		public void setBooked(double booked);
	
		public String getMasanpham();
		public void setMasanpham(String masp);
		public String getTensanpham();
		public void setTensanpham(String tensp);
		public double getSoluong();
		public void setSoluong(double soluong);
		public String getDonvitinh();
		public void setDonvitinh(String donvitinh);
}
