package entity;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Deque<Player> players;
    private Board board;
    private boolean isWinner;

    public void initializeGame() {

        players = new LinkedList<>();
        Player player1 = new Player("Player1", new PieceO());
        Player player2 = new Player("Player2", new PieceX());
        players.add(player1);
        players.add(player2);

        board = new Board(3);

        isWinner = false;

    }

    public void startGame() {

        Scanner scanner = new Scanner(System.in);

        while(!isWinner) {
            System.out.println("Board: \n");
            board.printBoard();

            List<List<Integer>> freeCells = board.getFreeCells();
            if(freeCells.isEmpty()) {
                System.out.println("It's a tie.");
                isWinner = true;
                continue;
            }

            Player currentTurn = players.pollFirst();
            System.out.print("Player " + currentTurn.getName() + ", enter row,column: ");
            String[] idx = scanner.nextLine().split(",");
            int row = Integer.valueOf(idx[0]);
            int col = Integer.valueOf(idx[1]);

            if(row<0 || row>=board.getSize() || col<0 || col>=board.getSize()) {
                System.out.println("Invalid cell chosen. Try again");
                players.addFirst(currentTurn);
                continue;
            }

            boolean cellSet = board.setCell(row, col, currentTurn.getPiece());

            if(!cellSet) {
                System.out.println("Chosen cell not free. Try again");
                players.addFirst(currentTurn);
                continue;
            }
            players.addLast(currentTurn);

            findWinner(row,col,currentTurn.getPiece().getPieceType());
            if(isWinner) {
                System.out.println("Board: ");
                board.printBoard();
                System.out.println("Congratulations " + currentTurn.getName() + ", you won the match!");
            }
        }

        scanner.close();
    }

    public void findWinner(int row,int col,PieceType pieceType) {

        boolean rowFilled = true, colFilled = true, diagFilled = true, antiDiagFilled = true;

        for(int i=0;i<board.getSize();i++) {
            if(board.getBoard()[i][col]==null || board.getBoard()[i][col].getPiece().getPieceType()!=pieceType) {
                colFilled = false;
            }
        }

        for(int i=0;i<board.getSize();i++) {
            if(board.getBoard()[row][i]==null || board.getBoard()[row][i].getPiece().getPieceType()!=pieceType) {
                rowFilled = false;
            }
        }

        for(int i=0;i<board.getSize();i++) {
            if(board.getBoard()[i][i]==null || board.getBoard()[i][i].getPiece().getPieceType()!=pieceType) {
                diagFilled = false;
            }
        }

        for(int i=0,j=board.getSize()-1;j>=0;i++,j--) {
            if(board.getBoard()[i][j]==null || board.getBoard()[i][j].getPiece().getPieceType()!=pieceType) {
                antiDiagFilled = false;
            }
        }

        if (rowFilled || colFilled || diagFilled || antiDiagFilled) {
            isWinner = true;
        }

    }
    
}
