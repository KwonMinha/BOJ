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
	public Node root;
	
	public void createNode(Node node, int key) {
		if(root == null) {
			root = new Node(key);
		} else {
			if(key < node.data) {
				//node.left = createNode(node.left, key);
			} else if(key > node.data) {
				//node.right = createNode(node.right, key);
				
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			
		}
	}

}
