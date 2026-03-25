package entity.role;

import java.util.List;
import entity.action.EnemyActionStrategy;
import entity.status.StatusEffect;

public abstract class Enemy extends Combatant {
    private final EnemyActionStrategy actionStrategy;

    public Enemy(String name, int hp, int attack, int speed, int defend, EnemyActionStrategy strategy) {
        super(name, hp, attack, speed, defend);
        this.actionStrategy = strategy;
    }

    @Override
    public void takeAction(List<Combatant> targets) {
        for (StatusEffect effect : statusEffects) {
            effect.apply(this);
        }
        if (!stop) {
            Combatant target = targets.get(0);
            actionStrategy.chooseAction(this, target).execute(target);
        }
    }
}
