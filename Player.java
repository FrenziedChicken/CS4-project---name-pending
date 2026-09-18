import java.util.ArrayList;

// Player.java
public class Player extends Entity {
    private final Hand hand = new Hand();

    public Player(int health, int mana){
        this(health, mana, 1, 0);
    }

    public Player(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public Player(int health, int mana, double spd, double arm, String typing){
        super(health, mana, spd, arm, "basic");
    }

    public Hand getHand() {
        return hand;
    }
}