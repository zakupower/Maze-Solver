import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tomov on 4.4.2017 г..
 */
public class Maze {
    private UndirectedGraph maze;
    private Map<MazeIndex,Integer> mazeMap;
    private int endTile;

    public Maze(int [] [] maze) {
        MazeGraphBuilder mgb = new MazeGraphBuilder(maze);
        this.maze = mgb.getMazeGraph();
        mazeMap = mgb.getMazeMap();
        endTile = this.maze.getSize()-1;
    }

    public ArrayList<Integer> solveDFS(int nodeToStart){
        ArrayList<Integer> solveOrder = new ArrayList<>();
        for(Integer vertex:maze.getDFSList(nodeToStart)) {
            if(vertex.equals(endTile)) {
                solveOrder.add(vertex);
                break;
            } else {
                solveOrder.add(vertex);
            }
        }
        return solveOrder;
    }
    public  ArrayList<Integer> solveBFS(int nodeToStart){
        ArrayList<Integer> solveOrder = new ArrayList<>();
        for(Integer vertex:maze.getBFSList(nodeToStart)) {
            if(vertex.equals(endTile)) {
                solveOrder.add(vertex);
                break;
            } else {
                solveOrder.add(vertex);
            }
        }
        return solveOrder;
    }
    public Map<MazeIndex,Integer> getMazeMap(){
        return mazeMap;
    }
    public UndirectedGraph getMazeGraph() { return maze;}

    public int getEndTile() {
        return endTile;
    }
}
