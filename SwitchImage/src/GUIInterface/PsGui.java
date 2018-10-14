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
	JButton open=null;  //�ϴ��ļ�
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
	
	
	//���췽��
	public PsGui(){
		partNum= 5;
		this.setTitle("PS�ļ���֤");
		this.setBounds(50, 50, 300, 250);//����Frame �Ĵ�С,����windows���������Ϊ50����͸߷ֱ�Ϊ300 200
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		addFile();
		addbutton();
		addText();
		this.setVisible(true);
	};
	
	//����ļ�ѡ��
	public void addFile(){
		//�������ѡ��ť
		open=new JButton("open");
		//��ʼ��͸����ť
		open.setBorder(null);
		open.setFocusable(false);
		open.setContentAreaFilled(true);//��������Ƿ�͸��
		open.setBounds(10, 20, 50, 20);
        this.add(open); 
        //����ı���ʾ��
        jft = new JTextField("ѡ��ExcelУ���ļ�");
        jft.setEnabled(true);
        jft.setBounds(70, 20, 200,20);
        this.add(jft); 
        open.addActionListener(this);
        
	};

	//����и����
	public void addText(){
		lab = new JLabel("�и����(������):");
		lab.setBorder(null);
		lab.setFocusable(false);
		lab.setBounds(10, 60,150, 20);
        this.add(lab); 
      //����ı���ʾ��
        jft1 = new JTextField(""+partNum);
        jft1.setEnabled(true);
        jft1.setBounds(120, 60, 50,20);
        this.add(jft1); 
		
	}
	
	
	// ���У�鰴ť
	public void addbutton(){
		//У��ԭ�ļ�
		vliSource=new JButton("У��ԭ�ļ�");
		//��ʼ��͸����ť
		vliSource.setBorder(null);
		vliSource.setFocusable(false);
		vliSource.setContentAreaFilled(true);//��������Ƿ�͸��
		vliSource.setBounds(100, 100, 80, 20);
        this.add(vliSource);
        vliSource.addActionListener(new VliateSource());
        
        //ִ��ps��ͼ
        exePsButton = new JButton("ִ��ps��ͼ");
      //��ʼ��͸����ť
        exePsButton.setBorder(null);
        exePsButton.setFocusable(false);
        exePsButton.setContentAreaFilled(true);//��������Ƿ�͸��
        exePsButton.setBounds(100, 140, 80, 20);
	    this.add(exePsButton);
	    exePsButton.addActionListener(new ExePsCut());
        
        //У��ԭ�ļ�
        vliTarget=new JButton("У��Ŀ���ļ�");
		//��ʼ��͸����ť
        vliTarget.setBorder(null);
        vliTarget.setFocusable(false);
        vliTarget.setContentAreaFilled(true);//��������Ƿ�͸��
        vliTarget.setBounds(100, 180, 80, 20);
	    this.add(vliTarget);
	    vliTarget.addActionListener(new VliateTarget());
	};
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub  
        jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  //ָ��������ʾ�ļ�
        jfc.setCurrentDirectory(jfc.getSelectedFile());
        int i= jfc.showDialog(new JLabel(), "ѡ��");  
        File file=jfc.getSelectedFile(); 
        if(i==jfc.APPROVE_OPTION){
        	String pattern =".*?(\\.xls|\\.xlsx){1}$";
    		if(Pattern.matches(pattern, file.getAbsolutePath())){
    			jft.setText(file.getAbsolutePath());
    		}else{
    			JOptionPane.showMessageDialog(this, "��ѡ��Excel�����ļ�");
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
    				//����־�ļ�
    				Runtime.getRuntime().exec("NotePad.exe "+logpath);
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}else{
    			JOptionPane.showMessageDialog(null, "��ѡ����ȷ���ļ�����");
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
    			JOptionPane.showMessageDialog(null, "��ѡ����ȷ���ļ�����");
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
    			JOptionPane.showMessageDialog(null, "������1~10֮���������");
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
