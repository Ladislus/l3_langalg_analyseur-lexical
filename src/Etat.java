import java.util.ArrayList;
import java.util.Collection;

public class Etat {

    private static int _nextId = 0;

    private final int _id = _nextId++;
    private final boolean _isFinal;
    private final Collection<Transition> _transitions = new ArrayList<>();

    public Etat(boolean isFinal) {
        this._isFinal = isFinal;
    }

    public int getId() {
        return this._id;
    }

    public boolean isFinal() {
        return this._isFinal;
    }

    public void addTransitions(Transition... transitions) {
        for(Transition transition : transitions) {
            this._transitions.add(transition);
            transition.setFrom(this);
        }
    }

    public Etat isAdmitted(String character) {
        for(Transition transition : this._transitions) {
            if(transition.isAdmitted(character)) return transition.getTo();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Etat ").append(this._id).append("\nFinal : ").append(this._isFinal);
        for(Transition t : this._transitions) {
            sb.append("\n\t").append(t);
        }
        return sb.toString();
    }

}