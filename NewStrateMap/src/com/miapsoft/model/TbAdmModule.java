package com.miapsoft.model;

/**
 * TbAdmModule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbAdmModule implements java.io.Serializable {

	// Fields

	private String moduleId;
	private String moduleName;
	private String parentModuleId;
	private String moduleDesc;
	private String moduleUrl;
	private String moduleLvaId;
	private String measureFlag;
	private String nodeId;
	private String parentNodeId;
	private String moduleType;
	private String subjectTypeId;
	private String urlTypeFlag;
	private String moduleFirstBit;
	private String lvlId;
	private String onLineTime;
	private String developer;
	private String dept;
	private String appClass;
	private String reqPerson;
	private String phoneNo;
	private String regionId;
	private String belngApp;
	private String belngArea;
	private String score;
	private String projectTime;
	private String offLine;
	private String wayName;

	// Constructors

	/** default constructor */
	public TbAdmModule() {
	}

	/** minimal constructor */
	public TbAdmModule(String moduleId) {
		this.moduleId = moduleId;
	}

	/** full constructor */
	public TbAdmModule(String moduleId, String moduleName,
			String parentModuleId, String moduleDesc, String moduleUrl,
			String moduleLvaId, String measureFlag, String nodeId,
			String parentNodeId, String moduleType, String subjectTypeId,
			String urlTypeFlag,String moduleFirstBit, String lvlId, String onLineTime, String developer,
			String dept, String appClass, String reqPerson, String phoneNo, String regionId, String belngApp,
			String belngArea, String score, String projectTime, String offLine, String wayName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.parentModuleId = parentModuleId;
		this.moduleDesc = moduleDesc;
		this.moduleUrl = moduleUrl;
		this.moduleLvaId = moduleLvaId;
		this.measureFlag = measureFlag;
		this.nodeId = nodeId;
		this.parentNodeId = parentNodeId;
		this.moduleType = moduleType;
		this.subjectTypeId = subjectTypeId;
		this.urlTypeFlag = urlTypeFlag;
		this.moduleFirstBit = moduleFirstBit;
		this.lvlId = lvlId;
		this.onLineTime = onLineTime;
		this.developer = developer;
		this.dept = dept;
		this.appClass = appClass;
		this.reqPerson = reqPerson;
		this.phoneNo = phoneNo;
		this.regionId = regionId;
		this.belngApp = belngApp;
		this.belngArea = belngArea;
		this.score = score;
		this.projectTime = projectTime;
		this.offLine = offLine;
		this.wayName = wayName;
		
	}

	// Property accessors

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getParentModuleId() {
		return this.parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleDesc() {
		return this.moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getModuleLvaId() {
		return this.moduleLvaId;
	}

	public void setModuleLvaId(String moduleLvaId) {
		this.moduleLvaId = moduleLvaId;
	}

	public String getMeasureFlag() {
		return this.measureFlag;
	}

	public void setMeasureFlag(String measureFlag) {
		this.measureFlag = measureFlag;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentNodeId() {
		return this.parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getSubjectTypeId() {
		return this.subjectTypeId;
	}

	public void setSubjectTypeId(String subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}

	public String getUrlTypeFlag() {
		return this.urlTypeFlag;
	}

	public void setUrlTypeFlag(String urlTypeFlag) {
		this.urlTypeFlag = urlTypeFlag;
	}

	public String getModuleFirstBit() {
		return moduleFirstBit;
	}

	public void setModuleFirstBit(String moduleFirstBit) {
		this.moduleFirstBit = moduleFirstBit;
	}

	public String getLvlId() {
		return lvlId;
	}

	public void setLvlId(String lvlId) {
		this.lvlId = lvlId;
	}

	public String getOnLineTime() {
		return onLineTime;
	}

	public void setOnLineTime(String onLineTime) {
		this.onLineTime = onLineTime;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAppClass() {
		return appClass;
	}

	public void setAppClass(String appClass) {
		this.appClass = appClass;
	}

	public String getReqPerson() {
		return reqPerson;
	}

	public void setReqPerson(String reqPerson) {
		this.reqPerson = reqPerson;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getBelngApp() {
		return belngApp;
	}

	public void setBelngApp(String belngApp) {
		this.belngApp = belngApp;
	}

	public String getBelngArea() {
		return belngArea;
	}

	public void setBelngArea(String belngArea) {
		this.belngArea = belngArea;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}

	public String getOffLine() {
		return offLine;
	}

	public void setOffLine(String offLine) {
		this.offLine = offLine;
	}

	public String getWayName() {
		return wayName;
	}

	public void setWayName(String wayName) {
		this.wayName = wayName;
	}

}