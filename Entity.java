// Entity.java
public class Entity {
    private int hp;
    private int block;

    public Entity(int hp) {
        this.hp = hp;
        this.block = 0;
    }

    public int getHp() {
        return hp;
    }

    public int getBlock() {
        return block;
    }

    public void addBlock(int b) {
        if (b < 0) throw new IllegalArgumentException("block must be >= 0");
        block += b;
    }

    /**
     * Apply damage: block absorbs first, remaining reduces hp.
     * hp is clamped at 0.
     */
    public void takeDamage(int dmg) {
        if (dmg < 0) throw new IllegalArgumentException("dmg must be >= 0");
        int remaining = dmg;
        if (block > 0) {
            int absorbed = Math.min(block, remaining);
            block -= absorbed;
            remaining -= absorbed;
        }
        hp -= remaining;
        if (hp < 0) hp = 0;
    }

    public boolean isDead() {
        return hp <= 0;
    }
}