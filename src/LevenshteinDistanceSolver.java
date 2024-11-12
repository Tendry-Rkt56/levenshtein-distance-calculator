public class LevenshteinDistanceSolver {
    public static LevenshteinDistanceSolution solve(String wordFrom, String wordTo) {
        int distance = 0;

        int wordFromLength = wordFrom.length(), wordToLength = wordTo.length();
        DynamicSolutionsMatrix matrix = new DynamicSolutionsMatrix(wordFromLength, wordToLength);

        int distanceFromAddition, distanceFromSubtraction, distanceFromSubstitution;
        for (int i = 1; i <= wordFromLength; i++) {
            for (int j = 1; j <= wordToLength; j++) {
                distanceFromAddition = matrix.getValue(i, j - 1) + 1;
                distanceFromSubtraction = matrix.getValue(i - 1, j) + 1;
                distanceFromSubstitution = matrix.getValue(i - 1, j - 1) + getCharsDiffCost(wordFrom.charAt(i - 1), wordTo.charAt(j - 1));
                distance = Math.min(Math.min(distanceFromAddition, distanceFromSubtraction), distanceFromSubstitution);
                matrix.setValue(i, j, distance);
            }
        }

        return new LevenshteinDistanceSolution(wordFrom, wordTo, distance, matrix);
    }

    public static int getCharsDiffCost(char c1, char c2) {
        return c1 != c2 ? 1 : 0;
    }
}
;