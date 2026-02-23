import java.util.Scanner;

public class Game {
    private Deck deck;
    // private Player p1;
    // private Player p2;
    private boolean gameOver = false;
    private boolean showingInstructions = true;
    public Player p1;
    public Player p2;
    String[] ranks;
    String[] suits;
    int[] values;
    private GameViewer window;

    public Game() {
        this.window = new GameViewer(this);
        ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        suits = new String[]{"Spades", "Hearts", "Diamonds", "Clubs"};
        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        deck = new Deck(this.window, this.ranks, this.suits, this.values);
        deck.shuffle();
        p1 = new Player("");    // Human
        p2 = new Player("");    // Computer
        for (int i = 0; i < 3; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
    }

    public boolean isShowingInstructions() {
        return showingInstructions;
    }



    public boolean isGameOver() {
        return gameOver;
    }

    public void startGame() {
        showingInstructions = false;
        window.repaint();
    }

    public void endGame() {
        gameOver = true;
        // prevent card reuse
        p1.getHand().clear();
        p2.getHand().clear();
        window.repaint();
    }

    public void printInstructions() {
        System.out.println("    WAR    ");
        System.out.println("You and I both hold 3 cards at a time.");
        System.out.println("Pick one each round. Higher card wins.");
        System.out.println("Don't get a negative score (you'll lose)");
        System.out.println("If your score plus my score is more than 20, we both lose points.");
        System.out.println("We play until no cards remain.");
        System.out.println("Good luck!");
        System.out.println();
        window.repaint();
    }

    public void playGame() {
        printInstructions();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");

        String playerName = input.nextLine();
        System.out.println("Enter computer's name: ");
        String compName = input.nextLine();
        this.p1.setName(playerName);    // Human
        this.p2.setName(compName);    // Computer

        int round = 1;
        showingInstructions = false;
        window.repaint();


        // as long as both players have cards, continue game
        while (!p1.getHand().isEmpty() && !p2.getHand().isEmpty()) {

            System.out.println("    Round " + round + "    ");
            round++;

            // Player chooses which card to play
            System.out.println("Your hand:");
            for (int i = 0; i < p1.getHand().size(); i++) {
                System.out.println((i + 1) + ": " + p1.getHand().get(i));
            }

            int playerChoice;

            // input validation loop
            while (true) {
                System.out.print("Pick a card to play (1-" + p1.getHand().size() + "): ");
                if (input.hasNextInt()) {
                    playerChoice = input.nextInt() - 1;
                    if (playerChoice >= 0 && playerChoice < p1.getHand().size()) break;
                }
                System.out.println("Invalid choice. Try again.");
                input.nextLine();
            }

            Card c1 = p1.getHand().get(playerChoice);
            p1.getHand().remove(playerChoice);

            System.out.println("You played: " + c1);

            // ---- Computer CHOICE ----
            int compChoice = (int) (Math.random() * p2.getHand().size());
            Card c2 = p2.getHand().get(compChoice);
            p2.getHand().remove(compChoice);
            System.out.println("                            ");
            System.out.println(compName + " played: " + c2);

            // decide winner
            if ((c1.getValue() + c2.getValue()) > 20) {
                p1.addScore(-1 * c1.getValue());
                p2.addScore(-1 * c2.getValue());
            } else if (c1.getValue() > c2.getValue()) {
                System.out.println("You win the round!");
                p1.addScore(c1.getValue() + c2.getValue());
            } else if (c2.getValue() > c1.getValue()) {
                System.out.println(compName + " wins the round!");
                p2.addScore(c1.getValue() + c2.getValue());
            } else {
                System.out.println("Tie! Each player gets 0 points.");
                p1.addScore(0);
                p2.addScore(0);
            }

            window.repaint()    ;

            if (p1.getScore() < 0) {
                System.out.println("You went below 0 :( " + compName + " WINS THE GAME!");
                endGame();
                return;
            }
            else if (p2.getScore() < 0) {
                System.out.println(compName + " went below 0 :( YOU WIN THE GAME!");
                endGame();
                return;
            }
            // draw new cards if possible
            if (!deck.isEmpty()) {
                p1.addCard(deck.deal());
                window.repaint();
            }
            if (!deck.isEmpty()) {
                p2.addCard(deck.deal());
                window.repaint();
            }

            System.out.println("Scores: " + playerName + ": " + p1.getScore() + " " + compName + ": " + p2.getScore());
            System.out.println();
            System.out.println();
        }

        // game ends
        System.out.println("    GAME OVER    ");
        endGame();
        System.out.println("Final Score " + playerName + ": " + p1.getScore() + " " + compName + ": " + p2.getScore());

        if (p1.getScore() > p2.getScore()) {
            System.out.println("YOU WIN THE GAME!");
        } else if (p2.getScore() > p1.getScore()) {
            System.out.println(compName + " WINS THE GAME!");
        } else {
            System.out.println("THE GAME ENDS IN A TIE.");
        }
    }


    public static void main(String[] args) {
        // create and shuffle deck
        Game g = new Game();
        g.playGame();


    }
}
