package entity.role;

import entity.action.skills.ShieldBash;

public class Warrior extends Player {
    private final ShieldBash shieldBash = new ShieldBash();

    public Warrior(String name) {
        super(name, 260, 40, 30, 20);  // hp=260, atk=40, spd=30, def=20
        skills.add(shieldBash);
    }

    @Override
    protected boolean activateSkill(Combatant target) {
        if (!shieldBash.isReady()) {
            ui.print("Shield Bash on cooldown (" + shieldBash.getRemainingCooldown() + " turns left).");
            return false;
        }
        shieldBash.execute(target);
        return true;
    }
}
