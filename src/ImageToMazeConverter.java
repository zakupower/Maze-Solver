import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.M;

/**
 * Created by Tomov on 7.3.2017 г..
 */
public class ImageToMazeConverter {

    public static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    public static int[][] getBinaryMaze(BufferedImage bufferedImage){
        int [] [] RGBMaze = convertTo2DWithoutUsingGetRGB(bufferedImage);
        ArrayList<ArrayList<Integer>> binaryMaze = new ArrayList<>(RGBMaze.length);
        for(int i = 0; i < RGBMaze.length; i++) {
            binaryMaze.add(new ArrayList<>(RGBMaze.length));
            for(int j = 0; j <RGBMaze[i].length; j++) {
                if(RGBMaze[i][j]==-1){
                    binaryMaze.get(i).add(1);
                } else {
                    binaryMaze.get(i).add(0);
                }
            }
        }
        System.out.println("Conversion from image to binary maze - Complete.");
        return listToArray(binaryMaze);
    }

    private static int [] [] listToArray(ArrayList<ArrayList<Integer>> list){
        int [] [] arr = new int[list.size()][];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = new int[list.get(i).size()];
            for(int j = 0; j < list.get(i).size();  j++) {
                arr[i][j] = list.get(i).get(j);
            }
        }
        return arr;
    }



}
