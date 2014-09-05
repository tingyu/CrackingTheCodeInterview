/**
3.7
An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis. 
People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, 
or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). 
They cannot select which specific animal they would like. Create the data structures to maintain 
this system and implement opera- tions such as enqueue, dequeueAny, dequeueDog and dequeueCat. 
You may use the built-in L inkedL ist data structure.
*/

/*
Soultion:

We could explore a variety of solutions to this problem. For instance, we could maintain a single queue.
This would make dequeueAny easy, but dequeueDog and dequeueCat would require iteration through 
the queue to find the first dog or cat. 
This would increase the complexity of the solution and decrease the efficiency.


An alternative approach that is simple, clean and efficient is to simply use separate queues for dogs and cats, 
and to place them within a wrapper class called Animal- Queue. 
We then store some sort of timestamp to mark when each animal was enqueued. 
When we call dequeueAny, we peek at the heads of both the dog and cat queue and return the oldest.
*/

public abstract class Animal{
	private int order;
	protected String name;
	public Animal(String n){
		name = n;
	}

	public void setOrder(int ord){
		order = ord;
	}

	public int getOrder(){
		return order;
	}

	public boolean isOlderThan(Animal a){
		return this.order < a.getOrder();
	}
}

public class AnimalQueue{
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0; //acts as timestamp

	public void enqueue(Animal a){
		//order is used as a sort of timestamp,so that we can compare the insertion order of a dog to a cat
		a.setOrder(order);
		order++;

		if(a instanceof Dog) dogs.addLast((Dog) a);
		else if(a instanceof Cat) cats.addLast((Cat) a);
	}

	public Animal dequeueAny(){
		//Look at tops of dog and cat queues, and pop the stacks with the oldest value
		if(dogs.size() == 0){
			return dequeueCats();
		}else if(cats.size() == 0){
			return dequeueDogs();
		}

		Dog dog = dogs.peek();
		Cat cat = cats.peek();

		if(dog.isOlderThan(cat)){
			return dequeueDogs();
		}else{
			return dequeueCats();
		}
	}

	public Dog dequeueDogs(){
		return dogs.poll();
	}

	public Cat dequeueCats(){
		return cats.poll();
	}
}

public class Dog extends Animal{
	public Dog(String n) {super(n);}
}

public class Cat extends Animal{
	public Cat(String n) {super(n);}
}