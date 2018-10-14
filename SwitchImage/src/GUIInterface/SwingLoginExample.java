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
		
		//创建frame实例
		JFrame frame = new JFrame("Lgoin Example");
		//离显示屏上边缘300像素，里显示屏左边缘300像素
		frame.setLocation(300,300); 
		//设置窗体的大小为300*200像素大小
		frame.setSize(350, 200);
		
		//frame.setBounds(50, 50, 300, 200);//设置Frame 的大小,距离windows界面上左均为50，宽和高分别为300 200
        //frame.setBackground(Color.RED);

		
		//设置窗体是否可以调整大小，参数为布尔值
		frame.setResizable(false);       
		 
		//用户单击窗口的关闭按钮时程序执行的操作
		//“0”或DO_NOTHING_ON_CLOSE
		//“1”或HIDE_ON_CLOSE
		//“2”或DISPOSE_ON_CLOSE
		//“3”EXIT_ON_CLOSE
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);  
		
		/* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);
        
        
      //设置窗体可见，没有该语句，窗体将不可见，此语句必须有，否则没有界面就没有如何意义了
      frame.setVisible( true);  
	}
	
	 private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* 
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /* 
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

	
	
}
