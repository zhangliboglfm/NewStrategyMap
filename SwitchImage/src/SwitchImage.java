import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



public class SwitchImage {

	public static void main(String[] args) throws Exception {
		
		// ��ȡԭʼͼƬ�ߴ�
		/*RandomAccessFile raf = new RandomAccessFile(new File("E:\\Image\\1.psd"), "r");  
		FileChannel fc = raf.getChannel();  
        long size = fc.size();  
        MappedByteBuffer mbbi = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);  
        mbbi.position(0x0c);  
        mbbi.getShort();  
        //ͼ��߶�  
        int height = mbbi.getInt();  
        //ͼ����  
        int width = mbbi.getInt(); 
        fc.close();
        raf.close();*/
		
      /*  IMOperation op = new IMOperation(); 
        op.addImage("E:\\Image\\4.psb"); 
        op.resize(null, 3000);      
        op.addRawArgs("-quality", "100%");
        //op.addRawArgs("-thumbnail", width+"x"+height+"!");
        //op.addRawArgs("-units", "pixelsperinch");
        //op.addRawArgs("-density", "96");
        //op.addRawArgs("-colorspace","RGB"); // ת��RGBϵ ����CMYKģʽ�µ�imageIOˮӡ�쳣
        //op.addRawArgs("-strip");
        op.addImage("E:\\Image\\bak\\4.jpg");    
        ConvertCmd convert = new ConvertCmd(); 
        convert.setSearchPath("C:\\Program Files\\ImageMagick-7.0.7-Q16"); 
        convert.run(op);
        //�и�ͼƬ
        try {
            // ��ȡԴͼ��
            BufferedImage bi = ImageIO.read(new File("E:\\Image\\bak\\4-1.jpg"));
            int srcWidth = bi.getWidth(); // Դͼ���
            int srcHeight = bi.getHeight(); // Դͼ�߶�
            Image image = bi.getScaledInstance(srcWidth, srcHeight,
                    Image.SCALE_DEFAULT);
            // �ĸ������ֱ�Ϊͼ���������Ϳ��
            // ��: CropImageFilter(int x,int y,int width,int height)
            int width =(int) Math.ceil(srcWidth/10);
            for (int i = 0; i < 10; i++) {
            	if(i==9){
            		width = srcWidth-9*width;
            	}
            	ImageFilter cropFilter = new CropImageFilter(i*width, 0, width, 3000);
            	Image img = Toolkit.getDefaultToolkit().createImage(
                         new FilteredImageSource(image.getSource(),
                                 cropFilter));
            	  BufferedImage tag = new BufferedImage(width, 3000, BufferedImage.TYPE_INT_RGB);
                  Graphics g = tag.getGraphics();
                  g.drawImage(img, 0, 0, width, 3000, null); // �����и���ͼ
                  g.dispose();
                  // ���Ϊ�ļ�
                 // ImageIO.write(tag, "JPEG", new File("E:\\Image\\bak1\\2-"+(i+1)+".jpg"));
                  FileOutputStream os = new FileOutputStream("E:\\Image\\bak1\\4-"+(i+1)+".jpg");
                  JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
                  JPEGEncodeParam jParam = encoder.getDefaultJPEGEncodeParam(tag) ;
                  jParam.setQuality(0.67f, false) ;
                  encoder.encode(tag);
                  os.close();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
		Image img =new PsdReader(new File("E:\\Image\\8.psb")).getImg();
		 BufferedImage tag = new BufferedImage(6000, 3000, BufferedImage.TYPE_INT_RGB);
         Graphics g = tag.getGraphics();
         g.drawImage(img, 0, 0, 6000, 3000, null); // �����и���ͼ
         g.dispose();
         // ���Ϊ�ļ�
        ImageIO.write(tag, "JPEG", new File("E:\\Image\\bak1\\900.jpg"));
	}
}
