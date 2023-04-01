

/**   
*    
* 项目名称：Algorithm   
* 类名称：Hanoi   
* 类描述：递归解决n层汉诺塔问题
* 创建人：万重山   
* 创建时间：2023年3月23日 下午8:44:05   
* @version        
*/
public class Hanoi {
	
	/*把n个盘子借助temp盘从start盘移动到end盘:可以把n个盘子看成两部分，上面n-1个和最下面第n个，就将问题简化为了盘子数为2时怎么移动的问题
	 *对于n等于2时的移动方法：先将上面的1移动到工具盘temp上，再将2移到目标盘end上，最后将1移到目标盘end上程序完成
	 *现在对应到n个盘子，上面的1换成了上面n-1个盘子（第一次递归调用时，终点盘变为temp，end反而成为了工具盘），那么我们只要解决n-1个盘子的移动问题即可
	 *而对于n-1个盘子，又可以分解成上面的n-2个盘子和最下面的第n-1个盘子
	 *很明显这是一个递归的过程，直到最后分解为移动1个盘子的问题，问题就解决了（也就是递归出口）
	 *只是在这个过程中起点盘start，工具盘temp和目标盘end会相应的发生改变而已，理解好这点就可以理解整个过程
	 */
	public void transAll(int n,String start,String temp,String end) {
		if(n>1) {
			transAll(n-1,start,end,temp);//把上面n-1个盘子从A盘移动到B盘
			transOne(n,start,end);//把最下面一个盘子移动到C盘
			transAll(n-1,temp,start,end);//把上面n-1个盘子从B盘移动到C盘
		}else {
			System.out.println(n+":从"+start+"移到"+end);
		}
	}
	
	private void transOne(int n, String start, String end) {
		// TODO Auto-generated method stub
		System.out.println(n+":从"+start+"移到"+end);
	}

	public static void main(String[] args) {
		Hanoi demo=new Hanoi();
		int n=3;
		demo.transAll(n, "A", "B", "C");
	}
}
