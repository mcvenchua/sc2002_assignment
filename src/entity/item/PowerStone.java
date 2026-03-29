package entity.item;

import entity.action.skills.SpecialSkill;
import entity.role.Combatant;
public class PowerStone extends Item {
    private Combatant user;
    private SpecialSkill skill;
    public PowerStone(Combatant user, SpecialSkill skill) {
        this.user = user;
        this.skill = skill;
    }
    @Override
    public void use(Combatant target) {
        int ins = this.skill.cooldown.getRemainingTicks();
        skill.execute(target);
        skill.cooldown.set(ins);
        
    }
}
