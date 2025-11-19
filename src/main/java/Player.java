public class Player {
    private String name;
    private int score;
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    @Override
    public String toString() {
        return this.name + " has " + this.score + " points!";
    }
}
