package com.miapsoft.manager;

import net.sf.json.JSONArray;

public interface AggregationManager {
	public JSONArray getaggregationInfo(String cgid);
	public JSONArray getauditaggregationInfo(String cgid);
	public JSONArray searchaggregationInfo(String cgid,String name);
	public JSONArray searchauditaggregationInfo(String cgid,String name);
	public JSONArray geteditaggregationInfo(String agid);
	public boolean editaggregationInfo(String compid,String name,String othername,String allname,String describe);
	public String addaggregation(String agid,String name,String othername,String allname,String describe);
	public String getworkId(String name);
	public boolean updateworkId(String workid,String compid);
	public JSONArray getaggregationagdata(String compid,String cgid);
	public JSONArray searchaggregationagdata(String compid,String cgid,String cgname);
	public JSONArray getnewaggregationagdata(String cgid);
	public JSONArray searchnewaggregationagdata(String cgid,String cgname);
	public String addaggregationmemberdata(String compid,String worksid,String worksname);
	public boolean deleteaggregationInfo(String compid);
	public JSONArray getaggregationImageInfo(String compid);
	public JSONArray getInsertImageInfo(String worksid);
	public String newaggregationImageInfo(String compid,String worksid);
	public String editaggregationImageInfo(String compid,String worksid);
	public boolean clearCOMP_workrelation(String compid);
	boolean deleteaggregationmember(String compid,String worksid);
}
