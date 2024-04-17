## Projet de fractales 

Ce projet est un générateur de fractales graphique. Il permet en effet 
- soit de générer une image avec les paramètres voulus
- soit d'ouvrir une interface graphique et de s'amuser avec les paramètres


## Compilation et exécution

Pour compiler le programme il suffit de faire à la racine du projet la commande 
```
gradle build
 ```
Ensuite pour exécuter le programme il y a deux options :
Si l'on veut jouer avec l'interface graphique
```
gradle run
```
Et si l'on veut juste générer une image, alors il faut choisir les paramètres
On commence par choisir le fractale à générer avec --set=Julia ou --set=Mand
Ensuite on donne le nombre d'itérations max : --iteration=300

Et si on a choisi julia il faut préciser les valeurs de la constante c : constante=0.3+0.4

On peut faire par exemple :

```
gradle run --args="--set=Mand --iteration=2000"
gradle run --args="--set=Julia --iteration=1000 --constante=-0.7+0.2"
```

## Architecture du projet

Au niveau de l'architecture globale du projet, on a implémenté un projet gradle pour structurer le code, automatiser la compilation et dans l'avenir si quelqu'un voudrait continuer le projet, utiliser des librairies, les implémenter facilement.
Au niveau de l'application on a utilisé le modèle MVC, pour bien compartimenter les différentes classes.


## Techniques de programmation

Le projet contient une belle interface graphique, une certaine rapidité de génération des images, une bonne palette de choix des paramètres, ainsi qu'un travail de sécurité de l'accès à des parties du code