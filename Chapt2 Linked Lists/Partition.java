/**
Write code to partition a linked list around a value x, such that all nodes less than x come before 
all nodes greater than or equal to x.
*/

/*
If this were an array,we would need to be careful about how we shifted elements. Array shifts are very expensive.
However, in a linked list, the situation is much easier. Rather than shifting and swapping elements, 
we can actually create two different linked lists: one for elements less than x, 
and one for elements greater than or equal to x.
We iterate through the linked list, inserting elements into our before list or our after list. 
Once we reach the end of the linked list and have completed this splitting, we merge the two lists.
The code below implements this approach
*/

//pass in the head of the linked list and value to partition around
public LinkedListNode partition(LinkedListNode node, int x){
	LinkedListNode beforeStart = null;
	LinkedListNode beforeEnd = null;
	LinkedListNode afterStart = null;
	LinkedListNode afterEnd = null;

	//Partition list
	while(node != null){
		LinkedListNode next = node.next;
		node.next = null;
		if(node.data < x){
			//insert node into end of before list
			if(beforeStart == null){
				beforeStart = node;//node.next = null
				beforeEnd = beforeStart; //move beforeEnd position to beforeEnd
			} else{
				beforeEnd.next = node;//node.next = null, insert after beforeEnd
				beforeEnd = node;//move beforeEnd position to the current tail
			}
		}else{
			//insert node into end of after list
			if(afterStart == null){
				afterStart = node;
				afterEnd = afterStart;
			} else{
				afterEnd.next = node; //node.next = null, insert after afterEnd
				afterEnd = node;//move afterEnd position to the current tail
			}
		}
		node = next; // have store next before assign it to null
	}

	if(beforeStart == null){
		return afterStart;
	}

	//merge before list and after list
	beforeEnd.next = afterStart;
	return beforeStart;
}

/*k, we know we have reached the kth to last element of the linked list.

If it bugs you to keep around four different variablesfor tracking two linked lists, you're not alone. 
We can get rid of some of these, with just a minor hit to the efficiency. 

This drop in efficiency comes because we have to traverse the linked list an extra time. 
The big-O time will remain the same though, and we get shorter, cleaner code.

The second solution operates in a slightly different way. Instead of inserting nodes into the end of the before 11st 
and the after list, it inserts nodes into the front of them.
*/

public LinkedListNode partition(LinkedListNode node, int x){
	LinkedListNode beforeStart = null;
	LinkedListNode afterStart = null;

	//partition list
	while(node !=null){
		LinkedListNode next = node.next;
		if(node.data < x){
			//insert node into start of before list
			node.next = beforeStart; //move node before beforeStart
			beforeStart = node; //Update before Start Position to Head of list+
		} else{
			//insert node into front of after listk, we know we have reached the kth to last element of the linked list.
			node.next = afterStart;//move node before afterStart
			afterStart = node;//Update after Start Position to Head of list+
		}
		node = next;
	}

	//merge before list and after list
	if(beforeStart == null){
		return afterStart;
	}

	//find end of before list, and merge the list
	LinkedListNode head = beforeStart;
	while(beforeStart.next !=null){
		beforeStart = beforeStart.next;//move
	}
	beforeStart.next = afterStart;

	return head;
}


/*
Note that in this problem, we need to be very careful about null values. 
Check out line 7 in the above solution. The line is here because we are modifying the linked list as we're 
looping through it. We need to store the next node in a temporary variable so that we remember 
which node should be next in our iteration.
*/

