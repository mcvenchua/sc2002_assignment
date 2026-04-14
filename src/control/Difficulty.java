package control;

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

    public int getLevelNo() { return levelNo; }
    public String getPdfDifficultyName() { return pdfDifficultyName; }
    public int getInitialGoblins() { return initialGoblins; }
    public int getInitialWolves() { return initialWolves; }
    public int getBackupGoblins() { return backupGoblins; }
    public int getBackupWolves() { return backupWolves; }

    public boolean hasBackupWave() {
        return backupGoblins > 0 || backupWolves > 0;
    }

    public static Difficulty fromLevel(int levelNo) {
        for (Difficulty d : values()) {
            if (d.levelNo == levelNo) return d;
        }
        throw new IllegalArgumentException(
                "levelNo must be 1 (Easy), 2 (Medium), or 3 (Hard) ");
    }
}
