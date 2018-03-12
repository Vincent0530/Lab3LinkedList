package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		SNode<E> sNuevo = (SNode<E>) nuevo;
		if(length == 0){
			first = sNuevo;
			last = sNuevo;
		}
		sNuevo.setNext(first);
		first = sNuevo;
		length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target == first)
			this.addFirstNode(nuevo); 
		else { 
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		
	}
	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		else{
			return first;
		}
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (last == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		else{
			return last;
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		
		
		// Pre: target is a node in the list
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)  
			throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
		
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// Pre: target is a node in the list
			if (target == first)  
				throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
			else 
				return findNodePrevTo(target);
		
	}

	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list; hence, the list is not empty
		
		if (target == first) 
			first = first.getNext(); 
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
					prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
