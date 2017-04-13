
import java.util.*;

/**
 * Created by Tomov on 03.04.2017 г..
 */
public interface Graph<T> {
     void addNode(T t) throws Exception;
     void removeNode(T t) throws Exception;
     void addEdge (T t1,T t2) throws Exception;
     void removeEdge(T t1, T t2) throws Exception;
}



