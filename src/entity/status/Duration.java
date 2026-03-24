package entity.status;

public class Duration {
    private int remainingRound;
    public Duration(int a){
        this.remainingRound=a;
    }
    public void set(int a){
        this.remainingRound=a;
    }
    public void reset(){
        this.remainingRound=0;
    }
    public int get(){
        return this.remainingRound;
    }
    public boolean is_over(){
        return this.remainingRound==0;
    }
    
}