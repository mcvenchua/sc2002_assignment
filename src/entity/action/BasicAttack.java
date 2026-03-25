package entity.action;

import entity.role.Combatant;

public class BasicAttack implements Action {
    private Combatant attacker;
    private Combatant target;
    private int attack;

    public BasicAttack(Combatant attacker, Combatant target,int attack) {
        this.attacker = attacker;
        this.target = target;
        this.attack=attack;
    }

    @Override
    public void execute(Combatant target) {
        this.target.getAttack();
        // TODO: implement basic attack logic
    }
}
