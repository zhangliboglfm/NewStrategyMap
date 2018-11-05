package com.miapsoft.manager;

public interface PhotogManager {
	String selphotogList(String importDate,String pgName,String start, String end);
	String selphotogListAudit(String auditStatus,String pgName,String start, String end);
	String selphotogPic(String pgid,String picType,String showFlag);
}
