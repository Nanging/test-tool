package testclass;

public class Triangle {
	public static String triangle(float a,float b,float c) {
		System.out.print("输入:"+ a + " " + b + " " + c + "  输出:");
		if(a <= 0 || b <= 0 || c <= 0) {
			System.out.println("a、b、c必须大于0");
			return "a、b、c必须大于0";
		}
		if(a > 1000 || b > 1000 || c > 1000) {
			System.out.println("a、b、c应该小于等于1000");
			return "a、b、c应该小于等于1000"; 
		}
		if(a+b <= c || b+c <= a || a+c <= b) {
			System.out.println("非三角形");
			return "非三角形";
		}
		if(a == b && a == c) {
			System.out.println("等边三角形");
			return "等边三角形";
		}
		if(a == b || b == c || a == c) {
			System.out.println("等腰三角形");
			return "等腰三角形";
		}
		System.out.println("一般三角形");
		return "一般三角形";
	}
}

