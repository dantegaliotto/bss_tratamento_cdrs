package com.gvt.cdr.bo.Erro430;

import java.util.List;

import com.gvt.cdr.bo.Erro430.dao.OraErro430Arbor;
import com.gvt.cdr.bo.Erro430.dao.OraErro430Svcpgen;
import com.gvt.cdr.bo.Erro430.dao.OraErro430Xe;
import com.gvt.cdr.bo.Erro430.vo.InstanciaVO;
import com.gvt.util.Log;

public class Erro430Load {

	private OraErro430Svcpgen svcpgen = new OraErro430Svcpgen().getInstance();
	private OraErro430Xe xe = new OraErro430Xe().getInstance();
	private OraErro430Arbor arbor = new OraErro430Arbor().getInstance();
	
	public void executeLoad430() {

		System.out.println("Carregando instâncias com planos duplicados...");
		this.cargaLoad();
		
	}

	private void cargaLoad() {

		List<InstanciaVO> casos430 = null;
		
		casos430 = svcpgen.retornaCasos430();

		Log.info("> Total de casos sem agrupamento: " + casos430.size(), this);
		
		for (int i = 0 ; i < casos430.size(); i++){

			arbor.retornaSubscrNo(casos430.get(i));
			arbor.retornaAccountNo(casos430.get(i));
			arbor.retornaCiclo(casos430.get(i));
			arbor.retornaNivelInstanciaLavoisier(casos430.get(i));

			System.out.println("grava no banco: " + casos430.get(i).getExternalId() 
					+ " / " + casos430.get(i).getLavoisier() 
					+ " / " + casos430.get(i).getServerId());
			xe.inserirLogLoad(casos430.get(i));
			
		}
		
	}

	
}
