package com.zhoukang.algorithms.tree;



public class Main {


    //求二叉树中结点p和q的第一个公共父节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }
        return root;
    }

    //求二叉树中结点p和q的第一个公共父节点
    public TreeNode lowestCommonAncestorBinTree(TreeNode root, TreeNode p, TreeNode q){
        if (root == null){
            return null;
        }
        if (root.value > p.value && root.value>q.value){
            return lowestCommonAncestorBinTree(root.left, p, q);
        }
        if (root.value < p.value && root.value< q.value){
            return lowestCommonAncestorBinTree(root.right, p, q);
        }
        return root;
    }
}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;
}