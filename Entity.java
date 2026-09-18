import java.util.ArrayList;

// Entity.java
public class Entity {
    private int block;
    //private Deck deck;
    private int max_hp, hp, max_mp, mp;
    private double speed, armor;
    private String status, type;
    private ArrayList ailments;
    // what ever the sprites gonna be

    public Entity(int health, int mana){
        this(health, mana, 1, 0);
    }

    public Entity(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public Entity(int health, int mana, double spd, double arm, String typing){
        max_hp = hp = health;
        max_mp = mp = mana;
        speed = spd;
        armor = arm;
        status = "alive";
        type = typing;
        ailments = new ArrayList();
        this.block = 0;
    }
    /*
    public void take_damage(int num, String typing){
        curr_health -= (int)num*(1.0-armor); // definetly should change to a better armor system
        if(curr_health<=0)
            status = "dead";
    }

     */

    /**
     * Apply damage: block absorbs first, remaining reduces hp.
     * hp is clamped at 0.
     *
     */
    public void takeDamage(int dmg) {
        if (dmg < 0) throw new IllegalArgumentException("dmg must be >= 0");
        int remaining = dmg;
        if (block > 0) {
            int absorbed = Math.min(block, remaining);
            block -= absorbed;
            remaining -= absorbed;
        }
        hp -= (int)remaining*(1.0-armor); //adding armor, will need to decide if or how we're doing this
        if (hp < 0) hp = 0;
        if(hp<=0)
            status = "dead";
    }

    public void useMp(int cost){
        mp -= cost;
        if(mp<0)
            mp = 0;
    }
    public void recovHp(int num){
        hp += num;
        if(hp>max_hp)
            hp = max_hp;
    }
    public void recovMp(int num){
        mp += num;
        if(mp>max_mp)
            mp = max_mp;
    }
    public void addBlock(int b) {
        if (b < 0) throw new IllegalArgumentException("block must be >= 0");
        block += b;
    }

    public void upHp(int num){
        max_hp += num;
    }
    public void upMp(int num){
        max_mp += num;
    }
    public void upArm(double num){
        armor += num;
    }
    public void upSpeed(double num){
        speed += num;
    }

    public String getStatus(){
        return status;
    }
    public int getHp() { return hp;}
    public int getMp() { return mp;}
    public int getBlock() { return block;}

    public boolean isDead() {
        return hp <= 0;
    }

    public String toString(){
        return "Health: " + hp + "/" + max_hp +"\nMana: " + mp + "/" + max_mp +
                "\nSpeed: " + speed + "\nArmor: " + armor + "\nAilments: " + ailments.toString();
    }


    /** This is Caden's code. i will be integrating parts of it into mine and then we can resolve any differences.
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
     *
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

    ****/
}