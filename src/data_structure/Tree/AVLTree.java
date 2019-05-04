package data_structure.Tree;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/11 15:36
 * @Version: 1.0
 */
public class AVLTree <T extends Comparable<T>>{
    private AVLTreeNode root;
    public class AVLTreeNode {
        T val;
        AVLTreeNode left;
        AVLTreeNode right;
        int height;

        AVLTreeNode(T val,AVLTreeNode left,AVLTreeNode right){
            this.height = 0;
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    public void insert(T val){
        root = insert(root,val);
    }
    private AVLTreeNode insert(AVLTreeNode node,T val){
        if (node == null){
            node = new AVLTreeNode(val,null,null);
        }else {
            if (val.compareTo(node.val) > 0){
                node.right = insert(node.right,val);
                if (heightOf(node.right) - heightOf(node.left) > 1){
                    if (val.compareTo(node.right.val) > 0)
                        node = rightRightRotation(node);
                    else
                        node = rightLeftRotation(node);
                }
            }else if (val.compareTo(node.val) < 0){
                node.left = insert(node.left,val);
                if (heightOf(node.left) - heightOf(node.right) > 1){
                    if (val.compareTo(node.left.val) > 0)
                        node = leftRightRotation(node);
                    else
                        node = leftLeftRotation(node);
                }
            }
        }
        node.height = Math.max(heightOf(node.left),heightOf(node.right))+1;
        return node;
    }

    public void delete(T val){

    }
    private AVLTreeNode delete(AVLTreeNode node,T val){
        if (node != null){
            if (val.compareTo(node.val) > 0){
                node.right = delete(node.right,val);
                if (heightOf(node.left) - heightOf(node.right) > 1){
                    if (heightOf(node.left.left) > heightOf(node.left.right))
                        node = leftLeftRotation(node);
                    else
                        node = leftRightRotation(node);//
                }
            }else if (val.compareTo(node.val) < 0){
                node.left = delete(node.left,val);
                if (heightOf(node.right)  - heightOf(node.left) > 0){
                    if (heightOf(node.right.left) > heightOf(node.right.right))
                        node = rightLeftRotation(node);
                    else
                        node = rightRightRotation(node);
                }
            }else {
                node = null;
            }
        }
        return node;
    }

    public int heightOfRoot(){
        return heightOf(root);
    }
    private int heightOf(AVLTreeNode node){
        if (node != null)
            return node.height;
        return 0;
    }
    private AVLTreeNode parentOf(AVLTreeNode node){
        AVLTreeNode p = root;
        AVLTreeNode res = null;
        while (p != node){
            res = p;
            if (node.val.compareTo(p.val) > 0){
                p = p.right;
            }else if (node.val.compareTo(p.val) < 0){
                p = p.left;
            }
        }
        return res;
    }
    private AVLTreeNode leftOf(AVLTreeNode parent){
        if (parent == null)
            return null;
        return parent.left;
    }
    private void setChild(AVLTreeNode parent,AVLTreeNode child,boolean dir){
        if (dir)
            setLeft(parent,child);
        else
            setRight(parent,child);
    }
    private void setLeft(AVLTreeNode parent,AVLTreeNode left){
        if (parent == null)
            return;
        parent.left = left;
    }
    private void setRight(AVLTreeNode parent,AVLTreeNode right){
        if (parent == null)
            return;
        parent.right = right;
    }
    private void resetHeight(AVLTreeNode node){
        node.height = Math.max(heightOf(node.left),heightOf(node.right)) +1;
    }
    private AVLTreeNode rotation(AVLTreeNode node,int mode){
        AVLTreeNode child = null;
        switch (mode){
            case 1:
                child = _RightRotation(node);
                break;
            case 2:
                child = _LeftRotation(node);
                break;
            case 3:
                child = node.left;
                setLeft(node,_LeftRotation(child));
                child = _RightRotation(node);
                break;
            case 4:
                child = node.right;
                setRight(node,_RightRotation(child));
                child = _LeftRotation(node);
                break;
        }
        return child;
    }
    //Actually Left or RightRotation,not visually.
    private AVLTreeNode _RightRotation(AVLTreeNode node){
        AVLTreeNode child;
        child = node.left;
        setLeft(node,child.right);
        setRight(child,node);
        resetHeight(node);
        resetHeight(child);
        return child;
    }
    private AVLTreeNode _LeftRotation(AVLTreeNode node){
        AVLTreeNode child;
        child = node.right;
        setRight(node,child.left);
        setLeft(child,node);
        //调整高度
        resetHeight(node);
        resetHeight(child);
        return child;
    }
    //Short Cut of rotation mode.
    private AVLTreeNode leftLeftRotation(AVLTreeNode node){
        return rotation(node,1);
    }
    private AVLTreeNode rightRightRotation(AVLTreeNode node){
        return rotation(node,2);
    }
    private AVLTreeNode leftRightRotation(AVLTreeNode node){
        return rotation(node,3);
    }
    private AVLTreeNode rightLeftRotation(AVLTreeNode node){
        return rotation(node,4);
    }

    public void inOrder(){
        Stack<AVLTreeNode> stack = new Stack<>();
        AVLTreeNode p = root;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            System.out.println(p.val);
            p = p.right;
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
//        Integer[] list = {15,561,562,8,46,1,87,6,1,6,4,32,154,321,321,3};
//        for (int val:list){
//            tree.insert(val);
//        }
//        tree.inOrder();
    }
}
