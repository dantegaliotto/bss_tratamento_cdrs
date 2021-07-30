package com.gvt.cdr.bo.Erro152.vo;

import java.sql.Date;
import com.gvt.util.vo.VO;

public class InstanciaSiebelVO extends VO {

	private String  externalId    ;
	private String  designador    ;
	private int     qtde          ;
	private Double  valor = 0.0   ;
	private Date    primeiroCdr   ;
	private Date    ultimoCdr     ;
	private String  contaCli      ;
	private String  contaAgg      ;
	private String  contaCob      ;
	private String  rowidCli      ;
	private String  rowidAgg      ;
	private String  rowidCob      ;
	private String  rowidIns      ;
	private String  porteCli      ;
	private String  annotation    ;
	private String  verCli        ;
	private String  verAgg        ;
	private String  verCob        ;
	private String  verInstancia  ;
	private int     resultado = 6 ;
	private String  log           ;

	public String getResultadoGeral(){
		return verCli + " " + verAgg + " " + verCob + " " + verInstancia;
	}
	
	public String getVerInstancia() {
		return verInstancia;
	}
	public void setVerInstancia(String verInstancia) {
		this.verInstancia = verInstancia;
	}
	public String getPorteCli() {
		return porteCli;
	}
	public void setPorteCli(String porteCli) {
		this.porteCli = porteCli;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getDesignador() {
		return designador;
	}
	public void setDesignador(String designador) {
		this.designador = designador;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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
	public String getContaCli() {
		return contaCli;
	}
	public void setContaCli(String contaCli) {
		this.contaCli = contaCli;
	}
	public String getContaAgg() {
		return contaAgg;
	}
	public void setContaAgg(String contaAgg) {
		this.contaAgg = contaAgg;
	}
	public String getContaCob() {
		return contaCob;
	}
	public void setContaCob(String contaCob) {
		this.contaCob = contaCob;
	}
	public String getRowidCli() {
		return rowidCli;
	}
	public void setRowidCli(String rowidCli) {
		this.rowidCli = rowidCli;
	}
	public String getRowidAgg() {
		return rowidAgg;
	}
	public void setRowidAgg(String rowidAgg) {
		this.rowidAgg = rowidAgg;
	}
	public String getRowidCob() {
		return rowidCob;
	}
	public void setRowidCob(String rowidCob) {
		this.rowidCob = rowidCob;
	}
	public String getRowidIns() {
		return rowidIns;
	}
	public void setRowidIns(String rowidIns) {
		this.rowidIns = rowidIns;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getVerCli() {
		return verCli;
	}
	public void setVerCli(String verCli) {
		this.verCli = verCli;
	}
	public String getVerAgg() {
		return verAgg;
	}
	public void setVerAgg(String verAgg) {
		this.verAgg = verAgg;
	}
	public String getVerCob() {
		return verCob;
	}
	public void setVerCob(String verCob) {
		this.verCob = verCob;
	}
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
	
}
