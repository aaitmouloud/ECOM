#/bin/sh
mvn install && cd EComGames-ear/ && mvn -Dglassfish.directory=/home/cedric/glassfish-4.1/glassfish glassfish:redeploy && cd ..
