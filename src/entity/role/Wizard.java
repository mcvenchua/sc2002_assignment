package entity.role;

import java.util.List;
import entity.action.skills.AcranBlast;

public class Wizard extends Player {
    private final AcranBlast arcaneBlast = new AcranBlast();

    public Wizard(String name) {
        super(name, 200, 50, 20, 10);  // hp=200, atk=50, spd=20, def=10
        skills.add(arcaneBlast);
    }

    @Override
    protected boolean activateSkill(List<Combatant> targets) {
        if (!arcaneBlast.isReady()) {
            ui.print("Arcane Blast on cooldown (" + arcaneBlast.getRemainingCooldown() + " turns left).");
            return false;
        }
        arcaneBlast.execute(null);
        return true;
    }
}
