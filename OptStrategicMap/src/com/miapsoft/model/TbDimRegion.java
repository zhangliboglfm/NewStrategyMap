package com.miapsoft.model;

public class TbDimRegion implements java.io.Serializable {

	private static final long serialVersionUID = 1456470786149169631L;

	private String regionId;
    
	private String regionParentId;
    
	private String lvlId;
    
	private String regionName;
    
	private String regionDesc;
	
	private String regionOrder;

	// Constructors

	/** default constructor */
	public TbDimRegion() {
	}

	/** minimal constructor */
	public TbDimRegion(String regionId) {
		this.regionId = regionId;
	}

	/** full constructor */
	public TbDimRegion(String regionId, String regionParentId, String lvlId,
			String regionName, String regionDesc,String regionOrder) {
		this.regionId = regionId;
		this.regionParentId = regionParentId;
		this.lvlId = lvlId;
		this.regionName = regionName;
		this.regionDesc = regionDesc;
		this.regionOrder = regionOrder;
	}

	// Property accessors

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionParentId() {
		return this.regionParentId;
	}

	public void setRegionParentId(String regionParentId) {
		this.regionParentId = regionParentId;
	}

	public String getLvlId() {
		return this.lvlId;
	}

	public void setLvlId(String lvlId) {
		this.lvlId = lvlId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionDesc() {
		return this.regionDesc;
	}

	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}

	public String getRegionOrder() {
		return regionOrder;
	}

	public void setRegionOrder(String regionOrder) {
		this.regionOrder = regionOrder;
	}

}