// Enemy.java
public class Enemy extends Entity {
    public Enemy(int health, int mana){
        this(health, mana, 1, 0);
    }

    public Enemy(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public Enemy(int health, int mana, double spd, double arm, String typing){
        super(health, mana, spd, arm, "basic");
    }

    /**
     * Very small demo AI: return a fixed damage value.
     * Replace or extend this with stateful logic later.
     */
    public int decideAttack() {
        return 3; // example fixed attack
    }
}