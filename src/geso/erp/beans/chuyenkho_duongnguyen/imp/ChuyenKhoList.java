package geso.erp.beans.chuyenkho_duongnguyen.imp;

import java.sql.ResultSet;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKhoList;
import geso.erp.db.sql.dbutils;

public class ChuyenKhoList extends Phan_Trang implements IChuyenKhoList {
	private String ma;
	private String ngayChuyenKho;
	private String khoNhan;
	private String khoChuyen;
	private String nguoiTao;
	private String nguoiSua;
	private String trangThai;
	private String ngayTao;
	private String ngaySua;
	private String lyDo;
	private String userId;
	private String msg;
	private ResultSet rsKhoChuyen;
	private ResultSet rsKhoNhan;
	private ResultSet rsData;
	dbutils db;

	public ChuyenKhoList() {
		this.ma = "";
		this.ngayChuyenKho = "";
		this.khoNhan = "";
		this.khoChuyen = "";
		this.nguoiTao = "";
		this.nguoiSua = "";
		this.trangThai = "";
		this.ngayTao = "";
		this.ngaySua = "";
		this.lyDo = "";
		this.userId = "";
		this.msg ="";
		this.db = new dbutils();
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma = ma;
	}

	@Override
	public String getNgayChuyenKho() {
		// TODO Auto-generated method stub
		return this.ngayChuyenKho;
	}

	@Override
	public void setNgayChuyenKho(String ngayChuyenKho) {
		// TODO Auto-generated method stub
		this.ngayChuyenKho = ngayChuyenKho;
	}

	@Override
	public String getKhoNhan() {
		// TODO Auto-generated method stub
		return this.khoNhan;
	}

	@Override
	public void setKhoNhan(String khoNhan) {
		// TODO Auto-generated method stub
		this.khoNhan = khoNhan;
	}

	@Override
	public String getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.khoChuyen;
	}

	@Override
	public void setKhoChuyen(String khoChuyen) {
		// TODO Auto-generated method stub
		this.khoChuyen = khoChuyen;
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.nguoiTao;
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		// TODO Auto-generated method stub
		this.nguoiTao = nguoiTao;
	}

	@Override
	public String getNguoiSua() {
		// TODO Auto-generated method stub
		return this.nguoiSua;
	}

	@Override
	public void setNguoiSua(String nguoiSua) {
		// TODO Auto-generated method stub
		this.nguoiSua = nguoiSua;
	}

	@Override
	public String getTrangThai() {
		// TODO Auto-generated method stub
		return this.trangThai;
	}

	@Override
	public void setTrangThai(String trangThai) {
		// TODO Auto-generated method stub
		this.trangThai = trangThai;
	}

	@Override
	public String getNgayTao() {
		// TODO Auto-generated method stub
		return this.ngayTao;
	}

	@Override
	public void setNgayTao(String ngayTao) {
		// TODO Auto-generated method stub
		this.ngayTao = ngayTao;
	}

	@Override
	public String getNgaySua() {
		// TODO Auto-generated method stub
		return this.ngaySua;
	}

	@Override
	public void setNgaySua(String ngaySua) {
		// TODO Auto-generated method stub
		this.ngaySua = ngaySua;
	}

	@Override
	public String getLyDo() {
		// TODO Auto-generated method stub
		return this.lyDo;
	}

	@Override
	public void setLyDo(String lyDo) {
		// TODO Auto-generated method stub
		this.lyDo = lyDo;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		this.msg = msg;
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;
	}

	@Override
	public ResultSet getRsKhoChuyen() {
		// TODO Auto-generated method stub
		return this.rsKhoChuyen;
	}

	@Override
	public void CreateRsKhoChuyen() {
		// TODO Auto-generated method stub
		String query = "SELECT PK_SEQ,TENKHO FROM KHO";
		this.rsKhoChuyen = db.get(query);
	}

	@Override
	public ResultSet getRsKhoNhan() {
		// TODO Auto-generated method stub
		return this.rsKhoNhan;
	}

	@Override
	public void CreateRsKhoNhan() {
		// TODO Auto-generated method stub
		String query = "SELECT PK_SEQ,TENKHO FROM KHO";
		this.rsKhoNhan = db.get(query);

	}

	@Override
	public void init(String search) {
		// TODO Auto-generated method stub
		String query = "SELECT CK.PK_SEQ ,CK.LYDO,NVTAO.TEN AS NHANVIENTAO,NGAYTAO,NVSUA.TEN AS NHANVIENSUA,NGAYSUA FROM CHUYENKHO CK"
				+" LEFT JOIN NHANVIEN NVTAO ON CK.NGUOITAO=NVTAO.PK_SEQ"
				+" LEFT JOIN NHANVIEN NVSUA ON CK.NGUOISUA=NVSUA.PK_SEQ WHERE 1=1";
		if (search.length() > 0)
			query = search;
		this.rsData = db.get(query);
		CreateRsKhoChuyen();
		CreateRsKhoNhan();
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet getRsData() {
		// TODO Auto-generated method stub
		return this.rsData;
	}

}
