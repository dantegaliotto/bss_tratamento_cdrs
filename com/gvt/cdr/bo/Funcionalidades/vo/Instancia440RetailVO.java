package com.gvt.cdr.bo.Funcionalidades.vo;

import java.sql.Date;
import com.gvt.util.vo.VO;

public class Instancia440RetailVO extends VO {

	private String externalId;
	private String conta;
	private int tipoUso;
	private int serverId;
	private Date dtGuiador = null;
	private String irmaComGuiador;
	private String plano;
	private Date primeiroCdr = null;
	private Date ultimoCdr = null;
	private Date dtGuiadorInativo1 = null;
	private Date dtGuiadorInativo2 = null;
	private String ordemAtivacao = null;
	private String ordemInativacao = null;
	private String statusFavorito = null;
	private Date dataFavorito = null;
	private double valor = 0;

	public String getStatusFavorito() {
		return statusFavorito;
	}
	public void setStatusFavorito(String statusFavorito) {
		this.statusFavorito = statusFavorito;
	}
	public Date getDataFavorito() {
		return dataFavorito;
	}
	public void setDataFavorito(Date dataFavorito) {
		this.dataFavorito = dataFavorito;
	}
	public String getIrmaComGuiador() {
		return irmaComGuiador;
	}
	public void setIrmaComGuiador(String irmaComGuiador) {
		this.irmaComGuiador = irmaComGuiador;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getOrdemAtivacao() {
		return ordemAtivacao;
	}
	public void setOrdemAtivacao(String ordemAtivacao) {
		this.ordemAtivacao = ordemAtivacao;
	}
	public String getOrdemInativacao() {
		return ordemInativacao;
	}
	public void setOrdemInativacao(String ordemInativacao) {
		this.ordemInativacao = ordemInativacao;
	}
	public Date getDtGuiadorInativo1() {
		return dtGuiadorInativo1;
	}
	public void setDtGuiadorInativo1(Date dtGuiadorInativo1) {
		this.dtGuiadorInativo1 = dtGuiadorInativo1;
	}
	public Date getDtGuiadorInativo2() {
		return dtGuiadorInativo2;
	}
	public void setDtGuiadorInativo2(Date dtGuiadorInativo2) {
		this.dtGuiadorInativo2 = dtGuiadorInativo2;
	}
	public Date getPrimeiroCdr() {
		return primeiroCdr;
	}
	public void setPrimeiroCdr(Date primeiroCdr) {
		this.primeiroCdr = primeiroCdr;
	}
	public Date getUltimoCdr() {
		return ultimoCdr;
	}
	public void setUltimoCdr(Date ultimoCdr) {
		this.ultimoCdr = ultimoCdr;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public int getTipoUso() {
		return tipoUso;
	}
	public void setTipoUso(int tipoUso) {
		this.tipoUso = tipoUso;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public Date getDtGuiador() {
		return dtGuiador;
	}
	public void setDtGuiador(Date guiador) {
		this.dtGuiador = guiador;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	
	
}

