// Player.java
public class Player extends Entity {
    private final Hand hand = new Hand();

    public Player(int hp) {
        super(hp);
    }

    public Hand getHand() {
        return hand;
    }
}