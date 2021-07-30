package com.gvt.cdr.bo.Erro430;

import java.util.List;

import com.gvt.cdr.bo.Erro430.dao.OraErro430Arbor;
import com.gvt.cdr.bo.Erro430.dao.OraErro430Svcpgen;
import com.gvt.cdr.bo.Erro430.dao.OraErro430Xe;
import com.gvt.cdr.bo.Erro430.vo.DuplicidadeVO;
import com.gvt.cdr.bo.Erro430.vo.InstanciaVO;

public class Erro430Duplicidades {

	private OraErro430Xe xe = new OraErro430Xe().getInstance();
	private OraErro430Arbor arbor = new OraErro430Arbor();
	private OraErro430Svcpgen svcpgen = new OraErro430Svcpgen().getInstance();
	
	public void executeDuplicidades430() {

		System.out.println("XXXXXXXXXX DUPLICIDADES XXXXXXXXXX");

		List<InstanciaVO> casos430 = svcpgen.retornaCasos430DaLoad();
		
		for (int i = 0 ; i < casos430.size(); i++){

			List<DuplicidadeVO> aux = arbor.retornaDuplicidades(casos430.get(i));
			
			System.out.println("grava no banco: " + casos430.get(i).getExternalId() + " | " + casos430.get(i).getServerId());
			System.out.println(aux.size());
			
			for (int j = 0 ; j < aux.size(); j++){
				xe.inserirLogDuplicidade(aux.get(j));
			}
			
		}

	}

}

