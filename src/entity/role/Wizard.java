package entity.role;

import entity.action.BasicAttack;
import entity.action.Defend;
import entity.action.UseItem;
import entity.action.skills.AcranBlast;

public class Wizard extends Player {
    private final AcranBlast arcaneBlast = new AcranBlast();

    public Wizard(String name) {
        super(name, 200, 50, 20, 10);  // hp=200, atk=50, spd=20, def=10
        skills.add(arcaneBlast);
    }

    @Override
    public void take_action(Combatant target) {
        while (true) {
            ui.print("Choose action: 1.Attack  2.Defend  3.Arcane Blast  4.Use Item");
            int p = ui.readInt();
            switch (p) {
                case 1:
                    next_act = new BasicAttack(this, target);
                    break;
                case 2:
                    next_act = new Defend(this);
                    break;
                case 3:
                    if (!arcaneBlast.isReady()) {
                        ui.print("Arcane Blast on cooldown (" + arcaneBlast.getRemainingCooldown() + " turns left).");
                        continue;
                    }
                    next_act = arcaneBlast;
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
