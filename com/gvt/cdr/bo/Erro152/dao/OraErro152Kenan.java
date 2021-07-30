package com.gvt.cdr.bo.Erro152.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Erro152.vo.InstanciaSiebelVO;
import com.gvt.cdr.dao.OraArbor;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro152Kenan extends OraArbor{
	
	private static OraErro152Kenan instance;

	public OraErro152Kenan() {

	}
	
	public OraErro152Kenan getInstance() {
		if (instance == null)
			instance = new OraErro152Kenan();

		return instance;
	}

	public boolean verificaContaCriada(String conta){
		
		boolean retorno = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro152kenanVerificaContaCriada");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, conta);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				retorno = true;
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

	public int verificaInstanciaCriadaCat(InstanciaSiebelVO instancia){
		
		int retorno = 0;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro152kenanVerificaInstanciaCriadaCat");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				retorno = rs.getInt	("server_id");
				
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

	public boolean verificaInstanciaCriadaCust(InstanciaSiebelVO instancia, int serverId){
		
		boolean retorno = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId-2));
			
			String query = QueryWarehouse.getQuery("erro152kenanVerificaInstanciaCriadaCust");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				retorno = true;
				
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

