package entity.role;

import entity.CoolDown;
import entity.status.SpecialSkill;

public abstract class Role {
    protected String name;
    protected int hp;
    protected int attack;
    protected int speed;
    protected int defend;
    protected CoolDown cooldown;   // composition: exactly 1 Cooldown

    public Role(String name, int hp, int attack, int speed, int defend) {
        this.name     = name;
        this.hp       = hp;
        this.attack   = attack;
        this.speed    = speed;
        this.defend   = defend;
        this.cooldown = new CoolDown(0);
    }

    public void take_action() {
        // TODO: design a method here to not only support attack for enemy
        // After calling this method, user can decide which action to take, please override this method in player and enemy level
    }

    
    public boolean isAlive() {
        return hp > 0;
    }

    public String getName()       { return name; }
    public int getHp()            { return hp; }
    public int getAttack()        { return attack; }
    public int getSpeed()         { return speed; }
    public int getDefend()        { return defend; }
    public CoolDown getCooldown() { return cooldown; }

    @Override
    public String toString() {
        return String.format("%s [HP:%d ATK:%d SPD:%d DEF:%d]",
                name, hp, attack, speed, defend);
    }
}
