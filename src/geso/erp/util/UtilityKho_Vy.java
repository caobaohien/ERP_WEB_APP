package geso.erp.util;

import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class UtilityKho_Vy implements Serializable{
	private dbutils dbutils;
	private ResultSet resultSet;
	private String message;
	private final String querySelect = "SELECT CK.PK_SEQ, CK.NGAYTAO, CK.LYDO, ISNULL(CK.TRANGTHAI, '') AS TRANGTHAI, CK.NGAYSUA, " +
										"N.TEN AS NGUOISUA, NV.TEN AS NGUOITAO " +
										"FROM CHUYENKHO CK " +
										"LEFT JOIN NHANVIEN NV ON NV.PK_SEQ = CK.NGUOITAO " +
										"LEFT JOIN NHANVIEN N ON N.PK_SEQ = CK.NGUOISUA";
	
	private final String queryUpdateTrangThai = "UPDATE CHUYENKHO SET TRANGTHAI = ";

	public UtilityKho_Vy() {
		dbutils = new dbutils();
		resultSet = null;
	}
	
	

	public String getQuerySelect() {
		return querySelect;
	}



	public String getQueryUpdateTrangThai() {
		return queryUpdateTrangThai;
	}

	public ResultSet getResultSetKho() {
		return resultSet;
	}

	public void setResultSetKho(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public ResultSet createResultSetKho() {
		String querySelectKho = "SELECT PK_SEQ, MAKHO, TENKHO FROM KHO";
		return dbutils.getScrol(querySelectKho);
	}

	public dbutils getDbutils() {
		return dbutils;
	}

	public void setDbutils(dbutils dbutils) {
		this.dbutils = dbutils;
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public boolean save(IPhieuChuyenKho_Vy phieuChuyenKho) {
		return false;
		
	}
	
	public boolean checkExist(String table, String PK_SEQ) {
		String query = "SELECT PK_SEQ FROM "+table+" WHERE PK_SEQ = "+ PK_SEQ;
		ResultSet resultSet = dbutils.get(query);
		
		try{
			if(resultSet.next()) {
				resultSet.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		return false;
		
	}
	public int timSanPham(List<ISanPham_Vy> danhSachSanPham, ISanPham_Vy sanPham) {
		for (int i = 0; i < danhSachSanPham.size(); i++) {
			if (danhSachSanPham.get(i).getPk_seq().equals(sanPham.getPk_seq())) {
				return i;
			}
		}
		return -1;
	}
	
	public int selectAvailableOfEachProductAtAWarehouse(String SANPHAM_FK, String KHO_FK) {
		String query = "SELECT AVAILABLE FROM KHO_SANPHAM WHERE SANPHAM_FK = "+SANPHAM_FK+" AND KHO_FK = "+KHO_FK;
		int avalable = 0;
		try {
			ResultSet resultSet = dbutils.get(query);
			if (resultSet.next()) {
				avalable = resultSet.getInt("AVAILABLE");
			}
		} catch (SQLException e) {
			this.message="Không thể thực hiện dòng lệnh ; "+query + "\n";
			e.printStackTrace();
			return -1;
		}
		return avalable;
	}
}
	
	