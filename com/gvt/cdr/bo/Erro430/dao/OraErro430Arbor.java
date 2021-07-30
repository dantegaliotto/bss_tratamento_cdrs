package com.gvt.cdr.bo.Erro430.dao;


import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro430.vo.DuplicidadeVO;
import com.gvt.cdr.bo.Erro430.vo.InstanciaVO;
import com.gvt.cdr.dao.OraArbor;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro430Arbor extends OraArbor{
	
	private static OraErro430Arbor instance;
	//private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public OraErro430Arbor getInstance() {
		if (instance == null)
			instance = new OraErro430Arbor();

		return instance;
	}

	
	public void retornaSubscrNo(InstanciaVO instanciaVO){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaSubscr");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaVO.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instanciaVO.setSubscrNo(rs.getInt("subscr_no"));
				//instanciaVO.setDtInstancia(rs.getDate("active_date"));
				
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

	public boolean retornaNivelInstanciaLavoisier(InstanciaVO instancia){
		
		boolean retorno = false;
		
		Connection conn = null;
		
		int serverId = instancia.getServerId()-2;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId));
			
			String query = QueryWarehouse.getQuery("erro430arborRetornaNivelLavoisier");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				instancia.setLavoisier(rs.getInt("nivellavoisier"));
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

	public void retornaCiclo(InstanciaVO instanciaVO){
		
		Connection conn = null;
		
		if (instanciaVO.getServerId()>=3){
			
			try{
			
				conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaVO.getServerId()-2));
			
				String query = QueryWarehouse.getQuery("erro440arborRetornaCiclo");
				PreparedStatement pstmt = conn.prepareStatement(query);
			
				pstmt.setInt(1, instanciaVO.getAccountNo());
			
				ResultSet rs = pstmt.executeQuery();

				if(rs.next()){
					instanciaVO.setCiclo(rs.getString("bill_period"));
					//instancia.setDtUltimoFaturamento(rs.getDate("prev_cutoff_date"));
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
		return;
		
	}

	public Date retornaDtProduto(int serverId, int subscrNo, int componentId){
		
		Date ativacao = null;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId-2));
			
			String query = QueryWarehouse.getQuery("erro440arborRetornoProduto");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, subscrNo);
			pstmt.setInt(2, componentId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				ativacao = rs.getDate("active_dt");
				
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
		
		return ativacao;
		
	}

	public void retornaAccountNo(InstanciaVO instanciaVO){
		
		int accountNo = 0;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("arborRetornaAccount");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaVO.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				accountNo = rs.getInt("account_no");
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
		
		instanciaVO.setAccountNo(accountNo);
		
		return;
		
	}

	
	/*
	public void retornaPlanosAtivos(InstanciaSiebelVO instanciaSiebel, InstanciaArborVO instanciaArbor){

		Connection conn = null;

		List<PlanoArborVO> planosArbor = new ArrayList<PlanoArborVO>();

		if ( (""+instanciaSiebel.getTipoPlano()).equals("null") || (instanciaSiebel.getServerId()+0 < 3) ){
			instanciaArbor.setPlanosArbor(planosArbor);
			return;
		}
		
		String query = null;
		
		try{

			query = QueryWarehouse.getQuery("erro440arborRetornaPlanosAtivosCorporate");

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));
		
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, instanciaArbor.getSubscrNo());
			pstmt.setInt(2, instanciaArbor.getSubscrNo());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){

				PlanoArborVO planoArbor = new PlanoArborVO();
				planoArbor.setComponentId(rs.getInt("component_id"));
				planoArbor.setDtPlano(rs.getDate("dt_ativacao"));
				planoArbor.setComponentInstId(rs.getInt("component_inst_id"));
				planoArbor.setPackageId(rs.getInt("package_id"));
				planoArbor.setSubscrNo(instanciaArbor.getSubscrNo());
				planoArbor.setArborComponentDescription(rs.getString("arborcomponentdescription"));
				planoArbor.setTipoPlano(rs.getString("tipo_plano"));
				
				planosArbor.add(planoArbor);
				
			}
			instanciaArbor.setPlanosArbor(planosArbor);
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
	}*/
	
    /*
	public void retornaProdutosAtivos(InstanciaVO instanciaSiebel, InstanciaArborVO instanciaArbor){

		Connection conn = null;

		List<ProdutoArborVO> produtosArbor = new ArrayList<ProdutoArborVO>();

		if ( (""+instanciaSiebel.getTipoPlano()).equals("null") || (instanciaSiebel.getServerId()+0 < 3) ){
			instanciaArbor.setProdutosArbor(produtosArbor);
			return;
		}
		
		String query = null;
		
		try{

			query = QueryWarehouse.getQuery("erro440arborRetornaProdutosAtivosCorporate");

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));
		
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, instanciaArbor.getSubscrNo());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){

				ProdutoArborVO produtoArbor = new ProdutoArborVO();
				produtoArbor.setComponentId(rs.getInt("component_id"));
				produtoArbor.setElementId(rs.getInt("element_id"));
				produtoArbor.setDtProduto(rs.getDate("billing_active_dt"));
				produtoArbor.setSubscrNo(instanciaArbor.getSubscrNo());
				
				produtosArbor.add(produtoArbor);
				
			}
			instanciaArbor.setProdutosArbor(produtosArbor);
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
	}*/

	/*
	public int validaPlano(int serverId, String externalId, int componentId){
		
		int validar = 0;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId-2));
			
			String query = QueryWarehouse.getQuery("erro440arborValidaPlano");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			//pstmt.setInt(1, subscrNo);
			pstmt.setInt(2, componentId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				validar = rs.getInt("valida");
				
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
		
		return validar;
		
	}*/


	public void buscaPackageId(AutorotAtivacaoVO caso) {

		Connection conn = null;

		if (caso.getServerId() < 3) return ;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(caso.getServerId()-2));
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaPackageId");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, caso.getComponentId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				caso.setPackageId(rs.getInt("package_id")) ;
				
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
	
/*
	public void buscaQtdeIncorrigiveis(InstanciaArborVO instanciaArbor) {

		Connection conn = null;

		try{

			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro440arborContaIncorrigiveis");
			
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
	}*/


	public int desativaNoAutorot(AutorotDesativacaoVO caso) {

		Connection conn = null;

		if (caso.getServerId() < 3) return -1;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(caso.getServerId()-2));
			
/// limpa a tabela para não estourar o indice
			String queryX = "delete from gvt_disc_component where component_instance_id = ? ";
			
			PreparedStatement pstmtX = conn.prepareStatement(queryX);

			pstmtX.setInt(1, caso.getComponentInstanceId());

			pstmtX.executeUpdate();
			
			pstmtX.close();

//
			
			String query = QueryWarehouse.getQuery("erro430arborInsertDesativacaoAutorot");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, caso.getExternalId());
			pstmt.setInt(2, caso.getPackageId());
			pstmt.setInt(3, caso.getComponentInstanceId());
			pstmt.setDate(4, caso.getDtDesativar());
			pstmt.setInt(5, caso.getExternalIdType());

			pstmt.executeUpdate();
			
			pstmt.close();
						
		}catch(SQLException ex){
			Log.error("Erro de SQL na execução", ex);
			return -2;
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
			return -2;
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return 1;
	}
	

	public int ativaNoAutorot(AutorotAtivacaoVO caso) {

		Connection conn = null;
		
		if (caso.getServerId()<3) return -1;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(caso.getServerId()-2));			

			String query = QueryWarehouse.getQuery("erro430arborInsertAtivacaoAutorot");
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, caso.getExternalId());
			pstmt.setInt(2, caso.getExternalIdType());
			pstmt.setString(3, caso.getPackageId()+"");
			pstmt.setString(4, caso.getComponentId()+"");
			pstmt.setDate  (5, caso.getDtAtivacao());
			pstmt.setString(6, caso.getFlag()+"");
			pstmt.setInt(7, caso.getJurisdiction());
			pstmt.setInt(8, caso.getUnits());
			pstmt.setInt(9, caso.getModLavoisier());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
		}catch(Exception ex){
			Log.error("Erro na execução", ex);
			return -2;
		}finally{
			try{
				conn.close();
			}catch(Exception ex){
				Log.error("Falha ao fechar conexão", ex);
			}
		}
		return 1;
	}


	public void buscaPackageIdComponentInstId(int ordemComponente, DuplicidadeVO duplicidade) {

		Connection conn = null;

		if (duplicidade.getServerId() < 3) return ;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(duplicidade.getServerId()-2));

			String query = QueryWarehouse.getQuery("erro430arborRetornaPackageIdComponentInstId");

			PreparedStatement pstmt = conn.prepareStatement(query);
			
			if (ordemComponente == 1){
				pstmt.setInt(1, duplicidade.getTrackingId1());
				pstmt.setInt(2, duplicidade.getComponentId1());
			}else{
				pstmt.setInt(1, duplicidade.getTrackingId2());
				pstmt.setInt(2, duplicidade.getComponentId2());
			}

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				if (ordemComponente == 1){
					duplicidade.setPackageId1(rs.getInt("package_id"));
					duplicidade.setComponentInstId1(rs.getInt("component_inst_id"));
				}else{
					duplicidade.setPackageId2(rs.getInt("package_id"));
					duplicidade.setComponentInstId2(rs.getInt("component_inst_id"));
				}
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


	public boolean verificaPlanoAtivo(int subscrNo, int componentId, Date dataPlano, int serverId) {

		Connection conn = null;
		boolean retorno = false;
		
		if (serverId < 3 || componentId == 0)
			return retorno;
		
		try{
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));
			
			String query = QueryWarehouse.getQuery("erro440arborVerificaPlanoAtivo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, subscrNo);
			pstmt.setInt(2, componentId);
			//pstmt.setDate(3, dataPlano);

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


	public boolean verificaElementosSemComponentes(int subscrNo, int serverId) {

		Connection conn = null;
		boolean retorno = false;
		
		try{
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));
			
			String query = QueryWarehouse.getQuery("erro440arborVerificaElementosSemComponentes");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, subscrNo);

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
	
	
	
	public int contaPlanosAtivos(int subscrNo, int serverId, String tipoPlano) {

		Connection conn = null;
		int retorno = 0;

		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));

			String query = null;
			
//			if ((""+tipoPlano).equals("C")){
				query = QueryWarehouse.getQuery("erro440arborContaPlanosAtivosCorporate");
//			} else {
//				if (((""+tipoPlano)).equals("R")){
//					query = QueryWarehouse.getQuery("erro440arborContaPlanosAtivosRetail");
//				}
//			}
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, subscrNo);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				retorno = rs.getInt("contagem");
			else
				retorno = 0;

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


	public List<DuplicidadeVO> retornaDuplicidades(InstanciaVO instancia){
		
		Connection conn = null;
		
		List<DuplicidadeVO> casosDuplicidade = new ArrayList<DuplicidadeVO>();
		
		if (instancia.getServerId() >= 3){

			try{
			
				conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
			
				String query = QueryWarehouse.getQuery("erro430arborGetDuplicidades");
				PreparedStatement pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, instancia.getExternalId());
				pstmt.setDate  (2, instancia.getDtPrimeiroCdr());
			
				ResultSet rs = pstmt.executeQuery();
			
				DuplicidadeVO reg = null;
	
				System.out.println("verificando > " + instancia.getExternalId());
				
				while(rs.next()){
					
					reg = new DuplicidadeVO();
					
					reg.setExternalId       (rs.getString("external_id"));
					reg.setServerId         (instancia.getServerId());
					reg.setExtIdType        (rs.getInt ("ext_id_type"));
					reg.setSubscrNo         (instancia.getSubscrNo());
					reg.setModLavoisier     (instancia.getLavoisier());
					reg.setTrackingId1      (rs.getInt ("tr_id1"));
					reg.setElementId1       (rs.getInt ("el_id1"));
					reg.setComponentId1     (rs.getInt ("comp_id1"));
					reg.setPackageId1       (rs.getInt ("pack_id1"));
					reg.setComponentInstId1 (rs.getInt ("component_inst_id1"));
					reg.setActiveDt1        (rs.getDate("act_dt1"));
					reg.setInactiveDt1      (rs.getDate("inac_dt1"));
					reg.setTrackingId2      (rs.getInt ("tr_id2"));
					reg.setElementId2       (rs.getInt ("el_id2"));
					reg.setComponentId2     (rs.getInt ("comp_id2"));
					reg.setPackageId2       (rs.getInt ("pack_id2"));
					reg.setComponentInstId2 (rs.getInt ("component_inst_id2"));
					reg.setActiveDt2        (rs.getDate("act_dt2"));
					reg.setInactiveDt2      (rs.getDate("inac_dt2"));
					
					casosDuplicidade.add(reg);
					
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
		return casosDuplicidade;
		
	}



/*
	public void retornaExtIdAccountNo(InstanciaVO instancia) {

		Connection conn = null;

		if (instancia.getServerId() < 3) return ;
		
		try{
			
			//conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaExtIdAccountNo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setExtIdAccountNo(rs.getString("ext_id_account_no")) ;
				
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
		
	}*/

	/*
	public List<AutorotDesativacaoVO> retornaPlanosDesativarPorPlanosIguais(InstanciaVO instanciaSiebel, InstanciaArborVO instanciaArbor){
		
		List<AutorotDesativacaoVO> listaDesativacoes = new ArrayList<AutorotDesativacaoVO>();
		
		Connection conn = null;

		try{
			
			AutorotDesativacaoVO reg = null;

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));

			String query = null;

			PreparedStatement pstmt = null;
		
			query = QueryWarehouse.getQuery("erro440arborRetornaPlanosDesativarPorPlanosIguais");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, instanciaArbor.getExternalId());
			pstmt.setString(2, instanciaArbor.getExternalId());

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){ 
				reg = new AutorotDesativacaoVO();
				reg.setInstancia(instanciaSiebel.getInstancia());
				reg.setExternalId(instanciaArbor.getExternalId());
				reg.setDtDesativar(rs.getDate("dt_desativar"));
				reg.setComponentId(rs.getInt("component_id"));
				reg.setPackageId(rs.getInt("package_id"));
				reg.setComponentInstanceId(rs.getInt("component_inst_id"));
				reg.setServerId(instanciaSiebel.getServerId());
				reg.setSubscrNo(rs.getInt("subscr_no"));
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
		
	}*/


	

}






/*

	public void retornaProdutoAtivo(int serverId, InstanciaArborVO instanciaArbor, int tipoUso){

		Connection conn = null;

		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId-2));
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaProdutoAtivo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, instanciaArbor.getSubscrNo());

			pstmt.setInt(1, instanciaArbor.getSubscrNo());
			pstmt.setString(2, tipoUso+"");

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){

				int componentId = rs.getInt("component_id");
				Date activeDt = rs.getDate("active_dt");
				
				if((tipoUso+"").charAt(0) == '4'){
					instanciaArbor.setParComponentIdaLocal(componentId);
					instanciaArbor.setDtParComponentIdaLocal(activeDt);
				}else{
					if((tipoUso+"").charAt(0) == '6'){
						instanciaArbor.setParComponentIdaVc(componentId);
						instanciaArbor.setDtParComponentIdaVc(activeDt);
					}else{
						if((tipoUso+"").charAt(0) == '2'){
							instanciaArbor.setParComponentIdaLdn(componentId);
							instanciaArbor.setDtParComponentIdaLdn(activeDt);
						}else{
							instanciaArbor.setParComponentIdaLdi(componentId);
							instanciaArbor.setDtParComponentIdaLdi(activeDt);
						}
					}
				}
				
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


*/