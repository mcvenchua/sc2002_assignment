package entity.role;

public class Warrior extends Player {

    public Warrior(String name) {
        super(name, 120, 18, 8, 12);
    }

    public void taunt(Role target) {
        System.out.println(name + " taunts " + target.getName() + "!");
    }
}
