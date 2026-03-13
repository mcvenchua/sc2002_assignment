package assignment.players;

import assignment.enemy.Enemy;

public class Warrior extends Players {
    public Warrior() {
        this.hp = 260;
        this.attack = 40;
        this.defense = 60;
        this.speed = 30;
        this.defendLast = 2;
    }
    public void BasicAttack(Enemy enemy) {
        int damage = this.attack - enemy.defense;
        if (damage < 0) {
            damage = 0;
        }
        enemy.hp -= damage;
        System.out.println("Warrior attacks for " + damage + " damage!");
    }
    public void SpecialSkill(Enemy enemy){
        enemy.silent=2;
        enemy.hp -= this.attack;
        System.out.println("Warrior uses special skill for " + this.attack + " damage!");
    }
    

}