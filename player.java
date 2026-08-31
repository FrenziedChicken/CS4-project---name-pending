import java.util.ArrayList;

public class player extends character{
    private ArrayList inventory;

    public player(int health, int mana){
        this(health, mana, 1, 0);
    }

    public player(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public player(int health, int mana, double spd, double arm, String typing){
        super(health, mana, spd, arm, typing);
    }

}
