import java.lang.reflect.Array;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.push;

/**
 * Created by Tomov on 5.4.2017 г..
 */
public class VertexWeightedGraph implements Graph<Node> {
    Map<Node,Set<Node>> adjacencyList;
    Node nodeToFinish;
    private int size=0;

    public VertexWeightedGraph(Node nodeToFinish){
        adjacencyList = new HashMap<Node,Set<Node>>();
        this.nodeToFinish = nodeToFinish;
    }


    public void addNode(Node node) throws Exception {
        adjacencyList.putIfAbsent(node,new LinkedHashSet<Node>());
        size++;
    }
    public void removeNode(Node node) throws Exception {
        node = getNodeFromGraph(node.getId());
        if(adjacencyList.containsKey(node)) {
            adjacencyList.remove(node);
            cleanUpEdges(node.getId());
        } else {
            throw new Exception("no such node");
        }
        size--;
    }
    private void cleanUpEdges(int nodeId) {
        for(Node i:adjacencyList.keySet()) {
            Set<Node> currentNodeEdges = adjacencyList.get(i);
            removeNodeWithId(currentNodeEdges,nodeId);
        }
    }
    private void removeNodeWithId(Set<Node> set,int nodeId) {
        for(Node n: set) {
            if(n.getId()==nodeId) set.remove(n);
        }
    }
    // undirected graph
    public void addEdge(Node firstNode, Node secondNode) throws Exception {
        if(adjacencyList.containsKey(firstNode) && adjacencyList.containsKey(secondNode)) {
            adjacencyList.get(firstNode).add(secondNode);
            adjacencyList.get(secondNode).add(firstNode);
        } else {
            throw new Exception("One or both nodes not in graph");
        }
    }

    public void removeEdge(Node firstNode, Node secondNode) throws Exception {
        if(adjacencyList.containsKey(firstNode) && adjacencyList.containsKey(secondNode)) {
            Set<Node> firstNodeEdges = adjacencyList.get(firstNode);
            Set<Node> secondNodeEdges = adjacencyList.get(secondNode);
            if(firstNodeEdges.contains(secondNode) && secondNodeEdges.contains(firstNode)) {
                firstNodeEdges.remove(secondNode);
                secondNodeEdges.remove(firstNode);
            }
        } else {
            throw new Exception("One or both nodes not in graph");
        }
    }
    public Set<Node> getNeighbourNodes(Node node) throws Exception {
        node = getNodeFromGraph(node.getId());
        return adjacencyList.get(node);
    }
    public void printAdjacencyList(){
        for(Node key : adjacencyList.keySet()) {
            System.out.print(key.getId() + "["+key.getWeight()+"] -> " );
            for(Node node : adjacencyList.get(key))
                System.out.print(node.getId()+", ");
            System.out.println();
        }
    }
    public ArrayList<Node> getShortestRootList(Node nodeToStart)throws Exception {
        Stack<Node> nodesStack = new Stack<>();
        ArrayList<Node> nodes = new ArrayList<>();
        nodeToStart = getNodeFromGraph(nodeToStart.getId());
        nodesStack.push(nodeToStart);
        while(!nodesStack.isEmpty()) {
            Node currentNode = nodesStack.pop();
            nodes.add(currentNode);
            if(currentNode.equals(nodeToFinish)) break;
            Node maxNode = findMaxWeightNeighbour(adjacencyList.get(currentNode));
            nodesStack.push(maxNode);
        }
        return nodes;
    }
    public void printShortestRoot(Node nodeToStart)throws Exception{
        for(Node node : getShortestRootList(nodeToStart)) {
            System.out.print("->"+node.getId());
        }
    }
    private Node findMaxWeightNeighbour(Set<Node> set) {
        int weight = Integer.MIN_VALUE;
        double maxWeight = weight;
        Node maxWeightNode = null;
        for(Node node : set) {
            if(node.getWeight()>=maxWeight){
                maxWeightNode = node;
                maxWeight = node.getWeight();
            }
        }
        return maxWeightNode;
    }
    public Node getNodeFromGraph(int id)throws Exception {
        for(Node key:adjacencyList.keySet()) {
            if(key.getId()==id) return key;
        }
        throw new Exception("No such node in graph.");
    }
}
