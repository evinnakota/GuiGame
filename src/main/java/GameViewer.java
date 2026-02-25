import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;


public class GameViewer extends JFrame {
    private Game game;
    public Image card;

    public GameViewer(Game game) {
        this.game = game;
        card = new ImageIcon("src/main/resources/1.png").getImage();
        setTitle("Card War");
        setSize(800,675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public void paint(Graphics g) {
        super.paint(g);

        // background
        g.setColor(new Color(109, 171, 100));
        g.fillRect(0, 0, getWidth(), getHeight());

        if (game.isShowingInstructions()) {
            drawInstructions(g);
            return;
        }

        // draw ONE screen only
        if (game.isGameOver()) {
            drawGameOver(g);
            return;
        } else {
            drawGame(g);
        }


        Color back = new Color(109, 171,100);
        g.setColor(back);



    }
    private void drawInstructions(Graphics g) {
        g.setColor(Color.BLACK);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("WAR", 320, 120);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("You and I both hold 3 cards at a time.", 180, 200);
        g.drawString("Pick one each round. Higher card wins.", 180, 240);
        g.drawString("Don't get a negative score (you lose).", 180, 280);
        g.drawString("If total value > 20, both players lose points.", 180, 320);
        g.drawString("We play until no cards remain.", 180, 360);
        g.drawString("Good luck!", 180, 400);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Continue", 260, 470);
    }

    private void drawGame(Graphics g) {
        // draw cards ONLY during gameplay
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("WAR", 350, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + game.p1.getScore(), 50, 150);
        g.drawString("Score: " + game.p2.getScore(), 50, 550);
        g.drawString(game.p1.getName(), 350, 150);
        g.drawString(game.p2.getName(), 350, 625);
        ArrayList<Card> hand = game.p1.getHand();
        for (int i = 0; i < hand.size(); i++) {
//            System.out.println("Drawing card " + i);
//            System.out.println(hand.get(i));
            hand.get(i).draw(g,i);
            game.p2.getHand().get(i).draw(g,i+10);// Delete later
        }
        // round result message
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(game.getMessage(), 450, 367);

        // score changes
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("change: " + game.getP1Change(), 450, 150);
        g.drawString("change: " + game.getP2Change(), 450, 625);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 200, 300);
        g.drawString("Player 1 Score: " + game.p1.getScore(), 200, 400);
        g.drawString("Player 2 Score: " + game.p2.getScore(), 200, 500);
    }

}
