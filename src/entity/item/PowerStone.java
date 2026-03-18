package entity.item;

import entity.role.Role;

public class PowerStone extends Item {
    private int attackBoost;

    public PowerStone(int attackBoost) {
        super("Power Stone");
        this.attackBoost = attackBoost;
    }

    @Override
    public void use(Role target) {
        target.boostAttack(attackBoost);
        System.out.println(target.getName() + " uses Power Stone! Attack +"
                + attackBoost);
    }
}
