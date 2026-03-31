package entity.item;

import entity.action.skills.SpecialSkill;
import entity.role.Combatant;
public class PowerStone extends Item {
    private final SpecialSkill skill;

    public PowerStone(SpecialSkill skill) {
        super("Power Stone");
        this.skill = skill;
    }

    @Override
    public void use(Combatant target) {
        int savedCooldown = skill.cooldown.getRemainingTicks();
        skill.execute(target);
        skill.cooldown.set(savedCooldown); // restore cooldown, free use
    }
}
