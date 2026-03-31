package entity.item;

import entity.role.Combatant;

public class Potion extends Item {

    public Potion() {
        super("Potion");
    }

    @Override
    public void use(Combatant user) {
        user.modifyHp(100);
    }
}
