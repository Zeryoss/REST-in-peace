# Installation et démarrage

Suivez ce guide rapide pour lancer le projet sur votre ordinateur.

## Prérequis

Pour récupérer le code et démarrer l'application vous aurez besoin de :

- [Git](https://git-scm.com/downloads)
- [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6](https://maven.apache.org) (Voir [how to install Maven](https://howtodoinjava.com/maven/how-to-install-maven-on-windows/), en anglais)

Un environnement de développement intégré (IDE) est fortment recommandé.
Par exemple [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/).

## 1. Clonage du dépôt git

```shell
git clone https://github.com/gilleshz/REST-in-peace
```

## 2. Démarrage de l'application

Vous pouvez démarrer l'application via votre IDE ou par ligne de commande.

#### 2.1. Lancement avec IntelliJ IDEA

Avec IntelliJ IDEA il suffit d'exécuter la fonction `main` de la classe `fr.univlorraine.gheintz.RESTinpeace.RestInPeaceApplication`

#### 2.2 Lancement sans IDE

Exécutez la commande suivante pour démarrer le projet sans IDE.

```shell
mvn spring-boot:run
```

## 3. Accéder à l'API

Quand l'application a été démarrée, l'API est accessible à l'uri suivant : http://localhost:8080/

Vous pouvez aussi consulter les routes disponibles sur Swagger à l'adresse :  http://localhost:8080/swagger-ui.html

## 4. Contribuer

Si vous souhaitez contribuer au projet, consultez le guide [Contributing](Contributing.md).
