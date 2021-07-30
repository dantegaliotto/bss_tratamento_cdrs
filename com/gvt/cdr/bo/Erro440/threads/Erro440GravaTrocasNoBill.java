package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.AccountVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440GravaTrocasNoBill extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		AccountVO accountNo = (AccountVO) arg0;
		
		xe.inserirLogTrocasCmf(accountNo);
		
	}
}
