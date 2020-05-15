import Grammaires.Grammaire;

import java.util.*;

public class MainAnalyseur {

    public static void main(String[] args) {

        Map<String, Set<String>> prod = new HashMap<>();
        prod.put("S", new HashSet<>(Arrays.asList("program ident begin LI end")));
        prod.put("LI", new HashSet<>(Arrays.asList("; I LI'")));
        prod.put("LI'", new HashSet<>(Arrays.asList("ε", "; LI")));
        prod.put("I", new HashSet<>(Arrays.asList("Affectation", "While", "For", "If", "break")));
        prod.put("Affectation", new HashSet<>(Arrays.asList("ident <- Affectation'")));
        prod.put("Affectation'", new HashSet<>(Arrays.asList("Expression", "ValBool")));
        prod.put("While", new HashSet<>(Arrays.asList("while BExpression do LI end")));
        prod.put("For", new HashSet<>(Arrays.asList("for ident from Valeur to Valeur do LI end")));
        prod.put("If", new HashSet<>(Arrays.asList("if BExpression then LI else LI end")));
        prod.put("ValBool", new HashSet<>(Arrays.asList("true", "false")));
        prod.put("BExpression", new HashSet<>(Arrays.asList("CombExpression BExpression'")));
        prod.put("BExpression'", new HashSet<>(Arrays.asList("or BExpression", "ε")));
        prod.put("CombExpression", new HashSet<>(Arrays.asList("NotExpression AndExpression")));
        prod.put("NotExpression", new HashSet<>(Arrays.asList("not NotExpression", "BoolExpression")));
        prod.put("AndExpression", new HashSet<>(Arrays.asList("and CombExpression", "ε")));
        prod.put("BoolExpression", new HashSet<>(Arrays.asList("ValBool", "Condition")));
        prod.put("Expression", new HashSet<>(Arrays.asList("TExpression Expression'")));
        prod.put("Expression'", new HashSet<>(Arrays.asList("OpPP Expression", "ε")));
        prod.put("TExpression", new HashSet<>(Arrays.asList("NExpression TExpression'")));
        prod.put("TExpression'", new HashSet<>(Arrays.asList("OpP TExpression", "ε")));
        prod.put("NExpression", new HashSet<>(Arrays.asList("VarNum", "( Expression )")));
        prod.put("OpP", new HashSet<>(Arrays.asList("*", "/")));
        prod.put("OpPP", new HashSet<>(Arrays.asList("+", "-")));
        prod.put("VarNum", new HashSet<>(Arrays.asList("ident varNum'", "entier")));
        prod.put("VarNum'", new HashSet<>(Arrays.asList("[ Expression ]", "ε")));
        prod.put("Valeur", new HashSet<>(Arrays.asList("ident", "entier")));
        prod.put("Condition", new HashSet<>(Arrays.asList("Expression OpRel Expression")));
        prod.put("OpRel", new HashSet<>(Arrays.asList("<=", "<", "=", ">", ">=", "!=")));

        Grammaire g = new Grammaire(
                "S",
                Arrays.asList("program", "ident", "begin", "end", "ε", "while", "for", "if", "<-", "true", "false", "break",
                              "do", "to", "not", "or", "and", "from", "then", "else", "*", "-", "+", "/", "[", "]", "(", ")",
                              "<=", "<", ">", ">=", "=", "!=", ";", "entier"),
                Arrays.asList("LI", "LI'", "I", "Affectation", "Affectation'", "While", "For", "If", "ValBool", "BExpression", "BExpression'",
                              "CombExpression", "NotExpression", "AndExpression", "BoolExpression", "Expression", "Expression'", "OpRel",
                              "TExpression", "TExpression'", "NExpression", "OpP", "OpPP", "VarNum", "VarNum'", "Valeur", "Condition"),
                prod);
        g.execute();
    }
}
