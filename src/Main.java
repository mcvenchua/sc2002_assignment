import control.RoundController;
import control.RoundController.Difficulty;
import entity.action.skills.SpecialSkill;
import entity.item.Potion;
import entity.item.PowerStone;
import entity.item.SmokeBomb;
import entity.role.Combatant;
import entity.role.Goblin;
import entity.role.Player;
import entity.role.Warrior;
import entity.role.Wizard;
import entity.role.Wolf;
import entity.strategy.MenuInputStrategy;
import ui.CLI;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CLI cli = CLI.getInstance();

        cli.print("========== Turn-Based Combat ==========");
        cli.print("");
        cli.print("Choose your class:");
        cli.print("  1. Warrior  — HP 260, ATK 40, DEF 20, SPD 30  (Shield Bash)");
        cli.print("  2. Wizard    — HP 200, ATK 50, DEF 10, SPD 20  (Arcane Blast)");
        int classChoice;
        while (true) {
            cli.print("Enter 1 or 2:");
            classChoice = cli.readInt();
            if (classChoice == 1 || classChoice == 2) {
                break;
            }
            cli.print("Invalid choice.");
        }

        Player player = classChoice == 1 ? new Warrior("Warrior") : new Wizard("Wizard");
        player.setUI(cli);
        player.setActionStrategy(new MenuInputStrategy(cli));

        cli.print("");
        cli.print("Difficulty (PDF §3.5):");
        cli.print("  1. Easy   — 3 Goblins");
        cli.print("  2. Medium — 1 Goblin + 1 Wolf, then backup: 2 Wolves");
        cli.print("  3. Hard   — 2 Goblins, then backup: 1 Goblin + 2 Wolves");
        int diffChoice;
        while (true) {
            cli.print("Enter 1, 2, or 3:");
            diffChoice = cli.readInt();
            if (diffChoice >= 1 && diffChoice <= 3) {
                break;
            }
            cli.print("Invalid choice.");
        }

        RoundController roundController = new RoundController();
        roundController.setDifficultyFromLevel(diffChoice);
        Difficulty difficulty = roundController.getDifficulty();

        cli.print("");
        cli.print("Pick TWO single-use items (duplicates allowed):");
        for (int slot = 1; slot <= 2; slot++) {
            promptAndAddItem(cli, player, slot);
        }

        List<Combatant> players = new ArrayList<>();
        players.add(player);

        List<Combatant> enemies = new ArrayList<>();
        spawnInitialEnemies(difficulty, enemies, roundController);
        roundController.addRole(player);

        cli.print("");
        cli.print("--- Battle start ---");
        cli.print("Difficulty: " + difficulty.getPdfDifficultyName());

        boolean backupSpawned = false;

        while (true) {
            boolean playerAlive = player.isAlive();
            long aliveEnemyCount = enemies.stream().filter(Combatant::isAlive).count();

            if (!playerAlive) {
                cli.print("\n=== Battle Over ===");
                cli.print("Defeated. Don't give up, try again!");
                cli.print("Enemies remaining: " + aliveEnemyCount);
                cli.print("Total rounds survived: " + roundController.getCurrentRound());
                promptReplay(cli);
                return;
            }

            if (aliveEnemyCount == 0) {
                if (!backupSpawned && difficulty.hasBackupWave()) {
                    cli.print("\n*** Initial wave eliminated — backup spawn! ***");
                    spawnBackup(difficulty, enemies, roundController);
                    backupSpawned = true;
                    continue;
                }
                cli.print("\n=== Battle Over ===");
                cli.print("Congratulations, you have defeated all your enemies.");
                cli.print("Remaining HP: " + player.getHp());
                cli.print("Total rounds: " + roundController.getCurrentRound());
                promptReplay(cli);
                return;
            }

            roundController.runRound(cli, players, enemies);
        }
    }

    private static void promptAndAddItem(UI ui, Player player, int slot) {
        while (true) {
            ui.print("Item " + slot + " — 1. Potion  2. Smoke Bomb  3. Power Stone");
            int c = ui.readInt();
            if (c == 1) {
                player.getItems().add(new Potion());
                return;
            }
            if (c == 2) {
                player.getItems().add(new SmokeBomb());
                return;
            }
            if (c == 3) {
                SpecialSkill skill = player.getSkills().get(0);
                player.getItems().add(new PowerStone(skill));
                return;
            }
            ui.print("Invalid choice.");
        }
    }

    private static void spawnInitialEnemies(Difficulty d, List<Combatant> enemies, RoundController rc) {
        int g = d.getInitialGoblins();
        int w = d.getInitialWolves();
        for (int i = 0; i < g; i++) {
            Goblin goblin = new Goblin("Goblin " + (i + 1));
            enemies.add(goblin);
            rc.addEnemy(goblin);
        }
        for (int i = 0; i < w; i++) {
            Wolf wolf = new Wolf("Wolf " + (i + 1));
            enemies.add(wolf);
            rc.addEnemy(wolf);
        }
    }

    private static void spawnBackup(Difficulty d, List<Combatant> enemies, RoundController rc) {
        for (int i = 0; i < d.getBackupGoblins(); i++) {
            Goblin goblin = new Goblin("Goblin (backup) " + (i + 1));
            enemies.add(goblin);
            rc.addEnemy(goblin);
        }
        for (int i = 0; i < d.getBackupWolves(); i++) {
            Wolf wolf = new Wolf("Wolf (backup) " + (i + 1));
            enemies.add(wolf);
            rc.addEnemy(wolf);
        }
    }

    private static void promptReplay(UI ui) {
        ui.print("");
        ui.print("Replay? 1. Same settings (restart)  2. Exit");
        int r = ui.readInt();
        if (r == 1) {
            main(new String[0]);
        }
    }
}
