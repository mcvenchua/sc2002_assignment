package entity.role;

import entity.strategy.EnemyActionStrategy;
import entity.status.StatusEffect;
import java.util.Iterator;

public abstract class Enemy extends Combatant {
    private final EnemyActionStrategy actionStrategy;

    public Enemy(String name, int hp, int attack, int speed, int defend, EnemyActionStrategy strategy, int max_hp) {
        super(name, hp, attack, speed, defend, max_hp);
        this.actionStrategy = strategy;
    }

    @Override
    public void takeAction(Combatant targets) {
        stop = false;
        Iterator<StatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            StatusEffect effect = it.next();
            effect.apply(this);   
            effect.tick();      
            if (!effect.isActive()) it.remove(); 
        }

        if (!stop) {
            actionStrategy.chooseAction(this, targets).execute(targets);
        }
    }
}
