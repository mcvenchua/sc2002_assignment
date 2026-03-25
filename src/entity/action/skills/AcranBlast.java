package entity.action.skills;

import java.util.List;

import entity.role.Combatant;

public class AcranBlast extends SpecialSkill {
    @Override
    protected List<Combatant> targets;
    protected Combatant user;
    public AcranBlast(){
        this.cooldown.reset();
    }
    public void execute(Combatant targets) {
        for (int i = 0; i < this.targets.size(); i++) {
        Combatant enemy = this.targets.getAttack(i);
        // TODO: implement ArcaneBlast logic
    }
        this.user.modifyAttack(10);
}
