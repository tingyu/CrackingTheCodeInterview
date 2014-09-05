/**
3.6
Write a program to sort a stack in ascending order (with biggest items on top). 
You may use at most one additional stack to hold items, but you may not copy the elements into 
any other data structure (such as an array). 
The stack supports the following operations: push, pop, peek, and isEmpty.
*/

/*
Solution:

One approach is to implement a rudimentary sorting algorithm. 
We search through the entire stack to find the minimum element and then push that onto a new stack. 
Then, we find the new minimum element and push that. 
This will actually require a total of three stacks: 
s i is the original stack, s2 is the final sorted stack, and s3 acts as a buffer during our searching of s i. 
To search s i for each minimum, we need to pop elements from s1 and push them onto the buffer, s3.
Unfortunately, we're only allowed one additional stack. Can we do better? Yes.

Rather than searching for the minimum repeatedly, we can sort s1 by inserting each element from s1 in order into s2. How would this work?
Imagine we have the following stacks, where s2 is"sorted"and si is not:


When we pop 5 from si, we need to find the right place in s2 to insert this number. 
In this case, the correct place is on s2 just above 3. How do we get it there? We can do this
by popping 5 from s i and holding it in a temporary variable. 
Then, we move 12 and 8 over to s i (by popping them from s2 and pushing them onto si) and then push 5 onto S

Note that 8 and 12 are still in s i - and that's okay! We just repeat the same steps for those 
two numbers as we did for 5, each time popping off the top of s i and putting it into the "right place" on s2. 
(Of course, since 8 and 12 were moved from s2 to s i precisely because they were larger than 5, 
the "right place" for these elements will be right on top of 5. We won't need to muck 
around with s2's other elements, and the inside of the below while loop will not be run when tmp is8 or 1 2.)

*/

public static Stack<Integer> sort(Stack<Integer> s){
	Stack<Integer> r = new Stack<Integer>();
	while(!s.isEmpty){
		int tmp = s.pop();
		while(!r.isEmpty() && r.peek() > tmp){//这里判断了r.isEmpty()是因为r.peek的需要，如果是空的话后面就会报错
			s.push(r.pop());
		}
		r.push(tmp);
	} 
	return r;
}


/*
This algorithm is 0(N2) time and 0(N) space.

If we were allowed to use unlimited stacks, we could implement a modified quicksort or mergesort.

With the mergesort solution, we would create two extra stacks and divide the stack into two parts. 
We would recursively sort each stack, and then merge them back together in sorted order into the 
original stack. 

Note that this would require the creation of two additional stacks per level of recursion.


With the quicksort solution, we would create two additional stacks and divide the stack 
into the two stacks based on a pivot element. 

The two stacks would be recursively sorted, 
and then merged back together into the original stack. 

Like the earlier solution, this one involves creatingtwo additional stacks per level of recursion.
*/