package entity.action.skills;

import entity.role.Combatant;
import entity.status.Stun;

public class ShieldBash extends SpecialSkill {

    private final Combatant attacker;

    public ShieldBash(Combatant attacker) {
        this.attacker = attacker;
    }

    @Override
    public void execute(Combatant target) {
        target.addStatusEffect(new Stun());
        target.getAttack(attacker.getAttack());
        cooldown.set(3);
    }
}
