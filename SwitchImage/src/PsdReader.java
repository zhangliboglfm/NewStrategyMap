import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
 
/**
 * 使用了FileChannel和MappedByteBuffer加快读取速度，起初用RandomAccessFile也是可以达到同样目的，但是效率过低。
 * @author Administrator
 *
 */
public class PsdReader {  
  
	private BufferedImage img = null;//最终获得的目标图片  
	private int[] pixels;  
	private RandomAccessFile raf;  
	private int[] byteArray;  
	private int[][][] channelColor;//每条通道的颜色，0号位是red，1号位是green，2号位是blue，如果是4通道，alpha直接跳过  
	private int[][] numOfBytePerLine;//每行的字节数，扫描图片信息的时候是逐行进行的，因为rle压缩的关系，每行的字节数都不一定相同  
	private short numOfChannel;//通道的数目，如果是带有透明度，则是4通道，否则通常是3通道  
	private int height;  
	private int width;  
	private short isRle;//压缩方式，是0则没有压缩，是1则是rle压缩  
	private MappedByteBuffer mbbi; 
      
    public PsdReader(File file) {  
        FileChannel fc = null;  
        try {  
            this.raf = new RandomAccessFile(file, "r");  
            fc = raf.getChannel();  
            long size = fc.size();  
            this.mbbi = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        this.readFile(); //关键，读取图片文件中的信息 
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);    
        pixels = new int[width*height];    
        this.initPixels(pixels);  
        this.setRGB(img, 0, 0, width, height, pixels);  
        try {  
            fc.close();  
            this.raf.close();  
            
            
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public BufferedImage getImg() {  
        return img;  
    }  
      
    private void initPixels(int[] pixels) {  
        int index = 0;  
        int a = 255;  
        for(int h=0; h<this.height; h++) {  
            for(int w=0; w<this.width; w++) {  
                int r = this.channelColor[0][h][w];  
                int g = this.channelColor[1][h][w];  
                int b = this.channelColor[2][h][w];  
                if(this.numOfChannel>3) {  
                    a = this.channelColor[3][h][w];  
                }  
                  
                pixels[index] = (a<<24) | (r<<16)  
                        | (g<<8) | b;  
                index++;  
            }  
        }  
    }  
      
    private void setRGB( BufferedImage image, int x, int y, int width, int height, int[] pixels ) {    
        int type = image.getType();    
        if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )    
            image.getRaster().setDataElements( x, y, width, height, pixels );    
        else    
            image.setRGB( x, y, width, height, pixels, 0, width );    
    }  
      
    private void readFile() {  
        try {  
            //-------第一部分：文件头------------------  
            //通道数量  
//          this.raf.seek(0x0c); 
        	System.out.println(this.mbbi.toString());
            this.mbbi.position(0x0c);  
//          numOfChannel = this.raf.readShort();  
            numOfChannel = this.mbbi.getShort();  
            //System.out.println("numOfChannel="+numOfChannel);  
            //图像高度  
//          height = this.raf.readInt();  
            height = this.mbbi.getInt();  
            //System.out.println("height="+height);  
            //图像宽度  
//          width = this.raf.readInt();  
            width = this.mbbi.getInt();  
            //System.out.println("width="+width);  
            //图像深度（每个通道的颜色位数）  
//          short depth = this.raf.readShort();  
            short depth = this.mbbi.getShort();  
            //System.out.println("depth="+depth);  
            //是rgb模式则type=3  
//          short type = this.raf.readShort();  
            short type = this.mbbi.getShort();  
            //System.out.println("type="+type);  
            //--------第二部分：色彩模式信息，这部分的长度通常为0,psd 颜色映射表----  
//          int lenOfColorModel = raf.readInt();  
            int lenOfColorModel = this.mbbi.getInt();  
            //System.out.println("lenOfColorModel="+lenOfColorModel);  
//          this.raf.seek(lenOfColorModel+this.raf.getFilePointer());//长度信息占4个字节，但是不用加,下同  
            this.mbbi.position(lenOfColorModel+this.mbbi.position());  
            //--------第三部分：图像资源数据------------------  
//          int lenOfImageResourceBlock = raf.readInt();  
            int lenOfImageResourceBlock = this.mbbi.getInt();  
            //System.out.println("lenOfImageResourceBlock="+lenOfImageResourceBlock);  
//          this.raf.seek(lenOfImageResourceBlock+this.raf.getFilePointer());  
            this.mbbi.position(lenOfImageResourceBlock+this.mbbi.position());  
            //--------第四部分：图层与蒙版信息----------------  
//          int lenOfLayerInfo = raf.readInt();  
            int lenOfLayerInfo = this.mbbi.getInt();  
            //System.out.println("lenOfLayer="+lenOfLayerInfo);  
//          this.raf.seek(lenOfLayerInfo+raf.getFilePointer());  
            this.mbbi.position(lenOfLayerInfo+this.mbbi.position());  
            //--------第五部分：图像数据--------------------  
//          isRle = raf.readShort();
            System.out.println(this.mbbi.getInt());
            isRle = this.mbbi.getShort();  
            //System.out.println("isRle="+isRle);  
//          //System.out.println("nowPosition="+this.raf.getFilePointer());  
            //System.out.println("nowPosition="+this.mbbi.position());  
              
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
  
        this.channelColor = new int[numOfChannel][height][width];  // 每条通道的颜色
        if(isRle==1){  
        this.numOfBytePerLine = new int[numOfChannel][height];  
	        for(int i=0; i<numOfChannel; i++) {  
	            for(int j=0; j<height; j++) {  
	                try {  
	                    //TODO  
	                    //this.numOfBytePerLine[i][j] = this.raf.readUnsignedShort();  
	                    int ti = this.mbbi.getShort();  
	                    if(ti<0) { ti += 65536; }  
	                    this.numOfBytePerLine[i][j] = ti;  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        for(int c=0; c<numOfChannel; c++) {  
	            for(int h=0; h<height; h++) {  
	                this.unpackbits(numOfBytePerLine[c][h],channelColor[c][h]);  
	            }  
	        }  
        }else if(isRle==0) {  
            for(int c=0; c<numOfChannel; c++) {  
                for(int h=0; h<height; h++) {  
                    for(int w=0; w<width; w++) {  
                        try {  
//                          this.channelColor[c][h][w] = this.raf.readUnsignedByte();  
                            int ti = this.mbbi.get();  
                            if(ti<0) { ti += 256; }  
                            this.channelColor[c][h][w] = ti;  
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            }  
        }  
    }  
      
    private void unpackbits(int lenOfInput, int[] channelColor) {  
        short n = 0;  
        int last = 0;  
          
        while(lenOfInput>0){  
        try {  
//          n = raf.readByte();  
            n = this.mbbi.get();  
            lenOfInput--;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        if(0<=n && n<=127) {  
            int repeatTime = n;  
            ++repeatTime;  
            for(int t=0; t<repeatTime; t++) {  
                try {  
//                  channelColor[last+t] = raf.readUnsignedByte();  
                    int ti = this.mbbi.get();  
                    if(ti<0) { ti += 256; }  
                    channelColor[last+t] = ti;  
                      
                    lenOfInput--;  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            last += repeatTime;  
        }  
        else if(-1>=n && n>=-127) {  
            int val = 0;  
            int repeatTime = -n;  
            ++repeatTime;  
            try {  
//              val = raf.readUnsignedByte();  
                int ti = this.mbbi.get();  
                if(ti<0) { ti += 256; }  
                val = ti;  
                //System.out.println(val);  
                lenOfInput--;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            for(int t=0; t<repeatTime; t++) {  
                    channelColor[last+t] = val;  
            }  
            last += repeatTime;  
        }  
        else if(n==-128) {  
            //noop  
        }  
        }  
    }  
}  

