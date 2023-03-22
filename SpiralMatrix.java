/*问题描述：要求输入一个正整数n，输出一个n阶的螺旋矩阵*/

import java.util.Scanner;

public class SpiralMatrix {
	public static int[][] arr;
	public static int count=1;
	public static void initMatrix(int n) {
		arr=new int[n][n];
		for(int i=0;i<n;i++)
			arr[i]=new int[n];
		if(n%2!=0)
			arr[n/2][n/2]=n*n;
	}
	
	//创建n阶螺旋矩阵
	/*n阶螺旋矩阵的构成可以看成一层又一层的正方形，大正方形包裹小正方形，小正方形进一步包裹更小的正方形
	*理解了螺旋矩阵的构成就可以很好地理解整个递归过程就是构正方形的过程
	*那么如何构造正方形就是写递归体的关键，一个边长为n的正方形可以看成边长为4条n-1的边，顺时针拼接而成
	*构造里面一层正方形的边长为n-2，这里可以考虑分初始输入n为奇偶两种情况
	*为奇数递归结束时即n到1时要求最中心值为n*n，这一步初始化数组时已经实现，偶数直接一直往下构造直到递归出口即n=0
	*/
	public static void createSpiralMatrix(int row,int col,int n) {
		if(n==1||n==0) {
			return ;
		}
		else {
			for(int i=1;i<n;i++) {
				arr[row][col++]=count++;
			}
			for(int i=1;i<n;i++) {
				arr[row++][col]=count++;
			}
			for(int i=1;i<n;i++) {
				arr[row][col--]=count++;
			}
			for(int i=1;i<n;i++) {
				arr[row--][col]=count++;
			}
			createSpiralMatrix(row+1,col+1,n-2);
		}
	}
	
	public static void display() {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.printf("%-5d ",arr[i][j]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		SpiralMatrix sm=new SpiralMatrix();
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数:");
		int n=sc.nextInt();
		sm.initMatrix(n);
		sm.createSpiralMatrix(0, 0, n);
		System.out.println(n+"阶螺旋矩阵:");
		sm.display();
	}
}
