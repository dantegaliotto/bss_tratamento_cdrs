package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;
import java.util.List;
import com.gvt.util.vo.VO;

public class InstanciaSiebelVO extends VO {

	private int seq;
	private String instancia;
	private String designador;
	private String rpon;
	private int accountNo;
	private int subscrNo;
	private int serverId;
	private String status;
	private String acao;
	private String extIdAccountNo;
	private String ciclo;
	private Date dtUltimoFaturamento;
	private int jurisdiction;
	private Date primeiroCdr;
	private Date ultimoCdr;
	private int qtde;
	private int qtd440;
	private int qtd430;
	private int qtdeIncorrigiveis;
	private String primeiroCdrStr;
	private int tipoUso;
	private int msgId;
	private Date dtInstanciaKenan;
	private Date dtInstanciaSiebel;
	private String tipoPlano;
	private String tipoInstanciaSiebel;
	private Date dataPlanoSiebel;
	private Date dataCspSiebel;
	private Date diaCarga;
	private String rowIdElementoPlano;
	private String plano;
	private String cspSiebel;
	private String franquiaSiebel;
	private String slocal;
	private String svc;
	private String sldn;
	private String sldi;
	private int componentId;
	private int cspIdSiebel;
	private int cspIdKenan;
	private int franquiaIdSiebel;
	private int franquiaIdKenan;
	private int parComponentIdLocal;
	private int parComponentIdVc;
	private int parComponentIdLdn;
	private int parComponentIdLdi;
	private String SB78oportunidade;
	private String SB78cnpj;
	private String SB78nomeResponsavel;
	private String SB78produto;
	private String SB78status;
	private String SB78nomeRegional;
	private int flgBpo;	
	private int flgKenan;
	private int flgAutorot;
	private int flgNoBill;
	private String flgSiebel8;
	private String log;
	private int resultadoInicial;
	private int resultado = 6;
	private int lavoisier;
	private String franquiaConta;
	private int compIdFranquiaConta;
	private String CspConta;
	private int CompIdCspConta;
	private String annotation;
	private List<InstanciaArborVO> instanciasArbor = null;
	private List<RangeVO> ranges = null;
	private String logView = null;
	private Double valor = 0.0;
	private int accountNoB = 0;
	private int parentId = 0;
	private int hierarchyId = 0;

	
	public int getAccountNoB() {
		return accountNoB;
	}
	public void setAccountNoB(int accountNoB) {
		this.accountNoB = accountNoB;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getHierarchyId() {
		return hierarchyId;
	}
	public void setHierarchyId(int hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getLogView() {
		return logView;
	}
	public void setLogView(String logView) {
		this.logView = logView;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public int getFlgNoBill() {
		return flgNoBill;
	}
	public void setFlgNoBill(int flgNoBill) {
		this.flgNoBill = flgNoBill;
	}
	public String getFranquiaConta() {
		return franquiaConta;
	}
	public void setFranquiaConta(String franquiaConta) {
		this.franquiaConta = franquiaConta;
	}
	public int getCompIdFranquiaConta() {
		return compIdFranquiaConta;
	}
	public void setCompIdFranquiaConta(int compIdFranquiaConta) {
		this.compIdFranquiaConta = compIdFranquiaConta;
	}
	public String getCspConta() {
		return CspConta;
	}
	public void setCspConta(String cspConta) {
		CspConta = cspConta;
	}
	public int getCompIdCspConta() {
		return CompIdCspConta;
	}
	public void setCompIdCspConta(int compIdCspConta) {
		CompIdCspConta = compIdCspConta;
	}
	public String getRowIdElementoPlano() {
		return rowIdElementoPlano;
	}
	public void setRowIdElementoPlano(String rowIdElementoPlano) {
		this.rowIdElementoPlano = rowIdElementoPlano;
	}
/*
	public String getPlanoRowId() {
		return planoRowId;
	}
	public void setPlanoRowId(String planoRowId) {
		this.planoRowId = planoRowId;
	}
	public String getCspSiebelRowId() {
		return cspSiebelRowId;
	}
	public void setCspSiebelRowId(String cspSiebelRowId) {
		this.cspSiebelRowId = cspSiebelRowId;
	}
	public String getSlocalRowId() {
		return slocalRowId;
	}
	public void setSlocalRowId(String slocalRowId) {
		this.slocalRowId = slocalRowId;
	}
	public String getSvcRowId() {
		return svcRowId;
	}
	public void setSvcRowId(String svcRowId) {
		this.svcRowId = svcRowId;
	}
	public String getSldnRowId() {
		return sldnRowId;
	}
	public void setSldnRowId(String sldnRowId) {
		this.sldnRowId = sldnRowId;
	}
	public String getSldiRowId() {
		return sldiRowId;
	}
	public void setSldiRowId(String sldiRowId) {
		this.sldiRowId = sldiRowId;
	}
*/
	public String getCspSiebel() {
		return cspSiebel;
	}
	public void setCspSiebel(String cspSiebel) {
		this.cspSiebel = cspSiebel;
	}
	public int getCspIdSiebel() {
		return cspIdSiebel;
	}
	public void setCspIdSiebel(int cspIdSiebel) {
		this.cspIdSiebel = cspIdSiebel;
	}
	public int getCspIdKenan() {
		return cspIdKenan;
	}
	public void setCspIdKenan(int cspIdKenan) {
		this.cspIdKenan = cspIdKenan;
	}
	public int getSubscrNo() {
		return subscrNo;
	}
	public void setSubscrNo(int subscrNo) {
		this.subscrNo = subscrNo;
	}
	public int getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getPrimeiroCdrStr() {
		return primeiroCdrStr;
	}
	public void setPrimeiroCdrStr(String primeiroCdrStr) {
		this.primeiroCdrStr = primeiroCdrStr;
	}
	public int getTipoUso() {
		return tipoUso;
	}
	public void setTipoUso(int tipoUso) {
		this.tipoUso = tipoUso;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getSB78oportunidade() {
		return SB78oportunidade;
	}
	public void setSB78oportunidade(String sb78oportunidade) {
		SB78oportunidade = sb78oportunidade;
	}
	public String getSB78cnpj() {
		return SB78cnpj;
	}
	public void setSB78cnpj(String sb78cnpj) {
		SB78cnpj = sb78cnpj;
	}
	public String getSB78nomeResponsavel() {
		return SB78nomeResponsavel;
	}
	public void setSB78nomeResponsavel(String responsavel) {
		SB78nomeResponsavel = responsavel;
	}
	public int getFlgKenan() {
		return flgKenan;
	}
	public void setFlgKenan(int flgKenan) {
		this.flgKenan = flgKenan;
	}
	public int getFlgAutorot() {
		return flgAutorot;
	}
	public void setFlgAutorot(int flgAutorot) {
		this.flgAutorot = flgAutorot;
	}
	public String getSB78produto() {
		return SB78produto;
	}
	public void setSB78produto(String sb78produto) {
		SB78produto = sb78produto;
	}
	public String getSB78status() {
		return SB78status;
	}
	public void setSB78status(String sb78status) {
		SB78status = sb78status;
	}
	public String getSB78nomeRegional() {
		return SB78nomeRegional;
	}
	public void setSB78nomeRegional(String regional) {
		SB78nomeRegional = regional;
	}
	public int getFlgBpo() {
		return flgBpo;
	}
	public void setFlgBpo(int flgBpo) {
		this.flgBpo = flgBpo;
	}
	public int getResultado() {
		return this.resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public List<InstanciaArborVO> getInstanciasArbor() {
		return instanciasArbor;
	}
	public void setInstanciasArbor(List<InstanciaArborVO> instanciasArbor) {
		this.instanciasArbor = instanciasArbor;
	}
	public String getSlocal() {
		return slocal;
	}
	public void setSlocal(String slocal) {
		this.slocal = slocal;
	}
	public String getSvc() {
		return svc;
	}
	public void setSvc(String svc) {
		this.svc = svc;
	}
	public String getSldn() {
		return sldn;
	}
	public void setSldn(String sldn) {
		this.sldn = sldn;
	}
	public String getSldi() {
		return sldi;
	}
	public void setSldi(String sldi) {
		this.sldi = sldi;
	}
	public int getParComponentIdLocal() {
		return parComponentIdLocal;
	}
	public void setParComponentIdLocal(int parComponentIdLocal) {
		this.parComponentIdLocal = parComponentIdLocal;
	}
	public int getParComponentIdVc() {
		return parComponentIdVc;
	}
	public void setParComponentIdVc(int parComponentIdVc) {
		this.parComponentIdVc = parComponentIdVc;
	}
	public int getParComponentIdLdn() {
		return parComponentIdLdn;
	}
	public void setParComponentIdLdn(int parComponentIdLdn) {
		this.parComponentIdLdn = parComponentIdLdn;
	}
	public int getParComponentIdLdi() {
		return parComponentIdLdi;
	}
	public void setParComponentIdLdi(int parComponentIdLdi) {
		this.parComponentIdLdi = parComponentIdLdi;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getTipoPlano() {
		return tipoPlano;
	}
	public void setTipoPlano(String tipoPlano) {
		this.tipoPlano = tipoPlano;
	}
	public String getRpon() {
		return rpon;
	}
	public void setRpon(String rpon) {
		this.rpon = rpon;
	}
/*	public String getRowIdElementoPlano() {
		return rowIdElementoPlano;
	}
	public void setRowIdElementoPlano(String rowIdElementoPlano) {
		this.rowIdElementoPlano = rowIdElementoPlano;
	}*/
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public Date getDataPlanoSiebel() {
		return dataPlanoSiebel;
	}
	public void setDataPlanoSiebel(Date dataPlanoSiebel) {
		this.dataPlanoSiebel = dataPlanoSiebel;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getInstancia() {
		return instancia;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	public String getDesignador() {
		return designador;
	}
	public void setDesignador(String designador) {
		this.designador = designador;
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
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public void setRanges(List<RangeVO> ranges) {
		this.ranges = ranges;
	}
	public List<RangeVO> getRanges() {
		return ranges;
	}
	public void setResultadoInicial(int resultadoInicial) {
		this.resultadoInicial = resultadoInicial;
	}
	public int getResultadoInicial() {
		return resultadoInicial;
	}
	public void setDiaCarga(Date diaCarga) {
		this.diaCarga = diaCarga;
	}
	public Date getDiaCarga() {
		return diaCarga;
	}
	public void setExtIdAccountNo(String extIdAccountNo) {
		this.extIdAccountNo = extIdAccountNo;
	}
	public String getExtIdAccountNo() {
		return extIdAccountNo;
	}
	public void setQtdeIncorrigiveis(int qtdeIncorrigiveis) {
		this.qtdeIncorrigiveis = qtdeIncorrigiveis;
	}
	public int getQtdeIncorrigiveis() {
		return qtdeIncorrigiveis;
	}
	public void setTipoInstanciaSiebel(String tipoInstanciaSiebel) {
		this.tipoInstanciaSiebel = tipoInstanciaSiebel;
	}
	public String getTipoInstanciaSiebel() {
		return tipoInstanciaSiebel;
	}
	public void setFranquiaSiebel(String franquiaSiebel) {
		this.franquiaSiebel = franquiaSiebel;
	}
	public String getFranquiaSiebel() {
		return franquiaSiebel;
	}
	public void setFranquiaIdSiebel(int franquiaIdSiebel) {
		this.franquiaIdSiebel = franquiaIdSiebel;
	}
	public int getFranquiaIdSiebel() {
		return franquiaIdSiebel;
	}
	public void setFranquiaIdKenan(int franquiaIdKenan) {
		this.franquiaIdKenan = franquiaIdKenan;
	}
	public int getFranquiaIdKenan() {
		return franquiaIdKenan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public void setDtInstanciaKenan(Date dtInstanciaKenan) {
		this.dtInstanciaKenan = dtInstanciaKenan;
	}
	public Date getDtInstanciaKenan() {
		return dtInstanciaKenan;
	}
	public void setDtInstanciaSiebel(Date dtInstanciaSiebel) {
		this.dtInstanciaSiebel = dtInstanciaSiebel;
	}
	public Date getDtInstanciaSiebel() {
		return dtInstanciaSiebel;
	}
	public void setDtUltimoFaturamento(Date dtUltimoFaturamento) {
		this.dtUltimoFaturamento = dtUltimoFaturamento;
	}
	public Date getDtUltimoFaturamento() {
		return dtUltimoFaturamento;
	}
	public void setDataCspSiebel(Date dataCspSiebel) {
		this.dataCspSiebel = dataCspSiebel;
	}
	public Date getDataCspSiebel() {
		return dataCspSiebel;
	}
	public void setQtd440(int qtd440) {
		this.qtd440 = qtd440;
	}
	public int getQtd440() {
		return qtd440;
	}
	public void setQtd430(int qtd430) {
		this.qtd430 = qtd430;
	}
	public int getQtd430() {
		return qtd430;
	}
	public void setLavoisier(int lavoisier) {
		this.lavoisier = lavoisier;
	}
	public int getLavoisier() {
		return lavoisier;
	}
	public void setFlgSiebel8(String flgSiebel8) {
		this.flgSiebel8 = flgSiebel8;
	}
	public String getFlgSiebel8() {
		return flgSiebel8;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	
}
