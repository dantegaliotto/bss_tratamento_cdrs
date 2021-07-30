package com.gvt.cdr.bo.Erro440;

import java.util.List;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Svcpgen;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.threads.Erro440BuscaInfArbor;
import com.gvt.cdr.bo.Erro440.threads.Erro440BuscaInfSiebel;
import com.gvt.cdr.bo.Erro440.threads.Erro440BuscaInfSiebel8;
import com.gvt.cdr.bo.Erro440.threads.Erro440GravaLoad;
import com.gvt.cdr.bo.Erro440.threads.Erro440GravaLog;
import com.gvt.cdr.bo.Erro440.threads.Erro440Mapeamento;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;
import com.gvt.util.threads.ThreadManager;

public class Erro440Load {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	private OraErro440Svcpgen svcpgen = new OraErro440Svcpgen().getInstance();
	
	public void executeLoad(String versaoSiebel, String annotation){
		
		Log.info(">>> Buscando casos 440 na CDRS_WORK", this);
		String ciclo = "M100";

		//int seq = 1157 ; //xe.carregaSequencial();
		int seq = xe.carregaSequencial();
		System.out.println(">>>>>>>>> ID DA EXECUCAO: " + seq + " <<<<<<<<<<");
/*  --------------------------------------------------------  */
		this.cargaLoad              (seq, annotation, versaoSiebel);
		this.cargaBssTratamentoCdrs (seq, annotation, versaoSiebel);
		this.updatesEspecificos     (ciclo, versaoSiebel);
/*  --------------------------------------------------------  */
		System.out.println(">>>>>>>>> ID DA EXECUCAO: " + seq + " <<<<<<<<<<");
		
	}

	private void updatesEspecificos(String ciclo, String versaoSiebel){

		if (versaoSiebel.compareTo("s8") == 0){
			xe.updateInstanciasDoCiclo(ciclo);
			xe.updatePlanosComDataPosteriorAosCDRs();
		}else{
			xe.updatePlanosComDataPosteriorAosCDRs();
			xe.updatePlanosHardCodeLdn();
			xe.updatePlanosHardCodeLocal();
			xe.updatePlanosHardCodeVc();
			xe.updateCorpA();
			xe.updateCorpB();
			xe.updateCorpD();
			xe.updateEconomico3000();
			xe.updateEconomico10000();
			//xe.updateEconomico30000();
			xe.updateEconomico6000();
			xe.updateErroMapeamento1(); // Plano LDI 1 (60+6)
			xe.updateErroMapeamento2(); // Plano Local Móvel 04 (30+6)
			xe.updateErroMapeamento3(); // Plano Local Móvel 05 (30+6)
			xe.updateCorpSemInstancia();
		}
		xe.updatePlanosAvulsos();
		xe.updateEconomixFlex800();

	}


	private void cargaLoad(int seq, String annotation, String versaoSiebel) {

		List<InstanciaSiebelVO> casos440 = null;
		
		casos440 = svcpgen.retornaCasos440(versaoSiebel, seq, annotation);

		Log.info("> Total de casos sem agrupamento: " + casos440.size(), this);
		
		for (int i = 0 ; i <= casos440.size(); i=i+10){
			int f;
			if (i+10 < casos440.size())
				f = i+10;
			else 
				f = casos440.size();
			
			Log.info(">> Iteracao:" + i);
			List<InstanciaSiebelVO> casosAux = casos440.subList(i, f);

			ThreadManager thread = new ThreadManager();
			
			Log.info(">> Gravando dados na tabela BSS_TRATAMENTO_CDRS_LOAD", this);
			thread.executar(casosAux, new Erro440GravaLoad()); 
			
		}
		Log.info(">> Marca clientes com problema na BSS_TRATAMENTO_CDRS_LOAD (ex.: barigui veiculos)", this);
		xe.marcaCasosComErroSiebel();
		
	}
	
	private void cargaBssTratamentoCdrs(int seq, String annotation, String versaoSiebel) {
		
		Log.info(">>> BUSCANDO casos 440 na BSS_TRATAMENTO_CDRS_LOAD", this);
		//xe.inserirLogExecucoes("cargaBssTratamentoCdrs", "inicio");
		// aqui esta trazendo apenas casos que ele consegue resolver o rpon
		// verificar o motivo de não estar resolvendo o rpon para estes external_id
		List<InstanciaSiebelVO> casos440 = xe.retornaCasos440Load(seq, annotation);

		Log.info("> Total de casos: " + casos440.size(), this);
		
		for (int i = 0 ; i <= casos440.size(); i=i+10){
			int f;
			if (i+10 < casos440.size()) 
				f = i+10;
			else 
				f = casos440.size();
			
			Log.info(">> Iteracao:" + i);
			List<InstanciaSiebelVO> casosAux = casos440.subList(i, f);

			ThreadManager thread = new ThreadManager();

			if ((versaoSiebel.compareTo("s8") == 0)||
				(versaoSiebel.compareTo("S8") == 0)){
				
				Log.info(">> Buscando informações do SIEBEL 8 - OITO", this);
				thread.executar(casosAux, new Erro440BuscaInfSiebel8());
			}else{
				Log.info(">> Buscando informações do SIEBEL 5 - CINCO", this);
				thread.executar(casosAux, new Erro440BuscaInfSiebel());
			
				Log.info(">> Buscando informações de Mapeamento", this);
				thread.executar(casosAux, new Erro440Mapeamento());
			}
			
			Log.info(">> Buscando informações do Arbor", this);
			thread.executar(casosAux, new Erro440BuscaInfArbor());

			//Log.info(">> Buscando informações na VIEW_CDR_EM_ERRO", this);
			//thread.executar(casosAux, new Erro440BuscaInfViewCdrEmErro());
			
			Log.info(">> Gravando dados na tabela BSS_TRATAMENTO_CDRS", this);
			thread.executar(casosAux, new Erro440GravaLog());
			
		}

	}

}
