package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Arbor;
import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440ValidaAtvPlano extends ActionAbstract<Object>{

	private OraErro440Arbor arbor440 = new OraErro440Arbor();
	
	public void exec(Object arg0) {
		
		PlanoArborVO reg = (PlanoArborVO) arg0;
		
		if(reg.getServerId()>2){
			reg.setResult(String.valueOf(arbor440.validaPlano(reg.getServerId(), reg.getExternalId(), reg.getComponentId())));
		}else{
			reg.setResult("0");
		}
		
	}

}
