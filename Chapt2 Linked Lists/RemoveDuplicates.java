/**
2.1
Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed
*/

/*
In order to remove duplicates from a linked list, we need to be able to track duplicates.
A simple hash table will work well here.

In the below solution, we simply iterate through the linked list, adding each element
to a hash table. When we discover a duplicate element, we remove the element and
continue iterating. We can do this all in one pass since we are using a linked list.
*/

public static void deleteDups(LinkedListNode n){
	Hashtable table = new Hashtable();
	LinkedListNode previous = null;
	while(n!=null){
		if(table.containsKey(n.data)){
			previous.next = n.next;
		}else{
			table.put(n.data, true);
			previous = n;
		}
		n = n.next;
	}
}

//The above solution takes 0(N) time, where N is the number of elements in the linked list.

/*
Follow Up: No Buffer Allowed
If we don't have a buffer, we can iterate with two pointers: current which iterates
through the linked list, and runner which checks all subsequent nodes for duplicates
*/

public static void deleteDups(LinkedListNode head){
	if(head == null) return;

	LinkedListNode current = head;
	while(current != null){
		//remove all future nodes that has the same value
		LinkedListNode runner = current;
		while(runner.next !=null){
			if(runner.next.data = current.data){
				runner.next = runner.next.next;
			}else{
				runner = runner.next;
			}
		}
	    current = current.next;
	}
}

//This code runs in 0(1) space, but 0(N 2 ) time.
