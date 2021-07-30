package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaInfViewCdrEmErro extends ActionAbstract<Object>{

	private OraErro440Xe xe = new OraErro440Xe();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instanciaSiebel = (InstanciaSiebelVO) arg0;
		
		if (instanciaSiebel.getResultado() < 0){
			return;
		}

		// BUSCA LOG NA VIEW
		xe.retornaInfoViewCdrEmErro(instanciaSiebel);
	
	}

}
