package com.gvt.cdr.bo.Erro430.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro430.vo.DuplicidadeVO;
import com.gvt.cdr.bo.Erro430.vo.InstanciaVO;
import com.gvt.cdr.bo.Erro430.vo.DesativacaoVO;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro430Xe{

	private static OraErro430Xe instance;

	public OraErro430Xe() {

	}
	
	public OraErro430Xe getInstance() {
		if (instance == null)
			instance = new OraErro430Xe();

		return instance;
	}

	public void geraQuerysDesativacaoChamado() {

		Connection conn = null;
		
		try{
		
			conn = Connections.getConn("xe");
			String queryLog = "";
			PreparedStatement pstmt = null;
			
			queryLog = QueryWarehouse.getQuery("erro430xeGeraQuerysDesativacaoChamados1");
			pstmt = conn.prepareStatement(queryLog);
			pstmt.executeUpdate();

			queryLog = QueryWarehouse.getQuery("erro430xeGeraQuerysDesativacaoChamados2");
			pstmt = conn.prepareStatement(queryLog);
			pstmt.executeUpdate();

			queryLog = QueryWarehouse.getQuery("erro430xeGeraQuerysDesativacaoChamados3");
			pstmt = conn.prepareStatement(queryLog);
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

	public List<DesativacaoVO> retornaDesativacoesParaSautorot() {

		List<DesativacaoVO> casosDesativacao = new ArrayList<DesativacaoVO>();

		Connection conn = null;
		
		try{
		
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeRetornaDesativacoesParaSautorot");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			DesativacaoVO reg = null;

			while(rs.next()){

				reg = new DesativacaoVO();
				
				reg.setExternalId(rs.getString("external_id"));
				reg.setComponentInst(rs.getString("componentinst"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setCreated(rs.getDate("created"));
				reg.setDtActive(rs.getDate("dtactive"));
				reg.setPrimeiroCdr(rs.getDate("primeirocdr"));
				reg.setDtDesativar(rs.getDate("dtdesativar"));

				casosDesativacao.add(reg);
				
			}
			
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
		
		return casosDesativacao;
	}

	public List<AutorotDesativacaoVO> retornaPlanosDesativarDoLog(){
		
		List<AutorotDesativacaoVO> casos = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;
		
		try{

			AutorotDesativacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeRetornaPlanosDesativarDoLog");
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setDtDesativar(rs.getDate("dt_desativar"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentInstanceId(rs.getInt("component_inst_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setDtEnvio(rs.getDate("dt_envio"));
				reg.setMotivo(rs.getString("motivo"));
 
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

	/* 2014-08-25 */
	public void inserirLogLoad(InstanciaVO instancia) {
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeInsertLogLoad");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1,  instancia.getExternalId()    );
			pstmt.setInt    (2,  instancia.getAccountNo()     );
			pstmt.setInt    (3,  instancia.getSubscrNo()      );
			pstmt.setInt    (4,  instancia.getServerId()      );
			pstmt.setString (5,  instancia.getCiclo()         );
			pstmt.setInt    (6,  instancia.getLavoisier()     );
			pstmt.setDate   (7,  instancia.getDtPrimeiroCdr() );
			pstmt.setDate   (8,  instancia.getDtUltimoCdr()   );
			pstmt.setInt    (9,  instancia.getQtde()          );
			
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

	/* 2014-08-25 */
	public void inserirLogDuplicidade(DuplicidadeVO duplicidade) {
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeInsertLogDuplicidades");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1,  duplicidade.getExternalId      ());
			pstmt.setInt    (2,  duplicidade.getServerId        ()); 
			pstmt.setInt    (3,  duplicidade.getExtIdType       ());
			pstmt.setInt    (4,  duplicidade.getSubscrNo        ());
			pstmt.setInt    (5,  duplicidade.getModLavoisier    ());
			pstmt.setInt    (6,  duplicidade.getTrackingId1     ());
			pstmt.setInt    (7,  duplicidade.getElementId1      ());
			pstmt.setInt    (8,  duplicidade.getComponentId1    ());
			pstmt.setInt    (9,  duplicidade.getPackageId1      ());
			pstmt.setInt    (10, duplicidade.getComponentInstId1());
			pstmt.setDate   (11, duplicidade.getActiveDt1       ());
			pstmt.setDate   (12, duplicidade.getInactiveDt1     ());
			pstmt.setInt    (13, duplicidade.getTrackingId2     ());
			pstmt.setInt    (14, duplicidade.getElementId2      ());
			pstmt.setInt    (15, duplicidade.getComponentId2    ());
			pstmt.setInt    (16, duplicidade.getPackageId2      ());
			pstmt.setInt    (17, duplicidade.getComponentInstId2());
			pstmt.setDate   (18, duplicidade.getActiveDt2       ());
			pstmt.setDate   (19, duplicidade.getInactiveDt2     ());
			
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

	/* 2014-08-25 */
	public List<AutorotAtivacaoVO> retornaPlanosAtivar() {

		List<AutorotAtivacaoVO> autorotAtivacoes = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{
			
			AutorotAtivacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeRetornaPlanosAtivar");
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			//pstmt.setString(1, seq);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setServerId(rs.getInt("serverid"));
				reg.setExternalIdType(rs.getInt("external_id_type"));
				reg.setSubscrNo(rs.getInt("subscr_no"));
				reg.setSiebelComponentDescription("");
				reg.setElementId(rs.getString("element_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setModLavoisier(rs.getInt("mod_lavoisier"));
				reg.setJurisdiction(rs.getInt("jurisdiction"));
				reg.setUnits(rs.getInt("units"));
				reg.setNivelKenan(rs.getString("nivelkenan"));
				reg.setDtAtivacao(rs.getDate("dt_ativar"));
				
				autorotAtivacoes.add(reg);
				
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
		return autorotAtivacoes;

	}

	/* 2014-08-25 */
	public void insertBssLogAtivacoesAutorot(AutorotAtivacaoVO caso) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeInsertLogAtivacao");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString (1,  caso.getExternalId    ());
			pstmt.setInt    (2,  caso.getExternalIdType());
			pstmt.setInt    (3,  caso.getSubscrNo      ());
			pstmt.setInt    (4,  caso.getPackageId     ());
			pstmt.setInt    (5,  caso.getComponentId   ());
			pstmt.setDate   (6,  caso.getDtAtivacao    ());
			pstmt.setInt    (7,  caso.getServerId      ());
			pstmt.setInt    (8,  caso.getFlag          ());
			pstmt.setString (9, caso.getMotivo        ());
			pstmt.setInt    (10, caso.getModLavoisier  ()); 
			pstmt.setInt    (11, caso.getJurisdiction  ());
			pstmt.setInt    (12, caso.getUnits         ());

			
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

	
	public List<AutorotDesativacaoVO> retornaPlanosDesativar(){
		
		List<AutorotDesativacaoVO> listaDesativacoes = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;

		try{
			
			AutorotDesativacaoVO reg = null;
			conn = Connections.getConn("xe");
			String query = null;

			PreparedStatement pstmt = null;
		
			// desativa planos (retail),(duplicados),(erro de elementos),(data passível de ser retroagida)
			query = QueryWarehouse.getQuery("erro430xeRetornaPlanosDesativar");
			pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setInstancia(rs.getString("instancia"));
				reg.setExternalId(rs.getString("external_id"));
				reg.setExternalIdType(rs.getInt("external_id_type"));
				reg.setDtDesativar(rs.getDate("dt_desativar"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrno"));
				reg.setComponentInstanceId(rs.getInt("component_inst_id"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setMotivo(rs.getString("motivo"));
				reg.setArborComponentDescription(rs.getString("arborcomponentdescription"));
				reg.setNivelKenan("Instância");

				listaDesativacoes.add(reg);
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
		return listaDesativacoes;
		
	}

	public void insertBss430Desativacoes(AutorotDesativacaoVO desativacao) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeInsertLogDesativacao");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

		    pstmt.setString (1, desativacao.getExternalId());
		    pstmt.setInt    (2, desativacao.getSubscrNo());
			pstmt.setDate   (3, desativacao.getDtDesativar());
			pstmt.setInt    (4, desativacao.getPackageId());
			pstmt.setInt    (5, desativacao.getComponentInstanceId());
			pstmt.setInt    (6, desativacao.getComponentId());
			pstmt.setInt    (7, desativacao.getServerId());
		    pstmt.setString (8, desativacao.getMotivo());
		    pstmt.setInt    (9, desativacao.getExternalIdType());
			
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

	public List<AutorotAtivacaoVO> retornaPlanosAtivarDoLog(){
		
		List<AutorotAtivacaoVO> casos = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{

			AutorotAtivacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeRetornaPlanosAtivarDoLog");
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setExternalIdType(rs.getInt("external_id_type"));
				reg.setDtAtivacao(rs.getDate("dt_ativacao"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setDtEnvio(rs.getDate("dt_envio"));
				reg.setFlag(rs.getInt("flag"));
				reg.setMotivo(rs.getString("motivo"));
				reg.setModLavoisier(rs.getInt("mod_lavoisier"));
				reg.setJurisdiction(rs.getInt("jurisdiction"));
				reg.setUnits(rs.getInt("units"));

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

	public void atualizaLogAtivacaoEnviado(AutorotAtivacaoVO caso, int resultado) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeUpdateLogAtivacaoFlagEnvio");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, resultado);
			pstmt.setString(2, caso.getExternalId());
			pstmt.setInt(3, caso.getComponentId());
			
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

	public void atualizaLogDesativacaoEnviado(AutorotDesativacaoVO caso, int resultado) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro430xeUpdateLogDesativacaoFlagEnvio");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, resultado);
			pstmt.setString(2, caso.getExternalId());
			pstmt.setInt(3, caso.getComponentId());
			pstmt.setInt(4,caso.getComponentInstanceId());
			
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

}
