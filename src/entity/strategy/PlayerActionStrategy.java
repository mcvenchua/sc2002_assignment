package entity.strategy;

import entity.action.Action;
import entity.role.Combatant;
import entity.role.Player;

public interface PlayerActionStrategy {
    Action chooseAction(Player self, Combatant target);
}
