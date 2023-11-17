package divide_and_rule;
import java.util.Scanner;

/**   
*    
* 项目名称：Algorithm   
* 类名称：RevNums   
* 类描述：查找一个序列中的所有逆序对   
* 创建人：万重山   
* 创建时间：2023年3月23日 下午10:05:56   
* @version        
*/
public class RevNums {
	public static int[] arr=new int[] {3,1,4,5,2,1};
	public static int size;
	
	/*
	 * 1:利用希尔排序的思想，先选取一个增量，按照增量将整个数组分成不同的组，再在组内寻找逆序对，直到组内某元素加上增量后超过数组长度即组内最后一个元素已经被比较完。
	 * 2:重复此过程，将按照同一增量的所有组先找完逆序对
	 * 3:将增量递减（每次减去1），重复1 2
	 * 4:直到增量减为1，最后做一次1 2，做完程序结束，可能的所有逆序对找完
	 * 注：希尔排序本质上是一种插入排序，这里借用希尔排序的增量来分组是为了穷尽所有情况，有点类似于蛮力法，也是一种解题思路
	*/
	public static int getRevNums() {
		System.out.println("逆序对:");
		int count=0;
		for(int d=arr.length-1;d>=1;d--) {
			for(int j=0;j<d;j++) {
				for(int i=j;i+d<=arr.length-1;i+=d) {
					if(arr[i]>arr[i+d]) {
						System.out.print("<"+arr[i]+","+arr[i+d]+">");
						count+=1;
					}
				}
			}	
		}
		return count;
	}
	
	/*
	 * 基本思路：将数据分成两部分，先求出左右两边的逆序对，再求合并后左边数据和右边数据构成的逆序对
	 * 对左右两边数据求逆序数则是递归调用的过程，递归出口：数据长度为2或者1的情况出现
	 */
	public static int getRevByBin(int low,int high) {
		if(low==high-1) {
			System.out.print("<"+arr[low]+","+arr[high]+">");
			return arr[low]>arr[high]?1:0;
		}else if(low==high){
			return 0;
		}else {
			return getRevByBin(low,(low+high)/2)+getRevByBin((low+high)/2+1,high)+getMerge(low,(low+high)/2,high);
		}
			
	}
	//前后两部分进行合并
	private static int getMerge(int low, int mid, int high) {
		int count=0;
		for(int i=low;i<=mid;i++) {
			for(int j=mid+1;j<=high;j++) {
				if(arr[i]>arr[j]) {
					System.out.print("<"+arr[i]+","+arr[j]+">");
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		RevNums demo=new RevNums();
		//int count=demo.getRevNums();
		int count=demo.getRevByBin(0, arr.length-1);
		System.out.println("逆序对数:"+count);
	}

	
}
