package entity.item;

import entity.role.Combatant;

public class SmokeBomb extends Item {
  
    public SmokeBomb() {
        super("Smoke Bomb");
    }
    @Override
    public void use(Combatant target) {
        target.applySmokeBomb();
    }
}
