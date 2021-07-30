package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class AutorotProvchargesVO extends VO {

	public static int CONTA = 1;
	public static int TNET = 0;
	public static int INSTANCIA = 0;
	
	private int serverId;
	private String rowIdElemento;
	private String externalId;
	private int externalIdType;
	private int componentId;
	private Date dtAtivar;
	private int flag;
	private int subscrNo;
	private String instancia;
	
	public String getInstancia() {
		return instancia;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getRowIdElemento() {
		return rowIdElemento;
	}
	public void setRowIdElemento(String rowIdElemento) {
		this.rowIdElemento = rowIdElemento;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public int getExternalIdType() {
		return externalIdType;
	}
	public void setExternalIdType(int externalIdType) {
		this.externalIdType = externalIdType;
		
		switch(externalIdType){
		case 6:
			this.flag = INSTANCIA;
			break;
		case 7:
			this.flag = TNET;
			break;
		case 1:
			this.flag = CONTA;
			break;
		}
		
		
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public Date getDtAtivar() {
		return dtAtivar;
	}
	public void setDtAtivar(Date dtAtivar) {
		this.dtAtivar = dtAtivar;
	}
	public int getFlag() {
		return flag;
	}
}
