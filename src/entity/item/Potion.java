package entity.item;

import entity.role.Combatant;

public class Potion extends Item {

    public void use(Combatant user) {
        int healAmount = 100;
        user.modifyHp((healAmount));
    }
   
}
