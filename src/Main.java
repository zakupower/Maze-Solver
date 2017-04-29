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
    public static void main(String[] args) throws IOException, Exception {
//        for(int i = 1; i < 20; i++) {
//            if(i==12) continue;//no route possible for maze12
//            Timer.start();
//            String imageFileName = "maze"+i;
//            BufferedImage image = ImageIO.read(new File("mazes/" + imageFileName + ".png"));
//            MazeImageSolver mazeImageSolver = new MazeImageSolver(image, imageFileName, true);//true to load from serialization
//            mazeImageSolver.solveShortestPath(0);
//            Timer.stop();
//            System.out.println("Maze "+i+" solved in " + Timer.getTimeInM() + "min : " + Timer.getTimeInS() + "sec : " + Timer.getTimeInMs() + "milisec .");
//        }
            Timer.start();
            String imageFileName = "finalMaze";
            BufferedImage image = ImageIO.read(new File("mazes/" + imageFileName + ".png"));
            MazeImageSolver mazeImageSolver = new MazeImageSolver(image, imageFileName, true);//true to load from serialization
            mazeImageSolver.solveShortestPath(0);
            Timer.stop();
            System.out.println("Final Maze solved in " + Timer.getTimeInM() + "min : " + Timer.getTimeInS() + "sec : " + Timer.getTimeInMs() + "milisec .");
    }

}
