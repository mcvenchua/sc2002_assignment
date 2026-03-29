package entity.item;

import entity.role.Combatant;

public class SmokeBomb extends Item {
    @Override
    public void use(Combatant target) {
        target.applySmokeBomb();
    }
}
