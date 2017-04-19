import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tomov on 4.4.2017 г..
 */
public class Maze {
    private UndirectedGraph maze;
    private Map<MazeIndex,Integer> mazeMap;
    private int endTile;
    MazeGraphBuilder mgb;
    public Maze(int [] [] maze) { // create without serialization
        mgb = new MazeGraphBuilder(maze);

        this.maze = mgb.getMazeGraph();
        mazeMap = mgb.getMazeMap();
        endTile = this.maze.getSize()-1;
    }
    public Maze(int [] [] maze, String fileName) { // create with serialization
        mgb = new MazeGraphBuilder(maze);
        serializeGraphBuilder(fileName);
        this.maze = mgb.getMazeGraph();
        mazeMap = mgb.getMazeMap();
        endTile = this.maze.getSize()-1;
    }
    public Maze(String graphSerializationFile) { // create from serialization file
        deserialzeGraphBuilder(graphSerializationFile);
        this.maze = mgb.getMazeGraph();
        mazeMap = mgb.getMazeMap();
        endTile = this.maze.getSize()-1;
    }
    private void serializeGraphBuilder(String filename) {

        try (ObjectOutputStream oos = new ObjectOutputStream
                (new FileOutputStream(new File("serial/"+filename+".ser")))
        ) {

            oos.writeObject(mgb);
            System.out.println("Graph Serializing - Complete");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void deserialzeGraphBuilder(String filename) {
        try (ObjectInputStream ois
                     = new ObjectInputStream(new FileInputStream(new File("serial/"+filename+".ser")))) {

            mgb = (MazeGraphBuilder) ois.readObject();
            System.out.println("Graph Deserializing - Complete");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
