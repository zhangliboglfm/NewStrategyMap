  
import java.io.IOException;  
import java.util.ArrayList;  
  
import org.im4java.core.CompositeCmd;  
import org.im4java.core.ConvertCmd;  
import org.im4java.core.IM4JavaException;  
import org.im4java.core.IMOperation;  
import org.im4java.core.IdentifyCmd;  
import org.im4java.process.ArrayListOutputConsumer;  
  
public class ImagesUtil {  
        /**  
         * ��������ü�ͼƬ  
         *  
         * @param srcPath   Ҫ�ü�ͼƬ��·��  
         * @param newPath   �ü�ͼƬ���·��  
         * @param x   ��ʼ������  
         * @param y   ��ʼ������  
         * @param x1  ����������  
         * @param y1  ����������  
         */    
        public static void cutImage(String srcPath, String newPath, int x, int y, int x1,    
                int y1)  throws Exception {    
            int width = x1 - x;    
            int height = y1 - y;    
            IMOperation op = new IMOperation();    
            op.addImage(srcPath);    
            /**  width���ü��Ŀ��    * height���ü��ĸ߶� * x���ü��ĺ����� * y���ü�������  */    
            op.crop(width, height, x, y);    
            op.addImage(newPath);    
            ConvertCmd convert = new ConvertCmd();  
            convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16");
            convert.run(op);    
        }    
        /**  
         * ���ݳߴ�����ͼƬ  
         * @param width  ���ź��ͼƬ���  
         * @param height  ���ź��ͼƬ�߶�  
         * @param srcPath   ԴͼƬ·��  
         * @param newPath   ���ź�ͼƬ��·��  
         */    
        public static void zoomImage(Integer width, Integer height, String srcPath, String newPath) throws Exception {    
            IMOperation op = new IMOperation();    
            op.addImage(srcPath);    
            if(width == null){//���ݸ߶�����ͼƬ  
                op.resize(null, height);      
            }else if(height == null){//���ݿ������ͼƬ  
                op.resize(width, null);  
            }else {  
                op.resize(width, height);  
            }  
            op.addRawArgs("-quality", "100%");
            op.addImage(newPath);    
            ConvertCmd convert = new ConvertCmd(); 
            convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16"); 
            convert.run(op);   
        }    
  
             
        /**  
         * ��ͼƬ��ˮӡ  
         * @param srcPath   ԴͼƬ·��  
         */    
        public static void addImgText(String srcPath,String content) throws Exception {    
            IMOperation op = new IMOperation();    
            op.font("΢���ź�");  
            op.gravity("southeast");  
            op.pointsize(18).fill("#BCBFC8").draw("text 0,0 "+content);   //("x1 x2 x3 x4") x1 ��ʽ��x2 x����� x3 y�����  x4����      
            op.addImage();    
            op.addImage();    
            ConvertCmd convert = new ConvertCmd(); 
            convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16");
            try {  
              convert.run(op,srcPath,srcPath);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }    
        /** 
         * ͼƬˮӡ 
         * 
         * @param srcImagePath   ԴͼƬ 
         * @param waterImagePath ˮӡ 
         * @param destImagePath  ����ͼƬ 
         * @param gravity  ͼƬλ�� 
         * @param dissolve ˮӡ͸���� 
         */  
        public static void waterMark(String waterImagePath, String srcImagePath, String destImagePath, String gravity, int dissolve) {  
            IMOperation op = new IMOperation();  
            op.gravity(gravity);  
            op.dissolve(dissolve);  
            op.addImage(waterImagePath);  
            op.addImage(srcImagePath);  
            op.addImage(destImagePath);  
            CompositeCmd cmd = new CompositeCmd();  
            try {  
                cmd.run(op);  
            } catch (IOException e) {  
                e.printStackTrace();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (IM4JavaException e) {  
                e.printStackTrace();  
            }  
        }  
        /** 
         * ͼƬ��ת 
         * 
         * @param srcImagePath 
         * @param destImagePath 
         * @param angle 
         */  
        public static void rotate(String srcImagePath, String destImagePath, double angle) {  
            try {  
                IMOperation op = new IMOperation();  
                op.rotate(angle);  
                op.addImage(srcImagePath);  
                op.addImage(destImagePath);  
                ConvertCmd convert = new ConvertCmd();  
                convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16");
                convert.run(op);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
          
        /** 
         * ͼƬ��Ϣ 
         * 
         * @param imagePath 
         * @return 
         */  
        public static String showImageInfo(String imagePath) {  
            String line = null;  
            try {  
                IMOperation op = new IMOperation();  
                op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");  
                op.addImage(1);  
                IdentifyCmd identifyCmd = new IdentifyCmd(true);  
                ArrayListOutputConsumer output = new ArrayListOutputConsumer();  
                identifyCmd.setOutputConsumer(output);  
                identifyCmd.run(op, imagePath);  
                ArrayList<String> cmdOutput = output.getOutput();  
                assert cmdOutput.size() == 1;  
                line = cmdOutput.get(0);  
       
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return line;  
        }  
        /** 
         * ͼƬ�ϳ� 
         * @param args 
         * @param maxWidth 
         * @param maxHeight 
         * @param newpath 
         * @param mrg 
         * @param type 1:��,2:�� 
         */  
        public static void montage(String[] args,Integer maxWidth,Integer maxHeight,String newpath,Integer mrg,String type) {  
            IMOperation op = new IMOperation();  
            ConvertCmd convert = new ConvertCmd(); 
            convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16");
            String thumb_size = maxWidth+"x"+maxHeight+"^";  
            String extent = maxWidth+"x"+maxHeight;  
            if("1".equals(type)){  
                op.addRawArgs("+append");  
            }else if("2".equals(type)){  
                op.addRawArgs("-append");  
            }  
              
            op.addRawArgs("-thumbnail",thumb_size);  
            op.addRawArgs("-gravity","center");  
            op.addRawArgs("-extent",extent);  
              
            Integer border_w = maxWidth / 40;  
            op.addRawArgs("-border",border_w+"x"+border_w);  
            op.addRawArgs("-bordercolor","#ccc");  
              
            op.addRawArgs("-border",1+"x"+1);  
            op.addRawArgs("-bordercolor","#fff");  
              
            for(String img : args){  
                op.addImage(img);  
            }  
            if("1".equals(type)){  
                Integer whole_width = ((mrg / 2) +1 + border_w + maxWidth + border_w + (mrg / 2) +1)*args.length - mrg;  
                Integer whole_height = maxHeight + border_w + 1;  
                op.addRawArgs("-extent",whole_width + "x" +whole_height);  
            }else if("2".equals(type)){  
                Integer whole_width = maxWidth + border_w + 1;  
                Integer whole_height = ((mrg / 2) +1 + border_w + maxHeight + border_w + (mrg / 2) +1)*args.length - mrg;  
                op.addRawArgs("-extent",whole_width + "x" +whole_height);  
            }  
            op.addImage(newpath);  
            try {  
            	convert.run(op);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        public static void main(String[] args) throws Exception{    
          //addImgText("e://a2.jpg");    
           zoomImage(null, 3000, "E:\\Image\\6.psd", "E:\\Image\\bak\\6.psd");  
             //waterMark("e://cc.jpg", "e://aa.jpg", "e://bb.jpg", "southeast", 30);  
            //rotate("e://aa.jpg", "e://ee.jpg", 90);  
            //System.out.println(showImageInfo("e://aa.jpg"));  
            //montage(files, 280, 200, "e://liboy1.jpg", 0,"2");  
            //cropImage("e://a.jpg", "e://liboy22.jpg", 1024, 727, 500, 350);  
            //cutImage("E:\\Image\\3.psd", "E:\\Image\\6.jpg", 0, 0, 3000, 3000);  
        }    
} 

