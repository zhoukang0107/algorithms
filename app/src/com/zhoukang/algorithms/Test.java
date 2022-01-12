package com.zhoukang.algorithms;


import java.util.*;

public class Test {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        print(reverseKGroup(head, 3));

        //[1,2,3,4,5,null,7,8]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        ListNode[] nodes = listOfDepth1(root);
    }


    

    static void print(ListNode node){
        while (node != null){
            System.out.print(node.val+ "->");
            node = node.next;
        }

    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
   }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k<=1){
            return head;
        }
        ListNode first = null;
        ListNode last = null;
        int count = 0;
        ListNode pre = null;
        ListNode next;
        ListNode h = head;
        while (head != null){
            if (count==0){
                first = head;
                count++;
                head= head.next;
            } else if (count==k-1){
                last = head;
                next = head.next;
                reverseList(first,last);
                if (pre != null){
                    pre.next = last;
                } else {
                    h = last;
                }
                pre = first;
                pre.next = next;
                head = next;
                count = 0;
            } else {
                count++;
                head= head.next;
            }
        }
        return h== null?head:h;
    }

    public static void reverseList(ListNode head1, ListNode head2){
        ListNode node = head1;
        ListNode pre = null;
        while (node != head2.next && node.next != head2.next) {
            if (pre == null) {
                pre = head1;
                head1 = head1.next;
            } else {
                ListNode t = head1.next;
                head1.next = pre;
                pre = head1;
                head1 = t;
            }
        }
    }



    public int numComponents(ListNode head, int[] G) {
        if (head == null || G==null || G.length <= 0){
            return 0;
        }
        ListNode node = head;
        int count = 0;
        boolean preOk = false;
        while (node != null){
            if (isInG(G, node.val)){
                if (!preOk){
                    count++;
                    preOk = true;
                }
            } else {
                preOk = false;
            }
        }
        return count;
    }

    public boolean isInG(int[] G ,int val){
        for (int i=0;i<G.length;i++){
            if (G[i] == val){
                return true;
            }
        }
        return false;
    }


    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(tree);
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        while (!stack.isEmpty()){
            int size = stack.size();
            ListNode lNode = null;
            for (int i=0;i<size;i++){
                TreeNode node = stack.pop();
                if (node.left != null){
                    stack.push(node.left);
                }
                if (node.right != null){
                    stack.push(node.right);
                }
                if (lNode == null){
                    lNode = new ListNode(node.val);
                } else {
                    lNode.next = new ListNode(node.val);
                    lNode = lNode.next;
                }

            }
            list.add(lNode);
        }
        ListNode[] arr = new ListNode[list.size()];
        for (int i=0;i<list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;

    }


    public static ListNode[] listOfDepth1(TreeNode tree) {
        if (tree == null){
            return null;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(tree);
        while (!queue.isEmpty()){
            int size = queue.size();
            ListNode head = null;
            ListNode t = null;
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (head == null){
                    head = new ListNode(node.val);
                    t = head;
                } else {
                    t.next = new ListNode(node.val);
                    t = t.next;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(head);
        }


        return list.toArray(new ListNode[0]);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null){
            return new LinkedList<List<Integer>>();
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        int level = 0;
        dfs(root, level, result);
        return result;
    }

    public void dfs(TreeNode root,int level,List<List<Integer>> result) {
        if (result.size()<=level){
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.add(root.val);
            result.add(list);
        } else {
            LinkedList<Integer> list = (LinkedList<Integer>) result.get(level);
            if (level % 2 ==0){
                list.addFirst(root.val);
            } else {
                list.addLast(root.val);
            }
        }
        if (root.left != null){
            dfs(root.left, level + 1, result);
        }
        if (root.right != null){
            dfs(root.right, level + 1, result);
        }
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}