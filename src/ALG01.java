import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ALG01 {

    private static final Collection<String> _OPERATORS = Arrays.asList(
            "*", "-", "+", "/", "%",
            "<", "<=", ">", ">=", "==", "<-");

    private static final Collection<String> _SPECIALS = Arrays.asList(
            ".", ";", "[", "]");

    private final Automate _numberAutomaton;
    private final Automate _identAutomaton;

    /**
     * Constructor
     *
     * @param numberAutomaton Automaton used for number recognition
     * @param identAutomaton Automaton used for word recognition
     */
    public ALG01(Automate numberAutomaton, Automate identAutomaton) {
        this._numberAutomaton = numberAutomaton;
        this._identAutomaton = identAutomaton;
    }

    /**
     * Function used to replace code with tokens
     *
     * @param file The file from which the code is extracted
     * @return String with correct tokens
     * @throws FileNotFoundException If the given file cause error (doesn't exist, unable to read...)
     * @throws InvalidCharacterException If an illegal character is found in the code
     */
    public String execute(File file) throws FileNotFoundException, InvalidCharacterException {
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        String currentLine;
        while(sc.hasNext()) {
            currentLine = sanitize(sc.nextLine());
            String[] words = currentLine.split(" ");
            for(String word : words) {

                //Ignore spaces
                if(word.isBlank()) continue;

                String admitted = this._identAutomaton.wordAdmitted(word);

                if(admitted.equals("keyWord")) sb.append(" ").append(word);
                else if(admitted.equals("final")) sb.append(" ident");
                else if(this._numberAutomaton.wordAdmitted(word).equals("final")) sb.append(" entier");
                else if(_OPERATORS.contains(word)) sb.append(" ").append(word);
                else if(_SPECIALS.contains(word)) sb.append(word);
                else if(!word.equals("")) {
                    throw new InvalidCharacterException("Invalid character found : " + word);
                }
            }
        }
        return compact(sb.toString()).trim();
    }

    /**
     * Function to add spaces before and after special characters
     *
     * @param s String to sanitize
     * @return The sanitized String
     */
    private String sanitize(String s) {
        return s.replaceAll(";"," ; ")
                .replaceAll("\\."," . ")
                .replaceAll("\\+", " + ")
                .replaceAll("\\*", " * ")
                .replaceAll("-"," - ")
                .replaceAll("/"," / ")
                .replaceAll("%", " % ")
                .replaceAll("\\[", " [ ")
                .replaceAll("]", " ] ")
                .replaceAll("<", " < ")
                .replaceAll(">", " > ")
                .replaceAll("==", " == ")
                .replaceAll("< -","<-");
    }

    /**
     * Function to remove unnecessary spaces after treatment
     *
     * @param s String to sanitize
     * @return String without useless spaces
     */
    private String compact(String s){
        return s.replaceAll("\\[ *", " [ ")
                .replaceAll(" *] *"," ] ")
                .replaceAll(" *; *"," ; ")
                .replaceAll(" *\\+"," + ")
                .replaceAll(" +- *"," - ")
                .replaceAll(" *\\* *"," * ")
                .replaceAll(" */ *"," / ")
                .replaceAll(" *\\+ *"," + ")
                .replaceAll(" *\\. *",".")
                .replaceAll(" +<-"," <- ")
                .replaceAll("< -","<-");


                /* decommenter ça pour enlever les espaces apre_s les bool
                .replaceAll(" *== *","==")
                .replaceAll(" +< +","<")
                .replaceAll(" +> +",">");
                */

    }
}
