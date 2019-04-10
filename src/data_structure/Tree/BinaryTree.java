package data_structure.Tree;

import data_structure.nodes.NormalTreeNode;
import data_structure.nodes.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * @Author: BrotherSh
 * @Date: 2019/4/3 18:04
 * @Version: 1.0
 */
public class BinaryTree {
    public static List<Integer> preOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null){
            while (p != null){
                res.add(p.val);
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()){
                p = stack.pop();
                p = p.right;
            }

        }
        return res;
    }
    public static List<Integer> inOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()){
                p = stack.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode last = null;
        while (!stack.isEmpty() || p != null){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()){
                p = stack.peek();
                if (p.right == null || p.right == last){
                    res.add(stack.pop().val);
                    last = p;
                    p = null;
                }else {
                    p = p.right;
                }
            }
        }
        return res;
    }
    public static List<Integer> BFS(NormalTreeNode root){
        LinkedList<NormalTreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        NormalTreeNode p;
        queue.addLast(root);
        while (!queue.isEmpty()){
            p = queue.poll();
            res.add(p.val);
            for (NormalTreeNode child: p.children){
                queue.addLast(child);
            }
        }
        return res;
    }
    public static List<Integer> DFS(NormalTreeNode root){
        Stack<NormalTreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        NormalTreeNode p;
        while (!stack.isEmpty()){
            p = stack.pop();
            res.add(p.val);
            for (NormalTreeNode child: p.children){
                stack.push(child);
            }
        }
        return res;
    }
}


