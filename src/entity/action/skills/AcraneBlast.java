package entity.action.skills;

import entity.role.Combatant;
import java.util.List;

public class AcraneBlast extends SpecialSkill {
    private final Combatant user;
    private final List<Combatant> targets;

    public AcraneBlast(Combatant user, List<Combatant> targets) {
        this.user = user;
        this.targets = targets;
    }

    @Override
    public void execute(Combatant ignored) {
        for (Combatant enemy : targets) {
            if (enemy.isAlive()) {
                enemy.getAttack(user.getAttack());
                if (!enemy.isAlive()) {
                    user.modifyAttack(10); // +10 ATK per kill, lasts until end of level
                }
            }
        }
        cooldown.set(3);
    }
}
