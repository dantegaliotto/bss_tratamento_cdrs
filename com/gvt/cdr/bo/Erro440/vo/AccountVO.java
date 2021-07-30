package com.gvt.cdr.bo.Erro440.vo;

import java.util.List;
import com.gvt.util.vo.VO;

public class AccountVO extends VO {

	private int accountNo;
	private int serverId;
	private List<TrocaCmfNoBillVO> trocasCmf = null;

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public void setTrocasCmf(List<TrocaCmfNoBillVO> trocasCmf) {
		this.trocasCmf = trocasCmf;
	}

	public List<TrocaCmfNoBillVO> getTrocasCmf() {
		return trocasCmf;
	}


	
	
}
