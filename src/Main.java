import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        Etat s0e = new Etat(false),
             s1e = new Etat(true),
             s2e = new Etat(false),
             s3e = new Etat(true);

        s0e.addTransitions(
            new Transition(s1e, "0"),
            new Transition(s2e, "-"),
            new Transition(s3e, "1", "2", "3", "4", "5", "6", "7", "8", "9")
        );

        s2e.addTransitions(
            new Transition(s3e, "1", "2", "3", "4", "5", "6", "7", "8", "9")
        );

        s3e.addTransitions(
            new Transition(s3e, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        );

        Automate numberAutomaton = new Automate(s0e, s1e, s2e, s3e);

        System.out.println(numberAutomaton);

        ///////////////////////////////////////////////////////////////////////////////////////

        Etat s0i = new Etat(false),
             s1i = new Etat(true);

        s0i.addTransitions(
            new Transition(s1i,
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                               "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                               "w", "x", "y", "z")
        );

        s1i.addTransitions(
            new Transition(s1i,
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                               "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                               "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6",
                               "7", "8", "9")
        );


        Automate indentAutomaton = new Automate(s0i, s1i);
        indentAutomaton.addKeyWords(
                "program","end","break",
                "begin","while","for","if",
                "do","true","false","form",
                "not", "then","else","and","or"
        );

        System.out.print(indentAutomaton);

        ///////////////////////////////////////////////////////////////////////////////////////

        ALG01 analysis = new ALG01(numberAutomaton, indentAutomaton);

        if(args.length <= 0) System.out.println("Usage : java Main [Filepath]");
        else {
            try {
                System.out.println("\nResult :");
                System.out.println(analysis.execute(new File(args[0])));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();

            }
        }
    }
}
