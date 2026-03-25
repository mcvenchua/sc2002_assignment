package entity.action;
import entity.role.Combatant;
public interface Action {
    void execute(Combatant target);
}