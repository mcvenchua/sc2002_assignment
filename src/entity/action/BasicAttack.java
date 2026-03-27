package entity.action;

import entity.role.Combatant;

public class BasicAttack implements Action {
    private final Combatant attacker;

    public BasicAttack(Combatant attacker, Combatant target, int attack) {
        this.attacker = attacker;
    }

    @Override
    public void execute(Combatant target) {
        target.getAttack(attacker.getAttack());
    }
}
