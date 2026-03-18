package entity.item;

import entity.role.Role;

public class SmokeBomb extends Item {

    public SmokeBomb() {
        super("Smoke Bomb");
    }

    @Override
    public void use(Role target) {
        // TODO: implement evasion / escape logic
        System.out.println(target.getName() + " throws a Smoke Bomb!");
    }
}
