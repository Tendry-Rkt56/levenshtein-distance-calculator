import java.util.ArrayList;

public class DynamicSolutionsMatrix {
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    private int rows;

    private int cols;

    public DynamicSolutionsMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        init();
    }

    public void init() {
        for (int i = 0; i <= rows; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= cols; j++) {
                if (i == 0) {
                    row.add(j);
                } else if (j == 0) {
                    row.add(i);
                } else {
                    row.add(null);
                }
            }
            matrix.add(row);
        }
    }

    public int getValue(int row, int col) {
        return matrix.get(row).get(col);
    }

    public void setValue(int row, int col, int value) {
        matrix.get(row).set(col, value);
    }

    public int getLastValue() {
        return matrix.get(rows).get(cols);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                output += getValue(i, j) + " ";
            }
            output += "\n";
        }
        return output;
    }
}
