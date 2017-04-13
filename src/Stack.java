import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomov on 31.3.2017 г..
 */
public class Stack<T> {
    private LinkedList<T> stack;
    public Stack(){
        stack = new LinkedList<T>();
    }
    public T pop(){
        return stack.removeFirst();
    }
    public void push(T value) {
        stack.addFirst(value);
    }
    public T peek() {
        return stack.peekLast();
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public String toString() {
        return stack.toString();
    }
    public void addListToStack(List<T> list) {
        for(T t: list) {
            this.push(t);
        }
    }
}