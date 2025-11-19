public class Game {
    public static void main(String[] args) {
        String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] suit = {"C", "D", "H", "S"};
        int[] values = {11,2,3,4,5,6,7,8,9,10,11,12,13};
//        String[] rank = {"A", "2", "3"};
//        String[] suit = {"C"};
//        int[] values = {1,2,3};
        Deck d = new Deck(rank,suit,values);
        Player p1 = new Player("Eshu");
        System.out.println(d.isEmpty());
        d.shuffle();
        while (!d.isEmpty()) {
            System.out.println(d.deal());
        }




    }
}
