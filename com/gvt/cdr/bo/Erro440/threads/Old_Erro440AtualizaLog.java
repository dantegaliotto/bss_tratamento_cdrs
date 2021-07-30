package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.util.threads.ActionAbstract;

public class Old_Erro440AtualizaLog extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		PlanoArborVO caso = (PlanoArborVO) arg0;
		
		xe.updateLogValidacaoPlanosNoArbor(caso);
		
	}

}
