package entity.role;

import entity.strategy.BasicAttackStrategy;

public class Wolf extends Enemy {

    public Wolf(String name) {
        super(name, 40, 45, 35, 5, new BasicAttackStrategy(), 40);
    }
}
