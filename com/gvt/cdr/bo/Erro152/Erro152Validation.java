package com.gvt.cdr.bo.Erro152;

import java.sql.Date;
import java.util.List;

import com.gvt.cdr.bo.Erro152.dao.OraErro152Dgrc;
import com.gvt.cdr.bo.Erro152.dao.OraErro152Kenan;
import com.gvt.cdr.bo.Erro152.vo.InstanciaSiebelVO;
import com.gvt.util.Log;

public class Erro152Validation {

	private OraErro152Dgrc  dgrc  = new OraErro152Dgrc().getInstance();
	private OraErro152Kenan kenan = new OraErro152Kenan().getInstance();
	
	public void executeValidation() {

		// traz lista de instancias a serem validadas no arbor
		List<InstanciaSiebelVO> instanciasSiebel = dgrc.carregaListaInstanciasSiebel("6");
		int serverId = 0;
		
		InstanciaSiebelVO instanciaSiebel = null;

		Log.info("> Total de casos: " + instanciasSiebel.size(), this);
		
		for(int x=0; x<instanciasSiebel.size(); x++){
			
			instanciaSiebel = (InstanciaSiebelVO)instanciasSiebel.get(x);

			if  (kenan.verificaContaCriada(instanciaSiebel.getContaCli())) instanciaSiebel.setVerCli("ok"); 
			else instanciaSiebel.setVerCli("NÃO");
			if  (kenan.verificaContaCriada(instanciaSiebel.getContaAgg())) instanciaSiebel.setVerAgg("ok"); 
			else instanciaSiebel.setVerAgg("NÃO");
			if  (kenan.verificaContaCriada(instanciaSiebel.getContaCob())) instanciaSiebel.setVerCob("ok"); 
			else instanciaSiebel.setVerCob("NÃO");
			
			serverId = kenan.verificaInstanciaCriadaCat(instanciaSiebel);
			if (serverId != 0) 
				if (kenan.verificaInstanciaCriadaCust(instanciaSiebel, serverId)) instanciaSiebel.setVerInstancia("ok");
				else instanciaSiebel.setVerInstancia("NÃO CUST");
			else instanciaSiebel.setVerInstancia("NÃO");

			System.out.println(instanciaSiebel.getResultadoGeral());
			if (instanciaSiebel.getResultadoGeral().equals("ok ok ok ok")){
				instanciaSiebel.setResultado(1);
				instanciaSiebel.setLog("Instância ok");
			}
			if (! instanciaSiebel.getResultadoGeral().startsWith("ok ok ok")){
				instanciaSiebel.setResultado(5);
				instanciaSiebel.setLog("Erro de conta");
			}else 
				if (! instanciaSiebel.getResultadoGeral().endsWith("ok")){
					instanciaSiebel.setResultado(4);
					instanciaSiebel.setLog("Erro na instância");
				}
			
			dgrc.atualizaFlagErro(instanciaSiebel);
			
		}

	}

}
