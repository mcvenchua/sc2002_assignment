package assignment.players;

import assignment.enemy.Enemy;

public class Wizard extends Players {
    public Wizard(){
        this.hp = 200;
        this.attack = 50;
        this.defense = 10;
        this.speed = 20;
        this.defendLast = 2;
    }
    public void BasicAttack(Enemy enemy) {
        int damage = this.attack - enemy.defense;
        if (damage < 0) {
            damage = 0;
        }
        enemy.hp -= damage;
        System.out.println("Wizard attacks for " + damage + " damage!");
    }
}
