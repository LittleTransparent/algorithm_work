package 算法课作业;

import java.util.*;

//按ASCII表排序，设长度长的比短的大（类似数字比大小）
public class RadixSort {
	private int listnum;//字符串的数量
	private int maxLength;//最长串的大小
	private List<String> slist;
	
	public RadixSort() {
		this.listnum=0;
		this.maxLength=0;
		this.slist=new ArrayList<>();
	}

	//用字符串数量和输入流初始化待排序表
	public RadixSort(int listnum,Scanner in) {
		List<String> slist=new ArrayList<>();
		int length=0;
		for(int i=0;i<listnum;i++) {
			System.out.print("Input the string:");
			String s=in.nextLine();
			slist.add(s);
			//跟在for循环内，筛选最长串大小
			if(length<s.length()) {
				length=s.length();
			}
		}
		this.listnum=listnum;
		this.maxLength=length;
		this.slist=slist;
	}
	
	//判断最大串长度是否不小于mixLength
	public boolean mixMaxLength(int mixLength) {
		if(this.maxLength<mixLength)
			return false;
		else return true;
	}
	
	public void sort(int capacity) {
		for(int i=0;i<this.maxLength;i++) {
			List<List<String>> list=new ArrayList<>();//预留一个，储存排完序的较短字符串
			for (int j=0;j<capacity+1;j++) {
				List<String> sublist= new ArrayList<String>();
				list.add(sublist);
			}
			while(!this.slist.isEmpty()) {
				String a=this.slist.remove(0);
				//长度大于i的需要排序
				if(a.length()>i) {
					int n=a.charAt(a.length()-1-i);
					//小于58的为数字，减48为原数；大于的为字母，减87，从10开始计数
					int j=(n<58)?n-48:n-87;
					list.get(j+1).add(a);//因为预留了一个，所以参加排序的都要向下移一个，所以是j+1
				}
				//否则直接进入预留位
				else {
					list.get(0).add(a);
				}
			}
			this.slist=list.get(0);
			for(int k=1;k<=capacity;k++) {
				this.slist.addAll(list.get(k));
			}
		}
	}
	
	@Override
	public String toString() {
		return "RadixSort slist=[" + slist + "]";
	}
		
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		//空表
		RadixSort r=new RadixSort();
		//当最大串长度符合要求时，完成表的初始化
		boolean tof=false;
		while(!tof) {
			System.out.println("Please input the mix length of largest strings.");
			int mixLength=in.nextInt();
			System.out.println("Please input the number of strings.");
			int listnum=in.nextInt();
			String enter=in.nextLine();
			r=new RadixSort(listnum,in);
			tof=r.mixMaxLength(mixLength);
		}
		r.sort(36);
		System.out.println(r);
	}
}
