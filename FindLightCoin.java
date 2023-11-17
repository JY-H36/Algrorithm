package divide_and_rule;

import java.util.Random;

/**   
*    
* 项目名称：Algorithm   
* 类名称：FindCoin   
* 类描述：对于100个硬币，利用二分法或者三分法查找假币，其中已知假币重量要轻一些   
* 创建人：万重山   
* 创建时间：2023年3月23日 下午10:13:23   
* @version        
*/
public class FindCoin {
	public static int[] arr = new int[100];
	public static int flag;//记录假币位置
	public static int countBin = 0;//记录二分法查找次数
	public static int countTri = 0;//记录三分法查找次数
	
	//初始化硬币重量
	public static void initArr() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 2;
		}
		Random rd = new Random();
		/*注：用58做的调试，可以使用随机数生成假币
		 * int flag=rd.nextInt(100);
		 * arr[flag]=1;
		 */
		arr[58] = 1;
		System.out.println("假币位置:" + 58);
	}

	public static void disp() {
		for (int i = 0; i < arr.length; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	/*获取从low位置到high位置硬币重量之和，不能单独出现，因为实际测量工具为天平，两边都需要放一定数量的硬币
	    这里是为了方便具象化成了一个简单的求和函数，事实上这个数值我们并不能得知，只是用来比较天平两边硬币的重量而已*/
	public static int getWeight(int low, int high) {
		int count = 0;
		for (int i = low; i <= high; i++) {
			count += arr[i];
		}
		return count;
	}

	/*
	 * 二分查找假币：将硬币二等分，可能多出一个硬币，将等分的两份硬币称重，假币在轻的那一份里，如果相等则假币为多余的硬币
	 * 对于筛选目标继续查找假币可以递归调用上面的过程，直到找出假币
	 */
	public static void binSearch(int low, int high) {

		// 当筛选目标还剩偶数个时
		if ((high - low) % 2 != 0) {
			if (getWeight(low, (low + high) / 2) < getWeight((low + high) / 2 + 1, high)) {
				countBin++;
				System.out.println(low + " " + (low + high) / 2);
				binSearch(low, (low + high) / 2);
			} else {
				countBin++;
				System.out.println((low + high) / 2 + 1 + " " + high);
				binSearch((low + high) / 2 + 1, high);
			}
		} else {// 当筛选目标还剩奇数个时
			if (getWeight(low, (low + high - 1) / 2) < getWeight((low + high) / 2, high - 1)) {
				countBin++;
				System.out.println(low + " " + (low + high - 1) / 2);
				binSearch(low, (low + high - 1) / 2);
			} else if (getWeight(low, (low + high - 1) / 2) > getWeight((low + high) / 2, high - 1)) {
				countBin++;
				System.out.println((low + high) / 2 + " " + (high - 1));
				binSearch((low + high) / 2, high - 1);
			} else {
				
				countBin++;
				return;
			}
		}

	}

	//三分查找假币：与二等分思想类似
	public static void triSearch(int low, int high) {
		//只剩两个硬币，直接称出结果
		if(low==high-1) {
			countTri++;
			return ;
		}
		int d = (high - low) / 3;//三分后前两份相等的硬币的数量
		if (getWeight(low, low + d) < getWeight(low + d + 1, low + d + 1 + d)) {
			countTri++;
			System.out.println(low + " " + (low + d));
			triSearch(low, low + d);
		} else if (getWeight(low, low + d) > getWeight(low + d + 1, low + d + 1 + d)) {
			countTri++;
			System.out.println((low + d + 1) + " " + (low + d + 1 + d));
			triSearch(low + d + 1, low + d + 1 + d);
		} else {
			countTri++;
			System.out.println((low + d + 2 + d) + " " + high);
			triSearch(low + d + 2 + d, high);
		}
	}

	public static void main(String[] args) {
		FindCoin demo = new FindCoin();
		demo.initArr();
		//demo.binSearch(0, arr.length - 1);
		//System.out.println("二分法查找次数:" + countBin);
		demo.triSearch(0, arr.length - 1);
		System.out.println("三分法查找次数:" + countTri);
	}
}
