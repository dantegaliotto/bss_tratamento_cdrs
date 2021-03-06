package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Siebel8 {
	
	private static OraErro440Siebel8 instance;
//	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public OraErro440Siebel8() {

	}
	
	public OraErro440Siebel8 getInstance() {
		if (instance == null)
			instance = new OraErro440Siebel8();

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
			Log.error("Erro na execu??o", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conex?o", ex);
			}
		}
		
	}
	
	
	public void retornaPlanoRetail(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("siebel8");
			
			String query = QueryWarehouse.getQuery("erro440siebel8RetornaProdutoRetail");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			pstmt.setString(2, instancia.getInstancia());
			pstmt.setString(3, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("R");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execu??o", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conex?o", ex);
			}
		}
		
	}

	public void retornaPlanoRetailPelaOferta(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("siebel8");
			
			String query = QueryWarehouse.getQuery("erro440siebel8RetornaProdutoRetailPelaOferta");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setRowIdElementoPlano(rs.getString("row_id"));
				instancia.setPlano(rs.getString("name"));
				instancia.setDataPlanoSiebel(rs.getDate("eff_end_dt"));
				instancia.setTipoPlano("R");
				
			}
				
			rs.close();
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execu??o", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conex?o", ex);
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
			Log.error("Erro na execu??o", ex);
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conex?o", ex);
			}
		}
		return retorno;
		
	}

	
}
