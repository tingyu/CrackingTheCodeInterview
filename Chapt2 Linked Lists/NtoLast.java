/**
Implement an algorithm to find the kth to last element of a singly linked list.
*/

/*
We will approach this problem both recursively and non-recursively. Remember that
recursive solutions are often cleaner but less optimal. For example, in this problem, the
recursive implementation is about half the length of the iterative solution but also takes
0(n) space, where n is the number of elements in the linked list.

Note that for this solution, we have defined k such that passing in k = 1 would return
the last element, k = 2 would return to the second to last element, and so on. It is
equally acceptable to define k such that k = 0 would return the last element.

Solution #1: If linked list size is known
If the size of the linked list is known, then the kth to last element is the (length - k)
th element. We can just iterate through the linked list to find this element. Because this
solution is so trivial, we can almost be sure that this is not what the interviewer intended.


Solution #2: Recursive
This algorithm recurses through the linked list. When it hits the end, the method passes
back a counter set to 0. Each parent call adds 1 to this counter. When the counter equals
k, we know we have reached the kth to last element of the linked list.

Implementing this is short and sweet—provided we have a way of "passing back" an
integer value through the stack. Unfortunately, we can't pass back a node and a counter
using normal return statements. So how do we handle this?

Approach A: Don't Return the Element.
One way to do this is to change the problem to simply printing the kth to last element.
Then, we can pass back the value of the counter simply through return values.
*/

public static int nthToLast(LinkedListNode head, int k){
	if(head == null){
		return 0;
	}
	int i = nthToLast(head.next, k) + 1;
	if(i == k){
		System.out.println(head.data);
	}
	return i;
}

/*
Of course, this is only a valid solution if the interviewer says it is valid.

Approach B:UseC++.
A second way to solve this is to use C++ and to pass values by reference. This allows us to
return the node value, but also update the counter by passing a pointer to it.
*/
node* nthToLast(node* head, int k, int& i){
	if(head == null) {
		return null;
	}

	node *nd = nthToLast(head->next, k, i);
	i = i + 1;
	if(k == i){
		return head;
	}
	return nd;
}


/*
Approach C: Create a Wrapper Class.
We described earlier that the issue was that we couldn't simultaneously return a counter and an index. 
If we wrap the counter value with simple class (or even a single element array), we can mimic passing by reference
*/
public class IntWrapper{
	public int value = 0;
}

LinkedListNode nthToLast(LinkedListNode head, int k, IntWrapper i){
	if(head ==null) {
		return null;
	}

	LinkedListNode node = nthToLastR2(head.next, k, i);
	i.value = i.value + 1;
	if(i.value == k){ //we've found the kth element
		return head;
	}
	return node;
}

/*
Each of these recursive solutions takes 0 ( n ) space due to the recursive calls.
There are a number of other solutions that we haven't addressed. We could store the counter in a static variable. 
Or, we could create a class that stores both the node and the counter, and return an instance of that class. 
Regardless of which solution we pick, 
we need a way to update both the node and the counter in a way that all levels of the recursive stack w ill see.
*/

/*
Solution 3 - Iterative
A more optimal, but less straightforward, solution is to implement this iteratively.
We can use two pointers, p1 and p2. We place them k nodes apart in the linked list by putting p1 at the beginning 
and moving p2 k nodes into the list.Then, when we move them at the same pace, p2 will hit the end of the linked list 
after LENGTH - k steps. 
At that point, p1 will be LENGTH - k nodes into the list, or k nodes from the end.
*/

LinkedListNode nthToLast(LinkedListNode head, int k){
	if(k<=0) return null;

	LinkedListNode p1 = head;
	LinkedListNode p2 = head;

	//Move p2 forward k nodes into the list
	for(int i = 0; i< k-1; i++){
		if(p2 == null) return null; //error check
		p2 = p2.next;
	}
	if(p2 == null) return null;

	//Now, move p1 and p2 at the same speed. When p2 hits the end, p1 will be at the right element
	while(p2.next !=null){
		p1 = p1.next;
		p2 = p2.next;
	}
	return p1;
}
//This algorithm takes0(n)timeand0(1) space.

