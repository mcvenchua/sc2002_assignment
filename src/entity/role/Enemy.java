package entity.role;

import entity.action.EnemyActionStrategy;
import entity.status.StatusEffect;
import java.util.List;

public abstract class Enemy extends Combatant {
    private final EnemyActionStrategy actionStrategy;

    public Enemy(String name, int hp, int attack, int speed, int defend, EnemyActionStrategy strategy, int max_hp) {
        super(name, hp, attack, speed, defend);
        this.actionStrategy = strategy;
        this.max_hp = max_hp;
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
