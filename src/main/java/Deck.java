import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();
    private GameViewer window;
    public Deck(GameViewer window, String[] ranks, String[] suits, int[] values) {
        this.window = window;
        if (ranks.length != values.length) {
            System.out.println("CANT DO THAT!");
            return;
        }
        for (int i = 0; i < ranks.length; i++)
            for (String suit : suits)
                cards.add(new Card(this.window, ranks[i], suit, values[i]));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getCardsLeft() {
        return cards.size();
    }

    public Card deal() {
        if (isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int rand = (int)(Math.random() * (i + 1));
            swap(i, rand);
        }
    }

    private void swap(int i, int j) {
        Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }
}
