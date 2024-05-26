
export CLASSPATH=$CLASSPATH:/home/appnetix/LOG4J_HOME/

 docker run --rm -ti --mount type=bind,src=./ejemplos/ejemplo1,dst=/app ibmjava:8-sdk


docker build -t correction-test-java .

docker run --rm correction-test-java

docker run --rm -ti -v `pwd`/src:/app/src correction-test-java /bin/sh

docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR.java:/tmp/exercise \
  correction-test-java /bin/sh


docker run --rm -ti \
  -v `pwd`/exercises/EmpleadoBR_compile_error.java:/tmp/exercise \
  -v `pwd`/run_tests.sh:/app/src/run_tests.sh \
  correction-test-java


/app/classes/empleados

https://www.baeldung.com/java-commons-lang-3

https://spring.io/guides/gs/maven


mvn package --offline -DskipTests
ls -l ~/.m2/repository
settings.xml


mvn dependency:list


# Run Tests: Run your tests using the mvn test command. S

Optimize size:
https://whitfin.io/blog/speeding-up-maven-docker-builds/


mvn archetype:generate -DgroupId=com.example -DartifactId=my-project -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


Surefire report schema:
https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report.xsd


ASI SI QUE PASAN:
docker run --rm -ti --mount type=bind,src=`pwd`/src,dst=/app/src correction-test-java /bin/bash
