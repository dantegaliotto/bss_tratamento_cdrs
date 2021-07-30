package com.gvt.cdr.bo.Erro440;

import java.util.List;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Arbor;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;


public class Erro440Action {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	private OraErro440Arbor arbor = new OraErro440Arbor();
	
	public void executeAction(String sequencial) {

		this.geraLogAtivacoesAutorot(sequencial);
		//this.geraLogDesativacoesAutorot(sequencial);

		this.enviaAtivacoesAutorot();
		//this.enviaDesativacoesAutorot();

	}

	public void geraLogAtivacoesAutorot(String seq){

 		List<AutorotAtivacaoVO> planosAtivar = xe.retornaPlanosAtivar(seq);

		Log.info(">>> Num casos ativ.: " + planosAtivar.size(), this);

		AutorotAtivacaoVO planoAtivar = null;
		
		for(int x=0; x<planosAtivar.size(); x++){
			
			planoAtivar = (AutorotAtivacaoVO) planosAtivar.get(x);

			arbor.buscaPackageId(planoAtivar);
			
			xe.insertBssLogAtivacoesAutorot(planoAtivar);

		}
		
	}

	public void geraLogDesativacoesAutorot(String seq){

		List<AutorotDesativacaoVO> planosDesativarCorporate = xe.retornaPlanosDesativar("C", seq);
		List<AutorotDesativacaoVO> planosDesativarRetailEDuplicados = xe.retornaPlanosDesativar("RD", seq);
		List<AutorotDesativacaoVO> planosDesativar = planosDesativarCorporate;
		planosDesativar.addAll(planosDesativarRetailEDuplicados);

//// 2014-09-24: RETIRADO O PROCESSAMENTO DE PLANOS NÍVEL CONTA
////	List<AutorotDesativacaoVO> planosDesativarSiebel8NivelConta = xe.retornaPlanosDesativar("RS8_CONTA", seq);
////	planosDesativar.addAll(planosDesativarSiebel8NivelConta);
		
		Log.info(">>> Num casos desat.: " + planosDesativar.size(), this);

		AutorotDesativacaoVO planoDesativar = null;
		
		for(int x=0;  x<planosDesativar.size(); x++){
			
			planoDesativar = (AutorotDesativacaoVO) planosDesativar.get(x);

			//arbor.buscaPackageIdComponentInstId(planoDesativar);
			
			xe.insertBssLogDesativacoesAutorot(planoDesativar);
			//xe.marcaPlanoComoDesconectado(planoDesativar);

		}
		
	}
	
	private void enviaDesativacoesAutorot() {

		List<AutorotDesativacaoVO> autorotDesativacoes = xe.retornaPlanosDesativarDoLog();
		
		for(int x=0; x<autorotDesativacoes.size(); x++){
			
			AutorotDesativacaoVO autorotDesativacao = (AutorotDesativacaoVO) autorotDesativacoes.get(x);

			System.out.println( "Desativando: " +
			   autorotDesativacao.getExternalId()                + " " +
			   autorotDesativacao.getComponentInstanceId()       + " " +
			   autorotDesativacao.getComponentId()               + " " + 
			   autorotDesativacao.getServerId()                  + " " + x + " | " +
			   autorotDesativacao.getArborComponentDescription() + " | " + 
			   autorotDesativacao.getMotivo());
			
			int resultado = arbor.desativaNoAutorot(autorotDesativacao);
			xe.atualizaLogDesativacaoEnviado(autorotDesativacao, resultado);

		}

	}

	private void enviaAtivacoesAutorot() {

		List<AutorotAtivacaoVO> autorotAtivacoes = xe.retornaPlanosAtivarDoLog();

		for(int x=0; x<autorotAtivacoes.size(); x++){

			AutorotAtivacaoVO autorotAtivacao = (AutorotAtivacaoVO) autorotAtivacoes.get(x);

			System.out.println( "Ativando: " +
					   autorotAtivacao.getExternalId()  + " " +
					   autorotAtivacao.getComponentId() + " " +
					   autorotAtivacao.getServerId()    + " " + x + " | " +
					   autorotAtivacao.getSiebelComponentDescription() + " | " + 
					   autorotAtivacao.getMotivo());
			
			int resultado = arbor.ativaNoAutorot(autorotAtivacao);
			xe.atualizaLogAtivacaoEnviado(autorotAtivacao, resultado);

		}
		// aqui pega a lista criada acima e atualiza os resultados para 2 e a data de 
		// envio para o sautorot

	}


}

