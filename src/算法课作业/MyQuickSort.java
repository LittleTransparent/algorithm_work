package 算法课作业;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyQuickSort {
	private int length;
	private List<Integer> array;
	
	@Override
	public String toString() {
		return "MyQuickSort length=" + length + "; array=" + array;
	}

	//用length个随机数初始化
	public MyQuickSort(int length) {
		Random r=new Random(100);
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<length;i++) {
			list.add(r.nextInt(100));
		}
		this.length=length;
		this.array=list;
	}
	
	//返回划分数组的元素位置
	public int split(int low,int high) {
		int i=low;
		int x=this.array.get(i);
		for(int j=low+1;j<=high;j++) {
			if(this.array.get(j)<=x) {
				i++;
				if(i!=j) {
					int t=this.array.get(i);
					this.array.set(i, this.array.get(j));
					this.array.set(j, t);
				}
			}
		}
		int t=this.array.get(i);
		this.array.set(i, this.array.get(low));
		this.array.set(low, t);
		int w=i;
		return w;
	}
	
	//排序
	public void quickSort(int low,int high) {
		if(low<high) {
			int w=split(low,high);
			quickSort(low,w-1);
			quickSort(w+1,high);
		}
	}
	
	
	public static void main(String[] args) {
		try(Scanner scan=new Scanner(System.in)){
			MyQuickSort mqs;
			System.out.print("请输入长度：");
			//length>100，mqs的array赋值，退出循环
			while(true) {
				int length=scan.nextInt();
				if(length<100) {
					System.out.print("长度太小，请重新输入：");
				}
				else {
					mqs=new MyQuickSort(length);
					break;
				}
			}
			System.out.println("排序前：");
			System.out.println(mqs);
			mqs.quickSort(0,mqs.length-1);
			System.out.println("\n");
			System.out.println("排序后：");
			System.out.println(mqs);
		}
	}
}
