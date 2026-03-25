package entity.role;

import entity.action.EnemyActionStrategy;
import entity.status.StatusEffect;

public abstract class Enemy extends Combatant {
    private final EnemyActionStrategy actionStrategy;

    public Enemy(String name, int hp, int attack, int speed, int defend, EnemyActionStrategy strategy) {
        super(name, hp, attack, speed, defend);
        this.actionStrategy = strategy;
    }

    @Override
    public void takeAction(Combatant target) {
        for (StatusEffect effect : statusEffects) {
            effect.apply(this);
        }
        if (!stop) {
            actionStrategy.chooseAction(this, target).execute(target);
        }
    }
}
