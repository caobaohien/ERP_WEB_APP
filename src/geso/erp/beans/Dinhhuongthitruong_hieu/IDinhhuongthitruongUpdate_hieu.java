package geso.erp.beans.Dinhhuongthitruong_hieu;

import java.sql.ResultSet;
import java.util.List;

public interface IDinhhuongthitruongUpdate_hieu {public String getUserId();
public void setUserId(String _userId);

public String getTrangthai();
public void setTrangthai(String _trangthai);
	public String getId();
public void setId(String _id);

public int getThang();
public void setThang(int _thang);
public int getNam();
public void setNam(int _nam);

public String getTinhtrangthitruong();
public void setTinhtrangthitruong(String _tinhtrangthitruong);

public int getNhandinhthoigiantheothang();
public void setNhandinhthoigiantheothang(int _thoigianthang);

public int getNhandinhthoigiantheoquy();
public void setNhandinhthoigiantheoquy(int _thoigianquy);

public void setmsg(String _msg);
public String getmsg();
public ResultSet getDataList();
public void setDataList(ResultSet _dmhlist);

public void init();
public void CreateRs();
public void DBclose();
public boolean save();

public List<IDinhhuong_hoatdong> getListkhhd();
public void setListkhhd(List<IDinhhuong_hoatdong> _list);
public boolean update();


}
