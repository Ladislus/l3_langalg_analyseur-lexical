package SpecificationSyntaxique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Transition {

    private Etat _from;
    private final Etat _to;
    private final Collection<String> _characters = new ArrayList<>();

    /**
     * Constructor
     *
     * @param to the destination state
     * @param characters List of valid transition characters
     */
    public Transition(Etat to, String... characters) {
        this._to = to;
        this._characters.addAll(Arrays.asList(characters));
    }

    /**
     * Set the starting state
     *
     * @param from the state
     */
    public void setFrom(Etat from) {
        this._from = from;
    }

    /**
     * Get the destination state
     *
     * @return the state
     */
    public Etat getTo() {
        return this._to;
    }

    /**
     * Function to check character is a valid transition character
     * @param character String the character
     * @return Boolean if the character is valid
     */
    public boolean isAdmitted(String character) {
        for(String s : this._characters) {
            if(s.equals(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ToString
     *
     * @return the transition to String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder()
                    .append(this._from.getId())
                    .append(" -> ")
                    .append(this._to.getId())
                    .append(" ( ");
        for(String s : this._characters) {
            sb.append(s).append(" ");
        }
        return sb.append(")").toString();
    }
}
