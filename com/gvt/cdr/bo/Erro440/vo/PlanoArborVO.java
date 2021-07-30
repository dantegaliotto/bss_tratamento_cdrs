package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;

import com.gvt.util.vo.VO;

public class PlanoArborVO extends VO {

	private int seq;
	private String instancia;
	private String externalId;
	private int componentId;
	private int componentInstId;
	private int PackageId;
	private int subscrNo;
	private Date dtPlano;
	private int serverId;
	private String arborComponentDescription;
	private String tipoPlano;
	private int validacaoElementos = 0;
	private String nivelKenan;
	
	
	
	public String getNivelKenan() {
		return nivelKenan;
	}
	public void setNivelKenan(String nivelKenan) {
		this.nivelKenan = nivelKenan;
	}
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
	public Date getDtPlano() {
		return dtPlano;
	}
	public void setDtPlano(Date dtPlano) {
		this.dtPlano = dtPlano;
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
	public void setComponentInstId(int componentInstId) {
		this.componentInstId = componentInstId;
	}
	public int getComponentInstId() {
		return componentInstId;
	}
	public void setPackageId(int packageId) {
		PackageId = packageId;
	}
	public int getPackageId() {
		return PackageId;
	}
	public void setArborComponentDescription(String arborComponentDescription) {
		this.arborComponentDescription = arborComponentDescription;
	}
	public String getArborComponentDescription() {
		return arborComponentDescription;
	}
	public void setTipoPlano(String tipoPlano) {
		this.tipoPlano = tipoPlano;
	}
	public String getTipoPlano() {
		return tipoPlano;
	}
	public void setValidacaoElementos(int validacaoElementos) {
		this.validacaoElementos = validacaoElementos;
	}
	public int getValidacaoElementos() {
		return validacaoElementos;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getSeq() {
		return seq;
	}
	
	
}
