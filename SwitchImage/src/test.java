
public class test {
	static int i=1;
	static{
	i=0;//��������ֵ������������ͨ��
	}
	
	{
		System.out.println(123+"");
	}
	public static void main(String[] args) {
		new test();
		System.out.println(i);//������������ʾ"�Ƿ���ǰ����"
		new test();
	}
	
}
