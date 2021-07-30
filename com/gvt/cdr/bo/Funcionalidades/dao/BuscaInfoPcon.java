package com.gvt.cdr.bo.Funcionalidades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class BuscaInfoPcon {
	
	private static BuscaInfoPcon instance;
//	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public BuscaInfoPcon() {

	}
	
	public BuscaInfoPcon getInstance() {
		if (instance == null)
			instance = new BuscaInfoPcon();

		return instance;
	}
	
	// Check if given string is a number (digits only)
	public static boolean isNumber(String string) {
		return string.matches("^\\d+$");
	}

	public void buscaInfoFavoritos(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("pcon");
			
			String query = QueryWarehouse.getQuery("PconBuscaInfoFavoritos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setDataFavorito(rs.getDate("ultima_alteracao"));
				instancia.setStatusFavorito(rs.getString("status"));
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
