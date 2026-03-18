package entity.role;

import entity.item.Item;
import entity.skill.SpecialSkill;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Role {
    protected List<Item> inventory;        // aggregation: 0..* Items
    protected List<SpecialSkill> skills;   // aggregation: 0..* SpecialSkills

    public Player(String name, int hp, int attack, int speed, int defend) {
        super(name, hp, attack, speed, defend);
        this.inventory = new ArrayList<>();
        this.skills    = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void useItem(int index, Role target) {
        if (index < 0 || index >= inventory.size()) {
            System.out.println("No item at index " + index);
            return;
        }
        Item item = inventory.remove(index);
        item.use(target);
    }

    public void learnSkill(SpecialSkill skill) {
        skills.add(skill);
    }

    public List<Item> getInventory()      { return inventory; }
    public List<SpecialSkill> getSkills() { return skills; }
}
