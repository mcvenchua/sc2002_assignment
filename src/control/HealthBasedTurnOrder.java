package control;

import entity.role.Combatant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HealthBasedTurnOrder implements TurnOrderStrategy {
    @Override
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        List<Combatant> ordered = new ArrayList<>(combatants);
        ordered.sort(Comparator.comparingInt(Combatant::getHp)
                .thenComparing(Combatant::getName));
        return ordered;
    }
}
