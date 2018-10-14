package com.miapsoft.model;

public class TbAdmRole implements java.io.Serializable{
	// Fields

	private static final long serialVersionUID = -7228673657974544819L;
	private String roleId;
	private String roleName;
	private String belongRegionId;
	private String roleDesc;

	// Constructors

	/** default constructor */
	public TbAdmRole() {
	}

	/** minimal constructor */
	public TbAdmRole(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	/** full constructor */
	public TbAdmRole(String roleId, String roleName, String belongRegionId,
			String roleDesc) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.belongRegionId = belongRegionId;
		this.roleDesc = roleDesc;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBelongRegionId() {
		return this.belongRegionId;
	}

	public void setBelongRegionId(String belongRegionId) {
		this.belongRegionId = belongRegionId;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
