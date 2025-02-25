package geso.erp.beans.chuyenkho_vy.imp;

import geso.dms.center.util.Phan_Trang;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKhoList_Vy;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.kho_vy.IKho_Vy;
import geso.erp.beans.kho_vy.imp.Kho_Vy;
import geso.erp.util.UtilityKho_Vy;

import java.util.List;

public class PhieuChuyenKhoList_Vy extends Phan_Trang implements IPhieuChuyenKhoList_Vy {
	private IPhieuChuyenKho_Vy phieuChuyenKho;
	private String message;
	private UtilityKho_Vy utilityKho;

	public PhieuChuyenKhoList_Vy() {
		this.phieuChuyenKho = new PhieuChuyenKho_Vy();
		utilityKho = new UtilityKho_Vy();
		this.message = utilityKho.getMessage();
	}

	public PhieuChuyenKhoList_Vy(IPhieuChuyenKho_Vy phieuChuyenKho) {
		this.phieuChuyenKho = phieuChuyenKho;
		utilityKho = new UtilityKho_Vy();
		this.message = utilityKho.getMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	@Override
	public void searchPhieuChuyenKho() {
		String newQuerySelect = utilityKho.getQuerySelect() + " WHERE 1=1 ";
		if (this.phieuChuyenKho.getPK_SEQ().length() > 0) {
			newQuerySelect += " AND CK.PK_SEQ LIKE '%"
					+ this.phieuChuyenKho.getPK_SEQ() + "%'";
		}
		if (this.phieuChuyenKho.getKhoChuyen().getPK_SEQ().length() > 0) {
			System.out.println(this.phieuChuyenKho.getKhoChuyen().getPK_SEQ());
			newQuerySelect += " AND CK.KHOCHUYEN LIKE '%"
					+ this.phieuChuyenKho.getKhoChuyen().getPK_SEQ() + "%'";
		}
		if (this.phieuChuyenKho.getKhoNhan().getPK_SEQ().length() > 0) {
			System.out.println(this.phieuChuyenKho.getKhoChuyen().getPK_SEQ());
			newQuerySelect += " AND CK.KHONHAN LIKE '%"
					+ this.phieuChuyenKho.getKhoNhan().getPK_SEQ() + "%'";
		}
		if (this.phieuChuyenKho.getTrangThai().length() > 0) {
			newQuerySelect += " AND CK.TRANGTHAI = "
					+ this.phieuChuyenKho.getTrangThai();
		}
		// Thuc thi cau lenh
		this.utilityKho.setResultSetKho(utilityKho.getDbutils().get(
				newQuerySelect));
		this.setMessage("Tim kiem thanh cong");

	}

	@Override
	public void initListPhieuChuyenKho() {
		// Thuc thi cau lenh
		this.utilityKho.setResultSetKho(utilityKho.getDbutils().get(
				utilityKho.getQuerySelect()));
	}

	@Override
	public String getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getPK_SEQ();
	}

	@Override
	public void setPK_SEQ(String pK_SEQ) {
		// TODO Auto-generated method stub
		this.phieuChuyenKho.setPK_SEQ(pK_SEQ);
	}

	@Override
	public String getNgayChuyenKho() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getNgayChuyenKho();
	}

	@Override
	public void setNgayChuyenKho(String ngayChuyenKho) {
		// TODO Auto-generated method stub
		this.phieuChuyenKho.setNgayChuyenKho(ngayChuyenKho);
	}

	@Override
	public IKho_Vy getKhoNhan() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getKhoNhan();
	}

	@Override
	public void setKhoNhan(IKho_Vy khoNhan) {
		// TODO Auto-generated method stub
		this.phieuChuyenKho.setKhoNhan(khoNhan);
	}

	@Override
	public IKho_Vy getKhoChuyen() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getKhoChuyen();
	}

	@Override
	public void setKhoChuyen(IKho_Vy khoChuyen) {
		// TODO Auto-generated method stub
		this.phieuChuyenKho.setKhoChuyen(khoChuyen);
	}

	@Override
	public String getNguoiTao() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getNguoiTao();
	}

	@Override
	public void setNguoiTao(String nguoiTao) {
		this.phieuChuyenKho.setNguoiTao(nguoiTao);
	}

	@Override
	public String getNguoiSua() {
		// TODO Auto-generated method stub
		return this.phieuChuyenKho.getNguoiSua();
	}

	@Override
	public void setNguoiSua(String nguoiSua) {
		this.phieuChuyenKho.setNguoiSua(nguoiSua);
	}

	@Override
	public String getNgayTao() {
		return this.phieuChuyenKho.getNgayTao();
	}

	@Override
	public String getNgaySua() {
		return this.phieuChuyenKho.getNgaySua();
	}

	@Override
	public String getTrangThai() {
		return this.phieuChuyenKho.getTrangThai();
	}

	@Override
	public void setTrangThai(String trangThai) {
		this.phieuChuyenKho.setTrangThai(trangThai);
	}

	@Override
	public String getLyDo() {
		return this.phieuChuyenKho.getLyDo();
	}

	@Override
	public void setLyDo(String lyDo) {
		this.phieuChuyenKho.setLyDo(lyDo);

	}

	public UtilityKho_Vy getUtilityKho() {
		return utilityKho;
	}

}
