package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class ProdutoArborVO extends VO {

	private String instancia;
	private String externalId;
	private int componentId;
	private int elementId;
	private int subscrNo;
	private Date dtProduto;
	private int serverId;
	private int resultado;
	
	public String getInstancia() {
		return instancia;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public void setDtProduto(Date dtProduto) {
		this.dtProduto = dtProduto;
	}
	public Date getDtProduto() {
		return dtProduto;
	}
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	public int getElementId() {
		return elementId;
	}
	
	
}
