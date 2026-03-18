package control;

import entity.role.Enemy;
import entity.role.Player;
import entity.role.Role;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundController {
    private int currentRound;
    private List<Role> roles;    // aggregation: 0..* Roles

    public RoundController() {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    
}
