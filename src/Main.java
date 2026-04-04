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

        Warrior warrior = new Warrior("Warrior");
        Wizard wizard = new Wizard("Wizard");
        warrior.setUI(cli);
        wizard.setUI(cli);

        warrior.getItems().add(new SmokeBomb());
        wizard.getItems().add(new Potion());

        Goblin goblin = new Goblin("Goblin");
        Wolf wolf = new Wolf("Wolf");

        List<Combatant> players = new ArrayList<>();
        players.add(warrior);
        players.add(wizard);

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(goblin);
        enemies.add(wolf);

        RoundController roundController = new RoundController();
        for (Combatant c : players) roundController.addRole(c);
        for (Combatant c : enemies) roundController.addRole(c);

        while (players.stream().anyMatch(Combatant::isAlive) &&
               enemies.stream().anyMatch(Combatant::isAlive)) {

            roundController.runRound(cli, players, enemies);
        }

        cli.print("\n=== Battle Over ===");
        if (players.stream().anyMatch(Combatant::isAlive)) {
            cli.print("Players win!");
        } else {
            cli.print("Enemies win!");
        }
    }
}
