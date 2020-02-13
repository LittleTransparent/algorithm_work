package �㷨����ҵ;

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

	//��length���������ʼ��
	public MyQuickSort(int length) {
		Random r=new Random(100);
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<length;i++) {
			list.add(r.nextInt(100));
		}
		this.length=length;
		this.array=list;
	}
	
	//���ػ��������Ԫ��λ��
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
	
	//����
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
			System.out.print("�����볤�ȣ�");
			//length>100��mqs��array��ֵ���˳�ѭ��
			while(true) {
				int length=scan.nextInt();
				if(length<100) {
					System.out.print("����̫С�����������룺");
				}
				else {
					mqs=new MyQuickSort(length);
					break;
				}
			}
			System.out.println("����ǰ��");
			System.out.println(mqs);
			mqs.quickSort(0,mqs.length-1);
			System.out.println("\n");
			System.out.println("�����");
			System.out.println(mqs);
		}
	}
}
