package entity.role;
import entity.status.StatusEffect;
import java.util.ArrayList;
import java.util.List;
public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int attack;
    protected int speed;
    protected int defend;
    protected  int max_hp;
    private int defendBonusRounds = 0;
    protected List<StatusEffect> statusEffects = new ArrayList<>();
    protected boolean stop = false;



    public Combatant(String name, int hp, int attack, int speed, int defend, int max_hp) {
        this.name     = name;
        this.hp       = hp;
        this.attack   = attack;
        this.speed    = speed;
        this.defend   = defend;
        this.max_hp   = max_hp;
    }

    public void prepareForTurn(List<Combatant> opponents) {}

    public void takeAction(Combatant target) {}

    public boolean isAlive() {
        return hp > 0;
    }

    public void getAttack(int attackerAttack) {
        for (StatusEffect e : statusEffects) {
            if (e.isActive() && e.blocksDamage()) return;
        }
        int damage = Math.max(0, attackerAttack - this.defend);
        this.hp = Math.max(0, this.hp - damage);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefend() {
        return defend;
    }

    public void modifyDefend(int delta) {
        this.defend += delta;
    }

    public void applyDefendBuff() {
        if (defendBonusRounds == 0) {
            modifyDefend(10);
        }
        defendBonusRounds = 2;
    }

    public void onEndBattleRound() {
        if (defendBonusRounds > 0) {
            defendBonusRounds--;
            if (defendBonusRounds == 0) modifyDefend(-10);
        }
        for (StatusEffect e : statusEffects) {
            e.onRoundEnd();
        }
        statusEffects.removeIf(e -> !e.isActive());
    }

    public void modifyAttack(int attack){
        this.attack+=attack;
    }

    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void setStop(boolean stop) { this.stop = stop; }
    public void modifyHp(int delta) {
        this.hp = Math.max(0, Math.min(max_hp, this.hp + delta));
    }
}
