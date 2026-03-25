package entity.role;

import entity.action.BasicAttackStrategy;

public class Wolf extends Enemy {

    public Wolf(String name) {
        super(name, 40, 45, 35, 5, new BasicAttackStrategy());
    }
}
