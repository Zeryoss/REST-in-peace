# REST-in-peace

REST-in-peace est une API REST de gestion de cimetière qui utilise [Spring Boot](http://projects.spring.io/spring-boot/).

Elle a été développée en tant que projet universitaire lors de ma deuxième année de Master Génie Informatique à l'UFR MIM de Metz.

Son objectif principal est de gérer un ensemble de pierres tombales dans un cimetierre. Une pierre tombale étant représentée par: 

- L'identité  de la personne enterrée
- Ses dates de naissance et de décès
- Un épitaphe
- Les coordonnées GPS de la pierre tombale (longitude et latitude)

#### Fonctionnalités

- Les méthodes HTTP classiques d'une API CRUD (GET, POST, PUT, DELETE) sont disponibles sur les routes ``/grave`` et ``/grave/{id}``
- Un système de recherche est disponible sur la route ``/grave`` en ajoutant le paramètre ``search`` comme ceci : ``/grave?search=MOT_RECHERCHÉ``
- Un export vers un tableur est disponible sur les routes ``/export/csv`` et ``/export/xlsx`` en fonction du format souhaité.
  Deux types d'export sont disponibles: léger et complet. Ils peuvent être précisés avec le paramètre ``type`` dans la requête comme ceci: ``/export/{format}?type=full`` ou ``/export/{format}?type=light``

## Documentation

Pour installer et démarrer le projet localement, suivez le guide [Getting started](GettingStarted.md).

Pour contribuer au projet, suivez [Contributing](Contributing.md) 
