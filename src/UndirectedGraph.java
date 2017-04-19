import java.io.Serializable;
import java.util.*;

public class UndirectedGraph implements Graph<Integer>, Serializable {
    private int size = 0;
    private Map<Integer,Set<Integer>> adjacencyList;
    public UndirectedGraph() {
        adjacencyList = new HashMap<Integer,Set<Integer>>();
    }

    public void addNode(Integer id) throws Exception {
        adjacencyList.putIfAbsent(id,new LinkedHashSet<>());
        size++;
    }
    public void removeNode(Integer nodeId) throws Exception {
        if(adjacencyList.containsKey(nodeId)) {
            adjacencyList.remove(nodeId);
            cleanUpEdges(nodeId);
        } else {
            throw new Exception("no such node");
        }
        size--;
    }
    private void cleanUpEdges(int nodeId) {
        for(int i:adjacencyList.keySet()) {
            Set<Integer> currentNodeEdges = adjacencyList.get(i);
            if(currentNodeEdges.contains(nodeId)) {
                currentNodeEdges.remove(nodeId);
            }
        }
    }
    // undirected graph
    public void addEdge(Integer firstNode, Integer secondNode) throws Exception {
        if(adjacencyList.containsKey(firstNode) && adjacencyList.containsKey(secondNode)) {
            adjacencyList.get(firstNode).add(secondNode);
            adjacencyList.get(secondNode).add(firstNode);
        } else {
            throw new Exception("One or both nodes not in graph");
        }
    }

    public void removeEdge(Integer firstNode, Integer secondNode) throws Exception {
        if(adjacencyList.containsKey(firstNode) && adjacencyList.containsKey(secondNode)) {
            Set<Integer> firstNodeEdges = adjacencyList.get(firstNode);
            Set<Integer> secondNodeEdges = adjacencyList.get(secondNode);
            if(firstNodeEdges.contains(secondNode) && secondNodeEdges.contains(firstNode)) {
                firstNodeEdges.remove(secondNode);
                secondNodeEdges.remove(firstNode);
            }
        } else {
            throw new Exception("One or both nodes not in graph");
        }
    }
    public Set<Integer> getNeighbourNodes(int node) {
        return adjacencyList.get(node);
    }
    public void printDFS(int nodeToStart){
        System.out.println(getDFSList(nodeToStart));
    }
    private void addNeighboursToStack(Stack<Integer> stack, Integer vertex, Set<Integer> visitedVertices) {
        for(int neighbourVertex : adjacencyList.get(vertex)) {
            if(!visitedVertices.contains(neighbourVertex)) {
                stack.push(neighbourVertex);
            }
        }
    }
    public void printBFS(int nodeToStart) {
        System.out.println(getBFSList(nodeToStart));
    }
    private void addNeighboursToQueue(Queue<Integer> queue, Integer vertex,Set<Integer> visitedVertices ) {
        for(Integer neighbourVertex : adjacencyList.get(vertex)) {
            if(!visitedVertices.contains(neighbourVertex)) {
                queue.queue(neighbourVertex);
            }
        }
    }
    public String toString() {
        return(adjacencyList.toString());
    }

    public ArrayList<Integer> getBFSList(int nodeToStart){
        ArrayList<Integer> BFSOrderList = new ArrayList<>();
        Queue<Integer> nextVertexQueue = new Queue<Integer>();
        HashSet<Integer> visitedVertices = new HashSet<>();
        int currentVertex;
        nextVertexQueue.queue(nodeToStart);
        while(!nextVertexQueue.isEmpty()) {
            currentVertex = nextVertexQueue.dequeue();
            BFSOrderList.add(currentVertex);
            visitedVertices.add(currentVertex);
            addNeighboursToQueue(nextVertexQueue,currentVertex,visitedVertices);
        }
        return BFSOrderList;
    }
    public ArrayList<Integer> getDFSList(int nodeToStart){
        ArrayList<Integer> DFSOrderList = new ArrayList<>();
        Stack<Integer> nextVertexStack = new Stack<Integer>();
        HashSet<Integer> visitedVertices = new HashSet<>();
        int currentVertex;

        nextVertexStack.push(nodeToStart);
        while(!nextVertexStack.isEmpty()) {
            currentVertex = nextVertexStack.pop();
            DFSOrderList.add(currentVertex);
            visitedVertices.add(currentVertex);
            addNeighboursToStack(nextVertexStack,currentVertex,visitedVertices);
        }
        return DFSOrderList;
    }
    public int getSize(){
        return size;
    }

    public Map<Integer, Set<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}