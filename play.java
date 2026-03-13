package assignment;

public class play{
    public static void main(String[] args) {
        /*
        Loading Screen - Initiation
        - List Players (Warrior and Wizard)
        - List Players’ Attributes
        - Option to pick between Warrior and Wizard
        - Option to pick Item
        - List levels of difficulty – Easy, Medium, Hard (Explained in 3.5)
        - List number of enemy combatants by levels of difficulty
        - List Enemies (Goblin, Wolf)
        - List Enemies’ Attributes
        - Option to select levels of difficulty */
        System.out.println("Loading Screen - Initiation");
        /*
        Loading Screen - Initiation 
        During Gameplay 
        - Games proceed in round governed by TurnOrderStrategy 
        - Conduct backup spawn of enemy combatants (if applicable) 
        - Apply existing status effects first 
        - Update HP (Existing HP-Damage+Defense), HP cannot go below 0, When damage 
        reduces HP below 0, the system clamps it to 0. The character is then defeated / dead / 
        eliminated.  Check for game ending condition 
        - Choose action (Player) 
        - Action is executed 
        - Update HP, status effects, inventory 
        - End Turn, display alive/eliminated status 
        - Check for game ending condition 
          Players win if all enemies are defeated 
          Players lose if HP reaches zero */
        System.out.println("Game Over");
        /*
        Game Completion Screen 
        - Player Victory Screen 
          Congratulations, you have defeated all your enemies. 
          Statistics: Remaining HP: | Total Rounds:  
        - Player Defeat Screen 
          Defeated. Don’t give up, try again! 
          Statistics: Enemies remaining: | Total Rounds Survived: 
          Option to replay with the same settings, start a new game (return to the home 
        screen), or exit. */
    }
}
