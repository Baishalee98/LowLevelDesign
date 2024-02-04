package entity;

public class Player {
    private String name;
    private Piece piece;
    
    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    
}
