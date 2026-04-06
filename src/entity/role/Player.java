package entity.role;

import entity.action.Action;
import entity.strategy.PlayerActionStrategy;
import entity.action.skills.SpecialSkill;
import entity.item.Item;
import entity.status.StatusEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ui.UI;

public abstract class Player extends Combatant {
    protected List<SpecialSkill> skills = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
    protected UI ui;
    protected List<Combatant> enemy = new ArrayList<>();
    private PlayerActionStrategy actionStrategy;

    public Player(String name, int hp, int attack, int speed, int defend, int max_hp) {
        super(name, hp, attack, speed, defend, max_hp);
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }

    public void setActionStrategy(PlayerActionStrategy strategy) {
        this.actionStrategy = strategy;
    }

    public void learnSkill(SpecialSkill skill) {
        skills.add(skill);
    }

    public void syncEnemyTargets(List<Combatant> opponents) {
        enemy.clear();
        enemy.addAll(opponents);
    }

    @Override
    public void takeAction(Combatant target) {
        stop = false;
        Iterator<StatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            StatusEffect effect = it.next();
            effect.apply(this);
            effect.tick();
            if (!effect.isActive()) it.remove();
        }
        if (stop) {
            ui.print(name + " is STUNNED and cannot act!");
            return;
        }
        for (SpecialSkill s : skills) {
            s.tickCooldown();
        }
        Action action = actionStrategy.chooseAction(this, target);
        if (action != null) action.execute(null);
    }

    public abstract boolean activateSkill(Combatant target);

    public List<SpecialSkill> getSkills() { return skills; }
    public List<Item> getItems() { return items; }

    public List<Combatant> getEnemyTargets() {
        return enemy;
    }
}
