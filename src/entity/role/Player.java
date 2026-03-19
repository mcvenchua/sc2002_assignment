package entity.role;

import java.util.ArrayList;
import java.util.List;

import entity.status.SpecialSkill;

public abstract class Player extends Role {
    protected List<SpecialSkill> skills;   // aggregation: 0..* SpecialSkills

    public Player(String name, int hp, int attack, int speed, int defend) {
        super(name, hp, attack, speed, defend);
        this.skills    = new ArrayList<>();
    }

    
    public void useItem(int index, Role target) {
        
    }

    public void learnSkill(SpecialSkill skill) {
        skills.add(skill);
    }

    public List<SpecialSkill> getSkills() { return skills; }
    
}
