package com.wxx.constant;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FanXing {

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		// List 默认类型为 Object[]
		List list = new ArrayList();
		list.add("a");
		list.add(100);
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			System.out.println(">>" + i + ">>" + list.get(i));
		}
		// <String> 就是泛型的定义
		// 泛型 只在 编译期 使用
		ArrayList<String> a = new ArrayList<String>();
		Class c1 = a.getClass();

		ArrayList b = new ArrayList();
		Class c2 = b.getClass();
		// 反射都是在运行时期的 运行时期已经相等 说明 在编译期已经采取 去泛型化的操作
		System.out.println("c1==c2 " + (c1 == c2));

		// 验证 泛型只存在于 编译期
		a.add("weixx");
		Class c3 = a.getClass();
		try {
			Method method = c3.getMethod("add", Object.class);
			method.invoke(a, 100);
			System.out.println(">>a>all>>>>" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 泛型方法
		// 使用和不使用泛型的两个方法的比对
		Fx1<Integer> fx1 = new Fx1<Integer>(100);
		fx1.showType();

		Fx1<String> fx2 = new Fx1<String>("一百");
		fx2.showType();

		Fx2 fx3 = new Fx2(100);
		fx3.showType();

		Fx2 fx4 = new Fx2("一百");
		fx4.showType();

		// 通配符 ?
		List<Integer> ex_int = new ArrayList<Integer>();
		// 编译报错
		// List<Number> ex_num = ex_int;
		Fx1<Number> fx5 = new Fx1<Number>(100);
		Fx1<Integer> fx6 = new Fx1<Integer>(200);
		getData(fx5);
		// 编译报错 取决于 getData() 入参类型 使用通配符 可以是程序更通用
		getData(fx6);

		// 上下边界
		Fx1<Number> fx7 = new Fx1<Number>(100);
		Fx1<Integer> fx8 = new Fx1<Integer>(200);
		Fx1<String> fx9 = new Fx1<String>("一百");
		getUpperNumberData(fx7);
		getUpperNumberData(fx8);
		//编译报错 超过了边界
		//getUpperNumberData(fx9);
		//编译报错 不允许创建 泛型类型明确的数组
		//List<String>[] aa = new List<String>[10];
		List<?>[] aa = new List<?>[10];

	}

	public static void getUpperNumberData(Fx1<? extends Number> temp) {
		System.out.println("我很喜欢西安这个城市" + temp);
	}

	private static void getData(Fx1<?> temp) {
		System.out.println("我很喜欢西安这个城市" + temp);
	}

	public static class Fx1<T> {
		private T ob;

		public T getOb() {
			return ob;
		}

		public void setOb(T ob) {
			this.ob = ob;
		}

		public Fx1(T ob) {
			this.ob = ob;
		}

		public void showType() {
			System.out.println("T 的实际类型是:" + ob.getClass().getName());
		}
	}

	public static class Fx2 {
		private Object ob;

		public Object getOb() {
			return ob;
		}

		public void setOb(Object ob) {
			this.ob = ob;
		}

		public Fx2(Object ob) {
			this.ob = ob;
		}

		public void showType() {
			System.out.println("T 的实际类型是:" + ob.getClass().getName());
		}
	}
}
