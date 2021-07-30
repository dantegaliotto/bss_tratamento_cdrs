package com.gvt.cdr.bo.Funcionalidades.vo;

import com.gvt.util.vo.VO;

public class CdrVO extends VO {

	private String externalId = null;
	private String externalTrackingId = null;
	private int tipoUsoCorreto = 0;
	
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getExternalTrackingId() {
		return externalTrackingId;
	}
	public void setExternalTrackingId(String externalTrackingId) {
		this.externalTrackingId = externalTrackingId;
	}
	public int getTipoUsoCorreto() {
		return tipoUsoCorreto;
	}
	public void setTipoUsoCorreto(int tipoUsoCorreto) {
		this.tipoUsoCorreto = tipoUsoCorreto;
	}

	
}

