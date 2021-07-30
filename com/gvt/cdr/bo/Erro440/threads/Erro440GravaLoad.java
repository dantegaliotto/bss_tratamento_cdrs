package com.gvt.cdr.bo.Erro440.threads;

import com.gvt.cdr.bo.Erro440.dao.OraErro440Xe;
import com.gvt.cdr.bo.Erro440.vo.InstanciaSiebelVO;
import com.gvt.util.threads.ActionAbstract;

public class Erro440GravaLoad extends ActionAbstract<Object> {

	private OraErro440Xe xe = new OraErro440Xe().getInstance();
	
	public void exec(Object arg0) {
		
		InstanciaSiebelVO instanciaSiebel = (InstanciaSiebelVO) arg0;
		
/*
 		// Marca o cliente que está em NoBill
		if (instanciaSiebel.getFlgNoBill() == 1){
			instanciaSiebel.setResultado(-2);
			instanciaSiebel.setLog("Cliente está em NoBill");
		}
*/		
		xe.inserirLogLoad(instanciaSiebel);
		
	}
}
