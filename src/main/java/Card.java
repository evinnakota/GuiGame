import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class Card {
    // card 0 = face-down
    // card 1-4 = ace of spades, hearts, diamonds, clubs
    // card 5-8 = 2 of suit
    // ... card 49-52 = king of suit

//    public static void draw(Graphics g, int card) {
//        g.drawImage(Card.getImage(card), 150, 150, 500, 704, window);
//    }
//

    private String rank;
    private String suit;
    private int value;
    private GameViewer window;

    public Card(GameViewer window, String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.window = window;
    }

    public static Image getImage(int card) {
        if (0 == card) return new ImageIcon("src/main/resources/back.png").getImage();
        return new ImageIcon("src/main/resources/"+card+".png").getImage();
    }

    public void draw(Graphics g, int i) {
        int index = 4*value - 4;
        if (this.suit.equals("Spades")) index += 1;
        if (this.suit.equals("Hearts")) index += 2;
        if (this.suit.equals("Diamonds")) index += 3;
        if (this.suit.equals("Clubs")) index += 4;
        Image card = Card.getImage(index);
//        System.out.println(index);
//        System.out.println(card);
//        g.drawImage(card, 150*(i+1), 150, 100, 140, window);
        g.drawImage(card, 150*(i%10+1), 150 + 200*(i/10), 100, 140, window);

    }

    public String getRank() {

        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    private void setRank(String rank) {
        this.rank = rank;
    }

    private void setSuit(String suit) {
        this.suit = suit;
    }

    private void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit + " (worth " + this.value + ")";
    }
}
