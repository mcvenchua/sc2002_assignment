package entity.strategy;

import entity.action.Action;
import entity.action.BasicAttack;
import entity.role.Combatant;
import entity.role.Enemy;

public class BasicAttackStrategy implements EnemyActionStrategy {
    @Override
    public Action chooseAction(Enemy self, Combatant target) {
        return new BasicAttack(self, target, self.getAttack());
    }
}
