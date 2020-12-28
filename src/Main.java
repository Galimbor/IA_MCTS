import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ITreePolicy treePolicy = new UCT();
//        IRolloutPolicy rolloutPolicy = new RandomUniform();
        IRolloutPolicy rolloutPolicy = new SpecialHeuristic();
        MCTS s = new MCTS(treePolicy, rolloutPolicy);

        System.out.println("With which piece would you like to start the game? 0 or X?");
        char openingPiece = sc.next().charAt(0);
        TicTacToe startingboard = new TicTacToe(openingPiece);

        while (startingboard.getStatus().equals("in progress")) {
            System.out.println(startingboard);
            Point c = selectMove(sc);
            try {
                startingboard.placeMove(c, openingPiece);
            } catch (TicTacToeException tttE) {
                System.out.println(tttE.toString());
                System.exit(0);
            }
//            while (!startingboard.placeMove(c, openingPiece)) {
//                c = selectMove(sc);
//            }

            System.out.println(startingboard);
            System.out.println("Opponent is choosing its next move, please wait...");
            if (startingboard.getStatus().equals("in progress")) {
                startingboard = (TicTacToe) s.solve(startingboard).getGame();
            }
        }
        System.out.println(startingboard);
        System.out.println("Game over: " + startingboard.getStatus());
//        iaPlaysFirst(startingboard,sc,s);
    }


    private static Point selectMove(Scanner sc) {
        System.out.println("select your move: ");
        System.out.println("Enter number of row");
        int row = Integer.parseInt(sc.next()) - 1;//to make it more easy
        System.out.println("Enter number of column:");
        int column = Integer.parseInt(sc.next()) - 1; // to make it more easy
        return new Point(row, column);
    }

    private static void iaPlaysFirst(TicTacToe startingboard, Scanner sc, MCTS mcts) throws CloneNotSupportedException {
        TicTacToe result = (TicTacToe) mcts.solve(startingboard).getGame();
        System.out.println(result);
    }
}
