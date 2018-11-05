package com.miapsoft.model;

public class TbLoginNumberZc{
	
	/**
	 * 登录信息模型
	 */
	private Integer id;
	
	private String userId;
	
	private String loginIp;
	
	private String ticket;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public TbLoginNumberZc(Integer id, String userId, String loginIp,
			String ticket) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginIp = loginIp;
		this.ticket = ticket;
	}
	public TbLoginNumberZc() {
		super();
	}
}
