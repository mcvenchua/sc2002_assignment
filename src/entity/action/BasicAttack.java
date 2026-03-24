package entity.action;

import entity.role.Combatant;

public class BasicAttack implements Action {
    private Combatant attacker;
    private Combatant target;

    public BasicAttack(Combatant attacker, Combatant target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute() {
        // TODO: implement basic attack logic
    }
}
