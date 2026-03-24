package entity.action;

import entity.role.Combatant;

public class Defend implements Action {
    private Combatant defender;

    public Defend(Combatant defender) {
        this.defender = defender;
    }

    @Override
    public void execute() {
        // TODO: implement defend logic
    }
}
