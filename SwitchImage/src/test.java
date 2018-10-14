
public class test {
	static int i=1;
	static{
	i=0;//给变量赋值可以正常编译通过
	}
	
	{
		System.out.println(123+"");
	}
	public static void main(String[] args) {
		new test();
		System.out.println(i);//这句编译器会提示"非法向前引用"
		new test();
	}
	
}
