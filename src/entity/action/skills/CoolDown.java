package entity.action.skills;

public class CoolDown {
    // each special skill has an independent cool_down instance
    private int remainingTicks;

    public CoolDown(int time) {
        this.remainingTicks = time;
    }

    public void reset() {
        this.remainingTicks = 0;
    }

    public void pass() {
        if (this.remainingTicks > 0) {
            this.remainingTicks--;
        }
    }

    public void set(int ticks) {
        this.remainingTicks = ticks;
    }

    public boolean isReady() {
        return this.remainingTicks == 0;
    }

    public int getRemainingTicks() {
        return this.remainingTicks;
    }
}
