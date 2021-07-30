package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Siebel;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440BuscaRpon extends ActionAbstract<Object> {

	private OraErro440Siebel siebel = new OraErro440Siebel().getInstance();
	
	public void exec(Object arg0) {

		InstanciaSiebelVO instancia = (InstanciaSiebelVO) arg0;

		siebel.retornaRponDesignadorStatusAcao(instancia, "Ativo");
/*
		if ((instancia.getResultado() == -2) && (instancia.getLog() == "Rpon ativo não encontrado")){
			siebel.retornaRponDesignadorStatusAcao(instancia, "Inativo");
			if ((instancia.getResultado() == -2) && (instancia.getLog() == "Rpon ativo não encontrado"))
				siebel.retornaRponDesignadorStatusAcao(instancia, "Cancelado");
		}
*/
	}

	/*
		String rpon = siebel.retornaRpon(instancia.getInstancia());
		
		if(rpon==null || rpon.equals("")){

			if( ! siebel.retornaDesignador(instancia)){
				if ( ! siebel.retornaDesignadorTextoLivre(instancia)){
					instancia.setResultado(-2);
					return;
				}
			}
			rpon = siebel.retornaRpon(instancia.getDesignador());
			
			if(rpon==null || rpon.equals("")){
				instancia.setLog("Siebel: Erro na busca do Rpon " + instancia.getDesignador() );
				instancia.setResultado(-2);
				return;
			}
		}
		instancia.setRpon(rpon);
	} */
	

}
