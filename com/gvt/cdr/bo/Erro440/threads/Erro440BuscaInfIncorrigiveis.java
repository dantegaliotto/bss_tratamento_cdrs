package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaInfIncorrigiveis extends ActionAbstract<Object>{

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;
		
		xe.retornaQtdeIncorrigiveis(instancia);
		
	}

}
