/**
 * @author Minha Gwon
 * @date 2021. 1. 2.
 * 이진 검색 트리 
 * https://www.acmicpc.net/problem/5639
 */

import java.util.Scanner;

class Node {
	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
	}
}


public class Main {
	public static Node root;

	public static Node insertNode(Node node, int key) {
		if(node == null) {
			return createNode(key);
		}

		if(key < node.data) {
			node.left = insertNode(node.left, key);
		} else if(key > node.data) {
			node.right = insertNode(node.right, key);
		}

		return node;
	}
	
	public static Node insertNode2(Node node, int key) {
		if(node == null) {
			root = new Node(key);
			return root;
			
//			node = new Node(key);
//			return node;
		}

		if(key < node.data) {
			node.left = insertNode2(node.left, key);
		} else if(key > node.data) {
			node.right = insertNode2(node.right, key);
		}

		return node;
	}

	public static Node insert(Node root, int key) {
		Node parent = root;
		Node newNode = new Node(key);

		if(parent ==  null) {
			return newNode;
		} else if(parent.data > newNode.data) {
			parent.left = insert(parent.left, key);
			return parent;	
		} else if(parent.data < newNode.data) {
			parent.right = insert(parent.right, key);
			return parent;	
		} else {
			return parent;
		}
	}

	public static Node createNode(int key) {
		Node node = new Node(key);
		return node;
	}

	//후위순회 Postorder : Left -> Right -> Root
	public void postOrder(Node node) {
		if(node != null) {
			if(node.left != null) postOrder(node.left);
			if(node.right != null) postOrder(node.right);
			System.out.println(node.data);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Main m = new Main();

		while(sc.hasNext()) {
			//m.root = insertNode2(m.root, Integer.parseInt(sc.nextLine()));
			m.root = insert(m.root, Integer.parseInt(sc.nextLine()));
		}
		
		// mac북에서 ctrl + d 누르면 입력받기 종료 
		m.postOrder(m.root);
	}

}
