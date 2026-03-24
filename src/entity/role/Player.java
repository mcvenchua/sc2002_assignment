package entity.role;

import java.util.ArrayList;
import java.util.List;

import entity.action.skills.SpecialSkill;
import entity.item.Item;
import ui.UI;

public abstract class Player extends Combatant {
    protected List<SpecialSkill> skills = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
    protected UI ui;

    public Player(String name, int hp, int attack, int speed, int defend) {
        super(name, hp, attack, speed, defend);
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }

    public void learnSkill(SpecialSkill skill) {
        skills.add(skill);
    }

    public List<SpecialSkill> getSkills() { return skills; }
    public List<Item> getItems() { return items; }
}
