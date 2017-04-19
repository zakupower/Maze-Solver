import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Tomov on 4.4.2017 г..
 */
public class MazeImageSolver {
    static final int BLUE = -11431955;
    Maze maze;
    Map<Integer,MazeIndex> inversedMap;
    BufferedImage solvedMaze;
    BufferedImage mazeToSolve;
    String fileName = "";
    int BFSSteps;
    int DFSSteps;

    public MazeImageSolver(BufferedImage mazeImage) {
        mazeToSolve = mazeImage;
        maze = new Maze(ImageToMazeConverter.getBinaryMaze(mazeImage));
        inversedMap = maze.getMazeMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
    public MazeImageSolver(BufferedImage mazeImage,String fileName, Boolean isSerialized) {
        this.fileName = fileName;
        mazeToSolve = mazeImage;
        if(isSerialized==true) {
            maze = new Maze(fileName);
        } else {
            maze = new Maze(ImageToMazeConverter.getBinaryMaze(mazeImage),fileName);
        }

        inversedMap = maze.getMazeMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
    public void solveDFS(int nodeToStart) throws IOException{
        solvedMaze = deepCopy(mazeToSolve);
        ArrayList<Integer> solveOrder = maze.solveDFS(nodeToStart);
        DFSSteps = solveOrder.size();
        for(Integer cell : solveOrder) {
            solvedMaze.setRGB(inversedMap.get(cell).getColIndex(),inversedMap.get(cell).getRowIndex(),BLUE);
        }
        System.out.println("Solving in DFS - Complete.");
        ImageIO.write(solvedMaze,"png",new File("mazes/"+fileName+"Solved DFS.png"));
    }
    public void solveBFS(int nodeToStart) throws IOException{
        solvedMaze = deepCopy(mazeToSolve);
        ArrayList<Integer> solveOrder = maze.solveBFS(nodeToStart);
        BFSSteps = solveOrder.size();
        for(Integer cell : solveOrder) {
            solvedMaze.setRGB(inversedMap.get(cell).getColIndex(),inversedMap.get(cell).getRowIndex(),BLUE);
        }
        System.out.println("Solving in BFS - Complete.");
        ImageIO.write(solvedMaze,"png",new File("mazes/"+fileName+"Solved BFS.png"));
    }
    public void solveShortestPath(int nodeToStart)throws Exception {
        GraphToWeightedGraphConvertor converter = new GraphToWeightedGraphConvertor(maze.getMazeGraph(),nodeToStart,maze.getEndTile());
        solvedMaze = deepCopy(mazeToSolve);
        VertexWeightedGraph vertexWeightedGraph = converter.convert();
        ArrayList<Node> solveOrder = vertexWeightedGraph.getShortestRootList(vertexWeightedGraph.getNodeFromGraph(nodeToStart));
        for(Node cell : solveOrder) {
            solvedMaze.setRGB(inversedMap.get(cell.getId()).getColIndex(),inversedMap.get(cell.getId()).getRowIndex(),BLUE);
        }
        System.out.println("Solving shortest path - Complete.");
        ImageIO.write(solvedMaze,"png",new File("mazes/"+fileName+"Solved ShortestRoute.png"));
    }
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


    public int getDFSSteps(){
        return DFSSteps;
    }
    public int getBFSSteps(){
        return BFSSteps;
    }
}
