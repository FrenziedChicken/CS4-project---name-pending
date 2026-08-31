public class enemy extends character{

    public enemy(int health, int mana){
        this(health, mana, 1, 0);
    }

    public enemy(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public enemy(int health, int mana, double spd, double arm, String typing){
        super(health, mana, spd, arm, typing);
    }


}
