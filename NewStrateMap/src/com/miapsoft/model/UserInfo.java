package com.miapsoft.model;

import java.util.List;


public class UserInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = -2365924456109070617L;
	//用户信息 
	private TbAdmUserUnit user;
	//角色信息
	private TbAdmRole role;
	//用户组信息
	private TbAdmUserGroup group;
	//用户公司
	private TbAdmUserGroup company;
	//用户职位
	private TbDimJob job;
	//用户工作地域
	private TbDimRegion region;
	//特殊权限
	private List special_role;
	//下属用户
	private List under;
	//上级用户
	private TbAdmUserUnit boss; 
	public TbAdmUserUnit getUser() {
		return user;
	}
	public void setUser(TbAdmUserUnit user) {
		this.user = user;
	}
	public TbAdmRole getRole() {
		return role;
	}
	public void setRole(TbAdmRole role) {
		this.role = role;
	}
	public TbAdmUserGroup getGroup() {
		return group;
	}
	public void setGroup(TbAdmUserGroup group) {
		this.group = group;
	}
	public TbAdmUserGroup getCompany() {
		return company;
	}
	public void setCompany(TbAdmUserGroup company) {
		this.company = company;
	}
	public TbDimJob getJob() {
		return job;
	}
	public void setJob(TbDimJob job) {
		this.job = job;
	}
	public TbDimRegion getRegion() {
		return region;
	}
	public void setRegion(TbDimRegion region) {
		this.region = region;
	}
	public List getSpecial_role() {
		return special_role;
	}
	public void setSpecial_role(List specialRole) {
		special_role = specialRole;
	}
	public List getUnder() {
		return under;
	}
	public void setUnder(List under) {
		this.under = under;
	}
	public TbAdmUserUnit getBoss() {
		return boss;
	}
	public void setBoss(TbAdmUserUnit boss) {
		this.boss = boss;
	}
}
