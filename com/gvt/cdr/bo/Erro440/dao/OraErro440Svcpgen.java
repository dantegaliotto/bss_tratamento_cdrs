package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.AccountVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Svcpgen {

	private static OraErro440Svcpgen instance;

	public OraErro440Svcpgen(){

	}
	
	public OraErro440Svcpgen getInstance() {
		if (instance == null)
			instance = new OraErro440Svcpgen();

		return instance;
	}

	public List<InstanciaSiebelVO> retornaCasos440(String versaoSiebel, int seq, String annotation){
		
		List<InstanciaSiebelVO> casos = new ArrayList<InstanciaSiebelVO>();
		
		InstanciaSiebelVO reg = null;
		
		Connection conn = null;
		
		System.out.println("#####################" + versaoSiebel);
		
		try{
			
			//conn = Connections.getConn("svcpgen");
			//conn = Connections.getConn("dgrc");
			conn = Connections.getConn(Connections.CONN_DGRC);
			
			
			String query = QueryWarehouse.getQuery("erro440svcpgenRetornaCasos440");
			
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			if ((versaoSiebel.compareTo("s8") == 0)||
				(versaoSiebel.compareTo("S8") == 0))
				pstmt.setString(1, "Y");
			else
				pstmt.setString(1, "N");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new InstanciaSiebelVO();
				
				reg.setInstancia(rs.getString("external_id"));
				reg.setSeq(seq);
				reg.setPrimeiroCdr(rs.getDate("primeiro_cdr"));
				reg.setUltimoCdr(rs.getDate("ultimo_cdr"));
				reg.setQtde(rs.getInt("qtde"));
				reg.setQtd440(rs.getInt("qtd440"));
				reg.setQtd430(rs.getInt("qtd430"));
				reg.setRpon(rs.getString("rpon"));
				reg.setDesignador(rs.getString("designador"));
				reg.setStatus(rs.getString("status"));
				reg.setFlgSiebel8(rs.getString("flg_siebel8"));
				reg.setFlgNoBill(rs.getInt("no_bill"));
				reg.setValor(rs.getDouble("valor"));
				reg.setAnnotation(annotation);

				System.out.println("Status:" + reg.getStatus());
				if((! reg.getStatus().equals("Ativo")) || (reg.getStatus() == null)){
					if (versaoSiebel.compareTo("s8") == 0)
						reg.setLog("Erro Siebel 8: Rpon ativo não encontrado");
					else
						reg.setLog("Erro Siebel 5: Rpon ativo não encontrado");
					reg.setResultado(-2);
				}

				casos.add(reg);
				
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
		
		return casos;
		
	}

	
	public List<AccountVO> retornaAccounts440(){
		
		List<AccountVO> casos = new ArrayList<AccountVO>();
		
		AccountVO reg = null;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SVCPGEN);
			//conn = Connections.getConn("svcpgen");
			//conn = Connections.getConn(Connections.CONN_DGRC);
			
			String query = QueryWarehouse.getQuery("erro440svcpgenRetornaAccounts440");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AccountVO();
				
				reg.setAccountNo(rs.getInt("account_no"));
				reg.setServerId(rs.getInt("server_id"));
				casos.add(reg);
				
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
		
		return casos;
		
	}

	
	public void verificaRetryCount(InstanciaSiebelVO instancia) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SVCPGEN);
			
			String query = QueryWarehouse.getQuery("erro440svcpgenVerificaRetryCount");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setLog("Kenan: Instância possui CDRs em erro com rety_count = 10");
				instancia.setResultado(-2);
				
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

