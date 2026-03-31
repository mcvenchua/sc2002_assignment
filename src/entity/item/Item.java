package entity.item;

import entity.role.Combatant;

public abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void use(Combatant target);
}
