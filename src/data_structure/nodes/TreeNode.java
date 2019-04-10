package data_structure.nodes;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/9 10:40
 * @Version: 1.0
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode insert(TreeNode root,int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            if (val < root.val) {
                root.left = insert(root.left, val);
            } else if (val > root.val) {
                root.right = insert(root.right, val);
            }
        }
        return root;
    }
    public static TreeNode insertByInitial(TreeNode root,int val){
        if (root == null){
            root = new TreeNode(val);
        }else {
            if (compareByInitial(root.val,val)){
                root.left = insertByInitial(root.left,val);
            }else {
                root.right = insertByInitial(root.right,val);
            }
        }
        return root;
    }
    static boolean compareByInitial(int a,int b){
        StringBuilder _a = new StringBuilder(a+"");
        StringBuilder _b = new StringBuilder(b+"");
        boolean flag = _a.length() > _b.length();
        if (flag){
            for (int i = 1;i <= _a.length()-_b.length();i++){
                _b.append(_b.charAt(0));
            }
        }else {
            for (int i = 1;i <= _b.length()-_a.length();i++){
                _a.append(_a.charAt(0));
            }
        }
        for (int index = 0;index < _a.length() && index < _b.length();index++){
            if (_a.charAt(index) > _b.charAt(index)){
                return true;
            }else if (_a.charAt(index) < _b.charAt(index)){
                return false;
            }
        }
        return true;
    }
}
