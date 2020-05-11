import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Transition {

    private Etat _from;
    private final Etat _to;
    private final Collection<String> _characters = new ArrayList<>();

    public Transition(Etat to, String... characters) {
        this._to = to;
        this._characters.addAll(Arrays.asList(characters));
    }

    public void setFrom(Etat from) {
        this._from = from;
    }

    public Etat getTo() {
        return this._to;
    }

    public boolean isAdmitted(String character) {
        for(String s : this._characters) {
            if(s.equals(character)) {
                return true;
            }
        }
        return false;
    }

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
