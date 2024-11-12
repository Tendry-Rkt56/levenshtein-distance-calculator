import java.util.ArrayList;
import java.util.LinkedList;

public class LevenshteinDistanceSolution {
    private int distance;

    private DynamicSolutionsMatrix matrix;

    private String wordFrom;
    private String wordTo;

    public LevenshteinDistanceSolution(String wordFrom, String wordTo, int distance, DynamicSolutionsMatrix matrix) {
        this.wordFrom = wordFrom;
        this.wordTo = wordTo;
        this.distance = distance;
        this.matrix = matrix;
    }

    public int getDistance() {
        return distance;
    }

    public DynamicSolutionsMatrix getMatrix() {
        return matrix;
    }

    public LinkedList<TransformationSnapshot> backtrackTransformations() {
        LinkedList<TransformationSnapshot> snapshots = new LinkedList<TransformationSnapshot>();
        int distance = matrix.getLastValue(), row = wordFrom.length(), col = wordTo.length(), position = col;
        char character = '_'; // Just a default value to prevent a non-initialized variable error
        TransformationOperation operation = TransformationOperation.Addition; // Just a default value to prevent a non-initialized variable error
        String wordSnapshot = wordTo;
        StringBuilder sb = null;
        boolean transformationOccurred;

        while (distance > 0) {
            transformationOccurred = true;

            if (distance == (matrix.getValue(row, col - 1) + 1)) {
                // Transformation from addition
                operation = TransformationOperation.Addition;
                character = wordSnapshot.charAt(col - 1);
                position = col;
                // String builder for the next snapshot
                sb = new StringBuilder(wordSnapshot);
                sb.deleteCharAt(col - 1);

                col--;
            } else if (distance == (matrix.getValue(row - 1, col) + 1)) {
                // Transformation from subtraction
                operation = TransformationOperation.Subtraction;
                character = wordFrom.charAt(row - 1);
                position = col + 1;
                // String builder for the next snapshot
                sb = new StringBuilder(wordSnapshot);
                sb.insert(col, character);

                row--;
            } else {
                if (distance == (matrix.getValue(row - 1, col - 1) + 1)) {
                    // Transformation from substitution
                    operation = TransformationOperation.Substitution;
                    character = wordSnapshot.charAt(col - 1);
                    position = col;
                    // String builder for the next snapshot
                    sb = new StringBuilder(wordSnapshot);
                    sb.setCharAt(col - 1, wordFrom.charAt(row - 1));
                } else {
                    transformationOccurred = false;
                }

                row--;
                col--;
            }

            if (transformationOccurred) {
                // Stacking the transformation snapshot that occurred
                snapshots.addFirst(new TransformationSnapshot(operation, position, character, wordSnapshot));

                // Setting the next word snapshot
                wordSnapshot = sb.toString();
            }

            distance = matrix.getValue(row, col);
        }

        return snapshots;
    }

    public void describe() {
        LinkedList<TransformationSnapshot> snapshots = backtrackTransformations();
        int count = snapshots.size();

        System.out.println("The Levenshtein distance is: " + distance);
        System.out.println("Initial word: " + wordFrom);
        System.out.println("The followings are the " + count + " transformations that occurred:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ".");
            snapshots.get(i).describe();
        }
    }
}
