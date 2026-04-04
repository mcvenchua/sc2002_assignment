package entity.status;

import entity.role.Combatant;

public class Stun extends StatusEffect {

    public Stun() {
        this.name = "Stun";
        this.duration = new Duration(2); 
    }

    @Override
    public void apply(Combatant target) {
        target.setStop(true);
    }
}
