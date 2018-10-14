package GUIInterface;

import java.awt.Color;
import java.awt.Frame;

public class Demo02 {

    public static void main(String[] args) {
        Frame frame = new Frame("我的第一个窗口");
        frame.setBounds(50, 50, 300, 200);//设置Frame 的大小,距离windows界面上左均为50，宽和高分别为300 200
        frame.setBackground(Color.PINK);
        frame.setVisible(true);//设置Frame为可见

    }

}
