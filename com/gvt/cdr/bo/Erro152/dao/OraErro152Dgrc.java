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

public class OraErro152Dgrc{

	private static OraErro152Dgrc instance;

	public OraErro152Dgrc(){

	}
	
	public OraErro152Dgrc getInstance() {
		if (instance == null)
			instance = new OraErro152Dgrc();

		return instance;
		
	}
	
	public void atualizaFlagErro(InstanciaSiebelVO instanciaSiebel) {
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_DGRC);
			
			String query =  " update BSS_TRATAMENTO152_CORP set " +
                            " vercli = '" + instanciaSiebel.getVerCli() + "'," +
                            " veragg = '" + instanciaSiebel.getVerAgg() + "'," +
                            " vercob = '" + instanciaSiebel.getVerCob() + "'," +
                            " verinstancia = '" + instanciaSiebel.getVerInstancia() + "'," +
                            " resultado = '" + instanciaSiebel.getResultado() + "'," +
                            " log = '" + instanciaSiebel.getLog() + "'" +
                            " where processo = 'Erro152' " +
                            " and external_id = '" + instanciaSiebel.getExternalId() + "'" +
                            " and created >= trunc(sysdate)"; 
			
			System.out.println(query);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
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
	
	public List<InstanciaSiebelVO> carregaListaInstanciasSiebel(String codErro) {

		List<InstanciaSiebelVO> listaInstanciasSiebel = new ArrayList<InstanciaSiebelVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			InstanciaSiebelVO reg = null;
			
			conn = Connections.getConn(Connections.CONN_DGRC);
			
			String query =  " select distinct * from BSS_TRATAMENTO152_CORP " +
                            " where processo='Erro152' and " +
                            " resultado in (" + codErro + ")" +
                            " and created = trunc(sysdate) ";
			
			
			System.out.println(query);
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new InstanciaSiebelVO();
				
                reg.setAnnotation (rs.getString	("annotation"));
                reg.setExternalId (rs.getString	("external_id"));
                reg.setDesignador (rs.getString	("designador"));
                reg.setQtde       (rs.getInt	("qtde"));
                reg.setValor      (rs.getDouble	("valor"));
                reg.setPrimeiroCdr(rs.getDate	("primeirocdr"));
                reg.setUltimoCdr  (rs.getDate	("ultimocdr"));
                reg.setContaCli   (rs.getString	("contacli"));
                reg.setContaAgg   (rs.getString	("contaagg"));
                reg.setContaCob   (rs.getString	("contacob"));
                reg.setRowidCli   (rs.getString	("rowidcli"));
                reg.setRowidAgg   (rs.getString	("rowidagg"));
                reg.setRowidCob   (rs.getString	("rowidcob"));
                reg.setRowidIns   (rs.getString	("rowidins"));
                reg.setPorteCli   (rs.getString	("portecli"));
                reg.setResultado  (rs.getInt	("resultado"));
                reg.setLog        (rs.getString	("log"));
			
				listaInstanciasSiebel.add(reg);

				if (contador % 10 == 0){
					Log.info("Load progress: " + contador);
				}
				contador ++;
				
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
		return listaInstanciasSiebel;
	}
	
	public void inserirLogLoad(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_DGRC);
			
			String query = QueryWarehouse.getQuery("erro152dgrcInsertLoad");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
            pstmt.setString (1,  instancia.getAnnotation());
            pstmt.setString (2,  instancia.getExternalId());
            pstmt.setString (3,  instancia.getDesignador());
            pstmt.setInt    (4,  instancia.getQtde());
            pstmt.setDouble (5,  instancia.getValor());
            pstmt.setDate   (6,  instancia.getPrimeiroCdr());
            pstmt.setDate   (7,  instancia.getUltimoCdr());
            pstmt.setString (8,  instancia.getContaCli());
            pstmt.setString (9,  instancia.getContaAgg());
            pstmt.setString (10, instancia.getContaCob());
            pstmt.setString (11, instancia.getRowidCli());
            pstmt.setString (12, instancia.getRowidAgg());
            pstmt.setString (13, instancia.getRowidCob());
            pstmt.setString (14, instancia.getRowidIns());
            pstmt.setString (15, instancia.getPorteCli());
            pstmt.setInt    (16, instancia.getResultado());
            pstmt.setString (17, instancia.getLog());
			
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

	public List<InstanciaSiebelVO> retornaCasos152Corp(String annotation){
		
		List<InstanciaSiebelVO> casos = new ArrayList<InstanciaSiebelVO>();
		
		InstanciaSiebelVO reg = null;
		
		Connection conn = null;
		
		System.out.println("#####################");
		
		try{
			
			conn = Connections.getConn(Connections.CONN_DGRC);
			
			
			String query = QueryWarehouse.getQuery("erro152dgrcRetornaCasos152Corp");
			
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new InstanciaSiebelVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setDesignador(rs.getString("designador"));
				reg.setQtde(rs.getInt("qtde"));
				reg.setValor(rs.getDouble("valor"));
				reg.setPrimeiroCdr(rs.getDate("primeirocdr"));
				reg.setUltimoCdr(rs.getDate("ultimocdr"));

				reg.setAnnotation(annotation);

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

	
}

