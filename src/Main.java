import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MCTS s = new MCTS();
        Board startingboard = new Board();
        startingboard.setOpeningPiece('X');
        System.out.println("With which piece would you like to start the game? 0 or X?");
        char openingPiece = sc.next().charAt(0);
        startingboard.setOpeningPiece(openingPiece);
        while (startingboard.getStatus().equals("in progress")) {
            System.out.println(startingboard);
            Coordinate c = selectMove(sc);
            while (!startingboard.placeMove(c, openingPiece)) {
                c = selectMove(sc);
            }
            System.out.println(startingboard);
            System.out.println("Opponent is choosing its next move, please wait...");
            if (startingboard.getStatus().equals("in progress")) {
                startingboard = (Board) s.solve(startingboard).getLayout();
            }
        }
        System.out.println(startingboard);
        System.out.println("Game over: " + startingboard.getStatus());
//        iaPlaysFirst(startingboard,sc,s);
    }


    private static Coordinate selectMove(Scanner sc) {
        System.out.println("select your move: ");
        System.out.println("Enter number of row");
        int row = Integer.parseInt(sc.next());
        System.out.println("Enter number of column:");
        int column = Integer.parseInt(sc.next());
        return new Coordinate(row, column);
    }

    private static void iaPlaysFirst(Board startingboard,Scanner sc, MCTS mcts) throws CloneNotSupportedException {
        Board result = (Board) mcts.solve(startingboard).getLayout();
        System.out.println(result);
    }
}
