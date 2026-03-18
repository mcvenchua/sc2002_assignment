package entity.item;

import entity.role.Role;

public abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use(Role target);

    public String getName() { return name; }
}
