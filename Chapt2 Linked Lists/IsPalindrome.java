/**
2.7
Implement a function to check if a linked list is a palindrome,
*/

/*
To approach this problem, we can picture a palindrome like 0 -> 1 -> 2 -> 1
- > 0. We know that, since it's a palindrome, the list must be the same backwards and
forwards.This leads us to our first solution

Solution #1: Reverse and Compare
Our first solution is to reverse the linked list and compare the reversed list to the original
list. If they're the same, the lists are identical.

Note that when we compare the linked list to the reversed list, we only actually need to
compare the first half of the list. If the first half of the normal list matches the first half
of the reversed list, then the second half of the normal list must match the second half
of the reversed list.
*/


/*
Solution #2: Iterative Approach
We want to detect linked lists where the front half of the list is the reverse of the second
half. How would we do that? By reversing the front half of the list. A stack can accom-
plish this.

We need to push the first half of the elements onto a stack. We can do this in two
different ways, depending on whether or not we know the size of the linked list.

If we know the size of the linked list, we can iterate through the first half of the elements
in a standard for loop, pushing each element onto a stack. We must be careful, of course,
to handle the case where the length of the linked list is odd.

If we don't know the size of the linked list, we can iterate through the linked list, using
the fast runner / slow runner technique described in the beginning of the chapter. At
each step in the loop, we push the data from the slow runner onto a stack. When the
fast runner hits the end of the list, the slow runner will have reached the middle of the
linked list. By this point, the stack will have all the elements from the front of the linked
list, but in reverse order.

Now, we simply iterate through the rest of the linked list. At each iteration, we compare
the node to the top of the stack. If we complete the iteration without finding a differ-
ence, then the linked list is a palindrome.
*/

boolean isPalindrome(LinkedListNode head){
	LinkedListNode fast = head;
	LinkedListNode slow = head;

	Stack<Intger> stack = new Stack<Intger>();

	//Push element from the first half of linked list onto stack. When faster runner (which is moving at 2x speed)
	//reaches the end of the linked list, then we know we are at the middle
	while(fast != null && fast.next != null){
		stack.push(slow.data);
		slow = slow.next;
		fast = fast.next.next;
	} 

	//Has odd number of elements, so skip the middle element 
	//why this is odd??????????????????????????????????????????????
	if(fast !=null){
		slow = slow.next;
	}

	while(slow !=null) {
		int top = stack.pop().intValue();

		//if values are different, then it's not a palindrome
		if(top != slow.data) {
			return false;
		}
		slow = slow.next;
	}
	return true;
}

/*
Solution #3: Recursive Approach
First, a word on notation: in the below solution, when we use the notation node Kx,
the variable K indicates the value of the node data, and x (which is either f or b) indi-
cates whether we are referring to the front node with that value or the back node. For
example, in the below linked list, node 3b would refer to the second (back) node with
value 3.
Now, like many linked list problems, you can approach this problem recursively. We may
have some intuitive idea that we want to compare element 0 and element n, element 1
and element n-1, element 2 and element n-2, and so on, until the middle element(s).
For example:
0 ( 1 ( 2 ( 3 ) 2 ) 1 ) 0
In order to apply this approach, we first need to know when we've reached the middle
element, as this will form our base case. We can do this by passing in length - 2 for
the length each time. When the length equals 0 or 1, we're at the center of the linked list.
*/

recurse(Node n, int length) {
	if(length == 0 || length == 1) {
		return [something]; //At middle
	}
	recurse(n.next, length -2);
	....
}

/*
This method will form the outline of the isPalindrome method. The "meat" of the
algorithm though is comparing node i to node n - i to check if the linked list is a
palindrome. How do we do that?

Let's examine what the call stack looks like:

1 vl = isPalindrome: list = 0 ( 1 ( 2 ( 3 ) 2 ) 1 ) 0 . length = 7
2     v2 = isPalindrome: list = 1 ( 2 ( 3 ) 2 ) 1 ) 0 . length = 5
3         v3 = isPalindrome: list = 2 ( 3 ) 2 ) 1 ) 0 . length = 3
4             v4 = isPalindrome: list = 3 ) 2 ) 1 ) 0 . length = 1
5              returns v3
6         returns v2
7     returns vl
8 returns ?


In the above call stack, each call wants to check if the list is a palindrome by comparing
its head node with the corresponding node from the back of the list. That is:
• Line 1 needs to compare node 0f with node 0b
• Line 2 needs to compare node If with node Ib
• Line 3 needs to compare node 2f with node 2b
• Line 4 needs to compare node 3f with node 3b.
If we rewind the stack, passing nodes back as described below, we can do just that:
• Line 4 sees that it is the middle node (since length = 1), and passes back head.
   next.The value head equals node 3, so head, next is node 2b.
• Line 3 compares its head, node 2f,to returned_node (the value from the previous
   recursive call), which is node 2b. If the values match, it passes a reference to node
  Ib (returned_node.next) up to line 2.
• Line 2 compares its head (node If) to returned_node (node Ib). If the values
   match, it passes a reference to node 0b(or, returned_node.next) up to line 1.
• Line 1 compares its head, node 0f, to returned_node, which is node 0b. If the
   values match, it returns true.
To generalize, each call compares its head to returned_node, and then passes
returned_node. next up the stack. In this way, every node i gets compared to node
n - i. If at any point the values do not match, we return false, and every call up the
stack checks for that value.
But wait, you might ask, sometimes we said we'll return a boolean value, and some-
times we're returning a node. Which is it?
It's both. We create a simple class with two members, a boolean and a node, and return
an instance of that class.

*/

class Result{
	public LinkedListNode node;
	public boolean result;
}

/*
The example below illustrates the parameters and return values from this sample list.

1  isPalindrome: list = 0 ( 1 ( 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) 0 . len =9
2  isPalindrome: list = 1 ( 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) 0 . len =7
3  isPalindrome: list = 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) 0 . len =5
4  isPalindrome: list = 3 ( 4 ) 3 ) 2 ) 1 ) 0. len = 
5  isPalindrome: list = 4 ) 3 ) 2 ) 1 ) 0. len = 1
6  returns node 3b, true
7  returns node 2b, true
8  returns node Ib, true
9  returns node 0b, true
10 returns nobe &o, true
Implementing this code is now just a matter of filling in the details.
*/

Result isPalindromeRecurse(LinkedListNode head, int length){
	if(head == null || length == 0) {
		return new Result(null, true);
	} else if(length ==1){
		return new Result(head.next, true);
	} else if(length ==2){
		return new Result(head.next.next, head.data == head.next.data);
	}
	Result res = isPalindromeRecurse(head.next, length -2);
	if(!res.result || res.node == null){
		return res;
	} else{
		res.result = head.data == res.node.data;
		res.node = res.node.next;
		return res;
	}
}

boolean isPalindrome(LinkedListNode head){
	Result p = isPalindromeRecurse(head, listSize(head));
	return p.result;
}

/*
Some of you might be wondering why we went through all this effort to create a special
Result class. Isn't there a better way? Not really—at least not in Java.
However, if we were implementing this in C or C++, we could have passed in a double
pointer.
*/
boolean isPalindromeRecurse(Node head, int length, Node** next){
	...
}
//It's ugly, but it works.






