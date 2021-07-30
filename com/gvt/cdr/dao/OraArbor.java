package com.gvt.cdr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Erro440.vo.AutorotProvchargesVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraArbor {

	private static int RUN_STATUS = 99;
	
	public boolean retornaDtInstanciaKenan(InstanciaSiebelVO instancia){

		boolean retorno = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("arborRetornaDtInstancia");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setDtInstanciaKenan(rs.getDate("active_date"));
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

	public boolean verificaServerId(String instancia, int serverId){
		
		boolean serverOk = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId));
						
			String query = QueryWarehouse.getQuery("arborVerificaServerId");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				serverOk = true;
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
		
		return serverOk;
		
	}

	
	
	public void insertProvcharges(AutorotProvchargesVO registro){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(registro.getServerId()-2));
			
			String query = QueryWarehouse.getQuery("arborInsertProvcharges");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, registro.getRowIdElemento());
			pstmt.setString(2, registro.getExternalId());
			pstmt.setInt(3, registro.getExternalIdType());
			pstmt.setInt(4, registro.getComponentId());
			pstmt.setInt(5, registro.getComponentId());
			pstmt.setDate(6, registro.getDtAtivar());
			pstmt.setInt(7, registro.getFlag());
			pstmt.setInt(8, RUN_STATUS);
			
			pstmt.executeUpdate();
			
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

	public void deleteProvcharges(AutorotProvchargesVO registro){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(registro.getServerId()-2));
			
			String query = QueryWarehouse.getQuery("arborDeleteProvcharges");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, registro.getRowIdElemento());
			pstmt.setString(2, registro.getExternalId());
			pstmt.setInt(3, registro.getExternalIdType());
			pstmt.setInt(4, registro.getComponentId());
			pstmt.setDate(5, registro.getDtAtivar());
			pstmt.setInt(6, registro.getFlag());
			pstmt.setInt(7, RUN_STATUS);
			
			pstmt.executeUpdate();
			
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

	public int retornaAccountPorInstancia(String externalId){
		
		int account = 0;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("arborRetornaAccount");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, externalId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				account = rs.getInt("account_no");
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
		
		return account;
		
	}
	

}

