package entity.role;

import entity.action.BasicAttack;
import entity.action.Defend;
import entity.action.UseItem;
import entity.action.skills.ShieldBash;

public class Warrior extends Player {
    private final ShieldBash shieldBash = new ShieldBash();

    public Warrior(String name) {
        super(name, 260, 40, 30, 20);  // hp=260, atk=40, spd=30, def=20
        skills.add(shieldBash);
    }

    @Override
    public void take_action(Combatant target) {
        while (true) {
            ui.print("Choose action: 1.Attack  2.Defend  3.Shield Bash  4.Use Item");
            int p = ui.readInt();
            switch (p) {
                case 1:
                    next_act = new BasicAttack(this, target);
                    break;
                case 2:
                    next_act = new Defend(this);
                    break;
                case 3:
                    if (!shieldBash.isReady()) {
                        ui.print("Shield Bash on cooldown (" + shieldBash.getRemainingCooldown() + " turns left).");
                        continue;
                    }
                    next_act = shieldBash;
                    break;
                case 4:
                    if (items.isEmpty()) {
                        ui.print("No items available.");
                        continue;
                    }
                    next_act = new UseItem(this, items.get(0), target);
                    break;
                default:
                    ui.print("Invalid choice.");
                    continue;
            }
            break;
        }
        next_act.execute();
    }
}
