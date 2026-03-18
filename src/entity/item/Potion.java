package entity.item;

import entity.role.Role;

public class Potion extends Item {
    private int healAmount;

    public Potion(int healAmount) {
        super("Potion");
        this.healAmount = healAmount;
    }

    @Override
    public void use(Role target) {
        target.heal(healAmount);
        System.out.println(target.getName() + " drinks a Potion and recovers "
                + healAmount + " HP!");
    }
}
