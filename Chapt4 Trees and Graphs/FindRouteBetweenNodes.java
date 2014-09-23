/**
4.2
Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
*/


/*
This problem can be solved by just simple graph traversal, such as depth first search or 
breadth first search. We start with one of the two nodes and, during traversal, 
check if the other node is found.We should mark any node found in the course of the algorithm 
as "already visited"to avoid cycles and repetition of the nodes.
*/

//The code below provides an iterative implementation of breadth first search.
public enum State{
	Unvisited, Visited, Visiting;
}

public static boolean search(Graph g, Node start, Node end){
	if(start == end) return true;

	//operate as Queue
	LinkedList<Node> q = new LinkedList<Node>();

	for(Node u: g.getNodes()){
		u.state = State.Unvisited;
	}

	start.state = State.Visiting;
	q.add(start);
	Node u;
	while(!q.isEmpty()){
		u = q.removeFirs(); //i.e. dequeue
		if(u != null){
			for(Node v : u.getAdjacent()){
				if(v.state == State.Unvisited){
					if(v == end){
						return true;
					}else{
						v.state = State.Visiting;
						q.add(v);
					}
				}
			}
		}
		u.state = State.Visited;
	}
	return false;
}

/*
It may be worth discussing with your interviewer the trade-offs between breadth first search 
and depth first search for this and other problems. For example, depth first search is a bit 
simpler to implement since it can be done with simple recursion. Breadth first search can also 
be useful to find the shortest path, whereas depth first search may traverse one adjacent node 
very deeply before ever going onto the immediate neighbors.
*/