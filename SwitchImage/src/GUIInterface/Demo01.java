package GUIInterface;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class Demo01 {

    public static void main(String[] args) {
    	Frame frame = new Frame("�ҵ�panel ����");
        frame.setBounds(50, 50, 500, 300);
        Panel panel = new Panel();
        panel.setBackground(Color.pink);
        panel.setBounds(0, 0, 200, 100); //���Ҫ�� panel���������ڷֿ飬��Ҫ�õ����ֹ�����������ֱ�ӵ���ʵ��
        
        frame.add(panel);
        frame.setVisible(true);


    }

}
