# DM Langage Algébrique - Ladislas WALCAK

## Lancement des programmes
Vous trouverez une version compilé en fichier .jar dans le dossier `out/`.  
Le dossier `resources/` contient le sujet, le PDF de rendu ainsi que les annexes utilisées durant le développement.

### Automaton.jar
le fichier .jar contenu dans le dossier automaton_jar est la programme qui concerne la partie 1.  
Afin de le lancer, rendez vous dans le dossier (out/automaton_jar/), puis effectuez la commande :  

`java -jar dm_langage_algebrique_walcak.jar "[path]" `  

Il faudra remplacer l'argument [path] par le chemin (absolu de préférence) vers un fichier contenant de l'ALG01.  
 
Le programme est divisé tel que :
 - La classe ALG01 contient les deux automates pour la reconnaissance des nombres & des identifiers.  
 - Les automates sont composé d'un Axiome, sous forme de String, ainsi que d'une liste d'Etat. La gestion des transitions est délégué à la classe Transition.  
 - Les transitions contiennent un état de départ, un état d'arriver ainsi qu'une liste de characteres possibles pour cette transition.  
 
 Grâce à cette décomposition des données, les functions peuvent être plus courtes en déléguant les actions à leurs attributs, jusqu'a obtenir des tâches très simples.
 
 ### Analyseur.jar
 Le fichier .jar contenu dans le dossier analyseur_jar est le programme qui concerne la partie 2.2.  
 Afin de le lancer, rendez vous dans le dossier (out/analyseur_jar/), puis effectuez la commande :  
 
 `java -jar dm_langage_algebrique_walcak.jar`    
 
 Le programme lancera l'analyse des la grammaire donné dans classe MainAnalyseur, puis affichera le rendu.  
 La fonction `getNext()`, utilisé pour calculer l'ensemble *suivant*, ne semble pas être correcte car elle retourne  
 des ensembles très différents des ensembles calculés à la main avec la grammaire G'. De plus, ce blocage m'a empêché  
 de continuer. C'est pour cela qu'il n'y a pas de menu de choix, et que la grammaire doit être modifier à la main dans le fichier MainAnalyser.
 
Les différentes informations de cette grammaire sont stockés dans des Set, afin qu'il ne puisse pas y avoir de répétition d'informations.  
De plus les règles de productions sont stockées dans une Map, avec comme valeur le non-terminal de cette production, et en valeur, une liste de règles possibles.    
   