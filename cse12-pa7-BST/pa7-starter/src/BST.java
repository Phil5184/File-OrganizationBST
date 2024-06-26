import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */

public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	Node<K, V> root;
	int size = 0;
	BST() {
		this.root = null;
	}
	BST(Node<K,V> root) {
		this.root = root;
	}
	
	private Node<K, V> put(Node<K,V> node, K key, V value) {
		if (node == null) {
			this.size += 1;
			return new Node<K, V>(key, value, null, null);
		}
		int comp = node.key.compareTo(key);
		if (comp < 0) {
			node.right = this.put(node.right,key, value);
			return node;
		}
		else if (comp > 0) {
			node.left = this.put(node.left, key, value);
			return node;
		}
		else {
			return node;
		}
	}
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (this.containsKey(key) == true) {
			return false;
		}
		else {
			this.root = this.put(this.root, key, value);
			return true;
		}
		
	}
	
	/*private Node<K, V> replace(Node<K,V> node, K key, V newValue) {
		
		int comp = node.key.compareTo(key);
		if (comp < 0) {
			node.right = this.replace(node.right,key, newValue);
			return node;
		}
		else if (comp > 0) {
			node.left = this.replace(node.left, key, newValue);
			return node;
		}
		else {
			node.setValue(newValue);
			return node;
		}
	}*/

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
//		if (this.containsKey(key) == false) {
//			return false;
//		}
//		else {
//			this.root = this.replace(this.root, key, newValue);
//			return true;
//		}
		Node<K,V> current = this.root;
		while(current != null) {
			if (current.key.compareTo(key) < 0) {
				current = current.right;
			}
			else if(current.key.compareTo(key) > 0) {
				current = current.left;
			}
			else {
				current.setValue(newValue);
				return true;
			}
		}
		return false;
		
	}
	
		
		
	private Node<K,V> remove(Node<K,V> node, K key) {
		if (node.key.compareTo(key) < 0) {
			node.right = remove(node.right, key);
			return node;
		}
		else if(node.key.compareTo(key) > 0) {
			node.left = remove(node.left, key);
			return node;
		}
		else {
			//if only one child
			if (node.left == null) {
				return node.right;
			}
			else if (node.right == null) {
				return node.left;
			}
			//find successor if both children exist
			Node<K,V> Parent = node;
			Node<K,V> successor = node.right;
			while (successor.left != null) {
				Parent = successor;
				successor = successor.left;
			}
			node.key = successor.key;
			node.value = successor.value;
			//skip over successor to delete it
			if (Parent.equals(node)) {
				Parent.right = successor.right;
			}
			else {
				Parent.left = successor.right;
			}
			return node;
		}
		
		
	
	}
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (this.containsKey(key) == false) {
			return false;
		}
		else {
			this.root = remove(this.root, key);
			this.size -= 1;
			return true;
		}

		
		
		
		
				/*if (this.containsKey(key) == false) {
			return false;
		}
		Node<K,V> current = this.root;
		while (current.key != null) {
			if (current.key.compareTo(key) < 0) {
				current = current.right;
			}
			else if (current.key.compareTo(key) > 0) {
				current = current.left;
			}
			else {
				break;
			}
		}
		//Node<K,V> swap = findSuccessor(current);
		//find successor (finder) and keep track of parent;
		Node<K,V> finder = current;
		if (finder.right == null) {
			continue;
		}
		else {
			finder = finder.right;
			while (finder.left != null) {
				finder = finder.left;
			}
			return finder;
		}
		current.key = finder.key;
		current.value = finder.value;
		//how to make delete successor after swap?
		swap = null;
		return true;*/
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		else {
			if (this.containsKey(key) == true) {
				this.replace(key, value);
			}
			else {
				this.put(key, value);
			}
		}
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		Node<K,V> current = this.root;
		while(current != null) {
			if (current.key.compareTo(key) < 0) {
				current = current.right;
			}
			else if(current.key.compareTo(key) > 0) {
				current = current.left;
			}
			else {
				return current.value;
			}
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		Node<K,V> current = this.root;
		while(current != null) {
			if (current.key.compareTo(key) < 0) {
				current = current.right;
			}
			else if(current.key.compareTo(key) > 0) {
				current = current.left;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	
	
	private ArrayList<K> keys(ArrayList<K> list, Node<K,V> node) {
		if (node.left != null) {
			list = keys(list, node.left);
		}
		list.add(node.key);
		if (node.right != null) {
			list = keys(list,node.right);
		}
		return list;
	}
	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		ArrayList<K> empty = new ArrayList<>();
		if (this.size() == 0) {
			return empty;
		}
		ArrayList<K> toReturn = new ArrayList<>();
		toReturn = keys(toReturn, this.root);
		return toReturn;
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables
		 */
		K key;
		V value;
		Node<K, V> left;
		Node<K, V> right;
		public Node(K key, V value, Node<K,V> left, Node<K,V> right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
			
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
			
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
		}
		
		
	}
	 
}