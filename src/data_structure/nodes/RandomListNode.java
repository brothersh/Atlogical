package data_structure.nodes;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/9 10:51
 * @Version: 1.0
 */
public class RandomListNode {
    public int label;
    public RandomListNode next = null;
    public RandomListNode random = null;

    public RandomListNode(int label) {
        this.label = label;
    }
}
