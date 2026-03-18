package entity.skill;

import entity.role.Role;

public class ArcaneBlast extends SpecialSkill {

    public ArcaneBlast() {
        super("Arcane Blast", 3);
    }

    @Override
    public void activate(Role caster, Role target) {
        int damage = caster.getAttack() * 2;
        target.takeDamage(damage);
        System.out.println(caster.getName() + " fires Arcane Blast at "
                + target.getName() + " for " + damage + " damage!");
    }
}
