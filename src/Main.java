import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String wordFrom;
        String wordTo;
        LevenshteinDistanceSolution solution;

        System.out.println("Welcome to the Levenshtein distance solver. Enter the starting word and destination word.");
        System.out.print("Starting word: ");
        wordFrom = sc.nextLine();
        System.out.print("Destination word: ");
        wordTo = sc.nextLine();

        solution = LevenshteinDistanceSolver.solve(wordFrom, wordTo);
        System.out.println("\n");
        solution.describe();
    }
}