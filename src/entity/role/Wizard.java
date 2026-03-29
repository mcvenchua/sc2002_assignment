package entity.role;

import java.util.List;
import entity.action.skills.AcraneBlast;

public class Wizard extends Player {
    private final AcraneBlast arcaneBlast = new AcraneBlast(this, this.enemy);

    public Wizard(String name) {
        super(name, 200, 50, 20, 10, 200);  // hp=200, atk=50, spd=20, def=10
        skills.add(arcaneBlast);
    }

    @Override
    protected boolean activateSkill(Combatant targets) {
        if (!arcaneBlast.isReady()) {
            ui.print("Arcane Blast on cooldown (" + arcaneBlast.getRemainingCooldown() + " turns left).");
            return false;
        }
        arcaneBlast.execute(null);
        return true;
    }
}
