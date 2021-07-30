package com.gvt.cdr.bo.Funcionalidades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class BuscaInfoSiebel8 {
	
	private static BuscaInfoSiebel8 instance;
//	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public BuscaInfoSiebel8() {

	}
	
	public BuscaInfoSiebel8 getInstance() {
		if (instance == null)
			instance = new BuscaInfoSiebel8();

		return instance;
	}
	
	// Check if given string is a number (digits only)
	public static boolean isNumber(String string) {
		return string.matches("^\\d+$");
	}

	public void retornaTipoInstanciaEData(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("siebel8");
			
			String query = QueryWarehouse.getQuery("erro440siebel8RetornaDtInstalacaoETipo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				// alterado para pegar a data da instancia do kenan
				// ALTERAR PARA PEGAR A DATA DO CLIENTE NO SIEBEL 8
				//instancia.setDtInstanciaSiebel(rs.getDate("end_dt"));
				instancia.setTipoInstanciaSiebel("Retail Siebel8");
				
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
	
	
	public void retornaPlanoRetail(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("siebel8");
			
			String query = QueryWarehouse.getQuery("erro440siebel8RetornaProdutoRetail");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			pstmt.setString(2, instancia.getExternalId());
			pstmt.setString(3, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setPlano(rs.getString("name"));
				
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

	public boolean retorna25Ilimitado(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn("siebel8");
			
			String query = QueryWarehouse.getQuery("erro440siebel8Retorna25Ilimitado");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
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

	
}
