package entity.role;

public abstract class Enemy extends Role {

    public Enemy(String name, int hp, int attack, int speed, int defend) {
        super(name, hp, attack, speed, defend);
    }

    // Subclasses define their AI behaviour
    public abstract void takeTurn(Role target);
}
