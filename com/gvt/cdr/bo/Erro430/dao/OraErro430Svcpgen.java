package com.gvt.cdr.bo.Erro430.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro430.vo.InstanciaVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro430Svcpgen {

	private static OraErro430Svcpgen instance;

	public OraErro430Svcpgen() {

	}
	
	public OraErro430Svcpgen getInstance() {
		if (instance == null)
			instance = new OraErro430Svcpgen();

		return instance;
	}

	public List<InstanciaVO> retornaCasos430(){

		List<InstanciaVO> casos = new ArrayList<InstanciaVO>();

		InstanciaVO reg = null;

		Connection conn = null;

		try{

			//conn = Connections.getConn("svcpgen");
			//conn = Connections.getConn("dgrc");
			conn = Connections.getConn(Connections.CONN_DGRC);

			String query = QueryWarehouse.getQuery("erro430svcpgenRetornaCasos430");

			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				reg = new InstanciaVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setServerId(rs.getInt("msg_id_serv"));
				reg.setDtPrimeiroCdr(rs.getDate("primeiro_cdr"));
				reg.setDtUltimoCdr(rs.getDate("ultimo_cdr"));
				reg.setQtde(rs.getInt("qtde"));

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

	
	public List<InstanciaVO> retornaCasos430DaLoad(){

		List<InstanciaVO> casos = new ArrayList<InstanciaVO>();

		InstanciaVO reg = null;

		Connection conn = null;

		try{

			//conn = Connections.getConn("svcpgen");
			//conn = Connections.getConn("dgrc");
			conn = Connections.getConn(Connections.CONN_DGRC);

			String query = QueryWarehouse.getQuery("erro430svcpgenRetornaCasos430DaLoad");

			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				
				reg = new InstanciaVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setAccountNo(rs.getInt("account_no"));
				reg.setSubscrNo(rs.getInt("subscr_no"));
				reg.setServerId(rs.getInt("serverid"));
				reg.setCiclo(rs.getString("ciclo"));
				reg.setLavoisier(rs.getInt("lavoisier"));
				reg.setDtPrimeiroCdr(rs.getDate("primeirocdr"));
				reg.setDtUltimoCdr(rs.getDate("ultimocdr"));
				reg.setQtde(rs.getInt("qtde"));
				
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
