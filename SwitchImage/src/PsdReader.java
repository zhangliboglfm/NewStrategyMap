import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;  
 
/**
 * ʹ����FileChannel��MappedByteBuffer�ӿ��ȡ�ٶȣ������RandomAccessFileҲ�ǿ��Դﵽͬ��Ŀ�ģ�����Ч�ʹ��͡�
 * @author Administrator
 *
 */
public class PsdReader {  
  
	private BufferedImage img = null;//���ջ�õ�Ŀ��ͼƬ  
	private int[] pixels;  
	private RandomAccessFile raf;  
	private int[] byteArray;  
	private int[][][] channelColor;//ÿ��ͨ������ɫ��0��λ��red��1��λ��green��2��λ��blue�������4ͨ����alphaֱ������  
	private int[][] numOfBytePerLine;//ÿ�е��ֽ�����ɨ��ͼƬ��Ϣ��ʱ�������н��еģ���Ϊrleѹ���Ĺ�ϵ��ÿ�е��ֽ�������һ����ͬ  
	private short numOfChannel;//ͨ������Ŀ������Ǵ���͸���ȣ�����4ͨ��������ͨ����3ͨ��  
	private int height;  
	private int width;  
	private short isRle;//ѹ����ʽ����0��û��ѹ������1����rleѹ��  
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
        this.readFile(); //�ؼ�����ȡͼƬ�ļ��е���Ϣ 
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
            //-------��һ���֣��ļ�ͷ------------------  
            //ͨ������  
//          this.raf.seek(0x0c); 
        	System.out.println(this.mbbi.toString());
            this.mbbi.position(0x0c);  
//          numOfChannel = this.raf.readShort();  
            numOfChannel = this.mbbi.getShort();  
            //System.out.println("numOfChannel="+numOfChannel);  
            //ͼ��߶�  
//          height = this.raf.readInt();  
            height = this.mbbi.getInt();  
            //System.out.println("height="+height);  
            //ͼ����  
//          width = this.raf.readInt();  
            width = this.mbbi.getInt();  
            //System.out.println("width="+width);  
            //ͼ����ȣ�ÿ��ͨ������ɫλ����  
//          short depth = this.raf.readShort();  
            short depth = this.mbbi.getShort();  
            //System.out.println("depth="+depth);  
            //��rgbģʽ��type=3  
//          short type = this.raf.readShort();  
            short type = this.mbbi.getShort();  
            //System.out.println("type="+type);  
            //--------�ڶ����֣�ɫ��ģʽ��Ϣ���ⲿ�ֵĳ���ͨ��Ϊ0,psd ��ɫӳ���----  
//          int lenOfColorModel = raf.readInt();  
            int lenOfColorModel = this.mbbi.getInt();  
            //System.out.println("lenOfColorModel="+lenOfColorModel);  
//          this.raf.seek(lenOfColorModel+this.raf.getFilePointer());//������Ϣռ4���ֽڣ����ǲ��ü�,��ͬ  
            this.mbbi.position(lenOfColorModel+this.mbbi.position());  
            //--------�������֣�ͼ����Դ����------------------  
//          int lenOfImageResourceBlock = raf.readInt();  
            int lenOfImageResourceBlock = this.mbbi.getInt();  
            //System.out.println("lenOfImageResourceBlock="+lenOfImageResourceBlock);  
//          this.raf.seek(lenOfImageResourceBlock+this.raf.getFilePointer());  
            this.mbbi.position(lenOfImageResourceBlock+this.mbbi.position());  
            //--------���Ĳ��֣�ͼ�����ɰ���Ϣ----------------  
//          int lenOfLayerInfo = raf.readInt();  
            int lenOfLayerInfo = this.mbbi.getInt();  
            //System.out.println("lenOfLayer="+lenOfLayerInfo);  
//          this.raf.seek(lenOfLayerInfo+raf.getFilePointer());  
            this.mbbi.position(lenOfLayerInfo+this.mbbi.position());  
            //--------���岿�֣�ͼ������--------------------  
//          isRle = raf.readShort();
            System.out.println(this.mbbi.getInt());
            isRle = this.mbbi.getShort();  
            //System.out.println("isRle="+isRle);  
//          //System.out.println("nowPosition="+this.raf.getFilePointer());  
            //System.out.println("nowPosition="+this.mbbi.position());  
              
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
  
        this.channelColor = new int[numOfChannel][height][width];  // ÿ��ͨ������ɫ
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

