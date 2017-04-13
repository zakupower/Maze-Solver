import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import static sun.misc.Version.print;

public class Main {
    public static void main(String[] args) throws IOException,Exception {
//        BigInteger timeElapsed = new BigInteger(System.currentTimeMillis()+"");
        Timer.start();
        String imageFileName = "webMaze2";
        BufferedImage image = ImageIO.read(new File("mazes/"+imageFileName+".png"));
        MazeImageSolver mazeImageSolver = new MazeImageSolver(image,imageFileName);
       // mazeImageSolver.solveBFS(0);// gets too hard on processor after 200x200
       // mazeImageSolver.solveDFS(0);// gets too hard on processor after 200x200
        mazeImageSolver.solveShortestPath(0);
//        timeElapsed = new BigInteger(""+ System.currentTimeMillis()).subtract(timeElapsed);
//        double timeInMinutes = ((double)Integer.parseInt(timeElapsed.toString()))/60000;
//        System.out.println("Complete. Time Elapsed: "+timeElapsed.toString()+"ms " + timeInMinutes+"min");
        Timer.stop();
        System.out.println("Solved in "+Timer.getTimeInM()+"min : "+ Timer.getTimeInS()+ "sec : " + Timer.getTimeInMs() + "milisec .");
    }


    private static void printMaze(int [] [] maze){
        for(int rowIndex = 0; rowIndex < maze.length; rowIndex++) {
            for(int colIndex = 0; colIndex < maze[rowIndex].length; colIndex++) {
                if(maze[rowIndex][colIndex]==1) {
                    System.out.print(maze[rowIndex][colIndex]);
                } else {
                    System.out.print(maze[rowIndex][colIndex]);
                }
            }
            System.out.println();
        }
    }
}
