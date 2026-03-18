package entity.role;

import entity.skill.SpecialSkill;

public abstract class Role {
    protected String name;
    protected int hp;
    protected int attack;
    protected int speed;
    protected int defend;
    protected Cooldown cooldown;   // composition: exactly 1 Cooldown

    public Role(String name, int hp, int attack, int speed, int defend) {
        this.name     = name;
        this.hp       = hp;
        this.attack   = attack;
        this.speed    = speed;
        this.defend   = defend;
        this.cooldown = new Cooldown(0);
    }

    public void attack(Role target) {
        int damage = Math.max(0, this.attack - target.defend);
        target.takeDamage(damage);
        System.out.println(name + " attacks " + target.getName()
                + " for " + damage + " damage!");
    }

    public void useSkill(SpecialSkill s, Role target) {
        if (!cooldown.isReady()) {
            System.out.println(name + "'s skill is on cooldown ("
                    + cooldown.getRemainingTicks() + " ticks left).");
            return;
        }
        s.activate(this, target);
        cooldown.reset(s.getCooldownTicks());
    }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public void heal(int amount) {
        hp += amount;
    }

    public void boostAttack(int amount) {
        attack += amount;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName()       { return name; }
    public int getHp()            { return hp; }
    public int getAttack()        { return attack; }
    public int getSpeed()         { return speed; }
    public int getDefend()        { return defend; }
    public Cooldown getCooldown() { return cooldown; }

    @Override
    public String toString() {
        return String.format("%s [HP:%d ATK:%d SPD:%d DEF:%d]",
                name, hp, attack, speed, defend);
    }
}
