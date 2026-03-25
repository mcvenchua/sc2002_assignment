package entity.action;

import entity.role.Combatant;
import entity.role.Enemy;

public class BasicAttackStrategy implements EnemyActionStrategy {
    @Override
    public Action chooseAction(Enemy self, Combatant target) {
        return new BasicAttack(self, target, self.getAttack());
    }
}
