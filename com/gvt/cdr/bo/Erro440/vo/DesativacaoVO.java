package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;

public class DesativacaoVO {

	private String externalId;
	private String componentInst;
	private int componentId;
	private Date created;
	private Date dtActive;
	private Date primeiroCdr;
	private Date dtDesativar;

	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getComponentInst() {
		return componentInst;
	}
	public void setComponentInst(String componentInst) {
		this.componentInst = componentInst;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getDtActive() {
		return dtActive;
	}
	public void setDtActive(Date dtActive) {
		this.dtActive = dtActive;
	}
	public Date getPrimeiroCdr() {
		return primeiroCdr;
	}
	public void setPrimeiroCdr(Date primeiroCdr) {
		this.primeiroCdr = primeiroCdr;
	}
	public Date getDtDesativar() {
		return dtDesativar;
	}
	public void setDtDesativar(Date dtDesativar) {
		this.dtDesativar = dtDesativar;
	}
	
}
