package GUIInterface;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class Demo01 {

    public static void main(String[] args) {
    	Frame frame = new Frame("我的panel 界面");
        frame.setBounds(50, 50, 500, 300);
        Panel panel = new Panel();
        panel.setBackground(Color.pink);
        panel.setBounds(0, 0, 200, 100); //如果要用 panel将整个窗口分块，则要用到布局管理器，不可直接调用实现
        
        frame.add(panel);
        frame.setVisible(true);


    }

}
