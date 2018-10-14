package GUIInterface;

import javax.swing.*;
 
public class HelloWorldSwing {
	
	/**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
	
	private static void createAndShowGUI(){
		//确保有一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//创建以及设计窗口
		JFrame frame = new JFrame("Hellow World!");
		//用户单击窗口的关闭按钮时程序执行的操作
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 添加 “Hellow World!” 标签
		JLabel label = new JLabel("Hello WorldSwing");
		frame.getContentPane().add(label);
		
		//显示窗口
		frame.pack();
		frame.setVisible(true);
			
	}
	
	public static void main(String[] args) {
		//显示应用GUI
		createAndShowGUI();
	}
	
}
