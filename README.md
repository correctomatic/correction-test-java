

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
