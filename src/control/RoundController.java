package control;

import entity.role.Combatant;
import entity.role.Goblin;
import entity.role.Player;
import entity.role.Wolf;
import java.util.ArrayList;
import java.util.List;
import ui.UI;

public class RoundController {

    private int currentRound;
    private List<Combatant> roles;
    private List<Combatant> players;
    private List<Combatant> enemies;
    private Difficulty difficulty = Difficulty.EASY;
    private final TurnOrderStrategy turnOrderStrategy;

    public RoundController(TurnOrderStrategy turnOrderStrategy) {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
        this.players = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.turnOrderStrategy = turnOrderStrategy;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("difficulty cannot be null");
        }
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficultyFromLevel(int levelNo) {
        setDifficulty(Difficulty.fromLevel(levelNo));
    }

    public void addPlayer(Player player) {
        players.add(player);
        roles.add(player);
    }

    public void addRole(Combatant role) {
        roles.add(role);
    }

    public List<Combatant> getRoles() {
        return roles;
    }

    public List<Combatant> getPlayers() {
        return players;
    }

    public void spawnEnemies() {
        int g = difficulty.getInitialGoblins();
        int w = difficulty.getInitialWolves();
        for (int i = 0; i < g; i++) addEnemy(new Goblin("Goblin " + enemyLetter(i)));
        for (int i = 0; i < w; i++) addEnemy(new Wolf("Wolf " + enemyLetter(i)));
    }

    private void spawnBackupEnemies() {
        int g = difficulty.getBackupGoblins();
        int w = difficulty.getBackupWolves();
        for (int i = 0; i < g; i++) addEnemy(new Goblin("Goblin (backup) " + enemyLetter(i)));
        for (int i = 0; i < w; i++) addEnemy(new Wolf("Wolf (backup) " + enemyLetter(i)));
    }

    private static String enemyLetter(int index) {
        return String.valueOf((char) ('A' + index));
    }

    public void runBattle(UI ui, Player player) {
        ui.print("");
        ui.print("--- Battle start ---");
        ui.print("Difficulty: " + difficulty.getPdfDifficultyName());

        boolean backupSpawned = false;

        while (true) {
            boolean playerAlive = player.isAlive();
            long aliveEnemyCount = enemies.stream().filter(Combatant::isAlive).count();

            if (!playerAlive) {
                ui.print("\n=== Battle Over ===");
                ui.print("Defeated. Don't give up, try again!");
                ui.print("Enemies remaining: " + aliveEnemyCount);
                ui.print("Total rounds survived: " + currentRound);
                return;
            }

            if (aliveEnemyCount == 0) {
                if (!backupSpawned && difficulty.hasBackupWave()) {
                    ui.print("\n*** Initial wave eliminated — backup spawn! ***");
                    spawnBackupEnemies();
                    backupSpawned = true;
                    continue;
                }
                ui.print("\n=== Battle Over ===");
                ui.print("Congratulations, you have defeated all your enemies.");
                ui.print("Remaining HP: " + player.getHp());
                ui.print("Total rounds: " + currentRound);
                return;
            }

            runRound(ui, players, enemies);
        }
    }

    public void runRound(UI ui, List<Combatant> players, List<Combatant> enemies) {
        ui.print("\n=== Round " + (currentRound + 1) + " ===");

        List<Combatant> alivePlayers = new ArrayList<>();
        for (Combatant p : players) {
            if (p.isAlive()) alivePlayers.add(p);
        }
        List<Combatant> aliveEnemies = new ArrayList<>();
        for (Combatant e : enemies) {
            if (e.isAlive()) aliveEnemies.add(e);
        }

        List<Combatant> allAlive = new ArrayList<>();
        allAlive.addAll(alivePlayers);
        allAlive.addAll(aliveEnemies);
        List<Combatant> turnOrder = turnOrderStrategy.determineTurnOrder(allAlive);

        for (Combatant actor : turnOrder) {
            if (!actor.isAlive()) continue;

            List<Combatant> opponents = alivePlayers.contains(actor) ? aliveEnemies : alivePlayers;
            if (opponents.isEmpty()) continue;

            ui.print("\n" + actor.getName() + "'s turn (HP: " + actor.getHp() + ")");
            actor.prepareForTurn(opponents);
            actor.takeAction(opponents.get(0));

            alivePlayers.removeIf(x -> !x.isAlive());
            aliveEnemies.removeIf(x -> !x.isAlive());
        }

        endBattleRound();
        printRoundStats(ui, players, enemies);
    }

    private void printRoundStats(UI ui, List<Combatant> players, List<Combatant> enemies) {
        ui.print("");
        ui.print("--- End of round " + getCurrentRound() + " — battlefield stats ---");
        ui.print("Players:");
        for (Combatant p : players) {
            ui.print("  " + p.getName() + ": HP " + p.getHp() + hpSuffix(p));
        }
        ui.print("Enemies:");
        for (Combatant e : enemies) {
            ui.print("  " + e.getName() + ": HP " + e.getHp() + hpSuffix(e));
        }
        ui.print("");
    }

    private static String hpSuffix(Combatant c) {
        return c.isAlive() ? "" : " (defeated)";
    }

    public void addEnemy(Combatant enemy) {
        enemies.add(enemy);
        roles.add(enemy);
    }

    public List<Combatant> getEnemies() {
        return enemies;
    }

    public void endBattleRound() {
        for (Combatant role : roles) {
            role.onEndBattleRound();
        }
        currentRound++;
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
