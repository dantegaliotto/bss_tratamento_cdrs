package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Siebel8;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaInfSiebel8 extends ActionAbstract<Object>{

	private OraErro440Siebel8 siebel8 = new OraErro440Siebel8().getInstance();
	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;
		
		siebel8.retornaTipoInstanciaEData(instancia);
		
		if(instancia.getTipoInstanciaSiebel()==null || instancia.getTipoInstanciaSiebel().equals("")){
			instancia.setLog("Erro Siebel8: Instância inativa");
			instancia.setResultado(-2);
			return;
		}
		
		this.buscaPlanosSiebel8(instancia);
		
		if (((instancia.getPlano()==null || instancia.getPlano().equals("")))){

			instancia.setLog("Erro Siebel8: Plano ativo não encontrado");
			instancia.setResultado(-2);
			instancia.setTipoPlano("");
			
		}

	}

	/*
	 * buscaPlanosSiebel - Foi alterada a regra em 24/02/2012 - antes só carregava os planos do Siebel
	 * para o Corporate A La Carte, agora deve carregar para todos os planos Corporate
	 * --------
	 * buscaPlanosSiebel - Foi alterada a regra em 09/04/2012 - Planos corporate sem parametros 
	 * devem ser configurados com tarifa VOX DDR
	 */
	private void buscaPlanosSiebel8(InstanciaSiebelVO instancia) {
		
		// Verificacao se é Lavoisier - se for sai da busca de planos
		siebel8.retornaPlanoRetail(instancia);
		if((instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals("")))
			siebel8.retornaPlanoRetailPelaOferta(instancia);
		if(! (instancia.getRowIdElementoPlano()==null || instancia.getRowIdElementoPlano().equals(""))){
			instancia.setTipoPlano("RS8");
			if( instancia.getInstancia().startsWith("TV")){
				instancia.setTipoPlano("TV");
			}else if( (instancia.getPlano().equals("GVT Ilimitado Local Casa") ||
					   instancia.getPlano().equals("GVT Ilimitado Local Smart") ||
					   instancia.getPlano().equals("GVT Ilimitado Local Favoritos")
			)){
			// NO CASO DO "GVT Ilimitado Local * " É NECESSÁRIO VERIFICAR SE POSSUI O CSP 25 ILIMITADO
				if(siebel8.retorna25Ilimitado(instancia)){
					instancia.setPlano(instancia.getPlano() + " (com 25 Ilimitado)");
				}
			}
			
			if(!xe.buscaComplementoPlanos(instancia)){
				instancia.setResultado(-2);
				instancia.setLog("Corrigir Java - Plano não encontrado no mapeamento (bss_mapa_planos)");
			}
			
			return;
			
		}
	}

}
