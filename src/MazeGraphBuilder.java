import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tomov on 3.4.2017 г..
 */
public class MazeGraphBuilder {
    private int [] [] maze;
    private Map<MazeIndex,Integer> mazeVertexMap;
    private UndirectedGraph graph;
    private int nodeIdCounter = 0;

    public MazeGraphBuilder(int[][] maze){
        this.maze = maze;
        graph = new UndirectedGraph();
        try {
            buildGraph();
            System.out.println("Undirected graph building - Complete.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    private void buildGraph()throws Exception{
        mazeVertexMap = new HashMap<>();
        for(int rowIndex = 0; rowIndex < maze.length; rowIndex++) {
            for(int colIndex = 0; colIndex < maze[rowIndex].length; colIndex++) {
                if(maze[rowIndex][colIndex]==1) {
                    MazeIndex rowCol = new MazeIndex(rowIndex,colIndex);
                    mazeVertexMap.put(rowCol,nodeIdCounter++);
                }
            }
        }
        createGraphVertices(mazeVertexMap);
        createEdges(mazeVertexMap);
    }
    private void createGraphVertices(Map<MazeIndex,Integer> map) throws Exception {
        for(int i = 0; i < map.size(); i++) {
            graph.addNode(i);
        }
    }
    private void createEdges(Map<MazeIndex,Integer> map)throws Exception {
        Set<MazeIndex> vertices = map.keySet();
        int counter = 0;
        int finalcounter = vertices.size();
        LoadingBar loadingBar = new LoadingBar(finalcounter);
        loadingBar.start();
        for(MazeIndex vertex : vertices) {
            loadingBar.update(counter++);
            createMazeIndexEdge(vertex,map,vertex.getRowIndex()-1,vertex.getColIndex());
            createMazeIndexEdge(vertex,map,vertex.getRowIndex()+1,vertex.getColIndex());
            createMazeIndexEdge(vertex,map,vertex.getRowIndex(),vertex.getColIndex()-1);
            createMazeIndexEdge(vertex,map,vertex.getRowIndex(),vertex.getColIndex()+1);
        }
    }
    private void createMazeIndexEdge(MazeIndex vertex,Map<MazeIndex,Integer> map,int row, int col) throws Exception {
        MazeIndex tempMazeIndex = new MazeIndex(row,col);
        tempMazeIndex = getValidVertex(map,tempMazeIndex);
        if(tempMazeIndex!=null) {
            graph.addEdge(map.get(vertex),map.get(tempMazeIndex));
        }
    }
    private MazeIndex getValidVertex(Map<MazeIndex,Integer> vertexMap,MazeIndex vertex){
        for(MazeIndex tempVert : vertexMap.keySet()) {
            if(tempVert.equals(vertex)) return tempVert;
        }
        return null;
    }
    public void printGraph(){
        System.out.println("DFS:");
        graph.printDFS(0);
        System.out.println("BFS:");
        graph.printBFS(0);
        System.out.println("Graph toString:");
        System.out.println(graph.toString());
    }
    public UndirectedGraph getMazeGraph() {
        return graph;
    }
    public Map<MazeIndex,Integer> getMazeMap() { return mazeVertexMap;}
}
