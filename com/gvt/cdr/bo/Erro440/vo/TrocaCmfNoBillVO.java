package com.gvt.cdr.bo.Erro440.vo;

import java.sql.Date;
import com.gvt.util.vo.VO;

public class TrocaCmfNoBillVO extends VO {

	private int accountNo;
	private Date quando;
	private int oldNoBill;
	private int newNoBill;
	private int serverId;
	private String remark;
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getQuando() {
		return quando;
	}

	public void setQuando(Date quando) {
		this.quando = quando;
	}

	public int getOldNoBill() {
		return oldNoBill;
	}

	public void setOldNoBill(int oldNoBill) {
		this.oldNoBill = oldNoBill;
	}

	public int getNewNoBill() {
		return newNoBill;
	}

	public void setNewNoBill(int newNoBill) {
		this.newNoBill = newNoBill;
	}

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

	
	
}
