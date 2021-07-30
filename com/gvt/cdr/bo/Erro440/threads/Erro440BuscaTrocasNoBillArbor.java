package com.gvt.cdr.bo.Erro440.threads;


import com.gvt.cdr.bo.Erro440.dao.OraErro440Arbor;
import com.gvt.cdr.bo.Erro440.vo.AccountVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaTrocasNoBillArbor extends ActionAbstract<Object>{

	private OraErro440Arbor arbor440 = new OraErro440Arbor();
	
	public void exec(Object arg0) {
		
		AccountVO account = (AccountVO) arg0;

		arbor440.retornaTrocasCmf(account);

	}

}
