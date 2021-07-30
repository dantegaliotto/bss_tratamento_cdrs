package com.gvt.cdr.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class AutorotAtivacaoVO extends VO {

	private String seq = " ";
	private String instancia;
	private String elementId;
	private String externalId;
	private int externalIdType;
	private int subscrNo;
	private int packageId;
	private int componentId;
	private Date dtAtivacao;
	private int serverId;
	private Date dtEnvio;
	private String resultado;
	private int flag;
	private String motivo;
	private String siebelComponentDescription;
	private String nivelKenan;
	private int modLavoisier;
	private int jurisdiction;
	private int units;
		
	public int getModLavoisier() {
		return modLavoisier;
	}
	public void setModLavoisier(int modLavoisier) {
		this.modLavoisier = modLavoisier;
	}
	public int getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Date getDtEnvio() {
		return dtEnvio;
	}
	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
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
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public Date getDtAtivacao() {
		return dtAtivacao;
	}
	public void setDtAtivacao(Date dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public int getSubscrNo() {
		return subscrNo;
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
	public void setSiebelComponentDescription(String siebelComponentDescription) {
		this.siebelComponentDescription = siebelComponentDescription;
	}
	public String getSiebelComponentDescription() {
		return siebelComponentDescription;
	}
	public void setNivelKenan(String nivelKenan) {
		this.nivelKenan = nivelKenan;
	}
	public String getNivelKenan() {
		return nivelKenan;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeq() {
		return seq;
	}

}
