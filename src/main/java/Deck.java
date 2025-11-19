import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck(String[] ranks, String[] suits, int[] values) {
        if (ranks.length != values.length) {
            System.out.println("CANT DO THAT!");
            return;
        }
        for (int i = 0; i < ranks.length; i++)
            for (String suit : suits)
                cards.add(new Card(ranks[i], suit, values[i]));
    }

    public boolean isEmpty() {
        return getCardsLeft() == 0;
    }

    public int getCardsLeft() {
        return this.cards.size();
    }

    public Card deal() {
        if (isEmpty()) return null;
        Card card = cards.get(getCardsLeft()-1);
        cards.remove(getCardsLeft()-1);
        return card;
    }

    public void shuffle() {
        for (int i = 0; i < getCardsLeft(); i++) {
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
