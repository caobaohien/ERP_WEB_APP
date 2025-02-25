package geso.erp.beans.sanpham_kiet06.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.sanpham_kiet06.ISanPhamList;
import geso.erp.db.sql.dbutils;

public class SanPhamList extends Phan_Trang implements ISanPhamList {

	String Ma, Ten, TrangThai, DonViTinh, UserId, Msg;
	ResultSet rsList, rsDonViTinh;
	dbutils db =  new dbutils();
	
	public SanPhamList(){
		Ma = "";
		Ten = "";
		TrangThai = "";
		DonViTinh = "";
		UserId = "";
		Msg = "";
	}
	//------------------------------------
	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.Ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.Ma = ma;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.Ten;
	}

	@Override
	public void setTen(String ten) {
		// TODO Auto-generated method stub
		this.Ten = ten;
	}

	@Override
	public String getDonViTinh() {
		// TODO Auto-generated method stub
		return this.DonViTinh;
	}

	@Override
	public void setDonViTinh(String dvt) {
		// TODO Auto-generated method stub
		this.DonViTinh = dvt;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.TrangThai;
	}

	@Override
	public void setTrangThai(String tt) {
		// TODO Auto-generated method stub
		this.TrangThai = tt;
	}

	@Override
	public ResultSet getRsList() {
		// TODO Auto-generated method stub
		return this.rsList;
	}

	@Override
	public void setRsList(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsList = rs;
	}

	@Override
	public ResultSet getRsDonVi() {
		// TODO Auto-generated method stub
		return this.rsDonViTinh;
	}

	@Override
	public void setRsDonVi(ResultSet rs) {
		// TODO Auto-generated method stub
		this.rsDonViTinh = rs;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.UserId;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.UserId = userId;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.Msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.Msg = msg;
	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query = "select SP.PK_SEQ, SP.TEN, SP.MA, SP.TRANGTHAI, DV.MA as DonVi "
				+ "from SANPHAM SP left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK";
		if (search.length() > 0)
			query = search;
		this.rsList = db.get(query);
		createRsDvt();
	}

	@Override
	public void createRsDvt() {
		// TODO Auto-generated method stub
		String query = "select PK_SEQ, MA from DONVI";
		this.rsDonViTinh = db.get(query);
	}

	@Override
	public void dbClose() {
		// TODO Auto-generated method stub
		db.shutDown();
	}

}
