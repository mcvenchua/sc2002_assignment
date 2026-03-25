package entity.role;

import java.util.List;
import entity.action.skills.ShieldBash;

public class Warrior extends Player {
    private final ShieldBash shieldBash = new ShieldBash();

    public Warrior(String name) {
        super(name, 260, 40, 30, 20);  // hp=260, atk=40, spd=30, def=20
        skills.add(shieldBash);
    }

    @Override
    protected boolean activateSkill(List<Combatant> targets) {
        if (!shieldBash.isReady()) {
            ui.print("Shield Bash on cooldown (" + shieldBash.getRemainingCooldown() + " turns left).");
            return false;
        }
        Combatant target = selectTarget(targets);
        shieldBash.execute(target);
        return true;
    }
}
