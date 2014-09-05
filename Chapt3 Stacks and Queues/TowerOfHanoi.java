/**
3.4
In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes 
which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size 
from top to bottom (i.e., each disk sits on top of an even larger one). 
Youhave the following constraints:

(T) Only one disk can be moved at a time.
(2) A disk is slid off the top of one tower onto the next rod.
(3) A disk can only be placed on top of a larger disk.
Write a program to move the disks from the first tower to the last using Stacks.
*/

/*
Solution:
This problem sounds like a good candidate for the Base Case and Build approach.
Let's start with the smallest possible example: n = 1.
Casen = 1. Can we move Disk1 from Tower1 to Tower3? Yes.
1. We simply move Disk1 from Tower1 to Tower3.

Casen = 2. Can we move Disk1 and Disk2 from Tower1 to Tower3? Yes.
1. Move Disk1 from Tower1 to Tower2
2. Move Disk2 from Tower1 to Tower3
3. Move Disk1 from Tower2 to Tower3
Note how in the above steps, Tower 2 acts as a buffer, holding a disk while we move other disks to Tower 3.

Casen = 3. Can we move Disk1,2,and 3 from Tower1 to Tower3? Yes.
1. We know we can move the top two disks from one tower to another (as shown earlier), 
so let's assume we've already done that. But instead, let's move them to Tower 2.
2. Move Disk 3 to Tower 3.
3. Move Disk 1 and Disk 2 to Tower 3. We already know how to do this—we just repeat what we did in Step 1.

Casen = 4.Can we move Disk1,2,3 and 4 from Tower1 to Tower3? Yes.
1. Move Disks1,2,and 3 to Tower2. We know how to do that from the earlier examples. 
2. Move Disk4 to Tower3.
3. Move Disks1,2 and 3 back to Tower3.
Remember that the labels of Tower 2 and Tower 3 aren't important. They're equivalent towers. 
So, moving disks to Tower 3 with Tower 2 serving as a buffer is equivalent to moving disks 
to Tower 2 with Tower 3 serving as a buffer.

This approach leads to a natural recursive algorithm. In each part, we are doing the following steps, 
outlined below with pseudocode:
*/

moveDisks(int n, Tower origin, Tower destination, Tower buffer){
	//base case
	if(n <= 0) return;

	//move top n-1 disks from origin to buffer, using destination as a buffer
	moveDisks(n - 1, origin, buffer, destination);

	//move top from origin to destination
	moveTop(origin, destination);

	//move top n-1 disks from buffer to destination, using origin as a buffer
	moveDisks(n - 1, buffer, destination, origin);
}

/*
The following code provides a more detailed implementation of this algorithm, 
using concepts of object-oriented design.
*/
public static void main(String[] args){
	int n = 3;
	Tower[] towers = new Tower[n];
	for(int i = 0; i < 3; i++){
		towers[i] = new Tower(i);
	}

	for(int i = n - 1; i >= 0; i--){
		towers[0].add(i);
	}

	towers[0].moveDisks(n, towers[2], towers[1]);
}

public class Tower{
	private Stack<Integer> disks; 
	private int index;
	public Tower(int i){
		disks = new Stack<Integer>();
		index = i;
	}

	public int index(){
		return index;
	}

	public void add(int d){
		if(!disks.isEmpty() && disks.peek() <= d){
			System.out.println("Error placing disk" + d);
		}else{ //如果是空或者比stack里面已有的小的时候才可以加到stack中间
			disks.push(d);
		}
	}

	public void moveTopTo(Tower t){
		int top = disks.pop();
		t.add(top);
		System.out.println("Move disk " +  top + " from " + index() + "to" + t.index());
	}

	public void moveDisks(int n, Tower destination, Tower buffer){
		if(n > 0){
			moveDisks(n - 1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}
}


/*
Implementing the towers astheir own object is not strictly necessary,
but it does help to make the code cleaner in some respects.
*/