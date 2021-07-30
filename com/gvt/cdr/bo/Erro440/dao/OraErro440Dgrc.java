package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Dgrc {
	
	private static OraErro440Dgrc instance;

	public OraErro440Dgrc() {

	}
	
	public OraErro440Dgrc getInstance() {
		if (instance == null)
			instance = new OraErro440Dgrc();

		return instance;
	}

	public int verificaDuplicidadeDesignadores(String externalId) {

		int retorno = 0;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("preg");
			
			String query = "";

			query = QueryWarehouse.getQuery("erro440dgrcContaDesignadores");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, externalId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				retorno = rs.getInt("num_designadores");

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
		
		return retorno;
		
	}
	
}
