package com.gvt.cdr.bo.Erro440.threads;

import java.util.List;
import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaArborVO;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440GravaLog extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instanciaSiebel = (InstanciaSiebelVO) arg0;
		
		//salvando o resultado inicial
		instanciaSiebel.setResultadoInicial(instanciaSiebel.getResultado());
		
		xe.inserirLogSiebel(instanciaSiebel);
		
		List<InstanciaArborVO> instanciasArbor = instanciaSiebel.getInstanciasArbor();
		
		InstanciaArborVO instanciaArbor = null;
		
		if(instanciasArbor!=null){
			
			for(int x=0; x < instanciasArbor.size(); x++){
				
				instanciaArbor = (InstanciaArborVO) instanciasArbor.get(x); 
				
				xe.inserirLogArbor(instanciaSiebel.getInstancia(), instanciaArbor, instanciaSiebel.getSeq());

				List<PlanoArborVO> planosArbor = instanciaArbor.getPlanosArbor();
				
				PlanoArborVO planoArbor = null;
				
				if(planosArbor!=null){
					
					for(int y=0; y<planosArbor.size(); y++){
						
						planoArbor = (PlanoArborVO) planosArbor.get(y); 
						
						xe.inserirLogPlanoArbor(instanciaSiebel.getInstancia(), 
								                planoArbor, instanciaSiebel.getServerId(),
								                instanciaSiebel.getSeq());
						
					}
				}
				/*
				List<ProdutoArborVO> produtosArbor = instanciaArbor.getProdutosArbor();
				
				ProdutoArborVO produtoArbor = null;
				
				if(produtosArbor!=null){
					
					for(int y=0; y<produtosArbor.size(); y++){
						
						produtoArbor = (ProdutoArborVO) produtosArbor.get(y); 
						
						xe.inserirLogProdutoArbor(instanciaSiebel.getInstancia(),instanciaArbor.getExternalId(), 
								                  produtoArbor, instanciaSiebel.getServerId());
						
					}
				}*/
			}
		}
	}
}
