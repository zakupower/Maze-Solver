import java.util.LinkedList;

/**
 * Created by Tomov on 4.4.2017 г..
 */
public class Queue<T> {
    private LinkedList<T> queue;

    public Queue(){
        queue = new LinkedList<T>();
    }
    public T peek(){
        return queue.peekFirst();
    }
    public void queue(T value){
        queue.addLast(value);
    }
    public T dequeue(){
        return queue.removeFirst();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}

