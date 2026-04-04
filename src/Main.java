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

    private static final class GameConfig {
        final int classChoice;
        final int diffChoice;
        final int itemSlot1;
        final int itemSlot2;

        GameConfig(int classChoice, int diffChoice, int itemSlot1, int itemSlot2) {
            this.classChoice = classChoice;
            this.diffChoice = diffChoice;
            this.itemSlot1 = itemSlot1;
            this.itemSlot2 = itemSlot2;
        }
    }

    public static void main(String[] args) {
        CLI cli = CLI.getInstance();

        cli.print("========== Turn-Based Combat ==========");
        cli.print("");

        GameConfig config = promptFullSetup(cli);

        while (true) {
            runBattle(cli, config);

            int next = promptPostGame(cli);
            if (next == 3) {
                cli.print("Goodbye!");
                return;
            }
            if (next == 2) {
                cli.print("");
                cli.print("--- New game (home screen) ---");
                cli.print("");
                config = promptFullSetup(cli);
            }
            // next == 1: replay with same config (loop continues)
        }
    }

    private static GameConfig promptFullSetup(UI cli) {
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

        cli.print("");
        cli.print("Pick TWO single-use items (duplicates allowed):");
        int item1 = promptItemChoice(cli, 1);
        int item2 = promptItemChoice(cli, 2);

        return new GameConfig(classChoice, diffChoice, item1, item2);
    }

    private static int promptItemChoice(UI ui, int slot) {
        while (true) {
            ui.print("Item " + slot + " — 1. Potion  2. Smoke Bomb  3. Power Stone");
            int c = ui.readInt();
            if (c >= 1 && c <= 3) {
                return c;
            }
            ui.print("Invalid choice.");
        }
    }

    private static Player createPlayerFromConfig(GameConfig cfg, CLI cli) {
        Player player = cfg.classChoice == 1 ? new Warrior("Warrior") : new Wizard("Wizard");
        player.setUI(cli);
        player.setActionStrategy(new MenuInputStrategy(cli));
        addItemByChoice(player, cfg.itemSlot1);
        addItemByChoice(player, cfg.itemSlot2);
        return player;
    }

    private static void addItemByChoice(Player player, int c) {
        if (c == 1) {
            player.getItems().add(new Potion());
        } else if (c == 2) {
            player.getItems().add(new SmokeBomb());
        } else {
            SpecialSkill skill = player.getSkills().get(0);
            player.getItems().add(new PowerStone(skill));
        }
    }

    private static void runBattle(CLI cli, GameConfig config) {
        Player player = createPlayerFromConfig(config, cli);

        RoundController roundController = new RoundController();
        roundController.setDifficultyFromLevel(config.diffChoice);
        Difficulty difficulty = roundController.getDifficulty();

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
                return;
            }

            roundController.runRound(cli, players, enemies);
        }
    }

    private static int promptPostGame(UI ui) {
        while (true) {
            ui.print("");
            ui.print("What next?");
            ui.print("  1. Replay with the same settings");
            ui.print("  2. New game (return to home screen)");
            ui.print("  3. Exit");
            ui.print("Enter 1, 2, or 3:");
            int r = ui.readInt();
            if (r >= 1 && r <= 3) {
                return r;
            }
            ui.print("Invalid choice.");
        }
    }

    private static String enemyLetter(int indexZeroBased) {
        return String.valueOf((char) ('A' + indexZeroBased));
    }

    private static void spawnInitialEnemies(Difficulty d, List<Combatant> enemies, RoundController rc) {
        int g = d.getInitialGoblins();
        int w = d.getInitialWolves();
        for (int i = 0; i < g; i++) {
            Goblin goblin = new Goblin("Goblin " + enemyLetter(i));
            enemies.add(goblin);
            rc.addEnemy(goblin);
        }
        for (int i = 0; i < w; i++) {
            Wolf wolf = new Wolf("Wolf " + enemyLetter(i));
            enemies.add(wolf);
            rc.addEnemy(wolf);
        }
    }

    private static void spawnBackup(Difficulty d, List<Combatant> enemies, RoundController rc) {
        for (int i = 0; i < d.getBackupGoblins(); i++) {
            Goblin goblin = new Goblin("Goblin (backup) " + enemyLetter(i));
            enemies.add(goblin);
            rc.addEnemy(goblin);
        }
        for (int i = 0; i < d.getBackupWolves(); i++) {
            Wolf wolf = new Wolf("Wolf (backup) " + enemyLetter(i));
            enemies.add(wolf);
            rc.addEnemy(wolf);
        }
    }
}
