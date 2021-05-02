# MyERP

## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)



### Lancement

    cd docker
    docker-compose up


### Arrêt

    cd docker
    docker-compose stop


### Remise à zero

    cd docker
    lancer le script deploy_docker_environment 
