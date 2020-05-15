package SpecificationSyntaxique;

import java.util.ArrayList;
import java.util.Collection;

public class Etat {

    private static int _nextId = 0;

    private final int _id = _nextId++;
    private final boolean _isFinal;
    private final Collection<Transition> _transitions = new ArrayList<>();

    /**
     * Constructor
     *
     * @param isFinal Boolean whether the state is final or not
     */
    public Etat(boolean isFinal) {
        this._isFinal = isFinal;
    }

    /**
     * ID getter
     * @return Int the ID
     */
    public int getId() {
        return this._id;
    }

    /**
     * isFinal getter
     * @return Boolean whether the state is final or not
     */
    public boolean isFinal() {
        return this._isFinal;
    }

    /**
     * Function to add a list of transition from this state to another
     * @param transitions List of transitions to add
     */
    public void addTransitions(Transition... transitions) {
        for(Transition transition : transitions) {
            this._transitions.add(transition);
            transition.setFrom(this);
        }
    }

    /**
     * Function to check whether a character is valid (a transition exist from this state to another with this character)
     * @param character String the given character
     * @return The next state if it exist, null else
     */
    public Etat isAdmitted(String character) {
        for(Transition transition : this._transitions) {
            if(transition.isAdmitted(character)) return transition.getTo();
        }
        return null;
    }

    /**
     * ToString
     *
     * @return The state to String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Etat ").append(this._id).append("\nFinal : ").append(this._isFinal);
        for(Transition t : this._transitions) {
            sb.append("\n\t").append(t);
        }
        return sb.toString();
    }

}