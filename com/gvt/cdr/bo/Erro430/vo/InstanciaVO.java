package com.gvt.cdr.bo.Erro430.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class InstanciaVO extends VO{
	
	private String externalId;
	private int serverId;
	private Date dtPrimeiroCdr;
	private Date dtUltimoCdr;
	private int qtde;
	private int accountNo;
	private int subscrNo;
	private String ciclo;
	private int lavoisier;

	
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public Date getDtPrimeiroCdr() {
		return dtPrimeiroCdr;
	}
	public void setDtPrimeiroCdr(Date dtPrimeiroCdr) {
		this.dtPrimeiroCdr = dtPrimeiroCdr;
	}
	public Date getDtUltimoCdr() {
		return dtUltimoCdr;
	}
	public void setDtUltimoCdr(Date dtUltimoCdr) {
		this.dtUltimoCdr = dtUltimoCdr;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtdeCdrs) {
		this.qtde = qtdeCdrs;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setLavoisier(int lavoisier) {
		this.lavoisier = lavoisier;
	}
	public int getLavoisier() {
		return lavoisier;
	}
	
}
