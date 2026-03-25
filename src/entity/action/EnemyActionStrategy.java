package entity.action;

import entity.role.Combatant;
import entity.role.Enemy;

public interface EnemyActionStrategy {
    Action chooseAction(Enemy self, Combatant target);
}
