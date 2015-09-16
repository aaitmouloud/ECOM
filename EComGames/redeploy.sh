#/bin/sh
mvn install -DskipTests && cd EComGames-ear/ && mvn -Dglassfish.directory=/home/seb/glassfish-4.1/glassfish glassfish:redeploy && cd ..
