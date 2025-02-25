package geso.erp.servlets.baocao1_thu;

import geso.erp.beans.donbanhang_thu.IDonBanHang_Thu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;

public class BaoCao1_ThuSvl extends HttpServlet{
	
	public BaoCao1_ThuSvl() {
        super();
     
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	
	private boolean CreateExcel(OutputStream out, HttpServletRequest request,
			HttpServletResponse response, IDonBanHang_Thu obj)
			throws IOException {
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter(
				"pathThu")
				+ "\\BaoCao1_Thu.xls");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);

		isFillData = FillData(workbook,obj);

		if (!isFillData) {
			if (fstream != null)
				fstream.close();
			return false;
		}

		workbook.save(out);
		fstream.close();
		return true;
	}
	
	
	private boolean FillData(Workbook workbook, IDonBanHang_Thu obj){
		return true;
		
	}
	
	
}
