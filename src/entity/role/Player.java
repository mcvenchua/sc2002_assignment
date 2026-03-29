package entity.role;

import entity.action.BasicAttack;
import entity.action.Defend;
import entity.action.UseItem;
import entity.action.skills.SpecialSkill;
import entity.item.Item;
import java.util.ArrayList;
import java.util.List;
import ui.UI;

public abstract class Player extends Combatant {
    protected List<SpecialSkill> skills = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
    protected UI ui;
    protected List<Combatant> enemy = new ArrayList<>();

    public Player(String name, int hp, int attack, int speed, int defend,int max_hp) {
        super(name, hp, attack, speed, defend, max_hp);
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }

    public void learnSkill(SpecialSkill skill) {
        skills.add(skill);
    }

    @Override
    public void takeAction(Combatant target) {
        while (true) {
            ui.print("Choose action: 1.Attack  2.Defend  3.Special Skill  4.Use Item");
            int p = ui.readInt();
            switch (p) {
                case 1:
                    new BasicAttack(this, target, this.attack).execute(target);
                    break;
                case 2:
                    new Defend(this).execute(null);
                    break;
                case 3:
                    break;
                case 4:
                    if (items.isEmpty()) {
                        ui.print("No items available.");
                        continue;
                    }
                    new UseItem(this, items.get(0), null).execute(null);
                    break;
                default:
                    ui.print("Invalid choice.");
                    continue;
            }
            break;
        }
    }

    private Combatant selectTarget(List<Combatant> targets) {
        while (true) {
            ui.print("Select target:");
            for (int i = 0; i < targets.size(); i++) {
                Combatant t = targets.get(i);
                ui.print((i + 1) + ". " + t.getName() + " (HP: " + t.getHp() + ")");
            }
            int choice = ui.readInt();
            if (choice >= 1 && choice <= targets.size()) {
                return targets.get(choice - 1);
            }
            ui.print("Invalid choice.");
        }
    }

    // Subclasses implement their specific special skill logic.
    // Return true if skill was used, false if on cooldown (loop will retry).
    protected abstract boolean activateSkill(Combatant target);

    public List<SpecialSkill> getSkills() { return skills; }
    public List<Item> getItems() { return items; }
}
