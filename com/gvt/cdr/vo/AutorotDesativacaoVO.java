package com.gvt.cdr.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class AutorotDesativacaoVO extends VO {

	private String instancia;
	private String externalId;
	private int externalIdType;
	private int subscrNo;
	private int packageId;
	private int componentInstanceId;
	private Date dtDesativar;
	private int componentId;
	private String arborComponentDescription;
	private int serverId;
	private Date dtEnvio;
	private String resultado;
	private String motivo;
	private String nivelKenan;
	
	
	
	public int getExternalIdType() {
		return externalIdType;
	}
	public void setExternalIdType(int externalIdType) {
		this.externalIdType = externalIdType;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getComponentInstanceId() {
		return componentInstanceId;
	}
	public void setComponentInstanceId(int componentInstanceId) {
		this.componentInstanceId = componentInstanceId;
	}
	public Date getDtEnvio() {
		return dtEnvio;
	}
	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
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
	public Date getDtDesativar() {
		return dtDesativar;
	}
	public void setDtDesativar(Date dtDesativar) {
		this.dtDesativar = dtDesativar;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	public String getInstancia() {
		return instancia;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setArborComponentDescription(String arborComponentDescription) {
		this.arborComponentDescription = arborComponentDescription;
	}
	public String getArborComponentDescription() {
		return arborComponentDescription;
	}
	public void setNivelKenan(String nivelKenan) {
		this.nivelKenan = nivelKenan;
	}
	public String getNivelKenan() {
		return nivelKenan;
	}

		
}
