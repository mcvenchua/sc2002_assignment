package entity.role;

public class Wolf extends Enemy {

    public Wolf(String name) {
        super(name, 55, 14, 16, 4);
    }

    @Override
    public void takeTurn(Role target) {
        attack(target);
    }
}
