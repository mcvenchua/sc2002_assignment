package entity.strategy;

import entity.action.Action;
import entity.action.BasicAttack;
import entity.action.Defend;
import entity.action.UseItem;
import entity.item.Item;
import entity.role.Combatant;
import entity.role.Player;
import entity.role.Wizard;
import java.util.List;
import ui.UI;

public class MenuInputStrategy implements PlayerActionStrategy {
    private final UI ui;

    public MenuInputStrategy(UI ui) {
        this.ui = ui;
    }

    /** @return picked enemy, or null if none / invalid (caller should continue loop). */
    private Combatant promptPickEnemy(Player self, String heading) {
        List<Combatant> foes = self.getEnemyTargets();
        if (foes.isEmpty()) {
            ui.print("No enemies.");
            return null;
        }
        ui.print(heading);
        for (int i = 0; i < foes.size(); i++) {
            Combatant e = foes.get(i);
            ui.print((i + 1) + ". " + e.getName() + " (HP: " + e.getHp() + ")");
        }
        int pick = ui.readInt();
        if (pick < 1 || pick > foes.size()) {
            ui.print("Invalid choice.");
            return null;
        }
        return foes.get(pick - 1);
    }

    @Override
    public Action chooseAction(Player self, Combatant target) {
        while (true) {
            ui.print("Choose action: 1.Attack  2.Defend  3.Special Skill  4.Use Item");
            int choice = ui.readInt();
            switch (choice) {
                case 1: {
                    Combatant victim = promptPickEnemy(self, "Choose target:");
                    if (victim == null) {
                        continue;
                    }
                    return new BasicAttack(self, victim, self.getAttack());
                }
                case 2:
                    return new Defend(self);
                case 3:
                    if (self.getSkills().isEmpty()) {
                        ui.print("No skills available.");
                        continue;
                    }
                    Combatant skillTarget;
                    if (self instanceof Wizard) {
                        skillTarget = null;
                    } else {
                        skillTarget = promptPickEnemy(self, "Choose target for special skill:");
                        if (skillTarget == null) {
                            continue;
                        }
                    }
                    boolean used = self.activateSkill(skillTarget);
                    if (!used) {
                        continue;
                    }
                    return null; 
                case 4:
                    if (self.getItems().isEmpty()) {
                        ui.print("No items available.");
                        continue;
                    }
                    ui.print("Choose item:");
                    for (int i = 0; i < self.getItems().size(); i++) {
                        ui.print((i + 1) + ". " + self.getItems().get(i).getName());
                    }
                    int itemChoice = ui.readInt();
                    if (itemChoice < 1 || itemChoice > self.getItems().size()) {
                        ui.print("Invalid choice.");
                        continue;
                    }
                    Item chosen = self.getItems().get(itemChoice - 1);
                    self.getItems().remove(chosen);
                    return new UseItem(self, chosen, target);
                default:
                    ui.print("Invalid choice.");
            }
        }
    }
}
