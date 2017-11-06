Il faut déployer l'application avec Glassfish car j'utilise les EJB, quelques paramétrages sont requis.

Répérer le ficher bonecp-datasource.xml (situé à la racine de l'archive) et éditer le pour modifier la valeur du username et du password en fonction de vos
identifiants mysql.

Il vous faudra ensuite rajouter ce ficher dans le répertoire /glassfish3/bin.

Pour finir exécutez le fichier nommé asadmin (le fichier .bat si vous travaillez sous Windows, l'autre sinon). 

Une console de commandes s’ouvre alors. 

Sous Windows, vous allez devoir y taper la commande suivante : add-resources bonecp-datasource.xml

Sous Mac ou Linux, vous devrez probablement préciser le chemin complet vers le fichier XML pour que la commande 

localise correctement votre fichier XML : add-resources /chemin/complet/vers/glassfish3/bin/bonecp-datasource.xml


