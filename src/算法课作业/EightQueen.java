package 算法课作业;

public class EightQueen {
	private int N;//题目要求的皇后数量N
	private int[][] chess;//棋盘，0表示无皇后，1表示有
	private int num; //计数器

	//初始化，n皇后问题，棋盘全为空
	public EightQueen(int n) {
		N=n;
		chess=new int[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				chess[i][j]=0;
			}
		}
	}
	
	//回溯算法
	public void backtrack(int row) {
		//row从0开始，小于N时运行，等于N时结束，退回上一级
		if(row==N) {
			put();
			return;
		} 
		for(int i=0;i<N;i++) {
			//清除此行记录
			//防止回溯时出现重复皇后
			for(int j=0;j<N;j++)
				this.chess[row][j]=0;
			this.chess[row][i]=1;
			//chess[row][i]可行则进入下一行
			if(isSafe(this.chess,row,i)) {
				backtrack(row+1); 
			}
		}
	}
	
	//判断上、左上、右上是否有皇后，没有则true
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
	
	//打印可行结果
	public void put() {
		num++;
		System.out.println("num"+ num +"：");
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				System.out.print(chess[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		//置N为8
		EightQueen queen = new EightQueen(8);
		//从0开始放皇后
		queen.backtrack(0);
		System.out.print("\n");
		System.out.println("一共有"+queen.num+"种结果");
	}
}
