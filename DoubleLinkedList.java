package Assignment3;

//doubleLinkedList
public class DoubleLinkedList<Item> { 
	private DoubleNode first; //Node keeps of item and its next, linked list
	private DoubleNode last;//node is a block with two items first and last
	private int N;
	
	//nested class DoubleNode to build doubly linked lists
	private class DoubleNode {
		Item item;
		DoubleNode nextNode;
		DoubleNode prevNode;

		public DoubleNode() {
			item = null;
			nextNode= null; //tail
			prevNode = null;//head
		}
	}

	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean firstIsEmpty() {
		//to check if the list is empty
		//if empty, return true
		return first == null; 
	}
	public boolean lastIsEmpty() {
		//to check if the list is empty
		//if empty, return true
		return last == null; 
	}
	
	public DoubleNode insertAtEnd(Item input){
		//create an old last double node to hold the curr last node
		DoubleNode oldLast = last;
		// create a new node for the last node
		last = new DoubleNode();
		//assign the new value for the last node 
		last.item = input;
		//next of node is null
		last.nextNode = null;
		//previous of node is oldlast
		last.prevNode = oldLast;
		if(firstIsEmpty()) {
			first = last;
		}
		else 
			oldLast.nextNode = last;
		N++;
		return oldLast;
	}
	
	public DoubleNode insertAtBeginning(Item input){
		DoubleNode oldFirst = first;
		first = new DoubleNode();
		first.item = input;
		first.nextNode = oldFirst;
		first.prevNode = null;
		if(lastIsEmpty()) {
			last = first;
		}
		else
			oldFirst.prevNode = first;
		N++;
		return oldFirst;
		
	}
	
	//choose a node in the linkedList and add an item into that node
	public void insertBeforeANode(Item input, DoubleNode chosenNode) {
		DoubleNode aNewNode =  new DoubleNode();
		aNewNode.item = input;
		aNewNode.nextNode = chosenNode;
		
		while (!isEmpty()) {
			if(chosenNode == null) {
				insertAtEnd(input);
				break;
			}
			else {
				if(chosenNode.prevNode == null) {
					insertAtEnd(input);
				}
		
				else {
					aNewNode.prevNode = chosenNode.prevNode;
					chosenNode.prevNode.nextNode = aNewNode;
					chosenNode.prevNode = aNewNode;	
				}
				N++;
				break;
			}
			
		}
	}
	
	public void insertAfterANode(Item input, DoubleNode chosenNode) {
		DoubleNode aNewNode = new DoubleNode();
		//create a new node to hold the new item
		aNewNode.item = input;
		//point to the previous node, chosen node
		aNewNode.prevNode = chosenNode;
		//point to the next node which is current next node
		
		while(!isEmpty()) {
			if(chosenNode == null) {
				insertAtEnd(input);
				break;
			}
			
			else {
				aNewNode.nextNode = chosenNode.nextNode;
				if(chosenNode.nextNode == null)
					insertAtEnd(input);
				else {
					//point the current node back to the new node as the prev node
					chosenNode.nextNode.prevNode = aNewNode;
					//point the next node of current node is the new node
					chosenNode.nextNode = aNewNode;	
				}
				N++;
				break;
			}
			
			
		}
	}
	
	public void moveToFront(DoubleNode chosenNode) {
		while(!isEmpty()) {
			if(chosenNode == null) {
				System.out.println("Not in the list");
				break;
			}	
			else {
				DoubleNode oldFirst = first;
				first = new DoubleNode();	
				first.item = chosenNode.item;
				first.nextNode = oldFirst; 
				first.prevNode = null;
				
				if (chosenNode.nextNode == null) {
					chosenNode.prevNode.nextNode = null;
				}
				
				else {
					oldFirst.prevNode = first;
					chosenNode.prevNode.nextNode = chosenNode.nextNode;
					chosenNode.nextNode.prevNode = chosenNode.prevNode;
					chosenNode = null;
				}
				break;
			}
		}
	}
	
	public void moveToEnd(DoubleNode chosenNode) {
		while(!isEmpty()) {
			if(chosenNode == null) {
				System.out.println("Not in the list");
				break;
			}	
			else {
				DoubleNode oldLast = last;
				last = new DoubleNode();	
				last.item = chosenNode.item;
				last.prevNode = oldLast; 
				last.nextNode = null;
				
				if (chosenNode.prevNode == null) {
					chosenNode.nextNode.prevNode = null;
				}
				
				else {
					oldLast.nextNode = last;
					chosenNode.nextNode.prevNode = chosenNode.prevNode;
					chosenNode.prevNode.nextNode = chosenNode.nextNode;
					chosenNode = null;
				}
				break;
			}
		}
	}

	public Item removeBegining() {
		Item item = first.item;
		first = first.nextNode;
		if(firstIsEmpty())
			last = null;
		N--;
		return item;
		
	}
	
	public Item removeTheEnd() {
		Item item = last.item;
		last = last.prevNode;
		if(lastIsEmpty())
			first = null;
		else
			last.nextNode = null;
		N--;
		return item;
	}
	
	public Item removeANode(DoubleNode chosenNode) {
		if(chosenNode != null) {
			if(chosenNode.prevNode != null && chosenNode.nextNode == null) 
				chosenNode = null;	
			
			if(chosenNode.prevNode != null && chosenNode.nextNode != null) {
				chosenNode.prevNode.nextNode = chosenNode.nextNode;
				chosenNode.nextNode.prevNode = chosenNode.prevNode;
			}
				
			if(chosenNode.prevNode == null && chosenNode.nextNode != null) 
				chosenNode.nextNode.prevNode = null;
			
			N--;
		}
		else 
			return null;
		return chosenNode.item;
	}
	
	public void getList() {
		
		DoubleNode currNode = first;
		if (!firstIsEmpty()) {
			/*while(currNode != null) {
				System.out.println(currNode.item + " ");
				currNode = currNode.nextNode;
			}*/
			System.out.print("      ");
			for(int i = 0; i < N; i++) {
				
				if (currNode != null) {
					System.out.print("" + currNode.item);
					if(currNode.nextNode != null) {
						System.out.print(" -> ");
					}
					else
						System.out.print("\n");
					currNode = currNode.nextNode;
				}
				
			}
		}
		else
			System.out.println("List is empty!");
	}
	
	public int getSize() {
		return N;
	}
	
	public DoubleNode searchANode(Item value) {
		DoubleNode currNode = first;
		while(true) {
			if (currNode == null)
				return null;
			if(currNode.item.equals(value))
				return currNode;
			currNode = currNode.nextNode;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoubleLinkedList<String> sentence = new DoubleLinkedList<>();
		sentence.insertAtEnd("C");
		sentence.insertAtEnd("O");
		sentence.insertAtEnd("M");
		sentence.insertAtEnd("P");
		sentence.insertAtEnd("U");
		sentence.insertAtEnd("T");
		sentence.insertAtEnd("E");
		System.out.println("1/ Starting List:         " );
		sentence.getList();
		
		//2.insert M at the beginning
		System.out.println("2/ Insert M at the beginning: ");
		sentence.insertAtBeginning("M");
		sentence.getList();
		//3.insert R at the end
		System.out.println("3/ Insert R at the end: ");
		sentence.insertAtEnd("R");
		sentence.getList();
		//4.remove from the beginning
		System.out.println("4/ Remove the beginning: ");
		sentence.removeBegining();	
		sentence.getList();
		//5.remove from the end
		System.out.println("5/ Remove the end:      ");
		sentence.removeTheEnd();
		sentence.getList();
		//6.insert M before P
		System.out.println("6/ Insert M before P:     ");
		sentence.insertBeforeANode("M", sentence.searchANode("P"));
		sentence.getList();
		//7.insert H before M
		System.out.println("7/ Insert H before M:      ");
		sentence.insertBeforeANode("H", sentence.searchANode("M"));
		sentence.getList();
		//8.insert B before A
		System.out.println("8/ Insert B before A:      ");
		sentence.insertBeforeANode("B", sentence.searchANode("A"));
		sentence.getList();
		
		//9.insert C after P
		System.out.println("9/ Insert C after P:      ");
		sentence.insertAfterANode("C", sentence.searchANode("P"));
		sentence.getList();

		//10.insert L after M
		System.out.println("10/ Insert L after M:    ");
		sentence.insertAfterANode("L", sentence.searchANode("M"));
		sentence.getList();
		
		//11.remove M
		System.out.println("11/ Remove M:      ");
		sentence.removeANode(sentence.searchANode("M"));
		sentence.getList();
		//12.remove G
		System.out.println("12/ Remove M:        ");
		sentence.removeANode(sentence.searchANode("G"));
		sentence.getList();
		//13.Move P to front
		System.out.println("13/ Move P to front ");
		sentence.moveToFront(sentence.searchANode("P"));
		sentence.getList();
		//14.Move L to end 
		System.out.println("14/ Move L to end: ");
		sentence.moveToEnd(sentence.searchANode("L"));
		sentence.getList();	
		}
	}


