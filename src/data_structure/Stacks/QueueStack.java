package data_structure.Stacks;

import java.util.LinkedList;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/9 10:54
 * @Version: 1.0
 */
public class QueueStack {
    LinkedList<Integer> queue = new LinkedList<>();
    int top;
    /** Initialize your data structure here. */

    /** Push element x onto stack. */
    public void push(int x) {
        queue.addLast(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int time = queue.size()-2;
        while (time-- > 0){
            int p = queue.poll();
            queue.addLast(p);
        }
        top = queue.poll();
        queue.addLast(top);
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
