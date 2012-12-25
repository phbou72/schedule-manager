Architecture
============

# <img src="https://github.com/glo-ulaval/Architecture/blob/7c7a0627c9be33064b959c31808eac6f0a953f4c/schedulemanager/src/main/webapp/assets/img/logo.png?raw=true"/>

Membres de l'équipe
===================

- **Philippe Bourdages** - phbou72
- **William Fortin** - wfsolutions
- **Bruno Gagnon-Adam** - bgagnonadam
- **Jonathan Rochette** - jrochette
- **Vincent Séguin** - Supra128
- **Olivier Sylvain** - osylvain

Mot de passe temporaire pour l'utilisation de l'application
===========================================================

ROLE DE DIRECTEUR
**User :** NATAW
**Password :** pass

ROLE D'ENSEIGNANT
**User :** LULAM50 et plusieurs autres (voir Users.xml)
**Password :** pass

ROLE D'ADMINISTRATEUR
**User :** ADMIN
**Password :** admin

ROLE DE RESPONSABLE D'HORAIRE
**User :** NADUC33
**Password :** pass

Compte usager pour les e-mails
===========================================================

**User :** glo4003.arhitecture@gmail.com
**Password :** glo4003a

Lancer les tests d'intégration à partir de Maven en CLI 
=======================================================

Entrer dans la console : mvn clean integration-test

Lancer le serveur Tomcat 7 par ligne de commande
=======================================================

Entrer dans la console : mvn clean install tomcat7:run

Configuration d'Eclipse
=======================

Dans 'Java > Code Style > Organize Imports'
  * Number of STATIC imports needed for .* (e.g. 'java.lang.Math.*'): 0
	
Dans 'Java > Editor > Save Actions'
  * Cocher 'Perform the selected actions on save'
  * Cocher 'Format source code', select 'Format all lines'
  * Cocher 'Organize imports'
  * Cocher 'Additional actions'
    * Configurer les actions : 
      * Activer 'Remove trailing whitespace'
      * Activer 'Correct indentation'
      * Activer 'Use block in if/else/for ... - Always'
      * Désactiver 'Use modifier final where possible'
      * Activer 'Remove unused import'
  * Appliquer les mêmes modifications dans 'Javascript > Save action' lorsque possible
	
Dans 'General > Workspace'
  * Text file encoding:
    **Other: UTF-8

Dans 'Java > Code Style > Formatter'
  * Cliquer sur import et sélectionner le fichier Formatter.xml à la racine du projet
  * 

Dans 'Web' et 'XML' : 
  * Spécifier l'encodage en UTF-8 pour tous les languages
  * Spéficier 180 pour le nombre de caractères par ligne

Dans 'Validation' : 
  * Supprimer les validations non utilisées

Dans 'Maven > Installations' : 
  * Utiliser maven installé sur l'ordinateur

