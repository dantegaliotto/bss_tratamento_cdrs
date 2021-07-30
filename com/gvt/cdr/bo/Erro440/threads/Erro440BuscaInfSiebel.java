package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Siebel;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaInfSiebel extends ActionAbstract<Object>{

	private OraErro440Siebel siebel = new OraErro440Siebel().getInstance();
	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;
		
		siebel.retornaTipoInstanciaEData(instancia);
		
		if(instancia.getTipoInstanciaSiebel()==null || instancia.getTipoInstanciaSiebel().equals("")){
			instancia.setLog("Siebel5 - Inst�ncia inativa no Siebel5 (Load)");
			instancia.setResultado(-2);
			return;
		}
		
		String resultado = this.buscaPlanosSiebel(instancia, "Ativo");
		
		if (resultado.equals("Plano n�o encontrado")){
			instancia.setLog("Erro Siebel 5: Sem plano");
			instancia.setResultado(-2);
			instancia.setTipoPlano("");
			return;
		}
		if (resultado.equals("Plano duplicado no Siebel 5")){
			instancia.setLog("Erro Siebel 5: Planos duplicados");
			instancia.setResultado(-2);
			instancia.setTipoPlano("");
			return;
		}

	}

	/*
	 * buscaPlanosSiebel - Foi alterada a regra em 24/02/2012 - antes s� carregava os planos do Siebel
	 * para o Corporate A La Carte, agora deve carregar para todos os planos Corporate
	 * --------
	 * buscaPlanosSiebel - Foi alterada a regra em 09/04/2012 - Planos corporate sem parametros 
	 * devem ser configurados com tarifa VOX DDR
	 */
	private String buscaPlanosSiebel(InstanciaSiebelVO instancia, String status) {
		
		// Verificacao se � TV - se for sai da busca de planos
		if (instancia.getInstancia().startsWith("TV-")){
			siebel.retornaPlanoTv(instancia, status);
			return "Inst�ncia TV";
		}

		// Verificacao se � Lavoisier - se for sai da busca de planos
		// 2016-05-18: ALTERADA QUERY PARA SINALIZAR SE O PLANO � N�O CORP
		siebel.retornaPlanoRetailLavoisier(instancia, status);
		if(! (instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals(""))){
			instancia.setTipoPlano("L");
			xe.buscaComplementoPlanos(instancia);
			return "Inst�ncia com plano N�o CORP";
		}
		
		// VERIFICA SE TEM SOBREPOSICAO DE PLANOS CORPORATE E RETAIL
		if (siebel.verificaDuplicidadePlanos(instancia)){
//			return "Plano duplicado no Siebel 5";
		}

		// Verifica se � Retail - se n�o for verifica se � corporate
		//siebel.retornaPlanoRetail(instancia, status);
		//siebel.retornaPlanoCSP(instancia, status);
		
		if(! (instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals(""))){
			// Cliente � Retail
			if (instancia.getPlano().startsWith("Plano Premium")){ 
				// N�o carrega o CSP nem guiador VC para o plano Cadillac
				instancia.setCspIdSiebel(0);
				instancia.setCspSiebel("");
				instancia.setCspIdKenan(0);
				instancia.setDataCspSiebel(null);
			}else{
				siebel.retornaPlanoRetailVc(instancia, status);
				// N�o precisa pegar CSP agora pois j� pegou no in�cio
			}
		}else{
			// Cliente n�o � Retail
			siebel.retornaRange(instancia);
			siebel.retornaPlanoCorp(instancia, status);
			if(instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals("")){

				if(instancia.getTipoInstanciaSiebel().startsWith("Linha telef�nica")){
					// Se for Linha telef�nica e n�o tinha Plano Corporate (� poss�vel) ent�o � retail sem plano
					// N�o faz nada aqui mas vai marcar o erro no 'exec'
				} // Se n�o era retail e n�o encontrou plano corporate deve ser 0800, 0800 RI ou VOX DDR
				else if(instancia.getTipoInstanciaSiebel().startsWith("VOX 0800 RI")){

					instancia.setTipoPlano("C");
					instancia.setCspIdSiebel(0);
					instancia.setCspSiebel("");
					instancia.setCspIdKenan(0);
					instancia.setDataCspSiebel(null);
					// busca o plano de 0800 RI do cliente
				    instancia.setPlano("");
					siebel.retornaProduto0800RI(instancia);
					if(instancia.getPlano().equals("RI GVT I"))
						instancia.setComponentId(29603);
					if(instancia.getPlano().equals("RI GVT II"))
						instancia.setComponentId(29602);
					if(instancia.getPlano().equals("RI GVT III"))
						instancia.setComponentId(29601);
					if(instancia.getPlano().equals("RI ESPECIAL"))
						instancia.setComponentId(29600);
				}else if(instancia.getTipoInstanciaSiebel().startsWith("VOX REMOTA RI")){

						instancia.setTipoPlano("C");
						instancia.setCspIdSiebel(0);
						instancia.setCspSiebel("");
						instancia.setCspIdKenan(0);
						instancia.setDataCspSiebel(null);
						// busca o plano de VOX REMOTA RI do cliente
					    instancia.setPlano("");
						siebel.retornaProdutoRI(instancia);
						if(instancia.getPlano().equals("VOX REMOTA GVT I"))
							instancia.setComponentId(29598);
						if(instancia.getPlano().equals("VOX REMOTA GVT II"))
							instancia.setComponentId(29596);
						if(instancia.getPlano().equals("VOX REMOTA GVT III"))
							instancia.setComponentId(29594);
						if(instancia.getPlano().equals("VOX REMOTA ESPECIAL"))
							instancia.setComponentId(29592);
				}else if(instancia.getTipoInstanciaSiebel().startsWith("VOX REMOTA")){

					instancia.setTipoPlano("C");
					instancia.setCspIdSiebel(0);
					instancia.setCspSiebel("");
					instancia.setCspIdKenan(0);
					instancia.setDataCspSiebel(null);
					// busca o plano de VOX REMOTA do cliente
				    instancia.setPlano("");
					siebel.retornaProdutoRI(instancia);
					if(instancia.getPlano().equals("VOX REMOTA GVT I"))
						instancia.setComponentId(27338);
					if(instancia.getPlano().equals("VOX REMOTA GVT II"))
						instancia.setComponentId(27340);
					if(instancia.getPlano().equals("VOX REMOTA GVT III"))
						instancia.setComponentId(27342);
					if(instancia.getPlano().equals("VOX REMOTA ESPECIAL"))
						instancia.setComponentId(27344);
					instancia.setCspSiebel("Servi�o Vox Dial Tone");
					instancia.setCspIdSiebel(25496);
					instancia.setCspIdKenan(xe.deParaCspSiebelKenan(instancia.getCspIdSiebel()));

				}else if(instancia.getTipoInstanciaSiebel().startsWith("VOX 0800")){
					// Para o 0800 zera o CSP e carrega o plano 0800 e o desconto
					instancia.setTipoPlano("C");
					instancia.setCspIdSiebel(0);
					instancia.setCspSiebel("");
					instancia.setCspIdKenan(0);
					instancia.setDataCspSiebel(null);
					if (siebel.retornaProduto0800(instancia))
						siebel.retornaDesconto0800(instancia);
				}else if(instancia.getSlocal()==null || instancia.getSlocal().equals("")){
					// Caso a instancia nao tenha retornado parametros e n�o seja Linha Telef�nica, entao deve-se 
					// provisionar o VOX DDR
					instancia.setTipoPlano("C");
					if((instancia.getCspIdSiebel() == 0) || (instancia.getCspIdSiebel() == 23842)){
						instancia.setCspSiebel("CSP 25 - LDN Minuto - LDI Minuto");
						instancia.setCspIdSiebel(23669);
						instancia.setCspIdKenan(xe.deParaCspSiebelKenan(instancia.getCspIdSiebel()));
						instancia.setDataCspSiebel(instancia.getDataPlanoSiebel());
					}
					instancia.setRowIdElementoPlano("0");
					instancia.setPlano("SERVI�O VOX DDR");
					instancia.setComponentId(25903);
					instancia.setDataPlanoSiebel(instancia.getDtInstanciaSiebel());
					
				}

			}else{
				// Significa que � "Corporate", n�o � 0800, n�o � VOX DDR puro
				siebel.retornaParametros(instancia);
				instancia.setTipoPlano("C");
				// 2014-11-12: Anivers�rio da minha M�e querida. N�o vai mais trazer o CSP25 PPP
				//instancia.setCspSiebel("CSP 25 - Para Plano Pacote");
				//instancia.setCspIdSiebel(23842);
				//instancia.setCspIdKenan(xe.deParaCspSiebelKenan(instancia.getCspIdSiebel()));
				//instancia.setDataCspSiebel(instancia.getDataPlanoSiebel());

			}
		
		}
		if(instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals("")){
			return "Plano n�o encontrado";
		}
		return "Plano ok";
		
	}


}
