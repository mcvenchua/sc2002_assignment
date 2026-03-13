package assignment.enemy;

import assignment.players.Players;

public class Enemy {
    public int hp;
    public int attack;
    public int defense;
    public int speed;
    public int silent=0;
    public void BasicAttack(Players player) {
        if (this.silent>0){
            System.out.println("Enemy is silenced and cannot attack!");
            return;
        }
        int damage = this.attack - player.defense;
        if (damage < 0) {
            damage = 0;
        }
        player.hp -= damage;
    }
    public void count_all(){
        if(this.silent>0){
            this.silent-=1;
        }
    }
}
