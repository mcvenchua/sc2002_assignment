package control;
import entity.role.Combatant;
import java.util.ArrayList;
import java.util.List;

public class RoundController {
    private int currentRound;
    private List<Combatant> roles;    // aggregation: 0..* Roles
    private List<Combatant> enemies;  // all enemy combatants

    public RoundController() {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void addRole(Combatant role) {
        roles.add(role);
    }

    public void addEnemy(Combatant enemy) {
        enemies.add(enemy);
        roles.add(enemy);
    }

    public List<Combatant> getEnemies() {
        return enemies;
    }

    public void endBattleRound() {
        for (Combatant role : roles) {
            role.onEndBattleRound();
        }
        currentRound++;
    }
    public void set_difficulty(int a){
        
    }

}
