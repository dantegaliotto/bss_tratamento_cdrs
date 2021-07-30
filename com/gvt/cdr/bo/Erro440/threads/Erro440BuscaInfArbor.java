package com.gvt.cdr.bo.Erro440.threads;

import java.util.ArrayList;
import java.util.List;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Arbor;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Dgrc;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Erro440.vo.RangeVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaInfArbor extends ActionAbstract<Object>{

	private OraErro440Arbor arbor440 = new OraErro440Arbor();
	private OraErro440Dgrc dgrc440 = new OraErro440Dgrc();
	private OraErro440Xe xe = new OraErro440Xe();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instanciaSiebel = (InstanciaSiebelVO) arg0;
		
		if (instanciaSiebel.getResultado() < 0){
			return;
		}

		// DATA DA INSTANCIA
		if(! arbor440.retornaDtInstanciaKenan(instanciaSiebel)){
			instanciaSiebel.setLog("Erro Kenan: Inst�ncia inativa");
			instanciaSiebel.setResultado(-2);
			return;	
		}

		// SERVER ID
		if (arbor440.verificaServerId(instanciaSiebel.getInstancia(),1)){
			instanciaSiebel.setServerId(3);
		}else if (arbor440.verificaServerId(instanciaSiebel.getInstancia(),2)){
			instanciaSiebel.setServerId(4);
		}else{
			instanciaSiebel.setLog("Erro Kenan: Erro buscando o serverId da inst�ncia");
			instanciaSiebel.setResultado(-2);
			return;
		}

		// NIVEL LAVOISIER
		arbor440.retornaNivelInstanciaLavoisier(instanciaSiebel, instanciaSiebel.getServerId()-2);
		
		// ACCOUNT_NO
		instanciaSiebel.setAccountNo(arbor440.retornaAccountPorInstancia(instanciaSiebel.getInstancia()));

		// INFORMA��ES HIERARQUIA PARA CALCULO DIST
//		arbor440.retornaHierarquia(instanciaSiebel);
		// busca info de DIST para clientes SEM HIERARQUIA
		// a.parent_id = a.hierarchy_id or a.parent_id is null
//		if (instanciaSiebel.getParentId() == instanciaSiebel.getHierarchyId() 
//			|| instanciaSiebel.getParentId() == 0){
//			arbor440.retornaDistSemHierarquia(instanciaSiebel);
//		}
		
		// CODIGO 9999...
		arbor440.retornaExtIdAccountNo(instanciaSiebel);
		
		// CICLO E DATA DO ULTIMO FATURAMENTO
		arbor440.retornaCiclo(instanciaSiebel);

/*
		// MONTAGEM DAS LISTAGENS DE RANGES (RETIRADA EM 24/10/2013 PARA S� TRATAR CASOS QUE TENHAM CDRS EM ERRO
		 *                                   N�O VAI TRATAR MAIS TODOS OS EXTERNAL_ID DO RPON)
		if(instanciaSiebel.getRanges()!=null && instanciaSiebel.getRanges().size()>0){
			// monta uma lista de instanciaArborVO para um range com mais de uma instancia
			if ( !this.criaRangeRamais(instanciaSiebel)){
				return;
			}
		}else{
			// monta uma lista de instanciaArborVO para um range com uma �nica instancia			
			List<InstanciaArborVO> instanciasArbor = new ArrayList<InstanciaArborVO>();
			
			InstanciaArborVO arb = new InstanciaArborVO();
			
			// faz a validacao se a instancia possui s� um designador no siebel
			if (1 < dgrc440.verificaDuplicidadeDesignadores(instanciaSiebel.getInstancia())){
				instanciaSiebel.setLog("Erro Siebel - Instancia em range com designador duplicado " + instanciaSiebel.getInstancia());
				instanciaSiebel.setResultado(-2);
				return;
			} 
			arb.setExternalId(String.valueOf(instanciaSiebel.getInstancia()));
			
			instanciasArbor.add(arb);

			instanciaSiebel.setInstanciasArbor(instanciasArbor);

		}
		*/
		// DUAS LINHAS ADICIONADAS EM SUBSTITUICAO AO PROCEDIMENTOS ACIMA
		List<InstanciaArborVO> instanciasArborAInserir = new ArrayList<InstanciaArborVO>();
		instanciaSiebel.setInstanciasArbor(instanciasArborAInserir);
		/////////////////////////// COMPLEMENTO DA LISTA DE INST�NCIAS COM AS QUE EST�O FORA DO RANGE
		/////////////////////////// MAS EST�O NA BSS_TRATAMENTO_CDRS_LOAD
		this.insereComplementoRanges(instanciaSiebel);
		///////////////////////////
		InstanciaArborVO instanciaArbor = null;
		
		List<InstanciaArborVO> instanciasArbor = instanciaSiebel.getInstanciasArbor();
		
		if(instanciasArbor!=null){
			
			boolean instanciasCorretasNoRange = false;
			for(int x=0; x<instanciasArbor.size(); x++){
				
				instanciaArbor = (InstanciaArborVO) instanciasArbor.get(x);
				
				// BUSCA O SUBSCR_NO E A DATA DE ATIVACAO DA INSTANCIA
				arbor440.retornaSubscrNo(instanciaArbor);
				if (instanciaArbor.getSubscrNo() == 0){
					instanciaArbor.setLog("Erro Kenan: subscr_no n�o encontrado para instancia do range");
					instanciaArbor.setResultado(-2);
				}else{
					if(arbor440.verificaElementosSemComponentes(instanciaArbor.getSubscrNo(), instanciaSiebel.getServerId())){
						instanciaArbor.setLog("Erro Kenan: Instancia do range possui element_id sem component_id ativo no Kenan");
						instanciaArbor.setResultado(-2);
					}else{
						instanciasCorretasNoRange = true;
						// n�o busca mais os produtos n�vel conta para evitar erros (comentado dentro do m�todo)
						arbor440.retornaPlanosAtivosInstanciaEConta(instanciaSiebel, instanciaArbor);
						// valida se o component_id dos planos possui todos os element_id necess�rios
						for(int i=0; i<instanciaArbor.getPlanosArbor().size(); i++){
							arbor440.validaElementos(instanciaArbor.getPlanosArbor().get(i));
						}
					}
				}
				if (!instanciasCorretasNoRange){
					instanciaSiebel.setLog("Erro Kenan: Todas as instancias do range com erro no Kenan");
					instanciaSiebel.setResultado(-2);
					return;
				}
			}
			
		}else{
			instanciaSiebel.setLog("Erro Siebel X: Sem instancias");
			instanciaSiebel.setResultado(-2);
			return;
		}
		//arbor440.retornaPlanosAtivosConta(instanciaSiebel);
		
	}

	
	private void insereComplementoRanges(InstanciaSiebelVO instanciaSiebel) {
		
		//cria lista auxiliar de InstanciasArbor complemento (que ser� adicionada a lista da instanciaSiebel);
		List<InstanciaArborVO> instanciasArborComplemento = new ArrayList<InstanciaArborVO>();
		//cria lista auxiliar de InstanciasArbor total (que ter� as instancias com o mesmo rpon em erro);
		List<InstanciaArborVO> instanciasArborTotalDoRpon = null;
		
		instanciasArborTotalDoRpon = xe.buscaTotalDeInstanciasDoRponNaLoad(instanciaSiebel.getRpon());
		if (instanciasArborTotalDoRpon.size() == 0){
			return;
		}
		
		for(int x=0; x<instanciasArborTotalDoRpon.size(); x++){

			InstanciaArborVO instanciaArborAVerificar = (InstanciaArborVO) instanciasArborTotalDoRpon.get(x);
			
			//if (!this.verificaInstanciaArborNaLista(instanciaArborAVerificar, instanciaSiebel.getInstanciasArbor())){
				instanciasArborComplemento.add(instanciaArborAVerificar);
			//}
			
		}
		instanciaSiebel.getInstanciasArbor().addAll(instanciasArborComplemento);

		return;
		
	}

/*
	private boolean verificaInstanciaArborNaLista(InstanciaArborVO instanciaArborAVerificar,
												  List<InstanciaArborVO> instanciasArbor) {
		
		for(int i=0; i<instanciasArbor.size(); i++){
			if (instanciaArborAVerificar.getExternalId().equals(instanciasArbor.get(i).getExternalId())){
				return true;
			}
		}

		return false;
	}
*/

	/**
	  * Atribui uma lista de instancias arbor para a instancia siebel recebida. N�o preenche as informa��es do arbor nas instancias.
	  */
	@SuppressWarnings("unused")
	private boolean criaRangeRamais(InstanciaSiebelVO instanciaSiebel){
		
		List<InstanciaArborVO> instanciasArbor = new ArrayList<InstanciaArborVO>();

		for(int r=0; r < instanciaSiebel.getRanges().size(); r++){

			RangeVO range = (RangeVO) instanciaSiebel.getRanges().get(r);
			
			if(range.getRangeFinal()!=null && range.getRangeInicial()!=null){
				
				long totalInst = Long.parseLong(range.getRangeFinal().trim()) - Long.parseLong(range.getRangeInicial().trim());
				if (totalInst > 1000){
					instanciaSiebel.setLog("Erro Siebel: Instancia em range com mais de 1000 inst�ncias (" + totalInst + ")");
					instanciaSiebel.setResultado(-2);
				}
				
				InstanciaArborVO arb = null;
				
				long instArbor = Long.parseLong(range.getRangeInicial().trim());
				
				for(long x=0; x<=totalInst; x++){
						
					arb = new InstanciaArborVO();
						
					arb.setExternalId(String.valueOf(instArbor));

					// verifica se tem designador duplicado... so aborta a cria��o do range caso
					// o ext_id com este erro seja o principal da instancia
					if (1 < dgrc440.verificaDuplicidadeDesignadores(String.valueOf(instArbor))){
						if (String.valueOf(instArbor).equals(instanciaSiebel.getInstancia())){
							arb.setLog("Erro Siebel: Inst�ncia em mais de um range no Siebel");
							instanciaSiebel.setLog("Erro Siebel: Inst�ncia em mais de um range no Siebel");
							arb.setResultado(-2);
							instanciaSiebel.setResultado(-2);
							return false;
						} 
						// retirado c�digo que verificava todas as inst�ncias do range
						/*else{
							arb.setLog("Erro Siebel: Inst�ncia em range com designador duplicado");
							instanciaSiebel.setLog("Erro Siebel: Inst�ncia est� em range que possui ext_id com designador duplicado " + String.valueOf(instArbor));
							arb.setResultado(-2);
						}*/
					}

					instanciasArbor.add(arb);

					instArbor++;
				}
			}
		}

		instanciaSiebel.setInstanciasArbor(instanciasArbor);
		return true;
	}

}
