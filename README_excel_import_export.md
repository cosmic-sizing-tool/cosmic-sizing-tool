# Excel import

Cette fonctionnalité n'est pas terminée.

## Ce qui a été fait

- drag and drop de fichier dans la fenêtre du navigateur
- lecture d'un fichier excel localement
- parsing du fichier JSON extrait à partir du .xlsx pour extraire les données des mesures
- du code propre dans la limite de ce qu'on connait du langage javascript
- le bouton "Envoyer" appelle la fonction qui crée le fichier JSON a partir d'un array d'objets de type mesures ayant un structure brute et l'affiche dans un output pour débogage

## Ce qui n'a pas été fait:

- une interface professionnelle, car on n'avait pas l'expérience pour faire cela en si peu de temps
- il manque des éléments dans l'interface pour respecter les demandes de la cliente -> manque de temps
- la validation des données pour chaque ligne(mesure) extraite
- si des colonnes sont vides, les données de la ligne précédente ne sont pas utilisées tel que spécifié par la cliente
- le format ne respecte pas ce que l'équipe de l'entrée de données des mesures nous a fourni -> on a eu cela tard dans la dernière itération
- validation des informations entrées dans les champs du projet
- intégration avec le reste du logiciel -> pour le moment la page est indépendante du reste du logiciel, l'intégration ne devrait pas être trop compliquée à faire
- le bouton "Envoyer" devrait être affiché seulement quand toutes les options nécessaires ont été choisies
- le bouton "Envoyer" devrait créer les JSON du bon format (tel que spécifié par l'équipe des mesures et l'équipe du projet) puis envoyer ces fichiers au serveur et afficher une confirmation de l'envoi des données puis rediriger à la bonne page
- le choix des colonnes facultatives n'a pas été programmé par manque de temps

## Bugs

- L'interface ne fonctionnait pas sur Firefox à cause d'un problème d'événements javascript lorsqu'un lien est cliqué. Le code a été nettoyé et ça devrait fonctionner en principe, mais ça n'a pas été testé.
- Si on clique sur envoyer sans avoir sélectionné toutes les colonnes d'association, ça plante. C'est normal, la validation n'a pas été implémentée. Tel que spécifié, le bouton envoyer devrait seulement apparaitre lorsqu'une bonne combinaison d'options sont sélectionnées.

## Limitations

Tout le traitement du fichier Excel se fait du côté client avec l'API [js-xlsx](https://github.com/SheetJS/js-xlsx). Cet API est open-source et a des limitations par rapport au traitement de cellules contenant des formules.

Si le traitement des formules est nécessaire, il est suggéré d'utiliser l'API [Apache POI](https://poi.apache.org/) du côté serveur pour télécharger(upload) le fichier Excel puis retourner un fichier JSON au client au lieu de tout faire du côté client. Cela risque d'être un peu plus lent puisqu'une communication avec le serveur est effectuée. Le code du côté client fonctionnera quand même, c'est seulement l'étape initiale (lecture du fichier) qui sera faite du côté serveur pour avoir le bon contenu des cellules avec des formules. Les librairies d'Apache POI sont déjà incluses dans le projet PLAY. Voir le commit [f0279a42b7e2353b2dc8e9de81fa413b69508905](https://github.com/cosmic-sizing-tool/cosmic-sizing-tool/commit/f0279a42b7e2353b2dc8e9de81fa413b69508905).

# Excel export

Cette fonctionnalité n'a pas été implémentée par manque de temps. Deux options sont possibles:

1. Télécharger l'information sous format JSON sur le client et créer le fichier Excel localement avec l'API [js-xlsx](https://github.com/SheetJS/js-xlsx).
2. Créer le fichier Excel sur le serveur avec l'API [Apache POI](https://poi.apache.org/) et le télécharger directement sur le client.