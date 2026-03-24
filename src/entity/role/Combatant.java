package entity.role;
import entity.action.Action;

public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int attack;
    protected int speed;
    protected int defend;
    protected Action next_act;


    public Combatant(String name, int hp, int attack, int speed, int defend) {
        this.name     = name;
        this.hp       = hp;
        this.attack   = attack;
        this.speed    = speed;
        this.defend   = defend;
    }
    public void take_action(Combatant target) {
        // Override in Player and Enemy to decide and execute an action
    }
    
    
    public boolean isAlive() {
        return hp > 0;
    }

    public String getName()       { return name; }
    public int getHp()            { return hp; }
    public int getAttack()        { return attack; }
    public int getSpeed()         { return speed; }
    public int getDefend()        { return defend; }


}
