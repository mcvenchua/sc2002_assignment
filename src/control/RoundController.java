package control;

import entity.role.Combatant;
import java.util.ArrayList;
import java.util.List;

public class RoundController {
    private int currentRound;
    private List<Combatant> roles;    // aggregation: 0..* Roles

    public RoundController() {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
    }

    public void addRole(Combatant role) {
        roles.add(role);
    }

    
}
