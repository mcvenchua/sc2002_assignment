package entity.role;
import java.util.ArrayList;
import java.util.List;
import entity.action.Action;
import entity.status.StatusEffect;
public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int attack;
    protected int speed;
    protected int defend;
    protected List<StatusEffect> statusEffects = new ArrayList<>();
    protected boolean stop = false;

    protected List<Action> next_act;


    public Combatant(String name, int hp, int attack, int speed, int defend) {
        this.name     = name;
        this.hp       = hp;
        this.attack   = attack;
        this.speed    = speed;
        this.defend   = defend;
    }
    public void takeAction(Combatant target) {
        // Override in Player and Enemy to decide and execute an action
    }
    
    
    public boolean isAlive() {
        return hp > 0;
    }
    public void getAttack(int attack){
        this.hp=Math.max(0,this.hp-=Math.max(0,attack-this.defend));
    }

    public String getName()       { return name; }
    public int getHp()            { return hp; }
    public int getAttack()        { return attack; }
    public int getSpeed()         { return speed; }
    public int getDefend()        { return defend; }
    public void modifyAttack(int attack){
        this.attack+=attack;
    }

    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void setStop(boolean stop) { this.stop = stop; }

}
