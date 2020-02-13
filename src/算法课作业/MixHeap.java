package 算法课作业;

import java.util.*;

//此段代码中，得到或赋值的元素位置均为从0开始计数，故处理方法与从1计数略有不同
public class MixHeap {
	@Override
	public String toString() {
		return "This mixHeap's array=[" + array + "]";
	}

	private int length=0;
	private List<Integer> array;
	
	//以长度为length的随机数列创建堆（是否为堆还不确定）
	public MixHeap(int length) {
		Random rand = new Random(100);
		List<Integer> array = new ArrayList<>() ;
		for(int i=0;i<length;i++) {
			array.add(rand.nextInt(100));
		}
		this.length=length;
		this.array=array;
	}
	
	//判断是否为最小堆
	public boolean isMixHeap() throws Exception {
		if(this.length<1) {
			throw new Exception("The heap is empty");
		}
		//若只有根，判断为是最小堆
		if(this.length==1) {
			return true;
		}
		//父节点大于子节点则非最小堆
		for(int i=0;i<this.length/2;i++) {
			if(this.array.get(i)>this.array.get(2*i+1)||this.array.get(i)>this.array.get(2*i+2)) 
				return false;
		}
		//父节点均判断而没有退出，为最小堆
		return true;
	}
	
	private void siftUp(int i) {
		while(i>0) {
			//小的上升，大的下放
			if(this.array.get(i)<this.array.get((i+1)/2-1)) {
				int a=this.array.get(i);
				this.array.set(i, this.array.get((i+1)/2-1));
				this.array.set((i+1)/2-1, a);
			}
			else break;
			i=(i+1)/2-1;
		}
	}
	
	private void siftDown(int i) {
		while(2*(i+1)<=this.length) {
			i=2*i+1;
			//第i+1个的数小于第i个数的话，选择第i+1个数作为候选
			if(i+1<=this.length-1&&this.array.get(i)>this.array.get(i+1)) {
				i=i+1;
			}
			//如果父节点大于第i/i+1个数中最小的数，下降
			if(this.array.get((i+1)/2-1)>this.array.get(i)) {
				int a=this.array.get(i);
				this.array.set(i, this.array.get((i+1)/2-1));
				this.array.set((i+1)/2-1, a);
			}
			else break;
		}
	}
	
	//给未形成heap的Arraylist进行堆排序
	public void makeHeap() {
		for(int i=this.length/2-1;i>=0;i--) {
			this.siftDown(i);
		}
	}
	
	//转换为最大堆
	private void siftDownAsMax(int i) {
		if(2*(i+1)>this.length) {
			System.out.println("illegality or useless node.");
		}
		while(2*(i+1)<=this.length) {
			i=2*i+1;
			//第i+1个的数大于第i个数的话，选择第i+1个数作为候选
			if(i+1<=this.length-1&&this.array.get(i)<this.array.get(i+1)) {
				i=i+1;
			}
			//如果父节点小于第i/i+1个数中最大的数，下降
			if(this.array.get((i+1)/2-1)<this.array.get(i)) {
				int a=this.array.get(i);
				this.array.set(i, this.array.get((i+1)/2-1));
				this.array.set((i+1)/2-1, a);
			}
			else break;
		}
	}
	
	public void makeHeapAsMax() {
		for(int i=this.length/2-1;i>=0;i--) {
			this.siftDownAsMax(i);
		}
	}
	
	//加入大小为num的元素
	public void insert(int num) {
		this.length++;
		this.array.add(num);
		this.siftUp(this.length-1);
	}
	
	//删除第index个元素
	public void delete(int index) {
		if(index+1>this.length)
			System.out.println("The index is not illegality.");
		else {
			int x=this.array.remove(index);
			this.length--;
			//index若为最后一位，直接退出，否则执行下面代码
			if(index!=this.length+1) {
				int y=this.array.remove(length-1);
				this.array.add(index,y);
				if(y<x) 
					this.siftUp(index);
				else
					this.siftDown(index);
			}
		}
	}
	
	//主函数
	public static void main(String[] args) throws Exception {
		try(Scanner scan=new Scanner(System.in))
		{
			System.out.print("Please import the size of the heap:");
			int length=scan.nextInt();
			MixHeap heap=new MixHeap(length);

			System.out.println("The array is heap:"+heap.isMixHeap());
			if(!heap.isMixHeap()) {
				System.out.println("before sort:"+heap);
				heap.makeHeap();
			}
			System.out.println("after sort:"+heap);
			System.out.println("\n");

			System.out.print("Please imput how many numbers you want to insert:");
			int supInsert=scan.nextInt();
			for(int i=0;i<supInsert;i++) {
				System.out.print("Please imput the number that you want to insert:");
				heap.insert(scan.nextInt());System.out.println(heap);
			}
			System.out.print("Please imput how many numbers you want to delete:");
			int supDelete=scan.nextInt();
			for(int i=0;i<supDelete;i++) {
				System.out.print("Please imput the index that you want to delete:");
				heap.delete(scan.nextInt());System.out.println(heap);
			}
			System.out.println("\n");

			System.out.print("sort as maxheap:");
			heap.makeHeapAsMax();
			System.out.println(heap);
		}
	}
}
