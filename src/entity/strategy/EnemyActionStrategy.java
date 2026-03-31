package entity.strategy;

import entity.action.Action;
import entity.role.Combatant;
import entity.role.Enemy;

public interface EnemyActionStrategy {
    Action chooseAction(Enemy self, Combatant target);
}
