package com.gvt.cdr.bo.Erro152;

import java.util.List;

import com.gvt.cdr.bo.Erro152.dao.OraErro152Kenan;
import com.gvt.cdr.bo.Erro152.dao.OraErro152Siebel;
import com.gvt.cdr.bo.Erro152.dao.OraErro152Dgrc;
import com.gvt.cdr.bo.Erro152.vo.InstanciaSiebelVO;
import com.gvt.util.Log;

public class Erro152CorpLoad {

	private OraErro152Dgrc   dgrc   = new OraErro152Dgrc  ().getInstance();
	private OraErro152Siebel siebel = new OraErro152Siebel().getInstance();
	//private OraErro152Kenan  kenan  = new OraErro152Kenan ().getInstance();
	
	public void executeLoad(String annotation){
		
		Log.info(">>> Buscando casos 152 CORP na CDRS_WORK", this);

		//int seq = 1157 ; //xe.carregaSequencial();
		System.out.println(">>>>>>>>> INÍCIO <<<<<<<<<<");

		this.cargaLoad    (annotation);

		System.out.println(">>>>>>>>> FIM <<<<<<<<<<");
		
	}


	private void cargaLoad(String annotation) {

		List<InstanciaSiebelVO> casos152 = null;
		
		casos152 = dgrc.retornaCasos152Corp(annotation);

		Log.info("> Total de casos sem agrupamento: " + casos152.size(), this);

		for (int i = 0 ; i < casos152.size(); i++){

			System.out.println(i + ": " + casos152.get(i).getExternalId() + " | " + casos152.get(i).getDesignador());
			siebel.buscaInformacoesContas(casos152.get(i));
			
			if (casos152.get(i).getContaCli() == null){
				casos152.get(i).setResultado(-2);
				casos152.get(i).setLog("Não encontrado no Siebel 5");
			}
			dgrc.inserirLogLoad(casos152.get(i));			
		}
	}

}
