package geso.erp.beans.chuyenkho_tan;

public interface ISanpham_tan {
	//Khoa chinh(tu dong tang) cua bang Chuyenkho_Sanpham
		public String getPK_SEQ();
		public void setPK_SEQ(String pk_seq);

		public String getMasanpham();
		public void setMasanpham(String masp);

		public String getTensanpham();
		public void setTensanpham(String tensp);
		
		public double getSoluongTon();
		public void setSoluongTon(double _soluong);
		public double getSoluongChuyen();
		public void setSoluongChuyen(double _soluong);
		
		// 
		public String getDonvitinh();
		public void setDonvitinh(String donvitinh);	

}
