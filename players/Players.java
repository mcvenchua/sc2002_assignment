package assignment.players;

public class Players {
    public int hp;
    public int attack;
    public int defense;
    public int speed;
    public int defendLast=0;
    public int cool=3;
    public item item[] = new item[2];
    void BasicAttack() {
    }
    void Item() {
    }
    void SpecialSkill() {
    }
    public void cooldown() {
        if(this.defendLast>0){
            this.defendLast-=1;
        }
    }
    public void Defend() {
        this.defense +=10;
        this.defendLast=2;
    }
    public void count_all(){
        if(this.defendLast>0){
            this.defendLast-=1;
        }
        else{
            this.defense -=10;
            this.defendLast=2;
        }
    }//each round should use this function should be called at the end of the round
    public void Item(){
        
    }
   
}
