// Enemy.java
public class Enemy extends Entity {
    public Enemy(int hp) {
        super(hp);
    }

    /**
     * Very small demo AI: return a fixed damage value.
     * Replace or extend this with stateful logic later.
     */
    public int decideAttack() {
        return 3; // example fixed attack
    }
}