import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class player extends character{ // for things related to PC
    private ArrayList<String> inventory; //should we make inventory unlimited or limited or with dedicated slots for certain things

    public player(int health, int mana){
        this(health, mana, 1, 0);
    }

    public player(int health, int mana, double spd, double arm){
        this(health, mana, spd, arm, "basic");
    }

    public player(int health, int mana, double spd, double arm, String typing){
        super(health, mana, spd, arm, typing);
        inventory = new ArrayList<String>();
    }
    public String displayInv(){ return inventory.toString();}
    public void addItem(String itm){
        inventory.add(itm);
    }
    public boolean useItem(String itm){
        int num = inventory.indexOf(itm);
        String used;
        if(num!=-1)
            used = inventory.remove(num);
        else
            return false;


        return true;
    }

    public void mouseClicked(MouseEvent me) { //just a test prob in wrong spot
        int x = me.getX();
        int y = me.getY();
        System.out.println("Mouse clicked at position: " + x + ", " + y);
    }
}
