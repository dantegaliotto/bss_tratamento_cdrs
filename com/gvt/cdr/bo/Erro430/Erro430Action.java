package com.gvt.cdr.bo.Erro430;

import java.util.List;

import com.gvt.cdr.bo.Erro430.dao.OraErro430Arbor;
import com.gvt.cdr.bo.Erro430.dao.OraErro430Xe;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.Log;


public class Erro430Action {

	private OraErro430Xe xe = new OraErro430Xe().getInstance();
	private OraErro430Arbor arbor = new OraErro430Arbor().getInstance();
	
	public void executeAction430(){
	
		System.out.println("Action do 430");
		
		// qdo estiver tratando casos com 2 ativos não precisa ativar nada
		//this.geraLogAtivacoesAutorot();
		//this.geraLogDesativacoesAutorot();

		this.enviaAtivacoesAutorot();
		this.enviaDesativacoesAutorot();

	}

	public void geraLogAtivacoesAutorot(){

 		List<AutorotAtivacaoVO> planosAtivar = xe.retornaPlanosAtivar();

		Log.info(">>> Num casos ativ.: " + planosAtivar.size(), this);

		AutorotAtivacaoVO planoAtivar = null;
		
		for(int x=0; x<planosAtivar.size(); x++){
			
			planoAtivar = (AutorotAtivacaoVO) planosAtivar.get(x);

			arbor.buscaPackageId(planoAtivar);
			
			xe.insertBssLogAtivacoesAutorot(planoAtivar);

		}
		
	}

	public void geraLogDesativacoesAutorot(){

		List<AutorotDesativacaoVO> planosDesativar = xe.retornaPlanosDesativar();
		
		Log.info(">>> Num casos desat.: " + planosDesativar.size(), this);

		AutorotDesativacaoVO planoDesativar = null;
		
		for(int x=0;  x<planosDesativar.size(); x++){
			
			planoDesativar = (AutorotDesativacaoVO) planosDesativar.get(x);
			
			xe.insertBss430Desativacoes(planoDesativar);
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
