import java.io.Serializable;

/**
 * Created by Tomov on 3.4.2017 г..
 */
public class MazeIndex implements Serializable {
    private int rowIndex;
    private int colIndex;

    public MazeIndex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }


    public boolean equals(Object obj) {
        return (obj instanceof MazeIndex) && ((MazeIndex) obj).rowIndex == this.rowIndex
                && ((MazeIndex) obj).colIndex == this.colIndex;
    }
}
