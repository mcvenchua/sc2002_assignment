package entity.role;

import entity.action.BasicAttack;

public abstract class Enemy extends Combatant {

    public Enemy(String name, int hp, int attack, int speed, int defend) {
        super(name, hp, attack, speed, defend);
    }

    @Override
    public void take_action(Combatant target) {
        next_act = new BasicAttack(this, target);
        next_act.execute();
    }
}
