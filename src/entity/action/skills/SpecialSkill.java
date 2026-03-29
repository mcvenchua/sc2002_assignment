package entity.action.skills;

import entity.action.Action;

public abstract class SpecialSkill implements Action {
    public CoolDown cooldown = new CoolDown(0);
    public boolean isReady() {
        return cooldown.isReady();
    }

    public void resetCooldown(int turns) {
        cooldown.set(turns);
    }

    public void tickCooldown() {
        cooldown.pass();
    }

    public int getRemainingCooldown() {
        return cooldown.getRemainingTicks();
    }
}
