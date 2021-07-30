package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;
import java.util.List;

import com.gvt.cdr.bo.Erro440.vo.PlanoArborVO;
import com.gvt.util.vo.VO;

public class InstanciaArborVO extends VO {

	private String externalId;
	private Date dtInstancia;
	private int subscrNo;
	private int qtdeCorrigiveis;
	private int qtdeIncorrigiveis;
	private int resultado;
	private String log;
	private List<PlanoArborVO> planosArbor;
	private List<ProdutoArborVO> produtosArbor;
	
	
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public Date getDtInstancia() {
		return dtInstancia;
	}
	public void setDtInstancia(Date dtInstancia) {
		this.dtInstancia = dtInstancia;
	}
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public List<PlanoArborVO> getPlanosArbor() {
		return planosArbor;
	}
	public void setPlanosArbor(List<PlanoArborVO> planosArbor) {
		if (this.planosArbor == null)
			this.planosArbor = planosArbor;
		else
			this.planosArbor.addAll(planosArbor);
	}
	public List<ProdutoArborVO> getProdutosArbor() {
		return produtosArbor;
	}
	public void setProdutosArbor(List<ProdutoArborVO> produtosArbor) {
		this.produtosArbor = produtosArbor;
	}
	public void setQtdeCorrigiveis(int qtdeCorrigiveis) {
		this.qtdeCorrigiveis = qtdeCorrigiveis;
	}
	public int getQtdeCorrigiveis() {
		return qtdeCorrigiveis;
	}
	public void setQtdeIncorrigiveis(int qtdeIncorrigiveis) {
		this.qtdeIncorrigiveis = qtdeIncorrigiveis;
	}
	public int getQtdeIncorrigiveis() {
		return qtdeIncorrigiveis;
	} 

}
