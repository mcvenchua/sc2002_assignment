package entity.skill;

import entity.role.Role;

public class Stun extends SpecialSkill {

    public Stun() {
        super("Stun", 4);
    }

    @Override
    public void activate(Role caster, Role target) {
        // TODO: apply stun status effect
        System.out.println(caster.getName() + " stuns " + target.getName() + "!");
    }
}
