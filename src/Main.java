import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MCTS s = new MCTS();
        s.setPlayer('0');
        Board startingboard = new Board();
        System.out.println("With which piece would you like to start the game? 0 or X?");
        char openingPiece = sc.next().charAt(0);
        startingboard.setOpeningPiece(openingPiece);
        while(startingboard.getStatus().equals("in progress")){
            System.out.println(startingboard);
            System.out.println("select your move: (in the format : x y)");
            System.out.println("Enter x coordinates:");
            int x = Integer.parseInt(sc.next());
            System.out.println("Enter y coordinates:");
            int y = Integer.parseInt(sc.next());
            Coordinate c = new Coordinate(x,y);
            startingboard.placeMove(c,openingPiece);
            if(startingboard.getStatus().equals("in progress")) {
                startingboard = (Board) s.solve(startingboard).getLayout();
            }
        }
        System.out.println(startingboard);
        System.out.println("Game over: " + startingboard.getStatus());
    }
}
