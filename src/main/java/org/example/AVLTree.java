package org.example;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class AVLTree {
    private Node root;
    boolean curFlag = false;
    int maxSeq = 1;

    public boolean add(int key) {
        if (findNode(root, key) == null) {
            root = bstInsert(root, key);
            return true;
        }
        return false;
    }

    public void changeFlag(int key) {
        Node node = findNode(root, key);
        if (node != null) {
            node.flag = !node.flag;
        }
    }

    private int Height(Node key) {
        if (key == null)
            return 0;

        else
            return key.height;
    }


    private int Balance(Node key) {
        if (key == null)
            return 0;

        else
            return (Height(key.right) - Height(key.left));
    }


    private void updateHeight(Node key) {
        int l = Height(key.left);
        int r = Height(key.right);

        key.height = Math.max(l, r) + 1;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node balanceTree(Node root) {
        updateHeight(root);

        int balance = Balance(root);

        if (balance > 1) //R
        {
            if (Balance(root.right) < 0)//RL
            {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            } else //RR
                return rotateLeft(root);
        }

        if (balance < -1)//L
        {
            if (Balance(root.left) > 0)//LR
            {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            } else//LL
                return rotateRight(root);
        }

        return root;
    }


    private Node bstInsert(Node root, int key) {
        // Performs normal BST insertion
        if (root == null)
            return new Node(key);

        else if (key < root.value)
            root.left = bstInsert(root.left, key);

        else
            root.right = bstInsert(root.right, key);

        // Balances the tree after BST Insertion
        return balanceTree(root);
    }

    private Node  successor(Node root) {
        if (root.left != null)
            return successor(root.left);

        else
            return root;
    }

    private Node findNode(Node root, int key) {
        if (root == null || key == root.value)
            return root;

        if (key < root.value)
            return findNode(root.left, key);

        else
            return findNode(root.right, key);
    }



    private void inOrder(Node cur, List<String> res) {
        if (cur == null) {
            return;
        }

        if (cur.left != null)
            inOrder(cur.left, res);
        res.add( String.format("k:%d f:%b", cur.value, cur.flag) );
        if (cur.right != null)
            inOrder(cur.right, res);

    }

    public Integer calculateMaxUnicSeq() {
        maxSeq = 1;
        curFlag = false;
        finder(root, new int[]{1});
        return maxSeq;
    }

    private void finder(Node cur, int[] curLength) {
        if (cur == null) {
            return;
        }

        if (cur.left != null) finder(cur.left, curLength);
        if(curFlag != cur.flag) {
            curFlag = cur.flag;
            curLength[0] += 1;
        } else {
            curLength[0] = 1;
        }
        maxSeq = Math.max(maxSeq, curLength[0]);
        if (cur.right != null) finder(cur.right, curLength);
    }

    @Getter
    static class Node {
        int value;
        int height;
        boolean flag;
        int maxSeq;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.height = 1;
        }

    }

}