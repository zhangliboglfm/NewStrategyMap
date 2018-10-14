package GUIInterface;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class SwingLoginExample {
	
	public static void main(String[] args) {
		
		//����frameʵ��
		JFrame frame = new JFrame("Lgoin Example");
		//����ʾ���ϱ�Ե300���أ�����ʾ�����Ե300����
		frame.setLocation(300,300); 
		//���ô���Ĵ�СΪ300*200���ش�С
		frame.setSize(350, 200);
		
		//frame.setBounds(50, 50, 300, 200);//����Frame �Ĵ�С,����windows���������Ϊ50����͸߷ֱ�Ϊ300 200
        //frame.setBackground(Color.RED);

		
		//���ô����Ƿ���Ե�����С������Ϊ����ֵ
		frame.setResizable(false);       
		 
		//�û��������ڵĹرհ�ťʱ����ִ�еĲ���
		//��0����DO_NOTHING_ON_CLOSE
		//��1����HIDE_ON_CLOSE
		//��2����DISPOSE_ON_CLOSE
		//��3��EXIT_ON_CLOSE
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  
		
		/* ������壬��������� HTML �� div ��ǩ
         * ���ǿ��Դ��������岢�� JFrame ��ָ��λ��
         * ��������ǿ�������ı��ֶΣ���ť�����������
         */
        JPanel panel = new JPanel();    
        // ������
        frame.add(panel);
        /* 
         * �����û�����ķ����������������
         */
        placeComponents(panel);
        
        
      //���ô���ɼ���û�и���䣬���彫���ɼ������������У�����û�н����û�����������
      frame.setVisible( true);  
	}
	
	 private static void placeComponents(JPanel panel) {

        /* ���ֲ���������߲���������
         * ������ò���Ϊ null
         */
        panel.setLayout(null);

        // ���� JLabel
        JLabel userLabel = new JLabel("User:");
        /* ������������������λ�á�
         * setBounds(x, y, width, height)
         * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* 
         * �����ı��������û�����
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // ����������ı���
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /* 
         *�����������������ı���
         * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // ������¼��ť
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

	
	
}
