package control;

import entity.role.Combatant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomTurnOrder implements TurnOrderStrategy {
    @Override
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        List<Combatant> ordered = new ArrayList<>(combatants);
        Collections.shuffle(ordered);
        return ordered;
    }
}
