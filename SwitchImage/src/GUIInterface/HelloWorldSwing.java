package GUIInterface;

import javax.swing.*;
 
public class HelloWorldSwing {
	
	/**
     * ��������ʾGUI�������̰߳�ȫ�Ŀ��ǣ�
     * ����������¼������߳��е��á�
     */
	
	private static void createAndShowGUI(){
		//ȷ����һ��Ư������۷��
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//�����Լ���ƴ���
		JFrame frame = new JFrame("Hellow World!");
		//�û��������ڵĹرհ�ťʱ����ִ�еĲ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ��� ��Hellow World!�� ��ǩ
		JLabel label = new JLabel("Hello WorldSwing");
		frame.getContentPane().add(label);
		
		//��ʾ����
		frame.pack();
		frame.setVisible(true);
			
	}
	
	public static void main(String[] args) {
		//��ʾӦ��GUI
		createAndShowGUI();
	}
	
}
