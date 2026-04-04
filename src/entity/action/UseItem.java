package entity.action;

import entity.item.Item;
import entity.item.PowerStone;
import entity.role.Combatant;

public class UseItem implements Action {
    private final Combatant user;
    private final Item item;
    private final Combatant target;

    public UseItem(Combatant user, Item item, Combatant target) {
        this.user = user;
        this.item = item;
        this.target = target;
    }

    @Override
    public void execute(Combatant target) {
        Combatant resolved;
        if (item instanceof PowerStone) {
            resolved = target != null ? target : (this.target != null ? this.target : user);
        } else {
            resolved = user;
        }
        item.use(resolved);
    }
}
