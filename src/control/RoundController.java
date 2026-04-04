package control;

import entity.role.Combatant;
import entity.role.Enemy;
import entity.role.Player;
import java.util.ArrayList;
import java.util.List;
import ui.UI;

public class RoundController {

    public enum Difficulty {
        EASY(1, "Easy", 3, 0, 0, 0),
        MEDIUM(2, "Medium", 1, 1, 0, 2),
        HARD(3, "Hard", 2, 0, 1, 2);

        private final int levelNo;
        private final String pdfDifficultyName;
        private final int initialGoblins;
        private final int initialWolves;
        private final int backupGoblins;
        private final int backupWolves;

        Difficulty(int levelNo, String pdfDifficultyName, int initialGoblins, int initialWolves, int backupGoblins, int backupWolves) {
            this.levelNo = levelNo;
            this.pdfDifficultyName = pdfDifficultyName;
            this.initialGoblins = initialGoblins;
            this.initialWolves = initialWolves;
            this.backupGoblins = backupGoblins;
            this.backupWolves = backupWolves;
        }

        public int getLevelNo() {
            return levelNo;
        }

        public String getPdfDifficultyName() {
            return pdfDifficultyName;
        }

        public int getInitialGoblins() {
            return initialGoblins;
        }

        public int getInitialWolves() {
            return initialWolves;
        }

        public int getBackupGoblins() {
            return backupGoblins;
        }

        public int getBackupWolves() {
            return backupWolves;
        }

        public boolean hasBackupWave() {
            return backupGoblins > 0 || backupWolves > 0;
        }
    }

    private int currentRound;
    private List<Combatant> roles;
    private List<Combatant> enemies;
    private Difficulty difficulty = Difficulty.EASY;

    public RoundController() {
        this.currentRound = 0;
        this.roles = new ArrayList<>();
        this.enemies = new ArrayList<>();
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
        switch (levelNo) {
            case 1:
                setDifficulty(Difficulty.EASY);
                break;
            case 2:
                setDifficulty(Difficulty.MEDIUM);
                break;
            case 3:
                setDifficulty(Difficulty.HARD);
                break;
            default:
                throw new IllegalArgumentException(
                        "levelNo must be 1 (Easy), 2 (Medium), or 3 (Hard) per PDF §3.5");
        }
    }

    public void set_difficulty(int levelNo) {
        setDifficultyFromLevel(levelNo);
    }

    public void addRole(Combatant role) {
        roles.add(role);
    }

    public List<Combatant> getRoles() {
        return roles;
    }

    public void runRound(UI ui, List<Combatant> players, List<Combatant> enemies) {
        ui.print("\n=== New Round ===");

        List<Combatant> aliveEnemies = new ArrayList<>();
        for (Combatant e : enemies) {
            if (e.isAlive()) {
                aliveEnemies.add(e);
            }
        }

        for (Combatant p : players) {
            if (p instanceof Player && p.isAlive() && !aliveEnemies.isEmpty()) {
                ui.print("\n" + p.getName() + "'s turn (HP: " + p.getHp() + ")");
                ((Player) p).syncEnemyTargets(aliveEnemies);
                Combatant target = aliveEnemies.get(0);
                p.takeAction(target);
                aliveEnemies.removeIf(e -> !e.isAlive());
            }
        }

        List<Combatant> alivePlayers = new ArrayList<>();
        for (Combatant p : players) {
            if (p.isAlive()) {
                alivePlayers.add(p);
            }
        }

        for (Combatant e : enemies) {
            if (e instanceof Enemy && e.isAlive() && !alivePlayers.isEmpty()) {
                ui.print("\n" + e.getName() + "'s turn (HP: " + e.getHp() + ")");
                Combatant target = alivePlayers.get(0);
                e.takeAction(target);
                alivePlayers.removeIf(x -> !x.isAlive());
            }
        }

        endBattleRound();
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
