/**
You have two numbers represented by a linked list, where each node contains a
single digit. The digits are stored in reverse order, such that the 1 's digit is at the head
of the list. Write a function that adds the two numbers and returns the sum as a
linked list.
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem
*/

/*
It's useful to remember in this problem how exactly addition works. Imagine the
problem:
617
+ 2 9 5
First, we add 7 and 5 to get 12. The digit 2 becomes the last digit of the number, and 1
gets carried over to the next step. Second, we add 1,1, and 9 to get 11 .The 1 becomes
the second digit, and the other 1 gets carried over the final step. Third and finally, we
add 1,6 and 2 to get 9. So, our value becomes 912.
We can mimic this process recursively by adding node by node, carrying over any
"excess"data to the next node. Let's walkthrough this for the below linked list:
+
7 -> 1 -> 6
5 -> 9 -> 2
We do the following:
1. We add 7 and 5 first, getting a result of 12.2 becomes the first node in our linked lis
and we "carry" the 1 to the next sum.
List: 2 -> ?
2. We then add 1 and 9, as well as the "carry," getting a result of 11. 1 becomes the
second element of our linked list, and we carry the 1 to the next sum.
List: 2 -> 1 -> ?
3. Finally, we add 6, 2 and our "carry," to get 9. This become the final element of our
linked list.
List: 2 -> 1 -> 9.
The code below implements this algorithm
*/

LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry){
	/* We're done if both lists are null AND the carry value is 0 */
	if(l1== null && l2 == null && carry ==0){
		return null;
	}

	LinkedListNode result = new LinkedListNode(carry, null, null); //?????????????????????????????????????????????????????

	//add value, and the data from l1, l2
	int value = carry;
	if(l1 !=null){
		value += l1.data;
	}

	if(l2 != null){
		value += l2.data;
	}

	reslut.data = value%10; //second digital of number

	//recursive
	if(l1 !=null || l2 !=null){
		LinkedListNode more = addLists(l1 == null? null: l1.next,
										l2 == null ? null: l2.next, 
										value >=10 ? 1: 0);
		reslut.setNext(more);
	}
	return reslut;
}

/*
In implementing this code, we must be careful to handle the condition when one linked
list is shorter than another. We don't want to get a null pointer exception
*/

/*
Follow Up
Part B is conceptually the same (recurse, carry the excess), but has some additional
complications when it comes to implementation:

1. One list may be shorter than the other, and we cannot handle this "on the fly." For
example, suppose we were adding (1 -> 2 -> 3 -> 4) and (5 -> 6 -> 7). We need to
know that the 5 should be "matched" with the 2, not the 1. We can accomplish this
by comparing the lengths of the lists in the beginning and padding the shorter list
with zeros.

2. In the first part, successive results were added to the tail (i.e., passed forward). This
meant that the recursive call would be passed the carry, and would return the result
(which is then appended to the tail). In this case, however, results are added to the
head (i.e., passed backward). The recursive call must return the result, as before, as
well as the carry.This is not terribly challenging to implement, but it is more cumber-
some. We can solve this issue by creating a wrapper class called Partial Sum
*/

public class PartialSum{
	public LinkedListNode sum = null;
	public int carry = 0;
}

LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2){
	int len1 = length(l1);
	int len2 = length(l2);

	//pad the shorter list with zeros -- see note 1
	if(len1 < len2){
		l1 = padList(l1, len2-len1);
	} else{
		l2 = padList(l2, len1-len2);
	}

	//Add Lists
	PartialSum sum = addListsHelper(l1, l2);

	// If there was a carry value left over, insert this at the front of the list. Otherwise, just return the linked list. 
	if(sum.carry == 0){
		return sum.sum;
	} else{
		LinkedListNode result = insertBefore(sum.sum, sum.carry);
		return result;
	}
}

PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2){
	if(l1 == null && l2 == null){
		PartialSum sum = new PartialSum();
		return sum;
	}

	//add smaller digits recursively
	PartialSum sum = addListsHelper(l1.next, l2.next);

	//add carry to current data
	int val = sum.carry + l1.data + l2.data;

	//insert sum of current digits
	LinkedListNode full_result = insertBefore(sum.sum, val%10);

	//Return sum so far, and the current value
	sum.sum = full_result;
	sum.carry = val/10;
	return sum;
}

//pad the list with zeros
LinkedListNode padList(LinkedListNode l, int padding){
	LinkedListNode head = l;
	for(int i =0; i < padding; i++){
		LinkedListNode n = new LinkedListNode(0, null, null);
		head.prev = n;
		n.next = head;
		head = n;
	}
	return head;
}

// Helper function to insert node in the front of a linked list
LinkedListNode insertBefore(LinkedListNode list, int data){
	LinkedListNode node = new LinkedListNode(data, null, null);
	if(list != null) {
		list.prev = node;
		node.next = list;
	}
	return node;
}

/*
Note how we have pulled insertBefore(), padList(), and length() (not listed)
into their own methods. This makes the code cleaner and easier to read—a wise thing
to do in your interviews!
*/