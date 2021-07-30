package com.gvt.cdr.bo.Erro440.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.AccountVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.cdr.bo.Erro440.vo.ProdutoArborVO;
import com.gvt.cdr.bo.Erro440.vo.TrocaCmfNoBillVO;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;

public class OraErro440Xe{

	private static OraErro440Xe instance;

	public OraErro440Xe(){

	}
	
	public OraErro440Xe getInstance() {
		if (instance == null)
			instance = new OraErro440Xe();

		return instance;
		
	}

	public void inserirLogSiebel(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogSiebel");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt    (1, instancia.getSeq());
			pstmt.setString (2, instancia.getAnnotation());
			pstmt.setString (3, instancia.getInstancia());
			pstmt.setString (4, instancia.getDesignador());
			pstmt.setString (5, instancia.getRpon());
			pstmt.setInt    (6, instancia.getAccountNo());
			pstmt.setString (7, instancia.getExtIdAccountNo());
			pstmt.setInt    (8, instancia.getServerId());
			pstmt.setString (9, instancia.getCiclo());
			pstmt.setDate   (10, instancia.getPrimeiroCdr());
			pstmt.setDate   (11, instancia.getUltimoCdr());
			pstmt.setInt    (12, instancia.getQtde());
			pstmt.setDate   (13, instancia.getDtInstanciaKenan());
			pstmt.setDate   (14, instancia.getDtInstanciaSiebel());
			pstmt.setDate   (15, instancia.getDataPlanoSiebel());
			pstmt.setString (16, instancia.getTipoPlano());
			pstmt.setString (17, instancia.getPlano());
			pstmt.setString (18, instancia.getCspSiebel());
			pstmt.setString (19, instancia.getSlocal());
			pstmt.setString (20, instancia.getSvc());
			pstmt.setString (21, instancia.getSldn());
			pstmt.setString (22, instancia.getSldi());
			pstmt.setInt    (23, instancia.getComponentId());
			pstmt.setInt    (24, instancia.getCspIdSiebel());
			pstmt.setInt    (25, instancia.getCspIdKenan());
			pstmt.setInt    (26, instancia.getParComponentIdLocal());
			pstmt.setInt    (27, instancia.getParComponentIdVc());
			pstmt.setInt    (28, instancia.getParComponentIdLdn());
			pstmt.setInt    (29, instancia.getParComponentIdLdi());
			pstmt.setInt    (30, instancia.getLavoisier());
			pstmt.setString (31, instancia.getFranquiaSiebel());
			pstmt.setInt    (32, instancia.getFranquiaIdSiebel());
			pstmt.setInt    (33, instancia.getFranquiaIdKenan());
			pstmt.setString (34, instancia.getFranquiaConta());
			pstmt.setInt    (35, instancia.getCompIdFranquiaConta());
			pstmt.setString (36, instancia.getCspConta());
			pstmt.setInt    (37, instancia.getCompIdCspConta());
			pstmt.setInt    (38, instancia.getResultado());
			pstmt.setInt    (39, instancia.getResultadoInicial());
			pstmt.setString (40, instancia.getLog());
			pstmt.setString (41, instancia.getTipoInstanciaSiebel());
			pstmt.setString (42, instancia.getRowIdElementoPlano());
			pstmt.setDate   (43, instancia.getDtUltimoFaturamento());
			pstmt.setDate   (44, instancia.getDataCspSiebel());
			pstmt.setInt    (45, instancia.getQtd440());
			pstmt.setInt    (46, instancia.getQtd430());
			pstmt.setString (47, instancia.getLogView());
			pstmt.setDouble (48, instancia.getValor());
			pstmt.setInt    (49, instancia.getAccountNoB());
			pstmt.setInt    (50, instancia.getParentId());
			pstmt.setInt    (51, instancia.getHierarchyId());
			
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

	public void inserirLogExecucoes(String etapa, String obs){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogExecucoes");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, etapa);
			pstmt.setString(2, obs);
			
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
	
	public void inserirLogLoad(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogLoad");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt    (1,  instancia.getSeq());
			pstmt.setString (2,  instancia.getAnnotation());
			pstmt.setString (3,  instancia.getInstancia());
			pstmt.setString (4,  instancia.getDesignador());
			pstmt.setString (5,  instancia.getRpon());
			pstmt.setDate   (6,  instancia.getPrimeiroCdr());
			pstmt.setDate   (7,  instancia.getUltimoCdr());
			pstmt.setInt    (8,  instancia.getQtde());
			pstmt.setString (9,  instancia.getLog());
			pstmt.setInt    (10, instancia.getResultado());
			pstmt.setString (11, instancia.getStatus());
			pstmt.setInt    (12, instancia.getQtd440());
			pstmt.setInt    (13, instancia.getQtd430());
			pstmt.setString (14, instancia.getFlgSiebel8());
			pstmt.setInt    (15, instancia.getFlgNoBill());
			pstmt.setDouble (16, instancia.getValor());
			
			
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

	public void inserirLogTrocasCmf(AccountVO account){
		
		Connection conn = null;
		
		if (account.getTrocasCmf().size() == 0)
			return;
		
		for (int i = 0 ; i < account.getTrocasCmf().size(); i++){
		
			TrocaCmfNoBillVO trocaCmf = account.getTrocasCmf().get(i);
			
			try{
			
				conn = Connections.getConn("xe");
			
				String query = QueryWarehouse.getQuery("erro440xeInsertTrocaCmf");
			
				PreparedStatement pstmt = conn.prepareStatement(query);

				//(account_no, quando, old_no_bill, new_no_bill, remark)
				
				pstmt.setInt    (1,  trocaCmf.getAccountNo());
				pstmt.setDate   (2, trocaCmf.getQuando());
				pstmt.setInt    (3,  trocaCmf.getOldNoBill());
				pstmt.setInt    (4,  trocaCmf.getNewNoBill());
				pstmt.setInt    (5,  trocaCmf.getServerId());
				pstmt.setString (6,  trocaCmf.getRemark());
			
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
	
	public void inserirTrocaNoBill(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogLoad");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString (1,  instancia.getInstancia());
			pstmt.setDate   (2,  instancia.getPrimeiroCdr());
			pstmt.setDate   (3,  instancia.getUltimoCdr());
			pstmt.setInt    (4,  instancia.getQtde());
			pstmt.setString (5,  instancia.getDesignador());
			pstmt.setString (6,  instancia.getRpon());
			pstmt.setString (7,  instancia.getLog());
			pstmt.setInt    (8,  instancia.getResultado());
			pstmt.setString (9,  instancia.getStatus());
			pstmt.setString (10, instancia.getAcao());
			pstmt.setInt    (11, instancia.getQtd440());
			pstmt.setInt    (12, instancia.getQtd430());
			pstmt.setString (13, instancia.getFlgSiebel8());
			pstmt.setInt    (14, instancia.getFlgNoBill());
			
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
	
	public void inserirLogLoadDia(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogLoadDia");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			pstmt.setInt(2,instancia.getTipoUso());
			pstmt.setDate(3,instancia.getDiaCarga());
			pstmt.setInt(4,instancia.getQtde());
			pstmt.setString(5, instancia.getDesignador());
			pstmt.setString(6, instancia.getRpon());
			pstmt.setString(7, instancia.getLog());
			pstmt.setString(8, instancia.getResult());
			
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
	
	public void gravaResultadoDesativacaoAutorotXe(AutorotDesativacaoVO desativacao){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateResultadoDesativacaoAutorotXe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, desativacao.getResultado());
			pstmt.setInt(2, desativacao.getComponentId());
			pstmt.setString(3, desativacao.getExternalId());
			
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

	public void gravaResultadoAtivacaoAutorotXe(AutorotAtivacaoVO ativacao){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateResultadoAtivacaoAutorotXe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, ativacao.getResultado());
			pstmt.setInt(2, ativacao.getComponentId());
			pstmt.setString(3, ativacao.getExternalId());
			
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
	
	public void inserirLogArbor(String instanciaSiebel, InstanciaArborVO instancia, int seq){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogArbor");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt    (1, seq);
			pstmt.setString (2, instanciaSiebel);
			pstmt.setString (3, instancia.getExternalId());
			pstmt.setDate   (4, instancia.getDtInstancia());
			pstmt.setInt    (5, instancia.getSubscrNo());
			pstmt.setInt    (6, instancia.getResultado());
			pstmt.setString (7, instancia.getLog());

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

	public void inserirLogPlanoArbor(String instanciaSiebel, PlanoArborVO planoArbor, int serverId, int sequencial){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogPlanoArbor");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt    (1, planoArbor.getSeq());
			pstmt.setString (2, instanciaSiebel);
			pstmt.setString (3, planoArbor.getExternalId());
			pstmt.setInt    (4, serverId);
			pstmt.setInt    (5, planoArbor.getSubscrNo());
			pstmt.setInt    (6, planoArbor.getComponentId());
			pstmt.setDate   (7, planoArbor.getDtPlano());
			pstmt.setInt    (8, planoArbor.getComponentInstId());
			pstmt.setInt    (9, planoArbor.getPackageId());
			pstmt.setString (10, planoArbor.getArborComponentDescription());
			pstmt.setString (11, planoArbor.getTipoPlano());
			pstmt.setInt    (12, planoArbor.getValidacaoElementos());
			pstmt.setString (13, planoArbor.getNivelKenan());

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

	public void inserirLogProdutoArbor(String instanciaSiebel, String externalIdArbor, ProdutoArborVO produtoArbor, int serverId){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogProdutoArbor");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel);
			pstmt.setString(2, externalIdArbor);
			pstmt.setInt(3, produtoArbor.getComponentId());
			pstmt.setInt(4, produtoArbor.getElementId());
			pstmt.setInt(5, produtoArbor.getSubscrNo());
			pstmt.setDate(6, produtoArbor.getDtProduto());
			pstmt.setInt(7, serverId);

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
	
	public String verificaExistenciaDeProduto(InstanciaSiebelVO instanciaSiebel, int element_id){
		
		String instanciaComElemento = "";
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("raidProcuraObjeto");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, instanciaSiebel.getInstancia());
			pstmt.setInt(2, element_id);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				instanciaComElemento = rs.getString("external_id");
			
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
		return instanciaComElemento;
		
	}
	
	public List<AutorotDesativacaoVO> retornaPlanosDesativarPorInstancia(InstanciaSiebelVO instanciaSiebel){
		
		List<AutorotDesativacaoVO> listaDesativacoes = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;

		if ((""+instanciaSiebel.getTipoPlano()).equals("null"))
			return listaDesativacoes;

		try{
			
			AutorotDesativacaoVO reg = null;
			conn = Connections.getConn("xe");
			String query = null;

			PreparedStatement pstmt = null;
		
			if ((""+instanciaSiebel.getTipoPlano()).equals("C")){
				query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarPorInstanciaCorporate");
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, instanciaSiebel.getInstancia());
				pstmt.setString(2, instanciaSiebel.getInstancia());
				pstmt.setString(3, instanciaSiebel.getInstancia());
			} else if ((""+instanciaSiebel.getTipoPlano()).equals("R")){
					query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarPorInstanciaRetail");
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, instanciaSiebel.getInstancia());
			}

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setInstancia(rs.getString("instancia"));
				reg.setExternalId(rs.getString("external_id"));
				reg.setDtDesativar(rs.getDate("dt_desativar"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrNO"));

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

	public List<AutorotDesativacaoVO> retornaPlanosDesativar(String tipoPlano, String seq){
		
		List<AutorotDesativacaoVO> listaDesativacoes = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;

		try{
			
			AutorotDesativacaoVO reg = null;
			conn = Connections.getConn("xe");
			String query = null;

			PreparedStatement pstmt = null;
		
			if (tipoPlano.equals("C")){
				query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarCorporate");
				pstmt = conn.prepareStatement(query);
			} else if (tipoPlano.equals("RD")){
				// desativa planos (retail),(duplicados),(erro de elementos),(data passível de ser retroagida)
				query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarRetailEDuplicados");
				pstmt = conn.prepareStatement(query);
			} else if (tipoPlano.equals("RS8_CONTA")){
				// desativa planos (retail),(duplicados),(erro de elementos),(data passível de ser retroagida)
				query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarSiebel8NivelConta");
				pstmt = conn.prepareStatement(query);
			}

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
				reg.setNivelKenan(rs.getString("nivelKenan"));

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
	
	public List<AutorotAtivacaoVO> retornaPlanosAtivarPorInstancia(InstanciaSiebelVO instanciaSiebel){
		
		List<AutorotAtivacaoVO> autorotAtivacoes = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{
			
			AutorotAtivacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosAtivarPorInstancia");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, instanciaSiebel.getInstancia());
			pstmt.setString(2, instanciaSiebel.getInstancia());
			pstmt.setString(3, instanciaSiebel.getInstancia());
			pstmt.setString(4, instanciaSiebel.getInstancia());
			pstmt.setString(5, instanciaSiebel.getInstancia());
			pstmt.setString(6, instanciaSiebel.getInstancia());
			pstmt.setString(7, instanciaSiebel.getInstancia());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setInstancia(rs.getString("instancia"));
				reg.setElementId(rs.getString("element_id"));
				reg.setExternalIdType(rs.getInt("external_id_type")); //
				reg.setExternalId(rs.getString("external_id"));
				reg.setDtAtivacao(rs.getDate("dt_ativar"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrNO"));
				reg.setFlag(rs.getInt("flag"));

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
	
	public List<AutorotAtivacaoVO> retornaPlanosAtivar(String seq){
		
		List<AutorotAtivacaoVO> autorotAtivacoes = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{
			
			AutorotAtivacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosAtivar");
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			//pstmt.setString(1, seq);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setSeq(seq);
				reg.setInstancia(rs.getString("instancia"));
				reg.setElementId(rs.getString("element_id"));
				reg.setExternalIdType(rs.getInt("external_id_type")); //
				reg.setExternalId(rs.getString("external_id"));
				reg.setDtAtivacao(rs.getDate("dt_ativar"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrNO"));
				reg.setFlag(rs.getInt("flag"));
				reg.setMotivo(rs.getString("motivo"));
				reg.setSiebelComponentDescription(rs.getString("siebelcomponentdescription"));
				reg.setNivelKenan(rs.getString("nivelkenan"));
				reg.setModLavoisier(rs.getInt("mod_lavoisier"));
				reg.setJurisdiction(rs.getInt("jurisdiction"));
				reg.setUnits(rs.getInt("units"));

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

	public List<AutorotDesativacaoVO> retornaPlanosDesativarDoLogOld(){
		
		List<AutorotDesativacaoVO> casos = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarDoLog");
			
			conn = Connections.getConn("xe");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			AutorotDesativacaoVO reg = null;
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setDtDesativar(rs.getDate("DT_DESATIVAR"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentInstanceId(rs.getInt("component_inst_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setDtEnvio(rs.getDate("dt_envio"));

				casos.add(reg);
				
			}
			
			rs.close();
			stmt.close();
			
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

	public List<AutorotDesativacaoVO> retornaPlanosDesativarDoLog(){
		
		List<AutorotDesativacaoVO> casos = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;
		
		try{

			AutorotDesativacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarDoLog");
			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, instanciaSiebel.getInstancia());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setDtDesativar(rs.getDate("DT_DESATIVAR"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentInstanceId(rs.getInt("component_inst_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setDtEnvio(rs.getDate("dt_envio"));
				reg.setMotivo(rs.getString("motivo"));
				reg.setArborComponentDescription(rs.getString("arborcomponentdescription"));
				reg.setNivelKenan(rs.getString("nivelkenan"));
				reg.setExternalIdType(rs.getInt("external_id_type"));

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

	public List<AutorotAtivacaoVO> retornaPlanosAtivarDoLog(){
		
		List<AutorotAtivacaoVO> casos = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{

			AutorotAtivacaoVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosAtivarDoLog");
			PreparedStatement pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, instanciaSiebel.getInstancia());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setElementId(rs.getString("element_id"));
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
				reg.setSiebelComponentDescription(rs.getString("siebelComponentDescription"));

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
	
	public List<AutorotAtivacaoVO> retornaPlanosAtivarDoLogX(){
		
		List<AutorotAtivacaoVO> casos = new ArrayList<AutorotAtivacaoVO>();
		
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosAtivarDoLog");
			
			conn = Connections.getConn("xe");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			AutorotAtivacaoVO reg = null;
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setElementId(rs.getString("element_id"));
				reg.setExternalId(rs.getString("external_id"));
				reg.setExternalIdType(rs.getInt("external_id_type"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setDtAtivacao(rs.getDate("DT_ATIVACAO"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setDtEnvio(rs.getDate("dt_envio"));

				casos.add(reg);
				
			}
			rs.close();
			stmt.close();
			
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

	public List<PlanoArborVO> retornaPlanosDesativarParaValidation(){
		
		List<PlanoArborVO> casos = new ArrayList<PlanoArborVO>();
		
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativar");
			
			conn = Connections.getConn("xe");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			PlanoArborVO reg = null;
			
			while(rs.next()){
				
				reg = new PlanoArborVO();
				
				reg.setInstancia(rs.getString("instancia"));
				reg.setExternalId(rs.getString("external_Id"));
				reg.setComponentId(rs.getInt("componentId"));
				reg.setSubscrNo(rs.getInt("subscrno"));
				reg.setServerId(rs.getInt("serverid"));
				
				casos.add(reg);
				
			}
			
			rs.close();
			stmt.close();
			
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
	
	public void updateLogValidacaoPlanosNoArbor(PlanoArborVO plano){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateLogValidacaoPlanosNoArbor");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			/*
			pstmt.setString(1, instancia.getResult());
			pstmt.setString(2, instancia.getInstancia());
			pstmt.setString(3, instancia.getExternalId());
			*/
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
	
	public void insertBssLogDesativacoesAutorot(AutorotDesativacaoVO caso) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogDesativacao");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, caso.getInstancia());
		    pstmt.setString(2, caso.getExternalId());
		    pstmt.setInt(3, caso.getSubscrNo());
			pstmt.setDate(4, caso.getDtDesativar());
			pstmt.setInt(5, caso.getPackageId());
			pstmt.setInt(6, caso.getComponentInstanceId());
			pstmt.setInt(7, caso.getComponentId());
			pstmt.setInt(8, caso.getServerId());
		    pstmt.setString(9, caso.getMotivo());
		    pstmt.setString(10, caso.getArborComponentDescription());
		    pstmt.setString(11, caso.getNivelKenan());
		    pstmt.setInt(12, caso.getExternalIdType());
			
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

	public void insertBssLogAtivacoesAutorot(AutorotAtivacaoVO caso) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInsertLogAtivacao");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, caso.getInstancia());
			pstmt.setString(2, caso.getElementId());
			pstmt.setString(3, caso.getExternalId());
			pstmt.setInt(4, caso.getExternalIdType());
			pstmt.setInt(5, caso.getSubscrNo());
			pstmt.setInt(6, caso.getPackageId());
			pstmt.setInt(7, caso.getComponentId());
			pstmt.setDate(8, caso.getDtAtivacao());
			pstmt.setInt(9, caso.getServerId());
			pstmt.setInt(10, caso.getFlag());
			pstmt.setString(11, caso.getMotivo());
			pstmt.setString(12, caso.getSiebelComponentDescription());
			pstmt.setInt(13, caso.getModLavoisier()); 
			pstmt.setInt(14, caso.getJurisdiction());
			pstmt.setInt(15, caso.getUnits());
			pstmt.setString(16, caso.getNivelKenan());
			
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
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateLogDesativacaoFlagEnvio");
			
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

	public void marcaPlanoComoDesconectado(AutorotDesativacaoVO caso) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdatePlanoDesativado");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, caso.getMotivo());
			pstmt.setString(2, caso.getExternalId());
			pstmt.setInt(3, caso.getComponentId());
			pstmt.setInt(4, caso.getComponentInstanceId());
			
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
	
	public void atualizaLogAtivacaoEnviado(AutorotAtivacaoVO caso, int resultado) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateLogAtivacaoFlagEnvio");
			
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

	public List<AutorotDesativacaoVO> retornaPlanosDesativarParaValidacao(){
		
		List<AutorotDesativacaoVO> casos = new ArrayList<AutorotDesativacaoVO>();
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosDesativarParaValidacao");
			conn = Connections.getConn("xe");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			AutorotDesativacaoVO reg = null;
			
			while(rs.next()){
				
				reg = new AutorotDesativacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrNO"));
				casos.add(reg);
				
			}
			
			rs.close();
			stmt.close();
			
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
	
	public List<AutorotAtivacaoVO> retornaPlanosAtivarParaValidacao(){
		
		List<AutorotAtivacaoVO> casos = new ArrayList<AutorotAtivacaoVO>();
		Connection conn = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaPlanosAtivarParaValidacao");
			conn = Connections.getConn("xe");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			AutorotAtivacaoVO reg = null;
			
			while(rs.next()){
				
				reg = new AutorotAtivacaoVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setServerId(rs.getInt("server_id"));
				reg.setSubscrNo(rs.getInt("subscrNO"));
				casos.add(reg);
				
			}
			
			rs.close();
			stmt.close();
			
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
	
	public List<InstanciaSiebelVO> carregaListaInstanciasSiebel(String codErro, String sequencial) {

		List<InstanciaSiebelVO> listaInstanciasSiebel = new ArrayList<InstanciaSiebelVO>();
		
		Connection conn = null;
		
		int contador = 0;
		
		try{

			InstanciaSiebelVO reg = null;
			
			conn = Connections.getConn("xe");
			
			String query =  " select distinct * from bss_tratamento_cdrs " +
			                " where processo='Erro440' and " +
			                " resultado in (" + codErro + ")" +
			                " and serverid >= 3 " +
            				" and created = trunc(sysdate) " +
            				" and seq = '" + sequencial + "'" ;

			System.out.println(query);
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reg = new InstanciaSiebelVO();
				
				reg.setInstancia(rs.getString("external_id"));
				reg.setAccountNo(rs.getInt("account_No"));
				reg.setCiclo(rs.getString("ciclo"));
				reg.setDesignador(rs.getString("designador"));
				reg.setPrimeiroCdr(rs.getDate("primeiroCdr"));
				reg.setUltimoCdr(rs.getDate("ultimoCdr"));
				reg.setQtde(rs.getInt("qtde"));
				reg.setRpon(rs.getString("rpon"));
				reg.setDtInstanciaKenan(rs.getDate("data_instancia_kenan"));
				reg.setDtInstanciaSiebel(rs.getDate("data_instancia_siebel"));
				reg.setPlano(rs.getString("plano"));
				reg.setCspSiebel(rs.getString("csp_siebel"));
				reg.setDataPlanoSiebel(rs.getDate("data_Plano_siebel"));
				reg.setTipoPlano(rs.getString("tipoplano"));
				reg.setSlocal(rs.getString("local"));
				reg.setSvc(rs.getString("vc"));
				reg.setSldn(rs.getString("ldn"));
				reg.setSldi(rs.getString("ldi"));
				reg.setComponentId(rs.getInt("componentId"));
				reg.setCspIdSiebel(rs.getInt("cspid_siebel"));
				reg.setCspIdKenan(rs.getInt("cspid_kenan"));
				reg.setParComponentIdLocal(rs.getInt("parComponentIdLocal"));
				reg.setParComponentIdVc(rs.getInt("parComponentIdVc"));
				reg.setParComponentIdLdn(rs.getInt("parComponentIdLdn"));
				reg.setParComponentIdLdi(rs.getInt("parComponentIdLdi"));
				reg.setServerId(rs.getInt("serverId"));
				reg.setLog(rs.getString("log"));
				//reg.setFlgBpo(rs.getInt("flg_bpo"));
				reg.setResultado(rs.getInt("resultado"));
				reg.setFranquiaConta(rs.getString("franquia_n_conta"));
				reg.setCompIdFranquiaConta(rs.getInt("comp_id_franquia"));
				reg.setCspConta(rs.getString("csp_n_conta"));
				reg.setCompIdCspConta(rs.getInt("comp_id_csp_conta"));
				reg.setTipoInstanciaSiebel(rs.getString("tipo_instancia_siebel"));
				reg.setDtUltimoFaturamento(rs.getDate("data_ultimo_faturamento"));
				//reg.setInstanciasArbor(carregaListaInstanciasArbor(reg.getInstancia()));
			
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

	public List<InstanciaArborVO> carregaListaInstanciasArbor(String instancia) {

		List<InstanciaArborVO> listaInstanciasArbor = new ArrayList<InstanciaArborVO>();
		
		Connection conn = null;
		
		try{

			InstanciaArborVO reg = null;

			conn = Connections.getConn("xe");

			String query = " select * from bss_tratamento_cdrs_range	" +
					       "  where processo='Erro440' " +
					       "    and instancia = '" + instancia +"'" +
					       "    and created = trunc(sysdate) " + 
					       "    and subscrno is not null and subscrno <> 0 " +
					       "    and (resultado is null or resultado <> -2)"; 
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				reg = new InstanciaArborVO();

				reg.setExternalId(rs.getString("external_id"));
				reg.setDtInstancia(rs.getDate("dtInstancia"));
				reg.setSubscrNo(rs.getInt("subscrno"));
				reg.setResultado(rs.getInt("resultado"));
				reg.setLog(rs.getString("log"));

				//reg.setPlanosArbor(carregaListaPlanosArbor(reg.getExternalId()));
				
				listaInstanciasArbor.add(reg);
				
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
		return listaInstanciasArbor;
	
	}

	public List<InstanciaArborVO> carregaListaInstanciasArborParaIncorrigiveis(InstanciaSiebelVO instanciaSiebel) {

		List<InstanciaArborVO> listaInstanciasArbor = new ArrayList<InstanciaArborVO>();
		
		Connection conn = null;
		
		try{

			InstanciaArborVO reg = null;

			conn = Connections.getConn("xe");

			String query = " select * from bss_tratamento_cdrs_load " +
						   " where created = trunc(sysdate) " +
						   " and rpon = '" + instanciaSiebel.getRpon() + "'";
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				reg = new InstanciaArborVO();

				reg.setExternalId(rs.getString("external_id"));
				
				listaInstanciasArbor.add(reg);
				
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
		return listaInstanciasArbor;
	
	}

	public void atualizaFlagEnvioInterface(InstanciaSiebelVO instanciaSiebel, int resultadoAiparch) {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateFlagEnvioBpo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, instanciaSiebel.getInstancia());
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
		return;
		
	}

	public void atualizaFlagErro(String sequencial, InstanciaSiebelVO instanciaSiebel, int erro, String resultadoPlanos) {
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query =  " update bss_tratamento_cdrs set " +
							" log = '" + resultadoPlanos + "'," +							
					        " resultado = " + erro +
					        " where processo='Erro440' and " +
			                " external_id = '" + instanciaSiebel.getInstancia() + "'" +
			                " and rpon = '" + instanciaSiebel.getRpon() + "'" +
			                " and created >= trunc(sysdate)" +
			                " and seq = '" + sequencial + "'"; 
			
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
	
	public List<InstanciaSiebelVO> retornaCasos440Load(int seq, String annotation) {

		List<InstanciaSiebelVO> casos = new ArrayList<InstanciaSiebelVO>();
		
		InstanciaSiebelVO reg = null;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaCasos440");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seq);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){

				reg = new InstanciaSiebelVO();
				
				reg.setRpon(rs.getString("rpon"));
				reg.setDesignador(rs.getString("designador"));
				reg.setInstancia(rs.getString("external_id"));
				reg.setPrimeiroCdr(rs.getDate("primeiro_cdr"));
				reg.setUltimoCdr(rs.getDate("ultimo_cdr"));
				reg.setQtde(rs.getInt("qtde"));
				reg.setQtd440(rs.getInt("qtd440"));
				reg.setQtd430(rs.getInt("qtd430"));
				reg.setAnnotation(annotation);
				reg.setValor(rs.getDouble("valor"));
				reg.setSeq(seq);
				
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

	public List<InstanciaSiebelVO> retornaCasos440LoadComSucesso() {

		
		List<InstanciaSiebelVO> casos = new ArrayList<InstanciaSiebelVO>();
		
		InstanciaSiebelVO reg = null;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaCasos440ComSucesso");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				reg = new InstanciaSiebelVO();
				

				reg.setInstancia(rs.getString("external_id"));
				reg.setPrimeiroCdr(rs.getDate("primeirocdr"));
				reg.setDataPlanoSiebel(rs.getDate("data_plano_siebel"));
				
				casos.add(reg);
				
			}
			
			rs.close();
			stmt.close();
			
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
	
	public void marcaCasosComErroSiebel() {

		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateCasosComErroDeRange");
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
	
	public List<InstanciaSiebelVO> retornaCasos440(int falseSubscriNoXe){
		
		List<InstanciaSiebelVO> casos = new ArrayList<InstanciaSiebelVO>();
		
		InstanciaSiebelVO reg = null;
		
		Connection conn = null;
		
		try{

			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaCasos440Contenda");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, falseSubscriNoXe);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				
				reg = new InstanciaSiebelVO();
				
				reg.setInstancia(rs.getString("external_id"));
				reg.setPrimeiroCdr(rs.getDate("primeiro_cdr"));
				reg.setUltimoCdr(rs.getDate("ultimo_cdr"));
				reg.setQtde(rs.getInt("total_cdrs"));

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

	public void insereAux(String aux) {
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = " insert into aux values ('" + aux + "')"; 
			
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

	public int deParaCspSiebelKenan(int cspIdSiebel) {
		
		int cspIdKenan = 0;
		
		Connection conn = null;
		
		try{

			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaCspIdKenan");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cspIdSiebel);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				cspIdKenan = rs.getInt("cspid_kenan");
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
		return cspIdKenan;
	}

	public void retornaQtdeIncorrigiveis(InstanciaSiebelVO instancia) {
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaQtdeIncorrigiveis");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, instancia.getInstancia());
			pstmt.setDate(2, instancia.getDataPlanoSiebel());

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				instancia.setQtdeIncorrigiveis(rs.getInt("qtde_incorrigiveis"));
			}else{
				instancia.setQtdeIncorrigiveis(0);
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

	public boolean buscaComplementoPlanos(InstanciaSiebelVO instancia) {
		
		Connection conn = null;
		boolean retorno = false;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeBuscaComplementoPlanos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, instancia.getPlano());

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()){
				instancia.setCspSiebel(rs.getString("csp_siebel"));
				instancia.setComponentId(rs.getInt("componentid"));
				instancia.setCspIdSiebel(rs.getInt("cspid_siebel"));
				instancia.setCspIdKenan(rs.getInt("cspid_kenan"));
				instancia.setParComponentIdVc(rs.getInt("parcomponentidvc"));
				instancia.setSvc(rs.getString("vc"));
				instancia.setFranquiaConta(rs.getString("franquia_n_conta"));
				instancia.setCompIdFranquiaConta(rs.getInt("comp_id_franquia"));
				instancia.setCspConta(rs.getString("csp_n_conta"));
				instancia.setCompIdCspConta(rs.getInt("comp_id_csp_conta"));
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
	
	public void atualizaInfoIncorrigiveisXe(InstanciaArborVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdateQtdeIncorrigiveisXe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, instancia.getQtdeIncorrigiveis());
			pstmt.setInt(2, instancia.getQtdeCorrigiveis());
			pstmt.setString(3, instancia.getExternalId());
			
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

	public void updateCorpSemInstancia() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpSemDesignador");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updatePlanosAvulsos() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdatePlanosAvulsos");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateContasSiebel8() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateContasSiebel8");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateInstanciasDoCiclo(String ciclo) {
		
		if (ciclo.equals("".toString())){
			return;
		}
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeInstanciasDoCiclo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, ciclo);
			pstmt.setString(2, ciclo);
			
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

	public void updatePlanosComDataPosteriorAosCDRs() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeUpdatePlanosComDataPosteriorAosCDRs");
			
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
	
	public void updateSemPlanoNoSiebel() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateSemPlanoNoSiebel");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateCorpSemParametros() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpSemParametros");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateCorpA() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpA");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateEconomixFlex800() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomixFlex800");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateCorpB() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpB");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateCorpD() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpD");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

public void updateEconomico30000() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomico30000");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

public void updateEconomico10000() {
	
	Connection conn = null;
	try{
		conn = Connections.getConn("xe");
		String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomico10000");
		PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
		pstmtLog.executeUpdate();
		pstmtLog.close();
		
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

public void updateEconomico3000() {
	
	Connection conn = null;
	try{
		conn = Connections.getConn("xe");
		String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomico3000");
		PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
		pstmtLog.executeUpdate();
		pstmtLog.close();
		
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

public void updateEconomico6000() {
	
	Connection conn = null;
	try{
		conn = Connections.getConn("xe");
		String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomico6000");
		PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
		pstmtLog.executeUpdate();
		pstmtLog.close();
		
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


public void updateEconomicoParte1() {
	
	Connection conn = null;
	try{
		conn = Connections.getConn("xe");
		String queryLog = QueryWarehouse.getQuery("erro440xeUpdateEconomicoParte1");
		PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
		pstmtLog.executeUpdate();
		pstmtLog.close();
		
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


	public void updatePlanosHardCodeLdn() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdatePlanosHardCodeLdn");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updatePlanosHardCodeLocal() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdatePlanosHardCodeLocal");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updatePlanosHardCodeVc() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdatePlanosHardCodeVc");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void carregaTabelaPeriodosNoBill() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeCarregaTabelaPeriodosNoBill");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateCorpComCSP() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateCorpComCSP");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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
	
	public void updateRetailSemCSP() {
		
		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateRetailSemCSP");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateErroMapeamento1() {

		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateErroMapeamento1");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateErroMapeamento2() {

		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateErroMapeamento2");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void updateErroMapeamento3() {

		Connection conn = null;
		try{
			conn = Connections.getConn("xe");
			String queryLog = QueryWarehouse.getQuery("erro440xeUpdateErroMapeamento3");
			PreparedStatement pstmtLog = conn.prepareStatement(queryLog);
			pstmtLog.executeUpdate();
			pstmtLog.close();
			
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

	public void buscaQtdeIncorrigiveis(InstanciaArborVO instanciaArbor) {
	
		Connection conn = null;

		try{

			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeContaIncorrigiveis");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaArbor.getExternalId());
			pstmt.setDate(2, instanciaArbor.getDtInstancia());
			pstmt.setString(3, instanciaArbor.getExternalId());
			pstmt.setDate(4, instanciaArbor.getDtInstancia());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instanciaArbor.setQtdeCorrigiveis(rs.getInt("corrigiveis"));
				instanciaArbor.setQtdeIncorrigiveis(rs.getInt("incorrigiveis"));
				
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

	public List<InstanciaArborVO> buscaTotalDeInstanciasDoRponNaLoad(String rpon) {

		List<InstanciaArborVO> casos = new ArrayList<InstanciaArborVO>();
		
		InstanciaArborVO reg = null;
		
		Connection conn = null;
		
		try{

			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaInstanciasArborCalculoComplemento");
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, rpon);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				
				reg = new InstanciaArborVO();
				
				reg.setExternalId(rs.getString("external_id"));

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

	public void updatePlanosLavoisier() {
		
		Connection conn = null;

		try{
			conn = Connections.getConn("xe");
			String query = QueryWarehouse.getQuery("erro440xeLavoisierNaMedida");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{ conn.close(); }catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
		try{
			conn = Connections.getConn("xe");
			String query = QueryWarehouse.getQuery("erro440xeLavoisierTotal");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{ conn.close(); }catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}

		try{
			conn = Connections.getConn("xe");
			String query = QueryWarehouse.getQuery("erro440xeLavoisierLocal");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{ conn.close(); }catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}

		try{
			conn = Connections.getConn("xe");
			String query = QueryWarehouse.getQuery("erro440xeLavoisierNaMedidaLight");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{ conn.close(); }catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}

		
	}

	public void updateMarcaLavoisierParaNaoProcessar() {
		
		Connection conn = null;

		try{
			conn = Connections.getConn("xe");
			String query = QueryWarehouse.getQuery("erro440xeLavoisierNaMedida");
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
		}finally{
			try{ conn.close(); }catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		
	}

	public int carregaSequencial() {

		int seq = 0;
		
		Connection conn = null;
		
		try{

			conn = Connections.getConn("xe");
			
			String query = QueryWarehouse.getQuery("erro440xeBuscaSequencial");
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				
				seq = rs.getInt("sequencial");
				
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
		
		return seq;

	}

	public void retornaInfoViewCdrEmErro(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		
		String cenario = null;
		
		try{
			
			String query = QueryWarehouse.getQuery("erro440xeRetornaInfoViewCdrEmErro");
			
			conn = Connections.getConn("xe");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				cenario = rs.getString("cenario");
				
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
		instancia.setLogView(cenario);
		
	}
	
}

