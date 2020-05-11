import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Automate {

    private final Etat _start;
    private final Collection<Etat> _states = new ArrayList<>();
    private final Collection<String> _keyWords = new ArrayList<>();

    public Automate(Etat start, Etat... states) {
        this._start = start;
        this._states.addAll(Arrays.asList(states));
    }

    public Etat getStart() {
        return this._start;
    }

    public Collection<Etat> getStates() {
        return this._states;
    }

    public void addKeyWords(String... keyWords) {
        this._keyWords.addAll(Arrays.asList(keyWords));
    }

    public Collection<String> getKeyWords() {
        return this._keyWords;
    }

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
