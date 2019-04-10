package data_structure.Stacks;

import java.util.Stack;

/**
 * @Author: BrotherSh
 * @Date: 2019/4/9 10:55
 * @Version: 1.0
 */
public class MinStack {
    Stack<Integer> _main = new Stack<>();
    Stack<Integer> _sub = new Stack<>();
    public void push(int node) {
        if (_sub.isEmpty() || node <= _sub.peek())
            _sub.push(node);
        _main.push(node);
    }

    public void pop() {
        if (_sub.peek() == _main.peek()){
            _sub.pop();
        }
        _main.pop();
    }

    public int top() {
        return _main.peek();
    }

    public int min() {
        return _sub.peek();
    }
}
