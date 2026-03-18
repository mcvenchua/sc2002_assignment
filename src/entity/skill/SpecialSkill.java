package entity.skill;

import entity.role.Role;

public abstract class SpecialSkill {
    protected String name;
    protected int cooldownTicks;

    public SpecialSkill(String name, int cooldownTicks) {
        this.name          = name;
        this.cooldownTicks = cooldownTicks;
    }

    public abstract void activate(Role caster, Role target);

    public String getName()        { return name; }
    public int getCooldownTicks()  { return cooldownTicks; }
}
