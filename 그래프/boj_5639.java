package 그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj_5639 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
        bw.close();
    }

    static void postOrder(Node node) throws IOException {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        bw.write(node.key + "\n");
    }

    static class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public void insert(int key) {
            if (key < this.key) {
                if (this.left == null)
                    this.left = new Node(key);
                else
                    this.left.insert(key);
                
            } else {
                if (this.right == null)
                    this.right = new Node(key);
                else
                    this.right.insert(key);
            }
        }
    }
    
}
