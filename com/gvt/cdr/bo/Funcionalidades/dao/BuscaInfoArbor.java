package com.gvt.cdr.bo.Funcionalidades.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia152Vox800RIUFVO;
import com.gvt.cdr.bo.Funcionalidades.vo.Instancia440RetailVO;
import com.gvt.cdr.dao.OraArbor;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class BuscaInfoArbor extends OraArbor{
	
	private static BuscaInfoArbor instance;
	
	public BuscaInfoArbor(){

	}
	
	public BuscaInfoArbor getInstance() {
		if (instance == null)
			instance = new BuscaInfoArbor();

		return instance;
		
	}

	public int buscaServerId (String externalId) {
		if (this.verificaServerId(externalId,1)){
			return(3);
		}else if (this.verificaServerId(externalId,2)){
			return(4);
		}else{
			return(0);
		}
	}

	public void buscaGuiador(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			if(instancia.getServerId() != 3 && instancia.getServerId() != 4) return;
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
						
			String query = QueryWarehouse.getQuery("KenanBuscaDataGuiadorDoUso");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,instancia.getTipoUso());
			pstmt.setInt(2,instancia.getTipoUso());
			pstmt.setString(3, instancia.getExternalId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setDtGuiador(rs.getDate("data_guiador"));
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

	public void buscaConta(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			if(instancia.getServerId() != 3 && instancia.getServerId() != 4) return;
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
						
			String query = QueryWarehouse.getQuery("KenanBuscaConta");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setConta(rs.getString("conta"));
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


	public void buscaInstanciaIrmaComGuiador(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			if(instancia.getServerId() != 3 && instancia.getServerId() != 4) return;
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
						
			String query = QueryWarehouse.getQuery("KenanBuscaInstanciaIrmaComGuiador");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,instancia.getTipoUso());
			pstmt.setInt(2,instancia.getTipoUso());
			pstmt.setString(3, instancia.getExternalId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setIrmaComGuiador(rs.getString("instancia_irma"));
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

	
	public void buscaGuiadorInativo(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			if(instancia.getServerId() != 3 && instancia.getServerId() != 4) return;
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
						
			String query = QueryWarehouse.getQuery("KenanBuscaDataGuiadorInativo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setDate(1, instancia.getPrimeiroCdr());
			pstmt.setInt(2,instancia.getTipoUso());
			pstmt.setString(3, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setDtGuiadorInativo1(rs.getDate("dt_ativacao"));
				instancia.setDtGuiadorInativo2(rs.getDate("dt_inativacao"));
				instancia.setOrdemAtivacao(rs.getString("ordem_ativ"));
				instancia.setOrdemInativacao(rs.getString("ordem_inat"));
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

	public void buscaQtdeServicos(Instancia440RetailVO instancia){
		
		Connection conn = null;
		
		try{
			
			if(instancia.getServerId() != 3 && instancia.getServerId() != 4) return;
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));

			String query = QueryWarehouse.getQuery("KenanBuscaQtdeServicos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				//instancia.setQtdeServicos(rs.getInt("qtde_servicos"));
				
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

	public void retornaUfCorreta(Instancia152Vox800RIUFVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("KenanBuscaUfCorreta");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId().substring(0,10));
//			System.out.println(instancia.getExternalId().substring(0,10));
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setExternalIdCorreto(rs.getString("external_id"));
				
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

