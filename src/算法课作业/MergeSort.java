package 算法课作业;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class MergeSort {
	private List<Integer> array;
	private int length;
	
	@Override
	public String toString() {
		return "MergeSort array=" + array ;
	}
	
	public MergeSort(Scanner in) {
		List<Integer> array=new ArrayList<>();
		System.out.println("Please input the length of the array.");
		int length=in.nextInt();
		System.out.println("Please input the numbers.");
		for(int i=0;i<length;i++) {
			int j=in.nextInt();
			array.add(j);
		}
		this.array = array;
		this.length=length;
	}
	
	public void merge(int low,int mid,int high) {
		List<Integer> array=this.array;
		List<Integer> subArray=new ArrayList<>();//临时向量
		int j=low,k=mid;  
		while(j<mid&&k<high){
			if(array.get(j)<array.get(k)) 
				subArray.add(array.get(j++));
			else 
				subArray.add(array.get(k++));
		}
		//左/右剩余元素
		while(j<mid){
			subArray.add(array.get(j++));
		}
		while(k<high){
			subArray.add(array.get(k++));
		}
		//将临时向量添加回去
		for(int m=high-1;m>=low;m--)
			array.remove(m);
		array.addAll(low,subArray);
	}
	
	//对左闭右开区间排序
	public void mergeSort(int low,int high) {
		//当元素有1个及以上时
		if(high-low>1) {
			int mid=(high+low)/2;
			mergeSort(low,mid);
			mergeSort(mid,high);
			merge(low,mid,high);
		}
	}
	
	public static void main(String[] args) {
		try(Scanner in=new Scanner(System.in))
		{
			MergeSort text=new MergeSort(in);
			text.mergeSort(0, text.length);
			System.out.println(text);
		}
	}
}
