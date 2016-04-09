/**
 * Created by yiklunchan on 2/17/16.
 * 
 * PuzzleSover can solve a boggle in n x m dimension. it finds words that appear in vertical and horizontal manners.
 */
import java.util.*;


public class PuzzleSolver {

    private static final Set<String> DICTIONARY = new HashSet<String>(Arrays.asList(
            new String[]{"OX", "CAT", "TOY", "AT", "DOG", "CATAPULT", "T"}
    ));

    private static final Set<String> PREFIXES = new HashSet<String>();


    private static int N = 0;

    private static int M = 0;

    private static int w_count = 0;

    private static String TOP = "TOP";
    private static String R_TOP = "R_TOP";
    private static String R_BOTTOM = "R_BOT";
    private static String RIGHT = "RIGHT";
    private static String L_BOTTOM = "L_BOT";
    private static String L_TOP = "L_TOP";
    private static String LEFT = "LEFT";
    private static String BOTTOM = "BOTTOM";
    private static String START = "START";

    static Boolean IsWord(String testWord) {
        if (DICTIONARY.contains(testWord))
            return true;
        return false;
    }

    private static void GeneratePrefix(Set<String> dict) {
        for (String s : dict) {
            String prefix = "";

            for (char c : s.toCharArray()) {
                prefix += c;

                PREFIXES.add(prefix);
            }
        }
    }

    static Boolean IsPrefix(String testWord) {
        return (PREFIXES.contains(testWord)) ? true : false;
    }

    public static int FindWords(char[][] puzzle) {
        GeneratePrefix(DICTIONARY);

        if (puzzle.length == 0) {
            return 0;
        }
        N = puzzle.length;
        M = puzzle[0].length;

        boolean vistied[][] = new boolean[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                dfs_traversal(puzzle, vistied, "", x, y, START);
            }
        }
        return w_count;
    }

    private static void dfs_traversal(char[][] puzzle, boolean[][] visited, String word, int x, int y, String dirtn) {
        if (x < 0 || x >= N || y < 0 || y >= M || visited[x][y])
            return;

        visited[x][y] = true;

        word += puzzle[x][y];

        if (IsWord(word))
            w_count++;

        if (IsPrefix(word)) {


            //search each neighbour in clockwise manner
            if (dirtn.equals(TOP) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x, y - 1, TOP);
            if (dirtn.equals(R_TOP) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x + 1, y - 1, R_TOP);
            if (dirtn.equals(RIGHT) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x + 1, y, RIGHT);
            if (dirtn.equals(R_BOTTOM) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x + 1, y + 1, R_BOTTOM);
            if (dirtn.equals(BOTTOM) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x, y + 1, BOTTOM);
            if (dirtn.equals(L_BOTTOM) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x - 1, y + 1, L_BOTTOM);
            if (dirtn.equals(LEFT) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x - 1, y, LEFT);
            if (dirtn.equals(L_TOP) || dirtn.equals(START))
                dfs_traversal(puzzle, visited, word, x - 1, y - 1, L_TOP);
        }

        visited[x][y] = false;

    }

    public static void main(String[] argv) {


        char[][] puzzle = {{'C', 'X', 'Y'}, {'A', 'Z', 'O'}, {'T', 'T', 'T'}};
        char[][] puzzle2 = {{'C', 'X', 'Y'}, {'A', 'Z', 'O'}, {'T', 'T', 'T'},
                {'A', 'T', 'O'}, {'P', 'O', 'X'}, {'U', 'Y', 'T'}, {'L', 'O', 'X'},
                {'T', 'O', 'X'}};
        char[][] puzzle3 = {{'T'}};


        //int result = FindWords(puzzle2);
        //System.out.println("final result " + result);

    }
}

