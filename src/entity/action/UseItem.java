package entity.action;

import entity.item.Item;
import entity.role.Combatant;

public class UseItem implements Action {
    private Combatant user;
    private Item item;
    private Combatant target;

    public UseItem(Combatant user, Item item, Combatant target) {
        this.user = user;
        this.item = item;
        this.target = target;
    }

    @Override
    public void execute(Combatant target) {
        // TODO: implement use item logic
    }
}
