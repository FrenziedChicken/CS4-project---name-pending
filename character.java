import java.util.ArrayList;

public class character {
    //private Deck deck;
    private int max_health, curr_health, max_mana, curr_mana;
    private double speed, armor;
    private String status, type;
    private ArrayList ailments;

    public character(int health, int mana){
        this(health, mana, 1, 0);
    }

    public character(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public character(int health, int mana, double spd, double arm, String typing){
        max_health = curr_health = health;
        max_mana = curr_mana = mana;
        speed = spd;
        armor = arm;
        status = "alive";
        type = typing;
        ailments = new ArrayList();
    }

    public void take_damage(int num, String typing){
        curr_health -= num;
        if(curr_health<=0)
            status = "dead";
    }
    public void use_mana(int num){
        curr_mana -= num;
        if(curr_mana<0)
            curr_mana = 0;
    }
    public void recov_health(int num){
        curr_health += num;
        if(curr_health>max_health)
            curr_health = max_health;
    }
    public void recov_mana(int num){
        curr_mana += num;
        if(curr_mana>max_mana)
            curr_mana = max_mana;
    }

    public void up_health(int num){
        max_health += num;
    }
    public void up_mana(int num){
        max_mana += num;
    }
    public void up_armor(double num){
        armor += num;
    }
    public void up_speed(double num){
        speed += num;
    }

    public String getStatus(){
        return status;
    }
    public int getHealth() { return curr_health;}
    public int getMana() { return curr_mana;}

    public String toString(){
        return "Health: " + curr_health + "/" + max_health +"\nMana: " + curr_mana + "/" + max_mana +
                "\nSpeed: " + speed + "\nArmor: " + armor + "\nAilments: " + ailments.toString();
    }

}
