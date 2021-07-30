package com.gvt.cdr.bo.Erro440;

import java.sql.Date;
import java.util.List;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Arbor;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.Log;

public class Erro440Validation {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	private OraErro440Arbor arbor = new OraErro440Arbor();
	
	public void executeValidation(String sequencial) {

		//xe.inserirLogExecucoes("validation", "inicio");
		// traz lista de instancias a serem validadas no arbor
		List<InstanciaSiebelVO> instanciasSiebel = xe.carregaListaInstanciasSiebel("2,4,6", sequencial);
		
		int indErro = 0;
		
		InstanciaSiebelVO instanciaSiebel = null;
		//InstanciaArborVO instanciaArbor = null;

		Log.info("> Total de casos: " + instanciasSiebel.size(), this);
		
		for(int x=0; x<instanciasSiebel.size(); x++){
			
			instanciaSiebel = (InstanciaSiebelVO) instanciasSiebel.get(x);

			instanciaSiebel.setInstanciasArbor(xe.carregaListaInstanciasArbor(instanciaSiebel.getInstancia()));
			
			String resultadoPlanos = this.validaPlanos(instanciaSiebel);

			Log.info(instanciaSiebel.getInstancia() + " " + resultadoPlanos);
			
			if (resultadoPlanos.startsWith("Planos Ok!")){		
				indErro = 1;
			}else {
				switch(instanciaSiebel.getResultado()){
					case 6:  indErro = 5; break;
					case 4:  indErro = 3; break;
					case 2:  indErro = -1; break;
					default: indErro = -9; break;
				}
			}
			xe.atualizaFlagErro(sequencial, instanciaSiebel, indErro, resultadoPlanos);
		}

	}

	
	public String validaPlanos(InstanciaSiebelVO instanciaSiebel){

		List<InstanciaArborVO> instanciasArbor = instanciaSiebel.getInstanciasArbor(); 
		InstanciaArborVO instanciaArbor = null;

		int qtdePlanos = 0;

		for(int x=0; x<instanciasArbor.size(); x++){
			
			instanciaArbor = (InstanciaArborVO) instanciasArbor.get(x);
			
			qtdePlanos = 0;
			int subscrNo = instanciaArbor.getSubscrNo();
			int accountNo = instanciaSiebel.getAccountNo();
			int serverId = instanciaSiebel.getServerId();
			Date dataPlano = instanciaSiebel.getDataPlanoSiebel();

			if ((""+instanciaSiebel.getTipoPlano()).equals("null")){
				instanciaSiebel.setResultado(-2);
				return "Erro no tipo plano (R, C, RS8, L)";}
			
			try{
				if (instanciaSiebel.getUltimoCdr().before(instanciaSiebel.getDataPlanoSiebel())){
					instanciaSiebel.setResultado(-2);
					return "Incorrigíveis - Data do plano posterior ao último CDR";}

				if (instanciaSiebel.getUltimoCdr().before(instanciaSiebel.getDtInstanciaKenan())){
					instanciaSiebel.setResultado(-2);
					return "Incorrigíveis - Data da instância posterior ao último CDR";}
			}catch(Exception ex){
				Log.error("Erro no cálculo da data... ignorar", ex);
			}
			
			// PARTE 0800 ------------------------------------------------------------
			if ((""+instanciaSiebel.getTipoPlano()).equals("C") && (""+instanciaSiebel.getInstancia()).startsWith("800")){
				// verifica primeiramente o servico do vox 0800 (vai verificar o desconto posteriormente junto ao local)
				if (instanciaSiebel.getComponentId() > 0){
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getComponentId(), dataPlano, serverId))
						return "Java deve tratar - Erro de VOX 0800";}

			// PARTE REMOTA RI -------------------------------------------------------
			}else if ((""+instanciaSiebel.getTipoPlano()).equals("C") && (""+instanciaSiebel.getTipoInstanciaSiebel()).startsWith("VOX REMOTA RI")){
				if (instanciaSiebel.getComponentId() > 0){
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getComponentId(), dataPlano, serverId))
						return "Java deve tratar - Erro de VOX REMOTA RI";}

			// PARTE TV --------------------------------------------------------------
			}else if ((""+instanciaSiebel.getTipoPlano()).equals("TV") && (""+instanciaSiebel.getInstancia()).startsWith("TV")){
				if (instanciaSiebel.getComponentId() > 0){
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getComponentId(), dataPlano, serverId))
						return "Java deve tratar - Erro plano TV";}

			// PARTE VOX DDR (APENAS VERIFICA O PLANO PARA QUEM NÃO TEM PARAMETROS CORPORATE)
			}else if ((instanciaSiebel.getComponentId() > 0) && (""+instanciaSiebel.getPlano()).startsWith("SERVIÇO VOX DDR")
				   && (instanciaSiebel.getParComponentIdLocal() == 0)){
				qtdePlanos++;
				if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getComponentId(), dataPlano, serverId))
					return "Java deve tratar - Erro de VOX DDR";

			// PARTE CORPORATE --------------------------------------------------------
			}else if ((""+instanciaSiebel.getTipoPlano()).equals("C")){

				if ((instanciaSiebel.getParComponentIdLocal() > 0) &&
					(instanciaSiebel.getParComponentIdLocal() != instanciaSiebel.getComponentId())){
					qtdePlanos++;
	   			    if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getParComponentIdLocal(), dataPlano, serverId))
	   				   return "Java deve tratar - Erro Plano Corporate";}
				if ((instanciaSiebel.getParComponentIdVc()    > 0) &&
					(instanciaSiebel.getParComponentIdVc()    != instanciaSiebel.getComponentId())){
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getParComponentIdVc(), dataPlano, serverId))
					   return "Java deve tratar - Erro Plano Corporate";}
				if ((instanciaSiebel.getParComponentIdLdn()   > 0) &&
					(instanciaSiebel.getParComponentIdLdn()   != instanciaSiebel.getComponentId())){ 
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getParComponentIdLdn(), dataPlano, serverId))
					   return "Java deve tratar - Erro Plano Corporate";}
				if ((instanciaSiebel.getParComponentIdLdi()   > 0) &&
					(instanciaSiebel.getParComponentIdLdi()   != instanciaSiebel.getComponentId())){
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getParComponentIdLdi(), dataPlano, serverId))
					   return "Java deve tratar - Erro Plano Corporate";}
				if ((instanciaSiebel.getComponentId() > 0) && (""+instanciaSiebel.getPlano()).startsWith("Plano Corporate")
						   && (qtdePlanos < 4)){
					instanciaSiebel.setResultado(-2);
				    return "Erro Siebel 5: Erro de Corporate sem parâmetros";
				}
				
			// PARTE RETAIL
			}else if ((instanciaSiebel.getComponentId() > 0) && 
					(   (""+instanciaSiebel.getTipoPlano()).equals("R") 
					 || (""+instanciaSiebel.getTipoPlano()).equals("RS8")
					 || (""+instanciaSiebel.getTipoPlano()).equals("L"))){ 
					qtdePlanos++;
					if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getComponentId(), dataPlano, serverId))
						return "Java deve tratar - Erro plano retail";
					if ((instanciaSiebel.getParComponentIdVc()  > 0) &&
					    (instanciaSiebel.getParComponentIdVc()  != instanciaSiebel.getComponentId())){
						qtdePlanos++;
						if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getParComponentIdVc(), dataPlano, serverId))
						   return "Java deve tratar - Erro plano VC RETAIL";}
			}
			
			// PARTE CSP GERAL
			if ((instanciaSiebel.getCspIdKenan()  > 0) &&
		        (instanciaSiebel.getCspIdKenan()  != instanciaSiebel.getComponentId()) &&
		        (instanciaSiebel.getCspIdKenan()  != 24006)){
				qtdePlanos++;
				if ( ! arbor.verificaPlanoAtivo(subscrNo, instanciaSiebel.getCspIdKenan(), dataPlano, serverId))
				   return "Java deve tratar - Erro de CSP";}

//// 2014-09-24: RETIRADA A VALIDAÇÃO PARA NÍVEL CONTA
////			// SER FOR SIEBEL 8 VAI VALIDAR OS PRODUTOS NIVEL CONTA
////			if (instanciaSiebel.getTipoPlano().equals("RS8") || instanciaSiebel.getTipoPlano().equals("L")){
////				// PARTE FRANQUIA NIVEL CONTA
////				if (instanciaSiebel.getCompIdFranquiaConta() > 0){
////					//qtdePlanos++; // NÃO CONSIDERA NÍVEL CONTA NA CONTAGEM DE PLANOS - SÓ VERIFICA SE OS OBRIGATÓRIOS ESTÃO ATIVOS (abaixo)
////					if ( ! arbor.verificaNivelContaAtivo(accountNo, instanciaSiebel.getCompIdFranquiaConta(), dataPlano, serverId))
////						return "Java deve tratar - Erro de Franquia Nível Conta";}
////				// PARTE CSP NIVEL CONTA
////				if (instanciaSiebel.getCompIdCspConta() > 0){
////					//qtdePlanos++; // NÃO CONSIDERA NÍVEL CONTA NA CONTAGEM DE PLANOS - SÓ VERIFICA SE OS OBRIGATÓRIOS ESTÃO ATIVOS (abaixo)
////					if ( ! arbor.verificaNivelContaAtivo(accountNo, instanciaSiebel.getCompIdCspConta(), dataPlano, serverId))
////						return "Java deve tratar - Erro de CSP Nível Conta";}
////
////			}
			
			// CONTAGEM DE PLANOS - SE CHEGOU AQUI DEVE ESTAR COM OS PLANOS CORRETOS MAS 
			// A QUANTIDADE DE PLANOS PODE ESTAR DIFERENTE NO KENAN
			if (qtdePlanos == 0){
				instanciaSiebel.setResultado(-2);
				return "Corrigir Java - Plano não encontrado no mapeamento (bss_mapa_planos)"; }
			
			int qtdePlanosAtivos = arbor.contaPlanosNivelInstanciaAtivos(subscrNo, serverId, instanciaSiebel.getTipoPlano());
			// NÃO CONSIDERA O NÍVEL CONTA NA CONTAGEM DE PLANOS
			//if (instanciaSiebel.getTipoPlano().equals("RS8") || instanciaSiebel.getTipoPlano().equals("L")){
			//	qtdePlanosAtivos = qtdePlanosAtivos + arbor.contaPlanosNivelContaAtivos(accountNo, serverId, instanciaSiebel.getTipoPlano());
			//}
			
			if (qtdePlanosAtivos > qtdePlanos)
				return "Java deve tratar - Planos a MAIS"; // (siebel: " + qtdePlanos + " kenan: " + qtdePlanosAtivos + ")";
			if (qtdePlanosAtivos < qtdePlanos)
				return "Java deve tratar - Planos a MENOS"; // (siebel: " + qtdePlanos + " kenan: " + qtdePlanosAtivos + ")";

			// VERIFICA A MAIOR DATA DOS PLANOS ATIVOS - SE FOR MAIOR QUE O ÚLTIMO CDR E PUDER SER RETROAGIDA
			// ENTÃO MARCA COMO INCORRETO
			try{
				boolean flgValidaConta = false;
				if (instanciaSiebel.getTipoPlano().equals("RS8") || instanciaSiebel.getTipoPlano().equals("L"))
					flgValidaConta = false; // FOI RETIRADA A VALIDAÇÃO DA DATA DOS PLANOS NIVEL CONTA P QQ CASO //= true;
				Date maiorDataDosPlanosAtivos = arbor.verificaAMaiorDataDosPlanosAtivos(accountNo, subscrNo, serverId, flgValidaConta);
				if (maiorDataDosPlanosAtivos.getTime() > instanciaSiebel.getPrimeiroCdr().getTime()&&
					maiorDataDosPlanosAtivos.getTime() > instanciaSiebel.getDataPlanoSiebel().getTime()&&
					//maiorDataDosPlanosAtivos.getTime() > instanciaSiebel.getDtUltimoFaturamento().getTime()&&
					maiorDataDosPlanosAtivos.getTime() > instanciaSiebel.getDtInstanciaKenan().getTime()){
					return "Java deve tratar - Data do plano pode ser retroagida";
				}
			}catch(Exception ex){
				Log.error("Erro no cálculo da data... ignorar", ex);
			}
			
			// PARA RETAIL, VALIDA OS COMPONENTES DA INSTANCIA RELATIVO AOS ELEMENTOS
			if (instanciaSiebel.getTipoPlano().equals("R"))
				if (instanciaSiebel.getComponentId() > 0)
					if(! arbor.validaElementosComponente(instanciaArbor, instanciaSiebel.getComponentId(), instanciaSiebel.getServerId()))
						return "Erro Kenan: Erro de elementos no plano Retail";
			
			// VERIFICA SE ALGUM DOS PLANOS DO CLIENTE FOI MARCADO COMO POSTERIOR A DATA DO PRIMEIRO CDR (COM POSSIBILIDADE DE CORREÇÃO)
			// E VAI DESCONECTAR OS PLANOS
			
		}
		return "Planos Ok!";
			
	}

}
