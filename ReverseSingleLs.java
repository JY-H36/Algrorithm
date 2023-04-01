//问题描述：原地逆置不带头结点的单链表

public class ReverseSingleLs {
    //创建内部结点类
    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
        }
    }
 
    //简单的创建单链表
    public  void createList() {
        ListNode listNode1 = new ListNode(21);
        ListNode listNode2 = new ListNode(22);
        ListNode listNode3 = new ListNode(23);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        this.head = listNode1;
    }
    
    //定义首结点（第一个结点，注意与头结点区分开）
    public ListNode head;
    //获取尾结点，因为完全逆置后尾结点成为新的头结点
    public ListNode getLastNode() {
    	ListNode p=head;
    	while(p.next!=null) {
    		p=p.next;
    	}
    	return p;
    }
    
    //输出单链表
    public void display(){
    	ListNode p=head;
    	while(p.next!=null) {
    		System.out.print(p.value+" ");
    		p=p.next;
    	}
    	System.out.println(p.value+" ");
    }
    
    /*逆置基本思想：
    *1 从头遍历单链表，直到找到最后一个结点，此时该结点的next域为null，将该结点的next域置为该结点的前一个结点
    *2 继续从头遍历单链表，直到找到一个结点的next指向的结点的next指向该结点本身（第一次找到的其实就是倒数第二个结点），
    *将该结点的next域置为该结点的前一个结点
    *重复2过程，直到找到的结点为头结点也就是到达了递归出口，将头结点的next置空，再将单链表的头结点更新为初始的尾结点，逆置完成
    */
    //原地逆置--非递归方式
    public void reverse(){
    	ListNode p=head,q=head,newHead=head;
    	while(p.next.next!=head) {
    		while(p.next!=null&&p.next.next!=p) {
        		q=p;p=p.next;
        	}
        	if(p.next==null) newHead=p;
    		p.next=q;
        	p=head;
    	}
    	p.next=null;
    	head=newHead;
    }
    
  //原地逆置--递归方式
    public void reverseByRe(){
    	ListNode p=head,q=head;
    	if(p.next.next==head)
    		p.next=null;
    	else {
    		while(p.next!=null&&p.next.next!=p) {
        		q=p;p=p.next;
        	}
    		p.next=q;
    		reverseByRe();
    	}
    }
    public static void main(String[] args) {
    	ReverseSingleLs ls=new ReverseSingleLs();
    	ls.createList();
    	ls.display();
    	//ls.reverse();
    	ListNode last=ls.getLastNode();
    	ls.reverseByRe();
    	ls.head=last;
    	ls.display();
    }
}
