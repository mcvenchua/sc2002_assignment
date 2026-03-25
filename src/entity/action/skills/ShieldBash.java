package entity.action.skills;

import entity.role.Combatant;
import entity.status.Stun;
public class ShieldBash extends SpecialSkill {
    @Override
    protected int attack;
    public ShieldBash(int attack){
        this.attack=attack;
    }
    public void execute(Combatant target) {

        target.addStatusEffect(new Stun());
        target.getAttack(this.attack);
        // TODO: implement ShieldBash logic
    }
}
