package com.zhoukang.algorithms.tree;


import java.util.*;

public class Main {
    /**
     *       1
     *   2       5
     * 3   4   6   7
     *
     * @param args
     */
    public static void main(String[] args){
        TreeNode root = new TreeNode();
        root.value = 1;
        root.left = new TreeNode();
        root.left.value = 2;
        root.left.left = new TreeNode();
        root.left.left.value = 3;
        root.left.right = new TreeNode();
        root.left.right.value = 4;

        root.right = new TreeNode();
        root.right.value = 5;
        root.right.left = new TreeNode();
        root.right.left.value = 6;
        root.right.right = new TreeNode();
        root.right.right.value = 7;

        List<TreeNode> list = new ArrayList<>();
        preTraversal(root, list);
        System.out.println(list);
        System.out.println(preTraversal(root));
        System.out.println(preTraversal1(root));
        List<TreeNode> list1 = new ArrayList<>();
        preTraversal1(root, list1);
        System.out.println(list1);
        List<TreeNode> list2 = new ArrayList<>();
        preTraversal2(root, list2);
        System.out.println(list2);
        System.out.println("************************");
        List<TreeNode> list3 = new ArrayList<>();
        inTraversal(root, list3);
        System.out.println(list3);
        List<TreeNode> list4 = new ArrayList<>();
        inTraversal1(root, list4);
        System.out.println(list4);
        System.out.println("************************");
        List<TreeNode> list5 = new ArrayList<>();
        postTraversal(root, list5);
        System.out.println(list5);
        List<TreeNode> list6 = new ArrayList<>();
        postTraversal1(root, list6);
        System.out.println(list6);
        List<TreeNode> list7 = new ArrayList<>();
        postTraversal2(root, list7);
        System.out.println(list7);
        System.out.println("************************");
        System.out.println("层序遍历");
        List<TreeNode> list8 = new ArrayList<>();
        bfs(root, list8);
        System.out.println(list8);
        List<TreeNode> list9 = new ArrayList<>();
        bfs1(root, list9);
        System.out.println(list9);
        System.out.println("深度优先遍历");
        List<TreeNode> list10 = new ArrayList<>();
        dfs(root, list10, new HashSet<>());
        System.out.println(list10);
        System.out.println("指定结点到根结点路径");
        List<TreeNode> list11 = new ArrayList<>();
        contains(root,  root.right.right, list11);
        System.out.println(list11);
        List<TreeNode> list12 = new ArrayList<>();
        getNodePath(root,  root.right.right, list12);
        System.out.println(list12);
        System.out.println("最大深度计算");
        System.out.println(getTreeMaxLevel(root));
        System.out.println(getTreeMaxLevel1(root));
        System.out.println("最小深度计算");
        System.out.println(getTreeMinLevel(root));
        System.out.println(getTreeMinLevel1(root));

    }

    /**
     * 前序遍历
     * @param node
     * @param list
     */
    public static void preTraversal(TreeNode node, List<TreeNode> list){
        list.add(node);
        if (node.left != null) preTraversal(node.left, list);
        if (node.right != null) preTraversal(node.right, list);
    }

    public static  List<TreeNode> preTraversal1(TreeNode node){
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                list.add(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return list;
    }

    public static  List<TreeNode> preTraversal(TreeNode node){
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()){
            if (node != null){
                stack.push(node);
                list.add(node);
            }
            //左结点不为空
            if (node != null && node.left != null){
                node = node.left;
            } else if (node != null && node.right!=null){ //左结点为空的话，访问右结点
                node = node.right;
            } else {
                //node的左右结点都为空，该结点出栈
                node = stack.pop().right;
            }
        }
        return list;
    }

    /**
     * 前序遍历 非递归
     * @param node
     * @param list
     */
    public static void preTraversal1(TreeNode root, List<TreeNode> list){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!=null ){
            stack.push(node);
            list.add(node);
            if (node.left != null){
                node = node.left;
            } else if (node.right != null){
                node = node.right;
            } else {
                do {
                    node = stack.pop().right;
                } while (!stack.isEmpty()&&node == null);
            }
        }
    }

    public static void preTraversal2(TreeNode root, List<TreeNode> list){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!=null || !stack.isEmpty() ){
            if (node != null){
                stack.push(node);
                list.add(node);
            }

            if (node != null && node.left != null){
                node = node.left;
            } else if (node != null && node.right != null){
                node = node.right;
            } else {
                node = stack.pop().right;
            }
        }
    }

    public static void inTraversal(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        if (root.left != null) inTraversal(root.left, list);
        list.add(root);
        if (root.right != null) inTraversal(root.right, list);
    }

    public static void inTraversal1(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!= null || !stack.isEmpty()){
            if (node!=null){
                stack.push(node);
            }
            if (node != null && node.left != null){
                node = node.left;
            } else if (node != null && node.right != null){
                node = node.right;
            } else {
                list.add(stack.pop());
                node = null;
                while (!stack.isEmpty()){
                    TreeNode n = stack.pop();
                    list.add(n);
                    if (n.right!= null){
                        node = n.right;
                        break;
                    }
                }
            }
        }
    }

    public static void postTraversal(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        if (root.left != null) postTraversal(root.left, list);
        if (root.right != null) postTraversal(root.right, list);
        list.add(root);
    }


    public static void postTraversal1(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisiabe = null;
        while (!stack.isEmpty()|| node != null){
            stack.push(node);

            if (node.left != null &&(lastVisiabe == null || (node.left != lastVisiabe &&  node.right != lastVisiabe))){
                node = node.left;
            } else if (node.right != null &&(lastVisiabe == null || (node.left != lastVisiabe &&  node.right != lastVisiabe))){
                node = node.right;
            } else {
                list.add(stack.pop());
                lastVisiabe = node;

                node = null;
                while (!stack.isEmpty()){
                    TreeNode n = stack.peek();
                    if (n.right !=null && lastVisiabe != n.right){
                        node = n.right;
                        break;
                    }
                    lastVisiabe = stack.pop();
                    list.add(lastVisiabe);
                }
            }
        }
    }

    public static void postTraversal2(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> out = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty()|| node != null){
            if (node != null){
                stack.push(node);
                out.push(node);
            }
            if (node != null&&node.right != null){
                node = node.right;
            } else if (node!=null && node.left!=null){
                node = node.left;
            } else {
                node = stack.pop().left;
            }
        }
        while (!out.isEmpty()){
            list.add(out.pop());
        }
    }

    /**
     * 层序遍历、广度遍历
     * @param root
     * @param list
     */
    public static void bfs(TreeNode root, List<TreeNode> list){
        if (root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }

    public static void bfs1(TreeNode root, List<TreeNode> list){
        if (root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            for (int i=0;i<count;i++){
                TreeNode node = queue.poll();
                list.add(node);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 深度优先遍历  前序遍历
     * @param root
     * @param list
     * @param visited
     */
    public static void dfs(TreeNode root, List<TreeNode> list, Set<TreeNode> visited){
        if (root == null) return;
        visited.add(root);
        list.add(root);
        if (root.left != null && !visited.contains(root.left)){
            dfs(root.left,list,visited);
        }

        if (root.right != null && !visited.contains(root.right)){
            dfs(root.right,list,visited);
        }
    }

    public static void dfs1(TreeNode root, List<TreeNode> list){
        if (root == null) return;
        Set<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty()){
            if (node != null){
                stack.push(root);
                visited.add(root);
            }
            if (node.left != null && !visited.contains(node.left)){
                node = node.left;
            } else if (node.right != null){
                node = node.right;
            }
        }
    }

    public static int getTreeMaxLevel(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.max(getTreeMaxLevel(node.left),getTreeMaxLevel(node.right));
    }

    public static int getTreeMaxLevel1(TreeNode root){
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int heigh = 0;
        while (!queue.isEmpty()){
            heigh++;
            int count = queue.size();
            for (int i=0;i<count;i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return heigh;
    }

    public static int getTreeMinLevel(TreeNode node){
        if (node == null) return 0;
        return 1 + Math.min(getTreeMinLevel(node.left),getTreeMinLevel(node.right));
    }

    public static int getTreeMinLevel1(TreeNode root){
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int heigh = 0;
        while (!queue.isEmpty()){
            heigh++;
            int count = queue.size();
            for (int i=0;i<count;i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                if (node.left == null && node.right==null){
                    return heigh;
                }
            }
        }
        return heigh;
    }

    public static boolean isBinarySearchTree(TreeNode node){
        if (node == null) return true;
        TreeNode left = getMaxLeft(node.left);
        TreeNode right = getMinRight(node.right);
        if (left !=null && left.value > node.value){
            return false;
        }
        if (right !=null && right.value < node.value){
            return false;
        }
        return isBinarySearchTree(node.left) && isBinarySearchTree(node.right);
    }

    private static TreeNode getMinRight(TreeNode node) {
        if (node == null) return null;
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    private static TreeNode getMaxLeft(TreeNode node) {
        if (node == null) return null;
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    public static boolean isBinarySearchTree1(TreeNode node){
        List<TreeNode> list = new ArrayList<>();
        inTraversal(node, list);
        TreeNode pre = null;
        for (TreeNode n: list){
            if (pre == null){
                pre = n;
                continue;
            }
            if (pre.value<=n.value){
                pre = n;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isBinarySearchTree2(TreeNode node){
        TreeNode[] pre = new TreeNode[1];
        return isBST(node, pre);
    }

    private static boolean isBST(TreeNode node, TreeNode[] pre) {
        if (node == null){
            return true;
        }
        if (!isBST(node.left, pre)){
            return false;
        }
        if (pre[0] == null){
            pre[0] = node;
        } else {
            if (pre[0].value <= node.value){
                pre[0] = node;
            } else {
                return false;
            }
        }
        if (!isBST(node.right, pre)){
            return false;
        }
        return true;
    }


    /**
     *  求二叉树中结点p和q的第一个公共父节点
     */
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

    /**
     * 求二叉树中结点p和q的第一个公共父节点
     */
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

    public TreeNode lowestCommonAncestorBinTree1(TreeNode root, TreeNode p, TreeNode q){
        while (root != null){
            if (root.value > p.value && root.value>q.value){
                root = root.left;
            } else if (root.value < p.value && root.value< q.value){
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
    //跟结点到指定结点的路径
    public static boolean contains(TreeNode root, TreeNode node, List<TreeNode> list){
        if (root == null) {
            return false;
        }
        if (root == node) {
            list.add(root);
            return true;
        }
        if (contains(root.left, node, list)){
            list.add(root);
            return true;
        } else if (contains(root.right, node, list)){
            list.add(root);
            return true;
        }
        return false;
    }

    //跟结点到指定结点的路径
    public static void getNodePath(TreeNode root, TreeNode node, List<TreeNode> list){
        if (root == null) {
            return;
        }
        Set<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root !=null || !stack.isEmpty()){
            if (root == node){
                stack.push(node);
                break;
            }
            if (root != null){
                visited.add(root);
                stack.push(root);
            }
            if (root!= null && root.left != null && !visited.contains(root.left)){
                root = root.left;
            } else if (root!=null && root.right !=null&& !visited.contains(root.left)){
                root = root.right;
            } else {
                root = stack.pop().right;
            }
        }
        for (TreeNode n : stack){
            list.add(n);
        }
    }

    /**
     * x的y次方
     * @param x
     * @param y
     * @return
     */
    public static int pow(int x, int y){
        if (y < 0){
            return 1/pow(x, -y);
        }
        if (y == 0){
            return 1;
        }
        if (y % 2 ==0){
            int t = pow(x, y/2);
            return t*t;
        } else {
            int t = pow(x, y/2);
            return t*t * x;
        }
    }

    public static int pow1(int x, int y){
        if (y<0){
            x = 1/x;
            y = -y;
        }
        int count = 1;
        while (count<y){
           // if (count )

            count = 2*count;
        }
        return 0;
    }
}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}