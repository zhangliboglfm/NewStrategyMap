package com.miapsoft.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.miapsoft.manager.AbstractManager;
import com.miapsoft.manager.FilmfilterManager;

@Service
public class FilmfilterManagerImpl extends AbstractManager implements FilmfilterManager{
	public JSONArray getNums(String SAMP_PIC_NAME,String FILM_CAMERA_ID,String DEAL_DATE,
			String IS_BLACK,String SAMP_PIC_LENGTH,String LABEL_NAME,
			String LENS_ID,String FILM_PROD_ID,String size,String state1,String state2){
		JSONArray value=new JSONArray();
		String sql="select count(*) as NUM from TB_SP_FILM_SAMP_PIC_INFO4 a left join TB_ADM_SAMP_PIC_STATUS d on a.SAMP_PIC_ID=d.SAMP_PIC_ID ";
		if(LABEL_NAME!=""&&LABEL_NAME!=null){
			value.add(state1);
			value.add(state2);
			sql+=" left join TB_ADM_FILM_SAMP_PIC_LABEL b on a.SAMP_PIC_ID=b.SAMP_PIC_ID" +
				" left join TB_CDE_SAMP_PIC_LABEL c on b.LABEL_ID=c.LABEL_ID where ( d.STATUS_ID=? or d.STATUS_ID=? )  ";
			String [] LABEL_NAMEs=LABEL_NAME.split(",");
			if(LABEL_NAMEs.length>1){
				sql+=" and (c.LABEL_NAME=?";
				for(int i=1;i<LABEL_NAMEs.length;i++){
					if(i==LABEL_NAMEs.length-1){
						value.add(LABEL_NAMEs[i]);
						sql+=" or c.LABEL_NAME=?)";
					}else{
						value.add(LABEL_NAMEs[i]);
						sql+=" or c.LABEL_NAME=?";
					}
				}
			}else{
				value.add(LABEL_NAMEs[0]);
				sql+=" and c.LABEL_NAME=?";
			}
		}else{
			value.add(state1);
			value.add(state2);
			sql+=" where ( d.STATUS_ID=? or d.STATUS_ID=? ) ";
		}
		if(SAMP_PIC_NAME!=""&&SAMP_PIC_NAME!=null){
			value.add(SAMP_PIC_NAME);
			sql+=" and a.SAMP_PIC_NAME=?";
		}
		if(FILM_CAMERA_ID!=""&&FILM_CAMERA_ID!=null&&!"0".equals(FILM_CAMERA_ID)){
			String [] FILM_CAMERA_IDs=FILM_CAMERA_ID.split(",");
			if(FILM_CAMERA_IDs.length>1){
				value.add(FILM_CAMERA_IDs[0]);
				sql+=" and (a.FILM_CAMERA_ID=?";
				for(int i=1;i<FILM_CAMERA_IDs.length;i++){
					if(i==FILM_CAMERA_IDs.length-1){
						value.add(FILM_CAMERA_IDs[i]);
						sql+=" or a.FILM_CAMERA_ID=?)";
					}else{
						value.add(FILM_CAMERA_IDs[i]);
						sql+=" or a.FILM_CAMERA_ID=?";
					}
				}
			}else{
				value.add(FILM_CAMERA_IDs[0]);
				sql+=" and a.FILM_CAMERA_ID=?";
			}
		}
		if(DEAL_DATE!=""&&DEAL_DATE!=null){
			value.add(DEAL_DATE);
			sql+=" and a.DEAL_DATE=?";
		}
		if(!"2".equals(IS_BLACK)&&!"".equals(IS_BLACK)){
			value.add(IS_BLACK);
			sql+=" and a.IS_BLACK=?";
		}
		if(LENS_ID!=""&&LENS_ID!=null&&!"0".equals(LENS_ID)){
			String [] LENS_IDs=LENS_ID.split(",");
			if(LENS_IDs.length>1){
				value.add(LENS_IDs[0]);
				sql+=" and (a.LENS_ID=?";
				for(int i=1;i<LENS_IDs.length;i++){
					if(i==LENS_IDs.length-1){
						value.add(LENS_IDs[i]);
						sql+=" or a.LENS_ID=?)";
					}else{
						value.add(LENS_IDs[i]);
						sql+=" or a.LENS_ID=?";
					}
				}
			}else{
				value.add(LENS_IDs[0]);
				sql+=" and a.LENS_ID=?";
			}
		}
		if(FILM_PROD_ID!=""&&FILM_PROD_ID!=null&&!"0".equals(FILM_PROD_ID)){
			String [] FILM_PROD_IDs=FILM_PROD_ID.split(",");
			if(FILM_PROD_IDs.length>1){
				value.add(FILM_PROD_IDs[0]);
				sql+=" and (a.FILM_PROD_ID=?";
				for(int i=1;i<FILM_PROD_IDs.length;i++){
					if(i==FILM_PROD_IDs.length-1){
						value.add(FILM_PROD_IDs[i]);
						sql+=" or a.FILM_PROD_ID=?)";
					}else{
						value.add(FILM_PROD_IDs[i]);
						sql+=" or a.FILM_PROD_ID=?";
					}
				}
			}else{
				value.add(FILM_PROD_IDs[0]);
				sql+=" and a.FILM_PROD_ID=?";
			}
		}
		if(!"0".equals(size)){
			if("1".equals(size)){
				value.add(SAMP_PIC_LENGTH);
				sql+=" and (case when a.SAMP_PIC_LENGTH>=a.SAMP_PIC_WIDTH then a.SAMP_PIC_WIDTH else a.SAMP_PIC_LENGTH end)<=?";
			}
			if("2".equals(size)){
				value.add(SAMP_PIC_LENGTH);
				sql+=" and (case when a.SAMP_PIC_LENGTH>=a.SAMP_PIC_WIDTH then a.SAMP_PIC_WIDTH else a.SAMP_PIC_LENGTH end)>=?";
			}
		}
		JSONArray result = JSONArray.fromObject(this.getJdbcTemplate().queryForList(sql,value.toArray()));
		return result;
	}
	public JSONArray getPic(String SAMP_PIC_NAME,String FILM_CAMERA_ID,String DEAL_DATE,
			String IS_BLACK,String SAMP_PIC_LENGTH,String LABEL_NAME,
			String LENS_ID,String FILM_PROD_ID,String pageNum,String pageSize,String size,String state1,String state2){
		JSONArray result=new JSONArray();
		JSONArray value=new JSONArray();
		int rows = (Integer.parseInt(pageSize)-1)*(Integer.parseInt(pageNum));
		/*String sql="select a.SAMP_PIC_ID,a.SAMP_PIC_NAME,a.LENS_ID,a.ISO,a.FILM_PROD_ID,a.FILM_CAMERA_ID from TB_SP_FILM_SAMP_PIC_INFO4 a left join TB_ADM_FILM_SAMP_PIC_LABEL b on a.SAMP_PIC_ID=b.SAMP_PIC_ID" +
				" left join TB_CDE_LABEL c on b.LABEL_ID=c.LABEL_ID where 1=1 ";*/
		String sql="select a.* from TB_SP_FILM_SAMP_PIC_INFO4 a  left join TB_ADM_SAMP_PIC_STATUS d on a.SAMP_PIC_ID=d.SAMP_PIC_ID ";
		if(LABEL_NAME!=""&&LABEL_NAME!=null){
			value.add(state1);
			value.add(state2);
			sql+=" left join TB_ADM_FILM_SAMP_PIC_LABEL b on a.SAMP_PIC_ID=b.SAMP_PIC_ID" +
				" left join TB_CDE_SAMP_PIC_LABEL c on b.LABEL_ID=c.LABEL_ID where ( d.STATUS_ID=? or d.STATUS_ID=? )  ";
			String [] LABEL_NAMEs=LABEL_NAME.split(",");
			if(LABEL_NAMEs.length>1){
				sql+=" and (c.LABEL_NAME=?";
				for(int i=1;i<LABEL_NAMEs.length;i++){
					if(i==LABEL_NAMEs.length-1){
						value.add(LABEL_NAMEs[i]);
						sql+=" or c.LABEL_NAME=?)";
					}else{
						value.add(LABEL_NAMEs[i]);
						sql+=" or c.LABEL_NAME=?";
					}
				}
			}else{
				value.add(LABEL_NAMEs[0]);
				sql+=" and c.LABEL_NAME=?";
			}
		}else{
			value.add(state1);
			value.add(state2);
			sql+=" where ( d.STATUS_ID=? or d.STATUS_ID=? ) ";
		}
		if(SAMP_PIC_NAME!=""&&SAMP_PIC_NAME!=null){
			value.add(SAMP_PIC_NAME);
			sql+=" and a.SAMP_PIC_NAME=?";
		}
		if(FILM_CAMERA_ID!=""&&FILM_CAMERA_ID!=null&&!"0".equals(FILM_CAMERA_ID)){
			String [] FILM_CAMERA_IDs=FILM_CAMERA_ID.split(",");
			if(FILM_CAMERA_IDs.length>1){
				value.add(FILM_CAMERA_IDs[0]);
				sql+=" and (a.FILM_CAMERA_ID=?";
				for(int i=1;i<FILM_CAMERA_IDs.length;i++){
					if(i==FILM_CAMERA_IDs.length-1){
						value.add(FILM_CAMERA_IDs[i]);
						sql+=" or a.FILM_CAMERA_ID=?)";
					}else{
						value.add(FILM_CAMERA_IDs[i]);
						sql+=" or a.FILM_CAMERA_ID=?";
					}
				}
			}else{
				value.add(FILM_CAMERA_IDs[0]);
				sql+=" and a.FILM_CAMERA_ID=?";
			}
		}
		if(DEAL_DATE!=""&&DEAL_DATE!=null){
			value.add(DEAL_DATE);
			sql+=" and a.DEAL_DATE=?";
		}
		if(!"2".equals(IS_BLACK)&&!"".equals(IS_BLACK)){
			value.add(IS_BLACK);
			sql+=" and a.IS_BLACK=?";
		}
		if(LENS_ID!=""&&LENS_ID!=null&&!"0".equals(LENS_ID)){
			String [] LENS_IDs=LENS_ID.split(",");
			if(LENS_IDs.length>1){
				value.add(LENS_IDs[0]);
				sql+=" and (a.LENS_ID=?";
				for(int i=1;i<LENS_IDs.length;i++){
					if(i==LENS_IDs.length-1){
						value.add(LENS_IDs[i]);
						sql+=" or a.LENS_ID=?)";
					}else{
						value.add(LENS_IDs[i]);
						sql+=" or a.LENS_ID=?";
					}
				}
			}else{
				value.add(LENS_IDs[0]);
				sql+=" and a.LENS_ID=?";
			}
		}
		if(FILM_PROD_ID!=""&&FILM_PROD_ID!=null&&!"0".equals(FILM_PROD_ID)){
			String [] FILM_PROD_IDs=FILM_PROD_ID.split(",");
			if(FILM_PROD_IDs.length>1){
				value.add(FILM_PROD_IDs[0]);
				sql+=" and (a.FILM_PROD_ID=?";
				for(int i=1;i<FILM_PROD_IDs.length;i++){
					if(i==FILM_PROD_IDs.length-1){
						value.add(FILM_PROD_IDs[i]);
						sql+=" or a.FILM_PROD_ID=?)";
					}else{
						value.add(FILM_PROD_IDs[i]);
						sql+=" or a.FILM_PROD_ID=?";
					}
				}
			}else{
				value.add(FILM_PROD_IDs[0]);
				sql+=" and a.FILM_PROD_ID=?";
			}
		}
		if(!"0".equals(size)){
			if("1".equals(size)){
				value.add(SAMP_PIC_LENGTH);
				sql+=" and (case when a.SAMP_PIC_LENGTH>=a.SAMP_PIC_WIDTH then a.SAMP_PIC_WIDTH else a.SAMP_PIC_LENGTH end)<=?";
			}
			if("2".equals(size)){
				value.add(SAMP_PIC_LENGTH);
				sql+=" and (case when a.SAMP_PIC_LENGTH>=a.SAMP_PIC_WIDTH then a.SAMP_PIC_WIDTH else a.SAMP_PIC_LENGTH end)>=?";
			}
		}
		sql+=" order by a.SAMP_PIC_ID LIMIT "+rows +" , "+pageNum;
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,value.toArray());
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			String re=map.get("SAMP_PIC_SRC").toString().replace(" ", "%20");
			obj.put("SAMP_PIC_ID", map.get("SAMP_PIC_ID"));
			obj.put("SAMP_PIC_SRC", re);
			obj.put("FILM_PROD_ID", (map.get("FILM_PROD_ID")==null?"暂无":map.get("FILM_PROD_ID")));
			obj.put("SAMP_PIC_NAME", (map.get("SAMP_PIC_NAME")==null?"暂无":map.get("SAMP_PIC_NAME")));
			obj.put("SAMP_PIC_SOURCE_URL", map.get("SAMP_PIC_SOURCE_URL"));
			obj.put("FILM_CAMERA_ID", (map.get("FILM_CAMERA_ID")==null?"暂无":map.get("FILM_CAMERA_ID")));
			obj.put("FOC", (map.get("FOC")==null?"暂无":map.get("FOC")));
			obj.put("LENS_ID", (map.get("LENS_ID")==null?"暂无":map.get("LENS_ID")));
			obj.put("ISO", (map.get("ISO")==null?"暂无":map.get("ISO")));
			obj.put("SHUT", (map.get("SHUT")==null?"暂无":map.get("SHUT")));
			obj.put("EXPS_MOD", (map.get("EXPS_MOD")==null?"暂无":map.get("EXPS_MOD")));
			obj.put("METRY_MOD", (map.get("METRY_MOD")==null?"暂无":map.get("METRY_MOD")));
			obj.put("EXPS_EV", (map.get("EXPS_EV")==null?"暂无":map.get("EXPS_EV")));
			obj.put("APER", (map.get("APER")==null?"暂无":map.get("APER")));
			obj.put("VIEW", (map.get("VIEW")==null?"暂无":map.get("VIEW")));
			obj.put("WHITE_BLA", (map.get("WHITE_BLA")==null?"暂无":map.get("WHITE_BLA")));
			obj.put("COLOR_SPACE", (map.get("COLOR_SPACE")==null?"暂无":map.get("COLOR_SPACE")));
			obj.put("SAMP_PIC_SOURCE_PUB_TIME", (map.get("SAMP_PIC_SOURCE_PUB_TIME")==null?"暂无":map.get("SAMP_PIC_SOURCE_PUB_TIME")));
			obj.put("SHOOT_DATE", (map.get("SHOOT_DATE")==null?"暂无":map.get("SHOOT_DATE")));
			obj.put("IS_BLACK", map.get("IS_BLACK"));
			obj.put("SAMP_PIC_LENGTH", map.get("SAMP_PIC_LENGTH"));
			obj.put("SAMP_PIC_WIDTH", map.get("SAMP_PIC_WIDTH"));
			obj.put("EXIF_INFO", map.get("EXIF_INFO"));
			obj.put("DEAL_DATE", map.get("DEAL_DATE"));
			result.add(obj);
		}
		return result;
	}
	public JSONArray getOnePic(String SAMP_PIC_ID){
		JSONArray result =new JSONArray();
		String sql="select a.*,c.REASON_NAME from TB_SP_FILM_SAMP_PIC_INFO4 a left join" +
				" TB_ADM_SAMP_PIC_AUDIT_INFO b on a.SAMP_PIC_ID=b.SAMP_PIC_ID and " +
				" b.DEAL_TIME=(select max(DEAL_TIME) from TB_ADM_SAMP_PIC_AUDIT_INFO where SAMP_PIC_ID=?) " +
				" left join TB_CDE_AUDIT_BACK_REASON c on b.BACK_REASON_ID=c.REASON_ID where a.SAMP_PIC_ID=?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,SAMP_PIC_ID,SAMP_PIC_ID);
		for(Map<String, Object> map:list){
			String REASON_NAME="";
			JSONObject obj=new JSONObject();
			for(int i=0;i<list.size();i++){
				if(i==list.size()-1){
					REASON_NAME+=list.get(i).get("REASON_NAME");
				}else{
					REASON_NAME+=list.get(i).get("REASON_NAME")+",";
				}
			}
			obj.put("SAMP_PIC_ID", map.get("SAMP_PIC_ID"));
			obj.put("SAMP_PIC_SRC", map.get("SAMP_PIC_SRC").toString().replace(" ", "%20"));
			obj.put("FILM_PROD_ID", (map.get("FILM_PROD_ID")==null?"暂无":map.get("FILM_PROD_ID")));
			obj.put("SAMP_PIC_NAME", (map.get("SAMP_PIC_NAME")==null?"暂无":map.get("SAMP_PIC_NAME")));
			obj.put("SAMP_PIC_SOURCE_URL", map.get("SAMP_PIC_SOURCE_URL"));
			obj.put("FILM_CAMERA_ID", (map.get("FILM_CAMERA_ID")==null?"暂无":map.get("FILM_CAMERA_ID")));
			obj.put("FOC", (map.get("FOC")==null?"暂无":map.get("FOC")));
			obj.put("LENS_ID", (map.get("LENS_ID")==null?"暂无":map.get("LENS_ID")));
			obj.put("ISO", (map.get("ISO")==null?"暂无":map.get("ISO")));
			obj.put("SHUT", (map.get("SHUT")==null?"暂无":map.get("SHUT")));
			obj.put("EXPS_MOD", (map.get("EXPS_MOD")==null?"暂无":map.get("EXPS_MOD")));
			obj.put("METRY_MOD", (map.get("METRY_MOD")==null?"暂无":map.get("METRY_MOD")));
			obj.put("EXPS_EV", (map.get("EXPS_EV")==null?"暂无":map.get("EXPS_EV")));
			obj.put("APER", (map.get("APER")==null?"暂无":map.get("APER")));
			obj.put("VIEW", (map.get("VIEW")==null?"暂无":map.get("VIEW")));
			obj.put("WHITE_BLA", (map.get("WHITE_BLA")==null?"暂无":map.get("WHITE_BLA")));
			obj.put("COLOR_SPACE", (map.get("COLOR_SPACE")==null?"暂无":map.get("COLOR_SPACE")));
			obj.put("SAMP_PIC_SOURCE_PUB_TIME", (map.get("SAMP_PIC_SOURCE_PUB_TIME")==null?"暂无":map.get("SAMP_PIC_SOURCE_PUB_TIME")));
			obj.put("SHOOT_DATE", (map.get("SHOOT_DATE")==null?"暂无":map.get("SHOOT_DATE")));
			obj.put("IS_BLACK", map.get("IS_BLACK"));
			obj.put("SAMP_PIC_LENGTH", map.get("SAMP_PIC_LENGTH"));
			obj.put("SAMP_PIC_WIDTH", map.get("SAMP_PIC_WIDTH"));
			obj.put("EXIF_INFO", map.get("EXIF_INFO"));
			obj.put("DEAL_DATE", map.get("DEAL_DATE"));
			obj.put("REASON_NAME",("null".equals(REASON_NAME)?"暂无":REASON_NAME));
			result.add(obj);
			break;
		}
		return result;
	}
	public JSONArray getLabel(){
		JSONArray result=new JSONArray();
		String sql="select LABEL_NAME,LABEL_ID,SHOW_ORDER from TB_CDE_SAMP_PIC_LABEL order by SHOW_ORDER";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("LABEL_NAME", map.get("LABEL_NAME"));
			obj.put("LABEL_ID", map.get("LABEL_ID"));
			obj.put("SHOW_ORDER", map.get("SHOW_ORDER"));
			result.add(obj);
		}
		return result;
	}
	public JSONArray getCamera(String keyword){
		JSONArray result=new JSONArray();
		String sql="select PROD_ID from TB_PR_CAMERA_PROD_INFO where PROD_ID like ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,"%"+keyword+"%");
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("PROD_ID", map.get("PROD_ID"));
			result.add(obj);
		}
		return result;
	}
	public JSONArray getFilm(String keyword){
		JSONArray result=new JSONArray();
		String sql="select PROD_ID from TB_PR_FILM_PROD_INFO where PROD_ID like ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,"%"+keyword+"%");
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("PROD_ID", map.get("PROD_ID"));
			result.add(obj);
		}
		return result;
	}
	public JSONArray getLens(String keyword){
		JSONArray result=new JSONArray();
		String sql="select PROD_ID from TB_PR_LENS_PROD_INFO where PROD_ID like ?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,"%"+keyword+"%");
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("PROD_ID", map.get("PROD_ID"));
			result.add(obj);
		}
		return result;
	}
	public String updateInfo(String SAMP_PIC_ID,String SAMP_PIC_NAME,String FILM_PROD_ID,String FILM_CAMERA_ID,String LENS_ID,String IS_BLACK){
		Object [] value={SAMP_PIC_NAME,FILM_PROD_ID,FILM_CAMERA_ID,LENS_ID,IS_BLACK,SAMP_PIC_ID};
		String sql="update TB_SP_FILM_SAMP_PIC_INFO4 set SAMP_PIC_NAME=?, FILM_PROD_ID=?" +
				",FILM_CAMERA_ID=?, LENS_ID=?,IS_BLACK=? where SAMP_PIC_ID=?";
		int result=this.getJdbcTemplate().update(sql,value);
		return result+"";
	}
	public String deletePic(String SAMP_PIC_ID){
		String [] SAMP_PIC_IDs=SAMP_PIC_ID.split(",");
		String [] sql=new String[SAMP_PIC_IDs.length];
		int [] result={};
		for(int i=0;i<SAMP_PIC_IDs.length;i++){
			sql[i]="update TB_ADM_SAMP_PIC_STATUS set STATUS_ID='0' where SAMP_PIC_ID='"+SAMP_PIC_IDs[i]+"'";
		}
		result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public String screenPic(String SAMP_PIC_ID){
		String [] SAMP_PIC_IDs=SAMP_PIC_ID.split(",");
		String [] sql=new String[SAMP_PIC_IDs.length];
		int [] result={};
		for(int i=0;i<SAMP_PIC_IDs.length;i++){
			sql[i]="update TB_ADM_SAMP_PIC_STATUS set STATUS_ID='3' where SAMP_PIC_ID='"+SAMP_PIC_IDs[i]+"'";
		}
		result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public String recoveryPic(String SAMP_PIC_ID){
		String [] SAMP_PIC_IDs=SAMP_PIC_ID.split(",");
		String [] sql=new String[SAMP_PIC_IDs.length];
		int [] result={};
		for(int i=0;i<SAMP_PIC_IDs.length;i++){
			sql[i]="update TB_ADM_SAMP_PIC_STATUS set STATUS_ID='1' where SAMP_PIC_ID='"+SAMP_PIC_IDs[i]+"'";
		}
		result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public String rejectPic(String SAMP_PIC_ID){
		String [] SAMP_PIC_IDs=SAMP_PIC_ID.split(",");
		String [] sql=new String[SAMP_PIC_IDs.length];
		int [] result={};
		for(int i=0;i<SAMP_PIC_IDs.length;i++){
			sql[i]="update TB_ADM_SAMP_PIC_STATUS set STATUS_ID='2' where SAMP_PIC_ID='"+SAMP_PIC_IDs[i]+"'";
		}
		result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public String pass(String SAMP_PIC_ID){
		String [] SAMP_PIC_IDs=SAMP_PIC_ID.split(",");
		String [] sql=new String[SAMP_PIC_IDs.length];
		int [] result={};
		for(int i=0;i<SAMP_PIC_IDs.length;i++){
			sql[i]="update TB_ADM_SAMP_PIC_STATUS set STATUS_ID='4' where SAMP_PIC_ID='"+SAMP_PIC_IDs[i]+"'";
		}
		result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public JSONArray getPicLabel(String SAMP_PIC_ID){
		JSONArray result=new JSONArray();
		String sql="select a.LABEL_ID,b.LABEL_NAME,b.SHOW_ORDER from TB_ADM_FILM_SAMP_PIC_LABEL a left join TB_CDE_SAMP_PIC_LABEL b on a.LABEL_ID=b.LABEL_ID where a.SAMP_PIC_ID=? order by b.SHOW_ORDER";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,SAMP_PIC_ID);
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("LABEL_ID", map.get("LABEL_ID"));
			obj.put("LABEL_NAME", map.get("LABEL_NAME"));
			obj.put("SHOW_ORDER", map.get("SHOW_ORDER"));
			result.add(obj);
		}
		return result;
	}
	public String addPicLabel(String SAMP_PIC_ID,String LABEL_ID,String order){
		String sql="insert into TB_ADM_FILM_SAMP_PIC_LABEL values(?,?,?,now())"; 
		int result=this.getJdbcTemplate().update(sql,SAMP_PIC_ID,LABEL_ID,order);
		return result+"";
	}
	public String deletePicLabel(String SAMP_PIC_ID,String LABEL_ID){
		String sql="delete from TB_ADM_FILM_SAMP_PIC_LABEL where SAMP_PIC_ID=? and LABEL_ID=?";
		int result=this.getJdbcTemplate().update(sql,SAMP_PIC_ID,LABEL_ID);
		return result+"";
	}
	public JSONArray searchLabel(String keyword,String SAMP_PIC_ID){
		JSONArray result=new JSONArray();
		String sql="select LABEL_ID,LABEL_NAME,SHOW_ORDER from TB_CDE_SAMP_PIC_LABEL where LABEL_NAME like ?";
		String sql2="select LABEL_ID from TB_ADM_FILM_SAMP_PIC_LABEL where SAMP_PIC_ID=?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql,"%"+keyword+"%");
		List<Map<String, Object>> list2 = this.getJdbcTemplate().queryForList(sql2,SAMP_PIC_ID);
		for(Map<String, Object> map:list){
			JSONObject obj=new JSONObject();
			obj.put("LABEL_ID", map.get("LABEL_ID"));
			obj.put("LABEL_NAME", map.get("LABEL_NAME"));
			obj.put("SHOW_ORDER", map.get("SHOW_ORDER"));
			obj.put("is", "0");
			for(Map<String, Object> map2:list2){
				if(map.get("LABEL_ID").equals(map2.get("LABEL_ID"))){
					obj.put("is","1");
				}
			}
			result.add(obj);
		}
		return result;
	}
	public String addLabel(String LABEL_NAME,String SAMP_PIC_ID,String LABEL_DESC){
		String sql="select max(a.SHOW_ORDER) as SHOW_ORDER , max(a.LABEL_ID) as LABEL_ID from TB_CDE_SAMP_PIC_LABEL a";
		String sql2="select count(*) from TB_CDE_SAMP_PIC_LABEL where LABEL_NAME=?";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		int count=this.getJdbcTemplate().queryForInt(sql2,LABEL_NAME);
		int maxorder=0;
		int maxid=0;
		String order;
		String labelId;
		String LABEL_ID;
		if(list.size()>0){
			for(Map<String, Object> map:list){
				maxorder=Integer.parseInt(map.get("SHOW_ORDER").toString());
				maxid=Integer.parseInt(map.get("LABEL_ID").toString());
			}
		}
		if(count==0){
			maxorder++;
			maxid++;
			order=String.valueOf(maxorder);
			labelId=String.valueOf(maxid);
			LABEL_ID=labelId;
			for(int i=0;i<4-labelId.length();i++){
				LABEL_ID="0"+LABEL_ID;
			}
			String sql3="insert into TB_CDE_SAMP_PIC_LABEL values(?,?,?,?,now())";
			int result=this.getJdbcTemplate().update(sql3,LABEL_ID,LABEL_NAME,LABEL_DESC,order);
			if(result!=0){
				String sql4="insert into TB_ADM_FILM_SAMP_PIC_LABEL values(?,?,?,now())";
				result=this.getJdbcTemplate().update(sql4,SAMP_PIC_ID,LABEL_ID,order);
				return LABEL_ID;
			}else{
				return "0";
			}
		}else{
			return "0";
		}
	}
	public JSONArray getDismissal(){
		JSONArray result=new JSONArray();
		String sql="select REASON_ID,REASON_NAME from TB_CDE_AUDIT_BACK_REASON where REASON_TYPE='驳回'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			for(Map<String, Object> map:list){
				JSONObject obj=new JSONObject();
				obj.put("REASON_ID", map.get("REASON_ID"));
				obj.put("REASON_NAME", map.get("REASON_NAME"));
				result.add(obj);
			}
		}
		return result;
	}
	public JSONArray getRegresses(){
		JSONArray result=new JSONArray();
		String sql="select REASON_ID,REASON_NAME from TB_CDE_AUDIT_BACK_REASON where REASON_TYPE='回退'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if(list.size()>0){
			for(Map<String, Object> map:list){
				JSONObject obj=new JSONObject();
				obj.put("REASON_ID", map.get("REASON_ID"));
				obj.put("REASON_NAME", map.get("REASON_NAME"));
				result.add(obj);
			}
		}
		return result;
	}
	public String addRegresses(String SAMP_PIC_ID,String SAMP_PIC_TYPE,String STATUS_ID,String AUDIT_DESC,String AUDIT_PERSN_ACCT_ID,String BACK_REASON_ID){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		String [] picId=SAMP_PIC_ID.split(",");
		String [] reId=BACK_REASON_ID.split(",");
		String [] sql=new String[picId.length*reId.length];
		List<String> list=new ArrayList<String>();
		for(int i=0;i<picId.length;i++){
			for(int j=0;j<reId.length;j++){
				list.add("insert into TB_ADM_SAMP_PIC_AUDIT_INFO values('"+picId[i]+"','"+SAMP_PIC_TYPE+"','"+STATUS_ID+"','"+AUDIT_DESC+"','"+AUDIT_PERSN_ACCT_ID+"','"+date+"','"+reId[j]+"')");
			}
		}
		for(int i=0;i<list.size();i++){
			sql[i]=list.get(i);
		}
		int [] result=this.getJdbcTemplate().batchUpdate(sql);
		return result.length+"";
	}
	public String addReason(String REASON_ID,String REASON_TYPE,String REASON_NAME,String REASON_DESC,String SHOW_ORDER){
		Object [] value={REASON_ID,REASON_TYPE,REASON_NAME,REASON_DESC,SHOW_ORDER};
		String sql="insert into TB_CDE_AUDIT_BACK_REASON values(?,?,?,?,?,now())";
		int result=this.getJdbcTemplate().update(sql,value);
		return result+"";
	}
}
