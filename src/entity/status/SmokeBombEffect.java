package entity.status;

import entity.role.Combatant;

public class SmokeBombEffect extends StatusEffect {

    public SmokeBombEffect() {
        this.name = "Smoke Bomb";
        this.duration = new Duration(2);
    }

    @Override
    public void apply(Combatant target) {}

    @Override
    public void tick() {}

    @Override
    public void onRoundEnd() {
        if (!duration.is_over()) duration.set(duration.get() - 1);
    }

    @Override
    public boolean blocksDamage() {
        return true;
    }
}
