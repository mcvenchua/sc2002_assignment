package entity.status;

import entity.role.Combatant;

public abstract class StatusEffect {
    protected String name;
    protected Duration duration;

    public String getName() { return name; }

    public void tick() {
        if (!duration.is_over()) duration.set(duration.get() - 1);
    }

    public boolean isActive() { return !duration.is_over(); }

    public abstract void apply(Combatant target);
}
