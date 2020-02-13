package �㷨����ҵ;

import java.util.*;

//�˶δ����У��õ���ֵ��Ԫ��λ�þ�Ϊ��0��ʼ�������ʴ��������1�������в�ͬ
public class MixHeap {
	@Override
	public String toString() {
		return "This mixHeap's array=[" + array + "]";
	}

	private int length=0;
	private List<Integer> array;
	
	//�Գ���Ϊlength��������д����ѣ��Ƿ�Ϊ�ѻ���ȷ����
	public MixHeap(int length) {
		Random rand = new Random(100);
		List<Integer> array = new ArrayList<>() ;
		for(int i=0;i<length;i++) {
			array.add(rand.nextInt(100));
		}
		this.length=length;
		this.array=array;
	}
	
	//�ж��Ƿ�Ϊ��С��
	public boolean isMixHeap() throws Exception {
		if(this.length<1) {
			throw new Exception("The heap is empty");
		}
		//��ֻ�и����ж�Ϊ����С��
		if(this.length==1) {
			return true;
		}
		//���ڵ�����ӽڵ������С��
		for(int i=0;i<this.length/2;i++) {
			if(this.array.get(i)>this.array.get(2*i+1)||this.array.get(i)>this.array.get(2*i+2)) 
				return false;
		}
		//���ڵ���ж϶�û���˳���Ϊ��С��
		return true;
	}
	
	private void siftUp(int i) {
		while(i>0) {
			//С������������·�
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
			//��i+1������С�ڵ�i�����Ļ���ѡ���i+1������Ϊ��ѡ
			if(i+1<=this.length-1&&this.array.get(i)>this.array.get(i+1)) {
				i=i+1;
			}
			//������ڵ���ڵ�i/i+1��������С�������½�
			if(this.array.get((i+1)/2-1)>this.array.get(i)) {
				int a=this.array.get(i);
				this.array.set(i, this.array.get((i+1)/2-1));
				this.array.set((i+1)/2-1, a);
			}
			else break;
		}
	}
	
	//��δ�γ�heap��Arraylist���ж�����
	public void makeHeap() {
		for(int i=this.length/2-1;i>=0;i--) {
			this.siftDown(i);
		}
	}
	
	//ת��Ϊ����
	private void siftDownAsMax(int i) {
		if(2*(i+1)>this.length) {
			System.out.println("illegality or useless node.");
		}
		while(2*(i+1)<=this.length) {
			i=2*i+1;
			//��i+1���������ڵ�i�����Ļ���ѡ���i+1������Ϊ��ѡ
			if(i+1<=this.length-1&&this.array.get(i)<this.array.get(i+1)) {
				i=i+1;
			}
			//������ڵ�С�ڵ�i/i+1���������������½�
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
	
	//�����СΪnum��Ԫ��
	public void insert(int num) {
		this.length++;
		this.array.add(num);
		this.siftUp(this.length-1);
	}
	
	//ɾ����index��Ԫ��
	public void delete(int index) {
		if(index+1>this.length)
			System.out.println("The index is not illegality.");
		else {
			int x=this.array.remove(index);
			this.length--;
			//index��Ϊ���һλ��ֱ���˳�������ִ���������
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
	
	//������
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
