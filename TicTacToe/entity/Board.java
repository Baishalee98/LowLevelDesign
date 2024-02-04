package entity;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private Cell[][] board;

    public Board(int size) {
        this.size = size;
        board = new Cell[size][size];
    }

    public void printBoard() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(board[i][j]==null) {
                    System.out.print("  | ");
                } else {
                    System.out.print(board[i][j].getPiece().getPieceType().name()+" | ");
                }
            }
            System.out.println();
        }
    }

    public List<List<Integer>> getFreeCells() {
        List<List<Integer>> freeCells = new ArrayList<>();

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(board[i][j]==null) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    freeCells.add(cell);
                }
            }
        }

        return freeCells;
    }

    public boolean setCell(int row,int col,Piece piece) {
        if(row<0 || row>=size || col<0 || col>=size)return false;
        if(board[row][col]!=null)return false;

        board[row][col] = new Cell(piece);
        return true;

    }
    
    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return board;
    }
    
}
