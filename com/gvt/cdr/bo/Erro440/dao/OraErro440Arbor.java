package com.gvt.cdr.bo.Erro440.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.AccountVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.cdr.bo.Erro440.vo.ProdutoArborVO;
import com.gvt.cdr.bo.Erro440.vo.TrocaCmfNoBillVO;
import com.gvt.cdr.dao.OraArbor;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;
import com.gvt.util.QueryWarehouse;
import com.gvt.util.dao.Connections;


public class OraErro440Arbor extends OraArbor{
	
//	private OraErro440Xe xe = new OraErro440Xe().getInstance();

	public void retornaSubscrNo(InstanciaArborVO instancia){
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaSubscr");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getExternalId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instancia.setSubscrNo(rs.getInt("subscr_no"));
				instancia.setDtInstancia(rs.getDate("active_date"));
				
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


	public boolean retornaNivelInstanciaLavoisier(InstanciaSiebelVO instancia, int serverId){
		
		boolean retorno = false;
		
		Connection conn = null;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId));
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaNivelLavoisier");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instancia.getInstancia());
			
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

	
	public void retornaCiclo(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		if (instancia.getServerId()>=3){

			try{
			
				conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
			
				String query = QueryWarehouse.getQuery("erro440arborRetornaCiclo");
				PreparedStatement pstmt = conn.prepareStatement(query);
			
				pstmt.setInt(1, instancia.getAccountNo());
			
				ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()){
					instancia.setCiclo(rs.getString("bill_period"));
					instancia.setDtUltimoFaturamento(rs.getDate("prev_cutoff_date"));
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

	public void retornaHierarquia(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		if (instancia.getServerId()>=3){

			try{
			
				conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
			
				String query = QueryWarehouse.getQuery("erro440arborRetornaHierarquia");
				PreparedStatement pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, instancia.getInstancia());
			
				ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()){
					instancia.setAccountNoB(rs.getInt("account_nob"));
					instancia.setParentId(rs.getInt("parent_id"));
					instancia.setHierarchyId(rs.getInt("hierarchy_id"));
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


	public void retornaDistSemHierarquia(InstanciaSiebelVO instancia){
		
		Connection conn = null;
		if (instancia.getServerId()>=3){

			try{
			
				conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instancia.getServerId()-2));
			
				String query = QueryWarehouse.getQuery("erro440arborRetornaDistSemHierarquia");
				PreparedStatement pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, instancia.getInstancia());
			
				ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()){
					instancia.setAccountNoB(rs.getInt("account_nob"));
					instancia.setParentId(rs.getInt("parent_id"));
					instancia.setHierarchyId(rs.getInt("hierarchy_id"));
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

	
	public void retornaPlanosAtivosInstanciaEConta(InstanciaSiebelVO instanciaSiebel, InstanciaArborVO instanciaArbor){

		Connection conn = null;

		List<PlanoArborVO> planosArbor = new ArrayList<PlanoArborVO>();

		if ( //(""+instanciaSiebel.getTipoPlano()).equals("null") || 
				(instanciaSiebel.getServerId()+0 < 3) ){
			instanciaArbor.setPlanosArbor(planosArbor);
			return;
		}
		
		String query = null;
		
		try{
			query = QueryWarehouse.getQuery("erro440arborRetornaPlanosAtivosInstancia");
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));
		
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, instanciaArbor.getSubscrNo());
			pstmt.setInt(2, instanciaArbor.getSubscrNo());
//// 2016-10-04: ALTERADA QUERY PARA TRAZER SOMENTE GUIADORES NÍVEL INSTÂNCIA "E" ALGUNS NÃO GUIADORES QUE TEM QUE SER PROVISIONADOS
			//pstmt.setInt(3, instanciaArbor.getSubscrNo());

//// 2014-09-24: RETIRADA A EXTRAÇÃO DE INFORMAÇÕES DOS PLANOS NÍVEL CONTA POIS HÁ MUITO ERRO DE PROVISIONAMENTO
//// DEVIDO A VARIAÇÕES DE CONFIGURAÇÕES			
//// SETA OS ACCOUNT_NO NA QUERY COMO -1 PARA NÃO TRAZER PLANOS			
////			// Só busca os produtos nível conta se o cliente for Siebel 8, 
////			// senão busca com account_no = -1 para não trazer nada
////			if ((instanciaSiebel.getTipoPlano() == "RS8") || (instanciaSiebel.getTipoPlano() == "L")){
////				pstmt.setInt(4, instanciaSiebel.getAccountNo());
////				pstmt.setInt(5, instanciaSiebel.getAccountNo());
////			}else{
////				pstmt.setInt(4, -1);
////				pstmt.setInt(5, -1);
////			}
			
			//pstmt.setInt(4, -1);
			//pstmt.setInt(5, -1);


			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				PlanoArborVO planoArbor = new PlanoArborVO();
				planoArbor.setSeq(instanciaSiebel.getSeq());
				planoArbor.setInstancia(instanciaSiebel.getInstancia());
				planoArbor.setExternalId(instanciaArbor.getExternalId());
				planoArbor.setComponentId(rs.getInt("component_id"));
				planoArbor.setDtPlano(rs.getDate("dt_ativacao"));
				planoArbor.setComponentInstId(rs.getInt("component_inst_id"));
				planoArbor.setPackageId(rs.getInt("package_id"));
				planoArbor.setSubscrNo(instanciaArbor.getSubscrNo());
				planoArbor.setArborComponentDescription(rs.getString("arborcomponentdescription"));
				planoArbor.setServerId(instanciaSiebel.getServerId());
				planoArbor.setTipoPlano(rs.getString("tipo_plano"));
				planoArbor.setNivelKenan(rs.getString("nivel_kenan"));
				
				if (planoArbor.getNivelKenan().equals("Conta")){
					planoArbor.setExternalId(instanciaSiebel.getExtIdAccountNo());
				}
				
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
	}

	
	public void retornaTrocasCmf(AccountVO account){

		Connection conn = null;

		List<TrocaCmfNoBillVO> trocaCmfNoBill = new ArrayList<TrocaCmfNoBillVO>();

		String query = null;
		
		try{
			query = QueryWarehouse.getQuery("erro440arborRetornaTrocasCmfNoBill");
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(account.getServerId()-2));

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, account.getAccountNo());

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				TrocaCmfNoBillVO trocaCmf = new TrocaCmfNoBillVO();
				trocaCmf.setServerId(account.getServerId());
				trocaCmf.setAccountNo(account.getAccountNo());
				trocaCmf.setQuando(rs.getDate("quando"));
				trocaCmf.setOldNoBill(rs.getInt("old_no_bill"));
				trocaCmf.setNewNoBill(rs.getInt("new_no_bill"));
				trocaCmf.setRemark(rs.getString("remark"));

				trocaCmfNoBill.add(trocaCmf);

			}
			account.setTrocasCmf(trocaCmfNoBill);
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


	public void validaElementos(PlanoArborVO plano){

		Connection conn = null;

		String query = null;
		
		if (plano.getNivelKenan() == "Conta"){
			plano.setValidacaoElementos(0);
			return;
		}
		
		try{

			query = QueryWarehouse.getQuery("erro440arborRetornaFalhaElementosPlano");

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(plano.getServerId()-2));
		
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, plano.getComponentId());
			pstmt.setInt(2, plano.getSubscrNo());
			pstmt.setInt(3, plano.getComponentId());
			pstmt.setInt(4, plano.getComponentInstId());
			pstmt.setInt(5, plano.getComponentId());

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				plano.setValidacaoElementos(-1);
			}else{
				plano.setValidacaoElementos(1);
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

	public boolean validaElementosComponente(InstanciaArborVO instanciaArbor, int componentId, int serverId){

		Connection conn = null;
		boolean retorno = true;
		
		String query = null;
		
		try{

			query = QueryWarehouse.getQuery("erro440arborRetornaFalhaElementosComponente");

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId -2));
		
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, componentId);
			pstmt.setInt(2, instanciaArbor.getSubscrNo());
			pstmt.setInt(3, componentId);
			//pstmt.setInt(4, instanciaArbor.getComponentInstId());
			//pstmt.setInt(5, instanciaArbor.getComponentId());

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				retorno = false;
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
	
	public void retornaProdutosAtivos(InstanciaSiebelVO instanciaSiebel, InstanciaArborVO instanciaArbor){

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
	}

	
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
		
	}


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
	}


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
			
			String query = QueryWarehouse.getQuery("erro440arborInsertDesativacaoAutorot");
			
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

			String query = QueryWarehouse.getQuery("erro440arborInsertAtivacaoAutorot");
			
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


	public void buscaPackageIdComponentInstId(AutorotDesativacaoVO desativacao) {

		Connection conn = null;

		if (desativacao.getServerId() < 3) return ;
		
		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(desativacao.getServerId()-2));
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaPackageIdComponentInstId");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, desativacao.getComponentId());
			pstmt.setString(2, desativacao.getExternalId());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				desativacao.setPackageId(rs.getInt("package_id")) ;
				desativacao.setComponentInstanceId(rs.getInt("COMPONENT_INST_ID")) ;
				
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

	public boolean verificaNivelContaAtivo(int accountNo, int componentId, Date dataPlano, int serverId) {

		Connection conn = null;
		boolean retorno = false;
		
		if (serverId < 3 || componentId == 0)
			return retorno;
		
		try{
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));
			
			String query = QueryWarehouse.getQuery("erro440arborVerificaNivelContaAtivo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, accountNo);
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


	
	public void buscaStatusDesativacaoAutorot(
			AutorotDesativacaoVO desativacaoPlano) {
		// TODO Auto-generated method stub
		
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
	
	
	
	public int contaPlanosNivelInstanciaAtivos(int subscrNo, int serverId, String tipoPlano) {

		Connection conn = null;
		int retorno = 0;

		try{

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));

			String query = null;
			query = QueryWarehouse.getQuery("erro440arborContaPlanosAtivosNivelInstancia");

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, subscrNo);
			pstmt.setInt(2, subscrNo);
			pstmt.setInt(3, subscrNo);
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

	public int contaPlanosNivelContaAtivos(int accountNo, int serverId, String tipoPlano) {

		Connection conn = null;
		int retorno = 0;

		try{

			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));

			String query = null;
			query = QueryWarehouse.getQuery("erro440arborContaPlanosAtivosNivelConta");

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, accountNo);
			pstmt.setInt(2, accountNo);
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

	
	public Date verificaAMaiorDataDosPlanosAtivos(int accountNo, int subscrNo, int serverId, boolean flgValidaConta) {

		Connection conn = null;
		Date retorno = null;

		try{
			
			conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(serverId - 2));

			String query = null;
			
			query = QueryWarehouse.getQuery("erro440maiorDataDosPlanosAtivos");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, subscrNo);
			pstmt.setInt(2, subscrNo);
			pstmt.setInt(3, subscrNo);
			/* caso seja necessário validar novamente a data dos produtos nível conta 
			 * completar o select com o que é utilizado no Load e descomentar estes códigos
			if (flgValidaConta){
				pstmt.setInt(3, accountNo);
				pstmt.setInt(4, accountNo);
			} else {
				pstmt.setInt(3, -1);
				pstmt.setInt(4, -1);
			}*/

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				retorno = rs.getDate("max_dt_ativacao");
			
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

	

	public void retornaExtIdAccountNo(InstanciaSiebelVO instanciaSiebel) {

		Connection conn = null;

		if (instanciaSiebel.getServerId() < 3) return ;
		
		try{
			
			//conn = Connections.getConn(Connections.CONN_ARBOR + String.valueOf(instanciaSiebel.getServerId()-2));
			conn = Connections.getConn(Connections.CONN_ARBOR_CATALOGO);
			
			String query = QueryWarehouse.getQuery("erro440arborRetornaExtIdAccountNo");
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, instanciaSiebel.getInstancia());
						
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				instanciaSiebel.setExtIdAccountNo(rs.getString("ext_id_account_no")) ;
				
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

	
	public List<AutorotDesativacaoVO> retornaPlanosDesativarPorPlanosIguais(InstanciaSiebelVO instanciaSiebel, InstanciaArborVO instanciaArbor){
		
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
		
	}


	

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