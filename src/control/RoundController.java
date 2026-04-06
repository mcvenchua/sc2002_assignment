package control;

import entity.role.Combatant;
import java.util.ArrayList;
import java.util.List;
import ui.UI;

public class RoundController {

    private int currentRound;
    private List<Combatant> roles;
    private List<Combatant> enemies;
    private Difficulty difficulty = Difficulty.EASY;
    private final TurnOrderStrategy turnOrderStrategy;

    public RoundController(TurnOrderStrategy turnOrderStrategy) {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
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

    public void addRole(Combatant role) {
        roles.add(role);
    }

    public List<Combatant> getRoles() {
        return roles;
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
