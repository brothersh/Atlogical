package data_structure.Tree;

/**
 * @Author: BrotherSh
 * @Date: 2019/3/24 16:41
 * @Version: 1.0
 */
public class RBTreeNode<T extends Comparable<T>> {
    private static final char RED = 'R';
    private static final char BLACK = 'B';
    private static final boolean LEFT = true;
    private static final boolean RIGHT = false;


    private static RBTreeNode _ROOT_NODE;

    private RBTreeNode left = null;
    private RBTreeNode right = null;
    private RBTreeNode parent = null;

    private char color;
    private T val;

    // Create A Non-Terminal NormalTreeNode
    public RBTreeNode(Object key,RBTreeNode parent,boolean dir){
        setColor(RED);
        setVal(key);
        setParentChildRelation(parent,this,dir);
    }
    private RBTreeNode(){
        setColor(BLACK);
        setVal(null);
    }

    /*
    Common setters
     */
    private void setParent(RBTreeNode newParent){
        parent = newParent;
    }
    private void setRight(RBTreeNode newRight){
        right = newRight;
    }
    private void setLeft(RBTreeNode newLeft){
        left = newLeft;
    }
    private void setColor(char newColor){
        color = newColor;
    }
    private void setVal(Object key){
        val = (T) key;
    }

    private static void setParent(RBTreeNode parent,RBTreeNode child){
        if (child != null){
            child.setParent(parent);
        }
    }
    private static void setRight(RBTreeNode parent,RBTreeNode right){
        if (parent != null){
            parent.setRight(right);
        }
    }
    private static void setLeft(RBTreeNode parent,RBTreeNode left){
        if (parent != null){
            parent.setLeft(left);
        }
    }
    private static void setColor(RBTreeNode node,char color){
        if (node != null){
            node.setColor(color);
        }
    }
    private static void setVal(RBTreeNode node,Object key){
        if (node != null){
            node.setVal(key);
        }
    }

    private static RBTreeNode getLeft(RBTreeNode node){
        if (node == null)
            return null;
        return node.left;
    }
    private static RBTreeNode getRight(RBTreeNode node){
        if (node == null)
            return null;
        return node.right;
    }
    private static RBTreeNode getParent(RBTreeNode node){
        if (node == null)
            return null;
        return node.parent;
    }
    private static RBTreeNode getRoot(){
        return _ROOT_NODE;
    }
    private static char getColor(RBTreeNode node){
        if (node == null)
            return BLACK;
        return node.color;
    }
    private static Object getVal(RBTreeNode node){
        if (node == null){
            return null;
        }
        return node.val;
    }


    /*
    Characteristic Of a R-B NormalTreeNode
     */

    private static boolean isRoot(RBTreeNode node){
        return node == _ROOT_NODE;
    }


    /*
    Common Static Method For RED-BLACK-TREE
     */
    public static boolean search(RBTreeNode root,Object key){
        return searchForKey(root,key) != null;
    }
    private static RBTreeNode searchForKey(RBTreeNode root, Object key){
        if (root == null){
            return null;
        }else {
            if (root.val.compareTo(key) == 0){
                return root;
            }else if (root.val.compareTo(key) < 0){
                return searchForKey(root.right,key);
            }else {
                return searchForKey(root.left,key);
            }
        }
    }

    public static RBTreeNode insert(RBTreeNode root,Object key){
        _ROOT_NODE = root;
        RBTreeNode parent = null;
        boolean dir = false;
        if (root == null){
            _ROOT_NODE = new RBTreeNode(key,null,false);
            setColor(_ROOT_NODE,BLACK);
            return _ROOT_NODE;
        }
        while (root != null){       //Find Where To Insert The Key
            parent = root;
            if (root.val.compareTo(key) > 0) {
                root = getLeft(root);
                dir = LEFT;
            }else if (root.val.compareTo(key) < 0) {
                root = getRight(root);
                dir = RIGHT;
            }else {
                return _ROOT_NODE;
            }
        }
        root = new RBTreeNode(key,parent,dir);  //Create New Non-Terminal NormalTreeNode And Initialize It
        fixed_insert(root);
        return _ROOT_NODE;
    }
    /*  Insert-relative Methods Group   */
    private static void fixed_insert(RBTreeNode node){
        RBTreeNode tmp = node;
        while (tmp != null && !isRoot(tmp) && getColor(tmp.parent) == RED){
            RBTreeNode parent = getParent(tmp);
            RBTreeNode grandpa = getParent(parent);
            boolean dir_p = getLeft(grandpa) == parent;
            boolean dir_t = getLeft(parent) == tmp;
            RBTreeNode uncle = (dir_p)?getRight(grandpa):getLeft(grandpa);
            if (getColor(uncle) == RED){
                setColor(parent,BLACK);
                setColor(uncle,BLACK);
                setColor(grandpa,RED);
                tmp = grandpa;
            }else {
                if (dir_p ^ dir_t){
                    tmp = parent;
                    rotation(tmp,dir_p);
                }
                setColor(getParent(tmp),BLACK);
                setColor(getParent(getParent(tmp)),RED);
                rotation(getParent(getParent(tmp)),!dir_p);
            }
        }
        setColor(_ROOT_NODE,BLACK);
    }

//    public static void delete(RBTreeNode root,Object val){
//        RBTreeNode tmp = searchForKey(root,val);
//        do {
//            if (tmp.left.isLeaf() && tmp.right.isLeaf()){
//                tmp.setLeaf(true);
//                tmp.setColor(BLACK);
//                break;
//            }else if (!tmp.left.isLeaf() && !tmp.right.isLeaf()){
//                RBTreeNode successor = getSuccessor(tmp);
//                tmp.setVal(successor.val);
//                tmp = successor;
//            }else {
//                RBTreeNode son;
//                RBTreeNode parent = tmp.parent;
//                RBTreeNode brother = tmp.brother;
//                if (tmp.left.isLeaf()){
//                    son = tmp.right;
//                }else {
//                    son = tmp.left;
//                }
//                parent.setChild(son,tmp.direction);
//                son.setParent(parent);
//                brother.setBrother(son);
//                son.setBrother(brother);
//                boolean color = tmp.color;
//                if (!color){
//                    fixed_delete(son);
//                }
//                break;
//            }
//        }while (true);
//
//    }
//    /*
//    Delete-relative Methods Group
//     */
//    private static void fixed_delete(RBTreeNode node){
//        RBTreeNode tmp = node;
//        do {
//            if (tmp.isRed()){
//                tmp.setColor(BLACK);
//                break;
//            }else if (tmp.brother.isRed()){
//                fixed_delete_case_1(tmp);
//            }else if (!tmp.brother.left.isRed() && !tmp.brother.right.isRed()){
//                fixed_delete_case_2(tmp);
//                if (tmp.parent.isRed()){
//                    tmp.parent.setColor(BLACK);
//                    break;
//                }else {
//                    tmp = tmp.parent;
//                }
//            }else if (tmp.brother.left.isRed() && !tmp.brother.right.isRed()){
//                fixed_delete_case_3(tmp);
//            }else {
//                fixed_delete_case_4(tmp);
//                break;
//            }
//        }while (!tmp.isRoot());
//    }
//    private static void fixed_delete_case_1(RBTreeNode node){
//        node.parent.setColor(RED);
//        node.brother.setColor(BLACK);
//        rotation(node.parent,LEFT);
//    }
//    private static void fixed_delete_case_2(RBTreeNode node){
//        node.brother.setColor(RED);
//    }
//    private static void fixed_delete_case_3(RBTreeNode node){
//        node.brother.setColor(RED);
//        node.brother.left.setColor(BLACK);
//        rotation(node.brother,RIGHT);
//    }
//    private static void fixed_delete_case_4(RBTreeNode node){
//        boolean color_p = node.parent.color;
//        node.parent.setColor(node.brother.color);
//        node.brother.setColor(color_p);
//        node.brother.right.setColor(BLACK);
//        rotation(node.parent,LEFT);
//    }

    public static void delete(RBTreeNode root,Object key){
        RBTreeNode _nil = null;
        RBTreeNode tmp = searchForKey(root,key);
        if (tmp == null)
            return;
        if (getLeft(tmp) != null && getRight(tmp) != null){
            RBTreeNode successor = getSuccessor(tmp);
            setVal(tmp,getVal(successor));
            tmp = successor;
        }
        if (getLeft(tmp) == null && getRight(tmp) == null){
            _nil = new RBTreeNode();
            setLeft(tmp,_nil);
        }
        RBTreeNode child = (getLeft(tmp) != null)?getLeft(tmp):getRight(tmp);       //  Replace the deleted node with it"s only child
        boolean dir = (getLeft(getParent(tmp)) == tmp);                            //
        char color_del = getColor(tmp);                                            //
        setParentChildRelation(getParent(tmp),child,dir);                          //
        tmp = child;
        char color = getColor(tmp);
        if (color == RED || color_del == RED){                                    // If deleted node is red or it's only-child is red,there's no NECESSARY to re-balance
            setColor(tmp,BLACK);                                                  //
        }                                                                         //
        while (tmp != null && !isRoot(tmp) && color == BLACK){
            dir = getLeft(getParent(tmp)) == tmp;
            RBTreeNode bro = (dir)?getRight(getParent(tmp)):getLeft(getParent(tmp));
            if (getColor(bro) == RED){                                      //Case 1
                setColor(bro,BLACK);
                setColor(getParent(tmp),RED);
                rotation(getParent(tmp),dir);
                bro = (dir)?getRight(getParent(tmp)):getLeft(getParent(tmp));
            }
            if (getColor(getLeft(bro)) == BLACK && getColor(getRight(bro)) == BLACK){   //Case 2
                color = getColor(getParent(tmp));
                setColor(getLeft(bro),RED);
                setColor(getRight(bro),RED);
                tmp = getParent(tmp);
            }else {
                char tmp_color = getColor((dir)?getRight(bro):getLeft(bro));
                if (tmp_color == BLACK){                      //Case 3
                    setColor(bro,RED);
                    setColor((dir)?getLeft(bro):getRight(bro),BLACK);
                    rotation(bro,!dir);
                }
                bro = (dir)?getRight(getParent(tmp)):getLeft(getParent(tmp));    //Case 4
                char tmp_c = getColor(getParent(tmp));
                setColor(getParent(tmp),getColor(bro));
                setColor(bro,tmp_c);
                rotation(getParent(tmp),dir);
                tmp = _ROOT_NODE;
            }
        }
        if (getLeft(getParent(_nil)) == _nil){
            setLeft(getParent(_nil),null);
        }else {
            setRight(getParent(_nil),null);
        }
    }
    /*  Common Characteristic Methods Such As ROTATION */
    private static void rotation(RBTreeNode node,boolean direction){
        RBTreeNode p = getParent(node);
        RBTreeNode s = (direction)?getRight(node):getLeft(node);
        RBTreeNode ss = (direction)?getLeft(s):getRight(s);


        boolean dir = getLeft(p) == node;

        setParentChildRelation(p,s,dir);
        setParentChildRelation(s,node,direction);
        setParentChildRelation(node,ss,!direction);
    }
    private static RBTreeNode getSuccessor(RBTreeNode node){
        RBTreeNode tmp = getRight(node);
        while (getLeft(tmp) != null){
            tmp = getLeft(tmp);
        }
        return tmp;
    }
    private static void setParentChildRelation(RBTreeNode parent,RBTreeNode child,boolean direction){
        if (parent == null){
            _ROOT_NODE = child;
        }
        setParent(parent,child);
        if (direction){
            setLeft(parent,child);
        }else {
            setRight(parent,child);
        }
    }

    public static void print(RBTreeNode root){
        if (root != null){
            print(getLeft(root));
            System.out.println(getVal(root));
            print(getRight(root));
        }
    }
    private static void blank(int i){
        for (int j = 0;j < i;j++){
            System.out.print(" ");
        }
    }

}
