package �㷨����ҵ;

public class EightQueen {
	private int N;//��ĿҪ��Ļʺ�����N
	private int[][] chess;//���̣�0��ʾ�޻ʺ�1��ʾ��
	private int num; //������

	//��ʼ����n�ʺ����⣬����ȫΪ��
	public EightQueen(int n) {
		N=n;
		chess=new int[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				chess[i][j]=0;
			}
		}
	}
	
	//�����㷨
	public void backtrack(int row) {
		//row��0��ʼ��С��Nʱ���У�����Nʱ�������˻���һ��
		if(row==N) {
			put();
			return;
		} 
		for(int i=0;i<N;i++) {
			//������м�¼
			//��ֹ����ʱ�����ظ��ʺ�
			for(int j=0;j<N;j++)
				this.chess[row][j]=0;
			this.chess[row][i]=1;
			//chess[row][i]�����������һ��
			if(isSafe(this.chess,row,i)) {
				backtrack(row+1); 
			}
		}
	}
	
	//�ж��ϡ����ϡ������Ƿ��лʺ�û����true
	public boolean isSafe(int[][] chess,int row,int col) {
		int n=1;
		while(row-n>=0){
			if(chess[row-n][col]==1)                
				return false;
			if(col-n>=0 && chess[row-n][col-n]==1)       
				return false;
			if(col+n<N && chess[row-n][col+n]==1)        
				return false;
			n++;
		}
		return true;
	}
	
	//��ӡ���н��
	public void put() {
		num++;
		System.out.println("num"+ num +"��");
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				System.out.print(chess[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		//��NΪ8
		EightQueen queen = new EightQueen(8);
		//��0��ʼ�Żʺ�
		queen.backtrack(0);
		System.out.print("\n");
		System.out.println("һ����"+queen.num+"�ֽ��");
	}
}
