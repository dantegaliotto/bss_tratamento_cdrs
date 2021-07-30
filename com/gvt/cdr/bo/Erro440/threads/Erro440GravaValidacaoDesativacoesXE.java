package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.vo.AutorotDesativacaoVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440GravaValidacaoDesativacoesXE extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		AutorotDesativacaoVO desativacaoArbor = (AutorotDesativacaoVO) arg0;
		
		xe.gravaResultadoDesativacaoAutorotXe(desativacaoArbor);
		
	}
}
