package entity.item;

import entity.role.Combatant;
import entity.status.SmokeBombEffect;

public class SmokeBomb extends Item {

    public SmokeBomb() {
        super("Smoke Bomb");
    }

    @Override
    public void use(Combatant target) {
        target.addStatusEffect(new SmokeBombEffect());
    }
}
