import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ALG01 {

    private final Automate _numberAutomaton;
    private final Automate _identAutomaton;

    public ALG01(Automate numberAutomaton, Automate identAutomaton) {
        this._numberAutomaton = numberAutomaton;
        this._identAutomaton = identAutomaton;
    }

    public String execute(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        String currentLine;
        while(sc.hasNext()) {
            currentLine = sc.nextLine().replaceAll(";", " ;");
            String[] words = currentLine.split(" ");
            for(String word : words) {

                //Ignore spaces
                if(word.isBlank()) continue;

                String admitted = this._identAutomaton.wordAdmitted(word);

                if(admitted.equals("keyWord")) sb.append(" ").append(word);
                else if(admitted.equals("final")) sb.append(" ident");
                else if(this._numberAutomaton.wordAdmitted(word).equals("final")) sb.append(" entier");
                else {
                    if(word.equals(";")) sb.append(word);
                    else sb.append(" ").append(word);
                }
            }
        }
        return sb.toString().trim();
    }
}
