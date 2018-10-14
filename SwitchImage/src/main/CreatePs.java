package main;

import utils.CreateFile;

public class CreatePs {
	
	public static void CreatePsShell(String filepath,int n){
		StringBuffer strbuff1 = new StringBuffer();
		strbuff1.append("#target photoshop \n");
		strbuff1.append("var startRulerUnits = app.preferences.rulerUnits;  \n");
		strbuff1.append("var startTypeUnits = app.preferences.typeUnits; \n");
		strbuff1.append("var startDisplayDialogs = app.displayDialogs;  \n");
		strbuff1.append("app.preferences.rulerUnits = Units.PIXELS;  \n");
		strbuff1.append("app.preferences.typeUnits = TypeUnits.PIXELS;  \n");
		strbuff1.append("app.displayDialogs = DialogModes.NO;  \n\n");
		strbuff1.append("var date = new Date();  \n");
		strbuff1.append("var logname = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+date.getHours()+date.getMinutes(); \n");
		strbuff1.append(("var filelog = new File('"+filepath+"\\psLog\\'+logname+'.log');  \n").replace("\\", "\\\\"));
		strbuff1.append("filelog.open('w', 'TEXT',true);   \n\n");
		strbuff1.append("var part = "+n+";   \n");
		strbuff1.append(("var sampleFolder = new Folder('"+filepath+"\\sources');   \n").replace("\\", "\\\\"));
		strbuff1.append("var childFiles=sampleFolder.getFiles();   \n");
		strbuff1.append("for(var k=0;k<childFiles.length;k++){   \n");
		strbuff1.append("    var fileList = childFiles[k].getFiles();   \n");
		strbuff1.append("    var parentName = decodeURI(childFiles[k].name);   \n");
		strbuff1.append("    for(var j = 0; j<fileList.length;j++){   \n");
		strbuff1.append("             while (app.documents.length != 0) {   \n");
		strbuff1.append("                app.activeDocument.close(SaveOptions.DONOTSAVECHANGES);   \n");
		strbuff1.append("             };   \n\n");
		strbuff1.append("            if(fileList[j] instanceof File){   \n\n");
		strbuff1.append("                var  oridoc  =open(fileList[j]);   \n");
		strbuff1.append("                var filename = (oridoc.name);   \n\n");
		strbuff1.append("                var FirstName =  filename.substring(0,filename.lastIndexOf('.'));   \n");
		strbuff1.append("                oridoc.resizeImage(null,3000);   \n");
		strbuff1.append("                var width = oridoc.width;   \n");
		strbuff1.append("                var oneWidth = Math.ceil(width/part);   \n\n");
		strbuff1.append("                var exportOptionsSaveForWeb = new ExportOptionsSaveForWeb();   \n");
		strbuff1.append("                exportOptionsSaveForWeb.format = SaveDocumentType.JPEG;   \n");
		strbuff1.append("                exportOptionsSaveForWeb.quality = 67;     \n\n");
		strbuff1.append("                for (var i =0;i<part;i++){   \n");
		strbuff1.append("                        var newdoc=oridoc.duplicate();   \n");
		strbuff1.append("                        if(i==(part-1)){   \n");
		strbuff1.append("                            newdoc.crop ([oneWidth*i,0,width,3000])   \n");
		strbuff1.append("                         }else{   \n");
		strbuff1.append("                            newdoc.crop ([oneWidth*i,0,oneWidth*(i+1),3000])   \n");
		strbuff1.append("                          }   \n");
		strbuff1.append(("                        var fileOut = new File('"+filepath+"\\target\\'+parentName+'\\'+FirstName+'_'+i+'.jpg');   \n").replace("\\", "\\\\"));
		strbuff1.append("                        newdoc.exportDocument(fileOut, ExportType.SAVEFORWEB, exportOptionsSaveForWeb);   \n");
		strbuff1.append("                        newdoc.close (SaveOptions.DONOTSAVECHANGES)   \n");
		strbuff1.append("                        filelog.write(FirstName+'第'+i+'部分切割完成 \\r\\n');   \n");
		strbuff1.append("                }   \n");
		strbuff1.append("                    filelog.write('************'+FirstName+'切割完成*************** \\r\\n\\r\\r');   \n");
		strbuff1.append("          }else{   \n");
		strbuff1.append("              continue;   \n");
		strbuff1.append("          }   \n\n");
		strbuff1.append("     }  \n");
		strbuff1.append("       filelog.write('\\r\\n\\r\\n************第'+k+'个文件夹'+parentName+'切割完成*************** \\r\\n\\r\\r');  \n\n");
		strbuff1.append(" }  \n\n");
		strbuff1.append("filelog.close();  \n\n");
		
		
		
		CreateFile.createFile1(filepath+"\\来源1.jsx", strbuff1.toString());
		
	}
}
