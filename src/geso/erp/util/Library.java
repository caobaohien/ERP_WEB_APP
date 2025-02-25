package geso.erp.util;

import java.sql.ResultSet;

import geso.erp.db.sql.dbutils;

public class Library{
	
	 	static public String GetShopId(String userId,dbutils db){
	 		String ShopId="";
	 		try{
	 			String query="SELECT SHOP_FK FROM NHANVIEN WHERE PK_SEQ="+userId;
	 			ResultSet rs=db.get(query);
	 			if(rs.next()){
	 				ShopId=rs.getString("SHOP_FK");
	 			}
	 			rs.close();
	 			
	 		}catch(Exception er){
	 			er.printStackTrace();
	 		}
			return ShopId;
		}
 

}
