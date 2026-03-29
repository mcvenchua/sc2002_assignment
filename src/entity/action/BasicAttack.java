package entity.action;

import entity.role.Combatant;

public class BasicAttack implements Action {
    private final Combatant attacker;
    private final Combatant target;
    private final int attack;

    public BasicAttack(Combatant attacker, Combatant target, int attack) {
        this.attacker = attacker;
        this.target = target;
        this.attack = attack;
    }

    @Override
    public void execute(Combatant target) {
        Combatant victim = target != null ? target : this.target;
        victim.getAttack(attacker.getAttack());
    }
}
