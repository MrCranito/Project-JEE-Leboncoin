Il faut d�ployer l'application avec Glassfish car j'utilise les EJB, quelques param�trages sont requis.

R�p�rer le ficher bonecp-datasource.xml (situ� � la racine de l'archive) et �diter le pour modifier la valeur du username et du password en fonction de vos
identifiants mysql.

Il vous faudra ensuite rajouter ce ficher dans le r�pertoire /glassfish3/bin.

Pour finir ex�cutez le fichier nomm� asadmin (le fichier .bat si vous travaillez sous Windows, l'autre sinon). 

Une console de commandes s�ouvre alors. 

Sous Windows, vous allez devoir y taper la commande suivante : add-resources bonecp-datasource.xml

Sous Mac ou Linux, vous devrez probablement pr�ciser le chemin complet vers le fichier XML pour que la commande 

localise correctement votre fichier XML : add-resources /chemin/complet/vers/glassfish3/bin/bonecp-datasource.xml


