public class Game {
    public static void main(String[] args) {
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] suit = {"C", "D", "H", "S"};
        int[] values = {11,2,3,4,5,6,7,8,9,10,11,12,13};
//        String[] rank = {"A", "2", "3"};
//        String[] suit = {"C"};
//        int[] values = {1,2,3};
        Deck d = new Deck(rank,suit,values);
        d.shuffle();
        Player p1 = new Player("Eshu");
        Player p2 = new Player("Ryan");
        for (int i = 0; i < 3; i++) {
            p1.addCard(d.deal());
            p2.addCard(d.deal());
        }
        System.out.println(p1);
        System.out.println(p2);
        for (int i = 0; i < 52; i++) {
            Card c1 = p1.getHand().getFirst();
            Card c2 = p2.getHand().getFirst();
            p1.addScore(c1.getValue());
            p2.addScore(c2.getValue());
            p1.getHand().removeFirst();
            p2.getHand().removeFirst();
            System.out.println(p1);
            System.out.println(p2);
            if (p1.getHand().isEmpty() || p1.getHand().isEmpty()) {
                for (int j = 0; j < 3; j++) {
                    p1.addCard(d.deal());
                    p2.addCard(d.deal());
                }
            }
        }




    }
}
