package entity.role;

import entity.strategy.BasicAttackStrategy;

public class Goblin extends Enemy {

    public Goblin(String name) {
        super(name, 55, 35, 25, 15, new BasicAttackStrategy(), 55);
    }
}
