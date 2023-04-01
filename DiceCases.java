//问题描述：投骰子，投到数字几就向前走几步，求当走到第n步时有多少种投法

import java.util.Scanner;

public class DiceCases {
	//递归实现
	/*
	*每一次投掷有6种情况，那么走到第n步时的所有情况即为这6种情况下剩余的步数的投法之和
	*其中如果剩下的步数为0和1说明投法已经确定，就是此次投掷的点数为剩余的步数（剩余0）或者下一次只能投1（剩余步数为1）
	*也可能剩余的步数为负数，此时明显与事实相悖，是因为考虑了6种情况，但是递归到最后，剩余步数逐渐变小
	*比如可能只剩了3步，在考虑投法时仍按照6种情况考虑，所以出现剩余步数为负数直接舍弃，投法不成立
	*/
	public static int getKindsByRe(int n) {
		if(n<0)
			return 0;
		else if(n==0||n==1)
			return 1;
		else
			return getKindsByRe(n-6)+getKindsByRe(n-5)+getKindsByRe(n-4)+getKindsByRe(n-3)+getKindsByRe(n-2)+getKindsByRe(n-1);
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数:");
		int n=sc.nextInt();
		//int res=getKindsByRe(n);
		int res=getKindsByRe(n);
		System.out.println("走到第"+n+"步有"+res+"种投掷方法");
	}
}
