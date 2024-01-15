package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1991 {
    static class Tree {
        char key;
        Tree left;
        Tree right;

        public Tree(char key) {
            this.key = key;
            this.left = this.right = null;
        }

        public Tree(char key, Tree left, Tree right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public void setLeft(char parent, char left) {
            if (this.key == parent) {
                this.left = new Tree(left);
                return;
            }
            if (this.left != null) {
                this.left.setLeft(parent, left);
            }
            if (this.right != null) {
                this.right.setLeft(parent, left);
            }
        }

        public void setRight(char parent, char right) {
            if (this.key == parent) {
                this.right = new Tree(right);
                return;
            }
            if (this.left != null) {
                this.left.setRight(parent, right);
            }
            if (this.right != null) {
                this.right.setRight(parent, right);
            }

        }

        public void preOrder() {
            System.out.print(this.key);
            if (this.left != null) this.left.preOrder();
            if (this.right != null) this.right.preOrder();
        }

        public void midOrder() {
            if (this.left != null) this.left.midOrder();
            System.out.print(this.key);
            if (this.right != null) this.right.midOrder();
        }

        public void postOrder() {
            if (this.left != null) this.left.postOrder();
            if (this.right != null) this.right.postOrder();
            System.out.print(this.key);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Tree root = new Tree('A');

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (left != '.') root.setLeft(parent, left);
            if (right != '.') root.setRight(parent, right);
        }

        root.preOrder();
        System.out.println();
        root.midOrder();
        System.out.println();
        root.postOrder();
        System.out.println();
    }
}
