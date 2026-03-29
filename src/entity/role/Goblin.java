package entity.role;

import entity.action.BasicAttackStrategy;

public class Goblin extends Enemy {

    public Goblin(String name) {
        super(name, 55, 35, 25, 15, new BasicAttackStrategy(), 55);
    }
}
