import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tomov on 5.4.2017 г..
 */
public class GraphToWeightedGraphConvertor {
    private UndirectedGraph undirectedGraph;
    private int nodeIdToStart;
    private int nodeIdToFinish;
    private VertexWeightedGraph weightedGraph;
    public GraphToWeightedGraphConvertor(UndirectedGraph undirectedGraph, int nodeToStart, int nodeToFinish){
        this.undirectedGraph = undirectedGraph;
        this.nodeIdToFinish = nodeToFinish;
        this.nodeIdToStart = nodeToStart;
    }

    public VertexWeightedGraph convert() throws Exception{
        Queue<Node> traverseQueue = new Queue<Node>();
        HashSet<Integer> traversedNodes = new HashSet<Integer>();
        double weight = undirectedGraph.getSize();

        traverseQueue.queue(new Node(nodeIdToFinish,weight));
        weightedGraph = new VertexWeightedGraph(traverseQueue.peek());
        weightedGraph.addNode(traverseQueue.peek());
        while(!traverseQueue.isEmpty()) {
            Node currentNode = traverseQueue.dequeue();
            if(traversedNodes.contains(currentNode.getId())) continue; // this does wonders
            if(traversedNodes.size()%100==0)
            System.out.println(traversedNodes.size() + ":" + undirectedGraph.getSize());
            traversedNodes.add(currentNode.getId());
            addNeighbourEdges(currentNode,undirectedGraph.getNeighbourNodes(currentNode.getId()),traversedNodes,traverseQueue,--weight);
        }
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Undirected graph to weighted graph - Complete.");
        return weightedGraph;
    }

    private void addNeighbourEdges(Node currentNode,Set<Integer> neighbours, Set<Integer> traveresedNodes,Queue<Node> traverseStack, double weight) throws Exception {
        for(Integer nodeId : neighbours) {
            if(!traveresedNodes.contains(nodeId)) {
                Node nodeToAdd = new Node(nodeId,weight);
                weightedGraph.addNode(nodeToAdd);
                weightedGraph.addEdge(currentNode,nodeToAdd);
                traverseStack.queue(nodeToAdd);
            }
        }
    }

    private boolean isTraversedNode(int nodeid, Set<Node> traversedNodes) {
        for(Node node: traversedNodes) {
            if(node.getId()==nodeid) {
                return true;
            }
        }
        return false;
    }
}
