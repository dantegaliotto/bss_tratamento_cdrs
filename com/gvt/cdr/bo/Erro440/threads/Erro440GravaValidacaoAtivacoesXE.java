package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.vo.AutorotAtivacaoVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440GravaValidacaoAtivacoesXE extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		AutorotAtivacaoVO ativacaoArbor = (AutorotAtivacaoVO) arg0;
		
		xe.gravaResultadoAtivacaoAutorotXe(ativacaoArbor);
		
	}
}
