package GUIInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.CreatePs;
import main.vlidateSourceFile;
import main.vlidateTargetFile;


public class PsGui extends JFrame implements ActionListener{
	
	int partNum ;
	JButton open=null;  //上传文件
	JFileChooser jfc =null;
	JTextField jft = null;
	JTextField jft1 = null;
	JButton vliSource = null;
	JButton vliTarget = null;
	JButton exePsButton =null;
	JLabel lab = null;
	public static void main(String[] args) {
		PsGui psGui = new PsGui();
	};
	
	
	//构造方法
	public PsGui(){
		partNum= 5;
		this.setTitle("PS文件验证");
		this.setBounds(50, 50, 300, 250);//设置Frame 的大小,距离windows界面上左均为50，宽和高分别为300 200
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		addFile();
		addbutton();
		addText();
		this.setVisible(true);
	};
	
	//添加文件选择
	public void addFile(){
		//添加文字选择按钮
		open=new JButton("open");
		//初始化透明按钮
		open.setBorder(null);
		open.setFocusable(false);
		open.setContentAreaFilled(true);//设置填充是否透明
		open.setBounds(10, 20, 50, 20);
        this.add(open); 
        //添加文本显示狂
        jft = new JTextField("选择Excel校验文件");
        jft.setEnabled(true);
        jft.setBounds(70, 20, 200,20);
        this.add(jft); 
        open.addActionListener(this);
        
	};

	//添加切割份数
	public void addText(){
		lab = new JLabel("切割份数(正整数):");
		lab.setBorder(null);
		lab.setFocusable(false);
		lab.setBounds(10, 60,150, 20);
        this.add(lab); 
      //添加文本显示狂
        jft1 = new JTextField(""+partNum);
        jft1.setEnabled(true);
        jft1.setBounds(120, 60, 50,20);
        this.add(jft1); 
		
	}
	
	
	// 添加校验按钮
	public void addbutton(){
		//校验原文件
		vliSource=new JButton("校验原文件");
		//初始化透明按钮
		vliSource.setBorder(null);
		vliSource.setFocusable(false);
		vliSource.setContentAreaFilled(true);//设置填充是否透明
		vliSource.setBounds(100, 100, 80, 20);
        this.add(vliSource);
        vliSource.addActionListener(new VliateSource());
        
        //执行ps切图
        exePsButton = new JButton("执行ps切图");
      //初始化透明按钮
        exePsButton.setBorder(null);
        exePsButton.setFocusable(false);
        exePsButton.setContentAreaFilled(true);//设置填充是否透明
        exePsButton.setBounds(100, 140, 80, 20);
	    this.add(exePsButton);
	    exePsButton.addActionListener(new ExePsCut());
        
        //校验原文件
        vliTarget=new JButton("校验目标文件");
		//初始化透明按钮
        vliTarget.setBorder(null);
        vliTarget.setFocusable(false);
        vliTarget.setContentAreaFilled(true);//设置填充是否透明
        vliTarget.setBounds(100, 180, 80, 20);
	    this.add(vliTarget);
	    vliTarget.addActionListener(new VliateTarget());
	};
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub  
        jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  //指定仅仅显示文件
        jfc.setCurrentDirectory(jfc.getSelectedFile());
        int i= jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile(); 
        if(i==jfc.APPROVE_OPTION){
        	String pattern =".*?(\\.xls|\\.xlsx){1}$";
    		if(Pattern.matches(pattern, file.getAbsolutePath())){
    			jft.setText(file.getAbsolutePath());
    		}else{
    			JOptionPane.showMessageDialog(this, "请选择Excel类型文件");
    		}
        	
        };
        
	}
	
	class VliateSource implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String excelpthString =jft.getText();
			String pattern = ".*?(\\.xls|\\.xlsx){1}$";
    		if(Pattern.matches(pattern, excelpthString)){
    			String logpath =vlidateSourceFile.vlitateSource(jft.getText());
    			try {
    				//打开日志文件
    				Runtime.getRuntime().exec("NotePad.exe "+logpath);
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}else{
    			JOptionPane.showMessageDialog(null, "请选择正确的文件类型");
    		}
			
			
		}
		
	};
	
	class VliateTarget implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String excelpthString =jft.getText();
			String pattern = ".*?(\\.xls|\\.xlsx)$";
    		if(Pattern.matches(pattern, excelpthString)){
    		}else{
    			JOptionPane.showMessageDialog(null, "请选择正确的文件类型");
    		}
			
		}
		
	}
	
	class ExePsCut implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String excelpthString =jft.getText();
			String parentpath =excelpthString.substring(0,excelpthString.lastIndexOf("\\"));
			
			String numstr =jft1.getText();
			String pattern = "^([1-9]|10)$";
    		if(!Pattern.matches(pattern, numstr)){
    			JOptionPane.showMessageDialog(null, "请输入1~10之间的正整数");
    			jft1.setText(""+partNum);
    			return ;
    		}else{
    			partNum = Integer.parseInt(jft1.getText());
    		};
			
			CreatePs.CreatePsShell(parentpath, partNum);
			String cmd ="python "+parentpath+"\\userPs.py";
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		
	}
}
