package divide_and_rule;

/**   
*    
* 项目名称：Algorithm   
* 类名称：FindMul   
* 类描述：在一个有序序列（以递增为例）中查找众数，不考虑多个众数以及没有众数的情况  
* 创建人：万重山   
* 创建时间：2023年3月23日 下午9:14:21   
* @version        
*/
public class FindMul {
	//存储递增序列
	public static int[] arr=new int[] {1,1,3,4,5};
	public static int value;//众数值
	public static int occ=0;//众数出现次数即重数
	public static void disp() {
		for (int i = 0; i < arr.length; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static int getMax(int a,int b,int c) {
		int max=a;
		if(b>=max) {
			max=b;
		}
		if(c>max) {
			max=c;
		}
		return max;
	}
	
	/*
	 * 基本思路：将整个序列分成3个部分：中位数，中位数左边的序列以及中位数右边的序列
	 * 1先求出中位数出现的次数，如果中位数的次数已经足够大（同时大于等于左右两边的序列长度），则中位数就是众数
	 * 2如果中位数的次数不够大时，继续求左右两边序列的众数并与中位数的出现次数相比较（事实上可能只需要比较某一边序列的众数，视具体情况而定）
	 * 众数为这几者当中出现次数最多的
	 * 求某个序列众数又可以递归地调用1 2的过程
	 */
	public static int getMul(int low,int high) {
		int mid=(low+high)/2;
		int count;
		int left=0,right=0;
		for(int i=mid;i>=low;i--) {
			left=low;
			if(arr[i]!=arr[mid]) {
				left=i;
				break;
			}
		}
		System.out.println("左:"+left);
		for(int i=mid;i<=high;i++) {
			right=high;
			if(arr[i]!=arr[mid]) {
				right=i;
				break;
			}
		}
		System.out.println("右:"+right);
		count=right-left-1;//中位数的重数
		if(arr[left]==arr[mid]) {
			count++;
		}
		if(arr[right]==arr[mid]) {
			count++;
		}
		if(count>=(left-low+1)&&count>=(high-right+1)) {
			//只有出现次数大于前面的众数的重数才更新众数
			if(count>occ) {
				occ=count;
				value=arr[mid];
			}
			return count;
		}else if(count<(left-low+1)&&count<(high-right+1)) {		//众数长度同时小于左右两边序列的长度
			return getMax(getMul(low,left),count,getMul(right,high));
		}
		else if(count<(left-low+1)) {//众数长度小于左边序列长度
			return getMul(low,left)>count? getMul(low,left):count;
		}else {//众数长度小于右边序列长度
			return getMul(right,high)>count? getMul(right,high):count;
		}
	}
	
	
	public static void main(String[] args) {
		FindMul demo=new FindMul();
		demo.disp();
		demo.getMul(0, arr.length-1);
		System.out.println("众数:"+value);
		System.out.println("重数:"+occ);
	}
}
