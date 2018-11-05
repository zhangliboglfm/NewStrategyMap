/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: 精益有容（北京）科技有限公司</p> 
* @author 白少华
* @date 2018-8-6
*/
package com.miapsoft.controller;

public class ServerFilePath {
	
	private static String systemFilePath = "/home/nfs/resourcelib/system";//上传临时目录
	private static String tmpFilePath = "/home/nfs/resourcelib/tmp";//上传临时目录
	private static String photogdir = "/home/nfs/resourcelib/photographer/photog";//摄影家，头像，作品目录
	private static String coreintrodir = "/home/nfs/resourcelib/photographer/coreintro";//摄影家核心介绍Word、图片、H5、封面
	private static String worksintrodir = "/home/nfs/resourcelib/photographer/worksintro";//摄影家作品解读Word、图片、H5、封面
	private static String articledir = "/home/nfs/resourcelib/photographer/article";//专题文章Word、图片、H5、封面
	private static String appdir = "/home/nfs/resourcelib/sysmanage/appicon/";//专题文章Word、图片、H5、封面
	
	private static String calligdir = "/home/nfs/resourcelib/calligrapher/callig";//书法家，头像，作品目录
	private static String cgarticledir = "/home/nfs/resourcelib/calligrapher/article";//书法家文章路径
	private static String cgworksintrodir = "/home/nfs/resourcelib/calligrapher/cgworksintro";//书法家作品图片、封面
	
	private static String pModelPath = "/home/nfs/resourcelib/model/摄影家模板.xlsx";//专题文章Word、图片、H5、封面
	private static String aModelPath = "/home/nfs/resourcelib/model/文章模板文件（文件夹名称建议修改为摄影家ID）.zip";//专题文章Word、图片、H5、封面
	private static String wModelPath = "/home/nfs/resourcelib/model/摄影家作品模板文件.zip";//专题文章Word、图片、H5、封面
	private static String cgModelPath = "/home/nfs/resourcelib/model/书法家作品模板文件（文件夹名称建议修改为作品ID）.zip";//书法家作品上传模板
	private static String cgInputModelPath = "/home/nfs/resourcelib/model/书法家模板.xlsx";//书法家基本信息录入模板
	private static String cgArtModelPath = "/home/nfs/resourcelib/model/书法家文章模板文件.zip";//书法家文章信息录入模板

	/*private static String systemFilePath = "/home/resourcelib/system";//上传临时目录
	private static String tmpFilePath = "/home/resourcelib/tmp";//上传临时目录
	private static String photogdir = "/home/resourcelib/photographer/photog";//摄影家，头像，作品目录
	private static String coreintrodir = "/home/resourcelib/photographer/coreintro";//摄影家核心介绍Word、图片、H5、封面
	private static String worksintrodir = "/home/resourcelib/photographer/worksintro";//摄影家作品解读Word、图片、H5、封面
	private static String articledir = "/home/resourcelib/photographer/article";//专题文章Word、图片、H5、封面
	private static String appdir = "/home/resourcelib/sysmanage/appicon/";//专题文章Word、图片、H5、封面
	
	private static String calligdir = "/home/resourcelib/calligrapher/callig";//书法家，头像，作品目录
	private static String cgarticledir = "/home/resourcelib/calligrapher/article";//书法家，头像，作品目录
	private static String cgworksintrodir = "/home/resourcelib/calligrapher/cgworksintro";//书法家作品图片、封面
	
	private static String pModelPath = "/home/resourcelib/model/摄影家模板.xlsx";//专题文章Word、图片、H5、封面
	private static String aModelPath = "/home/resourcelib/model/文章模板文件（文件夹名称建议修改为摄影家ID）.zip";//专题文章Word、图片、H5、封面
	private static String wModelPath = "/home/resourcelib/model/摄影家作品模板文件.zip";//专题文章Word、图片、H5、封面
	private static String cgModelPath = "/home/resourcelib/model/书法家作品模板文件（文件夹名称建议修改为作品ID）.zip";//专题文章Word、图片、H5、封面
	private static String cgInputModelPath = "/home/resourcelib/model/书法家模板.xlsx";//书法家基本信息录入模板
	private static String cgArtModelPath = "/home/resourcelib/model/书法家文章模板文件.zip";//书法家文章信息录入模板
*/	
	
/*	private static String systemFilePath = "H:/resourcelib/system";//上传临时目录
	private static String tmpFilePath = "H:/resourcelib/tmp";//上传临时目录
	private static String photogdir = "H:/resourcelib/photographer/photog";//摄影家，头像，作品目录
	private static String coreintrodir = "H:/resourcelib/photographer/coreintro";//摄影家核心介绍Word、图片、H5、封面
	private static String worksintrodir = "H:/resourcelib/photographer/worksintro";//摄影家作品解读Word、图片、H5、封面
	private static String articledir = "H:/resourcelib/photographer/article";//专题文章Word、图片、H5、封面
	private static String appdir = "H:/resourcelib/sysmanage/appicon/";//专题文章Word、图片、H5、封面
	
	private static String calligdir = "H:/resourcelib/calligrapher/callig";//书法家，头像，作品目录
	private static String cgarticledir = "H:/resourcelib/calligrapher/article";//书法家文章路径
	private static String cgworksintrodir = "H:/resourcelib/calligrapher/cgworksintro";//书法家作品图片、封面
	
	private static String pModelPath = "H:/resourcelib/model/摄影家模板.xlsx";//专题文章Word、图片、H5、封面
	private static String aModelPath = "H:/resourcelib/model/文章模板文件（文件夹名称建议修改为摄影家ID）.zip";//专题文章Word、图片、H5、封面
	private static String wModelPath = "H:/resourcelib/model/摄影家作品模板文件.zip";//专题文章Word、图片、H5、封面
	private static String cgModelPath = "H:/resourcelib/model/书法家作品模板文件（文件夹名称建议修改为作品ID）.zip";//书法家作品上传模板
	private static String cgInputModelPath = "H:/resourcelib/model/书法家模板.xlsx";//书法家基本信息录入模板
	private static String cgArtModelPath = "H:/resourcelib/model/书法家文章模板文件.zip";//书法家文章信息录入模板
*/	
	
	public static String getCgInputModelPath() {
		return cgInputModelPath;
	}
	public static void setCgInputModelPath(String cgInputModelPath) {
		ServerFilePath.cgInputModelPath = cgInputModelPath;
	}
	public static String getCgArtModelPath() {
		return cgArtModelPath;
	}
	public static void setCgArtModelPath(String cgArtModelPath) {
		ServerFilePath.cgArtModelPath = cgArtModelPath;
	}
	public static String getCgworksintrodir() {
		return cgworksintrodir;
	}
	public static void setCgworksintrodir(String cgworksintrodir) {
		ServerFilePath.cgworksintrodir = cgworksintrodir;
	}
	public static String getCgModelPath() {
		return cgModelPath;
	}
	public static void setCgModelPath(String cgModelPath) {
		ServerFilePath.cgModelPath = cgModelPath;
	}
	/**
	 * @return the systemFilePath
	 */
	public static String getSystemFilePath() {
		return systemFilePath;
	}
	/**
	 * @param systemFilePath the systemFilePath to set
	 */
	public static void setSystemFilePath(String systemFilePath) {
		ServerFilePath.systemFilePath = systemFilePath;
	}
	/**
	 * @return the tmpFilePath
	 */
	public static String getTmpFilePath() {
		return tmpFilePath;
	}
	/**
	 * @param tmpFilePath the tmpFilePath to set
	 */
	public static void setTmpFilePath(String tmpFilePath) {
		ServerFilePath.tmpFilePath = tmpFilePath;
	}
	/**
	 * @return the photogdir
	 */
	public static String getPhotogdir() {
		return photogdir;
	}
	/**
	 * @param photogdir the photogdir to set
	 */
	public static void setPhotogdir(String photogdir) {
		ServerFilePath.photogdir = photogdir;
	}
	/**
	 * @return the coreintrodir
	 */
	public static String getCoreintrodir() {
		return coreintrodir;
	}
	/**
	 * @param coreintrodir the coreintrodir to set
	 */
	public static void setCoreintrodir(String coreintrodir) {
		ServerFilePath.coreintrodir = coreintrodir;
	}
	/**
	 * @return the worksintrodir
	 */
	public static String getWorksintrodir() {
		return worksintrodir;
	}
	/**
	 * @param worksintrodir the worksintrodir to set
	 */
	public static void setWorksintrodir(String worksintrodir) {
		ServerFilePath.worksintrodir = worksintrodir;
	}
	/**
	 * @return the articledir
	 */
	public static String getArticledir() {
		return articledir;
	}
	/**
	 * @param articledir the articledir to set
	 */
	public static void setArticledir(String articledir) {
		ServerFilePath.articledir = articledir;
	}
	/**
	 * @return the pModelPath
	 */
	public static String getpModelPath() {
		return pModelPath;
	}
	/**
	 * @return the cgModelPath
	 */
	public static String getcgModelPath() {
		return cgModelPath;
	}
	/**
	 * @param pModelPath the pModelPath to set
	 */
	public static void setpModelPath(String pModelPath) {
		ServerFilePath.pModelPath = pModelPath;
	}
	/**
	 * @return the aModelPath
	 */
	public static String getaModelPath() {
		return aModelPath;
	}
	/**
	 * @param aModelPath the aModelPath to set
	 */
	public static void setaModelPath(String aModelPath) {
		ServerFilePath.aModelPath = aModelPath;
	}
	public static String getwModelPath() {
		return wModelPath;
	}
	public static void setwModelPath(String wModelPath) {
		ServerFilePath.wModelPath = wModelPath;
	}
	
	public static String getAppdir() {
		return appdir;
	}
	public static void setAppdir(String appdir) {
		ServerFilePath.appdir = appdir;
	}
	/**
	 * @return the calligdir
	 */
	public static String getCalligdir() {
		return calligdir;
	}
	/**
	 * @param calligdir the calligdir to set
	 */
	public static void setCalligdir(String calligdir) {
		ServerFilePath.calligdir = calligdir;
	}
	/**
	 * @return the cgarticledir
	 */
	public static String getCgarticledir() {
		return cgarticledir;
	}
	/**
	 * @param cgarticledir the cgarticledir to set
	 */
	public static void setCgarticledir(String cgarticledir) {
		ServerFilePath.cgarticledir = cgarticledir;
	}
}
