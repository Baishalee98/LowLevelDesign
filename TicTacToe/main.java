import entity.Game;

public class main {

    public static void main(String[] args) {
        System.out.println("Lets play Tic Tac Toe!!!");
        Game game = new Game();
        game.initializeGame();
        game.startGame();
    }
}