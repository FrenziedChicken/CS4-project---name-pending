import java.util.ArrayList;

// Player.java
public class Player extends Entity {
    private final Hand hand = new Hand();
    private int coin;

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

    public int getCoin() {  return coin; }
    public void spendCoin(int num){ coin-=num;}
    public void gainCoin(int num){  coin+=num;}
}