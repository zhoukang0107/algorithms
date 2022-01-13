package com.zhoukang.algorithms.tree;

import java.util.List;

public class BinaryTree {
    public static void main(String[] args){

    }

    /**
     * 中序递归遍历
     * @param root
     * @param list
     */
    public static void inOrder(TreeNode root, List<TreeNode> list){
        if (root == null) {
            return;
        }
        if (root.left != null){
            inOrder(root.left, list);
        }
        list.add(root);
        if (root.right != null){
            inOrder(root.right, list);
        }
    }

    /**
     * 前序递归遍历
     */
    public static void preOrder(TreeNode root, List<TreeNode> list){
        if (root == null) {
            return;
        }
        list.add(root);
        if (root.left != null){
            inOrder(root.left, list);
        }
        if (root.right != null){
            inOrder(root.right, list);
        }
    }

    /**
     * 后序递归遍历
     */
    public static void postOrder(TreeNode root, List<TreeNode> list){
        if (root == null) {
            return;
        }
        if (root.left != null){
            inOrder(root.left, list);
        }
        if (root.right != null){
            inOrder(root.right, list);
        }
        list.add(root);
    }

}
