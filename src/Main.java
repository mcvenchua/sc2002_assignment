import control.RoundController;
import control.SpeedBasedTurnOrder;
import entity.item.Potion;
import entity.item.PowerStone;
import entity.item.SmokeBomb;
import entity.role.Player;
import entity.role.Warrior;
import entity.role.Wizard;
import entity.strategy.MenuInputStrategy;
import ui.CLI;
import ui.UI;

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
    //root
    public static void main(String[] args) {
        CLI cli = CLI.getInstance();

        cli.print("========== Turn-Based Combat ==========");
        cli.print("");

        GameConfig config = promptFullSetup(cli);

        while (true) {
            new RoundController(new SpeedBasedTurnOrder()).runBattle(cli, createPlayer(config, cli), config.diffChoice);

            int next = promptPostGame(cli);
            if (next == 3) { cli.print("Goodbye!"); return; }
            if (next == 2) { cli.print(""); cli.print("--- New game ---"); cli.print(""); config = promptFullSetup(cli); }
            // next == 1: replay with same config (loop continues)
        }
    }

    private static GameConfig promptFullSetup(UI cli) {
        cli.print("Choose your class:");
        cli.print("  1. Warrior  — HP 260, ATK 40, DEF 20, SPD 30  (Shield Bash)");
        cli.print("\n" + //
                        "                          ,dM\n" + //
                        "                         dMMP\n" + //
                        "                        dMMM'\n" + //
                        "                        \\MM/\n" + //
                        "                        dMMm.\n" + //
                        "                       dMMP'_\\---.\n" + //
                        "                      _| _  p ;88;`.\n" + //
                        "                    ,db; p >  ;8P|  `.\n" + //
                        "                   (``T8b,__,'dP |   |\n" + //
                        "                   |   `Y8b..dP  ;_  |\n" + //
                        "                   |    |`T88P_ /  `\\;\n" + //
                        "                   :_.-~|d8P'`Y/    /\n" + //
                        "                    \\_   TP    ;   7`\\\n" + //
                        "         ,,__        >   `._  /'  /   `\\_\n" + //
                        "         `._ \"\"\"\"~~~~------|`\\;' ;     ,'\n" + //
                        "            \"\"\"~~~-----~~~'\\__[|;' _.-'  `\\\n" + //
                        "                    ;--..._     .-'-._     ;\n" + //
                        "                   /      /`~~\"'   ,'`\\_ ,/\n" + //
                        "                  ;_    /'        /    ,/\n" + //
                        "                  | `~-l         ;    /\n" + //
                        "                  `\\    ;       /\\.._|\n" + //
                        "                    \\    \\      \\     \\\n" + //
                        "                    /`---';      `----'\n" + //
                        "                   (     /            fsc\n" + //
                        "                    `---'");
        cli.print("  2. Wizard    — HP 200, ATK 50, DEF 10, SPD 20  (Arcane Blast)");
        cli.print("                       ,---.\r\n" + //
                        "                       /    |\r\n" + //
                        "                      /     |\r\n" + //
                        "  Gandalf            /      |\r\n" + //
                        "                    /       |\r\n" + //
                        "               ___,'        |\r\n" + //
                        "             <  -'          :\r\n" + //
                        "              `-.__..--'``-,_\\_\r\n" + //
                        "                 |o/ <o>` :,.)_`>\r\n" + //
                        "                 :/ `     ||/)\r\n" + //
                        "                 (_.).__,-` |\\\r\n" + //
                        "                 /( `.``   `| :\r\n" + //
                        "                 \\'`-.)  `  ; ;\r\n" + //
                        "                 | `       /-<\r\n" + //
                        "                 |     `  /   `.\r\n" + //
                        " ,-_-..____     /|  `    :__..-'\\\r\n" + //
                        "/,'-.__\\\\  ``-./ :`      ;       \\\r\n" + //
                        "`\\ `\\  `\\\\  \\ :  (   `  /  ,   `. \\\r\n" + //
                        "  \\` \\   \\\\   |  | `   :  :     .\\ \\\r\n" + //
                        "   \\ `\\_  ))  :  ;     |  |      ): :\r\n" + //
                        "  (`-.-'\\ ||  |\\ \\   ` ;  ;       | |\r\n" + //
                        "   \\-_   `;;._   ( `  /  /_       | |\r\n" + //
                        "    `-.-.// ,'`-._\\__/_,'         ; |\r\n" + //
                        "       \\:: :     /     `     ,   /  |\r\n" + //
                        "        || |    (        ,' /   /   |\r\n" + //
                        "        ||                ,'   / SSt|\r" + //
                        "");
        int classChoice;
        while (true) {
            cli.print("Enter 1 or 2:");
            classChoice = cli.readInt();
            if (classChoice == 1 || classChoice == 2) break;
            cli.print("Invalid choice.");
        }

        cli.print("");
        cli.print("Difficulty (PDF §3.5):");
        cli.print("  1. Easy   — 3 Goblins");
        cli.print(
    "  ,      ,           ,      ,           ,      ,\n" +
    " /(.-\"\"-.)\\        /(.-\"\"-.)\\        /(.-\"\"-.)\\\n" +
    "|\\  \\/      \\/  /|  |\\  \\/      \\/  /|  |\\  \\/      \\/  /|\n" +
    "| \\ / =.  .= \\ / |  | \\ / =.  .= \\ / |  | \\ / =.  .= \\ / |\n" +
    "\\( \\   o\\/o   / )/  \\( \\   o\\/o   / )/  \\( \\   o\\/o   / )/\n" +
    " \\_, '-/  \\-' ,_/    \\_, '-/  \\-' ,_/    \\_, '-/  \\-' ,_/\n" +
    "   /   \\__/   \\        /   \\__/   \\        /   \\__/   \\\n" +
    "   \\ \\__/\\__/ /        \\ \\__/\\__/ /        \\ \\__/\\__/ /\n" +
    " ___\\ \\|--|/ /___    ___\\ \\|--|/ /___    ___\\ \\|--|/ /___\n" +
    "/`    \\      /    `\\ /`    \\      /    `\\ /`    \\      /    `\\\n" +
    "/       '----'       \\/       '----'       \\/       '----'       \\"
);
        cli.print("  2. Medium — 1 Goblin + 1 Wolf, then backup: 2 Wolves");
        cli.print("  ,      ,\n" + //
                        "            /(.-\"\"-.)\\\n" + //
                        "        |\\  \\/      \\/  /|\n" + //
                        "        | \\ / =.  .= \\ / |\n" + //
                        "        \\( \\   o\\/o   / )/\n" + //
                        "         \\_, '-/  \\-' ,_/\n" + //
                        "           /   \\__/   \\\n" + //
                        "           \\ \\__/\\__/ /\n" + //
                        "         ___\\ \\|--|/ /___\n" + //
                        "       /`    \\      /    `\\\n" + //
                        "  /       '----'       \\");
        cli.print(
    "                        ,     ,                       ,     ,                       ,     ,\n" +
    "                        |\\---/|                       |\\---/|                       |\\---/|\n" +
    "                       /  , , |                      /  , , |                      /  , , |\n" +
    "                  __.-'|  / \\ /                 __.-'|  / \\ /                 __.-'|  / \\ /\n" +
    "         __ ___.-'        ._O|        __ ___.-'        ._O|        __ ___.-'        ._O|\n" +
    "      .-'  '        :      _/      .-'  '        :      _/      .-'  '        :      _/\n" +
    "     / ,    .        .     |      / ,    .        .     |      / ,    .        .     |\n" +
    "    :  ;    :        :   _/      :  ;    :        :   _/      :  ;    :        :   _/\n" +
    "    |  |   .'     __:   /        |  |   .'     __:   /        |  |   .'     __:   /\n" +
    "    |  :   /'----'| \\  |         |  :   /'----'| \\  |         |  :   /'----'| \\  |\n" +
    "    \\  |\\  |      | /| |         \\  |\\  |      | /| |         \\  |\\  |      | /| |\n" +
    "     '.'| /       || \\ |          '.'| /       || \\ |          '.'| /       || \\ |\n" +
    "     | /|.'       '.l \\\\_         | /|.'       '.l \\\\_         | /|.'       '.l \\\\_\n" +
    "     || ||             '-'         || ||             '-'          || ||             '-'\n" +
    "     '-''-'                       '-''-'                         '-''-'"
);
        cli.print("  3. Hard   — 2 Goblins, then backup: 1 Goblin + 2 Wolves");
                cli.print(
    "  ,      ,           ,      ,           ,      ,\n" +
    " /(.-\"\"-.)\\        /(.-\"\"-.)\\        /(.-\"\"-.)\\\n" +
    "|\\  \\/      \\/  /|  |\\  \\/      \\/  /|  |\\  \\/      \\/  /|\n" +
    "| \\ / =.  .= \\ / |  | \\ / =.  .= \\ / |  | \\ / =.  .= \\ / |\n" +
    "\\( \\   o\\/o   / )/  \\( \\   o\\/o   / )/  \\( \\   o\\/o   / )/\n" +
    " \\_, '-/  \\-' ,_/    \\_, '-/  \\-' ,_/    \\_, '-/  \\-' ,_/\n" +
    "   /   \\__/   \\        /   \\__/   \\        /   \\__/   \\\n" +
    "   \\ \\__/\\__/ /        \\ \\__/\\__/ /        \\ \\__/\\__/ /\n" +
    " ___\\ \\|--|/ /___    ___\\ \\|--|/ /___    ___\\ \\|--|/ /___\n" +
    "/`    \\      /    `\\ /`    \\      /    `\\ /`    \\      /    `\\\n" +
    "/       '----'       \\/       '----'       \\/       '----'       \\"
);
        cli.print(
    "                        ,     ,                               ,     ,\n" +
    "                        |\\---/|                               |\\---/|\n" +
    "                       /  , , |                              /  , , |\n" +
    "                  __.-'|  / \\ /                         __.-'|  / \\ /\n" +
    "         __ ___.-'        ._O|                __ ___.-'        ._O|\n" +
    "      .-'  '        :      _/             .-'  '        :      _/\n" +
    "     / ,    .        .     |             / ,    .        .     |\n" +
    "    :  ;    :        :   _/             :  ;    :        :   _/\n" +
    "    |  |   .'     __:   /               |  |   .'     __:   /\n" +
    "    |  :   /'----'| \\  |                |  :   /'----'| \\  |\n" +
    "    \\  |\\  |      | /| |                \\  |\\  |      | /| |\n" +
    "     '.'| /       || \\ |                 '.'| /       || \\ |\n" +
    "     | /|.'       '.l \\\\_                | /|.'       '.l \\\\_\n" +
    "     || ||             '-'                 || ||             '-'\n" +
    "     '-''-'                              '-''-'"
);
        int diffChoice;
        while (true) {
            cli.print("Enter 1, 2, or 3:");
            diffChoice = cli.readInt();
            if (diffChoice >= 1 && diffChoice <= 3) break;
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
            if (c >= 1 && c <= 3) return c;
            ui.print("Invalid choice.");
        }
    }

    private static Player createPlayer(GameConfig cfg, CLI cli) {
        Player player = cfg.classChoice == 1 ? new Warrior("Warrior") : new Wizard("Wizard");
        player.setUI(cli);
        player.setActionStrategy(new MenuInputStrategy(cli));
        for (int c : new int[]{cfg.itemSlot1, cfg.itemSlot2}) {
            switch (c) {
                case 1 -> player.getItems().add(new Potion());
                case 2 -> player.getItems().add(new SmokeBomb());
                default -> player.getItems().add(new PowerStone(player.getSkills().get(0)));
            }
        }
        return player;
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
            if (r >= 1 && r <= 3) return r;
            ui.print("Invalid choice.");
        }
    }
}
