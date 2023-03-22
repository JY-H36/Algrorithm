package ex1;

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
