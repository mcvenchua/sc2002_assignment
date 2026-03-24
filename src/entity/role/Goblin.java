package entity.role;

import entity.action.BasicAttack;

public class Goblin extends Enemy {

    public Goblin(String name) {
        super(name, 55, 35, 25, 15);  // hp=55, atk=35, spd=25, def=15
    }

    @Override
    public void take_action(Combatant target) {
        next_act = new BasicAttack(this, target);
        next_act.execute();
    }
}
