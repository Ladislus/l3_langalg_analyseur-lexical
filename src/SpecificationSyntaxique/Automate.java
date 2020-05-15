package SpecificationSyntaxique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Automate {

    private final Etat _start;
    private final Collection<Etat> _states = new ArrayList<>();
    private final Collection<String> _keyWords = new ArrayList<>();

    /**
     * Constructor
     *
     * @param start The starting state
     * @param states All the remaining states
     */
    public Automate(Etat start, Etat... states) {
        this._start = start;
        this._states.addAll(Arrays.asList(states));
    }

    /**
     * Function to add special keywords to the automaton
     *
     * @param keyWords A list of keywords
     */
    public void addKeyWords(String... keyWords) {
        this._keyWords.addAll(Arrays.asList(keyWords));
    }

    /**
     * Function to test whether a given String is recognized by this automaton
     * @param word String to test
     * @return String 'missingTransition' if invalid, 'notFinal' if incomplet (didn't reach a final state), 'keyWord' if it is a keyword, 'final' if valid
     */
    public String wordAdmitted(String word) {
        if(!this._keyWords.contains(word)) {
            Etat currentState = this._start;
            String[] characters = word.split("");

            for(String s : characters) {
                Etat next = currentState.isAdmitted(s);
                //If no transition exist with this character
                if(Objects.isNull(next)) return "missingTransition";
                else {
                    currentState = next;
                }
            }
            //If last state is final
            if(currentState.isFinal()) return "final";
            //If last state isn't final
            else return "notFinal";
        } else {
            //Word is in keyWords
            return "keyWord";
        }
    }

    /**
     * ToString
     *
     * @return The automaton as String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(this._start.toString()).append("\n");
        for(Etat e : this._states) {
            sb.append(e).append("\n");
        }
        sb.append("KeyWords : ");
        for(String s : this._keyWords) {
            sb.append(s).append(" ");
        }
        return sb.append("\n").toString();
    }
}
