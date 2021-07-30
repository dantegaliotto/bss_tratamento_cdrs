package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Aiparch {
	
	private OraErro440Siebel siebel = new OraErro440Siebel().getInstance();

	private static OraErro440Aiparch instance;

	public OraErro440Aiparch() {

	}
	
	public OraErro440Aiparch getInstance() {
		if (instance == null)
			instance = new OraErro440Aiparch();

		return instance;
	}
	
	public int retornaComponentId(String produto){
		
		int component = 0;
		
		Connection conn = null;
		
		try{
			
			//conn = Connections.getConn(Connections.CONN_AIPARCH);
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro440aiparchRetornaComponent");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, produto);
			pstmt.setString(2, produto);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				component = rs.getInt("arborcomponentid");
				
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
		
		return component;
		
	}

	public int atualizaDadosBpoInstancia(InstanciaSiebelVO instanciaSiebel) {

		System.out.println("atualiza dados instancia no aiparch");
		List<String> listaRpons = siebel.buscaRponsDaInstancia(instanciaSiebel);
		int retorno = -1;

		for (int i = 0; i < listaRpons.size(); i++){
			this.updateBpoPeloRpon(listaRpons.get(i));
			retorno = 1;
		}
		
		return retorno;
		
	}


	private void updateBpoPeloRpon(String rpon) {

		Connection conn = null;

		String currentState = buscaCurrentState(rpon);
		
		if (currentState == ""){
			
			try{
				
				conn = Connections.getConn("aiparch_marconatto");
				
				String query = QueryWarehouse.getQuery("erro440arborAtualizaInfoInstanciaBpo");
				
				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, currentState);
				pstmt.setString(2, rpon);

				pstmt.executeUpdate();
				
				pstmt.close();
							
			}catch(Exception ex){
				Log.error("Erro na execução", ex);
				return;
			}finally{
				try{
					conn.close();
				}catch(Exception ex){
					Log.error("Falha ao fechar conexão", ex);
				}
			}
		}
		return;
	}

	private String buscaCurrentState(String rpon) {

		Connection conn = null;
		
		String currentState = "";
		
		try{
			
			conn = Connections.getConn(Connections.CONN_AIPARCH);
			
			String query = QueryWarehouse.getQuery("erro440aiparchBuscaCurrentState");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, rpon);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				currentState = rs.getString("CURRENT_STATE");
				
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
		
		return currentState;
	}

}
