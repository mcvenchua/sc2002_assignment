import control.RoundController;
import entity.item.Potion;
import entity.item.SmokeBomb;
import entity.role.Combatant;
import entity.role.Goblin;
import entity.role.Warrior;
import entity.role.Wizard;
import entity.role.Wolf;
import ui.CLI;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CLI cli = CLI.getInstance();

        // Create players
        Warrior warrior = new Warrior("Warrior");
        Wizard wizard = new Wizard("Wizard");
        warrior.setUI(cli);
        wizard.setUI(cli);

        // Give items to players
        warrior.getItems().add(new SmokeBomb());
        wizard.getItems().add(new Potion());

        // Create enemies
        Goblin goblin = new Goblin("Goblin");
        Wolf wolf = new Wolf("Wolf");

        // Set up combatant lists
        List<Combatant> players = new ArrayList<>();
        players.add(warrior);
        players.add(wizard);

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(goblin);
        enemies.add(wolf);

        // Set up round controller
        RoundController roundController = new RoundController();
        for (Combatant c : players) roundController.addRole(c);
        for (Combatant c : enemies) roundController.addRole(c);

        // Battle loop
        while (players.stream().anyMatch(Combatant::isAlive) &&
               enemies.stream().anyMatch(Combatant::isAlive)) {

            cli.print("\n=== New Round ===");

            // Players act
            List<Combatant> aliveEnemies = new ArrayList<>();
            for (Combatant e : enemies) if (e.isAlive()) aliveEnemies.add(e);

            for (Combatant p : players) {
                if (p.isAlive() && !aliveEnemies.isEmpty()) {
                    cli.print("\n" + p.getName() + "'s turn (HP: " + p.getHp() + ")");
                    p.takeAction(aliveEnemies);
                    aliveEnemies.removeIf(e -> !e.isAlive());
                }
            }

            // Enemies act
            List<Combatant> alivePlayers = new ArrayList<>();
            for (Combatant p : players) if (p.isAlive()) alivePlayers.add(p);

            for (Combatant e : enemies) {
                if (e.isAlive() && !alivePlayers.isEmpty()) {
                    cli.print("\n" + e.getName() + "'s turn (HP: " + e.getHp() + ")");
                    e.takeAction(alivePlayers);
                    alivePlayers.removeIf(p -> !p.isAlive());
                }
            }

            roundController.endBattleRound();
        }

        // Result
        cli.print("\n=== Battle Over ===");
        if (players.stream().anyMatch(Combatant::isAlive)) {
            cli.print("Players win!");
        } else {
            cli.print("Enemies win!");
        }
    }
}
