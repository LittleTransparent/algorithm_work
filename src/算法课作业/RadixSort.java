package �㷨����ҵ;

import java.util.*;

//��ASCII�������賤�ȳ��ıȶ̵Ĵ��������ֱȴ�С��
public class RadixSort {
	private int listnum;//�ַ���������
	private int maxLength;//����Ĵ�С
	private List<String> slist;
	
	public RadixSort() {
		this.listnum=0;
		this.maxLength=0;
		this.slist=new ArrayList<>();
	}

	//���ַ�����������������ʼ���������
	public RadixSort(int listnum,Scanner in) {
		List<String> slist=new ArrayList<>();
		int length=0;
		for(int i=0;i<listnum;i++) {
			System.out.print("Input the string:");
			String s=in.nextLine();
			slist.add(s);
			//����forѭ���ڣ�ɸѡ�����С
			if(length<s.length()) {
				length=s.length();
			}
		}
		this.listnum=listnum;
		this.maxLength=length;
		this.slist=slist;
	}
	
	//�ж���󴮳����Ƿ�С��mixLength
	public boolean mixMaxLength(int mixLength) {
		if(this.maxLength<mixLength)
			return false;
		else return true;
	}
	
	public void sort(int capacity) {
		for(int i=0;i<this.maxLength;i++) {
			List<List<String>> list=new ArrayList<>();//Ԥ��һ��������������Ľ϶��ַ���
			for (int j=0;j<capacity+1;j++) {
				List<String> sublist= new ArrayList<String>();
				list.add(sublist);
			}
			while(!this.slist.isEmpty()) {
				String a=this.slist.remove(0);
				//���ȴ���i����Ҫ����
				if(a.length()>i) {
					int n=a.charAt(a.length()-1-i);
					//С��58��Ϊ���֣���48Ϊԭ�������ڵ�Ϊ��ĸ����87����10��ʼ����
					int j=(n<58)?n-48:n-87;
					list.get(j+1).add(a);//��ΪԤ����һ�������Բμ�����Ķ�Ҫ������һ����������j+1
				}
				//����ֱ�ӽ���Ԥ��λ
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
		//�ձ�
		RadixSort r=new RadixSort();
		//����󴮳��ȷ���Ҫ��ʱ����ɱ�ĳ�ʼ��
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
