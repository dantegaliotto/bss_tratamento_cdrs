package com.gvt.cdr.bo.Funcionalidades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Funcionalidades.vo.CdrVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class BuscaInfoPgen {
	
	private static BuscaInfoPgen instance;
//	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public BuscaInfoPgen() {

	}
	
	public BuscaInfoPgen getInstance() {
		if (instance == null)
			instance = new BuscaInfoPgen();

		return instance;
	}
	
	// Check if given string is a number (digits only)
	public static boolean isNumber(String string) {
		return string.matches("^\\d+$");
	}

	public void buscaInfoCDRFavoritos(CdrVO cdr){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("svcpgen");
			
			String query = QueryWarehouse.getQuery("PgenBuscaInfoCDRFavoritos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, cdr.getExternalTrackingId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				cdr.setTipoUsoCorreto(rs.getInt("uso_original"));
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}	
	
}
