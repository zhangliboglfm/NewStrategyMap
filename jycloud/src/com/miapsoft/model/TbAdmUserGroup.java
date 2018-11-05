package com.miapsoft.model;

public class TbAdmUserGroup implements java.io.Serializable{

	private static final long serialVersionUID = -5738788252950183661L;
	
	// Fields

	private String userGroupId;
	private String userGroupName;
	private String userGroupDesc;
	private String parentUserGroupId;
	private String topFlag;
	private String regionId;

	// Constructors

	/** default constructor */
	public TbAdmUserGroup() {
	}

	/** minimal constructor */
	public TbAdmUserGroup(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	/** full constructor */
	public TbAdmUserGroup(String userGroupId, String userGroupName,
			String userGroupDesc, String parentUserGroupId, String topFlag,
			String regionId) {
		this.userGroupId = userGroupId;
		this.userGroupName = userGroupName;
		this.userGroupDesc = userGroupDesc;
		this.parentUserGroupId = parentUserGroupId;
		this.topFlag = topFlag;
		this.regionId = regionId;
	}

	// Property accessors

	public String getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return this.userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserGroupDesc() {
		return this.userGroupDesc;
	}

	public void setUserGroupDesc(String userGroupDesc) {
		this.userGroupDesc = userGroupDesc;
	}

	public String getParentUserGroupId() {
		return this.parentUserGroupId;
	}

	public void setParentUserGroupId(String parentUserGroupId) {
		this.parentUserGroupId = parentUserGroupId;
	}

	public String getTopFlag() {
		return this.topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

}
