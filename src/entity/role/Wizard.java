package entity.role;

public class Wizard extends Player {

    public Wizard(String name) {
        super(name, 70, 25, 12, 5);
    }

    public void meditate() {
        cooldown.reset(0);
        System.out.println(name + " meditates — skills recharged!");
    }
}
