package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Svcpgen;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440ValidaInfoCdr extends ActionAbstract<Object> {

//	private OraErro440Siebel siebel = new OraErro440Siebel().getInstance();
	private OraErro440Svcpgen svcpgen = new OraErro440Svcpgen().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;
		
		svcpgen.verificaRetryCount(instancia);

	}
		
}
