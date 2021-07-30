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
			instancia.setLog("Siebel5 - Instância inativa no Siebel5 (Load)");
			instancia.setResultado(-2);
			return;
		}
		
		String resultado = this.buscaPlanosSiebel(instancia, "Ativo");
		
		if (resultado.equals("Plano não encontrado")){
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
	 * buscaPlanosSiebel - Foi alterada a regra em 24/02/2012 - antes só carregava os planos do Siebel
	 * para o Corporate A La Carte, agora deve carregar para todos os planos Corporate
	 * --------
	 * buscaPlanosSiebel - Foi alterada a regra em 09/04/2012 - Planos corporate sem parametros 
	 * devem ser configurados com tarifa VOX DDR
	 */
	private String buscaPlanosSiebel(InstanciaSiebelVO instancia, String status) {
		
		// Verificacao se é TV - se for sai da busca de planos
		if (instancia.getInstancia().startsWith("TV-")){
			siebel.retornaPlanoTv(instancia, status);
			return "Instância TV";
		}

		// Verificacao se é Lavoisier - se for sai da busca de planos
		// 2016-05-18: ALTERADA QUERY PARA SINALIZAR SE O PLANO É NÃO CORP
		siebel.retornaPlanoRetailLavoisier(instancia, status);
		if(! (instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals(""))){
			instancia.setTipoPlano("L");
			xe.buscaComplementoPlanos(instancia);
			return "Instância com plano Não CORP";
		}
		
		// VERIFICA SE TEM SOBREPOSICAO DE PLANOS CORPORATE E RETAIL
		if (siebel.verificaDuplicidadePlanos(instancia)){
//			return "Plano duplicado no Siebel 5";
		}

		// Verifica se é Retail - se não for verifica se é corporate
		//siebel.retornaPlanoRetail(instancia, status);
		//siebel.retornaPlanoCSP(instancia, status);
		
		if(! (instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals(""))){
			// Cliente é Retail
			if (instancia.getPlano().startsWith("Plano Premium")){ 
				// Não carrega o CSP nem guiador VC para o plano Cadillac
				instancia.setCspIdSiebel(0);
				instancia.setCspSiebel("");
				instancia.setCspIdKenan(0);
				instancia.setDataCspSiebel(null);
			}else{
				siebel.retornaPlanoRetailVc(instancia, status);
				// Não precisa pegar CSP agora pois já pegou no início
			}
		}else{
			// Cliente não é Retail
			siebel.retornaRange(instancia);
			siebel.retornaPlanoCorp(instancia, status);
			if(instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals("")){

				if(instancia.getTipoInstanciaSiebel().startsWith("Linha telefônica")){
					// Se for Linha telefônica e não tinha Plano Corporate (é possível) então é retail sem plano
					// Não faz nada aqui mas vai marcar o erro no 'exec'
				} // Se não era retail e não encontrou plano corporate deve ser 0800, 0800 RI ou VOX DDR
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
					instancia.setCspSiebel("Serviço Vox Dial Tone");
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
					// Caso a instancia nao tenha retornado parametros e não seja Linha Telefônica, entao deve-se 
					// provisionar o VOX DDR
					instancia.setTipoPlano("C");
					if((instancia.getCspIdSiebel() == 0) || (instancia.getCspIdSiebel() == 23842)){
						instancia.setCspSiebel("CSP 25 - LDN Minuto - LDI Minuto");
						instancia.setCspIdSiebel(23669);
						instancia.setCspIdKenan(xe.deParaCspSiebelKenan(instancia.getCspIdSiebel()));
						instancia.setDataCspSiebel(instancia.getDataPlanoSiebel());
					}
					instancia.setRowIdElementoPlano("0");
					instancia.setPlano("SERVIÇO VOX DDR");
					instancia.setComponentId(25903);
					instancia.setDataPlanoSiebel(instancia.getDtInstanciaSiebel());
					
				}

			}else{
				// Significa que é "Corporate", não é 0800, não é VOX DDR puro
				siebel.retornaParametros(instancia);
				instancia.setTipoPlano("C");
				// 2014-11-12: Aniversário da minha Mãe querida. Não vai mais trazer o CSP25 PPP
				//instancia.setCspSiebel("CSP 25 - Para Plano Pacote");
				//instancia.setCspIdSiebel(23842);
				//instancia.setCspIdKenan(xe.deParaCspSiebelKenan(instancia.getCspIdSiebel()));
				//instancia.setDataCspSiebel(instancia.getDataPlanoSiebel());

			}
		
		}
		if(instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals("")){
			return "Plano não encontrado";
		}
		return "Plano ok";
		
	}


}
