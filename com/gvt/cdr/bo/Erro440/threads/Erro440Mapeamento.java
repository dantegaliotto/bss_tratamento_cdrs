package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Aiparch;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440Mapeamento extends ActionAbstract<Object>{

	private OraErro440Aiparch aiparch = new OraErro440Aiparch().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;

		if (instancia.getResultado() < 0 || instancia.getTipoPlano().equals("L")){
			return;
		}
		
		// atualiza o componentid do plano só se estiver vazio pois pode ter sido 
		// preenchido no load - Erro440BuscaInfSiebel.java
		if (instancia.getComponentId() == 0)
			instancia.setComponentId(aiparch.retornaComponentId(instancia.getPlano()));
       		
		instancia.setParComponentIdLocal(aiparch.retornaComponentId(instancia.getSlocal()));
		instancia.setParComponentIdVc(aiparch.retornaComponentId(instancia.getSvc()));
		instancia.setParComponentIdLdn(aiparch.retornaComponentId(instancia.getSldn()));
		instancia.setParComponentIdLdi(aiparch.retornaComponentId(instancia.getSldi()));
		
	}

}
