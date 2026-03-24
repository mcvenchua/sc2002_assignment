package entity.role;

import entity.action.BasicAttack;

public class Wolf extends Enemy {

    public Wolf(String name) {
        super(name, 40, 45, 35, 5);  // hp=40, atk=45, spd=35, def=5
    }

    @Override
    public void take_action(Combatant target) {
        next_act = new BasicAttack(this, target);
        next_act.execute();
    }
}
