import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // create and shuffle deck
        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values =  {1,2,3,4,5,6,7,8,9,10,11,12,13};

        Deck deck = new Deck(ranks, suits, values);
        deck.shuffle();

        Player p1 = new Player("P1");    // Human
        Player p2 = new Player("P2");    // Computer

        // start by dealing 3 cards each
        for (int i = 0; i < 3; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }

        int round = 1;

        System.out.println("=== CARD BATTLE GAME ===");
        System.out.println("You and the Computer both hold 3 cards at a time.");
        System.out.println("Pick one each round. Higher card wins.");
        System.out.println();

        // as long as both players have cards, continue game
        while (!p1.getHand().isEmpty() && !p2.getHand().isEmpty()) {

            System.out.println("–––– Round " + round + " ––––");
            round++;

            // Player chooses which card to play
            System.out.println("Your hand:");
            for (int i = 0; i < p1.getHand().size(); i++) {
                System.out.println((i+1) + ": " + p1.getHand().get(i));
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
                input.nextLine(); // clear buffer
            }

            Card c1 = p1.getHand().get(playerChoice);
            p1.getHand().remove(playerChoice);

            System.out.println("You played: " + c1);

            // ---- Computer CHOICE ----
            int compChoice = (int)(Math.random() * p2.getHand().size());
            Card c2 = p2.getHand().get(compChoice);
            p2.getHand().remove(compChoice);
            System.out.println("--------------------------------");
            System.out.println("Computer played: " + c2);

            // decide winner
            if (c1.getValue() > c2.getValue()) {
                System.out.println("You win the round!");
                p1.addScore(c1.getValue() + c2.getValue());
            } else if (c2.getValue() > c1.getValue()) {
                System.out.println("Computer wins the round!");
                p2.addScore(c1.getValue() + c2.getValue());
            } else {
                System.out.println("Tie! Each player gets 1 point.");
                p1.addScore(0);
                p2.addScore(0);
            }

            // draw new cards if possible
            if (!deck.isEmpty()) {
                p1.addCard(deck.deal());
            }
            if (!deck.isEmpty()) {
                p2.addCard(deck.deal());
            }

            System.out.println("Scores → You: " + p1.getScore() + "   Computer: " + p2.getScore());
            System.out.println("--------------------------------");
            System.out.println();
        }

        // game ends
        System.out.println("–––– GAME OVER ––––");
        System.out.println("Final Score → You: " + p1.getScore() + "   Computer: " + p2.getScore());

        if (p1.getScore() > p2.getScore()) {
            System.out.println("YOU WIN THE GAME!");
        } else if (p2.getScore() > p1.getScore()) {
            System.out.println("Computer WINS THE GAME!");
        } else {
            System.out.println("THE GAME ENDS IN A TIE.");
        }
    }
}
