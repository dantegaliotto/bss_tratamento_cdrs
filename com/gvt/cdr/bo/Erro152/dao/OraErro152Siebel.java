package com.gvt.cdr.bo.Erro152.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro152.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro152Siebel {
	
	private static OraErro152Siebel instance;
	//private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public OraErro152Siebel() {

	}
	
	public OraErro152Siebel getInstance() {
		if (instance == null)
			instance = new OraErro152Siebel();

		return instance;
	}
	
	
	public void buscaInformacoesContas(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_SIEBEL);
			
			String query = QueryWarehouse.getQuery("erro152siebelBuscaInfoContas");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getDesignador());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				// alterado para pegar a data da instancia do kenan
				instancia.setContaCli(rs.getString("cliente"));
				instancia.setContaAgg(rs.getString("agregadora"));
				instancia.setContaCob(rs.getString("cobranca"));

				instancia.setPorteCli(rs.getString("portecli"));
				instancia.setRowidCli(rs.getString("rowidcli"));
				instancia.setRowidAgg(rs.getString("rowidagg"));
				instancia.setRowidCob(rs.getString("rowidcob"));
				instancia.setRowidIns(rs.getString("rowid_instancia"));
				
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
