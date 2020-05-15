package Grammaires;

import java.util.*;

public class Grammaire {

    private static final String EPSILON = "ε";

    private final Set<String> _terms = new HashSet<>();
    private final Set<String> _notTerms = new HashSet<>();
    private final Map<String, Set<String>> _productions;
    private final String _axiom;

    private Map<String, Set<String>> _first;
    private Map<String, Set<String>> _next;

    public Grammaire(String axiom, Collection<String> terms, Collection<String> notTerms, Map<String, Set<String>> productions) {
        this._axiom = axiom;
        this._terms.addAll(terms);
        this._notTerms.addAll(notTerms);
        this._productions = productions;
    }

    public void execute() {
        if(this._first == null) this._first = getFirst();
        if(this._next == null) this._next = getNext();

        printMap("Premier", this._first);
        printMap("Suivant", this._next);
    }

    private void printMap(String msg, Map<String, Set<String>> map) {
        System.out.println(msg + " :");
        for (String s : map.keySet()) {
            System.out.print(s + " { ");
            for (String v : map.get(s)) {
                System.out.print(v + " ");
            }
            System.out.println("}");
        }
        System.out.println("\n");
    }

    private boolean isEpsilon(String s) {
        return s.equals(EPSILON);
    }

    private Map<String, Set<String>> getFirst() {
        Map<String, Set<String>> res = new HashMap<>();

        res.put(this._axiom, processFirst(this._axiom));
        for (String nt : this._notTerms) {
            res.put(nt, processFirst(nt));
        }

        return res;
    }

    private Set<String> processFirst(String nt) {
        Set<String> res = new HashSet<>();

//        try {
//            for (String rule : this._productions.get(nt)) {
//
//                //Si la règle est X -> ε, alors on ajoute epsilon a premier
//                if(isEpsilon(rule)) {
//                    res.add(EPSILON);
//                    continue;
//                }
//
//                //Sinon, on split la règle pour obtenir toutes les characters
//                String[] splittedRule = rule.split(" ");
//
//                for (int i = 0; i < splittedRule.length; ++i) {
//
//                    String currentChar = splittedRule[i];
//
////                    System.out.println(splittedRule[i]);
//
//                    if(this._terms.contains(currentChar)) {
//                        res.add(currentChar);
//                        break;
//                    }
//                    else {
//                        if(!currentChar.equals(nt)) {
//                            Set<String> firstOfNt = processFirst(currentChar);
//                            res.addAll(firstOfNt);
//                            if(firstOfNt.contains(EPSILON)) {
//                                for (int j = i; j < splittedRule.length - 1; ++j) {
//                                    if(this._notTerms.contains(splittedRule[j])) {
//                                        res.addAll(processFirst(splittedRule[j]));
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (NullPointerException e) {
//            System.out.println("Invalid non terminal char : " + nt);
//        }
//        return res;
        if(this._terms.contains(nt)) {
            res.add(nt);
            return res;
        } else {
            for (String rule : this._productions.get(nt)) {
                if(isEpsilon(rule)) {
                    res.add("ε");
                }
            }
            for (String rule : this._productions.get(nt)) {
                boolean p = false;
                if(!(rule.split(" ")[0].equals(nt))) {
                    for (String first : this.processFirst(rule.split(" ")[0])) {
                        if(isEpsilon(first)) p = true;
                        else res.add(first);
                    }
                }
                if(p) {
                    int index = 0;
                    while ((index < rule.split(" ").length - 1) && p) {
                        ++index;
                        p = false;
                        for (String first : this.processFirst(rule.split(" ")[index])) {
                            if(isEpsilon(first)) p = true;
                            else res.add(first);
                        }
                    }
                }
            }
        }
        return res;
    }

    private Map<String, Set<String>> getNext() {
        Map<String, Set<String>> res = new HashMap<>();

        res.put(this._axiom, processNext(this._axiom));
        for (String nt : this._notTerms) {
            res.put(nt, processNext(nt));
        }
        
        return res;
    }

    private Set<String> processNext(String s){
        Set<String> res = new HashSet<>();
        if(this._axiom.equals(s)){
            res.add("$");
        }
        for(Set<String> rules : this._productions.values()) {

            for(String rule : rules) {

                if(rule.contains(s)) {

                    //On cherche l'elem suivant
                    String[] elems = rule.split(" ");

                    for(int i = 0; i < elems.length; ++i) {

                        if(elems[i].equals(s)) {
                            if(i < elems.length - 1) {
                                if(this._terms.contains(elems[i + 1])) res.add(elems[i + 1]);
                                else res.addAll(this.processFirst(elems[i + 1]));
                            }
                        }
                    }
                }

            }
        }
        if(res.isEmpty()){
            res.add("$");
        }
        return res;
    }
}
