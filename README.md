# Serious Meuporg

## Auteurs et Contributeurs

[<img alt="Adrien CASELLES" src="https://avatars2.githubusercontent.com/u/22172746?v=3&s=400" width="140">](https://github.com/adrien3) | [<img alt="Florentin NOËL" src="https://avatars1.githubusercontent.com/u/14941833?v=3&s=400" width="140">](https://github.com/flo5324) | [<img alt="Morgane TROYSI" src="https://avatars1.githubusercontent.com/u/15076317" width="140">](https://github.com/mtroysi) | [<img alt="Sara ZALARHE" src="https://avatars0.githubusercontent.com/u/17701146?v=3&s=400" width="140">](https://github.com/szalarhe)
------------------------|---------------------------|---------------------------|---------------------------|
[@adrien3](https://github.com/adrien3) |[@flo5324](https://github.com/flo5324) |[@mtroysi](https://github.com/mtroysi) |[@szalarhe](https://github.com/szalarhe)

## Description
Le but du projet Serious Meuporg est de réaliser une application web de gestion de tâches entièrement configurable par l’utilisateur afin de pouvoir s’adapter à tout type de domaine, de la gestion de projet à une plateforme d’e-pédagogie. On désire également introduire le concept de gamification afin de rendre ludique la réalisation des tâches et instaurer de la compétition via un système de classement.
 
En effet, dans l’optique d’un jeu sérieux, les utilisateurs deviennent des joueurs, les tableaux des guildes et les tâches des quêtes. En accomplissant une quête, le joueur gagne de l’argent et de l’expérience. L’expérience lui permet de monter de niveau et ainsi, en plus de l’argent récolté, d’avoir accès à différents objets en boutique. 
 
Nous avons choisi de nommer notre application “Game Master”, en référence aux Maîtres du Jeu chargés, dans l’univers des jeux, de guider les joueurs et de veiller au respect des règles.

Ce projet a été réalisé par [Adrien CASELLES](https://github.com/adrien3), [Florentin NOËL](https://github.com/flo5324), [Morgane TROYSI](https://github.com/mtroysi)  et [Sara ZALARHE](https://github.com/szalarhe) sous la direction de [Benoît DA MOTA](https://github.com/BenoitDamota) dans le cadre du projet annuel de M2 SILI à l’Université d’Angers.

## Installation
### Pré-requis
* Installer NodeJS  
```$ sudo apt-get install nodejs```
* Installer npm  
```$ sudo apt-get install npm```
* Installer maven (requis seulement pour lancer le projet sans passer par un IDE)  
```$ sudo apt-get install maven```
### Installation
* Cloner le projet  
```$ git clone https://github.com/mtroysi/serious-meuporg.git```
 
* Se placer dans le répertoire du projet et installer les dépendances  
```$ npm install```  
```$ bower install```  

### Erreurs possibles
Lors de l’exécution de la commande ```bower install```, deux erreurs sont possibles :  
* La commande est introuvable : lancer la commande ```npm install -g bower``` et recommencer  
* ```/usr/bin/env: node: No such file or directory``` : lancer la commande ```ln -s /usr/bin/nodejs /usr/bin/node``` et recommencer  

## Lancement de l'application  
* Démarrer le back de l’application soit en passant par votre IDE, soit en ligne de commande  
```$ mvn spring-boot:run```  
 
* Démarrer le front de l’application  
```$ gulp serve```  
L’application s’ouvre automatiquement dans un nouvel onglet de votre navigateur.  

